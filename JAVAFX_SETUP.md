# Dungeon Quest RPG - JavaFX GUI Setup

## Quick Start Guide

### Step 1: Download JavaFX SDK

1. Go to: **https://gluonhq.com/products/javafx/**
2. Download the appropriate JavaFX SDK for your operating system:
   - Windows: JavaFX Windows SDK
   - Mac: JavaFX macOS SDK
   - Linux: JavaFX Linux SDK
3. Extract the downloaded ZIP/archive file
4. Rename the extracted folder to `javafx-sdk`
5. Place the `javafx-sdk` folder **inside your RPG Game directory**

Your folder structure should look like this:
```
RPG Game/
├── javafx-sdk/          ← Place it here!
│   └── lib/
├── src/
├── bin/
├── build.bat
└── run.bat
```

### Step 2: Compile the Game

**Windows:**
```bash
build.bat
```

**Linux/Mac:**
```bash
chmod +x build.sh
./build.sh
```

### Step 3: Run the Game

**Windows:**
```bash
run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

---

## Technical Details: Modular Structure

The project has been upgraded to a **Modular JavaFX Project**. This ensures better compatibility and fixes FXML loading issues.

**Key Changes:**
1. **`src/module-info.java`**: Defines the project module and permissions.
2. **`src/main/Main.java`**: The entry point is now in a package.
3. **`run.bat`**: Now uses the `--module` flag to launch the game smoothly.

Your folder structure should now look like this:
```
RPG Game/
├── bin/                 ← Compiled modular classes
├── javafx-sdk/          ← JavaFX libraries
├── src/
│   ├── module-info.java
│   └── main/Main.java
├── build.bat
└── run.bat
```
---

## What's New in the GUI Version?

### 🎨 Beautiful Visual Interface
- Dark fantasy theme with gradients and effects
- Animated health bars that change color based on HP
- Styled buttons with hover effects
- Combat log with scrolling text

### 🖱️ Point-and-Click Gameplay
- No more typing! Click buttons to navigate
- Visual inventory system
- Interactive shop interface
- Real-time stat updates

### 📊 Enhanced Information Display
- Progress bars for HP and EXP
- Clear stat comparisons (base vs equipped)
- Visual equipment slots
- Gold and level prominently displayed

### ⚔️ Improved Combat Experience
- See both player and enemy stats side-by-side
- Combat log shows all actions
- Smooth transitions between turns
- Victory/defeat dialogs with rewards

---

## Features

All features from the console version are preserved:
- ✅ Turn-based combat
- ✅ Character progression and leveling
- ✅ Inventory and equipment system
- ✅ Shop with multiple items
- ✅ Multiple enemy types
- ✅ Random loot drops

Plus new GUI-exclusive features:
- 🎨 Visual health bars
- 🖱️ Mouse-driven interface
- 📊 Real-time stat display
- 💬 Pop-up dialogs
- 🎮 Smooth screen transitions

---

## Troubleshooting

### "JavaFX SDK not found"
- Make sure you downloaded JavaFX SDK from https://gluonhq.com/products/javafx/
- Verify the folder is named exactly `javafx-sdk` (not `javafx-sdk-23.0.1`)
- Place it directly in the RPG Game folder

### "javac is not recognized"
- Make sure JDK is in your PATH (you already did this for the console version)
- Restart your terminal after adding JDK to PATH

### Graphics don't appear correctly
- Update your graphics drivers
- Try running on a different machine
- JavaFX requires Java 8+ and modern graphics support

### Module errors
- Make sure you're using JDK 11 or higher (you have JDK 25, which is perfect!)
- Verify JavaFX SDK matches your OS (Windows/Mac/Linux)

---

## System Requirements

- **Java JDK**: 11 or higher (you have 25.0.2 ✅)
- **JavaFX SDK**: 17 or higher
- **OS**: Windows 10+, macOS 10.12+, or Linux
- **RAM**: 512 MB minimum
- **Display**: 900x650 minimum resolution

---

## Enjoy Your New GUI RPG! 🎮⚔️

The game now has a beautiful graphical interface while keeping all the mechanics you love from the console version!
