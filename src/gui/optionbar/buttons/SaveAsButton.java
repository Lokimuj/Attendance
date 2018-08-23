package gui.optionbar.buttons;

import gui.optionbar.windows.SaveAsWindow;
import javafx.scene.control.Button;
import model.Sheet;

public class SaveAsButton extends Button {

    boolean isWindowOpen = false;

    public SaveAsButton(Sheet sheet){
        super("SAVE AS...");
        this.setOnAction(e->{
            if(!isWindowOpen){
                isWindowOpen = true;
                new SaveAsWindow(this, sheet).show();
            }
        });
    }

    public void windowClosed(){
        this.isWindowOpen = false;
    }
}
