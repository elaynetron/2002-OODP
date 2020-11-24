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
					this.student.addToCoursesRegistered(toRegister);
					updateVacancy(courseCode, indexNum, -1);
					System.out.println("Course successfully registered.\n");
					//TODO
					//NotificationMgr.sendEmail(this.student, courseCode);
				} else {
					RegisteredCourse toRegister = new RegisteredCourse(this.student, course, index, "Waitlist");
					this.student.addToCoursesRegistered(toRegister);
					updateWaitList(course, index);
					System.out.println("Course added to waitlist.\n");
				}
				DataMgr.updateCourseList(course);
			} else
				System.out.println("Failed to register for course - Reached maximum number of AUs.\n");
		} else
			System.out.println("Failed to register for course - Course clashes with other registered courses.\n");
		DataMgr.updateStudentList(this.student);
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
		ArrayList<RegisteredCourse> registeredList = this.student.getCoursesRegistered();
		Course course = getCourse(courseCode);
		Index index = null;
		boolean dropped = false;
		for (RegisteredCourse registeredCourse : registeredList) {
			if (registeredCourse.getCourse().getCourseCode().compareTo(courseCode) == 0) {
				registeredList.remove(registeredCourse);
				this.student.setCoursesRegistered(registeredList);
				index = registeredCourse.getIndex();
				System.out.println("Course dropped successfully.\n");
				dropped = true;
			}
		}
		if (!dropped) System.out.println("You are not registered for the course!");
		DataMgr.updateStudentList(this.student);

		ArrayList<Student> waitList = index.getWaitList();
		updateVacancy(courseCode, index.getIndexNum(), 1);
		Student studentToInform = waitList.get(0);
		waitList.remove(studentToInform);
		ArrayList<RegisteredCourse> studentToInformRegisteredList = studentToInform.getCoursesRegistered();
		for (RegisteredCourse registeredCourse : studentToInformRegisteredList) {
			if (registeredCourse.getCourse().equals(course)) {
				registeredCourse.setStatus("Registered");
				System.out.println("Student " + studentToInform.getUsername() + " has been informed.");
			}
		}
		//TODO
		//NotificationMgr.sendEmail(studentToInform, courseCode);
		DataMgr.updateCourseList(course);
		DataMgr.updateStudentList(studentToInform);
	}

	public void checkCoursesRegistered() {
		System.out.println("Student " + this.student.getUsername() + "'s Registered Courses");
		Course course;
		Index index;
		String examDate;
		ArrayList<RegisteredCourse> registeredList = this.student.getCoursesRegistered();
		if (!registeredList.isEmpty()) {
			for (RegisteredCourse registeredCourse : registeredList) {
				course = registeredCourse.getCourse();
				System.out.println(course.getCourseCode() + " " + course.getCourseName());
				System.out.println("AU: " + course.getAU() + " | School: " + course.getSchool() + " |  Type: "
						+ course.getCourseType());
				examDate = Integer.toString(course.getExamDate().get(Calendar.DAY_OF_MONTH)) + Integer.toString(course.getExamDate().get(Calendar.MONTH)+1) + Integer.toString(course.getExamDate().get(Calendar.YEAR));
				if (examDate.compareTo("31122") != 0) { // Gregorian calendar format, meaning 00/00/0000
					System.out.format("Exam Date: " + examDate.substring(0,2) + "/" + examDate.substring(2,4) + "/" + examDate.substring(4) + "\n");
				}
	
				index = registeredCourse.getIndex();
				System.out.println("Index Number: " + index.getIndexNum());
				System.out.println();
			}
		}
		System.out.println();
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
		ArrayList<RegisteredCourse> registeredList = this.student.getCoursesRegistered();
		Course course = getCourse(courseCode); // method to get course from coursecode
		boolean success = false;
		for (RegisteredCourse registeredCourse : registeredList) {
			if (registeredCourse.getCourse().getCourseCode().compareTo(courseCode) == 0) {
				Index newIndex = getIndex(courseCode, indexNum);
				if (newIndex.getVacancy() > 0) {
					Index oldIndex = registeredCourse.getIndex();
					updateVacancy(courseCode, oldIndex.getIndexNum(), 1);
					updateVacancy(courseCode, newIndex.getIndexNum(), -1);
					DataMgr.updateCourseList(registeredCourse.getCourse());
					registeredCourse.setIndex(newIndex);
					System.out.println("Successfully changed index number.\n");
					success = true;
					break;
				}
			}
		}
		this.student.setCoursesRegistered(registeredList);
		if (!success) System.out.println("You are not registered for this course, please try again.\n");
		DataMgr.updateStudentList(this.student);
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
		ArrayList<RegisteredCourse> selfCourses = this.student.getCoursesRegistered();
		ArrayList<RegisteredCourse> peerCourses = peer.getCoursesRegistered();
		StudentMgr peerMgr = new StudentMgr(peer);
		boolean success = false;
		for (RegisteredCourse selfRegCourse : selfCourses) {
			for (RegisteredCourse peerRegCourse : peerCourses) {
				if (selfRegCourse.getCourse().getCourseCode().compareTo(courseCode) == 0 && peerRegCourse.getCourse().getCourseCode().compareTo(courseCode) == 0) {
					if (selfRegCourse.getIndex().getIndexNum() == selfIndexNum && peerRegCourse.getIndex().getIndexNum() == peerIndexNum) {
						selfRegCourse.setIndex(peerRegCourse.getIndex());
						peerRegCourse.setIndex(selfRegCourse.getIndex());
						System.out.println("Index number successfully swapped.\n");
						success = true;
						break;
					}
				}
			}
		}
		if (!success) System.out.println("Swap not successful.\n");
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
		if (!registeredList.isEmpty()) {
			for (RegisteredCourse registeredCourse : registeredList) {
				if (registeredCourse.getCourse().getCourseCode().compareTo(courseCode) == 0) return false; // ensure no repeat of course	
				Index indexRegistered = registeredCourse.getIndex();
					if (indexRegistered != null) {
						lessonListRegistered = indexRegistered.getLessonList();
						if (!lessonListRegistered.isEmpty()) {
							for (Lesson lessonRegistered : lessonListRegistered) {
								for (Lesson lesson : lessonList) {
									if (lesson.getLessonDay() == lessonRegistered.getLessonDay()) {
										int lessonStartHour = lesson.getLessonStart().get(Calendar.HOUR_OF_DAY);
										int lessonStartMin = lesson.getLessonStart().get(Calendar.MINUTE);
										int lessonEndHour = lesson.getLessonEnd().get(Calendar.HOUR_OF_DAY);
										int lessonEndMin = lesson.getLessonEnd().get(Calendar.MINUTE);
										int lessonRegisteredStartHour = lessonRegistered.getLessonStart().get(Calendar.HOUR_OF_DAY);
										int lessonRegisteredStartMin = lessonRegistered.getLessonStart().get(Calendar.MINUTE);
										int lessonRegisteredEndHour = lessonRegistered.getLessonEnd().get(Calendar.HOUR_OF_DAY);
										int lessonRegisteredEndMin = lessonRegistered.getLessonEnd().get(Calendar.MINUTE);
										if (lessonStartHour == lessonRegisteredStartHour) {
											if (lessonStartMin == lessonRegisteredStartMin) return false;
										}
										if (lessonEndHour == lessonRegisteredEndHour) {
											if (lessonEndMin == lessonRegisteredEndMin) return false;
										}
										if ((lessonStartHour < lessonRegisteredStartHour) && (lessonEndHour > lessonRegisteredEndHour)) return false;
										if ((lessonStartHour > lessonRegisteredStartHour) && (lessonEndHour < lessonRegisteredEndHour)) return false;
									}
								}
							}
						}
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
		if (!registeredList.isEmpty()) {
			for (RegisteredCourse registeredCourse : registeredList) {
				totalAU += registeredCourse.getCourse().getAU();
			}
		}
		return (totalAU <= 21); // can change number to fit maximum number of AUs
	}

	/**
	 * 
	 * @param notification
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void chooseNotification(int notificationChoice) throws ClassNotFoundException, IOException {
		this.student.setNotification(notificationChoice);
		System.out.println("Notification method set.\n");
		DataMgr.updateStudentList(this.student);
	}

	public int checkVacancies(String courseCode, int indexNum)
			throws EOFException, ClassNotFoundException, IOException {
		System.out.println("Course code: " + courseCode);
		System.out.println("Index number: " + indexNum);
		System.out.println("Number of vacancies: " + super.checkVacancies(courseCode, indexNum) + "\n");
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
	
	private void updateVacancy(String courseCode, int indexNum, int toAdd) throws EOFException, ClassNotFoundException, IOException {
		ArrayList<Course> courseList = DataMgr.readCourseList();
		for (Course course: courseList) {
			if (course.getCourseCode().compareTo(courseCode) == 0) {
				ArrayList<Index> indexList = course.getIndexList();
				for (Index index: indexList) {
					if (index.getIndexNum() == indexNum) {
						index.setVacancy(index.getVacancy() + toAdd);
						DataMgr.updateCourseList(course);
					}
				}
			}
		}
	}
}
