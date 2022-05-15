package com;

import java.sql.*;

public class Bill {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_2022", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readBills() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>id</th>" + "<th>acc_no</th><th>customer_name</th>"
					+ "<th>address</th><th>qty</th>" + "<th>total_price</th><th>date_time</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from bill_table";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String acc_no = Integer.toString(rs.getInt("acc_no"));
				String customer_name = rs.getString("customer_name");
				String address = rs.getString("address");
				String qty = Double.toString(rs.getDouble("qty"));
				String total_price = Double.toString(rs.getDouble("total_price"));
				String date_time = rs.getString("date_time");
				// Add into the html table
				output += "<tr><td>" + acc_no+ "</td>";
				output += "<td>" + customer_name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + qty + "</td>";
				output += "<td>" + total_price + "</td>";
				output += "<td>" + date_time + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + id + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the bills.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertBill( String acc_no, String customer_name, String address, String qty, String total_price, String date_time) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into bill_table (`id`,`acc_no`,`customer_name`,`address`,`qty`,`total_price`,`date_time`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(acc_no));
			preparedStmt.setString(3, customer_name);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, qty);
			preparedStmt.setDouble(6, Double.parseDouble(total_price));
			preparedStmt.setString(7, date_time);
			preparedStmt.execute();
			con.close();
			String newbills = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newbills + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the bill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBill(String id, String acc_no, String customer_name, String address, String qty, String total_price, String date_time, String string) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE bill_table SET acc_no=?,customer_name=?,address=?,,qty=?,total_price=?,date_time=? WHERE itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setInt(1, Integer.parseInt(acc_no));
			preparedStmt.setString(2, customer_name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, qty);
			preparedStmt.setDouble(5, Double.parseDouble(total_price));
			preparedStmt.setString(6, date_time);
			preparedStmt.setInt(7, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newbills = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newbills + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletebill(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from bill_table where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newbills = readBills();
			output = "{\"status\":\"success\", \"data\": \"" + newbills + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the bill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
