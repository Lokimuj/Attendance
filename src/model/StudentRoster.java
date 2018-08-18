package model;

import java.util.*;

public class StudentRoster {
    private Set<Character> usedIDs = new HashSet<>();
    private Map<Character,Student> idMap = new HashMap<>();
    private List<Student> students = new ArrayList<>();

    private HashMap<Object,Updater> updaters = new HashMap<>();

    private char nextID = 'a';
    private boolean isInitialized = false;

    public StudentRoster(){}

    public void addStudent(Student student){
        students.add(student);
        if(isInitialized){
            if(student.hasID()){
                if(usedIDs.contains(student.getId())){

                }
            }
        }
    }

    public Student getStudent(char id){
        return idMap.get(id);
    }

    public boolean isIDRegistered(char id){
        return usedIDs.contains(id);
    }

    public void initializeIDs(){
        usedIDs.clear();
        idMap.clear();
        List<Student> toBeMapped = new ArrayList<>();
        for(var student: students){
            if(student.hasID()){
                if(!usedIDs.contains(student.getId())){
                    usedIDs.add(student.getId());
                    idMap.put(student.getId(),student);
                }else{
                    toBeMapped.add(student);
                }
            }else {
                toBeMapped.add(student);
            }
        }
        refreshID();
        for(var student: toBeMapped){
            student.setId(nextID);
            usedIDs.add(nextID);
            idMap.put(nextID,student);
            refreshID();
        }
        isInitialized = true;
    }

    private void refreshID(){
        while(usedIDs.contains(nextID)){
            nextID++;
        }
    }


    public void update(){
        for(var updater: updaters.values()){
            updater.update();
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
        void update();
    }
}
