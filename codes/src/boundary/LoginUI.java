package boundary;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import control.LoginMgr;

public class LoginUI {
	private Scanner sc;
	
	public LoginUI(Scanner sc) throws ParseException, IOException {
		this.sc = sc;
		displayLoginUI();
	}

	private void displayLoginUI() throws ParseException, IOException{
		int choice = 0;
		String username, password;
		String validation;
		LoginMgr loginMgr = new LoginMgr();
		
		LoginUILoop:
		do {
			System.out.println("Please select:\n"
					+ "[1] Login as Admin\n"
					+ "[2] Login as Student\n"
					+ "[3] Back"
					+ "[4] Quit Application");
			try {
				choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1: // Admin
					System.out.println("Logging in as Admin...\n");
					do {
						System.out.print("Username: ");
						username = sc.nextLine();
						System.out.print("Password: ");
						password = sc.nextLine();
						validation = loginMgr.validateAdmin(username, password);
						System.out.println(validation);
					} while (!(validation == "Successful login!"));
					break;
					
				case 2: //Student
					System.out.println("Logging in as Student...\n");
					do {
						System.out.print("Username: ");
						username = sc.nextLine();
						System.out.print("Password: ");
						password = sc.nextLine();
						validation = loginMgr.validateStudent(username, password);
						System.out.println(validation);
					} while (!(validation == "Successful login!"));
					break;
					
				case 3:
					break LoginUILoop;
					
				case 4:
					System.out.println("Quitting Application...\n");
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
}
