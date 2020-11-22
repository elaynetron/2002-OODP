package boundary;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import control.AdminMgr;
import entity.Admin;

public class AdminUI {
    private Scanner sc;
    private Admin admin;
    public AdminUI(Scanner sc, Admin admin) throws ParseException, IOException {
        this.sc = sc;
        this.admin = admin;
        displayAdminUI();
    }

    public void displayAdminUI() throws ParseException, IOException{
        int choice = 0;

        AdminMgr adminMgr = new AdminMgr();
        System.out.format("Welcome, Admin %s", admin.getAdminName());
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
                        checkIndexVacancyUI();
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
        String startTime, endTime;
        System.out.println("Editing Student Access Period... ");
        System.out.print("Student Matric Number: ");
        matricNum = sc.nextLine();
        System.out.print("Start: ");
        startTime = sc.nextLine();
        System.out.print("End: ");
        endTime = sc.nextLine();
        AdminMgr.editStudentAccessPeriod(matricNum, startTime,endTime);
        System.out.format("New Period: %s to %s", startTime, endTime );
    }

    private void addStudentUI(){
        String username, password, firstName, lastName, matricNum, gender, nationality, mobileNum, email;
        System.out.println("Adding New Student... ");
        System.out.print("Student Username: ");
        username = sc.nextLine();
        System.out.print("Student Password: ");
        password = sc.nextLine();
        System.out.print("First Name: ");
        firstName = sc.nextLine();
        System.out.print("Last Name: ");
        lastName = sc.nextLine();
        System.out.print("Matric Number: ");
        matricNum = sc.nextLine();
        System.out.print("Gender: ");
        gender = sc.nextLine();
        System.out.print("Nationality: ");
        nationality = sc.nextLine();
        System.out.print("Mobile Number: ");
        mobileNum = sc.nextLine();
        System.out.print("Email: ");
        email = sc.nextLine();

        AdminMgr.addStudent(username, password, firstName, lastName, matricNum, gender, nationality, mobileNum, email);
        System.out.format("New Student %s Created", username);
    }
    private void deleteStudentUI(){
        String matricNum;
        System.out.println("Deleting Student... ");
        System.out.print("Matric number of student to be deleted: ");
        matricNum = sc.nextLine();
        AdminMgr.deleteStudent(matricNum);
        System.out.format("Student %s Deleted", matricNum);

    }

    private void printStudentListByCourseUI(){
        String courseCode;
        System.out.println("Printing Student List By Course... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        AdminMgr.printStudentListByCourse(courseCode);
    }

    private void printStudentListByIndexUI(){
        String courseCode, indexNum;
        System.out.println("Printing Student List By Index... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("Index Number: ");
        indexNum = sc.nextLine();
        AdminMgr.printStudentListByIndex(courseCode, indexNum);
    }

    private void addCourseUI(){
        String courseCode, courseName, school, courseType;
        //Calendar examDate = new Calendar();
        String examDate;
        int AU;
        System.out.println("Adding New Course... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("Course Name: ");
        courseName = sc.nextLine();
        System.out.print("AUs: ");
        AU = Integer.parseInt(sc.nextLine());
        System.out.print("School: ");
        school = sc.nextLine();
        System.out.print("Course Type: ");
        courseType = sc.nextLine();
        System.out.print("Exam Date");
        examDate = sc.nextLine();

        AdminMgr.addCourse(courseCode, courseName, AU, school, courseType, examDate);
        System.out.format("New Course %s Added", courseCode);
    }

    private void checkIndexVacancyUI(){
        String courseCode, indexNum;
        System.out.println("Checking Index Vacancy");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("Index Number: ");
        indexNum = sc.nextLine();
        AdminMgr.checkIndexVacancy(courseCode, indexNum);
    }

    private void updateCourseUI(){
        int choice = 0;
        String courseCode;
        System.out.println("Updating Course... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        AdminMgr AdminMgr = new AdminMgr();

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
        System.out.print("Old Course Code: ");
        oldCourseCode = sc.nextLine();
        System.out.print("New Course Code: ");
        newCourseCode = sc.nextLine();
        AdminMgr.updateCourseCode(oldCourseCode, newCourseCode);
        System.out.format("Course Code %s Updated to %s", oldCourseCode, newCourseCode);
    }

    private void updateCourseNameUI(){
        String courseCode, courseName;
        System.out.println("Updating Course Name... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("New Course Name: ");
        courseName = sc.nextLine();
        AdminMgr.updateCourseName(courseCode, courseName);
        System.out.format("Course %s/'s Name Changed to %s", courseCode, courseName);
    }

    private void updateAUUI(){
        String courseCode;
        int AU;
        System.out.println("Updating Course AU... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("New AU: ");
        AU = Integer.parseInt(sc.nextLine());
        AdminMgr.updateAU(courseCode, AU);
        System.out.format("Course %s/'s AU Changed to %d", courseCode, AU);
    }

    private void updateSchoolUI(){
        String courseCode, school;
        System.out.println("Updating School... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("New School: ");
        school = sc.nextLine();
        AdminMgr.updateCourseName(courseCode, school);
        System.out.format("Course %s/'s School Changed to %s", courseCode, school);
    }

    private void updateCourseTypeUI(){
        String courseCode, courseType;
        System.out.println("Updating Course Type... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("New Course Type: ");
        courseType = sc.nextLine();
        AdminMgr.updateCourseType(courseCode, courseType);
        System.out.format("Course %s/'s Type Changed to %s", courseCode, courseType);
    }

    private void updateExamDateUI(){
        String courseCode, examDate;
        System.out.println("Updating Exam Date... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("New Exam Date: ");
        examDate = sc.nextLine();
        AdminMgr.updateExamDate(courseCode, examDate);
        System.out.format("Course %s/'s Exam Date Changed to %s", courseCode, examDate);
    }

    private void addIndexUI(){
        String courseCode, indexNum;
        System.out.println("Adding Index... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("New Index: ");
        indexNum = sc.nextLine();
        AdminMgr.addIndex(courseCode, indexNum);
        System.out.format("Added Index %s to Course %s", indexNum, courseCode);
    }

    private void deleteIndexUI(){
        String courseCode, indexNum;
        System.out.println("Deleting Index... ");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("Index To Be Deleted: ");
        indexNum = sc.nextLine();
        AdminMgr.deletedIndex(courseCode, indexNum);
        System.out.format("Index %s Deleted", indexNum);
    }

    private void updateVacancyUI() {
        String courseCode, indexNum;
        int newVacancy;
        System.out.println("Updating Vacancy...");
        System.out.print("Course Code: ");
        courseCode = sc.nextLine();
        System.out.print("Index: ");
        indexNum = sc.nextLine();
        System.out.println("New Vacancy: ");
        newVacancy = Integer.parseInt(sc.nextLine());
        AdminMgr.updateVacancy(courseCode, indexNum, newVacancy);
        System.out.format("Vacancy of Index %s Is Updated to %d", indexNum, newVacancy);

    }

}

