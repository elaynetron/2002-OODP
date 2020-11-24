package control;

import java.io.*;
import java.util.*;

import entity.Admin;
import entity.Course;
import entity.Student;

public class DataMgr {
	public static ArrayList<Student> readStudentList() throws EOFException, IOException, ClassNotFoundException {
		ArrayList<Student> studentList = new ArrayList<>();
		try {
            FileInputStream fileIn = new FileInputStream("db/studentList.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
	            while (true) {
	            	studentList.add((Student) objectIn.readObject());
	            }
            } catch (EOFException e) {}
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return studentList;
	}
	
	public static void writeStudentList(ArrayList<Student> studentList) {
		try {
            FileOutputStream fileOut = new FileOutputStream("db/studentList.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Student student: studentList) {
            	objectOut.writeObject((Object) student);
            }
            objectOut.close();
            //System.out.println("Successfully written to studentList!");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void updateStudentList(Student newStudent) throws IOException, ClassNotFoundException{
		ArrayList<Student> studentList = readStudentList();
		try {
            FileOutputStream fileOut = new FileOutputStream("db/studentList.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Student student: studentList) {
            	if (student.getUsername().compareTo(newStudent.getUsername()) == 0) {
            		objectOut.writeObject((Object) newStudent);
            	}
            	else objectOut.writeObject((Object) student);
            }
            objectOut.close();
            //System.out.println("Successfully written to studentList!");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static ArrayList<Admin> readAdminList() throws EOFException, IOException, ClassNotFoundException {
		ArrayList<Admin> adminList = new ArrayList<>();
		try {
            FileInputStream fileIn = new FileInputStream("db/adminList.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
	            while (true) {
	            	adminList.add((Admin) objectIn.readObject());
	            }
            } catch (EOFException e) {}
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return adminList;
	}
	
	public static void writeAdminList(ArrayList<Admin> adminList) throws IOException, ClassNotFoundException{
		try {
            FileOutputStream fileOut = new FileOutputStream("db/adminList.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Admin admin: adminList) {
            	objectOut.writeObject((Object) admin);
            }
            objectOut.close();
            //System.out.println("Successfully written to adminList!");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void updateAdminList(Admin newAdmin) throws IOException, ClassNotFoundException{
		ArrayList<Admin> adminList = readAdminList();
		try {
            FileOutputStream fileOut = new FileOutputStream("db/adminList.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Admin admin: adminList) {
            	if (admin.getUsername().compareTo(newAdmin.getUsername()) == 0) {
            		objectOut.writeObject((Object) newAdmin);
            	}
            	else objectOut.writeObject((Object) admin);
            }
            objectOut.close();
            //System.out.println("Successfully written to adminList!");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static ArrayList<Course> readCourseList() throws EOFException, IOException, ClassNotFoundException {
		ArrayList<Course> courseList = new ArrayList<>();
		try {
            FileInputStream fileIn = new FileInputStream("db/courseList.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
	            while (true) {
	            	courseList.add((Course) objectIn.readObject());
	            }
            } catch (EOFException e) {}
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return courseList;
	}
	
	public static void writeCourseList(ArrayList<Course> courseList) throws IOException, ClassNotFoundException{
		try {
            FileOutputStream fileOut = new FileOutputStream("db/courseList.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Course course: courseList) {
            	objectOut.writeObject((Object) course);
            }
            objectOut.close();
            //System.out.println("Successfully written to courseList!");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void updateCourseList(Course newCourse) throws IOException, ClassNotFoundException{
		ArrayList<Course> courseList = readCourseList();
		try {
            FileOutputStream fileOut = new FileOutputStream("db/courseList.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Course course: courseList) {
            	if (course.getCourseCode().compareTo(newCourse.getCourseCode()) == 0) {
            		objectOut.writeObject((Object) newCourse);
            	}
            	else objectOut.writeObject((Object) course);
            }
            objectOut.close();
            //System.out.println("Successfully written to courseList!");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
