package control;

import java.io.*;
import java.util.*;

import entity.Admin;
import entity.Student;

public class LoginMgr {
	public String validateAdmin(String username, String password) throws EOFException, ClassNotFoundException, IOException {;
		ArrayList<Admin> adminList = DataMgr.readAdminList();
		for (Admin admin: adminList) {
			if (admin.getUsername() == username) {
				if (PasswordMgr.isExpectedPassword(password, admin.getSalt(), admin.getHashedPassword())) return "Successful login!";
				else return "Wrong password!";
			}
		}
		return "Invalid user!";
	}

	public Admin getAdmin(String username) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Admin> adminList = DataMgr.readAdminList();
		for (Admin admin: adminList) {
			if (admin.getUsername() == username) return admin;
		}
		return null; 
	}
	
	public String validateStudent(String username, String password) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Student> studentList = DataMgr.readStudentList();
		for (Student student: studentList) {
			if (student.getUsername() == username) {
				if (PasswordMgr.isExpectedPassword(password, student.getSalt(), student.getHashedPassword())) {
					if (validateAccessTime(student)) return "Successful login!";
					else return "Invalid Access Time!";
				}
				else return "Wrong password!";
			}
		}
		return "Invalid user!";
	}

	public Student getStudent(String matricNum) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Student> studentList = DataMgr.readStudentList();
		for (Student student: studentList) {
			if (student.getUsername() == matricNum) return student;
		}
		return null; 
	}
	
	private boolean validateAccessTime(Student student) {
		Calendar startAccessTime = student.getStart();
		Calendar endAccessTime = student.getEnd();
		Calendar currentTime = Calendar.getInstance();
		if ((currentTime.compareTo(startAccessTime) >= 0) && (currentTime.compareTo(endAccessTime) <= 0)) return true;
		else return false;
	}
}
