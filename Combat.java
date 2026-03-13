// Combat system
public class Combat {
    private Character attacker;
    private Character defender;
    private int round;

    public Combat(Character attacker, Character defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.round = 0;
    }

    public void startCombat() {
        System.out.println("\n=== Combat Start: " + attacker.getName() + " vs " + defender.getName() + " ===");
    }

    public void executeRound() {
        round++;
        System.out.println("\n--- Round " + round + " ---");

        // Attacker attacks
        int damage = calculateDamage(attacker, defender);
        defender.takeDamage(damage);
        System.out.println(attacker.getName() + " deals " + damage + " damage!");
        System.out.println(defender.getName() + " health: " + defender.getHealth() + "/" + defender.getMaxHealth());

        if (!defender.isAlive()) {
            endCombat();
            return;
        }

        // Defender counter-attacks
        damage = calculateDamage(defender, attacker);
        attacker.takeDamage(damage);
        System.out.println(defender.getName() + " deals " + damage + " damage!");
        System.out.println(attacker.getName() + " health: " + attacker.getHealth() + "/" + attacker.getMaxHealth());

        if (!attacker.isAlive()) {
            endCombat();
        }
    }

    private int calculateDamage(Character attacker, Character defender) {
        int baseDamage = attacker.getStrength();
        int variance = (int)(Math.random() * 5) - 2; // -2 to +2
        int defense = defender.getDefense();
        return Math.max(1, baseDamage + variance - defense / 3);
    }

    private void endCombat() {
        System.out.println("\n=== Combat End ===");
        if (attacker.isAlive()) {
            System.out.println(attacker.getName() + " wins!");
            attacker.gainExperience(50);
        } else {
            System.out.println(defender.getName() + " wins!");
            defender.gainExperience(50);
        }
    }

    public boolean isCombatActive() {
        return attacker.isAlive() && defender.isAlive();
    }

    // Getters
    public Character getAttacker() { return attacker; }
    public Character getDefender() { return defender; }
    public int getRound() { return round; }
}
