package gui.optionbar.buttons;

import gui.optionbar.OptionBar;
import javafx.scene.control.Button;
import model.Sheet;


public class SaveButton extends Button {

    public SaveButton(Sheet sheet){
        super("SAVE");
        setOnAction(e->sheet.save());
        this.setHeight(OptionBar.BUTTON_HEIGHT);
    }
}
