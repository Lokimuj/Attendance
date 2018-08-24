package gui.optionbar;

import gui.optionbar.buttons.LoadButton;
import gui.optionbar.buttons.SaveAsButton;
import gui.optionbar.buttons.SaveButton;
import gui.optionbar.windows.LoadWindow;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.Sheet;

public class OptionBar extends HBox {

    public static final int BUTTON_HEIGHT = 14;

    public OptionBar(Sheet sheet){
        super();
        SaveButton save = new SaveButton(sheet);
        SaveAsButton saveAs = new SaveAsButton(sheet);
        LoadButton load = new LoadButton(sheet);
        this.getChildren().addAll(save,saveAs,load);
    }
}
