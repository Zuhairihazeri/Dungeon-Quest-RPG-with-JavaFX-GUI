# Dungeon Quest RPG

A console-based text RPG game written in Java featuring turn-based combat, character progression, and dungeon exploration.

## Features

⚔️ **Turn-Based Combat** - Fight against various enemies with strategic choices
📈 **Character Progression** - Level up, gain stats, and become stronger
🎒 **Inventory System** - Collect and manage items, weapons, and armor
🛡️ **Equipment** - Equip weapons and armor to boost your stats
🏪 **Shop** - Buy potions and equipment with earned gold
🐉 **Multiple Enemy Types** - Face Goblins, Orcs, Trolls, Dark Knights, and Dragons

## Requirements

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) 8 or higher

## Installation & Setup

### Step 1: Verify Java Installation

Check if you have Java installed by opening Command Prompt and running:

```bash
java -version
```

You should see output showing Java version 1.8.0 or higher.

### Step 2: Install or Configure JDK

The game requires the Java Development Kit (JDK) to compile. If `javac` is not found:

**Option A: Install JDK**
1. Download JDK from: https://www.oracle.com/java/technologies/downloads/
2. Install the JDK
3. Add JDK's `bin` directory to your system PATH

**Option B: Use Existing JDK**
If you already have JDK installed but it's not in PATH:
1. Find your JDK installation (common locations):
   - `C:\Program Files\Java\jdk*`
   - `C:\Program Files (x86)\Java\jdk*`
2. Add the JDK's `bin` directory to your PATH environment variable

### Step 3: Compile the Game

**On Windows:**
```bash
build.bat
```

**On Linux/Mac:**
```bash
chmod +x build.sh
./build.sh
```

### Step 4: Run the Game

**On Windows:**
```bash
run.bat
```

**On Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

## How to Play

### Main Menu
- **New Game** - Start a new adventure
- **How to Play** - View game instructions
- **Exit** - Quit the game

### Adventurer's Camp
- **Explore Dungeon** - Fight enemies and progress through the dungeon
- **Character Status** - View your stats and equipment
- **Inventory & Equipment** - Manage items and equip gear
- **Shop** - Buy potions and equipment
- **Rest** - Restore HP to full
- **Quit Game** - Exit to main menu

### Combat
- **Attack** - Deal damage to the enemy
- **Use Item** - Use potions to heal during battle
- **Flee** - Attempt to escape (50% success rate)

### Progression
- Defeat enemies to earn Experience Points (EXP) and Gold
- Level up to increase HP, Attack, and Defense stats
- Equip better weapons and armor to boost your combat stats
- Buy items from the shop to prepare for tougher battles

## Game Mechanics

### Character Stats
- **HP (Health Points)** - Your health, if it reaches 0 you lose
- **Attack** - Determines damage dealt to enemies
- **Defense** - Reduces damage taken from enemies
- **Level** - Increases stats when you level up
- **Experience** - Gained from defeating enemies
- **Gold** - Currency for buying items

### Enemy Types
- **Goblin** (Lv. 1) - Weak starter enemy
- **Orc** (Lv. 3) - Tougher mid-game foe
- **Troll** (Lv. 5) - Strong and durable
- **Dark Knight** (Lv. 7) - Powerful armored warrior
- **Dragon** (Lv. 10) - Ultimate boss enemy

### Items
**Potions:**
- Small Health Potion - Restores 30 HP
- Medium Health Potion - Restores 60 HP
- Large Health Potion - Restores 100 HP

**Weapons:**
- Wooden Sword - Basic starting weapon
- Iron Sword - Mid-tier weapon
- Steel Sword - High-tier weapon
- Legendary Blade - Ultimate weapon

**Armor:**
- Leather Armor - Basic protection
- Chainmail - Mid-tier armor
- Plate Armor - Heavy protection
- Dragon Scale Armor - Ultimate armor

## Project Structure

```
RPG Game/
├── src/
│   ├── Main.java                    # Entry point
│   ├── entities/
│   │   ├── Character.java           # Base character class
│   │   ├── Player.java              # Player character
│   │   └── Enemy.java               # Enemy character
│   ├── items/
│   │   ├── Item.java                # Item interface
│   │   ├── Equipment.java           # Equipment class
│   │   ├── Potion.java              # Potion class
│   │   └── Inventory.java           # Inventory manager
│   ├── factories/
│   │   ├── ItemFactory.java         # Creates items
│   │   └── EnemyFactory.java        # Creates enemies
│   ├── combat/
│   │   └── CombatSystem.java        # Combat logic
│   ├── game/
│   │   └── Game.java                # Game controller
│   ├── ui/
│   │   └── GameUI.java              # UI display
│   └── utils/
│       └── Utils.java               # Utility functions
├── bin/                              # Compiled .class files
├── build.bat                         # Windows build script
├── run.bat                           # Windows run script
├── build.sh                          # Linux/Mac build script
└── run.sh                            # Linux/Mac run script
```

## Tips for Success

1. **Start with the shop** - Buy better equipment early to make combat easier
2. **Equip items immediately** - Don't forget to equip weapons and armor from your inventory
3. **Use potions wisely** - Heal during tough battles to survive
4. **Rest often** - Rest at the camp to restore HP between battles
5. **Know when to flee** - If facing a tough enemy, fleeing might save your life
6. **Level scaling** - Enemies get stronger as you level up, keep upgrading your gear

## Troubleshooting

### "javac is not recognized"
- JDK is not installed or not in PATH
- Install JDK and add it to your system PATH variable

### "java is not recognized"
- JRE is not installed or not in PATH
- Install Java and add it to your system PATH variable

### Game won't compile
- Ensure all .java files are in the `src/` directory
- Check that you're running `build.bat` from the project root directory
- Verify JDK version is 8 or higher

### Colors not showing correctly
- The game uses ANSI color codes which work on most modern terminals
- If colors don't display, the game is still fully playable

## License

This is a free educational project. Feel free to modify and distribute.

---

**Enjoy your adventure in Dungeon Quest RPG! ⚔️🐉**
