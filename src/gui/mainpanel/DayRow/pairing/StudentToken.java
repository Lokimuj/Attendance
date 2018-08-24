package gui.mainpanel.DayRow.pairing;

import gui.Attendance;
import gui.mainpanel.StudentRow.DetailTile;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.AttendanceDetail;
import model.Student;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class StudentToken extends VBox {

    public static final int TOKEN_WIDTH = 100;
    public static final int TOKEN_HEIGHT = 70;

    Student student;
    PairWindow window;
    boolean enabled = true;

    Border normal = new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

    Border selected = new Border(new BorderStroke(Color.RED,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

    public StudentToken(PairWindow window, Student student, AttendanceDetail detail) {
        super();
        this.student = student;
        this.window = window;
        this.setMinWidth(TOKEN_WIDTH);
        this.setHeight(TOKEN_HEIGHT);
        this.setBorder(normal);
        this.setPadding(new Insets(7));
        switch (detail.getType()){
            case NO_COMPUTER:
                this.setStyle(DetailTile.NO_COMP_STYLE);
                break;
            case COMPUTER:
                this.setStyle(DetailTile.COMP_STYLE);
        }

        ToggleGroup attendance = new ToggleGroup();

        Label firstName = new Label(student.getFirstName());
        Label lastName = new Label(student.getLastName());
        Label ritID = new Label(student.getRitID());
        this.setOnMouseClicked(e->{
            if(enabled) window.studentClicked(student,this);
        });
        this.getChildren().addAll(firstName,lastName,ritID);
    }

    public Student getStudent(){
        return student;
    }

    public void select(){
        this.setBorder(selected);
    }

    public void unselect(){
        this.setBorder(normal);
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

}
