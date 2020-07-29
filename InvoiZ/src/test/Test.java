package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import utilities.DataUtilities;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		
		
		
		
		
		System.out.println(DataUtilities.getInvoiceDetails("gjgh", "545").getString("InvoiceNo"));
		
		
		
		
		
///*		try {
//			// Connection conn = null;
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/rZMON02UdD", "rZMON02UdD",
//					"MwyFAZB81a");
//			JOptionPane.showMessageDialog(null, "successfully connected");
//			// Connection conn=DriverManager.getConnection("jdbc:mysql:E://my
//			// projects//InvoiZ//InvoiZ//Resourse//invoiz.sql","thushara","123qwe");
//			// JOptionPane.showMessageDialog(null,"Connection Successful");
//			String sql = "INSERT INTO `dealer` (`DealerId`, `DealerName`, `DealerTel`, `DealerPeriod`, `DealerCode`) VALUES (NULL, 'jhahna', '546165', '45', 'jsda');";
//			Statement stmt = null;
//			stmt = conn.createStatement();
//			stmt.executeUpdate(sql);
//			JOptionPane.showMessageDialog(null, "successfully inserted");
//
//			
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//			JOptionPane.showMessageDialog(null,
//					"Oops, something went wrong while Connecting to Database Please check Xampp is running!");
//
//		}
//
//	}*/
}
}