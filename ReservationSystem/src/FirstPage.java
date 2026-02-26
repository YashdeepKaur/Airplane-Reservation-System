import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FirstPage extends JPanel {

    private JMenu airlineSystemMenu;
    private JMenuItem bookTicketMenuItem;
    private JMenuItem flightstatusMenuItem;
    private JMenuItem userProfileMenuItem;
    private JMenuItem cancellationMenuItem;
    private JMenuItem feedbackMenuItem;
    private JMenuItem homeMenuItem;
    private JMenu ticketMenu;
    private JMenuItem listMenuItem;
    private JMenu miscMenu;

    public FirstPage() {
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
        welcomeLabel.setForeground(Color.BLUE);
        backgroundLabel.add(welcomeLabel, BorderLayout.CENTER);

        // Create menu and menu items
        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH);

        homeMenuItem = new JMenuItem("HOME");
        homeMenuItem.setForeground(Color.RED);
        homeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to return to the first page
                System.out.println("Return to the first page");
            }
        });
        menuBar.add(homeMenuItem);

        ticketMenu = new JMenu("TICKET");
        ticketMenu.setForeground(Color.RED);
        menuBar.add(ticketMenu);

        listMenuItem = new JMenuItem("LIST");
        listMenuItem.setForeground(Color.BLUE);
        ticketMenu.add(listMenuItem);

        miscMenu = new JMenu("MISC");
        miscMenu.setForeground(Color.RED);
        menuBar.add(miscMenu);

        airlineSystemMenu = new JMenu("AIRLINE SYSTEM");
        airlineSystemMenu.setForeground(Color.BLUE);
        menuBar.add(airlineSystemMenu);

        bookTicketMenuItem = new JMenuItem("BOOK TICKET");
        airlineSystemMenu.add(bookTicketMenuItem);

        flightstatusMenuItem = new JMenuItem("FLIGHT STATUS");
        airlineSystemMenu.add(flightstatusMenuItem);

        userProfileMenuItem = new JMenuItem("USER PROFILE");
        airlineSystemMenu.add(userProfileMenuItem);

        cancellationMenuItem = new JMenuItem("CANCELLATION");
        airlineSystemMenu.add(cancellationMenuItem);

        feedbackMenuItem = new JMenuItem("FEEDBACK");
        airlineSystemMenu.add(feedbackMenuItem);
    }

    public JMenu getAirlineSystemMenu() {
        return airlineSystemMenu;
    }

    public JMenuItem getBookTicketMenuItem() {
        return bookTicketMenuItem;
    }

    public JMenuItem getFlightStatusMenuItem() {
        return flightstatusMenuItem;
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

    public JMenuItem getHomeMenuItem() {
        return homeMenuItem;
    }

    public JMenuItem getListMenuItem() {
        return listMenuItem;
    }
}