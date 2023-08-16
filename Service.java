package amdocs_project;

import java.sql.*;
import java.util.Scanner;

class ServiceDetails {
	String app_id, cust_id, mech_id, app_time, app_status = "Pending", issue;
}

public class Service {
	String query;
	Statement stat;
	ResultSet rst;
	Scanner sc = new Scanner(System.in);
	
	public void takeAction() {
		String app_id;
		int res = 0;
		
		System.out.println("\tYou are now going to Take action for an Appointment record. Please enter below details correctly :-");
		System.out.println("Appointment ID : ");
		take_id:
		while(true) {
			while(true) {
				app_id = sc.nextLine();
				if(app_id.isBlank()) {
					System.out.println("Blank Appointment ID cann't be processed. Please re-enter : ");
					continue;
				}
				else
					break;
			}
			
			query = new String("SELECT * FROM APPOINTMENT WHERE APP_ID = '" + app_id + "';");
			
			try {
				rst = stat.executeQuery(query);
				rst.last();
				
				if(rst.getRow()>0) {
					rst.first();
					
					appointment_update :
						while(true) {
							int appointment_update_choice;
							
							System.out.println("\tWhat action you want to take for Appointment with Appointment ID : " + app_id +
									"\nPlease select any one from below fields : ");
							System.out.println("1. Process\n"
									+ "2. Complete\n"
									+ "3. Reject\n"
									+ "0. Exit\n");
							
							appointment_update_choice = Integer.parseInt(sc.nextLine());
							
							switch(appointment_update_choice) {
								case 1:
									rst.updateString("APP_STATUS", "Processed");
									rst.updateRow();
									res = 1;
									break appointment_update;
								case 2:
									rst.updateString("APP_STATUS", "Completed");
									rst.updateRow();
									res = 1;
									break appointment_update;
								case 3:
									rst.updateString("APP_STATUS", "Rejected");
									rst.updateRow();
									res = 1;
									break appointment_update;
								case 0:
									break appointment_update;
								default:
									System.out.println("Invalid choice ! Please try again.");
							}
						}
				}
				else {
					System.out.println("No any record exist with this Appointment ID. Please enter a valid ID.");
					continue take_id;
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception : " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Some other error : " + e.getMessage());
			}
			break;
		}
		
		if(res==1)
			System.out.println("Appointment status changed successfully !");
		else
			System.out.println("Appointment status is not changed !");
	}
	
	public void viewSingleRecord() {
		String app_id;
		int res = 0;
		
		System.out.println("\tYou are now going to View an Appointment record. Please enter below details correctly :-");
		System.out.println("Appointment ID : ");
		
		while(true) {
			app_id = sc.nextLine();
			if(app_id.isBlank()) {
				System.out.println("Blank Appointment ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		
		query = new String("SELECT * FROM APPOINTMENT WHERE APP_ID = '" + app_id + "';");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {
				rst.beforeFirst();
				
				System.out.println("These are the requested Appointment records :\n");
				while(rst.next())
				System.out.println(rst.getString("APP_ID") + " | " + rst.getString("CUST_ID")
				+ " | " + rst.getString("MECH_ID") + " | " + rst.getString("APP_TIME")
					+ " | " + rst.getString("APP_STATUS") + " | " + rst.getString("ISSUE"));
				res = 1;									
			}
			else {
				System.out.println("No any record exist with this Appointment ID.");
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		
		if(res==1)
			System.out.println("Requested Appointment record fetched successfully !");
		else
			System.out.println("Requested Appointment record not found !");
	}
	
	public void viewaAllRecords() {
		int res = 0;
		
		System.out.println("\tYou are now going to View All Pending Appointment records.");
		System.out.println("");
		
		query = new String("SELECT * FROM APPOINTMENT WHERE APP_STATUS = 'Pending' OR APP_STATUS = 'Processed';");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {				
				System.out.println("These are the Pending Appointment records :\n");
				rst.beforeFirst();
				while(rst.next())
					System.out.println(rst.getString("APP_ID") + " | " + rst.getString("CUST_ID")
					+ " | " + rst.getString("MECH_ID") + " | " + rst.getString("APP_TIME")
						+ " | " + rst.getString("APP_STATUS") + " | " + rst.getString("ISSUE"));
				System.out.println("-------------------------------------------------------------------------------");
				
				System.out.println();
				res = 1;									
			}
			else {
				System.out.println("No any Appointment record exists...");
				res = 1;
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		
		if(res==1)
			System.out.println("Requested Appointment record fetched successfully !");
		else
			System.out.println("Requested Appointment record not found !");
	}
	
	Service(Statement stat) {
		this.stat = stat;
		
		service_menu :
		while(true) {
			int service_menu_choice;
			
			System.out.println("\tYou are now in Service Menu.\nPlease select any one from below operations : ");
			System.out.println("1. Take action for an Appointment\n"
					+ "2. View single Appointment\n"
					+ "3. View all Pending Appointments\n"
					+ "0. Exit from Service Menu\n");
			
			service_menu_choice = Integer.parseInt(sc.nextLine());
			
			switch(service_menu_choice) {
				case 1:
					takeAction();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 2:
					viewSingleRecord();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 3:
					viewaAllRecords();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 0:
					break service_menu;
				default:
					System.out.println("Invalid choice ! Please try again.");
					System.out.println("--------------------------------------------------------------------------\n");
			}
		}
	}
	
}