package entity;

public class RegisteredCourse {
	private Student student;
	private Course course;
	private Index index;
	private String status;

	public RegisteredCourse(Student student, Course course, Index index, String status) {
		this.student = student;
		this.course = course;
		this.index = index;
		this.status = status;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setIndex(Index index) {
		this.index = index;
	}

	public Index getIndex() {
		return null;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}
