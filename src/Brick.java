import java.awt.*;

public class Brick extends GameObject {
    private boolean destroyed;

    public Brick(GameLogic game, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(game, xPosition, yPosition, xSize, ySize, color);
    }

    public boolean isDestroyed(){
        return false;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    public void setIntact(){

    }

}
