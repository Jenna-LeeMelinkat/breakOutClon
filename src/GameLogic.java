import java.util.List;

/**
 * Die Klasse GameLogic ist verantwortlich für die Logik des Spiels und verwaltet den Spielzustand.
 *
 * @version [Versionsnummer]
 */
public class GameLogic {
    // Die Anzahl der Bälle im Spiel
    private Integer ballCount;

    // Der aktuelle Punktestand
    private Integer score;

    // Eine Liste der verbleibenden Ziegel im Spiel
    private List<Brick> bricks;

    // Der Paddle des Spielers
    private Paddle paddle;

    // Der Ball, der im Spiel ist
    private Ball ball;

    // Der aktuelle Zustand des Spiels (z.B. laufend, beendet)
    private GameState game;

    /**
     * Konstruktor für die Klasse GameLogic.
     */
    public GameLogic(){
    }
}