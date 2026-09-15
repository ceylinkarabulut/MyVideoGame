// Base character class
public abstract class Character {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int level;
    protected int experience;
    protected int strength;
    protected int defense;
    protected Location location;

    public Character(String name, int maxHealth, int level) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.level = level;
        this.experience = 0;
        this.strength = 10;
        this.defense = 5;
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - defense / 2);
        this.health -= actualDamage;
        if (this.health < 0) this.health = 0;
    }

    public void heal(int amount) {
        this.health = Math.min(maxHealth, health + amount);
    }

    public void gainExperience(int exp) {
        this.experience += exp;
        while (this.experience >= level * 100) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.experience = 0;
        this.maxHealth += 10;
        this.health = maxHealth;
        this.strength += 2;
        this.defense += 1;
    }

    public boolean isAlive() {
        return health > 0;
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getLevel() { return level; }
    public int getExperience() { return experience; }
    public int getStrength() { return strength; }
    public int getDefense() { return defense; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
}