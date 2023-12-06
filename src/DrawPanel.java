import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private CarController m_model;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarController model) {
        m_model = model;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.black); // Important: DONT CHANGE!!
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Car car : m_model.getCars()){
            g.drawImage(car.getImage(), (int)car.getX(), (int)car.getY(), null); // see javadoc for more info on the parameters
        }
    }
}
