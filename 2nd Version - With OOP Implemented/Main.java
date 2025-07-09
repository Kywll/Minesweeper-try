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
    public static int playerInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Pick 1-25 to reveal a tile: ");
        int choice = input.nextInt();

        return choice;
    }
    public static void main(String[] args){
        //Thought Process:
        //Make a Game Board where you can detect surroundings
        //Have Random Bombs
        //Have a number on a tile based on how many bombs nearby
        //Tile with revealed and unrevealed state


        // TO DO:
        // June 23, 2025 to do until June 25, 2025:
        // fix the use of objects and improve the overall cleanliness of the code

        Board board = new Board();

        // Display the board but only filler
        for(int i=0;i<5;i++){
            for (int j =0;j<5;j++){
                System.out.print("=");

            }
            System.out.println();
        }

        // Sample Board
        board.sampleBoard();

        System.out.println();


        // Put in in a variable so it doesn't ask for the input again after declaring the function
        int firstChoice = playerInput();

        // Create the board
        board.createBoard(firstChoice);

        // Input the players choice
        board.tileInput(firstChoice);

        //Display the board
        board.displayBoard();


        // Display some more info

        //for (Tile t : tiles){
        //    t.displayInfo();
        //}

        // Actually play the game
        for(int i=0;i<24;i++) {
            // Check if you've won
            String winLoseStatus = board.winLose();
            if (!winLoseStatus.equals("Win") && !winLoseStatus.equals("Lose")){
                //Ask for player input and actually play the game
                board.tileInput(playerInput());

                //Display the board
                board.displayBoard();
            }
            else{
                // Ends the game if the play has won or lost
                break;
            }
        }
    }
}
