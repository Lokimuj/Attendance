package gui;

import gui.mainpanel.MainPanel;
import gui.optionbar.OptionBar;
import gui.optionbar.windows.SaveAsWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Sheet;
import model.Student;
import model.StudentRoster;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class Attendance extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Sheet sheet = new Sheet();
        primaryStage.setScene(new Scene(new MainWindow(sheet,primaryStage)));
        primaryStage.show();
    }
}
