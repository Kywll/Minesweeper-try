import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
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


                if (board[i][j].tileType == "Bomb"){
                    System.out.print("@");
                }
                else if (surroundingBombs == 0) {
                    System.out.print("=");
                } else if (surroundingBombs != 0) {
                    System.out.print(surroundingBombs);
                    surroundingBombs = 0;
                }

                // Displays the index of bombs
                //System.out.println("["+i+j+"}");

            }
            System.out.println("");
        }
        for (Tile t : tiles){
            t.displayInfo();
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Pick 1-25:");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                board[0][0].isRevealed();
                System.out.println(board[0][0].isClicked);
                break;
            case 2:
                System.out.println(board[0][1]);
                break;
            case 3:
                System.out.println(board[0][2]);
                break;
            case 4:
                System.out.println(board[0][3]);
                break;
            case 5:
                System.out.println(board[0][4]);
                break;
            case 6:
                System.out.println(board[1][0]);
                break;
            case 7:
                System.out.println(board[1][1]);
                break;
            case 8:
                System.out.println(board[1][2]);
                break;
            case 9:
                System.out.println(board[1][3]);
                break;
            case 10:
                System.out.println(board[1][4]);
                break;
            case 11:
                System.out.println(board[2][0]);
                break;
            case 12:
                System.out.println(board[2][1]);
                break;
            case 13:
                System.out.println(board[2][2]);
                break;
            case 14:
                System.out.println(board[2][3]);
                break;
            case 15:
                System.out.println(board[2][4]);
                break;
            case 16:
                System.out.println(board[3][0]);
                break;
            case 17:
                System.out.println(board[3][1]);
                break;
            case 18:
                System.out.println(board[3][2]);
                break;
            case 19:
                System.out.println(board[3][3]);
                break;
            case 20:
                System.out.println(board[3][4]);
                break;
            case 21:
                System.out.println(board[4][0]);
                break;
            case 22:
                System.out.println(board[4][1]);
                break;
            case 23:
                System.out.println(board[4][2]);
                break;
            case 24:
                System.out.println(board[4][3]);
                break;
            case 25:
                System.out.println(board[4][4]);
                break;
        }


    }



}
