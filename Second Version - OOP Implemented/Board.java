import java.util.Random;

public class Board {
    // Handles the game logic

    // TO BE CONTINUED
    // To do:
    // Make it abstract - done
    // Replace hardcoded numbers (5) with variables - done
    // Rename num to tileIndex and rename number to bombChance - done
    // Fix Constructors - done
    // Move main() logic into a Game class - done

    // clean out Game object and add appropriate functions?
    // Add input validation

    int boardHeight;
    int boardWidth;

    int bombLimit;

    int flagCounter;

    // Access the Tiles
    Tile[][] board;

    Board(int boardHeight, int boardWidth, int bombLimit){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.bombLimit = bombLimit;

        // Is needed because the constructors are not initialized yet and is 0 at default
        // THis is apparently needed when you determine the values on another class and not on the object itself
        this.board = new Tile[boardHeight][boardWidth];

        // Sets the number of bombs in the game to the flag counter
        this.flagCounter = bombLimit;
    }

    // BOARD SETUP
    public void createBoard(){
        int tileIndex = 0;
        //Make sample board
        for(int i=0;i<boardHeight;i++){
            for (int j =0;j<boardWidth;j++){
                // Create an object inside a 2d array on each iteration and uses the values of i and j to fill out the 2d array
                board[i][j] = new NormalTile(tileIndex, false);

                // Implements proper spacing
                if (tileIndex+1 <= 10){
                    System.out.print(tileIndex+1 + "  ");
                } else{
                    System.out.print(tileIndex+1 + " ");
                }

                tileIndex++;
            }
            System.out.println();
        }
    }

    public void addBombs(int startingSpot){
        // Creates a board

        Random random = new Random();

        // Determines if a tile should have a bomb or not
        int bombChance;

        // Current number of bombs
        int bombNumber = 0;

        // The current iteration of the loop or the index of the board
        int tileIndex = 0;

        // So the num in here is 0 based but the startingSpot is 1 based because it need to be used on the input.
        // What I did is to simply subtract 1 inside the createBoard function
        startingSpot -= 1;

        // A list of all the position of the tiles around the starting spot
        Tile[] neighbors = new Tile[8];

        // Create a nested for loop to access i and j for the board position then compares the position of the board to the result of the new row and column
        for(int i=0;i<boardHeight;i++){
            for (int j =0;j<boardWidth;j++) {
                // check if the input is on the current iteration
                if (tileIndex == startingSpot){
                    int[] rowDirection = {-1, -1, -1, 0, 0, 1, 1 ,1};
                    int[] columnDirection = {-1, 0, 1, -1, 1, -1, 0 ,1};

                    for (int n=0;n<neighbors.length;n++){
                        //pre added the positions
                        // Uses i and j which is the value of the row and column or the height and width
                        int newRow = i + rowDirection[n];
                        int newColumn = j + columnDirection[n];

                        if (newRow >= 0 && newRow < boardHeight && newColumn >= 0 && newColumn < boardWidth) {
                            // If not out of bounds then check insert the position to the neighbors list and if out of bounds it just puts a null so the index is still correct
                            neighbors[n] = board[newRow][newColumn];
                        }
                    }
                }
                tileIndex++;
            }
        }

        // MAKE THE BOARD
        tileIndex = 0;
        for(int i=0;i<boardHeight;i++){
            for (int j =0;j<boardWidth;j++){
                // Chooses a random number of 1 or 2 and if 1 then the current tile will be a bomb and if not then it will move on to the next and randomize again to choose
                bombChance = random.nextInt(0, 2);

                // Determines if the current board is next to the starting position
                boolean surrounding = false;

                // Loops and check if the current board is a neighbour of the starting position then returns true if yes
                for (int n=0;n<8;n++){
                    if (board[i][j] == neighbors[n] && neighbors[n] != null){
                        surrounding = true;
                        break;
                    }
                }

                // Checks if the current tile is not around the starting point
                // Check if out of bounds
                // Check first if bomb chance is 1 and bomb limit is not yet reached and the current tile is not clicked and it's not the starting point
                if (bombChance == 1 && bombNumber < bombLimit && !board[i][j].isClicked && tileIndex != startingSpot && !surrounding) {
                    // Adds a bomb on the current tile
                    board[i][j] = new BombTile(tileIndex, false);
                    bombNumber++;
                }

                tileIndex++;
            }
        }
    }

    // BOARD ACCESS
    public void displayBoard(){
        //Displays the Board
        // The loop checks for surrounding tiles if it was clicked or if there are bombs near it in 8 directions then prints it accordingly

        for(int i=0;i<boardHeight;i++){
            for (int j =0;j<boardWidth;j++){
                // Checks if the tile was Flagged
                if (!board[i][j].isFlagged){
                    // Checks if the tile was clicked
                    if (board[i][j].isClicked){
                        if (board[i][j].isBomb()){
                            System.out.print("@");
                        } else if (checkSurroundingBombs(i, j) == 0) {
                            System.out.print("0");
                        } else if (checkSurroundingBombs(i, j) != 0) {
                            System.out.print(checkSurroundingBombs(i, j));
                        }
                    } else {
                        System.out.print("=");
                    }
                } else{
                    System.out.print("?");
                }

                // Implements Spacing
                System.out.print(" ");

            }
            System.out.println();
        }
    }

    public void tileInput(int choice){
        // Loops and compares the num to the choice and if they are the same then the nested for loop's value will determine the tile's position on the board
        // choice-1 because it's 1 based but num is 0 based

        int tileIndex = 0;

        for (int i=0;i<boardHeight;i++){
            for (int j=0;j<boardWidth;j++){
                if (tileIndex == choice-1 && !board[i][j].isFlagged){
                    board[i][j].revealTile();
                    // Checks if there is surrounding bombs near the input and if there is none then you just clear everything around it on 8 directions
                    if (checkSurroundingBombs(i, j) == 0){
                        clearSurrounding(i, j);
                    }
                }
                tileIndex++;
            }
        }

    }

    public void flagTile(int inputIndex){
        int tileIndex = 0;

        for(int i=0;i<boardHeight;i++){
            for (int j =0;j<boardWidth;j++){
                // Compared the index to the input minus one so it's both 0 based and tile has to be unrevealed first
                if (tileIndex == inputIndex-1 && !board[i][j].isClicked){
                    // Checks if tile is not flagged yet and if not then it flags it
                    if (!board[i][j].isFlagged){
                        board[i][j].flagTile();
                        // Reduces 1 to the flag counter
                        flagCounter--;
                    } else{
                        // If flagged already then remove the flag
                        board[i][j].isFlagged = false;
                        // Adds 1 to the flag counter
                        flagCounter++;
                    }
                }
                tileIndex++;
            }
        }
    }

    public void currentFlags(){
        // prints the number of bombs based on flags
        System.out.println("Bombs left: " + flagCounter);
    }

    public String winLose(){
        // Checks for win or lose

        String winLose = "None";

        int revealTotal = 0;

        for (int i=0;i<boardHeight;i++){
            for (int j=0;j<boardWidth;j++){
                if (!winLose.equals("Lose") && !winLose.equals("Win")) {
                    // Loops through all tiles and check if a bomb has been clicked
                    if (board[i][j].isBomb() && board[i][j].isClicked) {
                        winLose = "Lose";
                        System.out.println();
                        revealBombs();
                        displayBoard();
                        System.out.println("You lost");
                        break;
                    }
                    // If not a bomb then it adds to reveal total and if it reached 20 which means all tiles has been revealed that is not a bomb then it returns a win
                    else if (board[i][j].isClicked && !board[i][j].isBomb()) {
                        revealTotal++;

                        if (revealTotal >= (boardHeight * boardWidth - bombLimit)) {
                            winLose = "Win";
                            System.out.println();
                            revealBombs();
                            displayBoard();
                            System.out.println("You won");
                            break;
                        }
                    }
                }
            }
        }
        return winLose;
    }

    public void revealBombs(){
        // Reveals all the bombs

        for(int i=0;i<boardHeight;i++){
            for(int j=0;j<boardWidth;j++){
                //Checks if it's a bomb then reveals it
                if(!board[i][j].isClicked && board[i][j].isBomb()){
                    board[i][j].revealTile();
                }
            }
        }
    }

    public void hint(){
        for(int i=0;i<boardHeight;i++){
            for(int j=0;j<boardWidth;j++){
                //Checks if it's a bomb then flags it if yes
                if(board[i][j].isBomb()){
                    board[i][j].flagTile();
                }
            }
        }
    }

    // PRIVATE HELPERS
    private int checkSurroundingBombs(int row, int column){
        // Placed the directions of the coordinates for the board positions around the selected tile.
        // Did it by placing the possible row and columns and matching it in arrays.
        // So it's basically something like board[row-1][column] but instead you just predict the position at the start immediately
        // Then you access the index of the directions and it's already matched

        int[] rowDirection = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] columnDirection= {-1, 0, 1, -1, 1, -1, 0, 1};

        int surroundingBombs = 0;

        for (int i=0;i<8;i++){
            //pre added the positions
            int newRow = row + rowDirection[i];
            int newColumn = column + columnDirection[i];

            //Checks for out of bounds and not even try to bother checking if it's out of bounds to prevent errors
            if (newRow >= 0 && newRow < boardHeight && newColumn >= 0 && newColumn < boardWidth){
                // Adds 1 to surrounding bombs if there is one
                if (board[newRow][newColumn].isBomb()){
                    surroundingBombs++;
                }
            }
        }

        return surroundingBombs;
    }

    private void clearSurrounding(int row, int column){
        // Placed the directions of the coordinates for the board positions around the selected tile.
        // Did it by placing the possible row and columns and matching it in arrays.
        // So it's basically something like board[row-1][column] but instead you just predict the position at the start immediately
        // Then you access the index of the directions and it's already matched

        int[] rowDirection = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] columnDirection= {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i=0;i<8;i++){
            int newRow = row + rowDirection[i];
            int newColumn = column + columnDirection[i];

            //Checks for out of bounds and not even try to bother checking if it's out of bounds to prevent errors
            if (newRow >= 0 && newRow < boardHeight && newColumn >= 0 && newColumn < boardWidth){
                // Makes sure that the tile is not revealed yet before trying to do so
                if (!board[newRow][newColumn].isClicked){
                    board[newRow][newColumn].revealTile();
                    //Checks if there is no surrounding bombs and if there is none then it clears the surrounding tiles and
                    // passes the row values to the same function and checks it infinitely until there is finally a bomb nearby
                    if (checkSurroundingBombs(newRow, newColumn) == 0){
                        clearSurrounding(newRow, newColumn);
                    }
                }
            }
        }
    }

}
