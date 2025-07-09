public class Tile {

    // To do:
    // Make it abstract


    int boardPosition;
    boolean isClicked;


    Tile(int boardPosition, boolean isClicked){
        this.boardPosition = boardPosition;
        this.isClicked = isClicked;

    }

    Tile(){

    }

    public boolean isBomb(){
        // Checks if a tile is a bomb or not. False on default
        // You should focus the logic on the function not the tile you are comparing
        // Like if you're trying to do if(Tile.isBomb()) it would return false if normal and true if bomb
        // if(!Tile.isBomb()) it would return true if normal and false if bomb
        // Basically it's like if Normal tile is a bomb then return false obviously
        // if(!NormalTile.isBomb()) then it returns true since it's like asking if Normal tile is not a bomb which is true

        return false;
    }

    void revealTile(){
        // Reveal the tile upon use
        isClicked = true;
        System.out.println("Tile has been revealed. Is it a bomb?: " + isBomb());
    }


    // USED FOR DEBUGGING AND CHECKING:
    void displayInfo(){
        System.out.println("Board Position: " + boardPosition + ", Is Clicked?: " + isClicked + ", Is it a bomb?: " + isBomb());
    }


}
