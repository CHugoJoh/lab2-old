import java.util.ArrayList;

public class ScandiaMechanic extends Mechanic<Scandia> {

        public ScandiaMechanic(){
            mechSize = 4;
            carsAtShop = new ArrayList<>();
        }

        public void fixCar(Scandia car) {
            if(car != null) {
                super.load(car);
            }
            else{
                System.out.println("This is a certified Volvo only shop my friend");
            }
        }
    }
