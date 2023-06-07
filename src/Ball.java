import java.awt.*;
/**
 * Die Klasse Ball ist ein Untertyp von GameObject und repräsentiert den Ball im Spiel.
 *
 * @author Jenna-Lee Melinkat
 * @author Julian Hahnefeld
 */
public class Ball extends GameObject {
    // Die x- und y-Geschwindigkeit des Balls
    private Integer xVelocity;
    private Integer yVelocity;

    /**
     * Konstruktor für den Ball.
     *
     * @param gameLogic die Spiellogik, zu der der Ball gehört
     * @param xPosition die X-Koordinate der oberen linken Ecke des Balls
     * @param yPosition die Y-Koordinate der oberen linken Ecke des Balls
     * @param xSize     die Breite des Balls
     * @param ySize     die Höhe des Balls
     * @param color     die Farbe des Balls
     */
    public Ball(GameLogic gameLogic, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(gameLogic, xPosition, yPosition, xSize, ySize, color);
    }

    /**
     * Setzt die Geschwindigkeit des Balls.
     *
     * @param xVelocity die x-Geschwindigkeit des Balls
     * @param yVelocity die y-Geschwindigkeit des Balls
     */
    public void setVelocity(Integer xVelocity, Integer yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    /**
     * Gibt die x-Geschwindigkeit des Balls zurück.
     *
     * @return die x-Geschwindigkeit des Balls
     */
    public Integer getxVelocity() {
        xVelocity = 2;
        return xVelocity;
    }

    /**
     * Gibt die y-Geschwindigkeit des Balls zurück.
     *
     * @return die y-Geschwindigkeit des Balls
     */
    public Integer getyVelocity() {
        return yVelocity;
    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(xPosition - xSize / 2, yPosition - ySize / 2, xSize, ySize);
    }

    public void move() {
        xPosition += xVelocity;
        if (xPosition < 0) {
            xVelocity = -xVelocity;
        } else if (xPosition >= Configuration.FIELD_X_SIZE) {
            xVelocity = -xVelocity;
        }

        yPosition += yVelocity;
        if (yPosition < 0) {
            yVelocity = -yVelocity;
        } else if (yPosition >= Configuration.FIELD_Y_SIZE) {
            yVelocity = -yVelocity;
        }
    }
}

