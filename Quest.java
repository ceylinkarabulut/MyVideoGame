// Quest management
public class Quest {
    private String id;
    private String title;
    private String description;
    private int reward;
    private QuestStatus status;

    public enum QuestStatus {
        AVAILABLE, ACCEPTED, COMPLETED, FAILED
    }

    public Quest(String id, String title, String description, int reward) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.reward = reward;
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
    public QuestStatus getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Status: %s)",
                id, title, description, status);
    }
}