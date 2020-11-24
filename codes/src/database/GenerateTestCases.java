package database;

import java.io.*;
import java.util.*;

import entity.Admin;
import entity.Course;
import entity.Index;
import entity.Lesson;
import entity.Student;

public class GenerateTestCases {
	public static void main(String[] args) throws IOException {
		int numOfVacancies = 10;

		Scanner reader = null;
		try {
			File file = new File("db/TestCases.txt");
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}

		// Student classes
		ObjectOutputStream objectOut = null;
		try {
			FileOutputStream fileOut = new FileOutputStream("db/studentList.dat");
			objectOut = new ObjectOutputStream(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		}

		reader.nextLine(); // skip first line
		String oneline = reader.nextLine();
		;
		String[] onelineArray;
		Student student;
		String startDateTime, endDateTime;
		int year, month, date, hour, minute;
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		while (!oneline.isEmpty()) {
			onelineArray = oneline.split(">");
			startDateTime = onelineArray[10];
			date = Integer.parseInt(startDateTime.substring(0, 2));
			month = Integer.parseInt(startDateTime.substring(2, 4)) -1; // format due to Gregorian calendar
			year = Integer.parseInt(startDateTime.substring(4, 8));
			hour = Integer.parseInt(startDateTime.substring(9, 11));
			minute = Integer.parseInt(startDateTime.substring(12));
			start = Calendar.getInstance();
			start.set(year, month, date, hour, minute, 0);

			endDateTime = onelineArray[11];
			date = Integer.parseInt(endDateTime.substring(0, 2));
			month = Integer.parseInt(endDateTime.substring(2, 4)) -1; // format due to Gregorian calendar
			year = Integer.parseInt(endDateTime.substring(4, 8));
			hour = Integer.parseInt(endDateTime.substring(9, 11));
			minute = Integer.parseInt(endDateTime.substring(12));
			end = Calendar.getInstance();
			end.set(year, month, date, hour, minute, 0);

			student = new Student(onelineArray[1], onelineArray[2], onelineArray[3], onelineArray[4], onelineArray[5],
					onelineArray[6].charAt(0), onelineArray[7], Integer.parseInt(onelineArray[8]), onelineArray[9],
					start, end);
			objectOut.writeObject((Object) student);
			oneline = reader.nextLine();
		} // loop until reach empty line
		objectOut.close();

		// Course classes
		try {
			FileOutputStream fileOut = new FileOutputStream("db/courseList.dat");
			objectOut = new ObjectOutputStream(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 3; i++) { // skip next 3 lines
			reader.nextLine();
		}

		String[] courseInfo, lessonInfo;
		Course course;
		Calendar examDate = Calendar.getInstance();
		ArrayList<Index> indexList;
		int currentIndexNum;
		ArrayList<Lesson> lessonList;
		String remarks;
		do {
			indexList = new ArrayList<Index>(); // reset
			lessonList = new ArrayList<Lesson>(); // reset
			courseInfo = reader.nextLine().split(">");
			currentIndexNum = Integer.parseInt(reader.nextLine());

			oneline = reader.nextLine();
			while (!oneline.isEmpty()) {
				if (oneline.compareTo(";") == 0) { // move on to next Index
					indexList.add(new Index(currentIndexNum, numOfVacancies, null, lessonList));
					lessonList = new ArrayList<Lesson>(); // reset
					currentIndexNum = Integer.parseInt(reader.nextLine());
				} else if (!oneline.isEmpty()) { // Lesson Information
					lessonInfo = oneline.split(">");
					start.clear(); // reset calendar values
					start.set(Calendar.HOUR, Integer.parseInt(lessonInfo[4].substring(0, 2)));
					start.set(Calendar.MINUTE, Integer.parseInt(lessonInfo[4].substring(3)));
					end.clear(); // reset calendar values
					end.set(Calendar.HOUR, Integer.parseInt(lessonInfo[5].substring(0, 2)));
					end.set(Calendar.MINUTE, Integer.parseInt(lessonInfo[5].substring(3)));
					try {
						remarks = lessonInfo[6];
					} catch (Exception e) {
						remarks = "";
					}
					lessonList.add(new Lesson(lessonInfo[0], lessonInfo[1], Integer.parseInt(lessonInfo[2]),
							lessonInfo[3], start, end, remarks));
				}
				oneline = reader.nextLine();
			}
			indexList.add(new Index(currentIndexNum, numOfVacancies, null, lessonList));

			examDate.clear(); // reset
			examDate.set(Integer.parseInt(courseInfo[5].substring(4)), Integer.parseInt(courseInfo[5].substring(2, 4)),
					Integer.parseInt(courseInfo[5].substring(0, 2)));
			course = new Course(courseInfo[0], courseInfo[1], Integer.parseInt(courseInfo[2]), courseInfo[3],
					courseInfo[4], examDate, indexList);
			objectOut.writeObject((Course) course);
		} while (reader.hasNextLine());

		objectOut.close();
		reader.close();

		// Admin class
		try {
			FileOutputStream fileOut = new FileOutputStream("db/adminList.dat");
			objectOut = new ObjectOutputStream(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Admin admin = new Admin("ETAN074", "ADMINPW");
		objectOut.writeObject((Admin) admin);
		objectOut.close();
	}
}
