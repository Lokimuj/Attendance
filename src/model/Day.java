package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day {
    private String date;
    HashMap<Student,AttendanceDetail> studentDetails;
    List<Student> noComp;
    List<Student> comp;
    boolean upToDate = false;


    public Day(String date){
        this.date = date;
        studentDetails = new HashMap<>();
        noComp = new ArrayList<>();
        comp = new ArrayList<>();
    }

    public void linkStudent(Student student, AttendanceDetail detail){
        detail.subscribe((v1,v2) -> upToDate = false);
        studentDetails.put(student,detail);
    }

    public synchronized void split() {
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
    }

    public synchronized List<Student> getNoComp(){
        return noComp;
    }

    public synchronized List<Student> getComp() {
        return comp;
    }

    public synchronized List<StudentPair> pairUp(){
        if(!upToDate){
            split();
        }
        Collections.shuffle(comp);
        Collections.shuffle(noComp);
        ArrayList<StudentPair> pairs = new ArrayList<>();
        int i = 0;
        for(;i<comp.size() && i<noComp.size();i++){
            pairs.add(new StudentPair(comp.get(i),noComp.get(i)));
        }
        if(i<comp.size()){
            pairs.add(new StudentPair(comp.get(i),null));
        }

        for(;i<noComp.size()-1;i+=2){
            pairs.add(new StudentPair(noComp.get(i),noComp.get(i+1)));
        }
        if(i<noComp.size()){
            pairs.add(new StudentPair(noComp.get(i),null));
        }

        return pairs;
    }

    public String write(){
        return date;
    }

    @Override
    public String toString() {
        String out = "DAY " + date + ":";
        for(Student student: studentDetails.keySet()){
            out += "\n" + student.display() + " : " + studentDetails.get(student).write();
        }
        return out;
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
