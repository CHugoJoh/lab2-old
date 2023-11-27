import java.util.ArrayList;

public class SaabMechanic extends Mechanic<Saab95> {

    public SaabMechanic(){
        mechSize = 4;
        carsAtShop = new ArrayList<>();

    }

    public void fixCar(Saab95 car) {
        if(car != null) {
            super.load(car);
        }
        else{
            System.out.println("This is a certified Volvo only shop my friend");
        }
    }
}
