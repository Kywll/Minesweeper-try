public class Tile {
    int boardPosition;
    boolean isClicked;
    String tileType;



    Tile(int boardPosition, boolean isClicked, String tileType){
        this.boardPosition = boardPosition;
        this.isClicked = isClicked;
        this.tileType = tileType;
    }

    Tile(){


    }

    void isRevealed(){
        isClicked = true;
        System.out.println("Tile has been revealed");
    }

    void displayInfo(){
        System.out.println("Board Position: " + boardPosition + ", Is Clicked?: " + isClicked + ", Tile Type: " + tileType);
    }


}
