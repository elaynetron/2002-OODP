package boundary;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class MainUI {
	private static Scanner sc;

	public static void main(String[] args) throws IOException, ParseException {
		sc = new Scanner(System.in);
		int choice = 0;
		// Can add MainUI functions other than login for future extensions here
		do {
			System.out.println("Welcome to STARS Planner!\n" + "Please select:\n" + "[1] Login as Admin/Student\n"
					+ "[2] Quit Application");
			try {
				choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1: // Login
					new LoginUI(sc);
					break;

				case 2:
					System.out.println("Quitting Application...\n");
					break;

				default:
					System.out.println("Invalid, please try again.\n");
				}
			}

			catch (Exception e) {
				System.out.println("Invalid, please try again.\n");
			}
		} while (choice != 2);

	}

}
