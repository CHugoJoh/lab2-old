import java.awt.*;

public class Scandia extends Car implements HasRamp {
    private static double platformDegree = 0;

    public Scandia() {
        nrDoors = 2;
        enginePower = 200;
        color = Color.red;
        modelName = "Scania";
        platformDegree = 0;

        stopEngine();
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    protected void incrementSpeed(double amount) {
        if(getRampDegree() != 0) {
            System.out.println("Flaket måste vara nere.");
        }
        else{
            currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        }

    }

    @Override
    protected void decrementSpeed(double amount) {
        if(getRampDegree() != 0 ){
            System.out.println("Flaket måste vara nere.");
        }
        else{
            decrementSpeed(amount);
        }
    }
    public void clampDegree() {
        if (getRampDegree() < 0.0) {
            platformDegree = 0;
        } else if (getRampDegree() > 70) {
            platformDegree = 70;
        }
    }


    @Override
    public void raiseRamp() {
        if (getCurrentSpeed() == 0.0) {
            platformDegree += 1;
            clampDegree();
        } else {
            System.out.println("Du står inte stilla. Parkera först.");
        }
    }

    @Override
    public void lowerRamp() {
        if (getCurrentSpeed() == 0.0) {
            platformDegree -= 1;
            clampDegree();
        } else {
            System.out.println("Du står inte stilla. Parkera först.");
        }
    }

    @Override
    public double getRampDegree() {
        return platformDegree;
    }
}
