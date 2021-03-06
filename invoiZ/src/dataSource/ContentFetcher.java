package dataSource;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import userInterface.timeManager;

public class ContentFetcher {

	public static void updateTable(JTable ui_table, String db_table) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		// TODO add dealer name to invoice and cheque tables
		String sql = "SELECT InvoiceNo,ChequeNo,InvoiceAmount,DealerName,InvoiceDate,InvoiceDeadline,Short,CashAmount FROM "
				+ db_table+" ORDER BY `InvoiceDeadline`";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ui_table.setModel(DbUtils.resultSetToTableModel(rs));
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
			e.printStackTrace();
		}finally {
			try {		
				 if (stmt != null) { stmt.close(); }
				 if (connection != null) {connection.close();};
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

	}
	
	public static void WeekReport(JTable ui_table, String db_table) {
		
		String monday = timeManager.getweek();
		
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		// TODO add dealer name to invoice and cheque tables
		String sql = "SELECT InvoiceNo,ChequeNo,InvoiceAmount,DealerName,InvoiceDate,InvoiceDeadline,Short,CashAmount FROM "
				+ db_table+" WHERE `InvoiceDate`>= '"+monday+"' ORDER BY `InvoiceDate`";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ui_table.setModel(DbUtils.resultSetToTableModel(rs));
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
			e.printStackTrace();
		}finally {
			try {		
				 if (stmt != null) { stmt.close(); }
				 if (connection != null) {connection.close();};
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

	}

	public static void updateTable_byNO_Invoice(JTable ui_table, String db_table, String InvoiceNO) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
//TODO add dealer name to invoice and cheque tables
		String sql = "SELECT `InvoiceNo`, `ChequeNo`, `InvoiceAmount`,`DealerName` , `InvoiceDate`, `InvoiceDeadline`,'Short',`CashAmount` FROM `"
				+ db_table + "` WHERE `InvoiceNo` LIKE '" + InvoiceNO + "%'";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ui_table.setModel(DbUtils.resultSetToTableModel(rs));
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
			e.printStackTrace();
		}finally {
			try {		
				 if (stmt != null) { stmt.close(); }
				 if (connection != null) {connection.close();};
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

	}
	
	public static void updateTable_by_dealer(JTable ui_table, String db_table, String Dealername) {

		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
//TODO add dealer name to invoice and cheque tables
		String sql = "SELECT `InvoiceNo`, `ChequeNo`, `InvoiceAmount`, `DealerName`, `InvoiceDate`, `InvoiceDeadline`,'Short',`CashAmount` FROM `invoice` WHERE `DealerName` LIKE '"+Dealername+"%'";

		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);
			ui_table.setModel(DbUtils.resultSetToTableModel(rs));
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
			e.printStackTrace();
		}finally {
			try {		
				 if (stmt != null) { stmt.close(); }
				 if (connection != null) {connection.close();};
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

	}

	public static void updateTable_dealer(JTable ui_table, String db_table) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		// TODO add dealer name to invoice and cheque tables
		String sql = "SELECT `DealerName`, `DealerTel`, `DealerPeriod` FROM `dealer`";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ui_table.setModel(DbUtils.resultSetToTableModel(rs));
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
			e.printStackTrace();
		}finally {
			try {		
				 if (stmt != null) { stmt.close(); }
				 if (connection != null) {connection.close();};
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

	}

	public static void updateTable_byName_dealer(JTable ui_table, String db_table, String dealerName) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
//TODO add dealer name to invoice and cheque tables
		String sql = "SELECT `DealerName`, `DealerTel`, `DealerPeriod` FROM `dealer` WHERE `DealerName` LIKE  '"
				+ dealerName + "%'";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ui_table.setModel(DbUtils.resultSetToTableModel(rs));
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
			e.printStackTrace();
		}finally {
			try {		
				 if (stmt != null) { stmt.close(); }
				 if (connection != null) {connection.close();};
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

	}

	public static void updateTable_cheque(JTable ui_table, String db_table) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
		// TODO add dealer name to invoice and cheque tables
		String sql = "SELECT `ChequeNo`, `InvoiceNo`, `ChequeAmount`, `ChequeDate` FROM `cheque` ORDER BY `ChequeDate`";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ui_table.setModel(DbUtils.resultSetToTableModel(rs));
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
			e.printStackTrace();
		}finally {
			try {		
				 if (stmt != null) { stmt.close(); }
				 if (connection != null) {connection.close();};
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

	}

	public static void updateTable_byName_cheque(JTable ui_table, String db_table, String chequeNo) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;
//TODO add dealer name to invoice and cheque tables
		String sql = "SELECT `ChequeNo`, `InvoiceNo`, `DealerName`, `ChequeAmount`, `ChequeDate` FROM `cheque` WHERE `ChequeNo` LIKE  '"
				+ chequeNo + "%'";
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ui_table.setModel(DbUtils.resultSetToTableModel(rs));
			// JOptionPane.showMessageDialog(null, "tables are loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, something went wrong while fetching data From Database!");
			e.printStackTrace();
		}finally {
			try {		
				 if (stmt != null) { stmt.close(); }
				 if (connection != null) {connection.close();};
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

	}
	
	

}

