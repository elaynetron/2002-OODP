package control;

import entity.Student;
import entity.RegisteredCourse;

import java.io.*;
import java.util.*;

import entity.Course;
import entity.Index;
import entity.Lesson;


public class AdminMgr extends CourseMgr{
    public static void editStudentAccessPeriod(String matricNum, Calendar startTime, Calendar endTime) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Student> studentList = DataMgr.readStudentList();
        for (Student student: studentList) {
            if (student.getMatricNum().compareTo(matricNum) == 0) {
                student.setStart(startTime);
                student.setEnd(endTime);
                DataMgr.updateStudentList(student);
            }
        }
    }

    public static void addStudent(String username, String password, String firstName, String lastName, String matricNum, char gender, String nationality, int mobileNum, String email, Calendar startDate, Calendar endDate) throws EOFException, ClassNotFoundException, IOException{
        Student newStudent  = new Student(username, password, firstName, lastName, matricNum, gender, nationality, mobileNum, email, startDate, endDate);
        ArrayList<Student> studentList = DataMgr.readStudentList();
        studentList.add(newStudent);
        DataMgr.writeStudentList(studentList);

    }

    public static void deleteStudent(String matricNum) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Student> studentList = DataMgr.readStudentList();
        for (Student student: studentList) {
            if (student.getMatricNum().compareTo(matricNum) == 0) {
                studentList.remove(student);
                break;
            }
        }
        DataMgr.writeStudentList(studentList);
    }

    public static void printStudentListByCourse(String courseCode) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Student> studentList = DataMgr.readStudentList();
        ArrayList<RegisteredCourse> courseList;
        for (Student student: studentList) {
            courseList = student.getCoursesRegistered();
            for (RegisteredCourse course: courseList) {
                if(course.getCourse().getCourseCode().compareTo(courseCode) == 0)
                 System.out.print(student.getFirstName()+" "+student.getLastName()+" "+student.getGender()+" "+student.getNationality());
            }

        }
    }

    public static void printStudentListByIndex(String courseCode, int indexNum) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Student> studentList = DataMgr.readStudentList();
        ArrayList<RegisteredCourse> courseList;
        for (Student student: studentList) {
            courseList = student.getCoursesRegistered();
            for (RegisteredCourse course: courseList)
            {
                if(course.getCourse().getCourseCode().compareTo(courseCode) == 0 && course.getIndex().getIndexNum() == indexNum)
                    System.out.print(student.getFirstName()+" "+student.getLastName()+" "+student.getGender()+" "+student.getNationality());
            }
        }
    }

    public static void addCourse(String courseCode, String courseName, int AU, String school, String courseType, Calendar examDate) throws EOFException, ClassNotFoundException, IOException{
    	ArrayList<Course> courseList = DataMgr.readCourseList();
        Course newCourse = new Course(courseCode, courseName, AU, school, courseType, examDate, new ArrayList<Index>());
        courseList.add(newCourse);
        DataMgr.writeCourseList(courseList);
    }

    public static void updateCourseCode(String oldCourseCode, String newCourseCode) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(oldCourseCode) == 0) {
                course.setCourseCode(newCourseCode);
                break;
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static void updateCourseName(String courseCode, String courseName) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(courseCode) == 0) {
                course.setCourseName(courseName);
                break;
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static void updateAU(String courseCode, int AU) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(courseCode) == 0) {
                course.setAU(AU);
                break;
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static void updateSchool(String courseCode, String school) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(courseCode) == 0) {
                course.setSchool(school);
                break;
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static void updateCourseType(String courseCode, String courseType) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(courseCode) == 0) {
                course.setCourseType(courseType);
                break;
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static void updateExamDate(String courseCode, Calendar examDate) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(courseCode) == 0) {
                course.setExamDate(examDate);
                break;
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static void addIndex(String courseCode, int indexNum, int vacancy, ArrayList<Lesson> lessonList) throws EOFException, ClassNotFoundException, IOException{
    	ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(courseCode) == 0) {
                ArrayList<Index> indexList= course.getIndexList();
                Index newIndex = new Index(indexNum, vacancy, new ArrayList<Student>(), lessonList);
                indexList.add(newIndex);
                course.setIndexList(indexList);
                break;
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static void deleteIndex(String courseCode, int indexNum) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(courseCode) == 0) {
                ArrayList<Index> indexList= course.getIndexList();
                for(Index index: indexList) {
                    if (index.getIndexNum() == indexNum) {
                        indexList.remove(index);
                        break;
                    }
                }
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static void updateVacancy(String courseCode, int indexNum, int newVacancy) throws EOFException, ClassNotFoundException, IOException{
        ArrayList<Course> courseList = DataMgr.readCourseList();
        for (Course course: courseList) {
            if (course.getCourseCode().compareTo(courseCode) == 0) {
                ArrayList<Index> indexList= course.getIndexList();
                for(Index index: indexList) {
                    if (index.getIndexNum() == indexNum) {
                        index.setVacancy(newVacancy);
                        break;
                    }
                }
            }
        }
        DataMgr.writeCourseList(courseList);
    }

    public static boolean validateMatric(String matricNum) throws EOFException, ClassNotFoundException, IOException {
        ArrayList<Student> studentList = DataMgr.readStudentList();
        for (Student student: studentList) {
            if (student.getMatricNum().compareTo(matricNum) == 0)
                return true;
        }
        return false;
    }


}
