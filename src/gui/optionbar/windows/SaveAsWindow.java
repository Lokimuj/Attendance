package gui.optionbar.windows;

import gui.optionbar.buttons.SaveAsButton;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Sheet;

public class SaveAsWindow extends Stage {

    SaveAsButton button;
    boolean isFromButton;
    Sheet sheet;
    SaveAsWindow self;


    public SaveAsWindow(SaveAsButton button, Sheet sheet){
        super();
        this.button = button;
        isFromButton = true;
        this.sheet = sheet;
        self = this;
        setup();
    }

    public SaveAsWindow(Sheet sheet){
        super();
        isFromButton = false;
        this.sheet = sheet;
        self = this;
        setup();
    }

    public void setup(){
        VBox vBox = new VBox();
        TextField text = new TextField();
        Button save = new Button("Save");
        Label alerts = new Label("");

        save.setOnAction(e->{
            if(!text.getText().equals("")){
                sheet.save(text.getText());
                close();
            }else{
                Thread error = new Thread(()->{
                    Platform.runLater(()->{
                        alerts.setText("Filename must not be empty!");
                        alerts.setStyle("-fx-background-color: #FF0000;");
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    Platform.runLater(()->{
                        alerts.setStyle(null);
                    });
                });
                error.start();
            }
        }
        );
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e->self.close());

        HBox buttons = new HBox(save,cancel);

        vBox.getChildren().addAll(text,alerts,buttons);
        this.setScene(new Scene(vBox));
    }


    @Override
    public void close() {
        super.close();
        if(isFromButton){
            button.windowClosed();
        }
    }
}
