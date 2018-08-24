package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Adrian Postolache axp3806@rit.edu
 */
public class AttendanceDetail {
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
    private Object partnerKey;
    private StudentRoster roster;

    private HashMap<Object,Updater> updaters = new HashMap<>();

    private AttendanceDetail(Type type, char partnerID, StudentRoster roster){
        this.type = type;
        this.partnerID = partnerID;
        this.roster = roster;
        if(roster.isIDRegistered(partnerID)){
            this.partner = roster.getStudent(partnerID);
            partnerKey = this.partner.subscribe(c -> this.partnerID = c);
        }else{
            roster.subscribe(()->{
                if(roster.isIDRegistered(partnerID)){
                    this.partner = roster.getStudent(partnerID);
                    partnerKey = this.partner.subscribe(c ->{
                        this.partnerID = c;
                    });
                }else{
                    this.partnerID = NO_PARTNER_ID;
                }
            });
        }
    }

    public static AttendanceDetail CreateAbsentDetail(StudentRoster roster){
        return new AttendanceDetail(Type.ABSENT,NO_PARTNER_ID,roster);
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

    public String write(){
        return type.print() + partnerID;
    }

    public char getPartnerID() {
        return partnerID;
    }

    public void changePartner(char id){
        if(partnerID != NO_PARTNER_ID){
            partner.unsubscribe(partnerKey);
        }
        partnerID = id;
        partner = roster.getStudent(id);
        partnerKey = this.partner.subscribe(c -> this.partnerID = c);
        update();
    }

    public void clearPartner(){
        if(partnerID == NO_PARTNER_ID){
            return;
        }
        partner.unsubscribe(partnerKey);
        partnerID = NO_PARTNER_ID;
        partner = null;
        partnerKey = null;
        update();
    }

    public boolean hasPartner(){
        return partnerID != NO_PARTNER_ID;
    }

    public Student getPartner(){
        return partner;
    }

    public Type getType() {
        return type;
    }

    public void changeType(Type type){
        this.type = type;
        if(type == Type.ABSENT){
            clearPartner();
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
        return type.print() + (partnerID != NO_PARTNER_ID ? partner.display() : "/");
    }

    public interface Updater{
        void update(Type type, char partner);
    }
}
