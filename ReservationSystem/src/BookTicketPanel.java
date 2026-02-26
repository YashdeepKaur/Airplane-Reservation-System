import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookTicketPanel extends JPanel {
    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Book> bookings = new ArrayList<>();
    private ArrayList<String> cart = new ArrayList<>();
    private JTextArea cartTextArea;
    private JPanel cartPanel;
    private double total = 0;

    public BookTicketPanel() {
        setName("Airline Reservation System");
        setSize(new Dimension(1000, 800));
        setLayout(new BorderLayout());

        // Add example flights to the system
        flights.add(new Flight("AI101", "New York", "London", "10:00 AM","02:00 PM", "$500", new String[]{"Chicken Meal", "Vegetarian Meal"}, "D:\\ReservationSystem\\.vscode\\flight1.png"));
        flights.add(new Flight("AI202", "London", "Tokyo", "02:00 PM","05:00 PM", "$800", new String[]{"Beef Meal", "Vegan Meal"}, "D:\\ReservationSystem\\.vscode\\flight2.png"));
        flights.add(new Flight("AI303", "San Francisco", "Sydney", "05:30 PM","09:00 PM", "$900", new String[]{"Seafood Meal", "Gluten-Free Meal"}, "D:\\ReservationSystem\\.vscode\\flight3.png"));
        flights.add(new Flight("AI404", "Paris", "Dubai", "08:45 PM","01:00 AM", "$600", new String[]{"Pasta Meal", "Kosher Meal"}, "D:\\ReservationSystem\\.vscode\\flight4.png"));
        flights.add(new Flight("AI505", "Berlin", "Moscow", "07:00 AM","12:00 PM", "$400", new String[]{"Chicken Meal", "Vegetarian Meal"}, "D:\\ReservationSystem\\.vscode\\flight4.png"));
        flights.add(new Flight("AI606", "Rome", "Mumbai", "11:15 AM","02:00 PM" ,"$750", new String[]{"Beef Meal", "Vegan Meal"}, "D:\\ReservationSystem\\.vscode\\flight1.png"));
        flights.add(new Flight("AI707", "Los Angeles", "Beijing", "03:30 PM","06:00 PM", "$950", new String[]{"Seafood Meal", "Gluten-Free Meal"}, "D:\\ReservationSystem\\.vscode\\flight2.png"));
        flights.add(new Flight("AI808", "Chicago", "Toronto", "06:45 PM","10:00 PM", "$350", new String[]{"Pasta Meal", "Kosher Meal"}, "D:\\ReservationSystem\\.vscode\\flight3.png"));
        flights.add(new Flight("AI909", "Madrid", "Istanbul", "09:00 PM","04:00 AM", "$550", new String[]{"Chicken Meal", "Vegetarian Meal"}, "D:\\ReservationSystem\\.vscode\\flight4.png"));
        flights.add(new Flight("AI1010", "Bangkok", "Sydney", "01:15 AM","06:30 PM", "$850", new String[]{"Beef Meal", "Vegan Meal"}, "D:\\ReservationSystem\\.vscode\\flight4.png"));

        // Main panel for flight panels
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        for (Flight flight : flights) {
            JPanel panel = panelPrep(flight);
            panel.setPreferredSize(new Dimension(300, 300));
            mainPanel.add(panel);
        }
        add(mainPanel, BorderLayout.CENTER);

        // Cart panel
        cartPanel = new JPanel(new BorderLayout());
        cartTextArea = new JTextArea(20, 20);
        cartTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartTextArea);
        cartPanel.add(new JLabel("Cart"), BorderLayout.NORTH);
        cartPanel.add(scrollPane, BorderLayout.CENTER);
        JButton billButton = new JButton("Bill");
        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBill();
            }
        });
        cartPanel.add(billButton, BorderLayout.SOUTH);
        add(cartPanel, BorderLayout.EAST);

        setVisible(true);
        
    }

    public static void main(String[] args) {
        new BookTicketPanel();
    }

    public JPanel panelPrep(Flight flight) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(300, 300));

        // Display flight image
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(flight.getImagePath());
        java.awt.Image image = imageIcon.getImage(); 
        java.awt.Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);  
        imageIcon = new ImageIcon(newimg);
        imageLabel.setIcon(imageIcon);
        panel.add(imageLabel);

        // Display flight details
        JLabel flightInfo = new JLabel("<html><b>Flight:</b> " + flight.getFlightNumber() + "<br><b>From:</b> " + flight.getDepartureCity() + "<br><b>To:</b> " + flight.getArrivalCity() + "<br><b>Departure:</b> " + flight.getDepartureTime() + "<br><b>Arrival:</b> "+ flight.getArrivalTime()+ "<br><b>Price:</b>" + flight.getPrice() + "</html>");

        // Meal options
        JPanel mealPanel = new JPanel(new GridLayout(0, 1));
        JLabel mealLabel = new JLabel("Select Meal:");
        mealPanel.add(mealLabel);
        for (String meal : flight.getMealOptions()) {
            JButton mealButton = new JButton(meal);
            mealButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cart.add(flight.getFlightNumber() + " - " + meal);
                    updateCart();
                    JOptionPane.showMessageDialog(null, meal + " added to cart for flight " + flight.getFlightNumber());
                }
            });
            mealPanel.add(mealButton);
        }

        // Button to book ticket
        JButton bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookTicket(flight);
            }
        });

        panel.add(flightInfo);
        panel.add(mealPanel);
        panel.add(bookButton);

        return panel;
    }

    private void bookTicket(Flight flight) {
        String bookingInfo = "Flight: " + flight.getFlightNumber() + ", From: " + flight.getDepartureCity() + ", To: " + flight.getArrivalCity() + ", Departure: " + flight.getDepartureTime() + ", Arrival: " + flight.getArrivalTime() + ", Price: " + flight.getPrice();
        bookings.add(new Book());
        cart.add(bookingInfo);
        updateCart();
        JOptionPane.showMessageDialog(null, "Ticket booked!\n" + bookingInfo);
    }

    private void updateCart() {
        cartTextArea.setText("");
        total = 0;
        for (String item : cart) {
            cartTextArea.append(item + "\n");
            if (item.contains("$")) {
                total += Double.parseDouble(item.split("\\$")[1]);
            }
        }
    }

    private void showBill() {
        // Create payment frame
        JFrame paymentFrame = new JFrame("Payment");
        paymentFrame.setSize(new Dimension(600, 400));
        paymentFrame.setLayout(new GridLayout(0, 1));

        // Total Expense
        paymentFrame.add(new JLabel("Total Expense: $" + total));

        // Payment Options
        JRadioButton debitCardOption = new JRadioButton("Debit Card");
        JRadioButton paypalOption = new JRadioButton("PayPal");
        JRadioButton paytmOption = new JRadioButton("Paytm");
        JRadioButton phonePeOption = new JRadioButton("PhonePe");
        ButtonGroup paymentOptions = new ButtonGroup();
        paymentOptions.add(debitCardOption);
        paymentOptions.add(paypalOption);
        paymentOptions.add(paytmOption);
        paymentOptions.add(phonePeOption);

        paymentFrame.add(debitCardOption);
        paymentFrame.add(paypalOption);
        paymentFrame.add(paytmOption);
        paymentFrame.add(phonePeOption);

        // Payment Details
        JLabel cardLabel = new JLabel("Name:");
        JTextField cardField = new JTextField(20);
        JLabel paypalEmailLabel = new JLabel("Email:");
        JTextField paypalEmailField = new JTextField(20);
        JLabel paytmNumberLabel = new JLabel("Address:");
        JTextField paytmNumberField = new JTextField(20);
        JLabel phonePeNumberLabel = new JLabel("Phone Number:");
        JTextField phonePeNumberField = new JTextField(20);

        JPanel paymentDetailsPanel = new JPanel(new GridLayout(0, 2));
        paymentDetailsPanel.add(cardLabel);
        paymentDetailsPanel.add(cardField);
        paymentDetailsPanel.add(paypalEmailLabel);
        paymentDetailsPanel.add(paypalEmailField);
        paymentDetailsPanel.add(paytmNumberLabel);
        paymentDetailsPanel.add(paytmNumberField);
        paymentDetailsPanel.add(phonePeNumberLabel);
        paymentDetailsPanel.add(phonePeNumberField);

        // Add Payment Details to frame
        paymentFrame.add(paymentDetailsPanel);

        // Passport Details
        JLabel passportLabel = new JLabel("Passport Number:");
        JTextField passportField = new JTextField(20);
        JLabel nationalityLabel = new JLabel("Nationality:");
        JTextField nationalityField = new JTextField(20);
        JLabel dobLabel = new JLabel("Date of Birth:");
        JTextField dobField = new JTextField(20);
        JLabel passportIssueLabel = new JLabel("Passport Issue Date:"); 
        JTextField passportIssueField = new JTextField(20);
        JLabel passportExpiryLabel = new JLabel("Passport Expiry Date:");
        JTextField passportExpiryField = new JTextField(20);
       
    


        JPanel passportPanel = new JPanel(new GridLayout(0, 2));
        passportPanel.add(passportLabel);
        passportPanel.add(passportField);
        passportPanel.add(nationalityLabel);
        passportPanel.add(nationalityField);
        passportPanel.add(dobLabel);
        passportPanel.add(dobField);
        passportPanel.add(passportIssueLabel);
        passportPanel.add(passportIssueField);
        passportPanel.add(passportExpiryLabel);
        passportPanel.add(passportExpiryField);


        // Add Passport Details to frame
        paymentFrame.add(passportPanel);

               // Pay Button
JButton payButton = new JButton("Pay");
payButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Payment Successful!");

        // Show Ticket
        JFrame ticketFrame = new JFrame("Ticket");
        ticketFrame.setSize(new Dimension(400, 600));
        JTextArea ticketTextArea = new JTextArea();
        ticketTextArea.setEditable(false);

        StringBuilder ticketInfo = new StringBuilder();
        ticketInfo.append("Ticket Information:\n");
        ticketInfo.append("Name: ").append(cardField.getText()).append("\n");
        ticketInfo.append("Email: ").append(paypalEmailField.getText()).append("\n");
        ticketInfo.append("Address: ").append(paytmNumberField.getText()).append("\n");
        ticketInfo.append("Phone Number: ").append(phonePeNumberField.getText()).append("\n");
        ticketInfo.append("Passport Number: ").append(passportField.getText()).append("\n");
        ticketInfo.append("Nationality: ").append(nationalityField.getText()).append("\n");
        ticketInfo.append("Date of Birth: ").append(dobField.getText()).append("\n");
        ticketInfo.append("Passport Issue Date: ").append(passportIssueField.getText()).append("\n");
        ticketInfo.append("Passport Expiry Date: ").append(passportExpiryField.getText()).append("\n");
        ticketInfo.append("Booking ID: ").append(bookings.size()).append("\n"); // Add booking ID
       

        for (String item : cart) {
            ticketInfo.append(item).append("\n");
        }
        ticketInfo.append("Total Paid: $").append(total);

        ticketTextArea.setText(ticketInfo.toString());
        ticketFrame.add(ticketTextArea);
        ticketFrame.setVisible(true);
    }
});
        paymentFrame.add(payButton);
        paymentFrame.setVisible(true);
    }

}
