package amdocs_project;

import java.sql.*;
import java.util.Scanner;
import java.io.*;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

public class Splash {
	
	public static boolean executeDBQuery(String path, Statement stat){
        boolean executeStatus = false;
        String str;
        try{
            BufferedReader in = new BufferedReader(new FileReader(path));
            StringBuffer sb = new StringBuffer();
            while((str = in.readLine()) != null) {
                sb.append(str);
            }
            in.close();
            
            String strArray[] = sb.toString().split(";");
            for(String query : strArray){
                stat.executeUpdate(query);
//                System.out.println(">>"+query);
                executeStatus=true;
            }
        
        }
        catch(FileNotFoundException e){
        	System.out.println("File Not Found Exception : "+e.getMessage());
        }
        catch(IOException e){
        	System.out.println("IO Exception : "+e.getMessage());
        }
        catch(SQLException e){
        	System.out.println("SQL Exception : "+e.getMessage());
        }
        catch(Exception e){
        	System.out.println("Some Other Exception : "+e.getMessage());
        }
        
        return executeStatus;
    }
	
	public static void main(String[] args) {
		Statement stat;
		ResultSet rst;
		boolean flag = false;
		
		System.out.println("\tYour most Welcome in this Menu-driven Project !");
		System.out.println("Kindly wait for few seconds while we are setting all things ready for you...\n");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("--------------------------------------------------------------------------\n");
		
		stat = MySQLConnection.getMyConnection("");
		System.out.println("--------------------------------------------------------------------------\n");
		
		if(executeDBQuery("C:\\Users\\Harikesh\\eclipse-workspace\\amdocs_project\\bin\\amdocs_project/mySQLFile.sql", stat)) {
            stat = MySQLConnection.getMyConnection("amdocs_project");
            flag = true;
            System.out.println("--------------------------------------------------------------------------\n");
        }
        else{
        	System.out.println("Query Not Executed...");
            System.exit(0);
        }
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}		
		
		Scanner sc = new Scanner(System.in);
		
		main_menu :
		while(flag) {
			int main_menu_choice;
			
			System.out.println("\tYou are now in Main Menu.\nPlease select any one of the options below : ");
			System.out.println("1. Customer\n2. Mechanic\n3. Appointment\n4. Service\n0. Exit");
			
			main_menu_choice = Integer.parseInt(sc.nextLine());
			System.out.println("--------------------------------------------------------------------------\n");
			
			switch(main_menu_choice) {
				case 1:
					Customer customer = new Customer(stat);
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 2:
					Mechanic mechanic = new Mechanic(stat);
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 3:
					Appointment appointment = new Appointment(stat);
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 4:
					Service service = new Service(stat);
					System.out.println("--------------------------------------------------------------------------\n");
					break;
				case 0:
					break main_menu;
				default:
					System.out.println("Invalid choice ! Please try again.");
					System.out.println("--------------------------------------------------------------------------\n");
			}
		}
		System.out.println("--------------------------------------------------------------------------\n");
		System.out.println("Program closed successfully !");
		return;
	}
	
}
