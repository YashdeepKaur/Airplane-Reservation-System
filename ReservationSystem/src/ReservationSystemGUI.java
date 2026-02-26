import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReservationSystemGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private LoginSignupPanel loginSignupPanel;
    private FirstPagePanel firstPagePanel;
    private BookTicketPanel bookTicketPanel;
    private CancellationPanel cancellationPanel;
    private UserProfilePanel userProfilePanel;
    private FlightStatusPanel flightStatusPanel;
    private FeedbackPanel feedbackPanel;
    private SearchPanel searchPanel;

    public ReservationSystemGUI() {
        setTitle("Airline Reservation System");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        add(cardsPanel);

        initializePanels();
        addPanelsToCardLayout();
        setupActionListeners();

        // Create and set the menu bar
        JMenuBar menuBar = firstPagePanel.createMenuBar(); // Use the menu bar creation method from FirstPagePanel
        setJMenuBar(menuBar);

        cardLayout.show(cardsPanel, "LoginSignupPanel");
        setVisible(true);
    }

    private void initializePanels() {
        loginSignupPanel = new LoginSignupPanel();
        firstPagePanel = new FirstPagePanel();

        bookTicketPanel = new BookTicketPanel();
        cancellationPanel = new CancellationPanel();
        userProfilePanel = new UserProfilePanel();
        flightStatusPanel = new FlightStatusPanel();
        feedbackPanel = new FeedbackPanel();
        searchPanel = new SearchPanel();
    }

    private void addPanelsToCardLayout() {
        cardsPanel.add(loginSignupPanel, "LoginSignupPanel");
        cardsPanel.add(firstPagePanel, "FirstPagePanel");
        cardsPanel.add(bookTicketPanel, "BookTicketPanel");
        cardsPanel.add(cancellationPanel, "CancellationPanel");
        cardsPanel.add(userProfilePanel, "UserProfilePanel");
        cardsPanel.add(flightStatusPanel, "FlightStatusPanel");
        cardsPanel.add(feedbackPanel, "FeedbackPanel");
        cardsPanel.add(searchPanel, "SearchPanel");
    }

    private void setupActionListeners() {
        loginSignupPanel.getLoginButton().addActionListener(e -> {
            String username = loginSignupPanel.getUsernameField().getText();
            String password = new String(loginSignupPanel.getPasswordField().getPassword());
            if (validateLogin(username, password)) {
                cardLayout.show(cardsPanel, "FirstPagePanel");
                System.out.println("Login successful. Showing FirstPagePanel.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginSignupPanel.getSignupButton().addActionListener(e -> {
            String username = loginSignupPanel.getUsernameField().getText();
            String password = new String(loginSignupPanel.getPasswordField().getPassword());
            String fullname = loginSignupPanel.getFullNameField().getText();
            String email = loginSignupPanel.getEmailField().getText();

            if (registerUser(username, password, fullname, email)) {
                JOptionPane.showMessageDialog(this, "Signup successful. Please login.", "Signup Successful", JOptionPane.INFORMATION_MESSAGE);
                loginSignupPanel.getUsernameField().setText("");
                loginSignupPanel.getPasswordField().setText("");
                loginSignupPanel.getFullNameField().setText("");
                loginSignupPanel.getEmailField().setText("");
                cardLayout.show(cardsPanel, "LoginSignupPanel");
            } else {
                JOptionPane.showMessageDialog(this, "Signup failed. Please try again.", "Signup Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        firstPagePanel.getBookTicketMenuItem().addActionListener(e -> cardLayout.show(cardsPanel, "BookTicketPanel"));
        firstPagePanel.getCancellationMenuItem().addActionListener(e -> cardLayout.show(cardsPanel, "CancellationPanel"));
        firstPagePanel.getFlightStatusMenuItem().addActionListener(e -> cardLayout.show(cardsPanel, "FlightStatusPanel"));
        firstPagePanel.getUserProfileMenuItem().addActionListener(e -> cardLayout.show(cardsPanel, "UserProfilePanel"));
        firstPagePanel.getFeedbackMenuItem().addActionListener(e -> cardLayout.show(cardsPanel, "FeedbackPanel"));

        firstPagePanel.getSearchMenuItem().addActionListener(e -> cardLayout.show(cardsPanel, "SearchPanel"));

        searchPanel.getSearchButton().addActionListener(e -> {
            String departureCity = searchPanel.getDepartureCityField().getText();
            String arrivalCity = searchPanel.getArrivalCityField().getText();
            searchFlights(departureCity, arrivalCity);
        });
    }

    private boolean validateLogin(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error validating login: " + e.getMessage());
            return false;
        }
    }

    private boolean registerUser(String username, String password, String fullname, String email) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password, fullname, email) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fullname);
            preparedStatement.setString(4, email);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    private void searchFlights(String departureCity, String arrivalCity) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT * FROM flights WHERE departure_city_name = ? AND arrival_city_name = ?")) {
            preparedStatement.setString(1, departureCity);
            preparedStatement.setString(2, arrivalCity);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Build the result string
                    StringBuilder results = new StringBuilder();
                    do {
                        results.append("Flight Number: ").append(resultSet.getString("flight_number")).append("\n")
                               .append("Departure City: ").append(resultSet.getString("departure_city_name")).append("\n")
                               .append("Arrival City: ").append(resultSet.getString("arrival_city_name")).append("\n")
                               .append("Departure Time: ").append(resultSet.getString("departure_time")).append("\n")
                               .append("Arrival Time: ").append(resultSet.getString("arrival_time")).append("\n")
                               .append("Price: $").append(resultSet.getString("price")).append("\n\n");
                    } while (resultSet.next());
    
                    // Update the results in the SearchPanel
                    searchPanel.updateResults(results.toString());
                } else {
                    searchPanel.updateResults("No flights found for the given cities.");
                }
            }
        } catch (SQLException e) {
            searchPanel.updateResults("Error searching flights: " + e.getMessage());
        }
    }
    
    
    public static void main(String[] args) {
        new ReservationSystemGUI();
    }
}
