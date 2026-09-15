public class Item {
    public enum ItemType { WEAPON, POTION, QUEST }

    private final String name;
    private final String description;
    private final int value;
    private final ItemType type;
    private final int effectAmount; // heal amount for potions, strength bonus for weapons

    public Item(String name, String description, int value, ItemType type, int effectAmount) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.type = type;
        this.effectAmount = effectAmount;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getValue() { return value; }
    public ItemType getType() { return type; }
    public int getEffectAmount() { return effectAmount; }

    @Override
    public String toString() {
        return name + " - " + description + " (" + value + " gold)";
    }
}
