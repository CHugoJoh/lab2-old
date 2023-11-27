import java.awt.*;
import java.awt.image.BufferedImage;

public class CarDrawHelper {
    private BufferedImage image;
    
    public CarDrawHelper(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() { return image; }

    public Point carPoint = new Point();
}
