package gui.optionbar.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Sheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddStudentsWindow extends Stage {


    Sheet sheet;
    public AddStudentsWindow(Sheet sheet){
        super();
        this.sheet = sheet;

        Label errorManual = new Label("");

        Label firstNameLabel = new Label("First name: ");
        TextField firstName = new TextField();
        HBox firstNameBox = new HBox(firstNameLabel,firstName);

        Label lastNameLabel = new Label("Last name: ");
        TextField lastName = new TextField();
        HBox lastNameBox = new HBox(lastNameLabel,lastName);

        Label ritIDLabel = new Label("RIT ID: ");
        TextField ritID = new TextField();
        HBox ritIDBox = new HBox(ritIDLabel,ritID);

        Button enroll = new Button("Enroll");
        enroll.setOnAction(e->{
            if(firstName.getText().equals("") || lastName.getText().equals("") || ritID.getText().equals("")){
                errorManual.setText("No fields may be empty");
            }else if((firstName.getText()+lastName.getText()+ritID.getText()).contains(",") ||
                    (firstName.getText()+lastName.getText()+ritID.getText()).contains(Sheet.COMMENT_LINE_CHAR)){
                errorManual.setText("Fields must not contain ','  or '#'");
            }else{
                sheet.enrollNewStudent(firstName.getText(),lastName.getText(),ritID.getText(),true);
                firstName.setText("");
                lastName.setText("");
                ritID.setText("");
            }
        });

        Label or = new Label("OR");

        Label filenameLabel = new Label("Mycourses file name: ");
        TextField filename = new TextField();
        HBox filenameBox = new HBox(filenameLabel,filename);
        Label readerError = new Label("");
        Button readStudents = new Button("Read Students");
        readStudents.setOnAction(e->{
            try {
                Scanner file = new Scanner(new File(filename.getText()));
                addFromMycoursesFile(file);
                this.close();
            } catch (FileNotFoundException e1) {
                readerError.setText("File not found");
            }
        });
        Button done = new Button("Done");
        done.setOnAction(e-> this.close());

        HBox buttons = new HBox(readStudents,done);

        this.setScene(new Scene(new VBox(firstNameBox,lastNameBox,ritIDBox,errorManual,
                enroll,or,filenameBox,readerError,buttons)));
    }

    public void addFromMycoursesFile(Scanner file){
        file.nextLine();
        while(file.hasNext()) {
            String line = file.nextLine();
            if (line.startsWith("#")) {
                line = line.replaceAll("#","");
                String[] split = line.split(",");
                sheet.enrollNewStudent(split[2], split[1], split[0],false);
            }
        }
        sheet.refresh();
    }

}
