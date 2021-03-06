package dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import utilities.utils;

public class DataRepot {

	public static void InvoiceReportByMonth(JTable ui_table, int month) {
//TODO add year select option
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
//TODO add dealer name to invoice and cheque tables
		String sql = "SELECT `InvoiceNo`, `ChequeNo`, `DealerName`, `InvoiceDate`, `InvoiceDeadline`,'Short',`InvoiceAmount`,`CashAmount`,`ChequeAmount`,(`InvoiceAmount`-`CashAmount`-`ChequeAmount`) AS 'NETAMOUNT' FROM `invoice` WHERE MONTH(`InvoiceDate`) = "
				+ month + ";";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Object p = DbUtils.resultSetToTableModel(rs);
			if (p != null) {
				ui_table.setModel((TableModel) p);
			}
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
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

	public static Map<String, List<Object>> InvoiceReportNumbers(int year,int month) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;//TODO here we have unhandled error
		System.out.println(year);
		String sql = "SELECT SUM(`InvoiceAmount`) AS `totalAmount` , SUM(`CashAmount`) AS `totalCash`, SUM(`InvoiceAmount`-`CashAmount`-`ChequeAmount`) AS `totalUnsetteled`FROM invoice WHERE MONTH(`InvoiceDate`) = "+month+" AND YEAR (`InvoiceDate`)="+year+" ";
		try {

			stmt = connection.prepareStatement(sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			
			return utils.resultSetToArrayList(rs1);
			// JOptionPane.showMessageDialog(null, "Invoice is successfully updated ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to Reach DataBase");
			e.printStackTrace();
			return null;
		}finally {
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

	public static void chequeReportByMonthAndWeek(JTable ui_table, String Startadte, String Enddate) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
//TODO add dealer name to invoice and cheque tables
		String sql = "SELECT `ChequeNo`,`InvoiceNo`,`ChequeAmount`,`ChequeDate` FROM `cheque` WHERE `ChequeDate`>='"
				+ Startadte + "' AND `ChequeDate` <= '" + Enddate + "' ";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Object p = DbUtils.resultSetToTableModel(rs);
			if (p != null) {
				ui_table.setModel((TableModel) p);
			}
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
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

	public static Map<String, List<Object>> ChequeReportNumbers(String Startadte, String Enddate) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		String sql = "SELECT SUM(`ChequeAmount`) AS `totalAmount` FROM cheque WHERE `ChequeDate`>='" + Startadte
				+ "' AND `ChequeDate` <= '" + Enddate + "' ";
		try {

			stmt = connection.prepareStatement(sql);
			ResultSet rs1 = stmt.executeQuery(sql);
			return utils.resultSetToArrayList(rs1);
			// JOptionPane.showMessageDialog(null, "Invoice is successfully updated ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to Reach DataBase");
			e.printStackTrace();
			return null;
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
}
