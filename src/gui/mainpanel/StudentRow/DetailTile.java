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

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class DetailTile extends VBox {

    public static final String ABSENT_STYLE = "-fx-background-color: #DD4444;";
    public static final String NO_COMP_STYLE = "-fx-background-color: #DDDD44;";
    public static final String COMP_STYLE = "-fx-background-color: #44DD44;";

    AttendanceDetail detail;
    Label partnerID;
    Object detailKey;


    public DetailTile(AttendanceDetail detail) {
        super();

        this.detail = detail;
        detailKey = detail.subscribe((t,c)->update());

        this.setHeight(MainPanel.STUDENT_ROW_HEIGHT);
        this.setMinWidth(MainPanel.DETAIL_CELL_WIDTH);
        this.setMaxWidth(MainPanel.DETAIL_CELL_WIDTH);
        this.setAlignment(Pos.CENTER);
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ToggleGroup attendance = new ToggleGroup();

        HBox buttons = new HBox();

        for(AttendanceDetail.Type type:AttendanceDetail.Type.values()){
            RadioButton button = new RadioButton(""+type.print());
            button.setContentDisplay(ContentDisplay.BOTTOM);
            button.setMaxWidth(MainPanel.LEFT_COLUMN_WIDTH/6);
            button.setToggleGroup(attendance);
            button.setPadding(new Insets(3));
            button.setOnAction(e->{
                detail.changeType(type);
            });
            if(detail.getType() == type){
                button.setSelected(true);
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
            }
            buttons.getChildren().add(button);
        }

        buttons.setAlignment(Pos.CENTER);


        partnerID = new Label((detail.hasPartner() ? detail.getPartnerID() : "None")+"");

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
        partnerID.setText((detail.hasPartner() ? detail.getPartnerID() : "None")+"");
    }

}
