import java.awt.*;

public class Paddle extends GameObject {
    private Integer xVelocity;

    public Paddle(GameLogic gameLogic, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(gameLogic, xPosition, yPosition, xSize, ySize, color);
    }

    public void setxVelocity(Integer xVelocity) {
        this.xVelocity = xVelocity;
    }
}
