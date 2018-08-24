package gui.optionbar.buttons;

import gui.optionbar.OptionBar;
import javafx.scene.control.Button;
import model.Sheet;


/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class SaveButton extends Button {

    public SaveButton(Sheet sheet){
        super("SAVE");
        setOnAction(e->sheet.save());
        this.setHeight(OptionBar.BUTTON_HEIGHT);
    }
}
