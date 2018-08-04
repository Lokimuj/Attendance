package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String ritID;
    private char id;
    private AttendanceDetail[] details;

    public Student(String name, String ritID, char id, int days){
        details = new AttendanceDetail[days];
        this.name = name;
        this.ritID = ritID;
        this.id = id;
    }

    public void setup(List<Day> days, String inp){
        String[] detailDays = inp.split(",");
        for(int i = 0; i<details.length; i++){
            if(i<detailDays.length){
                String day = detailDays[i].trim();
                details[i] = AttendanceDetail.readAttendanceDetail(day);
                days.get(i).
            }
        }
    }
}
