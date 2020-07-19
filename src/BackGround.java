import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Polygon;

/**
 * background of level every level.
 */
public class BackGround implements Sprite {
    private int level;

    /**
     * creat background according to the level number.
     * @param myLevel  the level number
     */
    public BackGround(int myLevel) {
        this.level = myLevel;
    }
    /**
     * draw the sprite to the screen.
     *
     * @param d the surface of the game
     */
    public void drawOn(DrawSurface d) {
        // draw the background of level1 which is 3 circles one inside the
        // other and four lines
        if (this.level == 1) {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.BLUE);
            d.drawCircle(395, 130, 40);
            d.drawCircle(395, 130, 60);
            d.drawCircle(395, 130, 80);
            // up
            d.drawLine(395, 130, 395, 25);
            // right
            d.drawLine(410, 130, 500, 130);
            // left
            d.drawLine(395, 130, 290, 130);
            // down
            d.drawLine(395, 130, 395, 230);
        }
        // draw the background of level 2 which is sunshine
        if (this.level == 2) {
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.YELLOW);
            d.drawLine(80, 95, 60, 195);
            d.drawLine(80, 95, 40, 195);
            d.drawLine(80, 95, 20, 195);
            d.drawLine(80, 95, 80, 195);
            d.drawLine(80, 95, 100, 195);
            d.drawLine(80, 95, 120, 195);
            d.drawLine(80, 95, 140, 195);
            d.drawLine(80, 95, 160, 195);
            d.drawLine(80, 95, 180, 195);
            d.drawLine(80, 95, 200, 195);
            d.drawLine(80, 95, 220, 195);
            d.drawLine(80, 95, 240, 195);
            d.drawLine(80, 95, 260, 195);
            d.drawLine(80, 95, 280, 195);
            d.drawLine(80, 95, 300, 195);
            d.setColor(new Color(250, 236, 10));
            d.fillCircle(80, 95, 60);
            d.setColor(Color.YELLOW);
            d.fillCircle(80, 95, 50);
            d.setColor(new Color(200, 210, 10));
            d.fillCircle(80, 95, 40);
        }
        // draw the background of level 3 which is sweet home
        if (this.level == 3) {
            d.setColor(Color.GREEN.darker().darker());
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white.darker());
            d.fillRectangle(50, 300, 200, 200);
            d.setColor(Color.RED.brighter());
            d.fillPolygon(new Polygon(new int[]{150, 250, 50}, new int[]{200,
                    300, 300}, 3));
            d.setColor(Color.BLACK);
            d.drawText(55, 350, "my sweet home :)", 24);
            d.setColor(new Color(139, 69, 19));
            d.fillRectangle(110, 400, 80, 100);
        }
        // draw the background of level 2 which is 2 clouds
        if (this.level == 4) {
            d.setColor(new Color(125, 190, 206));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.WHITE);
            d.drawLine(120, 300, 90, 600);
            d.drawLine(130, 300, 100, 600);
            d.drawLine(140, 300, 110, 600);
            d.drawLine(150, 300, 120, 600);
            d.drawLine(160, 300, 130, 600);
            d.drawLine(170, 300, 140, 600);
            d.drawLine(180, 300, 150, 600);
            d.drawLine(190, 300, 160, 600);
            d.setColor(Color.WHITE);
            d.drawLine(520, 300, 490, 600);
            d.drawLine(530, 300, 500, 600);
            d.drawLine(540, 300, 510, 600);
            d.drawLine(550, 300, 520, 600);
            d.drawLine(560, 300, 530, 600);
            d.drawLine(570, 300, 540, 600);
            d.drawLine(580, 300, 550, 600);
            d.drawLine(590, 300, 560, 600);
            d.setColor(new Color(182, 192, 195));
            d.fillCircle(150, 300, 30);
            d.fillCircle(170, 310, 30);
            d.fillCircle(130, 310, 30);
            d.fillCircle(130, 290, 30);
            d.fillCircle(190, 290, 30);
            d.fillCircle(160, 270, 30);
            d.setColor(new Color(204, 215, 218));
            d.fillCircle(550, 300, 30);
            d.fillCircle(570, 310, 30);
            d.fillCircle(530, 310, 30);
            d.fillCircle(530, 290, 30);
            d.fillCircle(590, 290, 30);
            d.fillCircle(560, 270, 30);
        }
    }

    /**
     * notify the sprite that time has passed.
     *
     */
    public void timePassed() {
    }
}
