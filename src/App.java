import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class App {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private static final int delay = 50;

    Timer timer = new Timer(delay, new TimerListener());

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

                collision(car, x, y);
            }
        }
    }

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());

        var saab = new Saab95();
        saab.y = 100;
        cc.cars.add(saab);

        var scania = new Scandia();
        scania.y = 200;
        cc.cars.add(scania);

        // Start a new view and send a reference of self
        DrawPanel drawPanel = new DrawPanel(ScreenInfo.X, ScreenInfo.Y-240, cc);

        CarView view = new CarView("CarSim 1.0", cc, drawPanel);

        // Start the timer
        cc.timer.start();
    }

}
