package gui.optionbar.windows;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewFileWindow extends Stage {

    HBox csvOutput = new HBox();
    TextField dateInput = new TextField();
    Label error = new Label("");
    Sheet sheet;

    public NewFileWindow(Sheet sheet){
        super();
        this.sheet = sheet;
        Label dateLine = new Label("Current recitations:");
        csvOutput.setSpacing(4);
        HBox topBar = new HBox(dateLine,csvOutput);
        Label date = new Label("Enter next recitation name:");
        Button submit = new Button("submit");

        submit.setOnAction(e->{
            if(dateInput.getText().contains(",") || dateInput.getText().contains(Sheet.COMMENT_LINE_CHAR)){
                error.setText("Names must not contain ',' or '#'");
            }else{
                error.setText("");
                csvOutput.getChildren().add(new DateLabel(dateInput.getText(),csvOutput));
                dateInput.setText("");
            }
        });

        Button clear = new Button("clear");

        clear.setOnAction(e-> this.clear());
        HBox manual = new HBox(date,dateInput,submit,clear);

        Button finish = new Button("Finish");

        finish.setOnAction(e->{
            if(csvOutput.getChildren().size()>0) {
                sheet.setup(new Scanner(buildDateLine()));
                this.close();
            }else{
                error.setText("Must have at least one recitation");
            }
        });

        Button cs1Default = new Button("CS1 Default");
        cs1Default.setOnAction(e->setToCS1Default());
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e->close());
        HBox bottomBar = new HBox(finish,cs1Default,cancel);

        this.setScene(new Scene(new VBox(topBar,manual,error,bottomBar)));

    }


    void setToCS1Default(){
        csvOutput.getChildren().clear();
        for(int i = 1; i<15;i++){
            if(i==7 || i==12) continue;
            csvOutput.getChildren().add(new DateLabel(""+i,csvOutput));
        }
    }

    String buildDateLine(){
        List<Node> labels = csvOutput.getChildren();
        StringBuilder output = new StringBuilder(((Label) labels.get(0)).getText());
        for(int i = 1; i<labels.size();i++){
            output.append(",").append(((Label) labels.get(i)).getText());
        }
        output.append("\n");
        return output.toString();
    }

    void clear(){
        csvOutput.getChildren().clear();
        dateInput.setText("");
        error.setText("");
    }

    @Override
    public void hide() {
        super.hide();
        clear();
    }

    class DateLabel extends Label{
        public DateLabel(String text,HBox parent){
            super(text);
            this.setStyle("-fx-background-color: #FFCCCC");
            this.setOnMouseClicked(e->{
                parent.getChildren().remove(this);
            });
        }
    }
}
