import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(String itemName) {
        return items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public boolean hasItem(String itemName) {
        return findItem(itemName) != null;
    }

    public Item findItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) return item;
        }
        return null;
    }

    public List<Item> getItems() { return items; }

    public void displayInventory() {
        System.out.println("\n=== Inventory ===");
        if (items.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Item item : items) {
            System.out.println("- " + item);
        }
    }
}
