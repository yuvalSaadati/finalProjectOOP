import biuoop.DrawSurface;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.InputStream;

/**
 * ceate a sprite that os the background of the level.
 */
public class ImageBackground implements Sprite {
    private String image;

    /**
     * constructor.
     * @param image the image name
     */
    public ImageBackground(String image) {
        this.image = image;
    }

    /**
     * print the image of the screen.
     * @param surface of the level
     */
    public void drawOn(DrawSurface surface) {
        // get only the name of the image
        String imageName = this.image.substring(6, this.image.length() - 1);
        // get fron the streamer the image
        InputStream is =
                ClassLoader.getSystemClassLoader().getResourceAsStream(imageName);
        Image img = null;
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("cant load the image");
        }
        // draw the image
        surface.drawImage(0, 0, img);
    }
    /**
     * do nothing.
     */
    public void timePassed() {
    }
}
