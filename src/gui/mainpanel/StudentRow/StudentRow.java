package gui.mainpanel.StudentRow;

import gui.Attendance;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import model.AttendanceDetail;
import model.Sheet;
import model.Student;
import model.StudentRoster;

import java.util.List;

public class StudentRow extends HBox {

    public static final String HIGHLIGHT_STYLE = "-fx-background-color: #DDDDFF;";

    public StudentRow(Student student) {
        super();
        List<Node> children = this.getChildren();
        children.add(new StudentTile(student));
        for(AttendanceDetail detail: student.getDetails()){
            children.add(new DetailTile(detail));
        }
        this.setOnMouseEntered(e->{
            Platform.runLater(()->this.setStyle(HIGHLIGHT_STYLE));
        });
        this.setOnMouseExited(e->Platform.runLater(()->{
            this.setStyle(null);
        }));

    }
}
