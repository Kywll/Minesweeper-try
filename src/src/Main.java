import java.util.Scanner;
import java.util.Random;

//Notes for future myself when you forget how it works:
//Tile layout is 5x5 and 0-24 or 0 based
//How I did it is to make the tiles by using a loop and creating a tile object each iteration.
//I made it in a way that it creates a new board everytime you use the terminal.
//I did that by having a Tile array which is tracked by the variable num(0-24)
//I used a nested loop to mimic the 5x5 board and I made a board 2d array where you store the-
//contents of 1 dimensional array and so you could easily access it by doing board[row][column]
//I used board[i][j] on a loop to access through the 2d array.


public class Main {
    public static void main(String[] args){
        Game game = new Game();

        // Call the game
        game.play();

        // Ask the player if they want to restart and if they said yes then restart the game
        game.askForRestart();

        // June 27, 2025 TO DO:
        // Make it so you can choose the board size and the number of bombs - done
        // Add Hint option - done
        // input validation and better hint system
        // TO BE CONTINUED:
        // Learn how to use graphics then implement some basic ones

    }
}
