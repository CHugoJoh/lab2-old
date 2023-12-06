import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public abstract class Car implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double direction;
    protected double x;
    protected double y;

    public double getX() { return x; }

    public double getY() { return y; }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
	    color = clr;
    }

    public void startEngine(){
	    currentSpeed = 0.1;
    }

    public void stopEngine(){
	    currentSpeed = 0;
    }

    private void clampCurrentSpeed() {
        currentSpeed = Math.max(0.0, Math.min(enginePower, currentSpeed));
    }

    protected abstract double speedFactor();

    protected abstract void incrementSpeed(double amount);

    protected abstract void decrementSpeed(double amount);

    // TODO fix this method according to lab pm
    public void gas(double amount){
        if (amount > 1.0)
            amount = 1.0;
        else if (amount < 0.0)
            amount = 0.0;
        incrementSpeed(amount);
        clampCurrentSpeed();
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        amount = Math.min(1.0, Math.max(0.0, amount));
        decrementSpeed(amount);
        clampCurrentSpeed();
    }
    public void move(){
        // X is forward upwards
        x += currentSpeed * Math.cos(direction);
        y += currentSpeed * Math.sin(direction);
    }
    public void turnLeft(){
        direction -= Math.PI / 2.0;
    }
    public void turnRight(){
        direction += Math.PI / 2.0;
    }

    // TEMP!!
    public void loadImage() {
        BufferedImage image = null;

        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Remember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            image = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/" + car + ".jpg")));
        } catch (IOException ex)
        {
            // Ignore this will cause an exception
        }

        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            image = ImageIO.read(new File(car + ".jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            // volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
        } catch (IOException ex)
        {
            // Ignore this will cause an exception
        }
    }
}
