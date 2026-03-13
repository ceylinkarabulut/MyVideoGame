// Location/Area class
public class Location {
    private String id;
    private String name;
    private String description;
    private NPC[] npcs;
    private int npcCount;
    private Location[] connectedLocations;
    private int connectionCount;

    public Location(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.npcs = new NPC[5];
        this.npcCount = 0;
        this.connectedLocations = new Location[4];
        this.connectionCount = 0;
    }

    public void addNPC(NPC npc) {
        if (npcCount < npcs.length) {
            npcs[npcCount] = npc;
            npcCount++;
        }
    }

    public void connectLocation(Location location) {
        if (connectionCount < connectedLocations.length) {
            connectedLocations[connectionCount] = location;
            connectionCount++;
        }
    }

    public void displayLocation() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);

        if (npcCount > 0) {
            System.out.println("\nNPCs here:");
            for (int i = 0; i < npcCount; i++) {
                System.out.println("- " + npcs[i].getName());
            }
        }

        if (connectionCount > 0) {
            System.out.println("\nConnected Locations:");
            for (int i = 0; i < connectionCount; i++) {
                System.out.println("- " + connectedLocations[i].getName());
            }
        }
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public NPC[] getNPCs() { return npcs; }
    public int getNPCCount() { return npcCount; }
    public Location[] getConnectedLocations() { return connectedLocations; }
    public int getConnectionCount() { return connectionCount; }
}