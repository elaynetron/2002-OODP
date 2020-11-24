package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Index implements Serializable{
    private int indexNum;
    private int vacancy;
    private ArrayList<Student> waitList;
    private ArrayList<Lesson> lessonList;

    public Index(int indexNum, int vacancy, ArrayList<Student> waitList, ArrayList<Lesson> lessonList) {
        this.indexNum = indexNum;
        this.vacancy = vacancy;
        this.waitList = waitList;
        this.lessonList = lessonList;
    }

	public int getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(int indexNum) {
        this.indexNum = indexNum;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public ArrayList<Student> getWaitList() {
        return waitList;
    }

    public void setWaitList(ArrayList<Student> waitList) {
        this.waitList = waitList;
    }

    public ArrayList<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(ArrayList<Lesson> lessonList) {
        this.lessonList = lessonList;
    }
}
