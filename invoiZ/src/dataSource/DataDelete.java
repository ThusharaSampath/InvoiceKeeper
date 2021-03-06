package dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Util;

import utilities.DataUtilities;
import utilities.utils;

public class DataDelete {

	public static void invoiceDelete(String DealerName, String InvoiceNo) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			Map<String, List<Object>> invo = DataUtilities.getInvoiceDetails(DealerName, InvoiceNo);
			List<Object> chequeNos = invo.get("ChequeNo");

			String query1 = "DELETE FROM  invoice WHERE `InvoiceNo`='" + InvoiceNo + "' AND `DealerName`='" + DealerName
					+ "'";
			stmt.executeUpdate(query1);
/////////////////////////////////////////////////////////////////

			for (Object object : chequeNos) {
				String chequeNo = object.toString();

				if (DataUtilities.isChequeExist(chequeNo)) {
					Connection conn = null;
					conn = DatabaseConnect.dbConnector();
					Statement stmt1 = null;
					try {

						Map<String, List<Object>> cheque = DataUtilities.getChequeDetails(chequeNo);
						String OldInvoiceNos = (String) cheque.get("InvoiceNo").get(0);

						// String OldDealers = (String) cheque.get("DealerName").get(0);
						String newChequeNO = utils.deleteSelected(OldInvoiceNos, InvoiceNo);

						/// not reemoving dealer names

						String sql = "UPDATE `cheque` SET `InvoiceNo`='" + newChequeNO + "' WHERE `ChequeNo`='"
								+ chequeNo + "'";

						stmt1 = conn.createStatement();
						stmt1.executeUpdate(sql);
					} catch (Exception e) {
						System.out.println("Error at DATADELETE CHEQUE");
						e.printStackTrace();
					} finally {
						try {
							if (stmt1 != null) {
								stmt1.close();
							}
							if (conn != null) {
								conn.close();
							}
							;

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
/////////////////////////////////////////////////////////////
			// DataDelete.RemoveInvoiceFromCheques(chequeNos,DealerName,InvoiceNo);

			JOptionPane.showMessageDialog(null, "Invoice is deleted successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Something went wrong while deleting Invoice!");
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

	public static void DealerDelete(String dealer) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			String query1 = "DELETE FROM  dealer WHERE `DealerName`='" + dealer + "'";
			stmt.executeUpdate(query1);
			JOptionPane.showMessageDialog(null, "Dealer is deleted successfully");
/////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while deleting Dealer!");
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

	public static void ChequeDelete(String cheque) {
		Connection connection = null;
		connection = DatabaseConnect.dbConnector();
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			String query1 = "DELETE FROM  cheque WHERE `ChequeNo`='" + cheque + "'";

			Map<String, List<Object>> cheq = DataUtilities.getChequeDetails(cheque);
			List<Object> InvoiceNos = cheq.get("InvoiceNo");
			List<Object> DealerNames = cheq.get("DealerName");
			double ChequeAmount = (double) cheq.get("ChequeAmount").get(0);
		
		
			stmt.executeUpdate(query1);
////////////////////////////////////////////////////////////////////////////			
			for (int j = 0; j < InvoiceNos.size(); j++) {

				String InvoiceNo = InvoiceNos.get(j).toString();
				String DealerName = DealerNames.get(j).toString();

				if (DataUtilities.isInvoiceExist(DealerName, InvoiceNo)) {
					Connection conn = null;
					conn = DatabaseConnect.dbConnector();
					Statement stmt1 = null;
					try {

						Map<String, List<Object>> invo = DataUtilities.getInvoiceDetails(DealerName, InvoiceNo);
						String OldChequeNos = (String) invo.get("ChequeNo").get(0);
						String newChequeNO = utils.deleteSelected(OldChequeNos, cheque);
						double oldchequeAmount = (double) invo.get("ChequeAmount").get(0);
						double newChequeAmount = oldchequeAmount - ChequeAmount;

						// String OldDealers = (String) cheque.get("DealerName").get(0);

						String sql = "UPDATE `invoice` SET `ChequeNo`='" + newChequeNO + "',`ChequeAmount`="
								+ newChequeAmount + " WHERE `InvoiceNo`='" + InvoiceNo + "' AND `DealerName`='"
								+ DealerName + "' ";

						stmt1 = conn.createStatement();
						stmt1.executeUpdate(sql);
					} catch (Exception e) {
						System.out.println("Error at DATADELETE CHEQUE");
						e.printStackTrace();
					} finally {
						try {
							if (stmt1 != null) {
								stmt1.close();
							}
							if (conn != null) {
								conn.close();
							}
							;

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

///////////////////////////////////////////////////////////////////////////			

			stmt.executeUpdate(query1);
			JOptionPane.showMessageDialog(null, "Cheque is deleted successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while deleting Cheque!");
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

}
