# MyVideoGame

A small text-based RPG in Java: explore three connected locations, take on a quest, fight enemies, and shop for gear.

## Running it

```bash
javac -d out *.java
java -cp out Main
```

## How to play

You start in the **Village**, connected to a **Dark Forest** and an **Ancient Castle**.

| Command | What it does |
|---|---|
| `look` | Describe your surroundings |
| `go <location>` | Travel to a connected location |
| `talk <npc>` | Talk to someone here (accept/turn in quests) |
| `shop` | See what's for sale here |
| `buy <item>` | Buy an item from a shop here |
| `fight` | Fight the enemy here, if any |
| `inventory` / `inv` | View your inventory |
| `use <item>` | Use a potion or equip a weapon |
| `quests` | View your accepted quests |
| `status` | View your character stats |
| `quit` / `exit` | End the game |

The Innkeeper in the Village offers a quest to retrieve the Legendary Sword from the Forest Monster; defeat it, then talk to the Innkeeper again to turn the quest in. The Merchant sells potions and weapons. The Ancient Castle holds a tougher boss fight.

## Design

- `Character` (abstract) → `Player`, `Enemy` — shared health/leveling/combat stats
- `Location` — a node in the map graph, holding NPCs, an optional `Enemy`, and connections to other locations
- `NPC` — dialogue, an optional quest to give, and an optional shop inventory
- `Item` / `Inventory` — potions (heal), weapons (equip for a strength bonus), and quest items
- `Quest` — tracks status (available/accepted/completed) and an optional required item for turn-in
- `Combat` — turn-based attack/counter-attack loop, awarding XP and gold on victory
- `GameEngine` / `Main` — wires the game world together and runs the command loop
