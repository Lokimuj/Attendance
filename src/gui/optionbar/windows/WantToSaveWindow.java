package gui.optionbar.windows;

import gui.optionbar.buttons.SaveAsButton;
import gui.optionbar.buttons.SaveButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Sheet;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class WantToSaveWindow extends Stage {

    private final Sheet sheet;
    private final Done done;

    public WantToSaveWindow(Sheet sheet, Done done){
        super();
        this.sheet = sheet;
        this.done = done;
        Label message = new Label("Would you like to save before you load a new file?");
        SaveButton save = new SaveButton(sheet);
        SaveAsButton saveAs = new SaveAsButton(sheet);
        Button nah = new Button("nah");
        nah.setOnAction(e->{
            this.close();
        });
        this.setScene(new Scene(new VBox(message,new HBox(save,saveAs,nah))));
    }

    @Override
    public void hide() {
        super.hide();
        done.finished();
    }

    @Override
    public void close() {
        super.close();
    }

    public interface Done{
        void finished();
    }
}
