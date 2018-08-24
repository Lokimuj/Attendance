package gui;

import gui.mainpanel.MainPanel;
import gui.optionbar.OptionBar;
import javafx.scene.layout.BorderPane;
import model.DayCalendar;
import model.Sheet;
import model.StudentRoster;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class MainWindow extends BorderPane {


    public MainWindow(Sheet sheet){
        super();
        OptionBar bar = new OptionBar(sheet);
        MainPanel mainPanel = new MainPanel(sheet);
        this.setTop(bar);
        this.setCenter(mainPanel);
    }

}
