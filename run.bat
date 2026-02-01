@echo off
echo ========================================
echo  Dungeon Quest RPG - JavaFX GUI
echo ========================================
echo.
echo Starting game...
echo.

REM Set paths
set JAVAFX_PATH=javafx-sdk\lib
set BIN_PATH=bin

REM Run modular JavaFX application
java --module-path "%JAVAFX_PATH%;%BIN_PATH%" --module dungeon.quest/main.Main

pause
