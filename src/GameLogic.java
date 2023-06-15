import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Die Klasse GameLogic ist verantwortlich für die Logik des Spiels und verwaltet den Spielzustand.
 *
 * @author Jenna-Lee Melinkat
 * @author Julian Hahnefeld
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
    private GameState state;

    //Zeitmesser
    private Timer timer;

    /**
     * Konstruktor für die Klasse GameLogic.
     */
    public GameLogic(){
        ballCount = Configuration.BALL_COUNT_INITIAL;

        // set panel size
        setPreferredSize(new Dimension(Configuration.FIELD_X_SIZE, Configuration.FIELD_Y_SIZE));
        this.ball = new Ball(this, 160,160,Configuration.BALL_X_SIZE,Configuration.BALL_Y_SIZE,Color.black);
        this.paddle = new Paddle(this,160,Configuration.PADDLE_Y_POSITION, Configuration.PADDLE_X_SIZE, Configuration.PADDLE_Y_SIZE, Color.gray);

        setFocusable(true);
        addKeyListener(new BreakoutKeyAdapter());

        int counter = Configuration.BRICK_SCORE;
        int fensterPadding = 10;

        //Berechnet die Anzahl von Bricks, die in eine Reihe auf das Spielfeld passen und verteilt diese.
        int fensterbreiteFuerBricks = Configuration.FIELD_X_SIZE - (fensterPadding * 2);
        int passendeBricksInBreite = (fensterbreiteFuerBricks + Configuration.BRICK_PADDING) / (Configuration.BRICK_X_SIZE + Configuration.BRICK_PADDING);
        int linksShift = (fensterbreiteFuerBricks - (passendeBricksInBreite * (Configuration.BRICK_X_SIZE + Configuration.BRICK_PADDING) - Configuration.BRICK_PADDING)) / 2;
        int aktuellerBrickInZeile = 1;
        int xPosBrick = fensterPadding + linksShift + Configuration.BRICK_X_SIZE/2;
        int yPosBrick = Configuration.BRICK_Y_SIZE;

        while (counter > 0) {
            if(aktuellerBrickInZeile > 1) { //if aktuellerBrickinZeile more than one change xPosBrick
                xPosBrick += Configuration.BRICK_X_SIZE + Configuration.BRICK_PADDING;
            }
            if (aktuellerBrickInZeile > passendeBricksInBreite) { //if aktuellerBrickinZeile less than passendeBricksInBreite change xPosBrick with leftshift and yPosBrick
                xPosBrick = fensterPadding + linksShift + Configuration.BRICK_X_SIZE/2;
                yPosBrick += Configuration.BRICK_Y_SIZE + Configuration.BRICK_PADDING;
                aktuellerBrickInZeile = 1;
            }
            if (counter < passendeBricksInBreite && aktuellerBrickInZeile == 1) { //if BrickScore less than passendeBricksInBreite & aktuellerBrickInZeile is one change xPosBrick
                xPosBrick += (passendeBricksInBreite - counter) * (Configuration.BRICK_X_SIZE + Configuration.BRICK_PADDING) / 2;
             }
            Brick brick = new Brick(this, xPosBrick, yPosBrick, Configuration.BRICK_X_SIZE, Configuration.BRICK_Y_SIZE, Color.red);
            bricks.add(brick);
            aktuellerBrickInZeile++;
            counter--;
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

    //Startet das Spiel.
    public void start() {
        state = GameState.RUNNING;
        timer = new Timer(Configuration.LOOP_PERIOD, new GameLoop());
        score = 0;
        timer.start();}

    //Gibt onTick im Terminal aus und bewegt ball und paddle, zeichnet im Anschluss alle Komponenten neu

    /**
     * Bewegt den Ball und das Paddle
     * Zeichnet und verschiebt die Hitboxen des Balls
     * Prüft Kollision zwischen Ball/Stein und Ball/Paddle.
     */
    private void onTick() {
        ball.move();
        paddle.move();

        Rectangle ballHitBox = ball.getHitBox();
        Rectangle nextY = new Rectangle(ballHitBox);
        Rectangle nextX = new Rectangle(ballHitBox);

        nextX.setLocation(nextX.x + ball.getxVelocity(), nextX.y);
        nextY.setLocation(nextY.x, nextY.y + ball.getyVelocity());

        Brick hitBrick = null;
        for (Brick brick : bricks) {
            if (brick.getHitBox().intersects(nextX)) { // hit in the west or east
                hitBrick = brick;
                ball.setVelocity(-ball.getxVelocity(), ball.getyVelocity());
            }
            if (brick.getHitBox().intersects(nextY)) { // hit in the north or south
                hitBrick = brick;
                ball.setVelocity(ball.getxVelocity(), -ball.getyVelocity());
            }
        }
        if (hitBrick != null) { // if hit brick then remove it, score and show score in terminal
            bricks.remove(hitBrick);
            score ++;
            System.out.println("Aktuelle Punktzahl:" + score);
        }

        // check physics and rules
        if (ball.getHitBox().intersects(paddle.getHitBox())) { // ball hits paddle
            ball.setVelocity(ball.getxVelocity(), -ball.getyVelocity());
        } else if (ball.getY() > Configuration.FIELD_Y_SIZE - 5) { // ball is lost // reduce number of balls
            System.out.println(ballCount);
            --ballCount;
            if (ballCount <= 0) { // no balls left = GAME OVER
                state = GameState.GAME_OVER;
                System.out.printf("Game over: You lost. Score = %d%n", score); System.exit(-1);
            } else { restartWithNewBall();
            } }
        if (score == Configuration.BRICK_SCORE) { // no balls left = WIN
            state = GameState.GAME_OVER;
            System.out.printf("GG You won! Score = %d%n", score);
            System.exit(-1);
        }
        repaint();
    }

    /**
     * Stoppt die Bewegung des Paddles, setzt den Ball zurück in die Mitte über das Paddle,
     * gibt die Anzahl der verbleibenden Bälle im Terminal aus, setzt die Bewegung des Balls zurück.
     */
    public void restartWithNewBall(){
            paddle.setxVelocity(0);
            this.ball = new Ball(this, paddle.xPosition , paddle.yPosition - ((Configuration.PADDLE_Y_SIZE + Configuration.BALL_Y_SIZE)/2),Configuration.BALL_X_SIZE,Configuration.BALL_Y_SIZE,Color.black);
            System.out.print("You have " +  ballCount + "left");
            this.ball.setVelocity(-1,-1);
    }


    private class GameLoop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { onTick(); }
    }

    private class BreakoutKeyAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event)
        { onKeyReleased(event); } @Override
        public void keyPressed(KeyEvent event) { onKeyPressed(event); }
    }

    public void onKeyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT) { paddle.setxVelocity(-Configuration.PADDLE_VELOCITY);
        }
        if (key == KeyEvent.VK_RIGHT) { paddle.setxVelocity(Configuration.PADDLE_VELOCITY);
        }
    }

    public void onKeyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) { paddle.setxVelocity(0);
        } }

}