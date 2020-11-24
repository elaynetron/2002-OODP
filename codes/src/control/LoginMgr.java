package control;

import java.io.*;
import java.util.*;

import entity.Admin;
import entity.Student;

public class LoginMgr {
	public String validateAdmin(String username, String password) throws EOFException, ClassNotFoundException, IOException {;
		ArrayList<Admin> adminList = DataMgr.readAdminList();
		for (Admin admin: adminList) {
			if (admin.getUsername().compareTo(username) == 0) {
				if (PasswordMgr.isExpectedPassword(password, admin.getSalt(), admin.getHashedPassword())) return "Successful login!";
				else return "Wrong password!";
			}
		}
		return "Invalid user!";
	}

	public Admin getAdmin(String username) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Admin> adminList = DataMgr.readAdminList();
		for (Admin admin: adminList) {
			if (admin.getUsername().compareTo(username) == 0) return admin;
		}
		return null; 
	}
	
	public String validateStudent(String username, String password) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Student> studentList = DataMgr.readStudentList();
		for (Student student: studentList) {
			if (student.getUsername().compareTo(username) == 0) {
				if (PasswordMgr.isExpectedPassword(password, student.getSalt(), student.getHashedPassword())) {
					if (validateAccessTime(student)) return "Successful login!";
					return "Invalid Access Time!";
				}
				return "Wrong password!";
			}
		}
		return "Invalid user!";
	}

	public Student getStudent(String username) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Student> studentList = DataMgr.readStudentList();
		for (Student student: studentList) {
			if (student.getUsername().compareTo(username) == 0) return student;
		}
		return null; 
	}
	
	private boolean validateAccessTime(Student student) {
		Calendar startAccessTime = student.getStart();
		Calendar endAccessTime = student.getEnd();
		Calendar currentTime = Calendar.getInstance();
		if ((currentTime.compareTo(startAccessTime) > 0) && (currentTime.compareTo(endAccessTime) < 0)) return true;
		else return false;
	}
}
