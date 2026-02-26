import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginSignupPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JTextField fullNameText;
    private JTextField emailText;
    private JButton loginButton;
    private JButton signupButton;

    public LoginSignupPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createSignupPanel(), "Signup");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }

    private JPanel createSignupPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel usernameLabel = new JLabel("Username:");
        usernameText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordText = new JPasswordField();
        JLabel fullnameLabel = new JLabel("Fullname:");
        fullNameText = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailText = new JTextField();
        JButton signupButton = new JButton("Sign Up");

        panel.add(usernameLabel);
        panel.add(usernameText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(fullnameLabel);
        panel.add(fullNameText);
        panel.add(emailLabel);
        panel.add(emailText);
        panel.add(new JLabel());
        panel.add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());
                String fullname = fullNameText.getText();
                String email = emailText.getText();

                try (Connection connection = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO Users (username, password, full_name, email) VALUES (?,?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.setString(3, fullname);
                    statement.setString(4, email);
                    statement.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Sign up successful!");
                    cardLayout.show(mainPanel, "Login");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error: Failed to sign up.");
                }
            }
        });

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginButton);
        panel.add(signupButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                try (Connection connection = DatabaseConnection.getConnection()) {
                    String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(panel, "Login successful!");
                        // Navigate to main application panel or dashboard
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid username or password.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error: Failed to login.");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Signup");
            }
        });

        return panel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignupButton() {
        return signupButton;
    }

    public JTextField getFullNameField() {
        return fullNameText;
    }

    public JTextField getUsernameField() {
        return usernameText;
    }

    public JPasswordField getPasswordField() {
        return passwordText;
    }

    public JTextField getEmailField() {
        return emailText;
    }
}
