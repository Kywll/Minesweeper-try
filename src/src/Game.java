import java.util.Scanner;

// Handles Player Interaction
public class Game {

    Board board;


    public void chooseSettings(){
        // Ask player if they want to flag or not
        Scanner input = new Scanner(System.in);

        System.out.print("Type the Board Height: ");
        int boardHeight = input.nextInt();

        System.out.print("Type the Board Width: ");
        int boardWidth = input.nextInt();

        System.out.print("Type the Bomb Limit: ");
        int bombLimit = input.nextInt();

        // Have to use this.board instead of Board board = new Board() because if you do that you then make another
        // board instead of just changing the values of the initialized board before in the object
        this.board = new Board(boardHeight, boardWidth, bombLimit);

    }

    public String inputType(){
        // Ask player if they want to flag or not
        Scanner input = new Scanner(System.in);

        System.out.print("Type 'F' if you want to flag a tile and 'R' if you want to reveal a tile: ");
        String flagOrReveal = input.nextLine();

        // Loops until you get the desired inputs
        while(!flagOrReveal.equals("f") && !flagOrReveal.equals("r")) {
            System.out.print("Type 'F' if you want to flag a tile and 'R' if you want to reveal a tile: ");
            flagOrReveal = input.nextLine();
        }

        return flagOrReveal.toLowerCase();
    }

    public int playerInput(){
        // Ask player if they want to flag or not
        Scanner input = new Scanner(System.in);

        System.out.print("Type 1-" + (board.boardHeight * board.boardWidth) + " to choose a tile: ");
        int tileNumber = input.nextInt();

        // Loops until you get the desired inputs
        while(tileNumber < 1 || tileNumber > (board.boardHeight * board.boardWidth)){
            System.out.print("Type 1-" + (board.boardHeight * board.boardWidth) + " to choose a tile: ");
            tileNumber = input.nextInt();
        }
        return tileNumber;
    }

    public String inputHint(){
        // Ask player if they want to use hint
        Scanner input = new Scanner(System.in);
        System.out.println("Type 'Yes' if you want to get a hint and 'No' if not: ");
        String hintOrNot = input.nextLine().toLowerCase();

        return hintOrNot;
    }

    //Thought Process:
    //Make a Game Board where you can detect surroundings
    //Have Random Bombs
    //Have a number on a tile based on how many bombs nearby
    //Tile with revealed and unrevealed state

    public void play(){
        // Choose the setup of the board
        chooseSettings();

        // Create the Board
        board.createBoard();

        System.out.println();

        // Put in in a variable so it doesn't ask for the input again after declaring the function
        int firstChoice = playerInput();

        // Add Bombs to the board based on the first position
            board.addBombs(firstChoice);

        // Input the players choice
            board.tileInput(firstChoice);

        //Display the board
            board.displayBoard();

        // Checks if user already used hint so the can't use it again
        boolean hinted = false;
        // Actually play the game
        for(int i=1;i<(board.boardHeight * board.boardWidth);i++) {
            // Check if you've won
            String winLoseStatus = board.winLose();
            if (!winLoseStatus.equals("Win") && !winLoseStatus.equals("Lose")){
                // Asks the user if they want to get a hint
                if(!hinted){
                    if (inputHint().equals("yes")){
                        board.hint();
                        hinted = true;
                    }
                } else{
                    // Asks the user if they want to flag or not each iteration
                    String flagOrReveal = inputType();
                    // Checks first if the user wants to flag or not
                    if (flagOrReveal.equals("f")){
                        // If yes then it flags it
                        board.flagTile(playerInput());
                    }
                    else if (flagOrReveal.equals("r")){
                        // If not then it reveals the tile
                        //Ask for player input and actually play the game
                        board.tileInput(playerInput());
                    }
                }

                //Display the board
                board.displayBoard();
                // Display the number of flags
                board.currentFlags();
            }
            else{
                // Ends the game if the play has won or lost
                break;
            }
        }
    }

    public void askForRestart(){
        // Ask the player to restart
        Scanner input = new Scanner(System.in);

        System.out.println("Type 'Yes' if you want to restart the game and 'No' if not: ");
        String restartChoice = input.nextLine().toLowerCase();

        if (restartChoice.equals("yes")){
            // If yes then call the play() function again and repeat from the start
            play();
        }
    }
}
