import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FlightStatusPanel extends JPanel {
    private static final int LABEL_X = 50;
    private static final int LABEL_WIDTH = 100;
    private static final int FIELD_X = 160;
    private static final int FIELD_WIDTH = 160;
    private static final int COMPONENT_HEIGHT = 25;
    private static final int VERTICAL_SPACING = 40;

    private JTextField flightNumberField;
    private JButton checkStatusButton;

    public FlightStatusPanel() {
        setLayout(null);
        initializeComponents();
        addComponents();
        addActionListeners();
    }

    private void initializeComponents() {
        flightNumberField = new JTextField();
        checkStatusButton = new JButton("Check Status");
    }

    private void addComponents() {
        addComponent(new JLabel("Flight Number:"), flightNumberField, 0);
        addComponent(checkStatusButton, 1);
    }

    private void addComponent(JLabel label, JTextField textField, int row) {
        label.setBounds(LABEL_X, LABEL_X + row * VERTICAL_SPACING, LABEL_WIDTH, COMPONENT_HEIGHT);
        textField.setBounds(FIELD_X, LABEL_X + row * VERTICAL_SPACING, FIELD_WIDTH, COMPONENT_HEIGHT);
        add(label);
        add(textField);
    }

    private void addComponent(JButton button, int row) {
        button.setBounds(FIELD_X, LABEL_X + row * VERTICAL_SPACING, FIELD_WIDTH, COMPONENT_HEIGHT);
        add(button);
    }

    private void addActionListeners() {
        checkStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFlightStatusCheck();
            }
        });
    }

    private void handleFlightStatusCheck() {
        String flightNumber = flightNumberField.getText();
        String status = checkFlightStatus(flightNumber);
        displayFlightStatus(status);
    }

    private String checkFlightStatus(String flightNumber) {
        // Replace with actual logic to check flight status
        return "Flight " + flightNumber + " status: Available";
    }

    private void displayFlightStatus(String status) {
        JOptionPane.showMessageDialog(null, status, "Flight Status", JOptionPane.INFORMATION_MESSAGE);
    }

    public JTextField getFlightNumberField() {
        return flightNumberField;
    }

    public JButton getCheckStatusButton() {
        return checkStatusButton;
    }
}