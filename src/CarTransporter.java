import java.util.*;
import java.awt.*;

public class CarTransporter extends Car implements HasRamp {

    final double rampDownDegree = 20.0;
    private Stack<TransportableCar> loadedCars = new Stack<TransportableCar>(); 
    private int maxLoad;
    private double rampDegree = 0.0;

    CarTransporter() {
        maxLoad = 8;
        nrDoors = 2;
        color = Color.decode("#aa90F1");
        enginePower = 300;
        modelName = "Car transporter";
        stopEngine();
    }

    @Override
    protected double speedFactor() {
        // TODO: Make more sense
        return enginePower - loadedCars.size() / maxLoad; 
    }

    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    @Override
    protected void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    @Override
    public void raiseRamp() {
        if (currentSpeed > 0.000000001) 
            System.out.println("Hallå stå still om du ska göra så!");
        else
            rampDegree = rampDownDegree;
    }

    @Override
    public void lowerRamp() {
        rampDegree = 0;
    }

    @Override
    public double getRampDegree() {
        return rampDegree;
    }

    public int numberOfLoadedCars() { return loadedCars.size(); }
    
    private boolean isRampDown() {
        return Math.abs(rampDegree - rampDownDegree) < 0.0000001;
    }

    public void load(TransportableCar transportable) {
        if (!isRampDown())
            System.out.println("Men släpp ner rampen dårå!");
        else if (!transportable.isCloseEnough(getX(), getY()))
            System.out.println("Ställ bilen näärmare!");
        else if (loadedCars.size() + 1 >= maxLoad)
            System.out.println("För mänge biler!"); //biler??
        else
            loadedCars.push(transportable);
    }

    public void unload() {
        if (!isRampDown())
            System.out.println("Men släpp ner rampen dårå!");
        else if (loadedCars.size() == 0)
            System.out.println("Schlut på biler!");
        else 
            loadedCars.pop().offLoad(getX(), getY()); 
    }

    @Override
    public void move(){
        super.move();
        loadedCars.forEach(x -> x.transport(getX(), getY()));
    }

}
