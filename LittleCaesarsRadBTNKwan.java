import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class LittleCaesarsRadBTNKwan implements ActionListener{
	protected ButtonGroup bg;
	protected JRadioButton[] sizes = new JRadioButton[4];
	protected String[] sizeMessages = {"Small","Medium","Large","Party"};
	protected JLabel sizeCost;
	private final Double SMALL = 7.99,MEDIUM = 8.99,LARGE = 9.99,PARTY = 10.99;
	protected double []costOfSize = new double [3];
	protected NumberFormat currency;
	
	//things for adding up to 3 pizzas
	//for pizza info, store 0,1,2,or 3 for pizza size default to -1 so if pizza size not chosen yet, no radio button is selected
	protected int[] pizzaSizeInfo = {-1,-1,-1};
	//stores 0,1,or2 depending on which index pizza you are building which changes whenever you click new pizza, or the pizza tab
	protected int currentPizza =0;
	
	public LittleCaesarsRadBTNKwan() {
		bg = new ButtonGroup();
		currency = NumberFormat.getCurrencyInstance(Locale.CANADA);
		
		for (int i = 0;i<4;i++) {
			sizes[i] = createRadBtn(sizeMessages[i]);
		}
		sizeCost = createLabel("$0.00",100,25,20,325,BorderFactory.createLineBorder(Color.gray));
		sizeCost.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void addComponents(JPanel p) {
		JPanel sizePanel = new JPanel();
		sizePanel.setBounds(10,145,125,150);
		sizePanel.setLayout(new GridLayout(4,1));
		sizePanel.setBackground(Color.white);
		sizePanel.setBorder(BorderFactory.createTitledBorder("SIZE"));
		
		for (int i = 0;i<4;i++) {
			sizePanel.add(sizes[i]);
		}
		p.add(sizeCost);
		p.add(sizePanel);
	}
	
	public void actionPerformed(ActionEvent a) {
		if (sizes[0].isSelected()) {
			costOfSize[currentPizza] = SMALL;
			
			//update pizza info array after every click
			pizzaSizeInfo[currentPizza] = 0;
		}
		else if (sizes[1].isSelected()) {
			costOfSize[currentPizza] = MEDIUM;
			pizzaSizeInfo[currentPizza] = 1;
		}
		else if (sizes[2].isSelected()) {
			costOfSize[currentPizza] = LARGE;
			pizzaSizeInfo[currentPizza] = 2;
		}
		else {
			costOfSize[currentPizza] = PARTY;
			pizzaSizeInfo[currentPizza] = 3;
		}
		sizeCost.setText(currency.format(costOfSize[currentPizza]));
	}
	
	public JRadioButton createRadBtn(String name) {
		JRadioButton radBtn = new JRadioButton(name);
		radBtn.addActionListener(this);
		radBtn.setBackground(Color.white);
		bg.add(radBtn);
		return radBtn;
	}
	
	public JLabel createLabel(String lblMessage, int xSize, int ySize, int xPos, int yPos, Border b) {
		JLabel label = new JLabel(lblMessage);
		label.setBounds(xPos, yPos, xSize, ySize);
		label.setBackground(Color.white);
		label.setBorder(b);
		return label;
	}
}
