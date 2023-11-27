import java.awt.*;
import java.awt.image.BufferedImage;

public class CarDrawHelper {
    private BufferedImage image;
    
    public CarDrawHelper(BufferedImage image, Point point) {
        this.image = image;
        this.carPoint = point;
    }

    public BufferedImage getImage() { return image; }

    public Point carPoint = new Point();
}
