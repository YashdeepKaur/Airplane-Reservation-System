import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FeedbackPanel extends JPanel {
    private JTextArea feedbackTextArea;
    private JButton submitButton;

    public FeedbackPanel() {
        setLayout(null);

        JLabel feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setBounds(50, 50, 80, 25);
        add(feedbackLabel);

        feedbackTextArea = new JTextArea();
        feedbackTextArea.setBounds(140, 50, 300, 150);
        add(feedbackTextArea);

        submitButton = new JButton("Submit Feedback");
        submitButton.setBounds(200, 220, 160, 25);
        add(submitButton);

        submitButton.addActionListener(e -> {
            String feedback = feedbackTextArea.getText();
            boolean submitted = submitFeedback(feedback);
            if (submitted) {
                feedbackSubmitted();
            } else {
                feedbackSubmissionFailed();
            }
        });
    }

    private boolean submitFeedback(String feedback) {
        // Here you can add your logic to submit the feedback without using a database connection
        // For example, you can send an email or write to a file
        // For demonstration purposes, let's assume the feedback is submitted successfully
        return true;
    }

    void feedbackSubmitted() {
        JOptionPane.showMessageDialog(null, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    void feedbackSubmissionFailed() {
        JOptionPane.showMessageDialog(null, "Failed to submit feedback. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}