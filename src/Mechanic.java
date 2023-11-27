import java.util.*;

public class Mechanic<T extends Car> {

    protected static int mechSize;

    public Mechanic(){
        mechSize = 4;
    }

    public int getMechSize() {
        return mechSize;
    }

    public void setMechSize(int i) {
        mechSize = i;
    }

    protected ArrayList<T> carsAtShop = new ArrayList<>(mechSize);
    
    public void load(T car){
        if(carsAtShop.size() < mechSize) {
            carsAtShop.add(car);
        }
        else{
            System.out.println("We are at full capacity. Come back another day");
        }
    }

    public void unload(T car){
        if(carsAtShop.contains(car)){
            carsAtShop.remove(car);
        }
        else{
            System.out.println("the car is in another verkstad mate.");
        }
    }
}
