// An enemy character encountered at a location, with combat rewards and optional loot.
public class Enemy extends Character {
    private final int goldReward;
    private final int expReward;
    private final Item loot;

    public Enemy(String name, int maxHealth, int level, int strength, int defense, int goldReward, int expReward) {
        this(name, maxHealth, level, strength, defense, goldReward, expReward, null);
    }

    public Enemy(String name, int maxHealth, int level, int strength, int defense,
                 int goldReward, int expReward, Item loot) {
        super(name, maxHealth, level);
        this.strength = strength;
        this.defense = defense;
        this.goldReward = goldReward;
        this.expReward = expReward;
        this.loot = loot;
    }

    public int getGoldReward() { return goldReward; }
    public int getExpReward() { return expReward; }
    public Item getLoot() { return loot; }
}
