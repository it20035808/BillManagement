package model;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Bill {
	
	//Database connection
	private Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//connection details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "");
		}
		catch(Exception e) {
		 e.printStackTrace();
		}
		
		return con;
	}
	
	//Read Bills
	public String readBills() {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return("Error while connecting to the database for reading.");
			}
			
			//Prepare HTML table to be shown
			output = "<table border='1'>"
					+ "<tr>"
					+ "<th>Bill ID</th>"
					+ "<th>Billing Date</th>"
					+ "<th>Unit Cost</th>" 
					+ "<th>Units Used</th>"
					+ "<th>Service Charge</th>"
					+ "<th>Total Cost</th>"
					+ "<th>Bill Settled</th>"
					+ "<th>User ID</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			//create prepared statement
			String query = "select * from bills";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			//loop through resultset rows
			while(rs.next()) {
				String billID = Integer.toString(rs.getInt("billID"));
				Date billDate = rs.getDate("billDate");
				String unitCost = Integer.toString(rs.getInt("unitCost"));
				String unitsUsed = Integer.toString(rs.getInt("unitsUsed"));
				String serviceCharge = Integer.toString(rs.getInt("serviceCharge"));
				String totalCost = Integer.toString(rs.getInt("totalCost"));
				String settled = Boolean.toString(rs.getBoolean("settled"));
				String userID = Integer.toString(rs.getInt("userID"));
				
				//add to output html table
				output += "<tr><td>" + billID + "</td>"
						+  "<td>" + billDate + "</td>"
						+  "<td>" + unitCost + "</td>"
						+  "<td>" + unitsUsed + "</td>"
						+  "<td>" + serviceCharge + "</td>"
						+  "<td>" + totalCost + "</td>"
						+  "<td>" + settled + "</td>"
						+  "<td>" + userID + "</td>";
				
				//buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-billID='" + billID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-billID='" + billID + "'></td></tr>";		
			}
		
			con.close();

			// Complete the html table
			output += "</table>";
			
		}
		catch(Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}	
	
	
	
	//Create Bills
	public String createBill(String BillingDate, String unitCost, String unitsUsed, String serviceCharge, String billSettled, String userID) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			//calculate total cost
			int totalCost = (Integer.parseInt(unitCost) * Integer.parseInt(unitsUsed)) + Integer.parseInt(serviceCharge);
			
			//create a prepared statement
			String query = "insert into bills (`billDate`, `unitCost`, `unitsUsed`, `serviceCharge`, `totalCost`, `settled`, `userID`)"
					+" values(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//converting String to util.Date to sql.Date
			java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(BillingDate);
			java.sql.Date  date2 = new java.sql.Date(date1.getTime());
			
			// binding values
			preparedStmt.setDate(1, date2);
			preparedStmt.setInt(2, Integer.parseInt(unitCost));
			preparedStmt.setInt(3, Integer.parseInt(unitsUsed));
			preparedStmt.setInt(4, Integer.parseInt(serviceCharge));
			preparedStmt.setInt(5, totalCost);
			preparedStmt.setBoolean(6, Boolean.parseBoolean(billSettled));
			preparedStmt.setInt(7, Integer.parseInt(userID));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			//output = "Inserted successfully";
			String newBills = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
			
		}
		catch(Exception e) {
			//output = "Error while inserting the item.";
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	//Update Bills 
	//for settles bills
	public String updateSettledBills(String billID, String billSettled) {
		String output = "";
		
		Connection con = connect();
		
		try {
			
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			//create prepared statement
			String query = "update bills set settled=? where billID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setBoolean(1, Boolean.parseBoolean(billSettled));
			preparedStmt.setInt(2, Integer.parseInt(billID));
			
			//execute statement
			preparedStmt.execute();
			
			con.close();
			
			//output = "Updated successfully";
			String newBills = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
			
		}
		catch(Exception e) {
			//output = "Error while updating bill";
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	
	
	//Delete Bills
	public String deleteBill(String billID) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for deleting."; 
			}
			
			//create prepared statement
			String query = "delete from bills where billID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values to prepared statement
			preparedStmt.setInt(1, Integer.parseInt(billID));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			//output = "Deleted successfully";
			String newBills = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
		}
		catch(Exception e) {
			//output = "Error while deleting the bill.";
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}
