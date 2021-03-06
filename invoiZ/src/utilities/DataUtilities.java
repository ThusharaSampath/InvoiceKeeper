package utilities;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import dataSource.DatabaseConnect;

public class DataUtilities {

	public static <E> E getSplited(String s) {
		s = s.replaceAll(",+$", "");
		s =s.replaceAll(",,", ",");
		s = s.replaceAll("^,+", "");
		System.out.println(s);
		String[] p = s.split(",");
		for (String string : p) {
			System.out.println(string);
		}
		System.out.println(p.length);
		return (E) p;

	}

	public static String concat(String s1, String newString) {
		if (s1 == null) {
			return newString;
		} else {
			return s1 + "," + newString;
		}

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

	public static Map<String, List<Object>> getInvoiceDetails(String DealerName, String invoiceNo) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt2 = null;
		String sql2 = "SELECT `InvoiceNo`, `ChequeNo`, `InvoiceAmount`, `DealerId`, `DealerName`, `InvoiceDate`, `InvoiceDeadline`, `Short`, `ChequeDate`,`ChequeAmount`,`CashAmount` FROM `invoice` WHERE `InvoiceNo` = '"
				+ invoiceNo + "' AND `DealerName`='" + DealerName + "' ";
		try {
			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs1 = stmt2.executeQuery(sql2);
			
			return utils.resultSetToArrayList(rs1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to access Invoice database");
			return null;
		} finally {
			try {
				if (stmt2 != null) {
					stmt2.close();
				}
				if (connection != null) {
					connection.close();
				}
				;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean isChequeExist(String ChequeNO) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt2 = null;
		String sql2 = "SELECT `ChequeNo` FROM `cheque` WHERE `ChequeNo` = '" + ChequeNO + "'";
		// String sql2 = "SELECT * FROM cheque WHERE 1";
		try {
			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs1 = stmt2.executeQuery(sql2);
			if (rs1.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to access Cheque database");
			return false;
		} finally {
			try {
				if (stmt2 != null) {
					stmt2.close();
				}
				if (connection != null) {
					connection.close();
				}
				;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static  Map<String, List<Object>> getChequeDetails(String ChequeNO) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt2 = null;
		String sql2 = "SELECT `ChequeNo`, `InvoiceNo`, `DealerName`, `ChequeAmount`, `ChequeDate`, `ChequeExpireDate`, `ChequeExpireStatus`, `ChequePassStatus` FROM `cheque` WHERE `ChequeNo`='"
				+ ChequeNO + "'";
		try {
			stmt2 = connection.prepareStatement(sql2);
			ResultSet rs1 = stmt2.executeQuery(sql2);
			return utils.resultSetToArrayList(rs1);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to access Cheque database");
			return null;
		} finally {
			try {
				if (stmt2 != null) {
					stmt2.close();
				}
				if (connection != null) {
					connection.close();
				}
				;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static double addChequeAmount_invoice(String DealerName, String invoiceNo, double amount) {

		Map<String, List<Object>> rs = getInvoiceDetails(DealerName, invoiceNo);
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		System.out.println(amount);
		try {
			double oldamount = (double) rs.get("ChequeAmount").get(0);
			System.out.println(oldamount);
			amount = amount + oldamount;
			System.out.println(amount);
			String sql = "UPDATE `invoice` SET `ChequeAmount`= " + amount + " WHERE InvoiceNo = '" + invoiceNo
					+ "' AND `DealerName` ='" + DealerName + "'";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
				;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return amount;
	}

	public static double addAmount_cheque(String ChequeNo, double amount) {

		Map<String, List<Object>> rs = getChequeDetails(ChequeNo);
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		try {
			double oldamount = (double) rs.get("ChequeAmount").get(0);

			amount = amount + oldamount;

			String sql = "UPDATE `cheque` SET `ChequeAmount`= " + amount + " WHERE `ChequeNo` = '" + ChequeNo + "'";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
				;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return amount;
	}

	public static void updateInvoice(String invoiceNo, long amount, long CashAmount, String dateOfInvoice,
			String deadline, String dealerName, String Short) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		try {

			String sql = "UPDATE `invoice` SET `InvoiceAmount`=" + amount + ",`InvoiceDate`='" + dateOfInvoice
					+ "',`InvoiceDeadline`='" + deadline + "',`Short`='" + Short + "',`CashAmount`=" + CashAmount
					+ " WHERE `InvoiceNo` = '" + invoiceNo + "' AND`DealerName` = '" + dealerName + "'";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Invoice is successfully updated ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to update Invoice");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
				;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static ResultSet getInvoiceNoOfDealer(String DealerName) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		String sql = "SELECT `InvoiceNo` FROM `invoice` WHERE `DealerName` = '" + DealerName + "'";
		try {

			stmt = connection.prepareStatement(sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			return rs1;
			// JOptionPane.showMessageDialog(null, "Invoice is successfully updated ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to update Invoice");
			e.printStackTrace();
			return null;
		}

	}

	public static long getUnsetteledAmountOfInvoice(String DealerName, String InvoiceNo) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		String sql = "Select (`InvoiceAmount`-`CashAmount`-`ChequeAmount`) AS remain FROM `invoice` WHERE `DealerName`='"
				+ DealerName + "' AND`InvoiceNo`='" + InvoiceNo + "'";
		try {

			stmt = connection.prepareStatement(sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				return rs1.getLong("remain");
			} else {
				return (Long) null;
			}
			// JOptionPane.showMessageDialog(null, "Invoice is successfully updated ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to Reach DataBase");
			e.printStackTrace();
			return 0;
		}

	}

	public static boolean isContain(String oldChequeNO, String newChequeNo) {
		if (oldChequeNO == null) {

			return false;
		}
		String[] ar = DataUtilities.getSplited(oldChequeNO);
		java.util.List<String> list = Arrays.asList(ar);
		if (list.contains(newChequeNo)) {
			return true;
		} else {
			return false;
		}
	}

}