// Non-player character class
public class NPC {
    private String name;
    private String dialogue;
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

    // Getters
    public String getName() { return name; }
    public String getDialogue() { return dialogue; }
}