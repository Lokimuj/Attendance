package gui;

import gui.optionbar.windows.SaveAsWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.Sheet;

public class Attendance extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Sheet sheet = new Sheet();
        sheet.setup("files/readme.txt");
        SaveAsWindow save = new SaveAsWindow(sheet);
        save.setup();
        save.show();
    }
}
