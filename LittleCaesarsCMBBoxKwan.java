import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class LittleCaesarsCMBBoxKwan extends LittleCaesarsChkBoxKwan implements ActionListener{
	protected JComboBox<Integer> [] drinks = new JComboBox[4];
	private JLabel[] lblDrinks = new JLabel[4];
	protected String[] drinksMessages = {"Coke","Sprite","Orange","Root Beer"};
	protected double costOfDrinks=0;
	protected JLabel drinksCost;
	
	public LittleCaesarsCMBBoxKwan() {
		super();
		
		for (int i = 0;i <4;i++) {
			drinks[i] = createComboBox();
			lblDrinks[i]=createLabel(drinksMessages[i],125,25,415,325,null);
		}
		drinksCost = createLabel("$0.00",100,25,488,325,BorderFactory.createLineBorder(Color.gray));
		drinksCost.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void addComponents(JPanel p) {
		super.addComponents(p);
		JPanel drinksPanel = new JPanel();
		drinksPanel.setBounds(415,145,250,150);
		drinksPanel.setLayout(new GridLayout(4,2));
		drinksPanel.setBackground(Color.white);
		drinksPanel.setBorder(BorderFactory.createTitledBorder("BEVERAGES"));
		
		for (int i = 0;i<4;i++) {
			drinksPanel.add(lblDrinks[i]);
			drinksPanel.add(drinks[i]);
		}
		p.add(drinksCost);
		p.add(drinksPanel);
	}
	
	public void actionPerformed(ActionEvent a) {
		int numOfDrinks = 0;
		if (a.getSource()instanceof JComboBox) {
			for (int i = 0;i<4;i++) {
				numOfDrinks+=drinks[i].getSelectedIndex();
			}
			costOfDrinks = numOfDrinks*0.99;
			drinksCost.setText(currency.format(costOfDrinks));
			
		}
		else {
			super.actionPerformed(a);
		}
	}
	
	public JComboBox createComboBox() {
		JComboBox <Integer>comboBox = new JComboBox();
		Integer [] numbers = {0,1,2,3,4,5,6};
		comboBox = new JComboBox(numbers);
		comboBox.addActionListener(this);
		return comboBox;
	}
}
