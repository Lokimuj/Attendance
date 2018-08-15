package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DayCalendar {
    private HashMap<Day,Integer> indexMap = new HashMap<>();
    private List<Day> days;
    int curInd = 0;

    public DayCalendar(Collection<Day> days){
        this.days = new ArrayList<>(days);
        for(Day day : days){
            indexMap.put(day,curInd++);
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


}
