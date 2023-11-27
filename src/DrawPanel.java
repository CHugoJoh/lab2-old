import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Enumeration;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    Dictionary<String, Integer> dict= new Hashtable<>();
    dict.put("Volvo", 10);
    dict.put("Saab", 10);
    dict.put("Scania", 10);

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    // To keep track of a single cars position
    Point carPoint = new Point();

    // TODO: Make this general for all cars
    void moveit(int x, int y){
        carPoint.x = x;
        carPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.black); // Important: DONT CHANGE!!!
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
        } catch (IOException ex)
        {
            // Ignore this will cause an exception
        }

        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            // volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
        } catch (IOException ex)
        {
            // Ignore this will cause an exception
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(dict.get("Volovo"), volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }
}
