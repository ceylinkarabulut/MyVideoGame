import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the Adventure! ===");
        engine.exploreCurrentLocation();
        printHelp();

        while (engine.isGameRunning()) {
            System.out.print("\n> ");
            if (!scanner.hasNextLine()) break;
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();
            String arg = parts.length > 1 ? parts[1].trim() : "";

            switch (command) {
                case "look":
                    engine.exploreCurrentLocation();
                    break;
                case "go":
                    engine.moveToLocation(arg);
                    break;
                case "talk":
                    engine.talkToNPC(arg, scanner);
                    break;
                case "fight":
                    engine.startCombat();
                    break;
                case "shop":
                    engine.openShop();
                    break;
                case "buy":
                    engine.buyItem(arg);
                    break;
                case "inventory":
                case "inv":
                    engine.checkInventory();
                    break;
                case "use":
                    engine.useItem(arg);
                    break;
                case "quests":
                    engine.showQuests();
                    break;
                case "status":
                    engine.displayStatus();
                    break;
                case "help":
                    printHelp();
                    break;
                case "quit":
                case "exit":
                    engine.setGameRunning(false);
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for options.");
            }

            if (engine.isGameRunning() && !engine.getPlayer().isAlive()) {
                System.out.println("\nYou have died. Game over.");
                engine.setGameRunning(false);
            }
        }

        scanner.close();
    }

    private static void printHelp() {
        System.out.println("\nCommands:");
        System.out.println("  look             - describe your surroundings");
        System.out.println("  go <location>    - travel to a connected location");
        System.out.println("  talk <npc>       - talk to someone here");
        System.out.println("  shop             - see what's for sale here");
        System.out.println("  buy <item>       - buy an item from a shop here");
        System.out.println("  fight            - fight the enemy here, if any");
        System.out.println("  inventory / inv  - view your inventory");
        System.out.println("  use <item>       - use or equip an item");
        System.out.println("  quests           - view your accepted quests");
        System.out.println("  status           - view your character status");
        System.out.println("  help             - show this list");
        System.out.println("  quit / exit      - end the game");
    }
}
