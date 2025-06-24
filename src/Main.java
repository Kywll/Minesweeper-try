import java.util.Dictionary;
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

    public static void sampleBoard(Tile[] tiles, Tile[][] board){
        int num = 0;
        //Make sample board
        for(int i=0;i<5;i++){
            for (int j =0;j<5;j++){

                tiles[num] = new Tile(num, false, "Normal");

                board[i][j] = tiles[num];

                num = num + 1;
            }
        }
    }

    public static void createBoard(Tile[] tiles, Tile[][] board, int startingSpot){
        // Creates a board

        Random random = new Random();

        int number;
        int bombLimit = 5;
        int bombNumber = 0;


        int num = 0;

        // So the num in here is 0 based but the startingSpot is 1 based because it need to be used on the input.
        // What I did is to simply subtract 1 inside the createBoard function
        startingSpot -= 1;

        //Make the board
        for(int i=0;i<5;i++){
            for (int j =0;j<5;j++){
                number = random.nextInt(0, 2);

                if (number == 1 && bombNumber < bombLimit && !board[i][j].isClicked){
                    if (num != startingSpot && num != (startingSpot-6) && num != (startingSpot-5) && num != (startingSpot-4) && num != (startingSpot-1) && num != (startingSpot+1) && num != (startingSpot+4) && num != (startingSpot+5) && num != (startingSpot+6)) {
                        tiles[num] = new Tile(num, false, "Bomb");
                        bombNumber++;
                    }
                }
                /*
                else{
                    tiles[num] = new Tile(num, false, "Normal");
                }
                */
                board[i][j] = tiles[num];

                num = num + 1;
            }
        }
    }

    public static void displayBoard(Tile[][] board){
        //Displays the Board
        // The loop checks for surrounding tiles if it was clicked or if there are bombs near it in 8 directions then prints it accordingly

        int num = 0;
        for(int i=0;i<5;i++){
            for (int j =0;j<5;j++){
                // Checks if the tile was clicked
                if (board[i][j].isClicked){
                    if (board[i][j].tileType.equals("Bomb")){
                        System.out.print("@");
                    } else if (checkSurroundingBombs(board, i, j) == 0) {
                        System.out.print("0");
                    } else if (checkSurroundingBombs(board, i, j) != 0) {
                        System.out.print(checkSurroundingBombs(board, i, j));
                    }
                } else {
                    System.out.print("=");
                }

                num++;

            }
            System.out.println();
        }
    }

    public static int playerInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Pick 1-25 to reveal a tile: ");
        int choice = input.nextInt();

        return choice;
    }

    public static void tileInput(Tile[][] board, int choice){
        // Loops and compares the num to the choice and if they are the same then the nested for loop's value will determine the tile's position on the board
        // choice-1 because it's 1 based but num is 0 based

        int num = 0;

        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                if (num == choice-1){
                    board[i][j].isClicked = true;
                    // Checks if there is surrounding bombs near the input and if there is none then you just clear everything around it on 8 directions
                    if (checkSurroundingBombs(board, i, j) == 0){
                        clearSurrounding(board, i, j);
                    }
                }

                num++;
            }
        }
    }

    public static int checkSurroundingBombs(Tile[][] board, int row, int column){
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
            if (newRow >= 0 && newRow < 5 && newColumn >= 0 && newColumn < 5){
                // Adds 1 to surrounding bombs if there is one
                if (board[newRow][newColumn].tileType.equals("Bomb")){
                    surroundingBombs++;
                }
            }
        }

        return surroundingBombs;
    }

    public static void clearSurrounding(Tile[][] board, int row, int column){
        // Placed the directions of the coordinates for the board positions around the selected tile.
        // Did it by placing the possible row and columns and matching it in arrays.
        // So it's basically something like board[row-1][column] but instead you just predict the position at the start immediately
        // Then you access the index of the directions and it's already matched

        int[] rowDirection = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] columnDirection = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i=0;i<8;i++){
            int newRow = row + rowDirection[i];
            int newColumn =column + columnDirection[i];

            //Checks for out of bounds and not even try to bother checking if it's out of bounds to prevent errors
            if (newRow >= 0 && newRow < 5 && newColumn >= 0 && newColumn < 5){
                // Makes sure that the tile is not revealed yet before trying to do so
                if (!board[newRow][newColumn].isClicked){
                    board[newRow][newColumn].isClicked = true;
                    //Checks if there is no surrounding bombs and if there is none then it clears the surrounding tiles and
                    // passes the row values to the same function and checks it infinitely until there is finally a bomb nearby
                    if (checkSurroundingBombs(board, newRow, newColumn) == 0){
                        clearSurrounding(board, newRow, newColumn);
                    }
                }
            }
        }
    }

    public static String winLose(Tile[][] board){
        // Checks for win or lose

        String winLose = "None";

        int revealTotal = 0;

        int num = 0;
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                if (!winLose.equals("Lose") && !winLose.equals("Win")) {
                    // Loops through all tiles and check if a bomb has been clicked
                    if (board[i][j].tileType.equals("Bomb") && board[i][j].isClicked) {
                        winLose = "Lose";
                        System.out.println();
                        revealBombs(board);
                        displayBoard(board);
                        System.out.println("You lost");
                        break;
                    }
                    // If not a bomb then it adds to reveal total and if it reached 20 which means all tiles has been revealed that is not a bomb then it returns a win
                    else if (board[i][j].isClicked && board[i][j].tileType.equals("Normal")) {
                        revealTotal++;
                        if (revealTotal >= 20) {
                            winLose = "Win";
                            System.out.println();
                            revealBombs(board);
                            displayBoard(board);
                            System.out.println("You won");
                            break;
                        }
                    }
                }

                num++;
            }
        }
        return winLose;
    }

    public static void revealBombs(Tile[][] board){
        // Reveals all the bombs
        int num = 0;

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                //Checks if it's a bomb then reveals it
                if(!board[i][j].isClicked && board[i][j].tileType.equals("Bomb")){
                    board[i][j].isClicked = true;
                }

                num++;
            }
        }
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


        Tile[] tiles = new Tile[25];

        Tile[][] board = new Tile[5][5];

        // Display the board but only filler
        for(int i=0;i<5;i++){
            for (int j =0;j<5;j++){
                System.out.print("=");

            }
            System.out.println();
        }

        // Sample Board
        sampleBoard(tiles, board);

        System.out.println();


        // Put in in a variable so it doesn't ask for the input again after declaring the function
        int firstChoice = playerInput();

        // Create the board
        createBoard(tiles, board, firstChoice);

        // Input the players choice
        tileInput(board, firstChoice);

        //Display the board
        displayBoard(board);


        // Display some more info

        //for (Tile t : tiles){
        //    t.displayInfo();
        //}


        // Actually play the game
        for(int i=0;i<24;i++) {
            // Check if you've won
            String winLoseStatus = winLose(board);
            if (!winLoseStatus.equals("Win") && !winLoseStatus.equals("Lose")){
                //Ask for player input and actually play the game
                tileInput(board, playerInput());

                //Display the board
                displayBoard(board);
            }
            else{
                // Ends the game if the play has won or lost
                break;
            }
        }
    }
}
