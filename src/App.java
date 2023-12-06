import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class App {
    static CarController cc;
    static DrawPanel drawPanel;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private static final int delay = 50;

    private static class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            cc.update();
            drawPanel.repaint();
        }
    }

    public static void main(String[] args) {
        cc = new CarController();
        
        // Instance of this class
        cc.cars.add(new Volvo240());

        var saab = new Saab95();
        saab.y = 100;
        cc.cars.add(saab);

        var scania = new Scandia();
        scania.y = 200;
        cc.cars.add(scania);

        // Start a new view and send a reference of self
        drawPanel = new DrawPanel(ScreenInfo.X, ScreenInfo.Y-240, cc);

        new CarView("CarSim 1.0", cc, drawPanel);
        
        Timer timer = new Timer(delay, new TimerListener());
        // Start the timer
        timer.start();
    }

}
