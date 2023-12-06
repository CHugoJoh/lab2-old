public class App {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private static final int delay = 50;
        // The timer is started with an listener (see below) that executes the statements
        // each step between delays.
        Timer timer = new Timer(delay, new TimerListener());
    }
        /* Each step the TimerListener moves all the cars in the list and tells the
        * view to update its images. Change this method to your needs.
        * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

            collision(car, x, y);
            }
        }
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
