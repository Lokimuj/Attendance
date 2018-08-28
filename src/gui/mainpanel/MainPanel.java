package gui.mainpanel;

import gui.MainWindow;
import gui.mainpanel.DayRow.DayRow;
import gui.mainpanel.StudentRow.StudentRow;
import gui.mainpanel.StudentRow.StudentTile;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.Sheet;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class MainPanel extends VBox {
    public static final int LEFT_COLUMN_WIDTH = 120;
    public static final int STUDENT_ROW_HEIGHT = 50;
    public static final int DETAIL_CELL_WIDTH = 120;

    Sheet sheet;
    ScrollPane studentsScroller;
    VBox students;

    public MainPanel(Sheet sheet, MainWindow mainWindow){
        super();
        this.sheet = sheet;
        students = new VBox();
        studentsScroller = new ScrollPane(students);
        studentsScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        studentsScroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.setMaxWidth(mainWindow.getWidth());
        this.setMaxHeight(800);
        sheet.subscribe(()->{
            this.refresh();
            mainWindow.refresh();
        });
    }

    public void refresh(){
        this.getChildren().clear();
        students.getChildren().clear();
        this.getChildren().add(new DayRow(sheet));
        for(var student:sheet.getRoster().getStudents()){
            students.getChildren().add(new StudentRow(student));
        }
        this.getChildren().add(studentsScroller);
    }

}
