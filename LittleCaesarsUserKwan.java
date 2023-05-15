import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LittleCaesarsUserKwan implements WindowListener{
	
	public static void main(String[] args) {
		new LittleCaesarsUserKwan();
	}
	public LittleCaesarsUserKwan() {
		JFrame frame = new JFrame("Little Caesar's");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		JLabel logo = new JLabel();
		logo.setBounds(88,10,516,97);
		ImageIcon imgLogo = new ImageIcon(getClass().getClassLoader().getResource("LittleCaesarsLogo.png"));
		logo.setIcon(imgLogo);
		panel.add(logo);
		
		ImageIcon imgPayment = new ImageIcon(getClass().getClassLoader().getResource("PaymentOptions.png"));
		JLabel paymentLogo = new JLabel();
		paymentLogo.setBounds(28,380,80,166);
		paymentLogo.setIcon(imgPayment);
		panel.add(paymentLogo);
		
		frame.setContentPane(panel);
		
		LittleCaesarsRadBTNKwan lck = new LittleCaesarsButtonKwan();
		lck.addComponents(panel);
		
		frame.setResizable(false);
		frame.setSize(700,600);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.addWindowListener(this);
	}
	public void windowOpened(WindowEvent w) {

	}

	public void windowClosed(WindowEvent w) {
		
	}

	public void windowClosing(WindowEvent w) {
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION);
		if (option == 0) {
			JOptionPane.showMessageDialog(null, "Thank you for choosing Little Caesar's!","Little Caesar's",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	public void windowActivated(WindowEvent w) {
	}

	public void windowDeactivated(WindowEvent w) {	
	}

	public void windowIconified(WindowEvent w) {
	}
	public void windowDeiconified(WindowEvent w) {
	}

}