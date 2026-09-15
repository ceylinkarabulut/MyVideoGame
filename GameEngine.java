import java.util.List;
import java.util.Scanner;

// Main game engine
public class GameEngine {
    private Player player;
    private Location currentLocation;
    private boolean gameRunning;

    public GameEngine() {
        this.gameRunning = true;
        initializeGame();
    }

    private void initializeGame() {
        // Create locations
        Location village = new Location("village", "Village", "A peaceful village with friendly NPCs");
        Location forest = new Location("forest", "Dark Forest", "A mysterious forest filled with monsters");
        Location castle = new Location("castle", "Ancient Castle", "An old castle with treasures");

        // Connect locations
        village.connectLocation(forest);
        village.connectLocation(castle);
        forest.connectLocation(village);
        castle.connectLocation(village);

        // Create NPCs
        Quest fetchQuest = new Quest("q1", "Fetch the Sword",
                "Retrieve the legendary sword from the forest", 500, "Legendary Sword");
        NPC innkeeper = new NPC("Innkeeper", "Welcome to the inn! Would you like a quest?");
        innkeeper.assignQuest(fetchQuest);

        NPC merchant = new NPC("Merchant", "I sell items for gold!");
        merchant.addShopItem(new Item("Health Potion", "Restores 30 HP", 20, Item.ItemType.POTION, 30));
        merchant.addShopItem(new Item("Iron Sword", "A sturdy blade (+5 strength)", 150, Item.ItemType.WEAPON, 5));

        NPC guardian = new NPC("Guardian", "Beware, evil lurks in this castle!");

        village.addNPC(innkeeper);
        village.addNPC(merchant);
        castle.addNPC(guardian);

        // Populate enemies: the forest monster guards the quest item, the castle guardian is a boss fight
        forest.setEnemy(new Enemy("Forest Monster", 30, 2, 12, 3, 50, 60,
                new Item("Legendary Sword", "A blade of old legends", 0, Item.ItemType.QUEST, 0)));
        castle.setEnemy(new Enemy("Castle Guardian", 50, 4, 18, 8, 150, 120));

        // Create player
        this.player = new Player("Hero", 100, 1);
        this.currentLocation = village;
        player.setLocation(village);
    }

    public void displayStatus() {
        player.displayStatus();
        System.out.println("\nCurrent Location: " + currentLocation.getName());
    }

    public void moveToLocation(String locationName) {
        Location target = currentLocation.findConnection(locationName);
        if (target == null) {
            System.out.println("Cannot reach " + locationName + " from here!");
            return;
        }
        currentLocation = target;
        player.setLocation(target);
        target.displayLocation();
    }

    public void startCombat() {
        Enemy enemy = currentLocation.getEnemy();
        if (enemy == null || !enemy.isAlive()) {
            System.out.println("There's nothing to fight here.");
            return;
        }

        Combat combat = new Combat(player, enemy);
        combat.startCombat();
        while (combat.isCombatActive()) {
            combat.executeRound();
        }

        if (!enemy.isAlive()) {
            currentLocation.setEnemy(null);
            if (enemy.getLoot() != null) {
                player.getInventory().addItem(enemy.getLoot());
                System.out.println("You obtained: " + enemy.getLoot().getName() + "!");
            }
        }
        if (!player.isAlive()) {
            gameRunning = false;
        }
    }

    public void exploreCurrentLocation() {
        currentLocation.displayLocation();
    }

    public void checkInventory() {
        player.getInventory().displayInventory();
    }

    public void talkToNPC(String npcName, Scanner scanner) {
        NPC npc = currentLocation.findNPC(npcName);
        if (npc == null) {
            System.out.println("There's no one here by that name.");
            return;
        }
        npc.talk();

        if (!npc.getShopItems().isEmpty()) {
            System.out.println("(" + npc.getName() + " is selling. Type 'shop' to see their wares.)");
        }

        Quest quest = npc.getQuest();
        if (quest == null) return;

        switch (quest.getStatus()) {
            case AVAILABLE:
                System.out.println("Quest available: " + quest.getTitle() + " - " + quest.getDescription()
                        + " (Reward: " + quest.getReward() + " gold)");
                System.out.print("Accept quest? (y/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    player.acceptQuest(quest);
                }
                break;
            case ACCEPTED:
                if (quest.getRequiredItem() != null && player.getInventory().hasItem(quest.getRequiredItem())) {
                    player.getInventory().removeItem(quest.getRequiredItem());
                    player.completeQuest(quest);
                } else {
                    System.out.println("(Quest still in progress: " + quest.getTitle() + ")");
                }
                break;
            case COMPLETED:
                System.out.println("(You've already completed this quest.)");
                break;
            default:
                break;
        }
    }

    public void openShop() {
        for (NPC npc : currentLocation.getNPCs()) {
            if (!npc.getShopItems().isEmpty()) {
                System.out.println("\n=== " + npc.getName() + "'s Wares ===");
                for (Item item : npc.getShopItems()) {
                    System.out.println("- " + item);
                }
                return;
            }
        }
        System.out.println("No one here is selling anything.");
    }

    public void buyItem(String itemName) {
        for (NPC npc : currentLocation.getNPCs()) {
            for (Item item : npc.getShopItems()) {
                if (item.getName().equalsIgnoreCase(itemName)) {
                    if (player.getGold() < item.getValue()) {
                        System.out.println("You don't have enough gold.");
                        return;
                    }
                    player.removeGold(item.getValue());
                    player.getInventory().addItem(item);
                    System.out.println("Bought " + item.getName() + "!");
                    return;
                }
            }
        }
        System.out.println("That's not for sale here.");
    }

    public void useItem(String itemName) {
        Item item = player.getInventory().findItem(itemName);
        if (item == null) {
            System.out.println("You don't have that.");
            return;
        }
        switch (item.getType()) {
            case POTION:
                player.heal(item.getEffectAmount());
                player.getInventory().removeItem(item.getName());
                System.out.println("Used " + item.getName() + ". Health: "
                        + player.getHealth() + "/" + player.getMaxHealth());
                break;
            case WEAPON:
                player.equipWeapon(item);
                player.getInventory().removeItem(item.getName());
                System.out.println("Equipped " + item.getName() + "! Strength is now " + player.getStrength() + ".");
                break;
            default:
                System.out.println(item.getName() + " can't be used directly.");
        }
    }

    public void showQuests() {
        List<Quest> quests = player.getAcceptedQuests();
        if (quests.isEmpty()) {
            System.out.println("You haven't accepted any quests yet.");
            return;
        }
        System.out.println("\n=== Quests ===");
        for (Quest quest : quests) {
            System.out.println(quest);
        }
    }

    // Getters
    public Player getPlayer() { return player; }
    public Location getCurrentLocation() { return currentLocation; }
    public boolean isGameRunning() { return gameRunning; }
    public void setGameRunning(boolean running) { this.gameRunning = running; }
}
