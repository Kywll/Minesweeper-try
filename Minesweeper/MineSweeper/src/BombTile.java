public class BombTile extends Tile{

    BombTile(int boardPosition, boolean isClicked) {
        super(boardPosition, isClicked);
        // You can add things like sout(this is a bomb tile)
        // Basically just Polymorphism

    }




    @Override
    public boolean isBomb(){
        // Returns true since this object is obviously a bomb
        return true;
    }


}
