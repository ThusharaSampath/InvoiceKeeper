package dataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import utilities.DataUtilities;

public class DataStore {
	public static void update_Invoice_with_newchequeNo(String NewChequeNo, String InvoiceNO, String ChequeDate,
			String DealerName, double chequeAmount) { // method
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
		String sql1 = "SELECT `InvoiceNo`, `ChequeNo`,`ChequeDate`,`ChequeAmount` FROM `invoice` WHERE `InvoiceNo`='"
				+ InvoiceNO + "' AND `DealerName` = '" + DealerName + "'";
		try {
			stmt1 = connection.prepareStatement(sql1);
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if (rs1.next()) {

				String oldChequeNO = rs1.getString("ChequeNo");
				String oldChequeDate = rs1.getString("ChequeDate");
				double oldChequeAmount = rs1.getLong("ChequeAmount");

				if (DataUtilities.isContain(oldChequeNO, NewChequeNo)) { // check if this new cheque is already inserted
					JOptionPane.showMessageDialog(null, "This cheque is already interted to this invoice");
				} else {
					String newChequeNos = DataUtilities.concat(oldChequeNO, NewChequeNo);
					String newChequeDates = DataUtilities.concat(oldChequeDate, ChequeDate);
					double newChequeAmount = oldChequeAmount + chequeAmount;
					stmt = connection.createStatement();
					String sql = "UPDATE `invoice` SET `ChequeNo`='" + newChequeNos + "',`ChequeDate`='"
							+ newChequeDates + "' , `ChequeAmount`=" + newChequeAmount + "  WHERE `InvoiceNo`='"
							+ InvoiceNO + "' AND `DealerName` = '" + DealerName + "'";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Successfully updated invoice with this cheque");
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"Oops,Error while updating invoice DataBase with new cheque details");

			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Oops,Error while updating invoice DataBase with new cheque details");
		}

	}

	public static void append_newInvoiceNo_to_cheque(String newInvoiceNO, String ChequeNo, String Dealer) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		if (DataUtilities.isChequeExist(ChequeNo)) {
			Map<String, List<Object>> rs1 = DataUtilities.getInvoiceDetails(Dealer, newInvoiceNO);
			Map<String, List<Object>> rs2 = DataUtilities.getChequeDetails(ChequeNo);

			try {
				// from cheque db
				Date date = (Date) rs2.get("ChequeDate").get(0);
				String chequeDate = date.toString();
				//String chequeDate = (String) rs2.get("ChequeDate").get(0);
				double ChequeAmount = (double) rs2.get("ChequeAmount").get(0);// from cheque db
				String OldInvoiceNo = (String) rs2.get("InvoiceNo").get(0);// from cheque db
				String OldDealerName = (String) rs2.get("DealerName").get(0);

				String OldChequeNo = (String) rs1.get("ChequeNo").get(0);// from invoice db
				String OldChequeDate = (String) rs1.get("ChequeDate").get(0);// from invoice db
				double OldChequeAmount = (double) rs1.get("ChequeAmount").get(0);

				if (!DataUtilities.isContain(OldInvoiceNo, newInvoiceNO)) {
					////////////// update invoice with cheque///////////

					update_Invoice_with_newchequeNo(ChequeNo, newInvoiceNO, chequeDate, Dealer, ChequeAmount);

					///////////////////////////////////////////////////
					///////////// update cheque with invoice////////////
					String newDealerName = DataUtilities.concat(OldDealerName, Dealer);
					String newInvoiceNo = DataUtilities.concat(OldInvoiceNo, newInvoiceNO);
					stmt = connection.createStatement();
					String sql = "UPDATE `cheque` SET `DealerName`= '" + newDealerName + "',`InvoiceNo`='"
							+ newInvoiceNo + "'  WHERE `ChequeNo`='" + ChequeNo + "'";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Invoice is added to cheque Successfully");
					///////////////////////////////////////////////////
				} else {
					JOptionPane.showMessageDialog(null, "This invoice is already added to this cheque");
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!");
				e.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(null, "You need to save this Cheque first!");
		}

	}

}
