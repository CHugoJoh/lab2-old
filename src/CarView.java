import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame{
    // The controller member
    CarController carC;

    JPanel drawPanel;

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    int brakeAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JPanel adderPanel = new JPanel();
    JButton addButton = new JButton("Add");
    JButton removeButton = new JButton("Remove");

    JLabel makePickerInfo = new JLabel("Random");
    JSpinner makePicker;
    int makePickerData = -1;

    JLabel carPickerInfo = new JLabel("Random");
    JSpinner carPicker;
    int carPickerData = -1;

    // Constructor
    public CarView(String framename, CarController cc, JPanel drawPanel){
        this.carC = cc;

        this.drawPanel = drawPanel;

        initComponents(framename);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {
        
        this.setTitle(title);
        this.setPreferredSize(new Dimension(ScreenInfo.X, ScreenInfo.Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        
        this.add(drawPanel);
        
        SpinnerModel spinnerModel =
        new SpinnerNumberModel(0, //initial value
        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        adderPanel.setLayout(new GridLayout(7, 0, 0, 0));
        adderPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        adderPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        var addRemoveCarsLabel = new JLabel("Add/Remove cars");
        var daFont = addRemoveCarsLabel.getFont();
        Map<TextAttribute, Object> daAttributes = new HashMap<>(daFont.getAttributes());
        daAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        addRemoveCarsLabel.setFont(daFont.deriveFont(daAttributes));
        adderPanel.add(addRemoveCarsLabel, 0);

        

        var makePickerModel = new SpinnerNumberModel(-1, -1, 2, 1);
        makePicker = new JSpinner(makePickerModel);
        makePicker.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                makePickerData = (int) ((JSpinner)e.getSource()).getValue();
                makePickerInfo.setText(carC.getMakeFromIndex(makePickerData));
            }
        });

        adderPanel.add(makePicker, 1);
        adderPanel.add(makePickerInfo, 2);
        adderPanel.add(addButton, 3);
        
        
        var carPickerModel = new SpinnerNumberModel(-1, -1, carC.getCarAmount() - 1, 1);
        carPicker = new JSpinner(carPickerModel);
        carPicker.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                carPickerData = (int) ((JSpinner)e.getSource()).getValue();
                carPickerInfo.setText(carC.getMakeFromIndex(carPickerData));
            }
        });

        adderPanel.add(carPicker, 4);
        adderPanel.add(carPickerInfo, 5);
        adderPanel.add(removeButton, 6);

        
        this.add(adderPanel);

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        
        this.add(gasPanel);
        
        controlPanel.setLayout(new GridLayout(2,4));
        

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((ScreenInfo.X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(ScreenInfo.X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(ScreenInfo.X/5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.gas(gasAmount);
            }
        });

        brakeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent b) {
                carC.brake(gasAmount);
            }
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                carC.turboOn();
            }
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                carC.turboOff();
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                carC.liftBed();
            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                carC.lowerBed();
            }
        });

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}