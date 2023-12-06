public class App {
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
        DrawPanel drawPanel = new DrawPanel(ScreenInfo.X, ScreenInfo.Y-240, cc);

        CarView view = new CarView("CarSim 1.0", cc, drawPanel);

        // Start the timer
        cc.timer.start();
    }
}
