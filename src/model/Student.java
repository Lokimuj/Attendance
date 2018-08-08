package model;

import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String ritID;
    private char id;
    private AttendanceDetail[] details;

    public Student(String firstName, String lastName, String ritID, char id, int days){
        details = new AttendanceDetail[days];
        this.firstName = firstName;
        this.lastName = lastName;
        this.ritID = ritID;
        this.id = id;
    }

    public void setup(List<Day> days, String inp){
        String[] detailDays = inp.split(",");
        for(int i = 0; i<details.length; i++){
            if(i<detailDays.length){
                String day = detailDays[i].trim();
                details[i] = AttendanceDetail.readAttendanceDetail(day);
            } else{
                details[i] = AttendanceDetail.CreateAbsentDetail();
            }
            days.get(i).linkStudent(this,details[i]);
        }
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
        return lastName + "," + firstName + "," + ritID + "," + id;
    }
}
