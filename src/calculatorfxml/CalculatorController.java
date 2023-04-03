package calculatorfxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The CalculatorController class controls the behavior of the calculator UI and
 * implements the necessary methods to perform calculations and display the
 * results.
 * <p>
 * This class is responsible for handling user input events, updating the
 * display with the current calculation, and saving the calculation history to a
 * file.
 * </p>
 * <p>
 * The CalculatorController class uses various JavaFX UI components, including
 * the Text, Label, Pane, and StackPane classes, to implement the calculator UI.
 * </p>
 * <p>
 * The calculator supports basic arithmetic operations, including addition,
 * subtraction, multiplication, and division. It also supports decimal inputs,
 * negative numbers, and percentage calculations.
 * </p>
 * <p>
 * The CalculatorController class uses a Map data structure to store the
 * calculation history and provides methods to add, delete, and load the history
 * from a file.
 * </p>
 *
 * @author Joshua
 * @author Roopkira
 * @version 1.0
 * @since 2023-04-02
 */
public class CalculatorController implements Initializable {

    /**
     * The text object that displays the current action
     */
    @FXML
    private Text actiontarget;

    /**
     * The label object that displays the current number
     */
    @FXML
    private Label mainLabel;

    /**
     * The first operand of the current calculation
     */
    private double num1 = 0;

    /**
     * The current operator used in the calculation
     */
    private String operator = "+";

    /**
     * The number of decimal places in the current input
     */
    private int decimalPlaces = 1;

    /**
     * The ID of the current calculation in the history
     */
    private int historyId = 1;

    /**
     * The map that stores the calculation history
     */
    private Map<Integer, String> historyMap = new HashMap<>();

    /**
     * The current number being entered by the user
     */
    private StringBuilder currentNumber = new StringBuilder();

    /**
     * Handles mouse entered events for the buttons.
     *
     * @param event The mouse event.
     */
    @FXML
    private void handleMouseEnteredA(MouseEvent event) {
        StackPane stackPane = (StackPane) event.getSource();
        stackPane.setStyle("-fx-background-color: #404040;");
    }

    /**
     * Handles mouse exited events for the buttons.
     *
     * @param event The mouse event.
     */
    @FXML
    private void handleMouseExitedA(MouseEvent event) {
        StackPane stackPane = (StackPane) event.getSource();
        stackPane.setStyle("-fx-background-color: #343434; -fx-background-radius: 5 5 5 5; -fx-spacing: 2;");

    }

    /**
     * Handles mouse entered events for the buttons.
     *
     * @param event The mouse event.
     */
    @FXML
    private void handleMouseEntered(MouseEvent event) {
        StackPane stackPane = (StackPane) event.getSource();
        stackPane.setStyle("-fx-background-color: #343434;");
    }

    /**
     * Handles mouse exited events for the buttons.
     *
     * @param event The mouse event.
     */
    @FXML
    private void handleMouseExited(MouseEvent event) {
        StackPane stackPane = (StackPane) event.getSource();
        stackPane.setStyle("-fx-background-color: #404040; -fx-background-radius: 5 5 5 5; -fx-spacing: 2;");
    }

    /**
     * Handles mouse entered events for the buttons.
     *
     * @param event The mouse event.
     */
    @FXML
    private void handleMouseEnteredRed(MouseEvent event) {
        StackPane stackPane = (StackPane) event.getSource();
        stackPane.setStyle("-fx-background-color: #960101;");
    }

    /**
     * Handles mouse exited events for the buttons.
     *
     * @param event The mouse event.
     */
    @FXML
    private void handleMouseExitedRed(MouseEvent event) {
        StackPane stackPane = (StackPane) event.getSource();
        stackPane.setStyle("-fx-background-color: #8B0000; -fx-background-radius: 5 5 5 5; -fx-spacing: 2;");
    }

    /**
     * Handles mouse click events for the clear button.
     *
     * @param event The mouse event.
     */
    @FXML
    private void handleClearClick(MouseEvent event) {
        mainLabel.setText("0");
        currentNumber.setLength(0); // Reset the current number
        decimalPlaces = 1; // Reset the decimal places
    }

    /**
     * Handles mouse click events for the number buttons.
     *
     * @param event The mouse event.
     */
    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane) event.getSource()).getId().replace("btn", ""));
        if (value == 0 && currentNumber.length() == 0) {
            return; // Ignore leading zeros
        }
        if (!currentNumber.toString().contains(".") || value != 0) {
            currentNumber.append(value);
        } else {
            double decimalValue = value / Math.pow(10, decimalPlaces++);
            currentNumber.append(decimalValue == 0 ? "0" : String.valueOf(decimalValue).substring(1));
        }
        mainLabel.setText(currentNumber.toString());
    }

    /**
     * Initializes the controller class.
     *
     * @param location  The location used to resolve relative paths for the root
     *                  object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadHistory();
    }

    /**
     * Handles mouse click events for the symbol buttons.
     *
     * @param event The mouse event.
     */
    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane) event.getSource()).getId().replace("btn", "");
        if (symbol.equals("Equals")) {
            double num2 = Double.parseDouble(mainLabel.getText());
            double result = 0;
            switch (operator) {
                case "+" -> {
                    result = num1 + num2;
                    saveCalculation(num1, operator, num2, result);
                }
                case "-" -> {
                    result = num1 - num2;
                    saveCalculation(num1, operator, num2, result);
                }
                case "*" -> {
                    result = num1 * num2;
                    saveCalculation(num1, operator, num2, result);
                }
                case "/" -> {
                    result = num1 / num2;
                    saveCalculation(num1, operator, num2, result);
                }
            }
            operator = ".";
            mainLabel.setText(String.valueOf(result));
            currentNumber.setLength(0); // Reset the current number
            decimalPlaces = 1; // Reset the decimal places
        } else if (symbol.equals("Clear")) {
            mainLabel.setText(String.valueOf(0));
            operator = ".";
            currentNumber.setLength(0); // Reset the current number
            decimalPlaces = 1; // Reset the decimal places
        } else {
            switch (symbol) {
                case "Plus" -> operator = "+";
                case "Minus" -> operator = "-";
                case "Multiply" -> operator = "*";
                case "Divide" -> operator = "/";
            }
            num1 = Double.parseDouble(mainLabel.getText());
            mainLabel.setText(String.valueOf(0));
            currentNumber.setLength(0); // Reset the current number
            decimalPlaces = 1; // Reset the decimal places
        }
    }

    /**
     * Saves the calculation to a file.
     *
     * @param num1     The first number.
     * @param operator The operator used for the calculation.
     * @param num2     The second number.
     * @param result   The result of the calculation.
     */
    private void saveCalculation(double num1, String operator, double num2, double result) {
        try {
            FileWriter writer = new FileWriter("../history.txt", true); // Change to append mode
            String calculation = num1 + " " + operator + " " + num2 + " = " + result + "\n";
            historyMap.put(historyId++, calculation);
            writer.append(calculation); // Append the new calculation
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles mouse click events for the decimal button.
     *
     * @param event The mouse event.
     */
    @FXML
    private void onDecimalClicked(MouseEvent event) {
        if (!currentNumber.toString().contains(".")) {
            if (currentNumber.length() == 0) {
                currentNumber.append("0");
            }
            currentNumber.append(".");
        }
    }

    /**
     * Loads the history from a file
     */
    private void loadHistory() {
        try {
            File historyFile = new File("history.txt");
            if (historyFile.exists()) {
                Scanner scanner = new Scanner(historyFile);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    historyMap.put(historyId++, line);
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles mouse click events for the negative button.
     *
     * @param event The mouse event.
     */
    @FXML
    void onNegativeClicked(MouseEvent event) {
        if (currentNumber.length() == 0) {
            return;
        }

        double currentValue = Double.parseDouble(currentNumber.toString());
        currentValue = -currentValue;
        currentNumber.setLength(0);
        currentNumber.append(String.valueOf(currentValue));
        mainLabel.setText(currentNumber.toString());
    }

    /**
     * Handles mouse click events for the percent button.
     *
     * @param event The mouse event.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void onPercentClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 460, 725);
        Stage stage = new Stage();
        Image image = new Image(getClass().getResourceAsStream("image.png"));
        stage.setResizable(false);
        stage.setTitle("History");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }
}
