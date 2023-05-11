import java.awt.*;
/**
 * Die Klasse Brick ist ein Untertyp von GameObject und repräsentiert einen Ziegelstein im Spiel.
 *
 * @author Jenna-Lee Melinkat
 * @author Julian Hahnefeld
 */
public class Brick extends GameObject {
    // true, wenn der Ziegelstein zerstört wurde, sonst false
    private boolean destroyed;

    /**
     * Konstruktor für den Ziegelstein.
     *
     * @param game die Spiellogik, zu der der Ziegelstein gehört
     * @param xPosition die X-Koordinate der oberen linken Ecke des Ziegelsteins
     * @param yPosition die Y-Koordinate der oberen linken Ecke des Ziegelsteins
     * @param xSize die Breite des Ziegelsteins
     * @param ySize die Höhe des Ziegelsteins
     * @param color die Farbe des Ziegelsteins
     */
    public Brick(GameLogic game, int xPosition, int yPosition, int xSize, int ySize, Color color) {
        super(game, xPosition, yPosition, xSize, ySize, color);
    }

    /**
     * Gibt zurück, ob der Ziegelstein zerstört wurde.
     *
     * @return true, wenn der Ziegelstein zerstört wurde, sonst false
     */
    public boolean isDestroyed(){
        return false;
    }

    /**
     * Setzt den zerstört-Status des Ziegelsteins.
     *
     * @param destroyed true, wenn der Ziegelstein zerstört wurde, sonst false
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Setzt den Ziegelstein in den unzerstörten Zustand zurück.
     */
    public void setIntact(){

    }

}
