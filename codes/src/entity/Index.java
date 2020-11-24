package entity;

import java.io.EOFException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import control.DataMgr;

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
        return this.indexNum;
    }

    public void setIndexNum(int indexNum) {
        this.indexNum = indexNum;
    }

    public int getVacancy() {
    	return this.vacancy;
    }
    
    public int getVacancy(String courseCode) throws EOFException, ClassNotFoundException, IOException{
    	int numOfVacancies = 10;
        ArrayList<Student> studentList = DataMgr.readStudentList();
        for (Student student: studentList) {
        	for (RegisteredCourse registeredCourse: student.getCoursesRegistered()) {
        		if (registeredCourse.getCourse().getCourseCode().compareTo(courseCode) == 0) {
        			if (this.indexNum == registeredCourse.getIndex().getIndexNum()) numOfVacancies--;
        		}
        	}
        }
        return numOfVacancies;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public ArrayList<Student> getWaitList() {
        return this.waitList;
    }

    public void setWaitList(ArrayList<Student> waitList) {
        this.waitList = waitList;
    }

    public ArrayList<Lesson> getLessonList() {
        return this.lessonList;
    }

    public void setLessonList(ArrayList<Lesson> lessonList) {
        this.lessonList = lessonList;
    }
}
