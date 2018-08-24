package gui.optionbar.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Sheet;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class LoadWindow extends Stage {

    public LoadWindow(Sheet sheet){
        super();
        TextField filename = new TextField();
        Label error = new Label("");
        Button load = new Button("Load");
        Button cancel = new Button("Cancel");
        load.setOnAction(e->{
            if(filename.getText().equals("")){
                error.setText("filename must not be empty");
                return;
            }
            if(!sheet.setup(filename.getText())){
                error.setText("File not found");
            }else{
                this.close();
            }
        });

        cancel.setOnAction(e->{
            this.close();
        });
        this.setScene(new Scene(new VBox(filename,error,new HBox(load,cancel))));
    }
}
