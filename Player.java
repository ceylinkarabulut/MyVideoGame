// Player class extending Character
public class Player extends Character {
    private Inventory inventory;
    private int gold;
    private Quest[] acceptedQuests;
    private int questCount;

    public Player(String name, int maxHealth, int level) {
        super(name, maxHealth, level);
        this.inventory = new Inventory();
        this.gold = 100;
        this.acceptedQuests = new Quest[10];
        this.questCount = 0;
    }

    public void acceptQuest(Quest quest) {
        if (questCount < acceptedQuests.length) {
            quest.accept();
            acceptedQuests[questCount] = quest;
            questCount++;
            System.out.println(name + " accepted quest: " + quest.getTitle());
        }
    }

    public void completeQuest(Quest quest) {
        for (int i = 0; i < questCount; i++) {
            if (acceptedQuests[i].getId().equals(quest.getId())) {
                acceptedQuests[i].complete();
                this.gold += quest.getReward();
                this.gainExperience(quest.getReward() * 10);
                System.out.println(name + " completed quest: " + quest.getTitle());
                break;
            }
        }
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
    public Quest[] getAcceptedQuests() { return acceptedQuests; }
    public int getQuestCount() { return questCount; }
}