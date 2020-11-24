package boundary;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import control.LoginMgr;

public class LoginUI {
	private Scanner sc;
	
	public LoginUI(Scanner sc) throws ParseException, IOException {
		this.sc = sc;
		displayLoginUI();
	}

	private void displayLoginUI() throws ParseException, IOException{
		int choice = 0;
		String username;
		String validation;
		LoginMgr loginMgr = new LoginMgr();
		
		Console console = System.console();
		LoginUILoop:
		do {
			System.out.println("Please select:\n"
					+ "[1] Login as Admin\n"
					+ "[2] Login as Student\n"
					+ "[3] Back\n"
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
						//password = sc.nextLine();
						
						// code to hide echo by opening up new pane
						final String password, message = "Enter password";
						if( System.console() == null ) 
						{ // inside IDE like Eclipse or NetBeans
						  final JPasswordField pf = new JPasswordField(); 
						  password = JOptionPane.showConfirmDialog( null, pf, message,
						    JOptionPane.OK_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? 
						      new String( pf.getPassword() ) : "";
						}
						else password = new String( System.console().readPassword( "%s> ", message ) );
						
						validation = loginMgr.validateAdmin(username, password.toString());
						System.out.println();
						System.out.println(validation);
						System.out.println();
					} while (!(validation == "Successful login!"));
					new AdminUI(this.sc, loginMgr.getAdmin(username));
					break;
					
				case 2: //Student
					System.out.println("Logging in as Student...\n");
					do {
						System.out.print("Username: ");
						username = sc.nextLine();
						System.out.print("Password: ");
						//password = sc.nextLine();
						
						// code to hide echo by opening up new pane
						final String password, message = "Enter password";
						if( System.console() == null ) 
						{ // inside IDE like Eclipse or NetBeans
						  final JPasswordField pf = new JPasswordField(); 
						  password = JOptionPane.showConfirmDialog( null, pf, message,
						    JOptionPane.OK_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? 
						      new String( pf.getPassword() ) : "";
						}
						else password = new String( System.console().readPassword( "%s> ", message ) );
						
						validation = loginMgr.validateStudent(username, password.toString());
						System.out.println();
						System.out.println(validation);
						System.out.println();
					} while (!(validation == "Successful login!"));
					new StudentUI(this.sc, loginMgr.getStudent(username));
					break;
					
				case 3:
					break LoginUILoop;
					
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
}
