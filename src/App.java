import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class App {
    static CarModel carModel;
    static CarView carView;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private static final int delay = 50;

    private static class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            carModel.update();
            carView.repaint();
        }
    }

    public static void main(String[] args) {
        carModel = new CarModel();
        
        // Instance of this class
        carModel.cars.add(new Volvo240());

        var saab = new Saab95();
        saab.y = 100;
        carModel.cars.add(saab);

        var scania = new Scandia();
        scania.y = 200;
        carModel.cars.add(scania);

        // Start a new view and send a reference of self
        carView = new CarView(ScreenInfo.X, ScreenInfo.Y-240, carModel);

        new CarController("CarSim 1.0", carModel, carView);
        
        Timer timer = new Timer(delay, new TimerListener());
        // Start the timer
        timer.start();
    }

}
