package dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JOptionPane;

import utilities.DataUtilities;

public class DataStore {
	public static void update_Invoice_with_newchequeNo(String NewChequeNo, String InvoiceNO, String ChequeDate) { // method
																													// must
																													// be
																													// used
																													// after
																													// check
																													// existence
																													// of
																													// Invoice
																													// and
																													// cheque
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		Statement stmt1 = null;
		String sql1 = "SELECT `InvoiceNo`, `ChequeNo`,`ChequeDate` FROM `invoice` WHERE `InvoiceNo`='" + InvoiceNO
				+ "'";
		try {
			stmt1 = connection.prepareStatement(sql1);
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if (rs1.next()) {

				String oldChequeNO = rs1.getString("ChequeNo");
				String oldChequeDate = rs1.getString("ChequeDate");
				String[] ar = DataUtilities.getSplited(oldChequeNO);
				java.util.List<String> list = Arrays.asList(ar);
				if (list.contains(NewChequeNo)) { // check if this new cheque is already inserted
					JOptionPane.showMessageDialog(null, "This cheque is already interted to this invoice");
				} else {
					String newChequeNos = DataUtilities.concat(oldChequeNO, NewChequeNo);
					String newChequeDates = DataUtilities.concat(oldChequeDate, ChequeDate);

					stmt = connection.createStatement();
					String sql = "UPDATE `invoice` SET `ChequeNo`='"+newChequeNos+"',`ChequeDate`='"+newChequeDates+"' WHERE `InvoiceNo`='"+InvoiceNO+"'";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Successfully updated invoice with this cheque");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Oops, while updating invoice DataBase with new cheque details");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops, while updating invoice DataBase with new cheque details");
		}

	}

	public static void append_new_chequeNo_to_Invoice(String InvoiceNO, String chequeNo) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt1 = null;
		
		Statement stmt3 = null;
	

		String sql1 = "SELECT `ChequeNo` FROM `invoice` WHERE `InvoiceNo` = '" + InvoiceNO + "'";
		
		try {
			/////////////////////////////////////////////////////////////////////////////////
			stmt1 = connection.prepareStatement(sql1);
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if (rs1.next()) {

				String[] lp = DataUtilities.getSplited(rs1.getString(1));
				java.util.List<String> list = Arrays.asList(lp);
				if (list.contains(chequeNo)) {
					JOptionPane.showMessageDialog(null, "This cheque is already interted to this Invoice");
				} else {

					String ChequeNumbers = DataUtilities.concat(rs1.getString(1),(chequeNo));
					// System.out.println("this invoice no has these cheques : " +
					// rs1.getString(1));
					stmt3 = connection.createStatement();
					String sql3 = "UPDATE `invoice` SET `ChequeNo`='" + ChequeNumbers + "' WHERE InvoiceNo = '"
							+ InvoiceNO + "'";
					stmt3.executeUpdate(sql3);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"There is no Invoice exist with this Invoice No. please save this invoice first!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while adding new cheque to invoice DB");
		}
	}

	public static void append_newInvoiceNo_to_cheque(String newInvoiceNO, String ChequeNo,String Dealer) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		Statement stmt5 = null;

		String sql1 = "SELECT `InvoiceNo` ,`Dealername` FROM `cheque` WHERE `ChequeNo` = '" + ChequeNo + "'";
		String sql4 = "SELECT `ChequeNo` ,`ChequeDate`  FROM `cheque` WHERE `ChequeNo` = '" + ChequeNo + "'";
		String sql3 = "SELECT `InvoiceNo` FROM `invoice` WHERE `InvoiceNo` = '" + newInvoiceNO + "' AND `DealerName`='"+Dealer+"'";

		try {

			stmt3 = connection.prepareStatement(sql3);
			ResultSet rs2 = stmt3.executeQuery(sql3);

			if (rs2.next()) {// check invoice is exist?
				stmt4 = connection.prepareStatement(sql4);
				ResultSet rs3 = stmt3.executeQuery(sql4);
				if (rs3.next()) { // check if cheque is saved with full details
					String chequesdates = rs3.getString("ChequeDate");
					stmt1 = connection.prepareStatement(sql1);
					ResultSet rs1 = stmt1.executeQuery(sql1);
					while (rs1.next()) {
						String[] lp = DataUtilities.getSplited(rs1.getString("InvoiceNo"));
						java.util.List<String> list = Arrays.asList(lp);
						String[] lpp = DataUtilities.getSplited(rs1.getString("Dealername"));
						java.util.List<String> list1 = Arrays.asList(lp);
						
						
						if (list.contains(newInvoiceNO)) { // check if this new invoice is already inserted
							//TODO there is a error here dealer name check is not working
							JOptionPane.showMessageDialog(null, "This invoice is already interted");
						} else {
							String invoiceNumbers = DataUtilities.concat(rs1.getString("InvoiceNo"), newInvoiceNO);
							String dealerNames = DataUtilities.concat(rs1.getString("Dealername"), Dealer);
							// System.out.println("this invoice no has these cheques : " +
							// rs1.getString(1));
							stmt2 = connection.createStatement();
							String sql2 = "UPDATE `cheque` SET `InvoiceNo`='" + invoiceNumbers + "' , `DealerName`='" + dealerNames + "' WHERE ChequeNo = '"
									+ ChequeNo + "'";
							stmt2.executeUpdate(sql2);
							update_Invoice_with_newchequeNo(ChequeNo, newInvoiceNO, chequesdates); //TODO here cheque date?????????????????
							JOptionPane.showMessageDialog(null, "Invoice added to this cheque");
							
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "You need to Save cheque once to add another invoice to it!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "You need to save this invoice first!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while add Invoice Number to this chqeue  ");
		}
	}

}
