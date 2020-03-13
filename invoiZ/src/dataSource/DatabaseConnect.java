package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseConnect {
	Connection conn = null;

	public static Connection dbConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/InvoiZ", "thushara", "123qwe");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/rZMON02UdD", "rZMON02UdD", "MwyFAZB81a");
			//Connection conn = DriverManager.getConnection("jdbc:sqlite:E:/my projects/InvoiZSqlite.db");
			// Connection conn=DriverManager.getConnection("jdbc:mysql:E://my
			// projects//InvoiZ//InvoiZ//Resourse//invoiz.sql","thushara","123qwe");
			// JOptionPane.showMessageDialog(null,"Connection Successful");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			JOptionPane.showMessageDialog(null,
					"Oops, something went wrong while Connecting to Database Please check Xampp is running!");
			return null;
		}
	}

	public static Connection dbConnectorOnline() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/rZMON02UdD", "rZMON02UdD", "MwyFAZB81a");

			// Connection conn=DriverManager.getConnection("jdbc:mysql:E://my
			// projects//InvoiZ//InvoiZ//Resourse//invoiz.sql","thushara","123qwe");
			// JOptionPane.showMessageDialog(null,"Connection Successful");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			JOptionPane.showMessageDialog(null,
					"Oops, something went wrong while Connecting to Database Please check Xampp is running!");
			return null;
		}
	}
}