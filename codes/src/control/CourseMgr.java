package control;

import java.io.*;
import java.util.*;

import entity.Course;
import entity.Index;
import entity.Student;

public class CourseMgr {
	protected Student getWaitListStudent(String courseCode, int indexNumber)
			throws EOFException, ClassNotFoundException, IOException {
		Index index = filterCourseByCourseCodeAndIndexNum(courseCode, indexNumber);
		return index.getWaitList().get(0);
	}

	public int checkVacancies(String courseCode, int indexNumber)
			throws EOFException, ClassNotFoundException, IOException {
		Index indexNum = filterCourseByCourseCodeAndIndexNum(courseCode, indexNumber);
		if (indexNum == null) {
			return -1;
		}
		return indexNum.getVacancy(courseCode);
	}

	public boolean validateCourse(String courseCode) throws EOFException, ClassNotFoundException, IOException {
		List<Course> courseList = DataMgr.readCourseList();
		Course filteredCourse = courseList.stream()
				.filter(course -> course.getCourseCode().equalsIgnoreCase(courseCode)).findAny().orElse(null);
		if (filteredCourse == null) {
			return false;
		}
		return true;
	}

	public Course getCourse(String courseCode) throws EOFException, ClassNotFoundException, IOException {
		List<Course> courseList = DataMgr.readCourseList();
		for (Course course : courseList) {
			if (course.getCourseCode().compareTo(courseCode) == 0)
				return course;
		}
		return null;
	}

	public boolean validateIndexNum(String courseCode, int indexNumber)
			throws EOFException, ClassNotFoundException, IOException {
		Index indexNum = filterCourseByCourseCodeAndIndexNum(courseCode, indexNumber);
		if (indexNum == null) {
			return false;
		}
		return true;
	}

	public Index getIndex(String courseCode, int indexNum) throws EOFException, ClassNotFoundException, IOException {
		Course course = getCourse(courseCode);
		for (Index index : course.getIndexList()) {
			if (index.getIndexNum() == indexNum) {
				return index;
			}
		}
		return null;
	}

	private Index filterCourseByCourseCodeAndIndexNum(String courseCode, int indexNumber)
			throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Course> courseList = DataMgr.readCourseList();
		return courseList.stream().filter(course -> course.getCourseCode().equalsIgnoreCase(courseCode))
				.flatMap(course -> course.getIndexList().stream())
				.filter(indexNum -> indexNumber == indexNum.getIndexNum()).findAny().orElse(null);
	}
}
