import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserProfilePanel extends JPanel {
    private static final int LABEL_X = 50;
    private static final int LABEL_WIDTH = 80;
    private static final int FIELD_X = 140;
    private static final int FIELD_WIDTH = 160;
    private static final int COMPONENT_HEIGHT = 25;
    private static final int VERTICAL_SPACING = 40;

    private JTextField fullNameField;
    private JTextField emailField;
    private JButton updateButton;
    private JButton createButton;

    public UserProfilePanel() {
        setLayout(null);
        initializeComponents();
        addComponents();
        addActionListeners();
    }

    private void initializeComponents() {
        fullNameField = new JTextField();
        emailField = new JTextField();
        createButton = new JButton("Create Profile");
        updateButton = new JButton("Update Profile");
    }

    private void addComponents() {
        addComponent(new JLabel("Full Name:"), 0);
        addComponent(fullNameField, 0);
        addComponent(new JLabel("Email:"), 1);
        addComponent(emailField, 1);
        addComponent(createButton, 2);
        addComponent(updateButton, 3);
    }

    private void addComponent(JLabel label, int row) {
        label.setBounds(LABEL_X, LABEL_X + row * VERTICAL_SPACING, LABEL_WIDTH, COMPONENT_HEIGHT);
        add(label);
    }

    private void addComponent(JTextField textField, int row) {
        textField.setBounds(FIELD_X, LABEL_X + row * VERTICAL_SPACING, FIELD_WIDTH, COMPONENT_HEIGHT);
        add(textField);
    }

    private void addComponent(JButton button, int row) {
        button.setBounds(FIELD_X, LABEL_X + row * VERTICAL_SPACING, FIELD_WIDTH, COMPONENT_HEIGHT);
        add(button);
    }

    private void addActionListeners() {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleProfileCreation();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleProfileUpdate();
            }
        });
    }

    private void handleProfileCreation() {
        boolean created = createProfile(fullNameField.getText(), emailField.getText());
        if (created) {
            profileCreationSuccessful();
        } else {
            profileCreationFailed();
        }
    }

    private void handleProfileUpdate() {
        boolean updated = updateProfile(fullNameField.getText(), emailField.getText());
        if (updated) {
            profileUpdateSuccessful();
        } else {
            profileUpdateFailed();
        }
    }

    private boolean createProfile(String fullName, String email) {
        // Implement the logic to create a profile
        return true; // Replace with actual logic
    }

    private boolean updateProfile(String fullName, String email) {
        // Implement the logic to update a profile
        return true; // Replace with actual logic
    }

    private void profileCreationSuccessful() {
        JOptionPane.showMessageDialog(null, "Profile created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void profileCreationFailed() {
        JOptionPane.showMessageDialog(null, "Profile creation failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void profileUpdateSuccessful() {
        JOptionPane.showMessageDialog(null, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void profileUpdateFailed() {
        JOptionPane.showMessageDialog(null, "Profile update failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public JTextField getFullNameField() {
        return fullNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }
}