package gui.mainpanel.StudentRow;

import gui.mainpanel.MainPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.AttendanceDetail;

public class DetailTile extends VBox {

    public static final String ABSENT_STYLE = "-fx-background-color: #DD0000;";
    public static final String NO_COMP_STYLE = "-fx-background-color: #DDDD00;";
    public static final String COMP_STYLE = "-fx-background-color: #00DD00;";

    AttendanceDetail detail;


    public DetailTile(AttendanceDetail detail) {
        super();

        this.detail = detail;
        detail.subscribe((t,c)->update());

        this.setHeight(MainPanel.STUDENT_ROW_HEIGHT);
        this.setMinWidth(MainPanel.DETAIL_CELL_WIDTH);
        this.setAlignment(Pos.CENTER);
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ToggleGroup attendance = new ToggleGroup();

        RadioButton absent = new RadioButton("X");
        absent.setContentDisplay(ContentDisplay.BOTTOM);
        absent.setToggleGroup(attendance);
        absent.setPadding(new Insets(3));
        absent.setOnAction(e->{
            detail.changeType(AttendanceDetail.Type.ABSENT);
        });

        RadioButton noComp = new RadioButton("NC");
        noComp.setContentDisplay(ContentDisplay.BOTTOM);
        noComp.setToggleGroup(attendance);
        noComp.setPadding(new Insets(3));
        noComp.setOnAction(e->{
            detail.changeType(AttendanceDetail.Type.NO_COMPUTER);
        });

        RadioButton comp = new RadioButton("C");
        comp.setContentDisplay(ContentDisplay.BOTTOM);
        comp.setToggleGroup(attendance);
        comp.setPadding(new Insets(3));
        comp.setOnAction(e->{
            detail.changeType(AttendanceDetail.Type.COMPUTER);
        });

        HBox buttons = new HBox(absent,noComp,comp);
        buttons.setAlignment(Pos.CENTER);
        switch (detail.getType()){
            case ABSENT:
                absent.setSelected(true);
                this.setStyle(ABSENT_STYLE);
                break;
            case COMPUTER:
                comp.setSelected(true);
                this.setStyle(COMP_STYLE);
                break;
            case NO_COMPUTER:
                noComp.setSelected(true);
                this.setStyle(NO_COMP_STYLE);
                break;
        }

        Label partnerID = new Label((detail.hasPartner() ? detail.getPartnerID() : "None")+"");

        this.getChildren().addAll(buttons,partnerID);
    }


    private void update(){
        switch (detail.getType()){
            case ABSENT:
                this.setStyle(ABSENT_STYLE);
                break;
            case COMPUTER:
                this.setStyle(COMP_STYLE);
                break;
            case NO_COMPUTER:
                this.setStyle(NO_COMP_STYLE);
                break;
        }
        Label partnerID = new Label((detail.hasPartner() ? detail.getPartnerID() : "None")+"");
    }

}
