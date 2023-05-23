import java.awt.*;

/**
 * Die `Paddle`-Klasse definiert das Spiel-Paddle.
 *
 * @author Jenna-Lee Melinkat
 * @author Julian Hahnefeld
 */
public class Paddle extends GameObject {
    private Integer xVelocity;

    /**
     * Konstruktor für das Paddle-Objekt.
     *
     * @param gameLogic die Spiellogik.
     * @param xPosition die x-Position des Paddles.
     * @param yPosition die y-Position des Paddles.
     * @param xSize die Breite des Paddles.
     * @param ySize die Höhe des Paddles.
     * @param color die Farbe des Paddles.
     */
    public Paddle(GameLogic gameLogic, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(gameLogic, xPosition, yPosition, xSize, ySize, color);
    }

    /**
     * Setzt die x-Geschwindigkeit des Paddles.
     *
     * @param xVelocity die neue x-Geschwindigkeit des Paddles.
     */
    public void setxVelocity(Integer xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(xPosition - xSize / 2, yPosition - ySize / 2, xSize, ySize);
    }
}
