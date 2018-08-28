package gui;

import gui.mainpanel.MainPanel;
import gui.optionbar.OptionBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DayCalendar;
import model.Sheet;
import model.StudentRoster;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class MainWindow extends BorderPane {

    Stage stage;

    public MainWindow(Sheet sheet, Stage stage){
        super();
        this.stage = stage;
        OptionBar bar = new OptionBar(sheet);
        MainPanel mainPanel = new MainPanel(sheet,this);
        this.setTop(bar);
        this.setCenter(mainPanel);
    }

    public void refresh(){
        stage.hide();
        stage.show();
    }

}
