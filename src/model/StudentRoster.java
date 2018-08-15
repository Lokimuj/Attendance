package model;

import java.util.*;

public class StudentRoster {
    private Set<Character> usedIDs = new HashSet<>();
    private Map<Character,Student> idMap = new HashMap<>();
    private List<Student> students = new ArrayList<>();

    private char nextID = 'a';

    public StudentRoster(){}

    public boolean registerStudent(char id, Student student){
        if(usedIDs.contains(id)){
            return false;
        }
        if(student.hasID() && idMap.get(student.getId()) == student){
            idMap.remove(student.getId());
        }
        return false;
    }
}
