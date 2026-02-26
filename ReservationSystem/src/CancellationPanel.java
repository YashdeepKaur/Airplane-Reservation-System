import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CancellationPanel extends JPanel {
    private JTextField flightNumberField;
    private JTextField passportNoField;
    private JButton cancelButton;

    public CancellationPanel() {
        setLayout(null);

        JLabel flightNumberLabel = new JLabel("Flight Number:");
        flightNumberLabel.setBounds(50, 50, 80, 25);
        add(flightNumberLabel);

        flightNumberField = new JTextField();
        flightNumberField.setBounds(140, 50, 160, 25);
        add(flightNumberField);

        JLabel passportNoLabel = new JLabel("Passport No:");
        passportNoLabel.setBounds(50, 80, 80, 25);
        add(passportNoLabel);

        passportNoField = new JTextField();
        passportNoField.setBounds(140, 80, 160, 25);
        add(passportNoField);

        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(140, 110, 160, 25);
        add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightNumber = flightNumberField.getText();
                String passportNo = passportNoField.getText();

                if (flightNumber.isEmpty() || passportNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both flight number and passport number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean canceled = cancelTicket(flightNumber, passportNo);
                if (canceled) {
                    cancellationConfirmed();
                } else {
                    cancellationFailed();
                }
            }
        });
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public boolean cancelTicket(String flightNumber, String passportNo) {
        // Simulate a successful cancellation
        return true;
    }

    public void cancellationConfirmed() {
        JOptionPane.showMessageDialog(null, "Ticket cancellation confirmed.", "Success", JOptionPane.INFORMATION_MESSAGE);
        // Notify user or navigate to next panel
    }

    public void cancellationFailed() {
        JOptionPane.showMessageDialog(null, "Ticket cancellation failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        // Main method for standalone testing (optional)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                javax.swing.JFrame frame = new javax.swing.JFrame("Ticket Cancellation");
                frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 250);
                frame.add(new CancellationPanel());
                frame.setVisible(true);
            }
        });
    }
}