package control;

import entity.Course;
import entity.Index;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

public class CourseMgr {
    protected int getWaitListStudent(String courseCode, int indexNumber) throws EOFException, ClassNotFoundException, IOException {
        Index indexNum = filterCourseByCourseCodeAndIndexNum(courseCode, indexNumber);
        if (indexNum == null) {
            return -1;
        }

        if (indexNum.getWaitList() == null) {
            return 0;
        }

        return indexNum.getWaitList().size();
    }

    public int checkVacancies(String courseCode, int indexNumber) throws EOFException, ClassNotFoundException, IOException {
        Index indexNum = filterCourseByCourseCodeAndIndexNum(courseCode, indexNumber);
        if (indexNum == null) {
            return -1;
        }
        return indexNum.getVacancy();
    }
    
    public boolean validateCourse(String courseCode) throws EOFException, ClassNotFoundException, IOException {
        List<Course> courseList = DataMgr.readCourseList();
        Course filteredCourse =  courseList.stream().filter(course -> course.getCourseCode().equalsIgnoreCase(courseCode)).findAny().orElse(null);
        if (filteredCourse == null) {
            return false;
        }
        return true;
    }
    
    public Course getCourse(String courseCode) throws EOFException, ClassNotFoundException, IOException {
    	List<Course> courseList = DataMgr.readCourseList();
    	for (Course course: courseList) {
    		if (course.getCourseCode().compareTo(courseCode) == 0) return course;
    	}
    	return null;
    }

    public boolean validateIndexNum(String courseCode, int indexNumber) throws EOFException, ClassNotFoundException, IOException {
        Index indexNum = filterCourseByCourseCodeAndIndexNum(courseCode, indexNumber);
        if (indexNum == null) {
            return false;
        }
        return true;
    }
    
    public Index getIndex(String courseCode, int indexNum) throws EOFException, ClassNotFoundException, IOException {
		Course course = getCourse(courseCode);
		for (Index index: course.getIndexList()) {
			if (index.getIndexNum() == indexNum) {
				return index;
			}
		}
		return null;
	}

    private Index filterCourseByCourseCodeAndIndexNum(String courseCode, int indexNumber) throws EOFException, ClassNotFoundException, IOException {
        List<Course> courseList = DataMgr.readCourseList();
        return courseList.stream().filter(course -> course.getCourseCode().equalsIgnoreCase(courseCode))
            .flatMap(course -> course.getIndexList().stream())
            .filter(indexNum -> indexNumber == indexNum.getIndexNum())
            .findAny().orElse(null);
    }
}
