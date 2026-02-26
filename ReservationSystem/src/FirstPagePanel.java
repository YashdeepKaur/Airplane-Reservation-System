import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FirstPagePanel extends JPanel {

    private JMenu airlineSystemMenu;
    private JMenuItem bookTicketMenuItem;
    private JMenuItem flightStatusMenuItem;
    private JMenuItem userProfileMenuItem;
    private JMenuItem cancellationMenuItem;
    private JMenuItem feedbackMenuItem;
    private JMenuItem searchMenuItem; // New menu item
   
    public FirstPagePanel() {
        setLayout(new BorderLayout());
        

        // Background image
        String imagePath = "D:\\ReservationSystem\\.vscode\\front.jpg";
        ImageIcon icon = new ImageIcon(imagePath);
        java.awt.Image image = icon.getImage();
        java.awt.Image newimg = image.getScaledInstance(1064, 768, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        JLabel backgroundLabel = new JLabel(icon);
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome To RV Airways, Your home in the sky", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.BLACK);
        backgroundLabel.add(welcomeLabel, BorderLayout.CENTER);

        // Create menu and menu items
        airlineSystemMenu = new JMenu("AIRLINE SYSTEM");
        airlineSystemMenu.setForeground(Color.BLUE);

        bookTicketMenuItem = new JMenuItem("BOOK TICKET");
        airlineSystemMenu.add(bookTicketMenuItem);

        flightStatusMenuItem = new JMenuItem("FLIGHT STATUS");
        airlineSystemMenu.add(flightStatusMenuItem);

        userProfileMenuItem = new JMenuItem("USER PROFILE");
        airlineSystemMenu.add(userProfileMenuItem);

        cancellationMenuItem = new JMenuItem("CANCELLATION");
        airlineSystemMenu.add(cancellationMenuItem);

        feedbackMenuItem = new JMenuItem("FEEDBACK");
        airlineSystemMenu.add(feedbackMenuItem);

        searchMenuItem = new JMenuItem("SEARCH"); // New menu item
        airlineSystemMenu.add(searchMenuItem);
    }

    public JMenu getAirlineSystemMenu() {
        return airlineSystemMenu;
    }

    public JMenuItem getBookTicketMenuItem() {
        return bookTicketMenuItem;
    }

    public JMenuItem getFlightStatusMenuItem() {
        return flightStatusMenuItem;
    }

    public JMenuItem getUserProfileMenuItem() {
        return userProfileMenuItem;
    }

    public JMenuItem getCancellationMenuItem() {
        return cancellationMenuItem;
    }

    public JMenuItem getFeedbackMenuItem() {
        return feedbackMenuItem;
    }

    public JMenuItem getSearchMenuItem() {
        return searchMenuItem;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        

        // Create "HOME" menu and add it to the menu bar
        JMenu homeMenu = new JMenu("HOME");
        homeMenu.setForeground(Color.RED);
        menuBar.add(homeMenu);

       
        // Add the airline system menu to the menu bar
        menuBar.add(airlineSystemMenu);

        // Create and add other menus to the menu bar
        JMenu ticketMenu = new JMenu("TICKET");
        ticketMenu.setForeground(Color.RED);
        menuBar.add(ticketMenu);

        JMenu listMenu = new JMenu("LIST");
        listMenu.setForeground(Color.BLUE);
        menuBar.add(listMenu);

        JMenu miscMenu = new JMenu("MISC");
        miscMenu.setForeground(Color.RED);
        menuBar.add(miscMenu);

        return menuBar;
    }
}
