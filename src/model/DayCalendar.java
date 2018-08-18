package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DayCalendar {
    private HashMap<Day,Integer> indexMap = new HashMap<>();
    private List<Day> days = new ArrayList<>();


    public DayCalendar(String inpLine){
        String[] dates = inpLine.split(",");
        for(int i = 0; i<dates.length; i++){
            Day day = new Day(dates[i].replaceAll(" ",""));
            days.add(day);
            indexMap.put(day,i);
        }
    }

    public int getIndex(Day day){
        return indexMap.get(day);
    }

    public Day getDay(int index){
        return days.get(index);
    }

    public int size(){
        return days.size();
    }

    public String write(){
        String out = "";
        for(Day day: days){
            out+=day.write() + ",";
        }
        return out.substring(0,out.length()-1);
    }

    @Override
    public String toString() {
        String out = "CALENDAR:\n";
        int i = 1;
        for(Day day: days){
            out+= i++ + ": " + day + "\n";
        }

        return out;
    }
}
