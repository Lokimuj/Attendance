package gui.mainpanel.DayRow;


import gui.mainpanel.MainPanel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Day;

public class DayTile extends VBox {

    Day day;

    public DayTile(Day day){
        super();
        this.day = day;
        this.setHeight(DayRow.DAY_ROW_HEIGHT);
        this.setWidth(MainPanel.DETAIL_CELL_WIDTH);
        Label label = new Label(day.getDate());
        Button pairs = new Button("pairs");
        Button report = new Button("report");
        pairs.setMaxWidth(MainPanel.DETAIL_CELL_WIDTH/2.0);
        report.setMaxWidth(MainPanel.DETAIL_CELL_WIDTH/2.0);
        //TODO make buttons do the things
        this.getChildren().addAll(label,new HBox(pairs,report));
    }

}
