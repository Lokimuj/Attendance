package model;

import java.util.*;

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
            Student s1 = comp.get(i);
            Student s2 = noComp.get(i);
            s1.getDetail(this).changePartner(s2.getId());
            s2.getDetail(this).changePartner(s1.getId());
            pairs.add(new StudentPair(s1,s2));
        }
        for(;i<comp.size()-1;i+=2){
            Student s1 = comp.get(i);
            Student s2 = comp.get(i+1);
            s1.getDetail(this).changePartner(s2.getId());
            s2.getDetail(this).changePartner(s1.getId());
            pairs.add(new StudentPair(s1,s2));
        }
        if(i<comp.size()){
            pairs.add(new StudentPair(comp.get(i),null));
            comp.get(i).getDetail(this).clearPartner();
        }

        for(;i<noComp.size()-1;i+=2){
            Student s1 = noComp.get(i);
            Student s2 = noComp.get(i+1);
            s1.getDetail(this).changePartner(s2.getId());
            s2.getDetail(this).changePartner(s1.getId());
            pairs.add(new StudentPair(s1,s2));
        }
        if(i<noComp.size()){
            pairs.add(new StudentPair(noComp.get(i),null));
            comp.get(i).getDetail(this).clearPartner();
        }

        return pairs;
    }

    public List<StudentPair> getPairs(){
        ArrayList<StudentPair> pairs = new ArrayList<>();
        Set<Student> paired = new HashSet<>();
        for(var student:studentDetails.keySet()){
            if(!paired.contains(student)){
                StudentPair pair;
                AttendanceDetail detail = studentDetails.get(student);
                if(detail.hasPartner()){
                    pair = new StudentPair(student,detail.getPartner());
                    paired.add(detail.getPartner());
                }else{
                    pair = new StudentPair(student,null);
                }
                paired.add(student);
                pairs.add(pair);
            }
        }
        return pairs;
    }

    public void clearPartners(){
        for(AttendanceDetail detail: studentDetails.values()){
            detail.clearPartner();
        }
    }

    public void pairStudents(Student s1, Student s2){
        AttendanceDetail d1 = studentDetails.get(s1);
        AttendanceDetail d2 = studentDetails.get(s2);
        if(d1.hasPartner()){
            studentDetails.get(d1.getPartner()).clearPartner();
        }
        if(d2.hasPartner()){
            studentDetails.get(d2.getPartner()).clearPartner();
        }
        d1.changePartner(s2.getId());
        d2.changePartner(s1.getId());
    }

    public void unpair(Student student){
        AttendanceDetail detail = studentDetails.get(student);
        if(detail.hasPartner()){
            studentDetails.get(detail.getPartner()).clearPartner();
            detail.clearPartner();
        }
    }

    public void changeAttendance(Student student, AttendanceDetail.Type type){
        if(type == AttendanceDetail.Type.ABSENT){
            unpair(student);
        }
        studentDetails.get(student).changeType(type);
    }

    public String write(){
        return date;
    }

    @Override
    public String toString() {
        String out = "DAY " + date + ":";
        for(Student student: studentDetails.keySet()){
            out += "\n\t" + student.display() + " : " + studentDetails.get(student).write();
        }
        return out;
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
