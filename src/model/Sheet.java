package model;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class Sheet {
    public static final String COMMENT_LINE_CHAR = "#";

    private DayCalendar days;
    private StudentRoster roster;
    private String filename;

    private Map<Object,Refresher> refreshers = new HashMap<>();

    public boolean setup(String filename) {
        this.filename = filename;
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            return false;
        }
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
        refresh();
        return true;
    }

    public void display(){
        System.out.println(days + "\n" + roster);
    }

    public void save(String filename){
        try(
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        ){
            bw.write(days.write() + "\n" + roster.write());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(){
        save(filename);
    }

    public StudentRoster getRoster(){
        return roster;
    }

    public DayCalendar getDays(){
        return days;
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
                case "p":
                    if (split.length <2){
                        System.out.println("usage p index-of-day");
                        continue;
                    }
                    Day day = days.getDay(Integer.parseInt(split[1]));
                    var pairs = day.pairUp();
                    System.out.println("PAIRS:");
                    for(var pair:pairs){
                        System.out.println(pair);
                    }
                    display();
                    break;
                case "s":
                    if(split.length >2){
                        System.out.println("usage: s [filename]");
                        continue;
                    }
                    this.save((split.length > 1 ? split[1] : this.filename));
                    System.out.println("File saved!");
                    break;
                case "l":
                    if(split.length<2){
                        System.out.println("usage: l filename");
                        continue;
                    }
                    System.out.println("Want to save the current configuration first? y/n");
                    if (in.nextLine().startsWith("y")) {
                        this.save(this.filename);
                    }
                    this.setup(split[1]);
                    System.out.println("Save loaded!");
                    break;
            }
        }while(in.hasNext());
    }

    public Object subscribe(Refresher refresher){
        Object key = new Object();
        refreshers.put(key,refresher);
        return key;
    }

    public void unsubscribe(Object key){
        refreshers.remove(key);
    }

    public void refresh(){
        for(Refresher refresher:refreshers.values()){
            refresher.refresh();
        }
    }

    public interface Refresher{
        void refresh();
    }

    public static void main(String[] args) throws IOException {
        Sheet sheet = new Sheet();
        sheet.setup(args[0]);
        sheet.run();
    }
}
