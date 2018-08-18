package model;

import java.util.Scanner;

public class Sheet {
    public static final String COMMENT_LINE_CHAR = "#";

    private DayCalendar days;
    private StudentRoster roster;


    public void setup(Scanner file){
        String line = file.nextLine();
        while(!(line.startsWith(COMMENT_LINE_CHAR) || line.matches("\\s*"))){
            line = file.nextLine();
        }
        days = new DayCalendar(line.replaceAll("\\s+",""));
        roster = new StudentRoster();
        boolean readingDetails = false;
        Student curStudent = Student.EMPTY_STUDENT;
        while(file.hasNext()) {
            line = file.nextLine();
            if (line.startsWith(COMMENT_LINE_CHAR) || line.matches("\\s*")) {
                continue;
            }
            if (readingDetails) {
                curStudent.setup(roster, line.replaceAll("\\s+", ""));
            } else {
                curStudent = new Student(line.replaceAll("\\s+", ""), days);
                roster.addStudent(curStudent);
            }
            readingDetails = !readingDetails;
        }
    }

    public static void main(String[] args) {

    }
}
