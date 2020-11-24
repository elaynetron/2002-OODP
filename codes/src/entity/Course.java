package entity;

import java.util.Calendar;
import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String courseName;
    private int AU;
    private String school;
    private String courseType;
    private Calendar examDate;
    private ArrayList<Index> indexList;

    public Course(String courseCode, String courseName, int AU, String school, String courseType, Calendar examDate, ArrayList<Index> indexList){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.AU = AU;
        this.school = school;
        this.courseType = courseType;
        this.examDate = examDate;
        this.indexList = indexList;
    }

    public String getCourseCode(){
        return this.courseCode;
    }
    public void setCourseCode(String courseCode){
        this.courseCode = courseCode;
    }

    public String getCourseName(){
        return this.courseName;
    }
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public int getAU(){
        return this.AU;
    }
    public void setAU(int AU){
        this.AU = AU;
    }

    public String getSchool(){
        return this.school;
    }
    public void setSchool(String school){
        this.school = school;
    }

    public String getCourseType(){
        return this.courseType;
    }
    public void setCourseType(String courseType){
        this.courseType = courseType;
    }

    public Calendar getExamDate(){
        return this.examDate;
    }
    public void setExamDate(Calendar examDate){
        this.examDate = examDate;
    }

    public ArrayList<Index> getIndexList(){
        return this.indexList;
    }
    public void setIndexList(ArrayList<Index> indexList){
        this.indexList = indexList;
    }
}
