package userInterface;

import javax.swing.JRadioButton;

public class UImanager {

	public static String getCheque(String string, JRadioButton rdobtnCheque_I) {
		String chequeno;
		if (rdobtnCheque_I.isSelected()) {
			chequeno = (string);
		} else {
			chequeno = "00000";
		}
		return chequeno;
	}

}
