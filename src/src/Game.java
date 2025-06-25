import java.util.Scanner;

public class Game {
    // Handles Player Interaction

    Board board = new Board(5, 5, 5);

    public int playerInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Pick 1-25 to reveal a tile: ");
        int choice = input.nextInt();

        return choice;
    }

    //Thought Process:
    //Make a Game Board where you can detect surroundings
    //Have Random Bombs
    //Have a number on a tile based on how many bombs nearby
    //Tile with revealed and unrevealed state


    // TO DO:
    // June 23, 2025 to do until June 25, 2025:
    // fix the use of objects and improve the overall cleanliness of the code


    public void play(){
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
