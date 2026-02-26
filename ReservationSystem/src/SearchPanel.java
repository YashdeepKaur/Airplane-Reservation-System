import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {
    private JTextField departureCityField;
    private JTextField arrivalCityField;
    private JButton searchButton;
    private JTextArea resultsArea;

    public SearchPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel departureCityLabel = new JLabel("Departure City:");
        JLabel arrivalCityLabel = new JLabel("Arrival City:");

        departureCityField = new JTextField();
        arrivalCityField = new JTextField();
        searchButton = new JButton("Search");

        inputPanel.add(departureCityLabel);
        inputPanel.add(departureCityField);
        inputPanel.add(arrivalCityLabel);
        inputPanel.add(arrivalCityField);
        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.NORTH);

        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextField getDepartureCityField() {
        return departureCityField;
    }

    public JTextField getArrivalCityField() {
        return arrivalCityField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void updateResults(String results) {
        resultsArea.setText(results);
    }
}
