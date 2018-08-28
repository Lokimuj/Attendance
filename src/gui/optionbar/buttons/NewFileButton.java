package gui.optionbar.buttons;

import gui.optionbar.OptionBar;
import gui.optionbar.windows.NewFileWindow;
import gui.optionbar.windows.WantToSaveWindow;
import javafx.scene.control.Button;
import model.Sheet;

public class NewFileButton extends Button {

    NewFileWindow window;
    WantToSaveWindow wannaSave;

    public NewFileButton(Sheet sheet){
        super("New File");
        window = new NewFileWindow(sheet);
        wannaSave = new WantToSaveWindow(sheet,window::show);
        this.setHeight(OptionBar.BUTTON_HEIGHT);

        this.setOnAction(e->{
            if(!wannaSave.isShowing() && !window.isShowing()){
                wannaSave.show();
            }
        });
    }
}
