@echo off
REM Create bin directory if it doesn't exist
if not exist bin mkdir bin

REM Compile all .java files in src directory and subdirectories, placing output in bin directory
javac -d bin src/algorithm/*.java src/MainGUI/*.java

REM Check if compilation was successful
if %errorlevel% neq 0 (
    echo Compilation failed.
) else (
    echo Compilation successful.
)

REM Pause to keep console window open
pause