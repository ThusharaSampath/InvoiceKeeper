package dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import utilities.DataUtilities;

public class DataUpdate {

	public static void add_chequeNo_to_invoice(String DealerName, String InvoiceNo, String ChequeNo,
			double ChequeAmount, String ChequeDate) {
		if (DataUtilities.isInvoiceExist(DealerName, InvoiceNo)) { // check is invoice exist
			try {
				Map<String, List<Object>> rs1 = DataUtilities.getInvoiceDetails(DealerName, InvoiceNo);
				String oldChequeNos = (String) rs1.get("ChequeNO").get(0);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(null, "You need to save this invoice first!");
		}

	}

	public static Map<String, List<Object>> btnupdateStart_invoice(String DealerName, String InvoiceNo) {

		try {

			Map<String, List<Object>> rs1 = DataUtilities.getInvoiceDetails(DealerName, InvoiceNo);
			
			return rs1;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Oops, something went wrong!");
			e.printStackTrace();
			return null;
		}

	}

	public static void btnupdate_invoice(String invoiceNo, long amount, long CashAmount, String dateOfInvoice,
			String deadline, String dealerName, String Short) {

		try {
			DataUtilities.updateInvoice(invoiceNo, amount, CashAmount, dateOfInvoice, deadline, dealerName, Short);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Oops, something went wrong!");
			e.printStackTrace();

		}

	}

	public static void btnUpdateCheque(String chequeNo_C, double checkAmount_C, String issueDate_C) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		try {

			String sql = "UPDATE `cheque` SET `ChequeAmount`="+checkAmount_C+",`ChequeDate`='"+issueDate_C+"' WHERE `ChequeNo`='"+chequeNo_C+"'";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Cheque is successfully updated ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to update Invoice");
			e.printStackTrace();
		}

	}

}
