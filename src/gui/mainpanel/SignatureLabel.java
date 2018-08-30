package gui.mainpanel;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class SignatureLabel extends Label {
    public static final String SIGNATURE = "Program created by: Adrian Postolache";
    public SignatureLabel(){
        super(SIGNATURE);
        this.setFont(new Font(9));
    }
}
