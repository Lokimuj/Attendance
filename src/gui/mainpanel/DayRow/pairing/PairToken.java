package gui.mainpanel.DayRow.pairing;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.StudentPair;



public class PairToken extends HBox {

    public static final String BG_COLOR = "-fx-background-color: #FF9999;";

    StudentToken s1;
    StudentToken s2;

    public PairToken(PairWindow window, StudentToken s1, StudentToken s2) {
        super();
        this.s1 = s1;
        this.s2 = s2;
        this.setStyle(BG_COLOR);
        this.setPadding(new Insets(5));
        this.setWidth(StudentToken.TOKEN_WIDTH);
        this.setHeight(StudentToken.TOKEN_HEIGHT);

        Button unpair = new Button("Unpair");
        unpair.setOnAction(e->{
            window.unpair(s1.getStudent(),this);
        });
        unpair.setMinHeight(StudentToken.TOKEN_HEIGHT);
        s1.setEnabled(false);
        s2.setEnabled(false);

        this.getChildren().addAll(s1,unpair,s2);
    }

    public StudentToken getS1() {
        return s1;
    }

    public StudentToken getS2() {
        return s2;
    }

    public void reenableTokens(){
        s1.setEnabled(true);
        s2.setEnabled(true);
    }
}
