package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student {

    private static Set<Character> ids = new HashSet<>();
    private static char nextID = 'a';
    public static final Student EMPTY_STUDENT = new Student();

    private String firstName;
    private String lastName;
    private String ritID;
    private char id;
    private boolean hasID;
    private List<AttendanceDetail> details;
    private DayCalendar calendar;

    private Student(){}

    public Student(String inp, DayCalendar days){
        details = new ArrayList<>();
        calendar = days;

        String[] info = inp.split(",");
        this.lastName = info[0];
        this.firstName = info[1];
        this.ritID = info[2];
        if(info.length>=4){
            this.id = info[3].charAt(0);
            ids.add(this.id);
            hasID = true;
        }else{
            hasID = false;
        }
    }

    public void setup(StudentRoster roster, String inp){
        String[] detailDays = inp.split(",");
        for(int i = 0; i<calendar.size(); i++){
            AttendanceDetail detail;
            if(i<detailDays.length){
                String day = detailDays[i].trim();
                detail = AttendanceDetail.readAttendanceDetail(roster, day);
            } else{
                detail = AttendanceDetail.CreateAbsentDetail(roster);
            }
            details.add(detail);
            calendar.getDay(i).linkStudent(this,detail);
        }
    }

    public List<AttendanceDetail> getDetails(){
        return details;
    }

    public AttendanceDetail getDetail(Day day){
        return details.get(calendar.getIndex(day));
    }

    public boolean hasID() {
        return hasID;
    }

    public char getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRitID() {
        return ritID;
    }

    public String display(){
        return id + ". " + lastName + "," + firstName + "," + ritID;
    }

    @Override
    public String toString() {
        String out = firstName + " " + lastName + ", " + ritID + ", " + id +": ";
        for(var detail : details){
            out += detail.toString() + " ";
        }
        return out;
    }
}
