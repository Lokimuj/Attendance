package gui.mainpanel.DayRow.pairing;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Day;
import model.Student;
import model.StudentPair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PairWindow extends Stage {

    public static final int PAIR_GRID_PANE_COLUMNS = 3;
    public static final int SOLO_GRID_PANE_COLUMNS = 6;
    Day day;

    Student selectedStudent = null;
    StudentToken selectedToken = null;
    PairToken selectedPair = null;

    Map<Student,StudentToken> tokenMap = new HashMap<>();

    Set<PairToken> pairs = new HashSet<>();
    Set<StudentToken> solos = new HashSet<>();

    GridPane pairsPane;
    GridPane solosPane;
    Button pairUp;

    public PairWindow(Day day) {
        super();
        this.day = day;
        this.pairUp = new Button("Pair Up");
        pairUp.setOnAction(e->{
            this.pairUp();
        });
        for(StudentPair pair: day.getPairs()){
            tokenMap.put(pair.s1,new StudentToken(this,pair.s1,pair.s1.getDetail(day)));
            if(pair.hasPartner()){
                tokenMap.put(pair.s2,new StudentToken(this,pair.s2,pair.s2.getDetail(day)));
                pairs.add(new PairToken(this,tokenMap.get(pair.s1),tokenMap.get(pair.s2)));
            }else{
                solos.add(tokenMap.get(pair.s1));
            }
        }
        refresh();
    }

    void refresh(){
        pairsPane = new GridPane();
        pairsPane.setPadding(new Insets(10));
        pairsPane.setHgap(5);
        pairsPane.setVgap(5);

        solosPane = new GridPane();
        solosPane.setPadding(new Insets(10));
        solosPane.setHgap(5);
        solosPane.setVgap(5);


        int i = 0;
        for(var pair: pairs){
            pairsPane.add(pair,i%PAIR_GRID_PANE_COLUMNS, i++/PAIR_GRID_PANE_COLUMNS);
        }

        i = 0;
        for(var solo:solos){
            solosPane.add(solo,i%SOLO_GRID_PANE_COLUMNS,i++/SOLO_GRID_PANE_COLUMNS);
        }

        this.setScene(new Scene(new VBox(pairsPane,solosPane,pairUp)));
    }

    void setup(){
        pairs.clear();
        solos.clear();
        for(StudentPair pair: day.getPairs()){
            if(pair.hasPartner()){
                pairs.add(new PairToken(this,tokenMap.get(pair.s1),tokenMap.get(pair.s2)));
            }else{
                solos.add(tokenMap.get(pair.s1));
            }
        }
    }

    void studentClicked(Student student,StudentToken token){
        if(selectedStudent!=null){
            pair(selectedStudent,student);
            selectedToken.unselect();
            selectedToken = null;
            selectedStudent = null;
        }else {
            this.selectedStudent = student;
            this.selectedToken = token;
            token.select();
        }
    }

    void unpair(Student student, PairToken token){
        pairs.remove(token);
        token.reenableTokens();
        solos.add(token.s1);
        solos.add(token.s2);
        day.unpair(student);
        refresh();
    }

    void pair(Student s1, Student s2){
        day.pairStudents(s1,s2);
        solos.remove(tokenMap.get(s1));
        solos.remove(tokenMap.get(s2));
        pairs.add(new PairToken(this,tokenMap.get(s1),tokenMap.get(s2)));
        refresh();
    }

    void pairUp(){
        day.pairUp();
        setup();
        refresh();
    }

    void clearPairs(){}
}
