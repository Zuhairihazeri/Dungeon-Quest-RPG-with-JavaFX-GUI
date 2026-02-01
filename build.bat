@echo off
echo ========================================
echo  Dungeon Quest RPG - JavaFX GUI Build
echo ========================================
echo.

REM Check if JavaFX SDK is downloaded
if not exist "javafx-sdk" (
    echo JavaFX SDK not found. Please download it first.
    echo.
    echo Instructions:
    echo 1. Go to: https://gluonhq.com/products/javafx/
    echo 2. Download "JavaFX Windows SDK" latest version
    echo 3. Extract the ZIP file
    echo 4. Rename the extracted folder to "javafx-sdk"
    echo 5. Place it in the RPG Game directory
    echo.
    pause
    exit /b 1
)

echo JavaFX SDK found!
echo.
echo Compiling Java source files...
echo.

REM Clean and create bin directory
if exist "bin" rd /s /q "bin"
mkdir bin

REM Set JavaFX path
set JAVAFX_PATH=javafx-sdk\lib

echo Compiling modular JavaFX project...
powershell -Command "Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { '\"' + $_.FullName.Replace('\', '/') + '\"' } | Out-File -FilePath sources.txt -Encoding ascii"
javac --module-path "%JAVAFX_PATH%" -d bin @sources.txt

set BUILD_ERROR=%ERRORLEVEL%
del sources.txt

if %BUILD_ERROR% EQU 0 (
    echo.
    echo Copying resource files...
    
    REM Create resource directories in bin
    if not exist "bin\gui\fxml" mkdir "bin\gui\fxml"
    if not exist "bin\gui\resources" mkdir "bin\gui\resources"
    
    REM Copy FXML files
    xcopy /Y /Q "src\gui\fxml\*.fxml" "bin\gui\fxml\" >nul
    
    REM Copy CSS files
    xcopy /Y /Q "src\gui\resources\*.css" "bin\gui\resources\" >nul
    
    echo Resource files copied successfully!
    echo.
    echo ========================================
    echo  Compilation Successful!
    echo ========================================
    echo.
    echo Run 'run.bat' to start the game.
    echo.
) else (
    echo.
    echo ========================================
    echo  Compilation Failed!
    echo ========================================
    echo Please check the errors above.
    echo.
)

pause
