package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String ritID;
    private char id;
    private List<AttendanceDetail> details;

    public Student(String firstName, String lastName, String ritID, char id, int days){
        details = new ArrayList<>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.ritID = ritID;
        this.id = id;
    }

    public void setup(DayCalendar days, String inp){
        String[] detailDays = inp.split(",");
        for(int i = 0; i<days.size(); i++){
            AttendanceDetail detail;
            if(i<detailDays.length){
                String day = detailDays[i].trim();
                detail = AttendanceDetail.readAttendanceDetail(day);
            } else{
                detail = AttendanceDetail.CreateAbsentDetail();
            }
            details.add(detail);
            days.getDay(i).linkStudent(this,detail);
        }
    }

    public List<AttendanceDetail> getDetails(){
        return details;
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
