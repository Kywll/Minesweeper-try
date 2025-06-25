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

    // Access the Tiles
    Tile[][] board;

    Board(int boardHeight, int boardWidth, int bombLimit){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.bombLimit = bombLimit;

        // Is needed because the constructors are not initialized yet and is 0 at default
        // THis is apparently needed when you determine the values on another class and not on the object itself
        this.board = new Tile[boardHeight][boardWidth];
    }

    // BOARD SETUP
    public void sampleBoard(){
        int tileIndex = 0;
        //Make sample board
        for(int i=0;i<boardHeight;i++){
            for (int j =0;j<boardWidth;j++){
                // Create an object inside a 2d array on each iteration and uses the values of i and j to fill out the 2d array
                board[i][j] = new NormalTile(tileIndex, false);

                tileIndex++;
            }
        }
    }

    public void createBoard(int startingSpot){
        // Creates a board

        Random random = new Random();

        int bombChance;
        int bombNumber = 0;

        int tileIndex = 0;

        // So the num in here is 0 based but the startingSpot is 1 based because it need to be used on the input.
        // What I did is to simply subtract 1 inside the createBoard function
        startingSpot -= 1;

        //Make the board
        for(int i=0;i<boardHeight;i++){
            for (int j =0;j<boardWidth;j++){
                bombChance = random.nextInt(0, 2);

                // If the bomChance sends a 1 then make a bomb but only if bomb limit has not been reached
                if (bombChance == 1 && bombNumber < bombLimit && !board[i][j].isClicked){
                    // Adds bombs on tiles that is not around the tile that was clicked first
                    if (tileIndex != startingSpot && tileIndex != (startingSpot-6) && tileIndex != (startingSpot-5) && tileIndex != (startingSpot-4) && tileIndex != (startingSpot-1) && tileIndex != (startingSpot+1) && tileIndex != (startingSpot+4) && tileIndex != (startingSpot+5) && tileIndex != (startingSpot+6)) {
                        // Adds a bomb on the current tile
                        board[i][j] = new BombTile(tileIndex, false);
                        bombNumber++;
                    }
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
                if (tileIndex == choice-1){
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