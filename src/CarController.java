import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController{
    // member fields:
    public Iterable<Car> getCars() {
        return cars;
    }

    public int getCarAmount() { return cars.size(); }

    public String getMakeFromIndex(int i) {
        switch(i) {
            case -1:
                return "Random";
            case 0:
                return "Volvo240";
            case 1:
                return "Saab95";
            case 2:
                return "Scania";
            default:
                throw new IndexOutOfBoundsException("????? HOW ?????? IMPLAUSABLE");
        }
    }

    public String getCarFromIndex(int i) {
        if (i == -1)
            return "Random";
        
        return cars.get(i).toString();
    }

    public void update(){
        for (Car car : cars) {
            car.move();
            int x = (int) Math.round(car.getX());
            int y = (int) Math.round(car.getY());

            collision(car, x, y);
        }
    }

    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public static final int CarWidth = 100; //1 spiskumin == 8 gul lök

    public static final int CarHeight = 60;

    //methods:

    public static void collision(Car car, int x, int y) {
        if (x + CarWidth > ScreenInfo.X) {
            car.x = ScreenInfo.X - CarWidth;
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

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double) amount / 100);
        for (Car car : cars){
            car.brake(brake);

        }
    }

    // This is definetly the best way to do this
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
