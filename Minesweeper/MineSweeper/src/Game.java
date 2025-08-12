import java.util.Scanner;

// Handles Player Interaction
public class Game {

    Board board;

    GUI graphics;

    Scanner input = new Scanner(System.in);


    public void chooseSettings(){
        // Ask player if they want to flag or not

        System.out.print("Type the Board Height: ");
        int boardHeight = input.nextInt();
        input.nextLine(); // used to clear lines on scanner input

        System.out.print("Type the Board Width: ");
        int boardWidth = input.nextInt();
        input.nextLine(); // used to clear lines on scanner input

        System.out.print("Type the Bomb Limit: ");
        int bombLimit = input.nextInt();
        input.nextLine(); // used to clear lines on scanner input

        // create a new board then return it
        this.board = new Board(boardHeight, boardWidth, bombLimit);

    }

    private void updateScreen(){
        //Display the board
        board.displayBoard();
        // Display the number of flags
        board.currentFlags();
    }

    private boolean hasGameEnded(String status){
        // how this works is left side is true and right side is lose so if the parameter is equals to win then it returns true
        boolean trueOrFalse = false;
        if(status.equals("Win")){
            trueOrFalse = true;
        }

        return trueOrFalse;
    }

    public String inputType(){
        // Ask player if they want to flag or not

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

        System.out.print("Type 1-" + board.boardSize + " to choose a tile: ");
        int tileNumber = input.nextInt();


        // Loops until you get the desired inputs
        while(tileNumber < 1 || tileNumber > board.boardSize){
            System.out.print("Type 1-" + board.boardSize + " to choose a tile: ");
            tileNumber = input.nextInt();

        }
        input.nextLine(); // used to clear lines on scanner input
        return tileNumber;
    }

    public void handlePlayerMove(){
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

    public String inputHint(){
        // Ask player if they want to use hint
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
        graphics = new GUI();

        // Makes the value of board to the returned new board from the function
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

        // Checks if user already used hint so they can't use it again
        String hinted = inputHint();
        // Actually play the game
        for(int i=1;i<board.boardSize;i++) {
            // Check if you've won
            if (!hasGameEnded(board.winLose())){
                // Asks the user if they want to get a hint
                if (hinted.equals("yes")){
                    board.hint();
                    hinted = "No";
                } else{
                    //Asks if the user wants to flag or reveal
                    handlePlayerMove();
                }
                //Display the board and the number of flags
                updateScreen();
            }
            else{
                // Ends the game if the play has won or lost
                break;
            }
        }
    }

    public void askForRestart(){
        // Ask the player to restart

        System.out.println("Type 'Yes' if you want to restart the game and 'No' if not: ");
        String restartChoice = input.nextLine().toLowerCase();

        if (restartChoice.equals("yes")){
            // If yes then call the play() function again and repeat from the start
            play();
        }
    }
}
