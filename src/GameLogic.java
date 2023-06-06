import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Die Klasse GameLogic ist verantwortlich für die Logik des Spiels und verwaltet den Spielzustand.
 *
 * @version [Versionsnummer]
 */
public class GameLogic extends JPanel {
    // Die Anzahl der Bälle im Spiel
    private Integer ballCount;

    // Der aktuelle Punktestand
    private Integer score;

    // Eine Liste der verbleibenden Ziegel im Spiel
    private List<Brick> bricks = new LinkedList<>();

    // Der Paddle des Spielers
    private Paddle paddle;

    // Der Ball, der im Spiel ist
    private Ball ball;

    // Der aktuelle Zustand des Spiels (z.B. laufend, beendet)
    private GameState game;

    /**
     * Konstruktor für die Klasse GameLogic.
     *
     */
    public GameLogic(){
        // set panel size
        setPreferredSize(new Dimension(Configuration.FIELD_X_SIZE, Configuration.FIELD_Y_SIZE));
        this.ball = new Ball(this, 160,160,Configuration.BALL_X_SIZE,Configuration.BALL_Y_SIZE,Color.black);
        this.paddle = new Paddle(this,160,Configuration.PADDLE_Y_POSITION, Configuration.PADDLE_X_SIZE, Configuration.PADDLE_Y_SIZE, Color.gray);


        for(int i=0;i<11;i++){
            Brick brick = new Brick(this, i * 10, i,Configuration.BRICK_X_SIZE, Configuration.BRICK_Y_SIZE, Color.red);
            bricks.add(brick);
        }

    }

    @Override
    public void paintComponent(Graphics graphics) {
        // paint panel
        super.paintComponent(graphics);
        // configure rendering pipeline: Enable antialiasing and high render quality
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // render bricks, paddle and ball
        for (Brick brick: bricks) {
            brick.render(graphics);
        }
        paddle.render(graphics);
        ball.render(graphics);
        // synchronize graphics state
        Toolkit.getDefaultToolkit().sync();
    }

    counter = brickanzahl

while schleife
    counter > 0

    brickanzahl = counter

    fensterbreite-(abstandLinks+abstandRechts) = fensterbreiteFuerBricks;
    fensterbreiteFuerBricks / brickbreite = passendeBricksInBreite;
   if     brickanzahl < passendeBricksInBreite

    brickanzahl * brickbreite = insgesamtebrickbreite
    fensterbreiteFuerBricks - insgesamtebrickbreite = kompletterAbstand;
    kompletterAbstand/ (brickanzahl-1)

            else

    fensterbreiteFuerBricks - (passendeBricksInBreite* brickbreite) = kompletterAbstand
    kompletterAbstand/ (passendeBricksInBreite-1)

    counter - passendeBricksInBreite

    counter > 0
    ypos andern


// if passendeBricksInBreite < brickanzahl
}