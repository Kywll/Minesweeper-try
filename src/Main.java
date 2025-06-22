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

                System.out.println("The starting spot is: " + startingSpot);
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
        // TO BE CONTINUED - NEEDS TO JUST USE THE FUNCTION CHECK BOMBS AND MAKE SURE THIS FUNCTION ONLY DISPLAY NOT CHANGE ANYTHING JUST DISPLAY
        //Display Board
        // The loop checks for surrounding tiles if there are bombs near it in 8 directions
        int num = 0;
        for(int i=0;i<5;i++){
            for (int j =0;j<5;j++){
                int surroundingBombs = 0;
                try {
                    if (board[i - 1][j].tileType == "Bomb") {
                        surroundingBombs++;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException) {
                }
                try {
                    if (board[i + 1][j].tileType == "Bomb") {
                        surroundingBombs++;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException) {
                }
                try {
                    if (board[i][j - 1].tileType == "Bomb") {
                        surroundingBombs++;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException) {
                }
                try {
                    if (board[i][j + 1].tileType == "Bomb") {
                        surroundingBombs++;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException) {
                }
                try {
                    if (board[i - 1][j - 1].tileType == "Bomb") {
                        surroundingBombs++;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException) {
                }
                try {
                    if (board[i + 1][j + 1].tileType == "Bomb") {
                        surroundingBombs++;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException) {
                }
                try {
                    if (board[i - 1][j + 1].tileType == "Bomb") {
                        surroundingBombs++;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException) {
                }
                try {
                    if (board[i + 1][j - 1].tileType == "Bomb") {
                        surroundingBombs++;
                    }
                }
                catch (Exception ArrayIndexOutOfBoundsException) {
                }

                if (board[i][j].isClicked){
                    if (board[i][j].tileType == "Bomb"){
                        System.out.print("@");
                    } else if (surroundingBombs == 0) {
                        System.out.print("0");
                        //TO BE CONTINUED
                        clearSurrounding(board, i, j);
                    } else if (surroundingBombs != 0) {
                        System.out.print(surroundingBombs);
                    }
                } else {
                    System.out.print("=");
                }
                surroundingBombs = 0;
                num++;

                // Displays the index of bombs
                //System.out.println("["+i+j+"}");

            }
            System.out.println("");
        }
    }


    // Need to find a way to combine this two to reduce redundancy and confusion. Should be named getInput
    public static void playerInput(Tile[][] board){
        Scanner input = new Scanner(System.in);
        System.out.println("Pick 1-25:");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                board[0][0].isClicked = true;
                if (board[0][0].tileType == "Normal" || board[0][0].tileType == "Bomb"){
                    board[0][0].isRevealed();
                }
                break;
            case 2:
                board[0][1].isClicked = true;
                if (board[0][1].tileType == "Normal" || board[0][1].tileType == "Bomb"){
                    board[0][1].isRevealed();
                }
                break;
            case 3:
                board[0][2].isClicked = true;
                if (board[0][2].tileType == "Normal" || board[0][2].tileType == "Bomb"){
                    board[0][2].isRevealed();
                }
                break;
            case 4:
                board[0][3].isClicked = true;
                if (board[0][3].tileType == "Normal" || board[0][3].tileType == "Bomb"){
                    board[0][3].isRevealed();
                }
                break;
            case 5:
                board[0][4].isClicked = true;
                if (board[0][4].tileType == "Normal" || board[0][4].tileType == "Bomb"){
                    board[0][4].isRevealed();
                }
                break;
            case 6:
                board[1][0].isClicked = true;
                if (board[1][0].tileType == "Normal" || board[1][0].tileType == "Bomb"){
                    board[1][0].isRevealed();
                }
                break;
            case 7:
                board[1][1].isClicked = true;
                if (board[1][1].tileType == "Normal" || board[1][1].tileType == "Bomb"){
                    board[1][1].isRevealed();
                }
                break;
            case 8:
                board[1][2].isClicked = true;
                if (board[1][2].tileType == "Normal" || board[1][2].tileType == "Bomb"){
                    board[1][2].isRevealed();
                }
                break;
            case 9:
                board[1][3].isClicked = true;
                if (board[1][3].tileType == "Normal" || board[1][3].tileType == "Bomb"){
                    board[1][3].isRevealed();
                }
                break;
            case 10:
                board[1][4].isClicked = true;
                if (board[1][4].tileType == "Normal" || board[1][4].tileType == "Bomb"){
                    board[1][4].isRevealed();
                }
                break;
            case 11:
                board[2][0].isClicked = true;
                if (board[2][0].tileType == "Normal" || board[2][0].tileType == "Bomb"){
                    board[2][0].isRevealed();
                }
                break;
            case 12:
                board[2][1].isClicked = true;
                if (board[2][1].tileType == "Normal" || board[2][1].tileType == "Bomb"){
                    board[2][1].isRevealed();
                }
                break;
            case 13:
                board[2][2].isClicked = true;
                if (board[2][2].tileType == "Normal" || board[2][2].tileType == "Bomb"){
                    board[2][2].isRevealed();
                }
                break;
            case 14:
                board[2][3].isClicked = true;
                if (board[2][3].tileType == "Normal" || board[2][3].tileType == "Bomb"){
                    board[2][3].isRevealed();
                }
                break;
            case 15:
                board[2][4].isClicked = true;
                if (board[2][4].tileType == "Normal" || board[2][4].tileType == "Bomb"){
                    board[2][4].isRevealed();
                }
                break;
            case 16:
                board[3][0].isClicked = true;
                if (board[3][0].tileType == "Normal" || board[3][0].tileType == "Bomb"){
                    board[3][0].isRevealed();
                }
                break;
            case 17:
                board[3][1].isClicked = true;
                if (board[3][1].tileType == "Normal" || board[3][1].tileType == "Bomb"){
                    board[3][1].isRevealed();
                }
                break;
            case 18:
                board[3][2].isClicked = true;
                if (board[3][2].tileType == "Normal" || board[3][2].tileType == "Bomb"){
                    board[3][2].isRevealed();
                }
                break;
            case 19:
                board[3][3].isClicked = true;
                if (board[3][3].tileType == "Normal" || board[3][3].tileType == "Bomb"){
                    board[3][3].isRevealed();
                }
                break;
            case 20:
                board[3][4].isClicked = true;
                if (board[3][4].tileType == "Normal" || board[3][4].tileType == "Bomb"){
                    board[3][4].isRevealed();
                }
                break;
            case 21:
                board[4][0].isClicked = true;
                if (board[4][0].tileType == "Normal" || board[4][0].tileType == "Bomb"){
                    board[4][0].isRevealed();
                }
                break;
            case 22:
                board[4][1].isClicked = true;
                if (board[4][1].tileType == "Normal" || board[4][1].tileType == "Bomb"){
                    board[4][1].isRevealed();
                }
                break;
            case 23:
                board[4][2].isClicked = true;
                if (board[4][2].tileType == "Normal" || board[4][2].tileType == "Bomb"){
                    board[4][2].isRevealed();
                }
                break;
            case 24:
                board[4][3].isClicked = true;
                if (board[4][3].tileType == "Normal" || board[4][3].tileType == "Bomb"){
                    board[4][3].isRevealed();
                }
                break;
            case 25:
                board[4][4].isClicked = true;
                if (board[4][4].tileType == "Normal" || board[4][4].tileType == "Bomb"){
                    board[4][4].isRevealed();
                }
                break;
        }
    }
    public static int startingSpot(){
        Scanner input = new Scanner(System.in);
        System.out.println("Pick 1-25:");
        int choice = input.nextInt();

        return choice;
    }



    public static int checkSurroundingBombs(Tile[][] board, int row, int column){
        // Check the surrounding by subtracting and adding the values of row and column to match the position of all the surrounding tiles in 8 directions

        int surroundingBombs = 0;

        try {
            if (board[row - 1][column].tileType == "Bomb") {
                surroundingBombs++;
            }
        }
        catch (Exception ArrayIndexOutOfBoundsException) {
        }
        try {
            if (board[row + 1][column].tileType == "Bomb") {
                surroundingBombs++;
            }
        }
        catch (Exception ArrayIndexOutOfBoundsException) {
        }
        try {
            if (board[row][column - 1].tileType == "Bomb") {
                surroundingBombs++;
            }
        }
        catch (Exception ArrayIndexOutOfBoundsException) {
        }
        try {
            if (board[row][column + 1].tileType == "Bomb") {
                surroundingBombs++;
            }
        }
        catch (Exception ArrayIndexOutOfBoundsException) {
        }
        try {
            if (board[row - 1][column - 1].tileType == "Bomb") {
                surroundingBombs++;
            }
        }
        catch (Exception ArrayIndexOutOfBoundsException) {
        }
        try {
            if (board[row + 1][column + 1].tileType == "Bomb") {
                surroundingBombs++;
            }
        }
        catch (Exception ArrayIndexOutOfBoundsException) {
        }
        try {
            if (board[row - 1][column + 1].tileType == "Bomb") {
                surroundingBombs++;
            }
        }
        catch (Exception ArrayIndexOutOfBoundsException) {
        }
        try {
            if (board[row + 1][column - 1].tileType == "Bomb") {
                surroundingBombs++;
            }
        }
        catch (Exception ArrayIndexOutOfBoundsException) {
        }

        return surroundingBombs;
    }

    public static void clearSurrounding(Tile[][] board, int row, int column){
        // Reveal the surroundings too by subtracting and adding the values of row and column to match the position of all the surrounding tiles in 8 directions
        try {
            board[row - 1][column].isClicked = true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
        }

        try {
            board[row + 1][column].isClicked = true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
        }

        try {
            board[row][column - 1].isClicked = true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
        }

        try {
            board[row][column + 1].isClicked = true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
        }

        try {
            board[row - 1][column - 1].isClicked = true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
        }

        try {
            board[row + 1][column + 1].isClicked = true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
        }

        try {
            board[row - 1][column + 1].isClicked = true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
        }

        try {
            board[row + 1][column - 1].isClicked = true;
        } catch (Exception ArrayIndexOutOfBoundsException) {
        }

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
            }
        }
    }

    public static void checkZeroes(Tile[][] board, int row, int column){
        int num = 0;
        for(int i=0;i<5;i++) {
            for (int j = 0; j < 5; j++) {


                num++;
            }
        }
    }

    public static void main(String[] args){
        //Thought Process
        //Make a Game Board where you can detect surroundings
        //Have Random Bombs
        //Have a number on a tile based on how many bombs nearby
        //Tile with revealed and unrevealed state

        // June 19, 2025 To do:
        // Hide it all first then only show the number of surrounding bombs near it after clicking

        // Done so far!!

        //New things to do below

        //June 21, 2025 to do:
        //Current Goal )If you clicked on a 0 and there are other 0's besides it on 8 directions then reveal those 0's too
        //maybe try to scan through all of tiles and if they are 0 then clear the surroundings

        // (Done now) Maybe make it so that at the first click it's always a normal tile. Would have to create the board after clicking though?
        // Actually losing


        Tile[] tiles = new Tile[25];

        Tile[][] board = new Tile[5][5];

        // Display the board but only filler
        for(int i=0;i<5;i++){
            for (int j =0;j<5;j++){
                System.out.print("=");

            }
            System.out.println("");
        }

        // Sample Board
        sampleBoard(tiles, board);

        System.out.println("");

        int startingSpot = startingSpot();

        // Create the board
        createBoard(tiles, board, startingSpot);

        // Input the players choice
        tileInput(board, startingSpot);

        //Display the board
        displayBoard(board);


        // Display some more info

        //for (Tile t : tiles){
        //    t.displayInfo();
        //}

        // Actually play the game
        for(int i=0;i<24;i++) {
            //Ask for player input and actually play the game
            playerInput(board);

            //Display the board
            displayBoard(board);
        }
    }
}
