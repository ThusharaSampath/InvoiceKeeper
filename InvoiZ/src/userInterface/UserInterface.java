package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import dataSource.DataStore;
import manager.chequeManager;
import manager.dealerManager;
import manager.invoiceManager;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInterface {

	private JFrame frmInvoiz;
	private JTextField Amount;
	private JTextField InvoiceNo_input;
	private JTextField txtDealerName;
	private JTextField txtDealerTel;
	private JTextField txtperiod_D;
	private JTextField txtChequeNo_C;
	private JTextField txtInvoiceNo_C;
	private JTextField txtCheckAmount_C;
	private JTextField txtChequeNo_I;
	private JTextField txt_search_Invoice_by_No;
	private JTextField txt_search_cheque;
	private JTextField txt_search_dealer;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField txt_search_Invoice_by_Dealer;

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
		frmInvoiz.setResizable(false);
		frmInvoiz.setTitle("InvoiZ");
		frmInvoiz.getContentPane().setEnabled(false);
		frmInvoiz.setBounds(100, 100, 1090, 675);
		frmInvoiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmInvoiz.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1054, 614);
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
		lblAmount.setBounds(53, 413, 143, 30);
		panel.add(lblAmount);

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
		Date_Label.setBounds(640, 334, 120, 35);
		panel.add(Date_Label);

		JLabel Date = new JLabel("New label");
		Date.setFont(new Font("Tahoma", Font.BOLD, 30));
		Date.setBounds(770, 334, 227, 30);
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

		JRadioButton rdbtn_short = new JRadioButton("Select if yes");
		rdbtn_short.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtn_short.setBounds(157, 234, 252, 30);
		panel.add(rdbtn_short);

		txtChequeNo_I = new JTextField();
		txtChequeNo_I.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtChequeNo_I.setBounds(53, 197, 523, 30);
		panel.add(txtChequeNo_I);
		txtChequeNo_I.setColumns(10);
		txtChequeNo_I.setEnabled(false);

		JRadioButton rdobtnCheque_I = new JRadioButton("Pay With Check Today");
		rdobtnCheque_I.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdobtnCheque_I.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdobtnCheque_I.isSelected()) {
					txtChequeNo_I.setEnabled(true);
				} else {
					txtChequeNo_I.setEnabled(false);
				}
			}
		});
		rdobtnCheque_I.setBounds(157, 160, 216, 30);
		panel.add(rdobtnCheque_I);
		Amount = new JTextField();
		Amount.setFont(new Font("Tahoma", Font.BOLD, 16));
		Amount.setBounds(53, 444, 523, 30);
		panel.add(Amount);
		Amount.setColumns(10);
		Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						// since i dont know how to get variable out of IF i put all in IF and again
						// Else

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

							String dealerName = (String) DealerDropdwn1.getSelectedItem();
							// long static chequeNo;
							String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(), rdobtnCheque_I);
							// DealerName.setText(dealerCBox);

							invoiceManager.getInstance().saveInvoice(invoiceNo, amount, dateOfInvoice, deadline,
									dealerName, chequeNo, Short);
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

							String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(), rdobtnCheque_I);
							// DealerName.setText(dealerCBox);

							invoiceManager.getInstance().saveInvoice(invoiceNo, amount, dateOfInvoice, deadline,
									dealerName, chequeNo, Short);
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "There is invalid input, please check!");
					}
				}
			}
		});

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

							String dealerName = (String) DealerDropdwn1.getSelectedItem();
							// long static chequeNo;
							String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(), rdobtnCheque_I);
							// DealerName.setText(dealer342x);

							invoiceManager.getInstance().saveInvoice(invoiceNo, amount, dateOfInvoice, deadline,
									dealerName, chequeNo, Short);
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

							String chequeNo = UImanager.getCheque(txtChequeNo_I.getText(), rdobtnCheque_I);
							// DealerName.setText(dealerCBox);

							invoiceManager.getInstance().saveInvoice(invoiceNo, amount, dateOfInvoice, deadline,
									dealerName, chequeNo, Short);
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "There is invalid input, please check!");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Don't forget to enter Invoice Number, since it is essential !");
				}
			}
		});

		JButton btn_rest_I = new JButton("RESET");
		btn_rest_I.setFont(new Font("Tahoma", Font.BOLD, 25));
		btn_rest_I.setBounds(719, 500, 320, 75);
		panel.add(btn_rest_I);
		btn_rest_I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Amount.setText("");
				InvoiceNo_input.setText("");

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
		btnAddDealer.setBounds(829, 14, 211, 69);
		panel.add(btnAddDealer);

		JLabel DealerName = new JLabel("Dealer");
		DealerName.setFont(new Font("Tahoma", Font.BOLD, 16));
		DealerName.setBounds(55, 14, 160, 30);
		panel.add(DealerName);

		JLabel lblperiod = new JLabel("Period");
		lblperiod.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblperiod.setBounds(53, 342, 109, 30);
		panel.add(lblperiod);

		JLabel lblNewLabel_5 = new JLabel("Cheque No.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(53, 160, 109, 30);
		panel.add(lblNewLabel_5);

		JButton btnAddCheque = new JButton("Add Cheque");
		btnAddCheque.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnAddCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnAddCheque.setBounds(829, 94, 210, 69);
		panel.add(btnAddCheque);

		JButton btnInvoiceList = new JButton("Invoice List");
		btnInvoiceList.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnInvoiceList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnInvoiceList.setBounds(830, 178, 209, 60);
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
		btnDealersList.setBounds(801, 7, 238, 83);
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

		JComboBox<Object> DealerDropdwn_C = new JComboBox<Object>();
		DealerDropdwn_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		DealerDropdwn_C.setBounds(41, 190, 515, 30);
		panel_3.add(DealerDropdwn_C);

		txtperiod_D = new JTextField();
		txtperiod_D.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtperiod_D.setBounds(49, 218, 569, 30);
		panel_1.add(txtperiod_D);
		txtperiod_D.setColumns(10);
		Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					try {

						String name = txtDealerName.getText();
						long tel = Long.parseLong(txtDealerTel.getText());
						int period = Integer.parseInt(txtperiod_D.getText());
						if (!txtDealerName.getText().isEmpty()) {
							dealerManager.saveDealer(name, tel, period);//
						} else {
							JOptionPane.showMessageDialog(null, "Don't foget to enter Dealer name it is Essential! ");
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "There is/are invalid input(s), please check!");
					}
					try {
						ResultSet result = DataClass.getDealerNames();
						while (result.next()) {
							DealerDropdwn1.addItem(result.getString("DealerName"));
							DealerDropdwn_C.addItem(result.getString("DealerName"));
						}
					} catch (SQLException er) {
						// TODO Auto-generated catch block
						er.printStackTrace();
					}
				}
			}
		});

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
					} else {
						JOptionPane.showMessageDialog(null, "Don't foget to enter Dealer name it is Essential! ");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "There is/are invalid input(s), please check!");
				}
				try {
					ResultSet result = DataClass.getDealerNames();
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
		btnSave_D.setBounds(10, 500, 350, 75);
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
		btnReset_D.setBounds(689, 500, 350, 75);
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
		btnRefresh_I.setBounds(602, 53, 143, 30);
		panel.add(btnRefresh_I);

		JLabel lblNewLabel_10 = new JLabel("Any Short ?");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_10.setBounds(53, 234, 143, 30);
		panel.add(lblNewLabel_10);

		JLabel lblNewLabel_12 = new JLabel("Date");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_12.setBounds(53, 275, 58, 20);
		panel.add(lblNewLabel_12);

		JButton btnChequeList = new JButton("Cheque List");
		btnChequeList.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnChequeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnChequeList.setBounds(798, 11, 241, 75);
		panel_3.add(btnChequeList);

		JLabel lblCheque = new JLabel("Cheque Number");
		lblCheque.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCheque.setBounds(41, 21, 158, 30);
		panel_3.add(lblCheque);

		txtChequeNo_C = new JTextField();
		txtChequeNo_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtChequeNo_C.setBounds(41, 49, 515, 30);
		panel_3.add(txtChequeNo_C);
		txtChequeNo_C.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Invoice Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(41, 91, 158, 30);
		panel_3.add(lblNewLabel_1);

		txtInvoiceNo_C = new JTextField();
		txtInvoiceNo_C.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtInvoiceNo_C.setBounds(41, 119, 515, 30);
		panel_3.add(txtInvoiceNo_C);
		txtInvoiceNo_C.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("DealerName");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(41, 159, 158, 30);
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
		txtCheckAmount_C.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String ChequeNo_C = (txtChequeNo_C.getText());
						String InvoiceNo_C = (txtInvoiceNo_C.getText());
						long CheckAmount_C = Long.parseLong(txtCheckAmount_C.getText());
						String DealerName_C = (String) DealerDropdwn_C.getSelectedItem();
						String IssueDate_C = ((JTextField) dateChooser_C.getDateEditor().getUiComponent()).getText(); // TODO
																														// fix
																														// date
																														// chooser
						// DataStore.updateInvoice_cheque(ChequeNo_C, txtInvoiceNo_C.getText(),
						// IssueDate_C);
						if (!(txtChequeNo_C.getText()).isEmpty()) {
							chequeManager.saveCheque(ChequeNo_C, DealerName_C, InvoiceNo_C, CheckAmount_C, IssueDate_C);
						} else {
							JOptionPane.showMessageDialog(null, "Don't forget to Insert Cheque number,it's essential");
						}
						// (long chequeNo,String DealerName,long InvoiceNo,long chequeAmount, String
						// chequeDate)
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "There is/are invalid input, please check!");
					}
				}
			}
		});

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
					String InvoiceNo_C = (txtInvoiceNo_C.getText());
					long CheckAmount_C = Long.parseLong(txtCheckAmount_C.getText());
					String DealerName_C = (String) DealerDropdwn_C.getSelectedItem();
					String IssueDate_C = ((JTextField) dateChooser_C.getDateEditor().getUiComponent()).getText(); // TODO
																													// fix
																													// date
																													// chooser

					chequeManager.saveCheque(ChequeNo_C, DealerName_C, InvoiceNo_C, CheckAmount_C, IssueDate_C);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "There is/are invalid input, please check!");
				}

			}
		});

		JButton appendInvoiceToCheque = new JButton("Add Invoice to cheque");
		appendInvoiceToCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String IssueDate_C = ((JTextField) dateChooser_C.getDateEditor().getUiComponent()).getText();
					long ChequeNo_C = Long.parseLong(txtChequeNo_C.getText());
					String InvoiceNo_C = (txtInvoiceNo_C.getText());
					String DealerName_C = (String) DealerDropdwn_C.getSelectedItem();

					DataStore.append_newInvoiceNo_to_cheque(txtInvoiceNo_C.getText(), txtChequeNo_C.getText(),DealerName_C);// i put
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

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "There is/are invalid input, please check!");
				}
			}
		});
		appendInvoiceToCheque.setFont(new Font("Tahoma", Font.BOLD, 16));
		appendInvoiceToCheque.setBounds(577, 119, 241, 30);
		panel_3.add(appendInvoiceToCheque);
		btn_Save_C.setBounds(10, 500, 350, 75);
		panel_3.add(btn_Save_C);

		JButton btnReset_C = new JButton("RESET");
		btnReset_C.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnReset_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtChequeNo_C.setText("");
				txtInvoiceNo_C.setText("");
				txtCheckAmount_C.setText("");
			}

		});
		btnReset_C.setBounds(689, 500, 350, 75);
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
		btnRefresh_C.setBounds(577, 190, 141, 30);
		panel_3.add(btnRefresh_C);

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
		scrollPane_1.setBounds(10, 153, 1029, 420);
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
		btnNewButton.setBounds(824, 11, 215, 82);
		panel_4.add(btnNewButton);

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
		scrollPane.setBounds(10, 160, 1029, 420);
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

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 125, 1029, 450);
		panel_2.add(scrollPane_2);

		table_3 = new JTable();
		scrollPane_2.setViewportView(table_3);

	}
}
