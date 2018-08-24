package gui.mainpanel;

import gui.mainpanel.DayRow.DayRow;
import gui.mainpanel.StudentRow.StudentRow;
import gui.mainpanel.StudentRow.StudentTile;
import javafx.scene.layout.VBox;
import model.Sheet;

public class MainPanel extends VBox {
    public static final int LEFT_COLUMN_WIDTH = 120;
    public static final int STUDENT_ROW_HEIGHT = 50;
    public static final int DETAIL_CELL_WIDTH = 120;

    Sheet sheet;

    public MainPanel(Sheet sheet){
        super();
        this.sheet = sheet;
        sheet.subscribe(this::refresh);
        refresh();
    }

    public void refresh(){
        this.getChildren().clear();
        this.getChildren().addAll(new DayRow(sheet));
        for(var student:sheet.getRoster().getStudents()){
            this.getChildren().add(new StudentRow(student));
        }
    }

}
