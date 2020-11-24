package entity;

import java.io.*;
import java.util.*;

public class Course implements Serializable {
	String courseCode;
	String courseName;
	int AU;
	String school;
	String courseType;
	Calendar examDate;
	ArrayList<Index> indexList;

	public Course(String courseCode, String courseName, int AU, String school, String courseType, Calendar examDate,
			ArrayList<Index> indexList) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.AU = AU;
		this.school = school;
		this.courseType = courseType;
		this.examDate = examDate;
		this.indexList = indexList;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getAU() {
		return AU;
	}

	public void setAU(int AU) {
		this.AU = AU;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Calendar getExamDate() {
		return examDate;
	}

	public void setExamDate(Calendar examDate) {
		this.examDate = examDate;
	}

	public ArrayList<Index> getIndexList() {
		return indexList;
	}

	public void setIndexList(ArrayList<Index> indexList) {
		this.indexList = indexList;
	}
}
