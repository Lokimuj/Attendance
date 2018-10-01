package gui.optionbar.buttons;

import gui.optionbar.OptionBar;
import gui.optionbar.windows.LoadWindow;
import gui.optionbar.windows.WantToSaveWindow;
import javafx.scene.control.Button;
import model.Sheet;


/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class LoadButton extends Button {

    LoadWindow window;
    WantToSaveWindow wannaSave;

    public LoadButton(Sheet sheet){
        super("LOAD");
        window = new LoadWindow(sheet);
        wannaSave = new WantToSaveWindow(sheet,()->{
            window.show();
        });
        this.setHeight(OptionBar.BUTTON_HEIGHT);
        this.setOnAction(e->{
            if(!wannaSave.isShowing() && !window.isShowing()){
                if(sheet.isLoaded()) {
                    wannaSave.show();
                }else{
                    window.show();
                }
            }

        });
    }
}
