import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;



public class Main {
    public static void displayBoard(Tile[][] board){
        //Display Board
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
                    } else if (surroundingBombs != 0) {
                        System.out.print(surroundingBombs);
                    }
                } else {
                    System.out.print("=");
                }
                surroundingBombs = 0;

                // Displays the index of bombs
                //System.out.println("["+i+j+"}");

            }
            System.out.println("");
        }
    }

    public static void playerInput(Tile[][] board){
        Scanner input = new Scanner(System.in);
        System.out.println("Pick 1-25:");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                board[0][0].isRevealed();
                board[0][0].isClicked = true;
                break;
            case 2:
                board[0][1].isRevealed();
                board[0][1].isClicked = true;
                break;
            case 3:
                board[0][2].isRevealed();
                board[0][2].isClicked = true;
                break;
            case 4:
                board[0][3].isRevealed();
                board[0][3].isClicked = true;
                break;
            case 5:
                board[0][4].isRevealed();
                board[0][4].isClicked = true;
                break;
            case 6:
                board[1][0].isRevealed();
                board[1][0].isClicked = true;
                break;
            case 7:
                board[1][1].isRevealed();
                board[1][1].isClicked = true;
                break;
            case 8:
                board[1][2].isRevealed();
                board[1][2].isClicked = true;
                break;
            case 9:
                board[1][3].isRevealed();
                board[1][3].isClicked = true;
                break;
            case 10:
                board[1][4].isRevealed();
                board[1][4].isClicked = true;
                break;
            case 11:
                board[2][0].isRevealed();
                board[2][0].isClicked = true;
                break;
            case 12:
                board[2][1].isRevealed();
                board[2][1].isClicked = true;
                break;
            case 13:
                board[2][2].isRevealed();
                board[2][2].isClicked = true;
                break;
            case 14:
                board[2][3].isRevealed();
                board[2][3].isClicked = true;
                break;
            case 15:
                board[2][4].isRevealed();
                board[2][4].isClicked = true;
                break;
            case 16:
                board[3][0].isRevealed();
                board[3][0].isClicked = true;
                break;
            case 17:
                board[3][1].isRevealed();
                board[3][1].isClicked = true;
                break;
            case 18:
                board[3][2].isRevealed();
                board[3][2].isClicked = true;
                break;
            case 19:
                board[3][3].isRevealed();
                board[3][3].isClicked = true;
                break;
            case 20:
                board[3][4].isRevealed();
                board[3][4].isClicked = true;
                break;
            case 21:
                board[4][0].isRevealed();
                board[4][0].isClicked = true;
                break;
            case 22:
                board[4][1].isRevealed();
                board[4][1].isClicked = true;
                break;
            case 23:
                board[4][2].isRevealed();
                board[4][2].isClicked = true;
                break;
            case 24:
                board[4][3].isRevealed();
                board[4][3].isClicked = true;
                break;
            case 25:
                board[4][4].isRevealed();
                board[4][4].isClicked = true;
                break;
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




        Tile[] tiles = new Tile[25];

        Tile[][] board = new Tile[5][5];

        Random random = new Random();
        int number;
        int bombLimit = 5;
        int bombNumber = 0;


        int num = 0;

        //Make the board
        for(int i=0;i<5;i++){
            for (int j =0;j<5;j++){
                number = random.nextInt(0, 2);

                if (number == 1 && bombNumber < bombLimit){
                    tiles[num] = new Tile(num, false, "Bomb");
                    bombNumber++;
                }
                else{
                    tiles[num] = new Tile(num, false, "Normal");

                }

                board[i][j] = tiles[num];

                //System.out.println(board[i][j]);

                num = num + 1;

                // maybe make it 2d array like do Tiles[j][i]

            }
            System.out.println("");
        }

        //Display the board
        displayBoard(board);

        for (Tile t : tiles){
            t.displayInfo();
        }

        for(int i=0;i<25;i++) {
            //Ask for player input and actually play the game
            playerInput(board);

            //Display the board
            displayBoard(board);
        }
    }
}
