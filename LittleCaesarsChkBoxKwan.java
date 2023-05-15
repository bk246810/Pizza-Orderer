import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class LittleCaesarsChkBoxKwan extends LittleCaesarsRadBTNKwan implements ActionListener{
	protected JCheckBox [] chkBoxToppings = new JCheckBox[8];
	protected String [] toppingsMessages = {"Mushrooms","Pepperoni","Green Peppers","Bacon","Onions","Tomatoes","Hot Peppers","Extra Cheese"};
	protected JLabel toppingsCost,lblToppingsWarning;
	protected int []numToppings = new int[3];
	protected double []costOfToppings = new double[3];
	
	//for pizza info, store true or false for toppings
	protected boolean[][] pizzaToppingsInfo = new boolean[3][8];
	
	public LittleCaesarsChkBoxKwan() {
		super();
		
		
		for (int i = 0;i <8;i++) {
			chkBoxToppings[i] = createCheckBox(toppingsMessages[i]);
		}
		toppingsCost = createLabel("$0.00",100,25,222,325,BorderFactory.createLineBorder(Color.gray));
		toppingsCost.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblToppingsWarning = createLabel("First 3 toppings are free!",250,25,150,295,null);
		lblToppingsWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblToppingsWarning.setVisible(false);
	}
	
	public void addComponents(JPanel p) {
		super.addComponents(p);
		JPanel toppingsPanel = new JPanel();
		toppingsPanel.setBounds(150,145,250,150);
		toppingsPanel.setLayout(new GridLayout(4,2));
		toppingsPanel.setBackground(Color.white);
		toppingsPanel.setBorder(BorderFactory.createTitledBorder("TOPPINGS"));
		
		for (int i = 0;i<8;i++) {
			toppingsPanel.add(chkBoxToppings[i]);
		}
		p.add(toppingsCost);
		p.add(lblToppingsWarning);
		p.add(toppingsPanel);
	}
	
	public JCheckBox createCheckBox(String message) {
		JCheckBox checkBox = new JCheckBox(message);
		checkBox.setSize(200, 25);
		checkBox.addActionListener(this);
		checkBox.setBackground(Color.white);
		checkBox.setVisible(false);
		return checkBox;
	}
	
	public void actionPerformed(ActionEvent a) {
		if (a.getSource()instanceof JRadioButton) {
			
			super.actionPerformed(a);
			for (int i = 0;i <8;i++) {
				chkBoxToppings[i].setVisible(true);
			}
		}
		else {
			
			for (int i = 0;i<8;i++) {
				if (a.getSource()==chkBoxToppings[i]) {
					//if selecting
					if (chkBoxToppings[i].isSelected()) {
						numToppings[currentPizza]++;
						if (numToppings[currentPizza]>3) {
							costOfToppings[currentPizza] = (numToppings[currentPizza]-3)*1;
						}
						pizzaToppingsInfo[currentPizza][i]=true;
					}
					//if deselecting
					else {
						numToppings[currentPizza]--;
						if (numToppings[currentPizza]>3) {
							costOfToppings[currentPizza] = (numToppings[currentPizza]-3)*1;
						}
						else {
							costOfToppings[currentPizza] = 0.00;
						}
						pizzaToppingsInfo[currentPizza][i]=false;
					}
					toppingsCost.setText(currency.format(costOfToppings[currentPizza]));
				}
			}
		}
	}
}
