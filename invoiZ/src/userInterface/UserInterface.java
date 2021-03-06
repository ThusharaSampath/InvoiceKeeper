package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import dataSource.ContentFetcher;
import dataSource.DataClass;
import dataSource.DataDelete;
import dataSource.DataRepot;
import dataSource.DataStore;
import dataSource.DataUpdate;
import manager.chequeManager;
import manager.dealerManager;
import manager.invoiceManager;
import utilities.DataUtilities;
import utilities.utils;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

public class UserInterface {

	private JFrame frmInvoiz;
	private JTextField Amount;
	private JTextField InvoiceNo_input;
	private JTextField txtDealerName;
	private JTextField txtDealerTel;
	private JTextField txtperiod_D;
	private JTextField txtChequeNo_C;
	// private JTextField txtInvoiceNo_C;
	private JTextField txtCheckAmount_C;
	private JTextField txt_search_Invoice_by_No;
	private JTextField txt_search_cheque;
	private JTextField txt_search_dealer;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField txt_search_Invoice_by_Dealer;
	private JTextField txtCashAmount;
	private JTable tblReport_I;
	private JTable tbl_ChequeReport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frmInvoiz.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmInvoiz = new JFrame();
		frmInvoiz.setBackground(new Color(255, 250, 240));
		frmInvoiz.setTitle("InvoiZ");
		frmInvoiz.getContentPane().setEnabled(false);
		frmInvoiz.setBounds(100, 100, 1051, 675);
		frmInvoiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmInvoiz.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1015, 614);
		frmInvoiz.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		tabbedPane.addTab("Invoice", null, panel, null);
		panel.setLayout(null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Cheque", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAmount.setBounds(53, 164, 143, 30);
		panel.add(lblAmount);

		JLabel lblNewLabel_11 = new JLabel("please make sure select this again!!"); // this is waring when update mode
																					// of invoice is active
		lblNewLabel_11.setForeground(Color.RED);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_11.setBounds(586, 372, 305, 30);
		panel.add(lblNewLabel_11);

		lblNewLabel_11.setVisible(false); // intialy hide

		JComboBox<Object> periodDropdwn = new JComboBox<Object>();
		periodDropdwn.setFont(new Font("Tahoma", Font.BOLD, 16));
		periodDropdwn.setBounds(53, 372, 523, 30);
		panel.add(periodDropdwn);
		periodDropdwn.addItem(15);
		periodDropdwn.addItem(20);
		periodDropdwn.addItem(25);
		periodDropdwn.addItem(30);
		periodDropdwn.addItem(35);
		periodDropdwn.addItem(40);
		periodDropdwn.addItem(45);
		periodDropdwn.addItem(50);
		periodDropdwn.addItem(55);
		periodDropdwn.addItem(60);

		periodDropdwn.setSelectedItem(15);
		// ComboBoxModel<Object> period = periodDropdwn.getModel();
		// System.out.println(period);

		JLabel lblInvoiceNo = new JLabel("Invoice No.");
		lblInvoiceNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInvoiceNo.setBounds(53, 94, 243, 30);
		panel.add(lblInvoiceNo);

		InvoiceNo_input = new JTextField();
		InvoiceNo_input.setFont(new Font("Tahoma", Font.BOLD, 16));
		InvoiceNo_input.setBounds(53, 123, 523, 30);
		panel.add(InvoiceNo_input);
		InvoiceNo_input.setColumns(10);

		JLabel Date_Label = new JLabel("Today :");
		Date_Label.setFont(new Font("Tahoma", Font.BOLD, 30));
		Date_Label.setBounds(682, 426, 120, 35);
		panel.add(Date_Label);

		JLabel Date = new JLabel("New label");
		Date.setFont(new Font("Tahoma", Font.BOLD, 30));
		Date.setBounds(812, 428, 227, 30);
		panel.add(Date);
		Date.setText((timeManager.today()));// 2016/11/16 12:08:43

		JComboBox<Object> DealerDropdwn1 = new JComboBox<Object>();
		DealerDropdwn1.setFont(new Font("Tahoma", Font.BOLD, 16));
		DealerDropdwn1.setBounds(55, 53, 523, 30);
		panel.add(DealerDropdwn1);

		try {
			ResultSet result = DataClass.getDealerNames();
			while (result.next()) {
				DealerDropdwn1.addItem(result.getString("DealerName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DealerDropdwn1.setSelectedItem(null);

		JDateChooser dateChooser_I = new JDateChooser();
		dateChooser_I.setDateFormatString("yyyy-MM-dd");
		dateChooser_I.setBounds(53, 296, 523, 30);
		panel.add(dateChooser_I);

		txtCashAmount = new JTextField();
		txtCashAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCashAmount.setBounds(53, 449, 523, 30);
		panel.add(txtCashAmount);
		txtCashAmount.setColumns(10);

		JRadioButton rdbtn_short = new JRadioButton("Select if yes");
		rdbtn_short.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtn_short.setBounds(157, 234, 252, 30);
		panel.add(rdbtn_short);

		Amount = new JTextField();
		Amount.setFont(new Font("Tahoma", Font.BOLD, 16));
		Amount.setBounds(53, 195, 523, 30);
		panel.add(Amount);
		Amount.setColumns(10);
//		Amount.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent evt) {
//				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//					try {
//						// since i dont know how to get variable out of IF i put all in IF and again
//						// Else
//
//						if (rdbtn_short.isSelected()) {
//							String Short = "Short";
//							int period = (int) periodDropdwn.getSelectedItem();
//
//							long amount = Long.parseLong(Amount.getText());
//							// System.out.println("Amount is : " + Long.toString(amount));
//							String dateOfInvoice = ((JTextField) dateChooser_I.getDateEditor().getUiComponent())
//									.getText();
//							String deadline = timeManager.DateplusDays(dateOfInvoice, period);
//							// long invoiceNo = Long.parseLong(InvoiceNo_input.getText());
//							String invoiceNo = (InvoiceNo_input.getText());
//
//							String dealerName = (String) DealerDropdwn1.getSelectedItem();
//
//							long CashAmount = Long.parseLong(txtCashAmount.getText());
//							// long static chequeNo;
//							// String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(),
//							// rdobtnCheque_I);
//							// DealerName.setText(dealerCBox);
//
//							invoiceManager.getInstance().saveInvoice(invoiceNo, amount, CashAmount, dateOfInvoice,
//									deadline, dealerName, Short);
//						} else {
//							String Short = "No Short";
//							int period = (int) periodDropdwn.getSelectedItem();
//
//							long amount = Long.parseLong(Amount.getText());
//							// System.out.println("Amount is : " + Long.toString(amount));
//							String dateOfInvoice = ((JTextField) dateChooser_I.getDateEditor().getUiComponent())
//									.getText();
//							String deadline = timeManager.DateplusDays(dateOfInvoice, period);
//							String invoiceNo = (InvoiceNo_input.getText());
//							// System.out.println("invoice No. is : " + Long.toString(invoiceNo));
//
//							String dealerName = (String) DealerDropdwn1.getSelectedItem();
//							long CashAmount = Long.parseLong(txtCashAmount.getText());
//							// String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(),
//							// rdobtnCheque_I);
//							// DealerName.setText(dealerCBox);
//
//							invoiceManager.getInstance().saveInvoice(invoiceNo, amount, CashAmount, dateOfInvoice,
//									deadline, dealerName, Short);
//						}
//
//					} catch (Exception e) {
//						JOptionPane.showMessageDialog(null, "There is invalid input, please check!");
//					}
//				}
//				Amount.setText("");
//				InvoiceNo_input.setText("");
//				txtCashAmount.setText("");
//				dateChooser_I.setCalendar(null);
//				rdbtn_short.setSelected(false);
//
//			}
//		});

		JButton btn_save_I = new JButton("SAVE");
		btn_save_I.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_save_I.setBounds(16, 500, 320, 75);
		panel.add(btn_save_I);
		btn_save_I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!InvoiceNo_input.getText().isEmpty()) {

					try {
//since i dont know how to get variable out of IF i put all in IF and again Else

						if (rdbtn_short.isSelected()) {
							String Short = "Short";
							int period = (int) periodDropdwn.getSelectedItem();

							long amount = Long.parseLong(Amount.getText());
							// System.out.println("Amount is : " + Long.toString(amount));
							String dateOfInvoice = ((JTextField) dateChooser_I.getDateEditor().getUiComponent())
									.getText();
							String deadline = timeManager.DateplusDays(dateOfInvoice, period);
							// long invoiceNo = Long.parseLong(InvoiceNo_input.getText());
							String invoiceNo = (InvoiceNo_input.getText());
							long CashAmount = Long.parseLong(txtCashAmount.getText());
							String dealerName = (String) DealerDropdwn1.getSelectedItem();
							// long static chequeNo;
							// String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(),
							// rdobtnCheque_I);
							// DealerName.setText(dealer342x);
							if (amount >= CashAmount) {
								invoiceManager.getInstance().saveInvoice(invoiceNo, amount, CashAmount, dateOfInvoice,
										deadline, dealerName, Short);
							} else {
								JOptionPane.showMessageDialog(null,
										"Cash Amount can not be greater than Invoice Amount ");
							}
						} else {
							String Short = "No Short";
							int period = (int) periodDropdwn.getSelectedItem();

							long amount = Long.parseLong(Amount.getText());
							// System.out.println("Amount is : " + Long.toString(amount));
							String dateOfInvoice = ((JTextField) dateChooser_I.getDateEditor().getUiComponent())
									.getText();
							String deadline = timeManager.DateplusDays(dateOfInvoice, period);
							String invoiceNo = (InvoiceNo_input.getText());
							// System.out.println("invoice No. is : " + Long.toString(invoiceNo));

							String dealerName = (String) DealerDropdwn1.getSelectedItem();
							long CashAmount = Long.parseLong(txtCashAmount.getText());
							// String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(),
							// rdobtnCheque_I);
							// DealerName.setText(dealerCBox);

							if (amount >= CashAmount) {
								invoiceManager.getInstance().saveInvoice(invoiceNo, amount, CashAmount, dateOfInvoice,
										deadline, dealerName, Short);
							} else {
								JOptionPane.showMessageDialog(null,
										"Cash Amount can not be greater than Invoice Amount ");
							}
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "There is invalid input, please check!");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Don't forget to enter Invoice Number, since it is essential !");

				}
				Amount.setText("");
				InvoiceNo_input.setText("");
				txtCashAmount.setText("");
				dateChooser_I.setCalendar(null);
				rdbtn_short.setSelected(false);

			}
		});

		JButton btn_rest_I = new JButton("RESET");
		btn_rest_I.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_rest_I.setBounds(682, 500, 320, 75);
		panel.add(btn_rest_I);
		btn_rest_I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Amount.setText("");
				InvoiceNo_input.setText("");
				txtCashAmount.setText("");
				dateChooser_I.setCalendar(null);
				rdbtn_short.setSelected(false);

			}

		});
		// ComboBoxModel<Object> dealer = DealerDropdwn1.getModel();
		// System.out.println(dealer);

		JButton btnAddDealer = new JButton("Add Dealer");
		btnAddDealer.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnAddDealer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnAddDealer.setBounds(789, 14, 211, 69);
		panel.add(btnAddDealer);

		JLabel DealerName = new JLabel("Dealer");
		DealerName.setFont(new Font("Tahoma", Font.BOLD, 16));
		DealerName.setBounds(55, 14, 160, 30);
		panel.add(DealerName);

		JLabel lblperiod = new JLabel("Period");
		lblperiod.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblperiod.setBounds(53, 342, 109, 30);
		panel.add(lblperiod);

		JButton btnAddCheque = new JButton("Add Cheque");
		btnAddCheque.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnAddCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnAddCheque.setBounds(789, 100, 210, 69);
		panel.add(btnAddCheque);

		JButton btnInvoiceList = new JButton("Invoice List");
		btnInvoiceList.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnInvoiceList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnInvoiceList.setBounds(791, 176, 209, 60);
		panel.add(btnInvoiceList);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Dealer", null, panel_1, "Add new dealer");
		panel_1.setLayout(null);

		JButton btnDealersList = new JButton("Dealers List");
		btnDealersList.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDealersList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(4);
			}

		});
		btnDealersList.setToolTipText("saved dealers");
		btnDealersList.setBounds(785, 11, 215, 60);
		panel_1.add(btnDealersList);

		txtDealerName = new JTextField();
		txtDealerName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtDealerName.setBounds(49, 69, 569, 30);
		panel_1.add(txtDealerName);
		txtDealerName.setColumns(10);

		JLabel lblName = new JLabel("Name ");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(49, 37, 141, 30);
		panel_1.add(lblName);

		txtDealerTel = new JTextField();
		txtDealerTel.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtDealerTel.setBounds(49, 149, 569, 30);
		panel_1.add(txtDealerTel);
		txtDealerTel.setColumns(10);

		JLabel lblTel = new JLabel("Telephone Number");
		lblTel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTel.setBounds(49, 123, 205, 30);
		panel_1.add(lblTel);

		JLabel lblNewLabel = new JLabel("Period ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(49, 193, 100, 30);
		panel_1.add(lblNewLabel);

		JComboBox InvoiceNoDropdwn_C = new JComboBox();
		InvoiceNoDropdwn_C.setFont(new Font("Tahoma", Font.BOLD, 15));
		InvoiceNoDropdwn_C.setBounds(41, 121, 515, 30);
		panel_3.add(InvoiceNoDropdwn_C);

		JLabel lblunsetteled = new JLabel("0.0");
		lblunsetteled.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblunsetteled.setBounds(765, 124, 205, 25);
		panel_3.add(lblunsetteled);

		JComboBox<Object> DealerDropdwn_C = new JComboBox<Object>();
		DealerDropdwn_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		DealerDropdwn_C.setBounds(41, 56, 515, 30);
		panel_3.add(DealerDropdwn_C);
		DealerDropdwn_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvoiceNoDropdwn_C.removeAllItems();
				try {
					ResultSet result = DataUtilities.getInvoiceNoOfDealer((String) DealerDropdwn_C.getSelectedItem());
					while (result.next()) {

						InvoiceNoDropdwn_C.addItem(result.getString("InvoiceNo"));
					}
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}

			}
		});
		InvoiceNoDropdwn_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String InvoiceNo_C = ((String) InvoiceNoDropdwn_C.getSelectedItem());
				String DealerName_C = (String) DealerDropdwn_C.getSelectedItem();
				if (InvoiceNoDropdwn_C.getSelectedItem() != null) {
					long remain = DataUtilities.getUnsetteledAmountOfInvoice(DealerName_C, InvoiceNo_C);
					lblunsetteled.setText(Long.toString(remain));
				} else {
					lblunsetteled.setText("0.0");
				}

			}
		});

		txtperiod_D = new JTextField();
		txtperiod_D.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtperiod_D.setBounds(49, 218, 569, 30);
		panel_1.add(txtperiod_D);
		txtperiod_D.setColumns(10);
//		Amount.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent evt) {
//				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//					try {
//
//						String name = txtDealerName.getText();
//						long tel = Long.parseLong(txtDealerTel.getText());
//						int period = Integer.parseInt(txtperiod_D.getText());
//						if (!txtDealerName.getText().isEmpty()) {
//							dealerManager.saveDealer(name, tel, period);//
//						} else {
//							JOptionPane.showMessageDialog(null, "Don't foget to enter Dealer name it is Essential! ");
//						}
//
//					} catch (Exception e2) {
//						JOptionPane.showMessageDialog(null, "There is/are invalid input(s), please check!");
//					}
//					try {
//						ResultSet result = DataClass.getDealerNames();
//						while (result.next()) {
//							DealerDropdwn1.addItem(result.getString("DealerName"));
//							DealerDropdwn_C.addItem(result.getString("DealerName"));
//						}
//					} catch (SQLException er) {
//						// TODO Auto-generated catch block
//						er.printStackTrace();
//					}
//				}
//			}
//		});

		JButton btnSave_D = new JButton("SAVE");
		btnSave_D.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnSave_D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String name = txtDealerName.getText();
					long tel = Long.parseLong(txtDealerTel.getText());
					int period = Integer.parseInt(txtperiod_D.getText());
					if (!txtDealerName.getText().isEmpty()) {
						dealerManager.saveDealer(name, tel, period);//
						txtDealerName.setText("");
						txtDealerTel.setText("");
						txtperiod_D.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Don't foget to enter Dealer name it is Essential! ");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "There is/are invalid input(s), please check!");
				}
				try {
					ResultSet result = DataClass.getDealerNames();
					DealerDropdwn1.removeAll();
					while (result.next()) {
						DealerDropdwn1.addItem(result.getString("DealerName"));
						DealerDropdwn_C.addItem(result.getString("DealerName"));
					}
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
			}
		});
		btnSave_D.setBounds(10, 500, 320, 75);
		panel_1.add(btnSave_D);

		JButton btnReset_D = new JButton("RESET");
		btnReset_D.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnReset_D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtDealerName.setText("");
				txtDealerTel.setText("");
				txtperiod_D.setText("");
			}
		});
		btnReset_D.setBounds(680, 500, 320, 75);
		panel_1.add(btnReset_D);

		new ButtonGroup();
		// refresh btn in invoice
		JButton btnRefresh_I = new JButton("Refresh");
		btnRefresh_I.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRefresh_I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DealerDropdwn1.removeAllItems();
				try {
					ResultSet result = DataClass.getDealerNames();
					while (result.next()) {
						DealerDropdwn1.addItem(result.getString("DealerName"));
					}
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}

			}
		});
		btnRefresh_I.setBounds(602, 53, 140, 30);
		panel.add(btnRefresh_I);

		JLabel lblNewLabel_10 = new JLabel("Any Short ?");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_10.setBounds(53, 234, 143, 30);
		panel.add(lblNewLabel_10);

		JLabel lblNewLabel_12 = new JLabel("Date");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_12.setBounds(53, 275, 58, 20);
		panel.add(lblNewLabel_12);

		JLabel lblNewLabel_5 = new JLabel("Cash Amount");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(53, 413, 120, 25);
		panel.add(lblNewLabel_5);

		JButton btnUpdate_I = new JButton("Update");// updatebutton of invoice
		btnUpdate_I.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnUpdate_I.setBounds(346, 500, 320, 75);
		panel.add(btnUpdate_I);
		btnUpdate_I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!InvoiceNo_input.getText().isEmpty()) {
					try {
//since i dont know how to get variable out of IF i put all in IF and again Else

						if (rdbtn_short.isSelected()) {
							String Short = "Short";
							int period = (int) periodDropdwn.getSelectedItem();

							long amount = (long) Double.parseDouble(Amount.getText()); // TODO make it double
							// System.out.println("Amount is : " + Long.toString(amount));
							String dateOfInvoice = ((JTextField) dateChooser_I.getDateEditor().getUiComponent())
									.getText();
							String deadline = timeManager.DateplusDays(dateOfInvoice, period);
							// long invoiceNo = Long.parseLong(InvoiceNo_input.getText());
							String invoiceNo = (InvoiceNo_input.getText());
							long CashAmount = (long) Double.parseDouble(txtCashAmount.getText());
							String dealerName = (String) DealerDropdwn1.getSelectedItem();
							// long static chequeNo;
							// String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(),
							// rdobtnCheque_I);
							// DealerName.setText(dealer342x);

							DataUpdate.btnupdate_invoice(invoiceNo, amount, CashAmount, dateOfInvoice, deadline,
									dealerName, Short);
						} else {
							String Short = "No Short";
							int period = (int) periodDropdwn.getSelectedItem();

							long amount = (long) Double.parseDouble(Amount.getText());
							// System.out.println("Amount is : " + Long.toString(amount));
							String dateOfInvoice = ((JTextField) dateChooser_I.getDateEditor().getUiComponent())
									.getText();
							String deadline = timeManager.DateplusDays(dateOfInvoice, period);
							String invoiceNo = (InvoiceNo_input.getText());
							// System.out.println("invoice No. is : " + Long.toString(invoiceNo));

							String dealerName = (String) DealerDropdwn1.getSelectedItem();
							long CashAmount = (long) Double.parseDouble(txtCashAmount.getText());
							// String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(),
							// rdobtnCheque_I);
							// DealerName.setText(dealerCBox);

							DataUpdate.btnupdate_invoice(invoiceNo, amount, CashAmount, dateOfInvoice, deadline,
									dealerName, Short);

						}

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "There is invalid input, please check!");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Don't forget to enter Invoice Number, since it is essential !");

				}
				Amount.setText("");
				InvoiceNo_input.setText("");
				txtCashAmount.setText("");
				dateChooser_I.setCalendar(null);
				rdbtn_short.setSelected(false);

			}

		});
		btnUpdate_I.setEnabled(false);
		JToggleButton tglbtn_upadate_I = new JToggleButton("Update Mode");
		tglbtn_upadate_I.setBounds(790, 251, 210, 60);
		tglbtn_upadate_I.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel.add(tglbtn_upadate_I);

		tglbtn_upadate_I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtn_upadate_I.isSelected()) {

					if (!InvoiceNo_input.getText().isEmpty() & DealerDropdwn1.getSelectedItem() != null) {

						String invoiceNo = (InvoiceNo_input.getText());
						String dealerName = (String) DealerDropdwn1.getSelectedItem();
						if (DataUtilities.isInvoiceExist(dealerName, invoiceNo)) {

							try {
								Map<String, List<Object>> rs = DataUpdate.btnupdateStart_invoice(dealerName, invoiceNo);

								// here it should be a if (rs.next()) but it gives false , can't find why
								System.out.println(rs.toString());
								Amount.setText(Double.toString((double) rs.get("InvoiceAmount").get(0)));
								java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd")
										.parse(rs.get("InvoiceDate").get(0).toString());
								dateChooser_I.setDate(date1);
								txtCashAmount.setText(Double.toString((double) rs.get("CashAmount").get(0)));
								lblNewLabel_11.setVisible(true);
								btnUpdate_I.setEnabled(true);
								btn_save_I.setEnabled(false);

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Only saved Invoice can be Updated! so save this first");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"You must enter Invoice Number and select valid dealer name first");
					}
				} else {
					btnUpdate_I.setEnabled(false);
					btn_save_I.setEnabled(true);
					lblNewLabel_11.setVisible(false);
				}

			}

		});

		JButton btnChequeList = new JButton("Cheque List");
		btnChequeList.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnChequeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnChequeList.setBounds(785, 6, 215, 60);
		panel_3.add(btnChequeList);

		JLabel lblCheque = new JLabel("Cheque Number");
		lblCheque.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCheque.setBounds(41, 162, 158, 30);
		panel_3.add(lblCheque);

		txtChequeNo_C = new JTextField();
		txtChequeNo_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtChequeNo_C.setBounds(41, 190, 515, 30);
		panel_3.add(txtChequeNo_C);
		txtChequeNo_C.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Invoice Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(41, 91, 158, 30);
		panel_3.add(lblNewLabel_1);

//		txtInvoiceNo_C = new JTextField();        this should be removed just keep for test
//		txtInvoiceNo_C.setFont(new Font("Tahoma", Font.BOLD, 16));
//		txtInvoiceNo_C.setBounds(41, 119, 515, 30);
//		panel_3.add(txtInvoiceNo_C);
//		txtInvoiceNo_C.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("DealerName");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(41, 25, 158, 30);
		panel_3.add(lblNewLabel_2);

		try {
			ResultSet result = DataClass.getDealerNames();
			while (result.next()) {
				DealerDropdwn_C.addItem(result.getString("DealerName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDateChooser dateChooser_C = new JDateChooser();

		dateChooser_C.setDateFormatString("yyyy-MM-dd");
		dateChooser_C.setBounds(41, 280, 515, 30);
		panel_3.add(dateChooser_C);

		txtCheckAmount_C = new JTextField();
		txtCheckAmount_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCheckAmount_C.setBounds(42, 373, 515, 30);
		panel_3.add(txtCheckAmount_C);
		txtCheckAmount_C.setColumns(10);
//		txtCheckAmount_C.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent evt) {
//				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//					try {
//						String ChequeNo_C = (txtChequeNo_C.getText());
//						String InvoiceNo_C = ((String) InvoiceNoDropdwn_C.getSelectedItem());
//						long CheckAmount_C = Long.parseLong(txtCheckAmount_C.getText());
//						String DealerName_C = (String) DealerDropdwn_C.getSelectedItem();
//						String IssueDate_C = ((JTextField) dateChooser_C.getDateEditor().getUiComponent()).getText(); // TODO
//																														// fix
//																														// date
//																														// chooser
//						// DataStore.updateInvoice_cheque(ChequeNo_C, txtInvoiceNo_C.getText(),
//						// IssueDate_C);
//						if (!(txtChequeNo_C.getText()).isEmpty()) {
//							chequeManager.saveCheque(ChequeNo_C, DealerName_C, InvoiceNo_C, CheckAmount_C, IssueDate_C);
//						} else {
//							JOptionPane.showMessageDialog(null, "Don't forget to Insert Cheque number,it's essential");
//						}
//						// (long chequeNo,String DealerName,long InvoiceNo,long chequeAmount, String
//						// chequeDate)
//					} catch (Exception e) {
//						JOptionPane.showMessageDialog(null, "There is/are invalid input, please check!");
//					}
//				}
//			}
//		});

		JLabel lblNewLabel_3 = new JLabel("Cheque Amount");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(41, 343, 141, 30);
		panel_3.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Cheque Date");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(40, 248, 141, 30);
		panel_3.add(lblNewLabel_4);

		JButton btn_Save_C = new JButton("SAVE");
		btn_Save_C.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_Save_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// txtChequeNo_C,txtInvoiceNo_C,txtDealerName_C,txtCheckAmount_C,txtIssueDate_C,dateChooser_C
				try {
					String ChequeNo_C = (txtChequeNo_C.getText());
					String InvoiceNo_C = ((String) InvoiceNoDropdwn_C.getSelectedItem());
					long CheckAmount_C = Long.parseLong(txtCheckAmount_C.getText());
					String DealerName_C = (String) DealerDropdwn_C.getSelectedItem();
					String IssueDate_C = ((JTextField) dateChooser_C.getDateEditor().getUiComponent()).getText(); // TODO
																													// fix
																													// date
																													// chooser

					chequeManager.saveCheque(ChequeNo_C, DealerName_C, InvoiceNo_C, CheckAmount_C, IssueDate_C);
					txtChequeNo_C.setText("");
					txtCheckAmount_C.setText("");

					dateChooser_C.setCalendar(null);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "There is/are invalid input, please check!");
				}

			}
		});

		JButton appendInvoiceToCheque = new JButton("Add Invoice to cheque");
		appendInvoiceToCheque.setFont(new Font("Tahoma", Font.BOLD, 16));
		appendInvoiceToCheque.setBounds(577, 190, 241, 30);
		panel_3.add(appendInvoiceToCheque);
		appendInvoiceToCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!(txtChequeNo_C.getText()).isEmpty()) {
						long ChequeNo_C = Long.parseLong((txtChequeNo_C.getText()));
						System.out.println(ChequeNo_C);
						String InvoiceNo_C = ((String) InvoiceNoDropdwn_C.getSelectedItem());
						String DealerName_C = (String) DealerDropdwn_C.getSelectedItem();

						DataStore.append_newInvoiceNo_to_cheque(InvoiceNo_C, txtChequeNo_C.getText(), DealerName_C);// i
																													// put
						// add
						// update_Invoice_with_newchequeNo
						// in
						// append_newInvoiceNo_to_cheque
						// so i
						// dont
						// need
						// to
						// check
						// exixtance
						// again
						txtChequeNo_C.setText("");
						txtCheckAmount_C.setText("");

						dateChooser_C.setCalendar(null);
					} else {
						JOptionPane.showMessageDialog(null, "Enter Valid Cheque Number");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "There is/are invalid input, please check!");
					ex.printStackTrace();
				}
			}
		});

		btn_Save_C.setBounds(10, 500, 320, 75);
		panel_3.add(btn_Save_C);

		JButton btnReset_C = new JButton("RESET");
		btnReset_C.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnReset_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtChequeNo_C.setText("");
				// txtInvoiceNo_C.setText("");
				txtCheckAmount_C.setText("");
			}

		});
		btnReset_C.setBounds(680, 500, 320, 75);
		panel_3.add(btnReset_C);

		JButton btnRefresh_C = new JButton("Refresh");
		btnRefresh_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRefresh_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DealerDropdwn_C.removeAllItems();
				try {
					ResultSet result = DataClass.getDealerNames();
					while (result.next()) {
						DealerDropdwn_C.addItem(result.getString("DealerName"));
					}
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace(

					);
				}
			}
		});
		btnRefresh_C.setBounds(577, 56, 141, 30);
		panel_3.add(btnRefresh_C);
/////cheque update mode//////
		JButton btnUpdate_C = new JButton("Update");// updatebutton of invoice
		btnUpdate_C.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnUpdate_C.setBounds(343, 500, 320, 75);
		panel_3.add(btnUpdate_C);
		btnUpdate_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtCheckAmount_C.getText().isEmpty()) {
					try {
						String ChequeNo_C = (txtChequeNo_C.getText());

						double CheckAmount_C = Double.parseDouble(txtCheckAmount_C.getText());

						String IssueDate_C = ((JTextField) dateChooser_C.getDateEditor().getUiComponent()).getText(); // TODO

						DataUpdate.btnUpdateCheque(ChequeNo_C, CheckAmount_C, IssueDate_C);

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "There is invalid input, please check!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Don't forget to enter cheque Number, since it is essential !");

				}
				txtCheckAmount_C.setText("");

				dateChooser_C.setCalendar(null);

			}

		});
		btnUpdate_C.setEnabled(false);
		JToggleButton tglbtn_upadate_C = new JToggleButton("Update Mode");
		tglbtn_upadate_C.setBounds(790, 250, 210, 60);
		tglbtn_upadate_C.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_3.add(tglbtn_upadate_C);

		tglbtn_upadate_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtn_upadate_C.isSelected()) {

					if (!txtChequeNo_C.getText().isEmpty()) {

						String ChequeNo = txtChequeNo_C.getText();
						if (DataUtilities.isChequeExist(ChequeNo)) {
							Map<String, List<Object>> rs = DataUtilities.getChequeDetails(ChequeNo);
							try {

								// here it should be a if (rs.next()) but it gives false , can't find why
								txtCheckAmount_C.setText(Double.toString((double) rs.get("ChequeAmount").get(0)));
								java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd")
										.parse(rs.get("ChequeDate").get(0).toString());
								dateChooser_C.setDate(date2);
								DealerDropdwn_C.addItem(rs.get("DealeName"));
								txtChequeNo_C.setText(Integer.toString((int) rs.get("ChequeNo").get(0)));
								// TODO this must be tested

								btnUpdate_C.setEnabled(true);
								btn_Save_C.setEnabled(false);

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Save this Cheque first");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Enter valid ChequeNo");
					}
				} else {
					btnUpdate_C.setEnabled(false);
					btn_Save_C.setEnabled(true);

				}

			}

		});
////////////////////////////		
		JLabel lblNewLabel_13 = new JLabel("Unsetteled Amount :");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_13.setBounds(577, 124, 178, 25);
		panel_3.add(lblNewLabel_13);

///////////////////////invoice search panel /////////////////////////////		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Search Invoices", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Search by Invoice Number");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(38, 14, 253, 20);
		panel_4.add(lblNewLabel_6);

		txt_search_Invoice_by_No = new JTextField();
		txt_search_Invoice_by_No.setFont(new Font("Tahoma", Font.BOLD, 16));
		txt_search_Invoice_by_No.setToolTipText("Enter Invoice No.");
		txt_search_Invoice_by_No.setBounds(37, 40, 387, 30);
		panel_4.add(txt_search_Invoice_by_No);
		txt_search_Invoice_by_No.setColumns(10);
		txt_search_Invoice_by_No.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				table_1.removeAll();
				ContentFetcher.updateTable_byNO_Invoice(table_1, "invoice", txt_search_Invoice_by_No.getText());
				System.out.println(txt_search_Invoice_by_No.getText());
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		JButton btn_search_invoice_by_no = new JButton("Search");
		btn_search_invoice_by_no.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_search_invoice_by_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String txt = txt_search_Invoice_by_No.getText();
				if (txt.isEmpty()) {
					table_1.removeAll();
					System.out.println("ksdgfsdg");
					ContentFetcher.updateTable(table_1, "invoice");
					System.out.println(txt_search_Invoice_by_No.getText());
				} else {
					table_1.removeAll();
					ContentFetcher.updateTable_byNO_Invoice(table_1, "invoice", txt_search_Invoice_by_No.getText());
					System.out.println(txt_search_Invoice_by_No.getText());
				}

			}
		});
		btn_search_invoice_by_no.setBounds(442, 40, 121, 30);
		panel_4.add(btn_search_invoice_by_no);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 154, 990, 419);
		panel_4.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		txt_search_Invoice_by_Dealer = new JTextField();
//		txt_search_Invoice_by_Dealer.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});

		txt_search_Invoice_by_Dealer.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				table_1.removeAll();
				ContentFetcher.updateTable_by_dealer(table_1, "invoice", txt_search_Invoice_by_Dealer.getText());
				System.out.println(txt_search_Invoice_by_Dealer.getText());
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		txt_search_Invoice_by_Dealer.setFont(new Font("Tahoma", Font.BOLD, 16));
		txt_search_Invoice_by_Dealer.setBounds(37, 102, 387, 30);
		panel_4.add(txt_search_Invoice_by_Dealer);
		txt_search_Invoice_by_Dealer.setColumns(10);

		JButton btn_search_invoice_by_dealer = new JButton("Search");
		btn_search_invoice_by_dealer.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_search_invoice_by_dealer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = txt_search_Invoice_by_Dealer.getText();
				if (txt.isEmpty()) {
					table_1.removeAll();
					ContentFetcher.updateTable(table_1, "invoice");
					System.out.println(txt_search_Invoice_by_Dealer.getText());
				} else {
					table_1.removeAll();
					ContentFetcher.updateTable_by_dealer(table_1, "invoice", txt_search_Invoice_by_Dealer.getText());
					System.out.println(txt_search_Invoice_by_Dealer.getText());
				}

			}
		});
		btn_search_invoice_by_dealer.setBounds(442, 102, 121, 30);
		panel_4.add(btn_search_invoice_by_dealer);

		JLabel lblNewLabel_9 = new JLabel("Search Invoice by Dealer");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_9.setBounds(38, 82, 228, 20);
		panel_4.add(lblNewLabel_9);

		JButton btnNewButton = new JButton("This week");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_1.removeAll();
				ContentFetcher.WeekReport(table_1, "invoice");
			}
		});
		JButton btnInvoiceDelete = new JButton("Delete Invoice");
		btnInvoiceDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txt_search_Invoice_by_Dealer.getText();
				String No = txt_search_Invoice_by_No.getText();
				if (!name.isEmpty() & !No.isEmpty()) {
					if (DataUtilities.isInvoiceExist(name, No)) {
						DataDelete.invoiceDelete(name, No);
						txt_search_Invoice_by_Dealer.setText("");
						txt_search_Invoice_by_No.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "This Invoice does n");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please Enter Invoice number and Dealer Name!");
				}
			}
		});
		btnInvoiceDelete.setForeground(Color.BLACK);
		btnInvoiceDelete.setBackground(Color.RED);
		btnInvoiceDelete.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnInvoiceDelete.setBounds(764, 83, 236, 60);
		panel_4.add(btnInvoiceDelete);

		btnNewButton.setBounds(785, 14, 215, 60);
		panel_4.add(btnNewButton);
////////////////////////dealer search panel////////////////////////////
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Search Dealers", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Search Dealer by Name");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8.setBounds(43, 21, 244, 30);
		panel_5.add(lblNewLabel_8);

		txt_search_dealer = new JTextField();
		txt_search_dealer.setFont(new Font("Tahoma", Font.BOLD, 16));
		txt_search_dealer.setBounds(43, 60, 490, 30);
		panel_5.add(txt_search_dealer);
		txt_search_dealer.setColumns(10);

		txt_search_dealer.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				table_2.removeAll();
				ContentFetcher.updateTable_byName_dealer(table_2, "dealer", txt_search_dealer.getText());
				System.out.println(txt_search_dealer.getText());
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		JButton btnDealerDelete = new JButton("Delete Dealer");
		btnDealerDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dealer = txt_search_dealer.getText();

				DataDelete.DealerDelete(dealer);
				txt_search_dealer.setText("");

			}
		});
		btnDealerDelete.setForeground(Color.BLACK);
		btnDealerDelete.setBackground(Color.RED);
		btnDealerDelete.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDealerDelete.setBounds(785, 87, 215, 60);
		panel_5.add(btnDealerDelete);

		JButton btn_search_dealer = new JButton("Search");
		btn_search_dealer.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_search_dealer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String txt = txt_search_dealer.getText();
				if (txt.isEmpty()) {
					table_2.removeAll();
					ContentFetcher.updateTable_dealer(table_2, "dealer");
					System.out.println(txt_search_dealer.getText());
				} else {
					table_2.removeAll();
					ContentFetcher.updateTable_byName_dealer(table_2, "dealer", txt_search_dealer.getText());
					System.out.println(txt_search_dealer.getText());
				}

			}
		});
		btn_search_dealer.setBounds(568, 60, 129, 30);
		panel_5.add(btn_search_dealer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 160, 990, 420);
		panel_5.add(scrollPane);

		table_2 = new JTable();
		scrollPane.setViewportView(table_2);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Search Cheques", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Search Cheque by Number");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setBounds(52, 18, 275, 20);
		panel_2.add(lblNewLabel_7);

		txt_search_cheque = new JTextField();
		txt_search_cheque.setFont(new Font("Tahoma", Font.BOLD, 16));
		txt_search_cheque.setToolTipText("Enter Cheque No.");
		txt_search_cheque.setBounds(52, 49, 443, 30);
		panel_2.add(txt_search_cheque);
		txt_search_cheque.setColumns(10);
		txt_search_cheque.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				table_3.removeAll();
				ContentFetcher.updateTable_byName_cheque(table_3, "cheque", txt_search_cheque.getText());
				System.out.println(txt_search_cheque.getText());
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		JButton btn_search_cheque = new JButton("Search");
		btn_search_cheque.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_search_cheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = txt_search_cheque.getText();
				if (txt.isEmpty()) {
					table_3.removeAll();
					ContentFetcher.updateTable_cheque(table_3, "cheque");
					System.out.println(txt_search_cheque.getText());
				} else {
					table_3.removeAll();
					ContentFetcher.updateTable_byName_cheque(table_3, "cheque", txt_search_cheque.getText());
					System.out.println(txt_search_cheque.getText());
				}
			}
		});
		btn_search_cheque.setBounds(527, 49, 158, 30);
		panel_2.add(btn_search_cheque);

		JButton btnChequeDelete = new JButton("Delete Cheque");
		btnChequeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cheque = txt_search_cheque.getText();
				if (DataUtilities.isChequeExist(cheque)) {
					DataDelete.ChequeDelete(cheque);

				}
				txt_search_cheque.setText("");
			}
		});
		btnChequeDelete.setForeground(Color.BLACK);
		btnChequeDelete.setBackground(Color.RED);
		btnChequeDelete.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnChequeDelete.setBounds(744, 49, 256, 60);
		panel_2.add(btnChequeDelete);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 125, 990, 450);
		panel_2.add(scrollPane_2);

		table_3 = new JTable();
		scrollPane_2.setViewportView(table_3);
///////////////////////////////////Invoice Report
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Invoice Report", null, panel_6, null);
		panel_6.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("MONTH");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_14.setBounds(30, 84, 83, 25);
		panel_6.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("YEAR");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_15.setBounds(30, 28, 85, 25);
		panel_6.add(lblNewLabel_15);

		JComboBox cmbyear_I = new JComboBox();
		cmbyear_I.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbyear_I.setBounds(113, 25, 173, 30);
		panel_6.add(cmbyear_I);
		cmbyear_I.addItem(2019);
		cmbyear_I.addItem(2020);
		cmbyear_I.addItem(2021);
		cmbyear_I.addItem(2022);
		cmbyear_I.setSelectedItem(2020);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 152, 990, 423);
		panel_6.add(scrollPane_3);

		tblReport_I = new JTable();
		scrollPane_3.setViewportView(tblReport_I);

		JLabel lblNewLabel_16 = new JLabel("TOTAL AMOUNT");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_16.setBounds(325, 25, 135, 25);
		panel_6.add(lblNewLabel_16);

		JLabel lblTotalCash_I = new JLabel("TOTAL CASH");
		lblTotalCash_I.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalCash_I.setBounds(643, 81, 135, 25);
		panel_6.add(lblTotalCash_I);

		JLabel lblTotalUnsettale = new JLabel("TOTAL UNSETTLED");
		lblTotalUnsettale.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalUnsettale.setBounds(643, 25, 148, 25);
		panel_6.add(lblTotalUnsettale);

		JLabel lblTotal_I = new JLabel("0.0");
		lblTotal_I.setForeground(Color.BLACK);
		lblTotal_I.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTotal_I.setBounds(460, 23, 173, 25);
		panel_6.add(lblTotal_I);

		JLabel lbl_Total_Cash = new JLabel("0.0");
		lbl_Total_Cash.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_Total_Cash.setBounds(804, 80, 173, 25);
		panel_6.add(lbl_Total_Cash);

		JLabel lbl_unsetteled_I = new JLabel("0.0");
		lbl_unsetteled_I.setForeground(Color.RED);
		lbl_unsetteled_I.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_unsetteled_I.setBounds(804, 23, 156, 25);
		panel_6.add(lbl_unsetteled_I);

		JComboBox cmbMonth_I = new JComboBox();
		cmbMonth_I.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbMonth_I.setBounds(113, 81, 173, 30);
		panel_6.add(cmbMonth_I);
		cmbMonth_I.addItem("January");
		cmbMonth_I.addItem("February");
		cmbMonth_I.addItem("March");
		cmbMonth_I.addItem("April");
		cmbMonth_I.addItem("May");
		cmbMonth_I.addItem("June");
		cmbMonth_I.addItem("July");
		cmbMonth_I.addItem("September");
		cmbMonth_I.addItem("October");
		cmbMonth_I.addItem("November");
		cmbMonth_I.addItem("December");

//		cmbMonth_I.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int month = utils.MonthToInt((String) cmbMonth_I.getSelectedItem());
//				DataRepot.InvoiceReportByMonth(tblReport_I, month);
//				Map<String, List<Object>> numbers = DataRepot.InvoiceReportNumbers(month);
//
//				lblTotal_I.setText(Double.toString((double) numbers.get("totalAmount").get(0)));
//				lbl_Total_Cash.setText(Double.toString((double) numbers.get("totalCash").get(0)));
//				lbl_unsetteled_I.setText(Double.toString((double) numbers.get("totalUnsetteled").get(0)));
//
//			}
//		});
		JButton btnReport_I = new JButton("Generate");
		btnReport_I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int month = utils.MonthToInt((String) cmbMonth_I.getSelectedItem());
				int year = (int) cmbyear_I.getSelectedItem();
				DataRepot.InvoiceReportByMonth(tblReport_I, month);
				Map<String, List<Object>> numbers = DataRepot.InvoiceReportNumbers(year, month);

				lblTotal_I.setText(Double.toString((double) numbers.get("totalAmount").get(0)));
				lbl_Total_Cash.setText(Double.toString((double) numbers.get("totalCash").get(0)));
				lbl_unsetteled_I.setText(Double.toString((double) numbers.get("totalUnsetteled").get(0)));

			}
		});
		btnReport_I.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReport_I.setBounds(325, 66, 200, 50);
		panel_6.add(btnReport_I);

/////////////////////////////////////////report Cheque ///////////////////////////////////////////////		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Cheque Report", null, panel_7, null);
		panel_7.setLayout(null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 150, 990, 425);
		panel_7.add(scrollPane_4);

		tbl_ChequeReport = new JTable();
		scrollPane_4.setViewportView(tbl_ChequeReport);

		JLabel lblNewLabel_17 = new JLabel("MONTH");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_17.setBounds(30, 54, 83, 25);
		panel_7.add(lblNewLabel_17);

		JLabel lblNewLabel_18 = new JLabel("WEEK");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_18.setBounds(30, 93, 85, 25);
		panel_7.add(lblNewLabel_18);

		JComboBox cmbweek_C = new JComboBox();
		cmbweek_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbweek_C.setBounds(113, 90, 296, 30);
		panel_7.add(cmbweek_C);

		JLabel lblNewLabel_19 = new JLabel("TOTAL AMOUNT");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_19.setBounds(488, 23, 135, 25);
		panel_7.add(lblNewLabel_19);

		JLabel lblTotal_C = new JLabel("0.0");
		lblTotal_C.setForeground(Color.BLACK);
		lblTotal_C.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTotal_C.setBounds(623, 21, 173, 25);
		panel_7.add(lblTotal_C);

		JLabel lblNewLabel_20 = new JLabel("YEAR");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_20.setBounds(30, 14, 85, 25);
		panel_7.add(lblNewLabel_20);

		JComboBox cmbyear_C = new JComboBox();
		cmbyear_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbyear_C.setBounds(113, 11, 173, 30);
		panel_7.add(cmbyear_C);
		cmbyear_C.addItem(2019);
		cmbyear_C.addItem(2020);
		cmbyear_C.addItem(2021);
		cmbyear_C.addItem(2022);
		cmbyear_C.setSelectedItem(2020);

		JComboBox cmbMonth_C = new JComboBox();
		cmbMonth_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbMonth_C.setBounds(113, 51, 296, 30);
		panel_7.add(cmbMonth_C);
		cmbMonth_C.addItem("JANUARY");
		cmbMonth_C.addItem("FEBRUARY");
		cmbMonth_C.addItem("MARCH");
		cmbMonth_C.addItem("APRIL");
		cmbMonth_C.addItem("MAY");
		cmbMonth_C.addItem("JUNE");
		cmbMonth_C.addItem("JULY");
		cmbMonth_C.addItem("SEPTEMBER");
		cmbMonth_C.addItem("OCTOBER");
		cmbMonth_C.addItem("NOVEMBER");
		cmbMonth_C.addItem("DECEMBER");

		cmbMonth_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int year = (int) cmbyear_C.getSelectedItem();
				System.out.println(year);
				List<List<String>> list = utils.getWeeksDates((year),
						(String) cmbMonth_C.getSelectedItem());

				cmbweek_C.removeAllItems();
				for (List<String> list2 : list) {
					cmbweek_C.addItem(utils.concatDate(list2.get(0), list2.get(1)));
				}

			}
		});
//		cmbweek_C.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int year = Year.now().getValue();
//				if (cmbMonth_C.getSelectedItem() != null) {
//					List<List<String>> list = utils.getWeeksDates(Integer.toString(year),
//							(String) cmbMonth_C.getSelectedItem());
////TODO here it has harm less error
//					String[] dates = utils.splitDate((String) cmbweek_C.getSelectedItem(), " -- ");
//					DataRepot.chequeReportByMonthAndWeek(tbl_ChequeReport, dates[0], dates[1]);
//				
//					Map<String, List<Object>> numbers = DataRepot.ChequeReportNumbers(dates[0], dates[1]);
//					lblTotal_C.setText(Double.toString((double) numbers.get("totalAmount").get(0)));
//				}
//			}
//		});

		JButton btnReport_C = new JButton("Generate");
		btnReport_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = (int) cmbyear_C.getSelectedItem();
				if (cmbMonth_C.getSelectedItem() != null) {
					List<List<String>> list = utils.getWeeksDates((year),
							(String) cmbMonth_C.getSelectedItem());
//TODO here it has harm less error
					String[] dates = utils.splitDate((String) cmbweek_C.getSelectedItem(), " -- ");
					DataRepot.chequeReportByMonthAndWeek(tbl_ChequeReport, dates[0], dates[1]);

					Map<String, List<Object>> numbers = DataRepot.ChequeReportNumbers(dates[0], dates[1]);
					lblTotal_C.setText(Double.toString((double) numbers.get("totalAmount").get(0)));
				}

			}
		});
		btnReport_C.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReport_C.setBounds(488, 66, 200, 50);
		panel_7.add(btnReport_C);

	}
}
