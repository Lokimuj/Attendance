package model;

public class StudentPair {
    public Student s1, s2;

    public StudentPair(Student s1, Student s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    public Student getS1() {
        return s1;
    }

    public Student getS2() {
        return s2;
    }
}