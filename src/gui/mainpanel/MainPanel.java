package gui.mainpanel;

import gui.MainWindow;
import gui.mainpanel.DayRow.DayRow;
import gui.mainpanel.StudentRow.RowLoadVBox;
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
    SignatureLabel signature = new SignatureLabel();

    public MainPanel(Sheet sheet, MainWindow mainWindow){
        super();
        this.sheet = sheet;
        studentsScroller = new ScrollPane();
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
        studentsScroller.setContent(new RowLoadVBox(sheet.getRoster().getStudents()));
        this.getChildren().addAll(new DayRow(sheet),studentsScroller,signature);
    }

}
