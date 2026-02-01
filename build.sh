#!/bin/bash
echo "========================================"
echo " Dungeon Quest RPG - JavaFX GUI Build"
echo "========================================"
echo ""

# Check if JavaFX SDK is downloaded
if [ ! -d "javafx-sdk" ]; then
    echo "JavaFX SDK not found. Please download it first."
    echo ""
    echo "Instructions:"
    echo "1. Go to: https://gluonhq.com/products/javafx/"
    echo "2. Download 'JavaFX Linux/Mac SDK' latest version"
    echo "3. Extract the archive"
    echo "4. Rename the extracted folder to 'javafx-sdk'"
    echo "5. Place it in the RPG Game directory"
    echo ""
    exit 1
fi

echo "JavaFX SDK found!"
echo ""
echo "Compiling Java source files..."
echo ""

# Create bin directory if it doesn't exist
mkdir -p bin

# Set JavaFX path
JAVAFX_PATH="javafx-sdk/lib"

echo "Compiling modular JavaFX project..."
find src -name "*.java" > sources.txt
javac --module-path "$JAVAFX_PATH" -d bin @sources.txt
rm sources.txt

if [ $? -eq 0 ]; then
    echo ""
    echo "Copying resource files..."
    
    # Create resource directories in bin
    mkdir -p "bin/gui/fxml"
    mkdir -p "bin/gui/resources"
    
    # Copy FXML files
    cp src/gui/fxml/*.fxml bin/gui/fxml/
    
    # Copy CSS files
    cp src/gui/resources/*.css bin/gui/resources/
    
    echo "Resource files copied successfully!"
    echo ""
    echo "========================================"
    echo " Compilation Successful!"
    echo "========================================"
    echo ""
    echo "Run './run.sh' to start the game."
    echo ""
else
    echo ""
    echo "========================================"
    echo " Compilation Failed!"
    echo "========================================"
    echo "Please check the errors above."
    echo ""
fi
