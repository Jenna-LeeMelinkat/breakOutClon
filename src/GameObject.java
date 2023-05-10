import java.awt.*;

public class GameObject {
    private GameLogic game;
    protected Integer xPosition;
    protected Integer yPosition;
    protected Integer xSize;
    protected Integer ySize;
    private java.awt.Color color;

    public GameObject(GameLogic game, Integer xPosition, Integer yPosition, Integer xSize, Integer ySize, Color color) {
        this.game = game;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xSize = xSize;
        this.ySize = ySize;
        this.color = color;
    }

    public void setPosition(int xPos, int yPos){

    }
    public Integer getX(){
        return null;
    }
    public Integer getY(){
        return null;
    }
    public Integer getxSize(){
        return null;
    }
    public Integer getySize(){
        return null;
    }
    public Rectangle getHitBox(){
        return null;
    }
}
