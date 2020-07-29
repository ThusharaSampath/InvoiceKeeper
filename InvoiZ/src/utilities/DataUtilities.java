package utilities;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import dataSource.DatabaseConnect;

public class DataUtilities {

	public static <E> E getSplited(String s) {

		String[] p = s.split(",");
		for (String string : p) {
			System.out.println(string);
		}
		return (E) p;

	}

	public static String concat(String s1, String s2) {

		return s1 + "," + s2;

	}

	public static boolean isInvoiceExist(String DealerName, String invoiceNo) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt2 = null;
		String sql2 = "SELECT `InvoiceNo` FROM `invoice` WHERE `InvoiceNo` = '" + invoiceNo + "' AND `DealerName`='"
				+ DealerName + "'";
		try {
			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs1 = stmt2.executeQuery(sql2);
			if (rs1.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to access Invoice database");
			return false;
		}

	}

	public static ResultSet getInvoiceDetails(String DealerName, String invoiceNo) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnectorOnline();
		Statement stmt2 = null;
		String sql2 = "SELECT `InvoiceNo`, `ChequeNo`, `InvoiceAmount`, `DealerId`, `DealerName`, `InvoiceDate`, `InvoiceDeadline`, `Short`, `ChequeDate`,`ChequeAmount` FROM `invoice`  WHERE `InvoiceNo` = '"
				+ invoiceNo + "' AND `DealerName`='" + DealerName + "'";
		try {
			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs1 = stmt2.executeQuery(sql2);
			if (rs1.next()) {

				return rs1;

			} else {
				return null;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to access Invoice database");

			return null;
		}
//		}finally {
//			try {
//			 if (stmt2 != null) { stmt2.close(); }
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

	}

	public static boolean isChequeExist(String ChequeNO) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt2 = null;
		String sql2 = "SELECT `ChequeNo` FROM `cheque` WHERE `ChequeNo` = '" + ChequeNO + "'";
		try {
			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs1 = stmt2.executeQuery(sql2);
			if (rs1.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to access Cheque database");
			return false;
		}

	}

	public static ResultSet getChequeDetails(String ChequeNO) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt2 = null;
		String sql2 = "SELECT `ChequeNo`, `InvoiceNo`, `DealerName`, `ChequeAmount`, `ChequeDate`, `ChequeExpireDate`, `ChequeExpireStatus`, `ChequePassStatus` FROM `cheque` WHERE `ChequeNo`='"
				+ ChequeNO + "'";
		try {
			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs1 = stmt2.executeQuery(sql2);
			if (rs1.next()) {

				return rs1;

			} else {
				return null;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to access Cheque database");
			return null;
		}

	}

	public static double addChequeAmount_invoice(String DealerName, String invoiceNo, double amount) {

		ResultSet rs = getInvoiceDetails(DealerName, invoiceNo);
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		System.out.println(amount);
		try {
			double oldamount = rs.getDouble("ChequeAmount");
			System.out.println(oldamount);
			amount = amount + oldamount;
			System.out.println(amount);
			String sql ="UPDATE `invoice` SET `ChequeAmount`= "+amount+" WHERE InvoiceNo = '"+invoiceNo+"' AND `DealerName` ='"+DealerName+"'";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return amount;
	}

	public static double addAmount_cheque(String ChequeNo, double amount) {

		ResultSet rs = getChequeDetails(ChequeNo);
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		
		try {
			double oldamount = rs.getDouble("ChequeAmount");
			
			amount = amount + oldamount;
			
			String sql ="UPDATE `cheque` SET `ChequeAmount`= "+amount+" WHERE `ChequeNo` = '"+ChequeNo+"'";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return amount;
	}
}