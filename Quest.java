// Quest management
public class Quest {
    private final String id;
    private final String title;
    private final String description;
    private final int reward;
    private final String requiredItem; // item that must be in inventory to turn this in, or null
    private QuestStatus status;

    public enum QuestStatus {
        AVAILABLE, ACCEPTED, COMPLETED, FAILED
    }

    public Quest(String id, String title, String description, int reward) {
        this(id, title, description, reward, null);
    }

    public Quest(String id, String title, String description, int reward, String requiredItem) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.reward = reward;
        this.requiredItem = requiredItem;
        this.status = QuestStatus.AVAILABLE;
    }

    public void accept() {
        status = QuestStatus.ACCEPTED;
    }

    public void complete() {
        status = QuestStatus.COMPLETED;
    }

    public void fail() {
        status = QuestStatus.FAILED;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getReward() { return reward; }
    public String getRequiredItem() { return requiredItem; }
    public QuestStatus getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Status: %s)",
                id, title, description, status);
    }
}
