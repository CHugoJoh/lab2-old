import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController{
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public static final int CarWidth = 100; //1 spiskumin == 8 gul lök

    public static final int CarHeight = 60;

    //methods:

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
        cc.frame = new CarView("CarSim 1.0", cc, cc.cars);

        // Start the timer
        cc.timer.start();
    }

    public static void collision(Car car, int x, int y) {
        if (x + CarWidth > CarView.X) {
            car.x = CarView.X - CarWidth;
            car.currentSpeed = 0;
            car.turnLeft();
            car.turnLeft(); // Illegal! :3
        }

        if (x < 0) {
            car.x = 0;
            car.currentSpeed = 0;
            car.turnLeft();
            car.turnLeft(); // Illegal! :3
        }
    }
    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

                collision(car, x, y);
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(gas);

        }
    }

    void brake(int amount){
        double brake = ((double) amount / 100);
        for (Car car : cars){
            car.brake(brake);

        }
    }

    void turboOn(){
        for (Car car : cars) {
            if (car instanceof Saab95)
                ((Saab95)car).setTurboOn();
        }
    }

    void turboOff(){
        for(Car car : cars) {
            if (car instanceof Saab95)
                ((Saab95) car).setTurboOff();
        }
    }

    void liftBed(){
        for(Car car : cars) {
            if (car instanceof Scandia)
                ((Scandia) car).raiseRamp();
        }
    }
    void lowerBed(){
        for(Car car : cars) {
            if (car instanceof Scandia)
                ((Scandia) car).lowerRamp();
            }
        }
}
