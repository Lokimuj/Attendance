package model;

import java.util.*;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class Student {

    public static final Student EMPTY_STUDENT = new Student();

    private String firstName;
    private String lastName;
    private String ritID;
    private char id;
    private boolean hasID;
    private List<AttendanceDetail> details;
    private DayCalendar calendar;

    private HashMap<Object,Updater> updaters = new HashMap<>();


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
            hasID = true;
        }else{
            hasID = false;
        }
    }

    public Student(String lastName, String firstName, String ritID, DayCalendar days, StudentRoster roster){
        this.calendar = days;
        details = new ArrayList<>();
        this.lastName = lastName;
        this.firstName = firstName;
        this.ritID = ritID;
        hasID = false;
        for(int i = 0; i<days.size(); i++){
            AttendanceDetail detail = AttendanceDetail.CreateAbsentDetail(roster);
            details.add(detail);
            calendar.getDay(i).linkStudent(this,detail);
        }
    }

    public void setup(StudentRoster roster, String inp){
        ArrayList<String> detailDays;
        if(inp.equals("")){
            detailDays = new ArrayList<>();
        }else {
            detailDays = new ArrayList<>(Arrays.asList(inp.split(",")));
        }
        for(int i = 0; i<calendar.size(); i++){
            AttendanceDetail detail;
            if(i<detailDays.size()){
                String day = detailDays.get(i).trim();
                detail = AttendanceDetail.readAttendanceDetail(roster, day.replaceAll("\\s+",""));
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

    public void setId(char id){
        this.id = id;
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

    public String write(){
        String out = lastName + "," + firstName + "," + ritID + "," + id+"\n";
        for(AttendanceDetail detail: details){
            out+=detail.write()+",";
        }
        return out.substring(0,out.length()-1);
    }

    public String display(){
        return id + ". " + lastName + "," + firstName + "," + ritID;
    }

    @Override
    public String toString() {
        String out = firstName + " " + lastName + ", " + ritID + ", " + id +": ";
//        for(var detail : details){
//            out += detail.toString() + " ";
//        }
        return out;
    }


    public void update(){
        for(var updater: updaters.values()){
            updater.update(this.id);
        }
    }

    public Object subscribe(Updater updater){
        Object key = new Object();
        updaters.put(key,updater);
        return key;
    }

    public boolean unsubscribe(Object key){
        if(updaters.containsKey(key)){
            updaters.remove(key);
            return true;
        }else{
            return false;
        }
    }

    public interface Updater{
        void update(char id);
    }
}
