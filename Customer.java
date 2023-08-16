package amdocs_project;

import java.sql.*;
import java.util.Scanner;

class CustomerDetails {
	String cust_id, name, vehicle_no, contact, description;
}

public class Customer {
	String query;
	Statement stat;
	ResultSet rst;
	Scanner sc = new Scanner(System.in);
	
	public void registerCustomer() {
		CustomerDetails cust = new CustomerDetails();
		
		System.out.println("\tYou are now going to Register a new customer. Please enter below details correctly :-");
		System.out.println("Customer ID : ");
		while(true) {
			cust.cust_id = sc.nextLine();
			if(cust.cust_id.isBlank()) {
				System.out.println("Blank Customer ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Customer Name : ");
		while(true) {
			cust.name = sc.nextLine();
			if(cust.name.isBlank()) {
				System.out.println("Blank Customer name cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		System.out.println("Vehicle no. : ");
		while(true) {
			cust.vehicle_no = sc.nextLine();
			if(cust.vehicle_no.isBlank()) {
				System.out.println("Blank Vehicle no. cann't be processed. Please re-enter : ");
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
		System.out.println("Description : ");
		while(true) {
			cust.description = sc.nextLine();
			if(cust.description.isBlank()) {
				System.out.println("Blank Description cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		
		query = new String("INSERT INTO CUSTOMER VALUES('" + cust.cust_id + "','" + cust.name +
				 "','" + cust.vehicle_no + "','" + cust.contact + "','" + cust.description + "');");
		int res=0;
		try {
			res = stat.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		if(res==1)
			System.out.println("Customer registered successfully !");
		else
			System.out.println("Customer not registered !");
	}

	public void modifyCustomerDetails() {
		String cust_id;
		int res = 0;
		
		System.out.println("\tYou are now going to Modify a Customer record. Please enter below details correctly :-");
		System.out.println("Customer ID : ");
		take_id:
		while(true) {
			while(true) {
				cust_id = sc.nextLine();
				if(cust_id.isBlank()) {
					System.out.println("Blank Customer ID cann't be processed. Please re-enter : ");
					continue;
				}
				else
					break;
			}
			
			query = new String("SELECT * FROM CUSTOMER WHERE CUST_ID = '" + cust_id + "';");
			
			try {
				rst = stat.executeQuery(query);
				rst.last();
				
				if(rst.getRow()>0) {
					rst.first();
					
					customer_update :
						while(true) {
							int customer_update_choice;
							String str;
							
							System.out.println("\tYou are now updating record of Customer with Customer ID : " + cust_id +
									"\nPlease select any one from below fields : ");
							System.out.println("1. Customer Name\n"
									+ "2. Vehicle No\n"
									+ "3. Contact\n"
									+ "4. Description\n"
									+ "0. Save and Exit\n");
							
							customer_update_choice = Integer.parseInt(sc.nextLine());
							
							switch(customer_update_choice) {
								case 1:
									System.out.println("Customer Name : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Customer name cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("CUST_NAME", str);
									break;
								case 2:
									System.out.println("Vehicle no. : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Vehicle no. cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("VEHICLE_NO", str);
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
									System.out.println("Description : ");
									while(true) {
										str = sc.nextLine();
										if(str.isBlank()) {
											System.out.println("Blank Description cann't be processed. Please re-enter : ");
											continue;
										}
										else
											break;
									}
									rst.updateString("DESCRIPTION", str);
									break;
								case 0:
									rst.updateRow();
									res = 1;
									break customer_update;
								default:
									System.out.println("Invalid choice ! Please try again.");
							}
						}
				}
				else {
					System.out.println("No any record exist with this Customer ID. Please enter a valid ID.");
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
			System.out.println("Customer record updated successfully !");
		else
			System.out.println("Customer record not updated !");
	}
	
	public void deleteCustomerRecord() {
		String cust_id;
		int res = 0;
		
		System.out.println("\tAttention ! You are now going to Delete a Customer record. Please enter below details correctly :-");
		System.out.println("Customer ID : ");
		
		while(true) {
			cust_id = sc.nextLine();
			if(cust_id.isBlank()) {
				System.out.println("Blank Customer ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		
		query = new String("SELECT * FROM CUSTOMER WHERE CUST_ID = '" + cust_id + "';");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {
				rst.first();
				
				System.out.println("You are about to delete the below records :");
				System.out.println(rst.getString("CUST_ID") + " | " + rst.getString("CUST_NAME")
					+ " | " + rst.getString("VEHICLE_NO") + " | " + rst.getString("CONTACT")
						+ " | " + rst.getString("DESCRIPTION"));
				System.out.println();
				System.out.println("Are you sure to delete it ?\n1. Yes\nPress anything else to to cancel.");
				
				String delete_choice = sc.nextLine();
				switch (delete_choice) {
					case "1": {
						query = new String("DELETE FROM CUSTOMER WHERE CUST_ID = '" + cust_id + "';");
						stat.executeUpdate(query);
						res = 1;
						break;
					}
					default:
						break;
				}
									
			}
			else {
				System.out.println("No any record exist with this Customer ID.");
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		
		if(res==1)
			System.out.println("Customer record deleted successfully !");
		else
			System.out.println("Customer record not deleted !");
	}
	
	public void viewSingleRecord() {
		String cust_id;
		int res = 0;
		
		System.out.println("\tYou are now going to View a Customer record. Please enter below details correctly :-");
		System.out.println("Customer ID : ");
		
		while(true) {
			cust_id = sc.nextLine();
			if(cust_id.isBlank()) {
				System.out.println("Blank Customer ID cann't be processed. Please re-enter : ");
				continue;
			}
			else
				break;
		}
		
		query = new String("SELECT * FROM CUSTOMER WHERE CUST_ID = '" + cust_id + "';");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {
				rst.beforeFirst();
				
				System.out.println("These are the requested Customer records :\n");
				while(rst.next())
				System.out.println(rst.getString("CUST_ID") + " | " + rst.getString("CUST_NAME")
					+ " | " + rst.getString("VEHICLE_NO") + " | " + rst.getString("CONTACT")
						+ " | " + rst.getString("DESCRIPTION"));
				System.out.println();
				res = 1;									
			}
			else {
				System.out.println("No any record exist with this Customer ID.");
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		
		if(res==1)
			System.out.println("Requested Customer record fetched successfully !");
		else
			System.out.println("Requested Customer record not found !");
	}
	
	public void viewaAllRecords() {
		int res = 0;
		
		System.out.println("\tYou are now going to View All Customer records.");
		System.out.println("");
		
		query = new String("SELECT * FROM CUSTOMER;");
		
		try {
			rst = stat.executeQuery(query);
			rst.last();
			
			if(rst.getRow()>0) {
				rst.beforeFirst();
				
				System.out.println("These are the requested Customer records :\n");
				while(rst.next())
				System.out.println(rst.getString("CUST_ID") + " | " + rst.getString("CUST_NAME")
					+ " | " + rst.getString("VEHICLE_NO") + " | " + rst.getString("CONTACT")
						+ " | " + rst.getString("DESCRIPTION"));
				System.out.println();
				res = 1;									
			}
			else {
				System.out.println("No any Customer record exists...");
				res = 1;
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Some other error : " + e.getMessage());
		}
		
		if(res==1)
			System.out.println("Requested Customer record fetched successfully !");
		else
			System.out.println("Requested Customer record not found !");
	}
	
	Customer(Statement stat) {
		this.stat = stat;
		
		customer_menu :
		while(true) {
			int customer_menu_choice;
			
			System.out.println("\tYou are now in Customer Menu.\nPlease select any one from below operations : ");
			System.out.println("1. Register Customer\n"
					+ "2. Modify Customer details\n"
					+ "3. Delete Customer record\n"
					+ "4. View single record\n"
					+ "5. View all records\n"
					+ "0. Exit from Customer Menu\n");
			
			customer_menu_choice = Integer.parseInt(sc.nextLine());
			
			switch(customer_menu_choice) {
				case 1:
					registerCustomer();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 2:
					modifyCustomerDetails();
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 3:
					deleteCustomerRecord();
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
					break customer_menu;
				default:
					System.out.println("Invalid choice ! Please try again.");
					System.out.println("--------------------------------------------------------------------------\n");
			}
		}
	}
	
}