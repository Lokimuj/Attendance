package gui.optionbar;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.Sheet;

public class OptionBar extends HBox {

    public static final int BUTTON_HEIGHT = 14;

    public OptionBar(Sheet sheet){
        super();
        Button save = new Button("SAVE");
        save.setOnAction(e->sheet.save());
    }
}
