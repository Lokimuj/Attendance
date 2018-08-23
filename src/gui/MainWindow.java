package gui;

import javafx.scene.layout.BorderPane;
import model.DayCalendar;
import model.Sheet;
import model.StudentRoster;

public class MainWindow extends BorderPane {

    Sheet sheet;
    StudentRoster roster;
    DayCalendar days;
    String currentFile;

    public MainWindow(Sheet sheet){
        super();
    }

}
