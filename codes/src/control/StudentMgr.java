package control;
public class StudentMgr extends CourseMgr {

	private Student student;

	/**
	 * 
	 * @param student
	 */
	public void StudentMgr(Student student) {
		// TODO - implement StudentMgr.StudentMgr
		this.student = student;
	}

	/**
	 * 
	 * @param courseCode
	 * @param indexNum
	 */
	public void addCourse(String courseCode, int indexNum) {
		// TODO - implement StudentMgr.addCourse
		int vacancy = super.checkVacancies(courseCode,indexNum);
		if (vacancy > 0 && validateCourse(courseCode,indexNum) && validateIndexNum(courseCode,indexNum)) {
			Course c = ;//method to get course from coursecode?
			Index i = ;//method to get index frmo indexnum int
			RegisteredCourse toRegister = new RegisteredCourse(this.student,c,i,"Registered");
			ArrayList<RegisteredCourse> toUpdate = student.getCoursesRegistered();
			toUpdate.add(toRegister);
			System.out.println("Course successfully registered.");
		}
		else
			System.out.println("Failed to registered for course. Please try again.");
			
	}

	/**
	 * 
	 * @param courseCode
	 * @param indexNum
	 */
	private void updateWaitList(String courseCode, int indexNum) {
		// TODO - implement StudentMgr.updateWaitList
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param courseCode
	 */
	public void dropCourse(String courseCode) {
		// TODO - implement StudentMgr.dropCourse
		ArrayList<RegisteredCourse> toUpdate = student.getCoursesRegistered();
		Course c = ;//method to get course from coursecode
		if (toUpdate.contains(c)) {
			int index = toUpdate.indexOf(c);
			toUpdate.remove(index);
			System.out.println("Course dropped successfully.");
		}
		else
			System.out.println("You are not registered for the course!");
			
	}

	public void checkCoursesRegistered() {
		// TODO - implement StudentMgr.checkCoursesRegistered
		ArrayList<RegisteredCourse> toUpdate = student.getCoursesRegistered();
		for(int i = 0; i < toUpdate.size(); i++) {
			RegisteredCourse c = toUpdate.get(i);
			Index index = c.getIndex(i);
			Course CourseToPrint = c.getCourse();
			System.out.println(CourseToPrint.getCourseCode(),CourseToPrint.getCourseName(),index.getIndexNum(),index.getTutorialGrp()); // ??? Implement printing details from coursemgr?? LessonLIst?
		}
	}

	/**
	 * 
	 * @param courseCode
	 * @param indexNum
	 */
	public void changeIndexNum(String courseCode, int indexNum) {
		// TODO - implement StudentMgr.changeIndexNum
		//check wheter index number valid first!!
		ArrayList<RegisteredCourse> toUpdate = student.getCoursesRegistered();
		Course c = ;//method to get course from coursecode
		if (toUpdate.contains(c)) {
			dropCourse(courseCode);
			addCourse(courseCode,indexNum);
			System.out.println("Index number changed succesfully.");
		}
		else
			System.out.println("Invalid request.");

	/**
	 * 
	 * @param peer
	 * @param courseCode
	 * @param selfIndexNum
	 * @param peerIndexNum
	 */
	public void swapIndexNum(Student peer, String courseCode, int selfIndexNum, int peerIndexNum) {
		// TODO - implement StudentMgr.swopIndexNum
		Student self = this.student;
		Course toSwap = ;// course from coursecode
		ArrayList<RegisteredCourse> selfCourses = self.getCoursesRegistered();
		ArrayList<RegisteredCourse> peerCourses = peer.getCoursesRegistered();
		if (selfCourses.contains(toSwap) && peerCourses.contains(toSwap)) {
			self.dropCourse(courseCode);
			peer.dropCourse(courseCode);
			self.addCourse(courseCode,peerIndexNum);
			peer.addCourse(courseCode,selfIndexNum);
			System.out.println("Index number successfully swapped.");
		}
		else 
			System.out.println("Invalid request.");
	}

	/**
	 * 
	 * @param courseCode
	 * @param indexNum
	 */
	private Boolean checkClash(String courseCode, int indexNum) {
		// TODO - implement StudentMgr.checkClash
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param notification
	 */
	public void chooseNotification(int notification) {//not sure about this part..
		// TODO - implement StudentMgr.chooseNotification
		
	}
	
	public void checkVacancies(String courseCode, int indexNum) {
		System.out.println("Course code: " + courseCode);
		System.out.println("Index number: " + indexNum);
		System.out.println("Number of vacancies: " + super.checkVacancies(courseCode,indexNum));
	}

}