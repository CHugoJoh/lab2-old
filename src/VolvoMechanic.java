import java.util.ArrayList;

public class VolvoMechanic extends Mechanic<Volvo240>{


    public VolvoMechanic(){
        mechSize = 4;
        carsAtShop = new ArrayList<>();
    }

    public void fixCar(Volvo240 car) {
        if(car != null) {
            super.load(car);
        }
        else{
            System.out.println("This is a certified Volvo only shop my friend");
        }
    }
}

