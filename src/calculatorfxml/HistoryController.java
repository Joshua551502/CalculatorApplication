package calculatorfxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 
 * The controller class for the history.fxml file.
 * @author Joshua
 * @author Roopkira
 * @version 1.0
 * @since 2023-04-02
 */
public class HistoryController implements Initializable {

    @FXML
    private Label historyLabel;

    /**
     * 
     * The initialize method of the controller.
     * 
     * @param location  The URL location of the FXML file.
     * @param resources The ResourceBundle containing any resources needed by the
     *                  FXML file.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshHistory();
    }

    /**
     * 
     * Refresh the historyLabel text.
     */
    private void refreshHistory() {
        // Load the contents of the "history.txt" file
        StringBuilder fileContents = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File("../history.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String formattedLine = String.format("%-10s %-1s %-10s = %-10s", parts[0], parts[1], parts[2],
                        parts[4]);
                fileContents.append(formattedLine).append("\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            fileContents.append("No History!");
        }

        // Set the font to a fixed width font
        historyLabel.setFont(Font.font("Courier New", 18));

        // Set the text of the historyLabel to the formatted file contents
        historyLabel.setText(fileContents.toString());
    }

    /**
     * 
     * Handle the clear history button click event.
     * 
     * @param event The ActionEvent triggered by the clear history button click.
     */
    @FXML
    void onClearHistoryClicked(ActionEvent event) {
        try {
            // Clear the contents of the history.txt file
            FileWriter writer = new FileWriter("../history.txt", false);
            writer.write("");
            writer.close();

            // Update the historyLabel text
            historyLabel.setText("No History!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Handle the refresh button click event.
     * 
     * @param event The ActionEvent triggered by the refresh button click.
     */
    @FXML
    void onRefreshClicked(ActionEvent event) {
        refreshHistory();
    }
}