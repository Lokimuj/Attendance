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
    private char partner;

    private HashMap<Object,Updater> updaters = new HashMap<>();

    private AttendanceDetail(Type type, char partner){
        this.type = type;
        this.partner = partner;
    }

    public static AttendanceDetail CreateAbsentDetail(){
        return new AttendanceDetail(Type.ABSENT,ABSENT_CHARACTER_ID);
    }

    public static AttendanceDetail CreateNoComputerDetail(char partner){
        return new AttendanceDetail(Type.NO_COMPUTER,partner);
    }

    public static AttendanceDetail CreateNoComputerDetail(){
        return CreateNoComputerDetail(NO_PARTNER_ID);
    }

    public static AttendanceDetail CreateComputerDetail(char partner){
        return new AttendanceDetail(Type.COMPUTER,partner);
    }

    public static AttendanceDetail CreateComputerDetail(){
        return CreateComputerDetail(NO_PARTNER_ID);
    }

    public static AttendanceDetail readAttendanceDetail(String detail){
        if(detail.length()!=2){
            return null;
        }
        switch (detail.charAt(0)){
            case ABSENT_PREFIX:
                return CreateAbsentDetail();
            case NO_COMPUTER_PREFIX:
                return CreateNoComputerDetail(detail.charAt(1));
            case COMPUTER_PREFIX:
                return CreateComputerDetail(detail.charAt(1));
            default:
                return null;
        }
    }

    public String displayString(){
        return type.print() + partner;
    }

    public char getPartner() {
        return partner;
    }

    public Type getType() {
        return type;
    }

    public void changeType(Type type){
        this.type = type;
        if(type == Type.ABSENT){
            this.partner = ABSENT_CHARACTER_ID;
        }else if(partner == ABSENT_CHARACTER_ID){
            partner = NO_PARTNER_ID;
        }
        update();
    }

    public void changePartner(char partner){
        this.partner = partner;
        update();
    }


    public void update(){
        for(var updater: updaters.values()){
            updater.update(this.type,this.partner);
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
