package amdocs_project;

import java.sql.*;
import java.util.Scanner;

class MechanicDetails {
	String mech_id, name, experience, contact, timeSlot;
}

public class Mechanic {
	String query;
	Statement stat;
	ResultSet rst;
	Scanner sc = new Scanner(System.in);
	
	public void registerMechanic() {
		MechanicDetails cust = new MechanicDetails();
		
		System.out.println("\tYou are now going to Register a new Mechanic. Please enter below details correctly :-");
		System.out.println("Mechanic ID : ");
		while(true) {
			cust.mech_id = sc.nextLine();
			if(cust.mech_id.isBlank()) {
				System.out.println("Blank Mechanic ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Mechanic Name : ");
		while(true) {
			cust.name = sc.nextLine();
			if(cust.name.isBlank()) {
				System.out.println("Blank Mechanic name cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Experience : ");
		while(true) {
			cust.experience = sc.nextLine();
			if(cust.experience.isBlank()) {
				System.out.println("Blank Experience cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Contact no. : ");
		while(true) {
			cust.contact = sc.nextLine();
			if(cust.contact.isBlank()) {
				System.out.println("Blank Contact no. cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Time Slot : ");
		while(true) {
			cust.timeSlot = sc.nextLine();
			if(cust.timeSlot.isBlank()) {
				System.out.println("Blank Time Slot cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		
		query = new String("INSERT INTO MECHANIC VALUES('" + cust.mech_id + "','" + cust.name +
				 "','" + cust.experience + "','" + cust.contact + "','" + cust.timeSlot + "');");
		int res=0;
		try {
			res = stat.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		if(res==1)
			System.out.println("Mechanic registered successfully !");
		else
			System.out.println("Mechanic not registered !");
	}

	public void modifyMechanicDetails() {
		String mech_id;
		int res = 0;
		
		System.out.println("\tYou are now going to Modify a Mechanic record. Please enter below details correctly :-");
		System.out.println("Mechanic ID : ");
		take_id:
		while(true) {
			while(true) {
				mech_id = sc.nextLine();
				if(mech_id.isBlank()) {
					System.out.println("Blank Mechanic ID cann't be processed. Please re-enter : ");
					continue;
				}
				else
					break;
			}
			
			query = new String("SELECT * FROM MECHANIC WHERE MECH_ID = '" + mech_id + "';");
			
			try {
				rst = stat.executeQuery(query);
				rst.last();
				
				if(rst.getRow()>0) {
					rst.first();
					
					mechanic_update :
						while(true) {
							int mechanic_update_choice;
							String str;
							
							System.out.println("\tYou are now updating record of Mechanic with Mechanic ID : " + mech_id +
									"\nPlease select any one from below fields : ");
							System.out.println("1. Mechanic Name\n"
									+ "2. Experience\n"
									+ "3. Contact\n"
									+ "4. Time Slot\n"
									+ "0. Save and Exit\n");
							
							mechanic_update_choice = Integer.parseInt(sc.nextLine());
							
							switch(mechanic_update_choice) {
								case 1:
									System.out.println("Mechanic Name : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Mechanic name cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("MECH_NAME", str);
									break;
								case 2:
									System.out.println("Experience : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Experience cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("EXPERIENCE", str);
									break;
								case 3:
									System.out.println("Contact no. : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Contact no. cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("CONTACT", str);
									break;
								case 4:
									System.out.println("Time Slot : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Time Slot cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("TIME_SLOT", str);
									break;
								case 0:
									rst.updateRow();
									res = 1;
									break mechanic_update;
								default:
									System.out.println("Invalid choice ! Please try again.");
							}
						}
				}
				else {
					System.out.println("No any record exist with this Mechanic ID. Please enter a valid ID.");
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
			System.out.println("Mechanic record updated successfully !");
		else
			System.out.println("Mechanic record not updated !");
	}
	
	public void deleteMechanicRecord() {
		String mech_id;
		int res = 0;
		
		System.out.println("\tAttention ! You are now going to Delete a Mechanic record. Please enter below details correctly :-");
		System.out.println("Mechanic ID : ");
		
		while(true) {
			mech_id = sc.nextLine();
			if(mech_id.isBlank()) {
				System.out.println("Blank Mechanic ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		
		query = new String("SELECT * FROM MECHANIC WHERE MECH_ID = '" + mech_id + "';");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {
				rst.first();
				
				System.out.println("You are about to delete the below records :");
				System.out.println(rst.getString("MECH_ID") + " | " + rst.getString("MECH_NAME")
					+ " | " + rst.getString("EXPERIENCE") + " | " + rst.getString("CONTACT")
						+ " | " + rst.getString("TIME_SLOT"));
				System.out.println();
				System.out.println("Are you sure to delete it ?\n1. Yes\nPress anything else to to cancel.");
				
				String delete_choice = sc.nextLine();
				switch (delete_choice) {
					case "1": {
						query = new String("DELETE FROM MECHANIC WHERE MECH_ID = '" + mech_id + "';");
						stat.executeUpdate(query);
						res = 1;
						break;
					}
					default:
						break;
				}
									
			}
			else {
				System.out.println("No any record exist with this Mechanic ID.");
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		
		if(res==1)
			System.out.println("Mechanic record deleted successfully !");
		else
			System.out.println("Mechanic record not deleted !");
	}
	
	public void viewSingleRecord() {
		String mech_id;
		int res = 0;
		
		System.out.println("\tYou are now going to View a Mechanic record. Please enter below details correctly :-");
		System.out.println("Mechanic ID : ");
		
		while(true) {
			mech_id = sc.nextLine();
			if(mech_id.isBlank()) {
				System.out.println("Blank Mechanic ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		
		query = new String("SELECT * FROM MECHANIC WHERE MECH_ID = '" + mech_id + "';");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {
				rst.beforeFirst();
				
				System.out.println("These are the requested Mechanic records :\n");
				while(rst.next())
				System.out.println(rst.getString("MECH_ID") + " | " + rst.getString("MECH_NAME")
					+ " | " + rst.getString("EXPERIENCE") + " | " + rst.getString("CONTACT")
						+ " | " + rst.getString("TIME_SLOT"));
				System.out.println();
				res = 1;									
			}
			else {
				System.out.println("No any record exist with this Mechanic ID.");
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		
		if(res==1)
			System.out.println("Requested Mechanic record fetched successfully !");
		else
			System.out.println("Requested Mechanic record not found !");
	}
	
	public void viewaAllRecords() {
		int res = 0;
		
		System.out.println("\tYou are now going to View All Mechanic records.");
		System.out.println("");
		
		query = new String("SELECT * FROM MECHANIC;");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {
				rst.beforeFirst();
				
				System.out.println("These are the requested Mechanic records :\n");
				while(rst.next())
				System.out.println(rst.getString("MECH_ID") + " | " + rst.getString("MECH_NAME")
					+ " | " + rst.getString("EXPERIENCE") + " | " + rst.getString("CONTACT")
						+ " | " + rst.getString("TIME_SLOT"));
				System.out.println();
				res = 1;									
			}
			else {
				System.out.println("No any Mechanic record exists...");
				res = 1;
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		
		if(res==1)
			System.out.println("Requested Mechanic record fetched successfully !");
		else
			System.out.println("Requested Mechanic record not found !");
	}
	
	Mechanic(Statement stat) {
		this.stat = stat;
		
		mechanic_menu :
		while(true) {
			int mechanic_menu_choice;
			
			System.out.println("\tYou are now in Mechanic Menu.\nPlease select any one from below operations : ");
			System.out.println("1. Register Mechanic\n"
					+ "2. Modify Mechanic details\n"
					+ "3. Delete Mechanic record\n"
					+ "4. View single record\n"
					+ "5. View all records\n"
					+ "0. Exit from Mechanic Menu\n");
			
			mechanic_menu_choice = Integer.parseInt(sc.nextLine());
			
			switch(mechanic_menu_choice) {
				case 1:
					registerMechanic();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 2:
					modifyMechanicDetails();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 3:
					deleteMechanicRecord();
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
					break mechanic_menu;
				default:
					System.out.println("Invalid choice ! Please try again.");
					System.out.println("--------------------------------------------------------------------------\n");
			}
		}
	}
	
}