package gui.mainpanel.StudentRow;

import gui.mainpanel.MainPanel;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Student;

public class StudentTile extends VBox {

    public StudentTile(Student student) {
        super();
        this.setHeight(MainPanel.STUDENT_ROW_HEIGHT);
        this.setMinWidth(MainPanel.LEFT_COLUMN_WIDTH);
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Label id = new Label(student.getId()+"");
        String nameString = student.getLastName() + ", "+student.getFirstName();
        if(nameString.length()>18){
            nameString = nameString.substring(0,16)+"...";
        }
        Label name = new Label(nameString);
        Label ritid = new Label(student.getRitID());
        this.getChildren().addAll(id,name,ritid);
    }
}
