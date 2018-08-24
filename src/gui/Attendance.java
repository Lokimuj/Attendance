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


public class Attendance extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Sheet sheet = new Sheet();
        sheet.setup("files/readme.txt");
        StudentRoster roster = sheet.getRoster();
        Button add = new Button("add");
        add.setOnAction(e->{
            roster.addStudent(new Student("name","name","id",sheet.getDays(),roster));
        });
        OptionBar bar = new OptionBar(sheet);
        bar.getChildren().add(add);
        primaryStage.setScene(new Scene(new VBox(bar,new MainPanel(sheet))));
        primaryStage.show();
    }
}
