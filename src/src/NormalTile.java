public class NormalTile extends Tile{

    NormalTile(int boardPosition, boolean isClicked){
        super(boardPosition, isClicked);
        // You can add things like sout(this is a normal tile)
        // Basically just Polymorphism
    }


    @Override
    public boolean isBomb(){
        // Returns false since this object is obviously not a bomb
        return false;
    }


}
