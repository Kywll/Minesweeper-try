Java Minesweeper
A Minesweeper game implemented in Java with both a console-based version and a Swing-based graphical user interface.
The project focuses on object-oriented design, recursive game logic, and event-driven programming.

Table of Contents:
Overview
Features
Project Structure
Installation
Usage
Controls
Technologies Used
Future Improvements
Overview

This project is a complete implementation of the classic Minesweeper game.
It separates core game logic from the user interface, allowing the same board logic to be used for both the console and GUI versions.

Players can configure the board size and number of bombs at runtime.
The GUI version includes visual indicators, a timer, flag mode, and restart functionality.

Features:
Customizable board size and bomb count
Safe first click (bombs are placed after the first move)
Recursive clearing of empty tiles
Flagging system with bomb counter
Win and lose detection
Console-based gameplay
Swing-based graphical interface
Timer and restart button in GUI
Hint system (one-time reveal of bomb locations)

Project Structure
src/
│
├── Main.java          Entry point of the program
├── Game.java          Manages gameplay flow and user input (console)
├── Board.java         Core game logic and board state
│
├── Tile.java          Abstract tile class
├── NormalTile.java    Non-bomb tile implementation
├── BombTile.java      Bomb tile implementation
│
├── GUI.java           Swing-based graphical user interface
│
├── assets/
│   ├── bomb.png
│   ├── flag.png
│   ├── smile.png
│   ├── dead.png
│   ├── sunglasses.png
│   └── lightbulb.png

Installation Requirements
Java Development Kit (JDK) 8 or higher
Java-compatible IDE or command-line environment

Steps to play:
Enter the board height, width, and bomb count when prompted.
The GUI version will launch automatically.

Controls:
Left-click: Reveal tile
Right-click: Flag or unflag tile
Flag button: Toggle flag mode
Restart button: Reset the game
Hint button: Reveal bomb locations once

Console Controls
Choose between reveal or flag actions
Select tiles using numeric indices
Game status is displayed after each move

Technologies Used:
Java
Java Swing
Object-Oriented Programming
Event-driven programming
Recursive algorithms
2D arrays

Future Improvements:
Difficulty presets (Beginner, Intermediate, Expert)
High score and best-time tracking
Improved hint system
Sound effects
Enhanced UI animations
Save and load game functionality
