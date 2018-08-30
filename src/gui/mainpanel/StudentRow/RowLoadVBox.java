package gui.mainpanel.StudentRow;


import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Sheet;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class RowLoadVBox extends VBox implements SoftDisablable{

    public RowLoadVBox(){}

    List<EventHandler<MouseEvent>> handlers;
    public RowLoadVBox(List<Student> studentRows){
        super();
        double third = studentRows.size()/3.0;
        if(third<2){
            for(Student student:studentRows){
                this.getChildren().add(new StudentRow(student));
            }
        }else{
            third = Math.ceil(third);
            handlers = new ArrayList<>();
            for(int i = 0; i<3;i++){
                Integer index = new Integer(i);
                RowLoadVBox box = new RowLoadVBox(studentRows.subList(i*(int)third,(i<2 ? (i+1)*(int)third:studentRows.size())));

                handlers.add(e->{

                    box.softEnable();
                    for(int j = 0; j<3; j++){
                        if(j==index){
                            continue;
                        }
                        ((RowLoadVBox)this.getChildren().get(j)).softDisable();
                    }
                });
                box.setOnMouseEntered(handlers.get(i));
                this.getChildren().add(box);
            }

        }
    }

    @Override
    public void softDisable() {
        for (Node node : this.getChildren()) {
            if(handlers!=null) {
                node.setOnMouseEntered(null);
            }
            ((SoftDisablable)node).softDisable();
        }
    }

    @Override
    public void softEnable() {
        for (int i = 0;i<this.getChildren().size();i++) {
            SoftDisablable node = (SoftDisablable)this.getChildren().get(i);
            if(handlers!=null){
                ((RowLoadVBox)node).setOnMouseEntered(handlers.get(i));
            }
            node.softEnable();
        }
    }
}
