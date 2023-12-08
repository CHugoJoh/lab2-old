import java.awt.*;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class CarView extends JPanel{

    private CarModel m_model;

    // Initializes the panel and reads the images
    public CarView(int x, int y, CarModel model) {
        m_model = model;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.black); // Important: DONT CHANGE!!
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Car car : m_model.getCars()){
            g.drawImage(car.getImage(), (int)car.getX(), (int)car.getY(), null); // see javadoc for more info on the parameters
        }
    }
}
