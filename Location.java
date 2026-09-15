import java.util.ArrayList;
import java.util.List;

// Location/Area class
public class Location {
    private final String id;
    private final String name;
    private final String description;
    private final List<NPC> npcs = new ArrayList<>();
    private final List<Location> connectedLocations = new ArrayList<>();
    private Enemy enemy;

    public Location(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public void connectLocation(Location location) {
        connectedLocations.add(location);
    }

    public NPC findNPC(String npcName) {
        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName)) return npc;
        }
        return null;
    }

    public Location findConnection(String locationName) {
        for (Location location : connectedLocations) {
            if (location.getName().equalsIgnoreCase(locationName)
                    || location.getId().equalsIgnoreCase(locationName)) {
                return location;
            }
        }
        return null;
    }

    public void displayLocation() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);

        if (enemy != null && enemy.isAlive()) {
            System.out.println("\nA " + enemy.getName() + " blocks your path! (type 'fight')");
        }

        if (!npcs.isEmpty()) {
            System.out.println("\nNPCs here:");
            for (NPC npc : npcs) {
                System.out.println("- " + npc.getName());
            }
        }

        if (!connectedLocations.isEmpty()) {
            System.out.println("\nConnected Locations:");
            for (Location location : connectedLocations) {
                System.out.println("- " + location.getName());
            }
        }
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<NPC> getNPCs() { return npcs; }
    public List<Location> getConnectedLocations() { return connectedLocations; }
    public Enemy getEnemy() { return enemy; }
    public void setEnemy(Enemy enemy) { this.enemy = enemy; }
}
