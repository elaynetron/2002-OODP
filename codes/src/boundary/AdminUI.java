package boundary

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Calendar;

import control.AdminMgr;
import control.CourseMgr;
import entity.Admin;

public class AdminUI extends CourseUI{
    private Scanner sc;
    private Admin admin;
    public AdminUI(Scanner sc, Admin admin) throws ParseException, IOException {
        this.sc = sc;
        this.admin = admin;
        displayAdminUI();
    }

    public void displayAdminUI() throws ParseException, IOException{
        int choice = 0;


        System.out.format("Welcome, Admin %s", admin.getUsername());
        do {
            System.out.println("Please select:\n"
                    + "[1] Edit Student Access Period\n"
                    + "[2] Add New Student\n"
                    + "[3] Delete Student\n"
                    + "[4] Print Student List By Course\n"
                    + "[5] Print Student List By Index\n"
                    + "[6] Add Course\n"
                    + "[7] Check Index Vacancy\n"
                    + "[8] Update Course\n"
                    + "[9] Quit Application");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        editStudentAccessPeriodUI();
                        break;
                    case 2:
                        addStudentUI();
                        break;
                    case 3:
                        deleteStudentUI();
                        break;
                    case 4:
                        printStudentListByCourseUI();
                        break;
                    case 5:
                        printStudentListByIndexUI();
                        break;
                    case 6:
                        addCourseUI();
                    case 7:
                        checkVacanciesUI();
                    case 8:
                        updateCourseUI();
                    case 9:
                        System.out.println("Quitting Application...\n");
                        break;

                    default:
                        System.out.println("Invalid, please try again.\n");
                }
            }

            catch (Exception e) {
                System.out.println("Invalid, please try again.\n");
            }
        } while (choice != 8);

    }

    private void editStudentAccessPeriodUI(){
        String matricNum;
        int startYear = 0, startMonth = 0, startDay = 0, startHour = 0, startMinute = 0;
        Calendar startDate = Calendar.getInstance();
        int endYear = 0, endMonth = 0, endDay = 0, endHour = 0, endMinute = 0;
        Calendar endDate = Calendar.getInstance();
        System.out.println("Editing Student Access Period... ");
        boolean validMatric;
        do {
            System.out.print("Student Matric Number: ");
            matricNum = sc.nextLine();
            validMatric = AdminMgr.validateMatric(matricNum);
            if (!validMatric) System.out.println("Student does not exist, please try again. ");
        } while (!validMatric);
        System.out.print("Access Period Start: ");
        boolean validStartYear;
        do {
            System.out.print("Year: ");
            try{
                startYear = Integer.parseInt(sc.nextLine());
                validStartYear = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartYear = false;
            }
        } while (!validStartYear);
        boolean validStartMonth;
        do {
            System.out.print("Month: ");
            try{
                startMonth = Integer.parseInt(sc.nextLine());
                validStartMonth = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartMonth = false;
            }
        } while (!validStartMonth);
        boolean validStartDay;
        do {
            System.out.print("Day: ");
            try{
                startDay = Integer.parseInt(sc.nextLine());
                validStartDay = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartDay = false;
            }
        } while (!validStartDay);
        boolean validStartHour;
        do {
            System.out.print("Hour: ");
            try{
                startHour = Integer.parseInt(sc.nextLine());
                validStartHour = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartHour = false;
            }
        } while (!validStartHour);
        boolean validStartMinute;
        do {
            System.out.print("Minute: ");
            try{
                startMinute = Integer.parseInt(sc.nextLine());
                validStartMinute = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartMinute = false;
            }
        } while (!validStartMinute);
        startDate.set(startYear, startMonth, startDay, startHour, startMinute);

        System.out.print("Access Period End: ");
        boolean validEndYear;
        do {
            System.out.print("Year: ");
            try{
                endYear = Integer.parseInt(sc.nextLine());
                validEndYear = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndYear = false;
            }
        } while (!validEndYear);
        boolean validEndMonth;
        do {
            System.out.print("Month: ");
            try{
                endMonth = Integer.parseInt(sc.nextLine());
                validEndMonth = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndMonth = false;
            }
        } while (!validEndMonth);
        boolean validEndDay;
        do {
            System.out.print("Day: ");
            try{
                endDay = Integer.parseInt(sc.nextLine());
                validEndDay = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndDay = false;
            }
        } while (!validEndDay);
        boolean validEndHour;
        do {
            System.out.print("Hour: ");
            try{
                endHour = Integer.parseInt(sc.nextLine());
                validEndHour = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndHour = false;
            }
        } while (!validEndHour);
        boolean validEndMinute;
        do {
            System.out.print("Minute: ");
            try{
                endMinute = Integer.parseInt(sc.nextLine());
                validEndMinute = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndMinute = false;
            }
        } while (!validEndMinute);
        endDate.set(endYear, endMonth, endDay, endHour, endMinute);
        AdminMgr.editStudentAccessPeriod(matricNum, startDate, endDate);
        System.out.format("New Period: %s to %s", startDate, endDate );
    }

    private void addStudentUI(){
        String username, password, firstName, lastName, matricNum, gender, nationality, mobileNum, email;
        int startYear = 0, startMonth = 0, startDay = 0, startHour = 0, startMinute = 0;
        Calendar startDate = Calendar.getInstance();
        int endYear = 0, endMonth = 0, endDay = 0, endHour = 0, endMinute = 0;
        Calendar endDate = Calendar.getInstance();
        System.out.println("Adding New Student... ");
        System.out.print("Student Username: ");
        username = sc.nextLine();
        System.out.print("Student Password: ");
        password = sc.nextLine();
        System.out.print("First Name: ");
        firstName = sc.nextLine();
        System.out.print("Last Name: ");
        lastName = sc.nextLine();
        boolean existMatric;
        do {
            System.out.print("Matric Number: ");
            matricNum = sc.nextLine();
            existMatric = AdminMgr.validateMatric(matricNum);
            if (existMatric) System.out.println("Matric Number already exists, please try again. ");
        } while (existMatric);
        System.out.print("Gender: ");
        gender = sc.nextLine();
        System.out.print("Nationality: ");
        nationality = sc.nextLine();
        System.out.print("Mobile Number: ");
        mobileNum = sc.nextLine();
        System.out.print("Email: ");
        email = sc.nextLine();

        System.out.print("Access Period Start: ");
        boolean validStartYear;
        do {
            System.out.print("Year: ");
            try{
                startYear = Integer.parseInt(sc.nextLine());
                validStartYear = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartYear = false;
            }
        } while (!validStartYear);
        boolean validStartMonth;
        do {
            System.out.print("Month: ");
            try{
                startMonth = Integer.parseInt(sc.nextLine());
                validStartMonth = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartMonth = false;
            }
        } while (!validStartMonth);
        boolean validStartDay;
        do {
            System.out.print("Day: ");
            try{
                startDay = Integer.parseInt(sc.nextLine());
                validStartDay = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartDay = false;
            }
        } while (!validStartDay);
        boolean validStartHour;
        do {
            System.out.print("Hour: ");
            try{
                startHour = Integer.parseInt(sc.nextLine());
                validStartHour = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartHour = false;
            }
        } while (!validStartHour);
        boolean validStartMinute;
        do {
            System.out.print("Minute: ");
            try{
                startMinute = Integer.parseInt(sc.nextLine());
                validStartMinute = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validStartMinute = false;
            }
        } while (!validStartMinute);
        startDate.set(startYear, startMonth, startDay, startHour, startMinute);

        System.out.print("Access Period End: ");
        boolean validEndYear;
        do {
            System.out.print("Year: ");
            try{
                endYear = Integer.parseInt(sc.nextLine());
                validEndYear = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndYear = false;
            }
        } while (!validEndYear);
        boolean validEndMonth;
        do {
            System.out.print("Month: ");
            try{
                endMonth = Integer.parseInt(sc.nextLine());
                validEndMonth = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndMonth = false;
            }
        } while (!validEndMonth);
        boolean validEndDay;
        do {
            System.out.print("Day: ");
            try{
                endDay = Integer.parseInt(sc.nextLine());
                validEndDay = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndDay = false;
            }
        } while (!validEndDay);
        boolean validEndHour;
        do {
            System.out.print("Hour: ");
            try{
                endHour = Integer.parseInt(sc.nextLine());
                validEndHour = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndHour = false;
            }
        } while (!validEndHour);
        boolean validEndMinute;
        do {
            System.out.print("Minute: ");
            try{
                endMinute = Integer.parseInt(sc.nextLine());
                validEndMinute = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validEndMinute = false;
            }
        } while (!validEndMinute);
        endDate.set(endYear, endMonth, endDay, endHour, endMinute);

        AdminMgr.addStudent(username, password, firstName, lastName, matricNum, gender, nationality, mobileNum, email, startDate, endDate);
        System.out.format("New Student %s Created", username);
    }
    private void deleteStudentUI(){
        String matricNum;
        System.out.println("Deleting Student... ");
        boolean validMatric;
        do {
            System.out.print("Student Matric Number: ");
            matricNum = sc.nextLine();
            validMatric = AdminMgr.validateMatric(matricNum);
            if (!validMatric) System.out.println("Student does not exist, please try again. ");
        } while (!validMatric);
        AdminMgr.deleteStudent(matricNum);
        System.out.format("Student %s Deleted", matricNum);

    }

    private void printStudentListByCourseUI(){
        String courseCode;
        System.out.println("Printing Student List By Course... ");
        boolean validCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        AdminMgr.printStudentListByCourse(courseCode);
    }

    private void printStudentListByIndexUI(){
        String courseCode;
        int indexNum=0;
        System.out.println("Printing Student List By Index... ");
        boolean validCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        boolean exception;
        boolean validIndexNum = false;
        do {
            System.out.print("Index: ");
            try{
                indexNum = Integer.parseInt(sc.nextLine());
                exception = false;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                exception = true;
            }
            if (!exception) { // input is valid (integer)
                validIndexNum = CourseMgr.validateIndexNum(courseCode, indexNum);
                if (!validIndexNum) System.out.println("Index does not exist, please try again. ");
            }
        } while (!validIndexNum);
        AdminMgr.printStudentListByIndex(courseCode, indexNum);
    }

    private void addCourseUI(){
        String courseCode, courseName, school, courseType;
        int AU = 0;
        int examDateYear = 0, examDateMonth = 0, examDateDay = 0, examDateHour = 0, examDateMinute = 0;
        Calendar examDate = Calendar.getInstance();
        System.out.println("Adding New Course... ");
        boolean existCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            existCourse = CourseMgr.validateCourse(courseCode);
            if (existCourse) System.out.println("Course code already exists, please try again. ");
        } while (existCourse);
        System.out.print("Course Name: ");
        courseName = sc.nextLine();
        boolean validAU;
        do {
            System.out.print("AU: ");
            try{
                AU = Integer.parseInt(sc.nextLine());
                validAU = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validAU = false;
            }
        } while (!validAU);
        System.out.print("School: ");
        school = sc.nextLine();
        System.out.print("Course Type: ");
        courseType = sc.nextLine();

        System.out.println("Exam Date");
        boolean validYear;
        do {
            System.out.print("Year: ");
            try{
                examDateYear = Integer.parseInt(sc.nextLine());
                validYear = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validYear = false;
            }
        } while (!validYear);
        boolean validMonth;
        do {
            System.out.print("Month: ");
            try{
                examDateMonth = Integer.parseInt(sc.nextLine());
                validMonth = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validMonth = false;
            }
        } while (!validMonth);
        boolean validDay;
        do {
            System.out.print("Day: ");
            try{
                examDateDay = Integer.parseInt(sc.nextLine());
                validDay = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validDay = false;
            }
        } while (!validDay);
        boolean validHour;
        do {
            System.out.print("Hour: ");
            try{
                examDateHour = Integer.parseInt(sc.nextLine());
                validHour = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validHour = false;
            }
        } while (!validHour);
        boolean validMinute;
        do {
            System.out.print("Minute: ");
            try{
                examDateMinute = Integer.parseInt(sc.nextLine());
                validMinute = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validMinute = false;
            }
        } while (!validMinute);
        examDate.set(examDateYear, examDateMonth, examDateDay, examDateHour, examDateMinute);

        AdminMgr.addCourse(courseCode, courseName, AU, school, courseType, examDate);
        System.out.format("New Course %s Added", courseCode);
    }

    private void updateCourseUI(){
        int choice = 0;
        System.out.println("Updating Course... ");


        do {
            System.out.println("Please select:\n"
                    + "[1] Update Course Code\n"
                    + "[2] Update Course Name\n"
                    + "[3] Update AU\n"
                    + "[4] Update School\n"
                    + "[5] Update Course Type\n"
                    + "[6] Update Exam Date\n"
                    + "[7] Add Index\n"
                    + "[8] Delete Index\n"
                    + "[9] Update Vacancy\n"
                    + "[0] Quit Application");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        updateCourseCodeUI();
                        break;
                    case 2:
                        updateCourseNameUI();
                        break;
                    case 3:
                        updateAUUI();
                        break;
                    case 4:
                        updateSchoolUI();
                        break;
                    case 5:
                        updateCourseTypeUI();
                        break;
                    case 6:
                        updateExamDateUI();
                        break;
                    case 7:
                        addIndexUI();
                        break;
                    case 8:
                        deleteIndexUI();
                        break;
                    case 9:
                        updateVacancyUI();
                        break;
                    case 0:
                        System.out.println("Quitting Application...\n");
                        break;

                    default:
                        System.out.println("Invalid, please try again.\n");
                }
            }

            catch (Exception e) {
                System.out.println("Invalid, please try again.\n");
            }
        } while (choice != 0);


    }


    private void updateCourseCodeUI(){
        String oldCourseCode, newCourseCode;
        System.out.println("Updating Course Code...");
        boolean validCourse;
        do {
            System.out.print("Old Course code: ");
            oldCourseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(oldCourseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        boolean existCourse;
        do {
            System.out.print("New Course code: ");
            newCourseCode = sc.nextLine();
            existCourse = CourseMgr.validateCourse(newCourseCode);
            if (existCourse) System.out.println("Course code already exists, please try again. ");
        } while (existCourse);
        AdminMgr.updateCourseCode(oldCourseCode, newCourseCode);
        System.out.format("Course Code %s Updated to %s", oldCourseCode, newCourseCode);
    }

    private void updateCourseNameUI(){
        String courseCode, courseName;
        System.out.println("Updating Course Name... ");
        boolean validCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        System.out.print("New Course Name: ");
        courseName = sc.nextLine();
        AdminMgr.updateCourseName(courseCode, courseName);
        System.out.format("Course %s/'s Name Changed to %s", courseCode, courseName);
    }

    private void updateAUUI(){
        String courseCode;
        int AU=0;
        System.out.println("Updating Course AU... ");
        boolean validCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        boolean validAU;
        do {
            System.out.print("New AU: ");
            try{
                AU = Integer.parseInt(sc.nextLine());
                validAU = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validAU = false;
            }
        } while (!validAU);
        AdminMgr.updateAU(courseCode, AU);
        System.out.format("Course %s/'s AU Changed to %d", courseCode, AU);
    }

    private void updateSchoolUI(){
        String courseCode, school;
        System.out.println("Updating School... ");
        boolean validCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        System.out.print("New School: ");
        school = sc.nextLine();
        AdminMgr.updateSchool(courseCode, school);
        System.out.format("Course %s/'s School Changed to %s", courseCode, school);
    }

    private void updateCourseTypeUI(){
        String courseCode, courseType;
        System.out.println("Updating Course Type... ");
        boolean validCourse;
        do {
            System.out.println("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        System.out.print("New Course Type: ");
        courseType = sc.nextLine();
        AdminMgr.updateCourseType(courseCode, courseType);
        System.out.format("Course %s/'s Type Changed to %s", courseCode, courseType);
    }

    private void updateExamDateUI(){
        String courseCode;
        int examDateYear = 0, examDateMonth = 0, examDateDay = 0, examDateHour = 0, examDateMinute = 0;
        Calendar examDate = Calendar.getInstance();
        System.out.println("Updating Exam Date... ");
        boolean validCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        System.out.println("New Exam Date: ");
        boolean validYear;
        do {
            System.out.print("Year: ");
            try{
                examDateYear = Integer.parseInt(sc.nextLine());
                validYear = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validYear = false;
            }
        } while (!validYear);
        boolean validMonth;
        do {
            System.out.print("Month: ");
            try{
                examDateMonth = Integer.parseInt(sc.nextLine());
                validMonth = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validMonth = false;
            }
        } while (!validMonth);
        boolean validDay;
        do {
            System.out.print("Day: ");
            try{
                examDateDay = Integer.parseInt(sc.nextLine());
                validDay = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validDay = false;
            }
        } while (!validDay);
        boolean validHour;
        do {
            System.out.print("Hour: ");
            try{
                examDateHour = Integer.parseInt(sc.nextLine());
                validHour = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validHour = false;
            }
        } while (!validHour);
        boolean validMinute;
        do {
            System.out.print("Minute: ");
            try{
                examDateMinute = Integer.parseInt(sc.nextLine());
                validMinute = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validMinute = false;
            }
        } while (!validMinute);

        examDate.set(examDateYear, examDateMonth, examDateDay, examDateHour, examDateMinute);
        AdminMgr.updateExamDate(courseCode, examDate);
        System.out.format("Course %s/'s Exam Date Changed to %s", courseCode, examDate);
    }

    private void addIndexUI(){
        String courseCode;
        int indexNum=0;
        System.out.println("Adding Index... ");
        boolean validCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        boolean exception;
        boolean validIndexNum = false;
        do {
            System.out.print("Index: ");
            try{
                indexNum = Integer.parseInt(sc.nextLine());
                exception = false;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                exception = true;
            }
            if (!exception) { // input is valid (integer)
                validIndexNum = CourseMgr.validateIndexNum(courseCode, indexNum);
                if (!validIndexNum) System.out.println("Index does not exist, please try again. ");
            }
        } while (!validIndexNum);

        AdminMgr.addIndex(courseCode, indexNum);
        System.out.format("Added Index %d to Course %s", indexNum, courseCode);
    }

    private void deleteIndexUI(){
        String courseCode;
        int indexNum=0;
        System.out.println("Deleting Index... ");
        boolean validCourse;
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        boolean exception;
        boolean validIndexNum = false;
        do {
            System.out.print("Index: ");
            try{
                indexNum = Integer.parseInt(sc.nextLine());
                exception = false;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                exception = true;
            }
            if (!exception) { // input is valid (integer)
                validIndexNum = CourseMgr.validateIndexNum(courseCode, indexNum);
                if (!validIndexNum) System.out.println("Index does not exist, please try again. ");
            }
        } while (!validIndexNum);

        AdminMgr.deleteIndex(courseCode, indexNum);
        System.out.format("Index %d Deleted", indexNum);
    }

    private void updateVacancyUI() {
        String courseCode;
        int indexNum=0;
        int newVacancy=0;
        boolean validCourse;
        System.out.println("Updating Vacancy...");
        do {
            System.out.print("Course code: ");
            courseCode = sc.nextLine();
            validCourse = CourseMgr.validateCourse(courseCode);
            if (!validCourse) System.out.println("Course does not exist, please try again. ");
        } while (!validCourse);
        boolean exception;
        boolean validIndexNum = false;
        do {
            System.out.print("Index: ");
            try{
                indexNum = Integer.parseInt(sc.nextLine());
                exception = false;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                exception = true;
            }
            if (!exception) { // input is valid (integer)
                validIndexNum = CourseMgr.validateIndexNum(courseCode, indexNum);
                if (!validIndexNum) System.out.println("Index does not exist, please try again. ");
            }
        } while (!validIndexNum);
        boolean validVacancy;
        do {
            System.out.print("New Vacancy: ");
            try{
                newVacancy = Integer.parseInt(sc.nextLine());
                validVacancy = true;
            }
            catch (Exception e) {
                System.out.println("Invalid, please try again. ");
                validVacancy = false;
            }
        } while (!validVacancy);

        AdminMgr.updateVacancy(courseCode, indexNum, newVacancy);
        System.out.format("Vacancy of Index %d Is Updated to %d", indexNum, newVacancy);

    }



}

