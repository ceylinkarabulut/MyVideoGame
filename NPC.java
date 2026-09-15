import java.util.ArrayList;
import java.util.List;

// Non-player character class
public class NPC {
    private final String name;
    private final String dialogue;
    private final List<Item> shopItems = new ArrayList<>();
    private Quest quest;

    public NPC(String name, String dialogue) {
        this.name = name;
        this.dialogue = dialogue;
        this.quest = null;
    }

    public void talk() {
        System.out.println("\n" + name + ": " + dialogue);
    }

    public void assignQuest(Quest quest) {
        this.quest = quest;
    }

    public Quest getQuest() {
        return quest;
    }

    public void addShopItem(Item item) {
        shopItems.add(item);
    }

    public List<Item> getShopItems() {
        return shopItems;
    }

    // Getters
    public String getName() { return name; }
    public String getDialogue() { return dialogue; }
}
