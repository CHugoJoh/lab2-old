import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<String> getMakes() {
        List<String> list = new ArrayList<String>(); 
        list.add("Random");
        list.add("Volvo240");
        list.add("Saab95");
        list.add("Scania");
        return list;
    }

    public List<String> getCarsWithsRandom() {
        List<String> list = new ArrayList<String>();
        list.add("Random");
        for (int i = 0; i < cars.size(); i++) {
            list.add("[" + i + "] " + cars.get(i).toString());
        }

        return list;
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
    void startCars(){
        for(Car car : cars) {
            car.startEngine();
        }
    }
    void stopCars(){
        for(Car car : cars) {
            car.stopEngine();
        }
    }
    void addCar(String make){
        if(cars.size() < 10){
        switch(make){
            case "Random":
                addRandom();
                break;
            case "Volvo240":
                addVolvo();
                break;
            case "Saab95":
                addSaab();
                break;
            case "Scania":
                addScania();
                break;
            default:
                break;
        }}
        arrange();
    }
    void removeCar(String make){
        if (cars.size() == 0)
            return;

        if (make == "Random") {
            Random random = new Random();
            cars.remove(random.nextInt(cars.size()));
        }

        cars.remove(Character.getNumericValue(make.charAt(1)));

        arrange();
    }
    
    void addVolvo(){
        Car Volvo240 = new Volvo240();
        cars.add(Volvo240);
    }
    void addSaab(){
        Car Saab95 = new Saab95();
        cars.add(Saab95);
    }
    void addScania() {
        Car Scania = new Scandia();
        cars.add(Scania);
    }
    void addRandom(){
        Random random = new Random();
        int high = random.nextInt(3);
        switch(high){
            case 0:
                addVolvo();
                break;
            case 1:
                addSaab();
                break;
            case 2:
                addScania();
                break;
        }
    }
    
    public void arrange() {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).setY((i % 5) * 100);
            cars.get(i).setX((i / 5) * 300);
        }
    }
}
