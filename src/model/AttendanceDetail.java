package model;

import java.util.ArrayList;
import java.util.HashMap;

public class AttendanceDetail {
    public static final char ABSENT_CHARACTER_ID = 'X';
    public static final char NO_PARTNER_ID = '/';
    public static final char ABSENT_PREFIX = 'X';
    public static final char NO_COMPUTER_PREFIX = 'N';
    public static final char COMPUTER_PREFIX = 'C';

    public enum Type{
        ABSENT(ABSENT_PREFIX),
        NO_COMPUTER(NO_COMPUTER_PREFIX),
        COMPUTER(COMPUTER_PREFIX);

        char rep;

        Type(char rep){
            this.rep = rep;
        }
        public String print(){
            return String.valueOf(rep);
        }
    }

    private Type type;
    private Student partner;
    private char partnerID;

    private HashMap<Object,Updater> updaters = new HashMap<>();

    private AttendanceDetail(Type type, char partner, StudentRoster roster){
        this.type = type;
        this.partnerID = partner;

    }

    public static AttendanceDetail CreateAbsentDetail(StudentRoster roster){
        return new AttendanceDetail(Type.ABSENT,ABSENT_CHARACTER_ID,roster);
    }

    public static AttendanceDetail CreateNoComputerDetail(StudentRoster roster,char partner){
        return new AttendanceDetail(Type.NO_COMPUTER,partner,roster);
    }

    public static AttendanceDetail CreateNoComputerDetail(StudentRoster roster){
        return CreateNoComputerDetail(roster, NO_PARTNER_ID);
    }

    public static AttendanceDetail CreateComputerDetail(StudentRoster roster, char partner){
        return new AttendanceDetail(Type.COMPUTER,partner,roster);
    }

    public static AttendanceDetail CreateComputerDetail(StudentRoster roster){
        return CreateComputerDetail(roster, NO_PARTNER_ID);
    }

    public static AttendanceDetail readAttendanceDetail(StudentRoster roster, String detail){
        if(detail.length()!=2){
            return null;
        }
        switch (detail.charAt(0)){
            case ABSENT_PREFIX:
                return CreateAbsentDetail(roster);
            case NO_COMPUTER_PREFIX:
                return CreateNoComputerDetail(roster, detail.charAt(1));
            case COMPUTER_PREFIX:
                return CreateComputerDetail(roster, detail.charAt(1));
            default:
                return null;
        }
    }

    public String displayString(){
        return type.print() + partner;
    }

    public char getPartnerID() {
        return partnerID;
    }

    public Type getType() {
        return type;
    }

    public void changeType(Type type){
        this.type = type;
        if(type == Type.ABSENT){
            this.partnerID = ABSENT_CHARACTER_ID;
        }else if(partnerID == ABSENT_CHARACTER_ID){
            partnerID = NO_PARTNER_ID;
        }
        update();
    }

    public void update(){
        for(var updater: updaters.values()){
            updater.update(this.type,this.partnerID);
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

    @Override
    public String toString() {
        return type.print() + partner;
    }

    public interface Updater{
        void update(Type type, char partner);
    }
}
