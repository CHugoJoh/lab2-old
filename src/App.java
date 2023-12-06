import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class App {
    static CarController cc = new CarController();
    static DrawPanel drawPanel = new DrawPanel();
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private static final int delay = 50;

    static Timer timer = new Timer(delay, new TimerListener());

    private class TimerListener implements ActionListener {
        public void actionPerformed(){
            cc.update();
            drawPanel.repaint();
        }
    }

    public static void main(String[] args) {
        // Instance of this class
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
