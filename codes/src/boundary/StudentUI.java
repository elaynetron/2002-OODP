package boundary;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import control.StudentMgr;
import entity.Student;

public class StudentUI extends CourseUI {

	private Scanner sc;
	private Student student;
	private StudentMgr studentMgr;
	private CourseUI courseUI;

	/**
	 * 
	 * @param sc
	 * @param student
	 * @throws IOException
	 * @throws ParseException
	 */
	public StudentUI(Scanner sc, Student student) throws ParseException, IOException {
		this.sc = sc;
		this.student = student;
		this.studentMgr = new StudentMgr(student);
		this.courseUI = new CourseUI(sc);
		displayStudentUI();
	}

	public void displayStudentUI() throws ParseException, IOException {
		int choice = 0;

		do {
			System.out.println("Welcome to STARS " + student.getFirstName());
			System.out.println("Please select: ");
			System.out.println("[1] Add course");
			System.out.println("[2] Drop course");
			System.out.println("[3] Check courses registered");
			System.out.println("[4] Change course index number");
			System.out.println("[5] Swap course index number");
			System.out.println("[6] Send notification");
			System.out.println("[7] Check vacancies available");
			System.out.println("[8] Quit Application");

			try {
				choice = Integer.parseInt(sc.nextLine());

				switch (choice) {
				case 1:
					addCourseUI();
					break;
				case 2:
					dropCourseUI();
					break;
				case 3:
					checkCoursesRegisteredUI();
					break;
				case 4:
					changeIndexNumUI();
					break;
				case 5:
					swapIndexNumUI();
					break;
				case 6:
					chooseNotificationUI();
					break;
				case 7:
					checkVacanciesUI();
					break;
				case 8:
					System.out.println("Quitting Application...\n");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid, please try again.\n");
				}
			} catch (Exception e) {
				System.out.println("Invalid, please try again.\n");
			}

		} while (choice != 9);
	}

	private void addCourseUI() throws ParseException, IOException, ClassNotFoundException {
		System.out.println("[1] Add course selected ");
		String courseCode = null;
		boolean validCourse = false;

		do {
			System.out.println("Enter course code: ");
			courseCode = sc.nextLine();
			validCourse = this.studentMgr.validateCourse(courseCode);
			if (!validCourse)
				System.out.println("Course does not exist, please try again.\n");
		} while (!validCourse);

		boolean exception = false;
		boolean validIndexNum = false;
		int indexNum = 0;
		do {
			System.out.println("Enter index number: ");
			try {
				indexNum = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid, please try again. ");
				exception = true;
			}
			if (!exception) { // input is valid (integer)
				validIndexNum = this.studentMgr.validateIndexNum(courseCode, indexNum);
			}
		} while (!validIndexNum);
		this.studentMgr.addCourse(courseCode, indexNum);
	}

	private void dropCourseUI() throws EOFException, ClassNotFoundException, IOException {
		System.out.println("[2] Drop course selected. ");
		String courseCode = null;
		boolean validCourse = false;

		do {
			System.out.println("Enter course code: ");
			courseCode = sc.nextLine();
			validCourse = this.studentMgr.validateCourse(courseCode);
			if (!validCourse)
				System.out.println("Course does not exist, please try again. ");
		} while (!validCourse);

		this.studentMgr.dropCourse(courseCode);
	}

	private void checkCoursesRegisteredUI() {
		System.out.println("[3] Check courses registered selected. ");
		this.studentMgr.checkCoursesRegistered();
	}

	private void changeIndexNumUI() throws EOFException, ClassNotFoundException, IOException {
		System.out.println("[4] Change course index number selected ");

		String courseCode = null;
		boolean validCourse = false;
		do {
			System.out.println("Enter course code: ");
			courseCode = sc.nextLine();
			validCourse = this.studentMgr.validateCourse(courseCode);
			if (!validCourse)
				System.out.println("Course does not exist, please try again. ");
		} while (!validCourse);

		boolean exception = false;
		boolean validIndexNum = false;
		int indexNum = 0;

		do {
			System.out.println("Enter index number: ");
			try {
				indexNum = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid, please try again. ");
				exception = true;
			}
			if (!exception) { // input is valid (integer)
				validIndexNum = this.studentMgr.validateIndexNum(courseCode, indexNum);
			}
		} while (!validIndexNum);

		this.studentMgr.changeIndexNum(courseCode, indexNum);
	}

	private void swapIndexNumUI() throws EOFException, ClassNotFoundException, IOException {
		System.out.println("[5] Swap course index number selected. ");

		String peerUsername = null;
		boolean validPeerUsername = false;
		do {
			System.out.println("Enter peer's username: ");
			peerUsername = sc.nextLine();
			validPeerUsername = this.studentMgr.isExistingStudent(peerUsername);
			if (!validPeerUsername)
				System.out.println("Student does not exist, please try again. ");
		} while (!validPeerUsername);
		Student peer = this.studentMgr.getStudent(peerUsername);

		String courseCode = null;
		boolean validCourse = false;
		do {
			System.out.println("Enter course code: ");
			courseCode = sc.nextLine();
			validCourse = this.studentMgr.validateCourse(courseCode);
			if (!validCourse)
				System.out.println("Course does not exist, please try again. ");
		} while (!validCourse);

		boolean exception = false;
		boolean validIndexNum = false;
		int selfIndexNum = 0;

		do {
			System.out.println("Enter your index number: ");
			try {
				selfIndexNum = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid, please try again. ");
				exception = true;
			}
			if (!exception) { // input is valid (integer)
				validIndexNum = this.studentMgr.validateIndexNum(courseCode, selfIndexNum);
			}
		} while (!validIndexNum);

		exception = false;
		validIndexNum = false;
		int peerIndexNum = 0;

		do {
			System.out.println("Enter peer's index number: ");
			try {
				peerIndexNum = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid, please try again. ");
				exception = true;
			}
			if (!exception) { // input is valid (integer)
				validIndexNum = this.studentMgr.validateIndexNum(courseCode, peerIndexNum);
			}
		} while (!validIndexNum);

		this.studentMgr.swapIndexNum(peer, courseCode, selfIndexNum, peerIndexNum);
	}

	public void chooseNotificationUI() throws ClassNotFoundException, IOException {
		System.out.println("[6] Send notification selected. ");
		System.out.println("Choose notification to send. ");
		System.out.println("[1] Send Email");
		System.out.println("[2] Send SMS");
		System.out.println("[3] Send Whatsapp");

		int choice = 0;
		do {
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid, please try again. ");
			}
		} while (choice < 1 | choice > 3);
		this.studentMgr.chooseNotification(choice);
	}

	@Override
	public void checkVacanciesUI() throws IOException, ClassNotFoundException {
		System.out.println("[7] Check vacancies available selected. ");

		String courseCode = null;
		boolean validCourse = false;
		do {
			System.out.println("Enter course code: ");
			courseCode = sc.nextLine();
			validCourse = this.studentMgr.validateCourse(courseCode);
			if (!validCourse)
				System.out.println("Course does not exist, please try again. ");
		} while (!validCourse);

		boolean exception;
		boolean validIndexNum = false;
		int indexNum = 0;
		do {
			exception = false;
			System.out.println("Enter index number: ");
			try {
				indexNum = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid, please try again. ");
				exception = true;
			}
			if (!exception) { // input is valid (integer)
				validIndexNum = this.studentMgr.validateIndexNum(courseCode, indexNum);
			}
		} while (!validIndexNum);
		this.studentMgr.checkVacancies(courseCode, indexNum);
	}

}
