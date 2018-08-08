package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day {
    HashMap<Student,AttendanceDetail> studentDetails;
    List<Student> noComp;
    List<Student> comp;
    boolean upToDate = false;


    public Day(){
        studentDetails = new HashMap<>();
        noComp = new ArrayList<>();
        comp = new ArrayList<>();
    }

    public void linkStudent(Student student, AttendanceDetail detail){
        detail.subscribe((v1,v2) -> upToDate = false);
        studentDetails.put(student,detail);
    }

    public List<List<Student>> split() {
        List<List<Student>> pair = new ArrayList<>(2);

        if (!upToDate) {
            noComp = new ArrayList<>();
            comp = new ArrayList<>();
            for(var student: studentDetails.keySet()){
                switch(studentDetails.get(student).getType()){
                    case NO_COMPUTER:
                        noComp.add(student);
                        break;
                    case COMPUTER:
                        comp.add(student);
                }
            }
            upToDate = true;
        }
        pair.set(0, new ArrayList<>(noComp));
        pair.set(1, new ArrayList<>(comp));
        return pair;
    }



}
