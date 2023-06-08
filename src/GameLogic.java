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
    private GameState state;

    //Zeitmesser
    private Timer timer;

    /**
     * Konstruktor für die Klasse GameLogic.
     *
     */
    public GameLogic(){
        ballCount = Configuration.BALL_COUNT_INITIAL;

        // set panel size
        setPreferredSize(new Dimension(Configuration.FIELD_X_SIZE, Configuration.FIELD_Y_SIZE));
        this.ball = new Ball(this, 160,160,Configuration.BALL_X_SIZE,Configuration.BALL_Y_SIZE,Color.black);
        this.paddle = new Paddle(this,160,Configuration.PADDLE_Y_POSITION, Configuration.PADDLE_X_SIZE, Configuration.PADDLE_Y_SIZE, Color.gray);

        setFocusable(true);
        addKeyListener(new BreakoutKeyAdapter());


        /*for(int i=0;i< 11;i++) {

            Brick brick = new Brick(this, i * 10, i, Configuration.BRICK_X_SIZE, Configuration.BRICK_Y_SIZE, Color.red);
            bricks.add(brick);
        }*/

        int counter = Configuration.BRICK_SCORE;
        while (counter > 0) {
            //System.out.println(counter);
            int abstandLinks = 10;
            int abstandRechts = 10;
            int YPosBrick = (Configuration.BRICK_Y_SIZE);

            int fensterbreiteFuerBricks = Configuration.FIELD_X_SIZE;
            int passendeBricksInBreite = fensterbreiteFuerBricks / Configuration.BRICK_X_SIZE;
            if (counter == 1){
                int einzelAbstand = ((fensterbreiteFuerBricks - Configuration.BRICK_X_SIZE) / 2) + (Configuration.BRICK_X_SIZE / 2);
                abstandLinks =+ einzelAbstand;
                Brick brick = new Brick(this, abstandLinks, YPosBrick,Configuration.BRICK_X_SIZE, Configuration.BRICK_Y_SIZE, Color.red);
                bricks.add(brick);
                counter = 0;


            }
            //2
            if (counter <= passendeBricksInBreite && counter > 1) {

                int insgesamtebrickbreite = counter * Configuration.BRICK_X_SIZE;
                int kompletterAbstand = fensterbreiteFuerBricks - insgesamtebrickbreite;

                int einzelAbstand = kompletterAbstand / counter -1;
                for(int i=0;i<counter - 1;i++){

                    Brick brick = new Brick(this, abstandLinks, YPosBrick,Configuration.BRICK_X_SIZE, Configuration.BRICK_Y_SIZE, Color.red);
                    bricks.add(brick);
                    abstandLinks =+ (einzelAbstand + Configuration.BRICK_X_SIZE);
                    counter = counter - 1;

                }
                //4
            } else if(counter > passendeBricksInBreite){
                YPosBrick += ((Configuration.BRICK_Y_SIZE / 2) + Configuration.BRICK_Y_SIZE);
                int kompletterAbstand = fensterbreiteFuerBricks - (passendeBricksInBreite * Configuration.BRICK_X_SIZE);
                int einzelAbstand = kompletterAbstand / (passendeBricksInBreite - 1);
                for(int i=0;i<passendeBricksInBreite - 1;i++){

                    Brick brick = new Brick(this, abstandLinks, YPosBrick,Configuration.BRICK_X_SIZE, Configuration.BRICK_Y_SIZE, Color.red);
                    bricks.add(brick);
                    abstandLinks =+ (einzelAbstand + Configuration.BRICK_X_SIZE);
                    counter = counter - 1;

                }

            }
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

    //Startet den Timer
    public void start() {
        state = GameState.RUNNING;
        timer = new Timer(Configuration.LOOP_PERIOD, new GameLoop());
        score = 0;
        timer.start();}

    //Gibt onTick im Terminal aus und bewegt ball und paddle, zeichnet im Anschluss alle Komponenten neu
    private void onTick() {
        ball.move();
        paddle.move();

        Rectangle ballHitBox = ball.getHitBox();
        Rectangle nextY = new Rectangle(ballHitBox);
        Rectangle nextX = new Rectangle(ballHitBox);

        //nextX.setLocation(nextX.x, nextX.y + ball.getyVelocity());
        nextY.setLocation(nextY.x, nextY.y - 1 );

        Brick hitBrick = null;
        for (Brick brick : bricks) {
            if (brick.getHitBox().intersects(nextX)) { // hit in the west or east
                ball.setVelocity(-ball.getxVelocity(), ball.getyVelocity());
                hitBrick = brick;
                System.out.println("Hit Seite");
                break;
            }
            if (brick.getHitBox().intersects(nextY)) { // hit in the north or south
                ball.setVelocity(ball.getxVelocity(), -ball.getyVelocity());
                hitBrick = brick;
                System.out.println("Hit oben/unten");
                break;
            } }
        if (hitBrick != null) { // if hit brick then remove it and score
            bricks.remove(hitBrick);
            score ++;
            System.out.println(score);
        }

        // check physics and rules
        if (ball.getHitBox().intersects(paddle.getHitBox())) { // ball hits paddle
            ball.setVelocity(ball.getxVelocity(), -ball.getyVelocity());
        } else if (ball.getY() > Configuration.FIELD_Y_SIZE - 5) { // ball is lost // reduce number of balls
            System.out.println(ballCount);
            --ballCount;
            if (ballCount <= 0) { // no balls left
                state = GameState.GAME_OVER;
                System.out.printf("Game over: You lost. Score = %d%n", score); System.exit(-1);
            } else { restartWithNewBall();
            } }
        if (score == Configuration.BRICK_SCORE) { // no balls left
            state = GameState.GAME_OVER;
            System.out.printf("GG MF: You won!. Score = %d%n", score);
            System.exit(-1);
        }
        repaint();
    }

    public void restartWithNewBall(){
            paddle.setxVelocity(0);
            this.ball = new Ball(this, paddle.xPosition , paddle.yPosition - ((Configuration.PADDLE_Y_SIZE + Configuration.BALL_Y_SIZE)/2),Configuration.BALL_X_SIZE,Configuration.BALL_Y_SIZE,Color.black);
            System.out.println("blub");
            this.ball.setVelocity(-1,-1);
    }

    private class GameLoop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { onTick(); } }

    private class BreakoutKeyAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event)
        { onKeyReleased(event); } @Override

        public void keyPressed(KeyEvent event) { onKeyPressed(event); } }

    public void onKeyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT) { paddle.setxVelocity(-Configuration.PADDLE_VELOCITY);
        }
        if (key == KeyEvent.VK_RIGHT) { paddle.setxVelocity(Configuration.PADDLE_VELOCITY);
        } }
    public void onKeyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) { paddle.setxVelocity(0);
        } }











// if passendeBricksInBreite < brickanzahl
}