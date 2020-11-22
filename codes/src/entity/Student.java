package entity;

public class Student extends User {

	private String firstName;
	private String lastName;
	private String matricNum;
	private char gender;
	private String nationality;
	private int mobileNum;
	private String email;
	private Calendar start;
	private Calendar end;
	private int notification;
	private ArrayList<RegisteredCourse> coursesRegistered;

	/**
	 * 
	 * @param userName
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param matricNum
	 * @param gender
	 * @param nationality
	 * @param mobileNum
	 * @param email
	 * @param start
	 * @param end
	 */
	public void Student(String userName, String password, String firstName, String lastName, String matricNum, char gender, String nationality, int mobileNum, String email, Calendar start, Calendar end) {
		// TODO - implement Student.Student
		super(userName,password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.matricNum = matricNum;
		this.gender = gender;
		this.nationality = nationality;
		this.mobileNum = mobileNum;
		this.email = email;
		this.start = start;
		this.end = end;
		this.notification = 0; // off by default
		this.coursesRegistered = new ArrayList<RegisteredCourse>(); // Create empty arraylist for courses
		
		
	}

	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMatricNum() {
		return this.matricNum;
	}

	/**
	 * 
	 * @param matricNum
	 */
	public void setMatricNum(String matricNum) {
		this.matricNum = matricNum;
	}

	public char getGender() {
		return this.gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return this.nationality;
	}

	/**
	 * 
	 * @param nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getMobileNum() {
		return this.mobileNum;
	}

	/**
	 * 
	 * @param mobileNum
	 */
	public void setMobileNum(int mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getStart() {
		return this.start;
	}

	/**
	 * 
	 * @param start
	 */
	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return this.end;
	}

	/**
	 * 
	 * @param end
	 */
	public void setEnd(Calendar end) {
		this.end = end;
	}

	public int getNotification() {
		return this.notification;
	}

	/**
	 * 
	 * @param notification
	 */
	public void setNotification(int notification) {
		this.notification = notification;
	}

	public ArrayList<RegisteredCourse> getCoursesRegistered() {
		return this.coursesRegistered;
	}

	/**
	 * 
	 * @param coursesRegistered
	 */
	public void setCoursesRegistered(ArrayList<RegisteredCourse> coursesRegistered) {
		this.coursesRegistered = coursesRegistered;
	}

}