package model;

import java.io.*;
import java.util.Scanner;

public class Sheet {
    public static final String COMMENT_LINE_CHAR = "#";

    private DayCalendar days;
    private StudentRoster roster;


    public void setup(Scanner file){
        String line = file.nextLine();
        while(line.startsWith(COMMENT_LINE_CHAR) || line.matches("\\s*")){
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
        roster.initializeIDs();
        file.close();

    }

    public void display(){
        System.out.println(days + "\n" + roster);
    }

    public void run() throws IOException {
        Scanner in = new Scanner(System.in);
        String line;
        String[] split;
        display();
        do{
            System.out.println(">");
            line = in.nextLine();
            split = line.split("\\s+");
            if(split.length == 0 ){
                continue;
            }
            switch (split[0]){
                case "a":
                    if(split.length <4){
                        System.out.println("usage: a last-name first-name rit-id");
                        continue;
                    }
                    roster.addStudent(new Student(split[1],split[2],split[3],days,roster));
                    display();
                    break;
            }
        }while(in.hasNext());
    }

    public static void main(String[] args) throws IOException {
        Sheet sheet = new Sheet();
        sheet.setup(new Scanner(new File(args[0])));
        sheet.run();
    }
}
