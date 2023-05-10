import java.awt.*;

/**
 * Hello World
 */
public class Ball extends GameObject{
    private Integer xVelocity;
    private Integer yVelocity;

    public Ball(GameLogic gameLogic, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(gameLogic, xPosition, yPosition, xSize, ySize, color);
    }

    public void setVelocity(Integer xVelocity, Integer yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public Integer getxVelocity() {
        return xVelocity;
    }

    public Integer getyVelocity() {
        return yVelocity;
    }
}
