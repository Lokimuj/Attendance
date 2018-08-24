package gui.optionbar.buttons;

import gui.optionbar.windows.SaveAsWindow;
import javafx.scene.control.Button;
import model.Sheet;

public class SaveAsButton extends Button {

    boolean isWindowOpen = false;
    SaveAsWindow window;

    public SaveAsButton(Sheet sheet){
        super("SAVE AS...");
        window = new SaveAsWindow(this, sheet);

        this.setOnAction(e->{
            if(!window.isShowing()){
                window.setup();
                window.show();
            }
        });
    }

    public void windowClosed(){
        this.isWindowOpen = false;
    }
}
