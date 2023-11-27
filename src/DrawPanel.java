import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Map;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    Map<Car, CarDrawHelper> carMap = new Hashtable<>();

    // To keep track of a single cars position
    Point carPoint = new Point();

    // TODO: Make this general for all cars
    void moveit(int x, int y, Car car ){
        CarDrawHelper helper = carMap.get(car);
        helper.carPoint = new Point(x,y);
    }

    public void makeCars(ArrayList<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);

            BufferedImage image = null;
            
            // Print an error message in case file is not found with a try/catch block
            try {
                // You can remove the "pics" part if running outside of IntelliJ and
                // everything is in the same main folder.
                // volvoImage = ImageIO.read(new File("Volvo240.jpg"));
    
                // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
                // if you are starting in IntelliJ.
                image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            } catch (IOException ex)
            {
                // Ignore this will cause an exception
            }
    
            try {
                // You can remove the "pics" part if running outside of IntelliJ and
                // everything is in the same main folder.
                image = ImageIO.read(new File("Volvo240.jpg"));
    
                // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
                // if you are starting in IntelliJ.
                // volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            } catch (IOException ex)
            {
                // Ignore this will cause an exception
            }
            
            carMap.put(car, new CarDrawHelper(image, new Point((int)car.getX(), (int)car.getY()))); 
        }

    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.black); // Important: DONT CHANGE!!
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (final Car key : this.carMap.keySet()){
            final CarDrawHelper value = this.carMap.get(key);
            g.drawImage(value.getImage(), (int)value.carPoint.getX(), (int)value.carPoint.getY(), null); // see javadoc for more info on the parameters
        }
    }
}
