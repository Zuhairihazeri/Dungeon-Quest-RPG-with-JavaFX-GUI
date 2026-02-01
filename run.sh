#!/bin/bash
echo "========================================"
echo " Dungeon Quest RPG - JavaFX GUI"
echo "========================================"
echo ""
echo "Starting game..."
echo ""

# Set JavaFX path
JAVAFX_PATH="javafx-sdk/lib"

# Run with JavaFX
cd bin
java --module-path "../$JAVAFX_PATH" --add-modules javafx.controls,javafx.fxml Main
