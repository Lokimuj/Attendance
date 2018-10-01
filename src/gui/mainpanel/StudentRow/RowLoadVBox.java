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

    public static final float DIVISION_FACTOR = 2;
    public static final int MAX_GROUP_SIZE = 3;

    public RowLoadVBox(){}

    List<EventHandler<MouseEvent>> handlers;
    public RowLoadVBox(List<Student> studentRows){
        super();
         //Don't mind the var name
        if(studentRows.size()<MAX_GROUP_SIZE){
            for(Student student:studentRows){
                this.getChildren().add(new StudentRow(student));
            }
        }else{
            double third = studentRows.size()/DIVISION_FACTOR;
            third = Math.ceil(third);
            handlers = new ArrayList<>();
            for(int i = 0; i<DIVISION_FACTOR;i++){
                Integer index = new Integer(i);
                RowLoadVBox box = new RowLoadVBox(studentRows.subList(i*(int)third,(i<DIVISION_FACTOR-1 ? (i+1)*(int)third :
                                                                                    studentRows.size())));

                handlers.add(e->{

                    box.softEnable();
                    for(int j = 0; j<DIVISION_FACTOR; j++){
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
