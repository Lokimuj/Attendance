package model;

import java.util.HashMap;

public class Day {
    HashMap<Student,AttendanceDetail> studentDetails;

    public Day(){
        studentDetails = new HashMap<>();
    }

    public void linkStudent(Student student, AttendanceDetail detail){
        studentDetails.put(student,detail);
    }


}
