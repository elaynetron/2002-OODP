package boundary;

import java.io.*;
import java.util.*;

import control.CourseMgr;
import entity.Student;

public class CourseUI {
	private Scanner sc;
	private CourseMgr courseMgr;

	public CourseUI() {
	}

	public CourseUI(Scanner sc) {
		this.sc = sc;
		courseMgr = new CourseMgr();
		// displayCourseUI();
	}

	protected void displayCourseUI() {
		int choice = 0;
		do {
			System.out.println("Please select:\n" + "[1] Check vacancies\n" + "[2] Validate course\n"
					+ "[3] Validate Index number\n" + "[4] Quit Application");
			try {
				choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1: // Check vacancies
					checkVacanciesUI();
					break;
				case 2:
					validateCourseUI();
					break;
				case 3:
					validateIndexNumUI();
					break;

				case 4:
					System.out.println("Quitting Application...\n");
					System.exit(0);
					break;

				default:
					System.out.println("Invalid, please try again.\n");
				}
			}

			catch (Exception e) {
				System.out.println("Invalid, please try again.\n");
			}
		} while (choice != 4);
	}

	protected void validateIndexNumUI() throws EOFException, ClassNotFoundException, IOException {
		int indexNumber = 0;
		String courseCode;
		boolean valid = false;
		System.out.println("Validating index number...\n");
		do {
			System.out.print("Course code: ");
			courseCode = sc.nextLine();
			valid = courseMgr.validateCourse(courseCode);
		} while (!valid);

		valid = false;
		boolean exception;
		do {
			System.out.print("Index number: ");
			exception = false;
			try {
				indexNumber = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid, please try again.\n");
				exception = true;
			}
			if (!exception) {
				valid = courseMgr.validateIndexNum(courseCode, indexNumber);
			}
		} while (!valid);
		System.out.println("Course and Index Number is valid.");
	}

	protected void validateCourseUI() throws EOFException, ClassNotFoundException, IOException {
		String courseCode;
		boolean valid = false;
		System.out.println("Validating course...\n");
		do {
			System.out.print("Course code: ");
			courseCode = sc.nextLine();
			valid = courseMgr.validateCourse(courseCode);
		} while (!valid);
		System.out.println("Course is valid.");
	}

	protected void checkVacanciesUI() throws EOFException, ClassNotFoundException, IOException {
		int indexNumber = 0;
		String courseCode;
		boolean valid = false;
		System.out.println("Check vacancies...");
		do {
			System.out.print("Course code: ");
			courseCode = sc.nextLine();
			valid = courseMgr.validateCourse(courseCode);
			if (!valid)
				System.out.println("Invalid course code.");
		} while (!valid);

		valid = false;
		boolean exception;
		do {
			System.out.print("Index number: ");
			exception = false;
			try {
				indexNumber = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid, please try again.\n");
				exception = true;
			}
			if (!exception) {
				valid = courseMgr.validateIndexNum(courseCode, indexNumber);
				if (!valid)
					System.out.println("Invalid index number.");
			}
		} while (!valid);
		System.out.println("Vacancies: " + courseMgr.checkVacancies(courseCode, indexNumber));
	}
}
