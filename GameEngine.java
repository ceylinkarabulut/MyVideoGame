// Main game engine
public class GameEngine {
    private Player player;
    private Location currentLocation;
    private Location[] locations;
    private int locationCount;
    private boolean gameRunning;

    public GameEngine() {
        this.locations = new Location[10];
        this.locationCount = 0;
        this.gameRunning = true;
        initializeGame();
    }

    private void initializeGame() {
        // Create locations
        Location village = new Location("village", "Village", "A peaceful village with friendly NPCs");
        Location forest = new Location("forest", "Dark Forest", "A mysterious forest filled with monsters");
        Location castle = new Location("castle", "Ancient Castle", "An old castle with treasures");

        addLocation(village);
        addLocation(forest);
        addLocation(castle);

        // Connect locations
        village.connectLocation(forest);
        village.connectLocation(castle);
        forest.connectLocation(village);
        castle.connectLocation(village);

        // Create NPCs
        NPC innkeeper = new NPC("Innkeeper", "Welcome to the inn! Would you like a quest?");
        Quest fetchQuest = new Quest("q1", "Fetch the Sword", "Retrieve the legendary sword from the forest", 500);
        innkeeper.assignQuest(fetchQuest);

        NPC merchant = new NPC("Merchant", "I sell items for gold!");
        NPC guardian = new NPC("Guardian", "Beware, evil lurks in this castle!");

        village.addNPC(innkeeper);
        village.addNPC(merchant);
        castle.addNPC(guardian);

        // Create player
        this.player = new Player("Hero", 100, 1);
        this.currentLocation = village;
        player.setLocation(village);
    }

    public void addLocation(Location location) {
        if (locationCount < locations.length) {
            locations[locationCount] = location;
            locationCount++;
        }
    }

    public void displayStatus() {
        player.displayStatus();
        System.out.println("\nCurrent Location: " + currentLocation.getName());
    }

    public void moveToLocation(String locationName) {
        for (int i = 0; i < currentLocation.getConnectionCount(); i++) {
            if (currentLocation.getConnectedLocations()[i].getName().equalsIgnoreCase(locationName)) {
                currentLocation = currentLocation.getConnectedLocations()[i];
                player.setLocation(currentLocation);
                currentLocation.displayLocation();
                return;
            }
        }
        System.out.println("Cannot reach " + locationName + " from here!");
    }

    public void startCombat(NPC enemy) {
        // Create a simple enemy character for combat
        Character enemyCharacter = new Character("Enemy", 30, 1) {};
        Combat combat = new Combat(player, enemyCharacter);
        combat.startCombat();

        while (combat.isCombatActive()) {
            combat.executeRound();
        }
    }

    public void exploreCurrentLocation() {
        currentLocation.displayLocation();
    }

    public void checkInventory() {
        player.getInventory().displayInventory();
    }

    // Getters
    public Player getPlayer() { return player; }
    public Location getCurrentLocation() { return currentLocation; }
    public boolean isGameRunning() { return gameRunning; }
    public void setGameRunning(boolean running) { this.gameRunning = running; }
}