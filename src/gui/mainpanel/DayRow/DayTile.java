package gui.mainpanel.DayRow;


import gui.mainpanel.DayRow.pairing.PairWindow;
import gui.mainpanel.MainPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Day;

public class DayTile extends VBox {

    Day day;

    public DayTile(Day day){
        super();
        this.day = day;
        this.setHeight(DayRow.DAY_ROW_HEIGHT);
        this.setMinWidth(MainPanel.DETAIL_CELL_WIDTH);
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setStyle("-fx-background-color: #AAAAFF;");

        Label label = new Label(day.getDate());

        Button pairs = new Button("pairs");
        pairs.setFont(new Font(9));
        pairs.setStyle("-fx-background-color: #AA88FF;");
        pairs.setMinWidth(MainPanel.DETAIL_CELL_WIDTH/2 -5);
        pairs.setOnAction(e->{
            new PairWindow(day).show();
        });


        Button report = new Button("report");
        report.setStyle("-fx-background-color: #88AAFF;");
        report.setFont(new Font(9));
        report.setMinWidth(MainPanel.DETAIL_CELL_WIDTH/2 -5);


        HBox buttons = new HBox(pairs,report);
        buttons.setPadding(new Insets(3));
        buttons.setAlignment(Pos.CENTER);

        //TODO make buttons do the things
        this.getChildren().addAll(label,buttons);
    }

}
