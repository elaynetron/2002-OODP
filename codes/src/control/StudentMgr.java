package control;

import java.io.*;
import java.util.*;

import boundary.StudentUI;
import entity.Course;
import entity.Index;
import entity.Lesson;
import entity.RegisteredCourse;
import entity.Student;

public class StudentMgr extends CourseMgr {

	private Student student;

	/**
	 * 
	 * @param student
	 */
	public StudentMgr(Student student) {
		this.student = student;
	}

	/**
	 * 
	 * @param courseCode
	 * @param indexNum
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EOFException
	 */
	public void addCourse(String courseCode, int indexNum) throws EOFException, ClassNotFoundException, IOException {
		int vacancy = super.checkVacancies(courseCode, indexNum);
		if (checkClash(courseCode, indexNum)) {
			if (checkAUExceed(courseCode, indexNum)) {
				Course course = getCourse(courseCode);
				Index index = getIndex(courseCode, indexNum);

				if (vacancy > 0) {
					RegisteredCourse toRegister = new RegisteredCourse(this.student, course, index, "Registered");
					student.addToCoursesRegistered(toRegister);
					System.out.println("Course successfully registered.");
					NotificationMgr.sendEmail(this.student, courseCode);
				} else {
					RegisteredCourse toRegister = new RegisteredCourse(this.student, course, index, "Waitlist");
					student.addToCoursesRegistered(toRegister);
					updateWaitList(course, index);
					System.out.println("Course added to waitlist.");
				}
			} else
				System.out.println("Failed to register for course - Reached maximum number of AUs.");
		} else
			System.out.println("Failed to register for course - Course clashes with other registered courses.");
		DataMgr.updateStudentList(student);
	}

	/**
	 * 
	 * @param courseCode
	 * @param indexNum
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void updateWaitList(Course course, Index index) throws ClassNotFoundException, IOException {
		ArrayList<Student> waitList = index.getWaitList();
		waitList.add(this.student);
		DataMgr.updateCourseList(course);
	}

	/**
	 * 
	 * @param courseCode
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EOFException
	 */
	public void dropCourse(String courseCode) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<RegisteredCourse> registeredList = student.getCoursesRegistered();
		Course course = getCourse(courseCode);
		Index index = null;
		for (RegisteredCourse registeredCourse : registeredList) {
			if (registeredCourse.getCourse().equals(course)) {
				registeredList.remove(registeredCourse);
				index = registeredCourse.getIndex();
				System.out.println("Course dropped successfully.");
				return;
			}
		}
		System.out.println("You are not registered for the course!");
		DataMgr.updateStudentList(this.student);

		ArrayList<Student> waitList = index.getWaitList();
		index.setVacancy(index.getVacancy() + 1);
		Student studentToInform = waitList.get(0);
		waitList.remove(studentToInform);
		ArrayList<RegisteredCourse> studentToInformRegisteredList = studentToInform.getCoursesRegistered();
		for (RegisteredCourse registeredCourse : studentToInformRegisteredList) {
			if (registeredCourse.getCourse().equals(course)) {
				registeredCourse.setStatus("Registered");
				System.out.println("Student " + studentToInform.getUsername() + " has been informed.");
			}
		}
		NotificationMgr.sendEmail(studentToInform, courseCode);
		DataMgr.updateCourseList(course);
		DataMgr.updateStudentList(studentToInform);
	}

	public void checkCoursesRegistered() {
		System.out.println("Student " + this.student.getUsername() + "'s Registered Courses");
		Course course;
		Index index;
		ArrayList<RegisteredCourse> registeredList = student.getCoursesRegistered();
		for (RegisteredCourse registeredCourse : registeredList) {
			course = registeredCourse.getCourse();
			System.out.println(course.getCourseCode() + " " + course.getCourseName());
			System.out.println("AU: " + course.getAU() + " | School: " + course.getSchool() + " |  Type: "
					+ course.getCourseType());
			System.out.println("Exam Date: " + course.getExamDate().toString());

			index = registeredCourse.getIndex();
			System.out.println("Index Number: " + index.getIndexNum());
		}
	}

	/**
	 * 
	 * @param courseCode
	 * @param indexNum
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EOFException
	 */
	public void changeIndexNum(String courseCode, int indexNum)
			throws EOFException, ClassNotFoundException, IOException {
		ArrayList<RegisteredCourse> registeredList = student.getCoursesRegistered();
		Course course = getCourse(courseCode); // method to get course from coursecode
		for (RegisteredCourse registeredCourse : registeredList) {
			if (registeredCourse.getCourse().equals(course)) {
				dropCourse(courseCode);
				addCourse(courseCode, indexNum);
				System.out.println("Index number changed succesfully.");
				break;
			}
		}
	}

	/**
	 * 
	 * @param peer
	 * @param courseCode
	 * @param selfIndexNum
	 * @param peerIndexNum
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EOFException
	 */
	public void swapIndexNum(Student peer, String courseCode, int selfIndexNum, int peerIndexNum)
			throws EOFException, ClassNotFoundException, IOException {
		Course toSwap = getCourse(courseCode);
		ArrayList<RegisteredCourse> selfCourses = this.student.getCoursesRegistered();
		ArrayList<RegisteredCourse> peerCourses = peer.getCoursesRegistered();
		StudentMgr peerMgr = new StudentMgr(peer);
		for (RegisteredCourse selfRegCourse : selfCourses) {
			for (RegisteredCourse peerRegCourse : peerCourses) {
				if (selfRegCourse.getCourse().equals(toSwap) && peerRegCourse.getCourse().equals(toSwap)) {
					dropCourse(courseCode);
					peerMgr.dropCourse(courseCode);
					addCourse(courseCode, peerIndexNum);
					peerMgr.addCourse(courseCode, selfIndexNum);
					System.out.println("Index number successfully swapped.");
				}
			}
		}
		DataMgr.updateStudentList(this.student);
		DataMgr.updateStudentList(peer);
	}

	/**
	 * 
	 * @param courseCode
	 * @param indexNum
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws EOFException
	 */
	private boolean checkClash(String courseCode, int indexNum)
			throws EOFException, ClassNotFoundException, IOException {
		Course course = getCourse(courseCode);
		ArrayList<Lesson> lessonList = null;
		for (Index index : course.getIndexList()) {
			if (index.getIndexNum() == indexNum) {
				lessonList = index.getLessonList();
			}
		}
		ArrayList<RegisteredCourse> registeredList = this.student.getCoursesRegistered();
		ArrayList<Lesson> lessonListRegistered;
		for (RegisteredCourse registeredCourse : registeredList) {
			lessonListRegistered = registeredCourse.getIndex().getLessonList();
			for (Lesson lessonRegistered : lessonListRegistered) {
				for (Lesson lesson : lessonList) {
					if (lesson.getLessonStart().compareTo(lessonRegistered.getLessonEnd()) < 0)
						return false;
					if (lesson.getLessonEnd().compareTo(lessonRegistered.getLessonStart()) > 0)
						return false;
				}
			}
		}
		return true;
	}

	private boolean checkAUExceed(String courseCode, int indexNum)
			throws EOFException, ClassNotFoundException, IOException {
		Course course = getCourse(courseCode);
		int totalAU = course.getAU();
		ArrayList<RegisteredCourse> registeredList = this.student.getCoursesRegistered();
		for (RegisteredCourse registeredCourse : registeredList) {
			totalAU += registeredCourse.getCourse().getAU();
		}
		return (totalAU > 21); // can change number to fit maximum number of AUs
	}

	/**
	 * 
	 * @param notification
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void chooseNotification(int notificationChoice) throws ClassNotFoundException, IOException {
		this.student.setNotification(notificationChoice);
		DataMgr.updateStudentList(this.student);
	}

	public int checkVacancies(String courseCode, int indexNum)
			throws EOFException, ClassNotFoundException, IOException {
		System.out.println("Course code: " + courseCode);
		System.out.println("Index number: " + indexNum);
		System.out.println("Number of vacancies: " + super.checkVacancies(courseCode, indexNum));
		return 0;
	}

	public boolean isExistingStudent(String userName) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Student> studentList = DataMgr.readStudentList();
		for (Student student : studentList) {
			if (student.getUsername().compareTo(userName) == 0)
				return true;
		}
		return false;
	}

	public Student getStudent(String userName) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Student> studentList = DataMgr.readStudentList();
		for (Student student : studentList) {
			if (student.getUsername().compareTo(userName) == 0)
				return student;
		}
		return null;
	}
}
