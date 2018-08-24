package gui.mainpanel.DayRow;

import gui.mainpanel.MainPanel;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Day;
import model.Sheet;

import java.util.ArrayList;
import java.util.List;

public class DayRow extends HBox {
    public static final int DAY_ROW_HEIGHT = 50;

    public DayRow(Sheet sheet){
        super();
        this.setHeight(DAY_ROW_HEIGHT);
        Pane empty = new Pane();
        empty.setPrefHeight(DAY_ROW_HEIGHT);
        empty.setMaxWidth(MainPanel.LEFT_COLUMN_WIDTH);
        empty.setMinWidth(MainPanel.LEFT_COLUMN_WIDTH);
        empty.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        List<Node> children = this.getChildren();
        children.add(empty);
        for(Day day:sheet.getDays().getDays()){
            children.add(new DayTile(day));
        }
    }
}
