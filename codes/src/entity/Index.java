package entity;

import java.util.ArrayList;

public class Index {
    private int indexNum;
    private int vacancy;
    private ArrayList<Student> waitList;
    private ArrayList<Lesson> lessonList;

    public Index(int indexNum, int vacancy, ArrayList<Student> waitList, ArrayList<Lesson> lessonList){
        this.indexNum = indexNum;
        this.vacancy = vacancy;
        this.waitList = waitList;
        this.lessonList = lessonList;
    }

    public int getIndexNum(){
        return this.indexNum;
    }
    public void setIndexNum(int indexNum){
        this.indexNum = indexNum;
    }

    public int getVacancy(){
        return this.vacancy;
    }
    public void setVacancy(int vacancy){
        this.vacancy = vacancy;
    }

    public ArrayList<Student> getWaitList(){
        return this.waitList;
    }
    public void setWaitList(ArrayList<Student> waitList){
        this.waitList = waitList;
    }

    public ArrayList<Lesson> getLessonList(){
        return this.lessonList;
    }
    public void setLessonList(ArrayList<Lesson> lessonList){
        this.lessonList = lessonList;
    }
}
