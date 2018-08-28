package gui.optionbar.buttons;


import gui.optionbar.windows.AddStudentsWindow;
import javafx.scene.control.Button;
import model.Sheet;

public class AddStudentsButton extends Button {

    AddStudentsWindow window;

    public AddStudentsButton(Sheet sheet){
        super("Add Students");
        window = new AddStudentsWindow(sheet);
        this.setOnAction(e->{
            if(!window.isShowing()){
                window.show();
            }
        });
    }
}
