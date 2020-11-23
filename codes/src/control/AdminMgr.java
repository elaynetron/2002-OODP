package control;

import java.io.*;
import java.util.*;

import entity.Student;
import entity.RegisteredCourse;
import entity.Course;

public class AdminMgr extends CourseMgr{
    public static void editStudentAccessPeriod(String matricNum, Calendar startTime, Calendar endTime){
        ArrayList<Student> studentList = DataMgr.readStudentList();
        for (Student student: studentList) {
            if (student.getMatricNum() == matricNum) {
                student.setStart = startTime;
                student.setEnd = endTime;
            }
        }
    }

    public static void addStudent(String username, String password, String firstName, String lastName, String matricNum, String gender, String nationality, String mobileNum, String email, Calendar startDate, Calendar endDate){
        Student newStudent  = new Student(username, password, firstName, lastName, matricNum, gender, nationality, mobileNum, email, startDate, endDate);
        ArrayList<Student> studentList = DataMgr.readStudentList();
        studentList.add(newStudent);
        DataMgr.writeStudentList(studentList);

    }

    public static void deleteStudent(String matricNum){
        ArrayList<Student> studentList = DataMgr.readStudentList();
        for (Student student: studentList) {
            if (student.getMatricNum() == matricNum) {
                studentList.remove(new Student(student));
                break;
            }
        }
        DataMgr.writeStudentList(studentList);


    }

    public static void printStudentListByCourse(String courseCode) throws EOFException, ClassNotFoundException, IOException {
        ArrayList<Student> studentList = DataMgr.readStudentList();
        ArrayList<Student> newStudentList = null;
        ArrayList<RegisteredCourse> courseList;
        for (Student student: studentList) {
            courseList = student.getCourseRegistered();
            for (RegisteredCourse course: courseList)
            {
                if(course.getCourseCode == courseCode)
                 System.out.print(student.getFirstName()+" "+student.getLastName()+" "+student.getGender()+" "+student.getNationality());
            }

        }
    }

    public static void printStudentListByIndex(String courseCode, String indexNum) throws EOFException, ClassNotFoundException, IOException {
        ArrayList<Student> studentList = DataMgr.readStudentList();
        ArrayList<Student> newStudentList = null;
        ArrayList<RegisteredCourse> courseList;
        for (Student student: studentList) {
            courseList = student.getCourseRegistered();
            for (RegisteredCourse course: courseList)
            {
                if(course.getCourseCode == courseCode && course.getIndex == indexNum)
                    System.out.print(student.getFirstName()+" "+student.getLastName()+" "+student.getGender()+" "+student.getNationality());
            }

        }

    }

    public static void addCourse(String courseCode, String courseName, int AU, String school, String courseType, Calendar examDate){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        Course newCourse = new Course(courseCode, courseName, AU, school, courseType, examDate);
        courseList.add(newCourse);
        DataMgr.writeCourseList(courseList);
    }

    public static void updateCourseCode(String oldCourseCode, String newCourseCode){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == oldCourseCode) {
                course.setCourseCode(newCourseCode);
                break;
            }
        }
    }

    public static void updateCourseName(String courseCode, String courseName){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == courseCode) {
                course.setCourseName(courseName);
                break;
            }
        }
    }

    public static void updateAU(String courseCode, int AU){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == courseCode) {
                course.setAU(AU);
                break;
            }
        }
    }

    public static void updateSchool(String courseCode, String school){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == courseCode) {
                course.setSchool(school);
                break;
            }
        }
    }

    public static void updateCourseType(String courseCode, String courseType){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == courseCode) {
                course.setCourseType(courseType);
                break;
            }
        }
    }

    public static void updateExamDate(String courseCode, Calendar examDate){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == courseCode) {
                course.setExamDate(examDate);
                break;
            }
        }
    }

    public static void addIndex(String courseCode, String indexNum){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == courseCode) {
                ArrayList<Index> indexList= course.getIndexList;
                Index newIndex = new Index(indexNum);
                indexList.add(newIndex);
                course.setIndexList(indexList);
                break;
            }
        }
    }

    public static void deleteIndex(String courseCode, String indexNum){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == courseCode) {
                ArrayList<Index> indexList= course.getIndexList;
                for(Index index: indexList) {
                    if (index.getIndexNum() == indexNum) {
                        indexList.remove(new Index(index));
                        break;
                    }
                }
            }
        }
    }

    public static void updateVacancy(String courseCode, String indexNum, int newVacancy){
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode() == courseCode) {
                ArrayList<Index> indexList= course.getIndexList;
                for(Index index: indexList) {
                    if (index.getIndexNum() == indexNum) {
                        index.setVacancy(newVacancy);
                        break;
                    }
                }
            }
        }
    }


}
