package calculatorfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main class of the calculator application.
 *
 * @author Joshua
 * @author Roopkira
 * @version 1.0
 * @since 2023-04-02
 */
public class CalculatorApplication extends Application {

    /**
     * The main entry point for the calculator application.
     *
     * @param args The command line arguments.
     * @throws Exception If an exception occurs.
     * @since 1.0
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /**
     * Called by the JavaFX runtime when the application should start.
     *
     * @param primaryStage The primary stage for this application.
     * @throws Exception If an exception occurs.
     * @since 1.0
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));

        // Set the title of the stage
        primaryStage.setTitle("Calculator");

        // Set the scene fill color to black
        Scene scene = new Scene(root, 460, 725);

        // Load the image from the classpath
        Image image = new Image(getClass().getResourceAsStream("image.png"));

        // Set the stage properties
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(image);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
