package boundary;
import java.util.Scanner;

public class StudentUI extends CourseUI {

	private Scanner sc;
	private Student student;

	/**
	 * 
	 * @param sc
	 * @param student
	 */
	public void StudentUI(Scanner sc, Student student) {
		// TODO - implement StudentUI.StudentUI
		this.sc = sc;
		this.student = student;
		//displayStudentUI();
	}

	public void displayStudentUI() {
		// TODO - implement StudentUI.displayStudentUI
		int choice;
		do {
			System.out.println("Welcome to STARS " + student.getFirstName());
			System.out.println("Choose what you want to do. ");
			System.out.println("(1) Add course");
			System.out.println("(2) Drop course");
			System.out.println("(3) Check courses registered");
			System.out.println("(4) Change course index number");
			System.out.println("(5) Swap course index number");
			System.out.println("(6) Send notification");
			System.out.println("(7) Check vacancies available");
			System.out.println("(8) Exit");
			choice = sc.nextInt();
			
			switch(choice) {
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
				addCourseUI();
				break;
			case 5:
				addCourseUI();
				break;
			case 6:
				addCourseUI();
				break;
			case 7:
				checkVacanciesUI();
				break;
			}
			case 8:
				System.out.println("Exiting program...");
			
		}while(choice < 8);
	}

	private void addCourseUI() {
		// TODO - implement StudentUI.addCourseUI
		System.out.println("Add course selected. ");
		System.out.println("Enter course code: ");
		try{
			String courseCode = sc.nextLine();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		System.out.println("Enter index number: ");
		try{
			int indexNum = sc.nextInt();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		addCourse(courseCode,indexNum);
	}

	private void dropCourseUI() {
		// TODO - implement StudentUI.dropCourseUI
		System.out.println("Drop course selected. ");
		System.out.println("Enter course code: ");
		try{
			String courseCode = sc.nextLine();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		dropCourse(courseCode);
	}

	private void checkCoursesRegisteredUI() {
		// TODO - implement StudentUI.checkCoursesRegisteredUI
		System.out.println("Check courses registered selected. ");
		checkCoursesRegistered();
	}

	private void changeIndexNumUI() {
		// TODO - implement StudentUI.changeIndexNumUI
		System.out.println("Change course index number selected ");
		System.out.println("Enter course code: ");
		try{
			String courseCode = sc.nextLine();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		System.out.println("Enter index number: ");
		try{
			int indexNum = sc.nextInt();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		changeIndexNum(courseCode,indexNum);
	}

	private void swapIndexNumUI() {
		// TODO - implement StudentUI.swopIndexNumUI
		System.out.println("Swap course index number selected. ");
		System.out.println("Enter peer's matriculation number: ");
		try{
			String matricNum = sc.nextLine();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		System.out.println("Enter course code: ");
		try{
			String courseCode = sc.nextLine();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		System.out.println("Enter your own index number: ");
		try{
			int indexNum1 = sc.nextInt();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		System.out.println("Enter your peer's index number: ");
		try{
			int indexNum2 = sc.nextInt();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		Student peer = ;//get student from matric number method
		swapIndexNum(peer,courseCode,indexNum1,indexNum2);
	}

	public void chooseNotificationUI() {
		// TODO - implement StudentUI.chooseNotificationUI
		System.out.println("Send notification selected. ");
		System.out.println("Choose notification to send. ");
		System.out.println("(1) Send Email");
		System.out.println("(2) Send SMS");
		System.out.println("(3) Send Whatsapp");
		System.out.println("(4) Exit");
		choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			sendEmail(student,courseCode);
			System.out.println("Email sent");
			break;
		case 2:
			sendSMS(student,courseCode);
			System.out.println("SMS sent");
			break;
		case 3:
			sendWhatsapp(student,courseCode);
			System.out.println("Whatsapp sent");
			break;
		case 4:
			System.out.println("Exiting");
			break;
		}

	}
	
	public void checkVacanciesUI() {
		System.out.println("Enter course code: ");
		try{
			String courseCode = sc.nextLine();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		System.out.println("Enter index number: ");
		try{
			int indexNum = sc.nextInt();
		}
		catch (Exception e) {
			System.out.println("Invalid entry. ");
			displayStudentUI();
		}
		checkVacancies(courseCode,indexNum);
	}

}