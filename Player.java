import java.util.ArrayList;
import java.util.List;

// Player class extending Character
public class Player extends Character {
    private final Inventory inventory;
    private final List<Quest> acceptedQuests = new ArrayList<>();
    private int gold;

    public Player(String name, int maxHealth, int level) {
        super(name, maxHealth, level);
        this.inventory = new Inventory();
        this.gold = 100;
    }

    public void acceptQuest(Quest quest) {
        quest.accept();
        acceptedQuests.add(quest);
        System.out.println(name + " accepted quest: " + quest.getTitle());
    }

    public void completeQuest(Quest quest) {
        for (Quest accepted : acceptedQuests) {
            if (accepted.getId().equals(quest.getId())) {
                accepted.complete();
                this.gold += quest.getReward();
                this.gainExperience(quest.getReward() * 10);
                System.out.println(name + " completed quest: " + quest.getTitle());
                break;
            }
        }
    }

    public void equipWeapon(Item weapon) {
        this.strength += weapon.getEffectAmount();
    }

    public void displayStatus() {
        System.out.println("\n=== " + name + " Status ===");
        System.out.println("Level: " + level);
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("Experience: " + experience);
        System.out.println("Gold: " + gold);
        System.out.println("Strength: " + strength);
        System.out.println("Defense: " + defense);
    }

    // Getters and Setters
    public Inventory getInventory() { return inventory; }
    public int getGold() { return gold; }
    public void addGold(int amount) { this.gold += amount; }
    public void removeGold(int amount) { this.gold -= amount; }
    public List<Quest> getAcceptedQuests() { return acceptedQuests; }
}
