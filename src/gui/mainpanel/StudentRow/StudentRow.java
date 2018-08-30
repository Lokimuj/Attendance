package gui.mainpanel.StudentRow;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.AttendanceDetail;
import model.Student;

import java.util.List;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class StudentRow extends HBox implements SoftDisablable{

    public static final String HIGHLIGHT_STYLE = "-fx-background-color: #DDDDFF;";

    EventHandler<MouseEvent> mouseEnter;
    EventHandler<MouseEvent> mouseExit;



    public StudentRow(Student student) {
        super();
        List<Node> children = this.getChildren();
        children.add(new StudentTile(student));
        for(AttendanceDetail detail: student.getDetails()){
            children.add(new DetailTile(detail));
        }
        this.mouseEnter = e->{
            Platform.runLater(()->this.setStyle(HIGHLIGHT_STYLE));
        };
        this.setOnMouseEntered(mouseEnter);
        this.mouseExit = e->Platform.runLater(()->{
            this.setStyle(null);
        });
        this.setOnMouseExited(mouseExit);

    }

    @Override
    public void softDisable() {
        this.setOnMouseEntered(null);
        this.setOnMouseExited(null);
    }

    @Override
    public void softEnable() {
        this.setOnMouseEntered(mouseEnter);
        this.setOnMouseExited(mouseExit);
    }
}
