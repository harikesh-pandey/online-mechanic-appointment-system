package amdocs_project;

import java.sql.*;
import java.util.Scanner;

class AppointmentDetails {
	String app_id, cust_id, mech_id, app_time, app_status = "Pending", issue;
}

public class Appointment {
	String query;
	Statement stat;
	ResultSet rst;
	Scanner sc = new Scanner(System.in);
	
	public void registerAppointment() {
		AppointmentDetails app = new AppointmentDetails();
		
		System.out.println("\tYou are now going to Register a new Appointment. Please enter below details correctly :-");
		System.out.println("Appointment ID : ");
		while(true) {
			app.app_id = sc.nextLine();
			if(app.app_id.isBlank()) {
				System.out.println("Blank Appointment ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Customer ID : ");
		while(true) {
			app.cust_id = sc.nextLine();
			if(app.cust_id.isBlank()) {
				System.out.println("Blank Customer ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Mechanic ID : ");
		while(true) {
			app.mech_id = sc.nextLine();
			if(app.mech_id.isBlank()) {
				System.out.println("Blank Mechanic ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Appointment Time : ");
		while(true) {
			app.app_time = sc.nextLine();
			if(app.app_time.isBlank()) {
				System.out.println("Blank Appointment Time cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Issue : ");
		while(true) {
			app.issue = sc.nextLine();
			if(app.issue.isBlank()) {
				System.out.println("Blank Issue cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		
		query = new String("INSERT INTO APPOINTMENT VALUES('" + app.app_id + "','" + app.cust_id +
				 "','" + app.mech_id + "','" + app.app_time + "','" + app.app_status +  "','" + app.issue + "');");
		int res=0;
		try {
			res = stat.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		if(res==1)
			System.out.println("Appointment registered successfully !");
		else
			System.out.println("Appointment not registered !");
	}

	public void modifyAppointmentDetails() {
		String app_id;
		int res = 0;
		
		System.out.println("\tYou are now going to Modify an Appointment record. Please enter below details correctly :-");
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
							String str;
							
							System.out.println("\tYou are now updating record of Appointment with Appointment ID : " + app_id +
									"\nPlease select any one from below fields : ");
							System.out.println("1. Customer ID\n"
									+ "2. Mechanic ID\n"
									+ "3. Issue\n"
									+ "0. Save and Exit\n");
							
							appointment_update_choice = Integer.parseInt(sc.nextLine());
							
							switch(appointment_update_choice) {
								case 1:
									System.out.println("Customer ID : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Customer ID cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("CUST_ID", str);
									break;
								case 2:
									System.out.println("Mechanic ID : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Mechanic ID cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("MECH_ID", str);
									break;
								case 3:
									System.out.println("Issue : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Issue cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("ISSUE", str);
									break;
								case 0:
									rst.updateRow();
									res = 1;
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
			System.out.println("Appointment record updated successfully !");
		else
			System.out.println("Appointment record not updated !");
	}
	
	public void deleteAppointmentRecord() {
		String app_id;
		int res = 0;
		
		System.out.println("\tAttention ! You are now going to Delete a Appointment record. Please enter below details correctly :-");
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
				rst.first();
				
				System.out.println("You are about to delete the below records :");
				System.out.println(rst.getString("APP_ID") + " | " + rst.getString("CUST_ID")
					+ " | " + rst.getString("MECH_ID") + " | " + rst.getString("APP_TIME")
						+ " | " + rst.getString("APP_STATUS") + " | " + rst.getString("ISSUE"));
				System.out.println();
				System.out.println("Are you sure to delete it ?\n1. Yes\nPress anything else to to cancel.");
				
				String delete_choice = sc.nextLine();
				switch (delete_choice) {
					case "1": {
						query = new String("DELETE FROM APPOINTMENT WHERE APP_ID = '" + app_id + "';");
						stat.executeUpdate(query);
						res = 1;
						break;
					}
					default:
						break;
				}
									
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
			System.out.println("Appointment record deleted successfully !");
		else
			System.out.println("Appointment record not deleted !");
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
		
		System.out.println("\tYou are now going to View All Appointment records.");
		System.out.println("");
		
		query = new String("SELECT * FROM APPOINTMENT;");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {				
				System.out.println("These are the requested Appointment records :\n");
				
				System.out.println("Pending :");
				query = new String("SELECT * FROM APPOINTMENT WHERE APP_STATUS = 'Pending';");
				rst = stat.executeQuery(query);
				while(rst.next())
					System.out.println(rst.getString("APP_ID") + " | " + rst.getString("CUST_ID")
					+ " | " + rst.getString("MECH_ID") + " | " + rst.getString("APP_TIME")
						+ " | " + rst.getString("APP_STATUS") + " | " + rst.getString("ISSUE"));
				System.out.println("-------------------------------------------------------------------------------");
				
				System.out.println("Processed :");
				query = new String("SELECT * FROM APPOINTMENT WHERE APP_STATUS = 'Processed';");
				rst = stat.executeQuery(query);
				while(rst.next())
					System.out.println(rst.getString("APP_ID") + " | " + rst.getString("CUST_ID")
					+ " | " + rst.getString("MECH_ID") + " | " + rst.getString("APP_TIME")
						+ " | " + rst.getString("APP_STATUS") + " | " + rst.getString("ISSUE"));
				System.out.println("-------------------------------------------------------------------------------");
				
				System.out.println("Completed :");
				query = new String("SELECT * FROM APPOINTMENT WHERE APP_STATUS = 'Completed';");
				rst = stat.executeQuery(query);
				while(rst.next())
					System.out.println(rst.getString("APP_ID") + " | " + rst.getString("CUST_ID")
					+ " | " + rst.getString("MECH_ID") + " | " + rst.getString("APP_TIME")
						+ " | " + rst.getString("APP_STATUS") + " | " + rst.getString("ISSUE"));
				System.out.println("-------------------------------------------------------------------------------");
				
				System.out.println("Rejected :");
				query = new String("SELECT * FROM APPOINTMENT WHERE APP_STATUS = 'Rejected';");
				rst = stat.executeQuery(query);
				while(rst.next())
					System.out.println(rst.getString("APP_ID") + " | " + rst.getString("CUST_ID")
					+ " | " + rst.getString("MECH_ID") + " | " + rst.getString("APP_TIME")
						+ " | " + rst.getString("APP_STATUS") + " | " + rst.getString("ISSUE"));
				
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
	
	Appointment(Statement stat) {
		this.stat = stat;
		
		appointment_menu :
		while(true) {
			int appointment_menu_choice;
			
			System.out.println("\tYou are now in Appointment Menu.\nPlease select any one from below operations : ");
			System.out.println("1. Register Appointment\n"
					+ "2. Modify Appointment details\n"
					+ "3. Delete Appointment record\n"
					+ "4. View single record\n"
					+ "5. View all records\n"
					+ "0. Exit from Appointment Menu\n");
			
			appointment_menu_choice = Integer.parseInt(sc.nextLine());
			
			switch(appointment_menu_choice) {
				case 1:
					registerAppointment();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 2:
					modifyAppointmentDetails();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 3:
					deleteAppointmentRecord();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 4:
					viewSingleRecord();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 5:
					viewaAllRecords();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 0:
					break appointment_menu;
				default:
					System.out.println("Invalid choice ! Please try again.");
					System.out.println("--------------------------------------------------------------------------\n");
			}
		}
	}
	
}