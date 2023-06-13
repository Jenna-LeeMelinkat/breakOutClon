import java.awt.*;

/**
 * Die Klasse GameObject ist die Basisklasse für alle Objekte im Spiel und definiert ihre gemeinsamen Eigenschaften.
 *
 * @author Jenna-Lee Melinkat
 * @author Julian Hahnefeld
 */
public class GameObject {

    // Eine Referenz auf das Spiel-Objekt
    private GameLogic game;

    // Die x-Koordinate des Objekts
    protected Integer xPosition;

    // Die y-Koordinate des Objekts
    protected Integer yPosition;

    // Die Breite des Objekts
    protected Integer xSize;

    // Die Höhe des Objekts
    protected Integer ySize;

    // Die Farbe des Objekts
    protected java.awt.Color color;

    /**
     * Konstruktor für die Klasse GameObject.
     * @param game Die Referenz auf das GameLogic-Objekt
     * @param xPosition Die x-Koordinate des Objekts
     * @param yPosition Die y-Koordinate des Objekts
     * @param xSize Die Breite des Objekts
     * @param ySize Die Höhe des Objekts
     * @param color Die Farbe des Objekts
     */
    public GameObject(GameLogic game, Integer xPosition, Integer yPosition, Integer xSize, Integer ySize, Color color) {
        this.game = game;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xSize = xSize;
        this.ySize = ySize;
        this.color = color;
    }

    /**
     * Setzt die Position des Objekts.
     * @param xPos Die neue x-Koordinate des Objekts
     * @param yPos Die neue y-Koordinate des Objekts
     */
    public void setPosition(int xPos, int yPos){
        this.xPosition = xPos;
        this.yPosition = yPos;
    }

    /**
     * Gibt die aktuelle x-Koordinate des Objekts zurück.
     * @return Die aktuelle x-Koordinate des Objekts
     */
    public Integer getX(){
        return xPosition;
    }

    /**
     * Gibt die aktuelle y-Koordinate des Objekts zurück.
     * @return Die aktuelle y-Koordinate des Objekts
     */
    public Integer getY(){
        return yPosition;
    }

    /**
     * Gibt die Breite des Objekts zurück.
     * @return Die Breite des Objekts
     */
    public Integer getxSize(){
        return xSize;
    }

    /**
     * Gibt die Höhe des Objekts zurück.
     * @return Die Höhe des Objekts
     */
    public Integer getySize(){
        return ySize;
    }

    /**
     * Gibt die Hit-Box des Objekts als Rechteck zurück.
     * @return Die Hit-Box des Objekts als Rechteck
     */
    public Rectangle getHitBox(){
        return new Rectangle(xPosition - xSize / 2, yPosition - ySize / 2, xSize, ySize);
    }
}
