/**
 * Die `GameState`-Enum-Klasse definiert die verschiedenen Zustände,
 * die im Spiel auftreten können.
 *
 * @author Jenna-Lee Melinkat
 * @author Julian Hahnefeld
 */
public enum GameState {
    /**
     * Der Setup-Zustand, in dem das Spiel initialisiert wird.
     */
    SETUP,
    /**
     * Der Running-Zustand, in dem das Spiel ausgeführt wird.
     */
    RUNNING,
    /**
     * Der Paused-Zustand, in dem das Spiel angehalten ist.
     */
    PAUSED,
    /**
     * Der Game Over-Zustand, in dem das Spiel beendet ist.
     */
    GAME_OVER,
}
