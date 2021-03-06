package dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import entities.Cheque;
import entities.Dealer;
import entities.Invoice;

public class DataClass {
	public static int NUMBER_OF_CHEQUE;
	public static int NUMBER_OF_DEALERS;
	public static int NUMBER_OF_INVOICE;

	public static void saveInvoice(Invoice invoice) {

//		invoiceDB.add(invoice);
//		NUMBER_OF_INVOICE++;
//		System.out.print("invoice saved..");
		System.out.println(invoice);
		String InvoiceNO = invoice.getInvoiceNo();
		long invoiceAmount = invoice.getInvoiceAmount();
		long delerOfInvoice = invoice.getDealerOfInvoice();
		String ChequeNo = invoice.getChequeNo();
		String DateOfInvoice = invoice.getInvoiceDate();
		String invoiceDeadline = invoice.getInvoiceDeadline();
		String DealerName = invoice.getDealerName();
		String Short = invoice.getShort();
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		Statement stmt1 = null;
		String sql1 = "SELECT `InvoiceNo`, `DealerName`  FROM `invoice` WHERE `InvoiceNo` = '" + InvoiceNO + "'";
		try {
			stmt1 = connection.prepareStatement(sql1);
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if (rs1.next()) {
				String InvoiceNoold = rs1.getString("InvoiceNo");
				String dealerNameOld = rs1.getString("DealerName");
				System.out.println(InvoiceNoold.equals(InvoiceNO));
				System.out.println(DealerName.equals(dealerNameOld));
				if (InvoiceNoold.equals(InvoiceNO) && DealerName.equals(dealerNameOld)) {
					JOptionPane.showMessageDialog(null, "Oops, This invoice number with this dealer is already exist!");
				} else {
					stmt = connection.createStatement();
					String sql = "REPLACE INTO invoice "
							+ "(InvoiceNo,ChequeNo,InvoiceAmount,DealerId,DealerName,InvoiceDate,InvoiceDeadline,Short) "
							+ "VALUES ('" + InvoiceNO + "'," + ChequeNo + "," + invoiceAmount + "," + delerOfInvoice
							+ ",'" + DealerName + "','" + DateOfInvoice + "','" + invoiceDeadline + "','" + Short
							+ "')";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Invoice is saved");
				}
			} else {
				stmt = connection.createStatement();
				String sql = "REPLACE INTO invoice "
						+ "(InvoiceNo,ChequeNo,InvoiceAmount,DealerId,DealerName,InvoiceDate,InvoiceDeadline,Short) "
						+ "VALUES ('" + InvoiceNO + "'," + ChequeNo + "," + invoiceAmount + "," + delerOfInvoice + ",'"
						+ DealerName + "','" + DateOfInvoice + "','" + invoiceDeadline + "','" + Short + "')";
				System.out.println(sql);
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Invoice is saved");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while Saving Invoice ");
		}

//		System.out.print("\n"+ DataClass.invoiceDB.size());
	}

//TODO increse var lenths in db
	public static void saveDealer(Dealer Ob) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		String name = Ob.getDealerName();
		//String dealerCode = Ob.getDealerCode();
		String dealerCode = "NOT SET";
		long tel = Ob.getDealerTel();
		long period = Ob.getDealerTime();

		try {
			stmt = connection.createStatement();
			String sql = "REPLACE INTO dealer " + "(DealerName,DealerTel,DealerPeriod,DealerCode) " + "VALUES ('" + name
					+ "'," + tel + "," + period + ",'" + dealerCode + "')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Dealer is saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while Saving Dealer");
		}
		System.out.println(Ob);
	}

	@SuppressWarnings("null")
	public static long getDealerId(String dealerName) {
		Statement stmt = null;
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();

		try {
			String sql = "select DealerId from dealer where DealerName ='" + dealerName + "' ";
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return Long.parseLong(result.getString("DealerId"));
			} else {
				return (Long) null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching dealer ID!");
			return (Long) null;
		}

	}

	public static void saveCheque(Cheque cheque) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		String ChequeNo = cheque.getChequeNo();
		String InvoiceNo = cheque.getInvoiceNO();
		long DealerId = cheque.getDealerID();
		String DealerName = cheque.getDealerName();
		long ChequeAmount = cheque.getChequeAmount();
		String ChequeDate = cheque.getChequeDate();
		// String ChequeExpireDate = cheque.getChequeExpireDate();
		int ChequeExpireStatus = cheque.getChequeExpireStatus();
		int ChequePassStatus = cheque.getChequePassStatus();
		Statement stmt2 = null;
		Statement stmt3 = null;
		String sql2 = "SELECT `InvoiceNo` FROM `invoice` WHERE `InvoiceNo` = '" + InvoiceNo + "' AND `DealerName`='"+DealerName+"'";
		String sql3 = "SELECT `ChequeNo` FROM `cheque` WHERE `ChequeNo` = " + ChequeNo + "";
		try {

			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs1 = stmt2.executeQuery(sql2);
			stmt3 = connection.prepareStatement(sql3);
			ResultSet rs2 = stmt3.executeQuery(sql3);

			if (rs1.next()) { // invoice must be saved first
				if (!rs2.next()) { // cheque must not be saved if already saved
					stmt = connection.createStatement();
					String sql = "INSERT cheque "
							+ "(ChequeNo, InvoiceNo, DealerId, DealerName , ChequeAmount, ChequeDate, ChequeExpireStatus, ChequePassStatus)"
							+ "VALUES (" + ChequeNo + "," + InvoiceNo + "," + DealerId + ",'" + DealerName + "',"
							+ ChequeAmount + "," + "'" + ChequeDate + "'" + "," + ChequeExpireStatus + ","
							+ ChequePassStatus + ")";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Cheque is saved");

					//DataStore.append_new_chequeNo_to_Invoice(InvoiceNo, ChequeNo);
					DataStore.update_Invoice_with_newchequeNo((ChequeNo), InvoiceNo, ChequeDate);
				} else {
					JOptionPane.showMessageDialog(null, "This Cheque is Already exist");
				}
			} else {
				JOptionPane.showMessageDialog(null, "you need to save this invoice first");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while Saving cheque");
		}
		System.out.println(cheque);
	}

	public static ResultSet getDealerNames() {
		try {
			Statement stmt = null;
			Connection connection = null;
			connection = DatabaseConnect.dbConnector();
			String sql = "select DealerName from dealer";
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery(sql);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while getting dealer name!");
			return null;
		}

	}

}