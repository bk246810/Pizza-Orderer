import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class LittleCaesarsButtonKwan extends LittleCaesarsCMBBoxKwan implements ActionListener {
	private JButton[] btnActions = new JButton[5];
	private String[] btnMessages = { "CALCULATE", "CLEAR PIZZA", "CLEAR DRINKS","CHECKOUT", "EXIT" },
			lblMessages = { "SUBTOTAL: ", "DELIVERY FEE: ", "HST: ", "GRAND TOTAL: " };
	private JLabel[] lblTitleOfTotals = new JLabel[4], lblCostOfItems = new JLabel[4];
	private double []costOfItems = new double[4];
	
	//multiple pizza buttons
	private JButton[] selectCurrentPizza = new JButton[3];
	private String[] currentPizzaMessages = {"Pizza 1","New Pizza","New Pizza"};
	
	public LittleCaesarsButtonKwan() {
		super();

		for (int i = 0;i<5;i++) {
			btnActions[i] = createButton(btnMessages[i], 472, 380 + i * 33);
		}
		for (int i = 0; i < 4; i++) {
			

			lblTitleOfTotals[i] = createLabel(lblMessages[i], 100, 25, 175, 407 + i * 30, null);
			lblCostOfItems[i] = createLabel("", 100, 25, 275, 407 + i * 30, BorderFactory.createLineBorder(Color.gray));
			lblCostOfItems[i].setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		lblCostOfItems[1].setOpaque(true);
		
		for (int i = 0;i<3;i++) {
			selectCurrentPizza[i]=createButton(currentPizzaMessages[i],20+i*100,115);
			selectCurrentPizza[i].setSize(100,20);
			selectCurrentPizza[i].setActionCommand("New Pizza");
		}
		
		//highlight current pizza
		selectCurrentPizza[0].setBackground(Color.green);
		//hide the second "new pizza" button until you already have two pizzas
		selectCurrentPizza[2].setVisible(false);
		
	}

	public void addComponents(JPanel p) {
		super.addComponents(p);
		for (int i = 0;i<5;i++) {
			p.add(btnActions[i]);
		}
		for (int i = 0; i < 4; i++) {
			p.add(lblTitleOfTotals[i]);
			p.add(lblCostOfItems[i]);
		}
		for (int i = 0;i<3;i++) {
			p.add(selectCurrentPizza[i]);
		}
	}

	public void actionPerformed(ActionEvent a) {
		if (a.getSource()instanceof JButton) {
			if (a.getSource() == btnActions[0]) {
				calculate();
			} 
			else if (a.getSource() == btnActions[1]) {
				clearPizza();
			} 
			else if (a.getSource()== btnActions[2]) {
				clearDrinks();
			}
			else if (a.getSource() == btnActions[3]) {
				checkout();
			}
			else if (a.getSource()==selectCurrentPizza[0]) {
				setPizzaDetails(0);
			}
			else if (a.getSource()==selectCurrentPizza[1]) {
				setPizzaDetails(1);
				selectCurrentPizza[2].setVisible(true);
			}
			else if (a.getSource()==selectCurrentPizza[2]) {
				setPizzaDetails(2);
				
			}
			else {
				exit();
			}
		}
		//once you click a size, then you can see the toppings for that pizza
		else if (a.getSource()instanceof JRadioButton) {
			selectCurrentPizza[currentPizza].setActionCommand("Edit Pizza");
			lblToppingsWarning.setVisible(true);
			super.actionPerformed(a);

		} 
		else {
			super.actionPerformed(a);
		}
	}
	
	//resets the size and toppings
	public void setPizzaDetails(int pizzaIndex) {
		//size
		if (pizzaSizeInfo[pizzaIndex]!=-1) {
			sizes[pizzaSizeInfo[pizzaIndex]].setSelected(true);
		}
		else {
			bg.clearSelection();
		}
		
		//toppings
		for (int i = 0;i<8;i++) {
			chkBoxToppings[i].setSelected(pizzaToppingsInfo[pizzaIndex][i]);
		}
		
		//don't let user select toppings if pizza size not selected
		if (selectCurrentPizza[pizzaIndex].getActionCommand().equals("New Pizza")) {
			
			selectCurrentPizza[pizzaIndex].setText("Pizza "+(pizzaIndex+1));
			bg.clearSelection();
			for (int i = 0;i<8;i++) {
				chkBoxToppings[i].setVisible(false);
			}
			lblToppingsWarning.setVisible(false);
		}
		else {
			for (int i = 0;i<8;i++) {
				chkBoxToppings[i].setVisible(true);
			}
			lblToppingsWarning.setVisible(true);
		}
		selectCurrentPizza[currentPizza].setBackground(Color.white);
		currentPizza = pizzaIndex;
		selectCurrentPizza[currentPizza].setBackground(Color.green);
		sizeCost.setText(currency.format(costOfSize[currentPizza]));
		toppingsCost.setText(currency.format(costOfToppings[currentPizza]));
	}
	public void calculate() {
		
		//subtotal
		costOfItems[0] = 0;
		for (int i = 0;i<3;i++) {
			costOfItems[0]  +=costOfSize[i]+costOfToppings[i];
		}
		costOfItems[0]+=costOfDrinks;
		//delivery fee
		
		//only give a delivery fee if you are buying something and subtotal is less than $15
		if (costOfItems[0]<15&&costOfItems[0]>0) {
			costOfItems[1]=3;
			lblCostOfItems[1].setBackground(Color.white);
		}
		else {
			costOfItems[1] = 0;
			lblCostOfItems[1].setBackground(Color.green);
		}
		//HST
		costOfItems[2]=costOfItems[0]*0.13;
		//grand total
		costOfItems[3]=costOfItems[0]+costOfItems[1]+costOfItems[2];
		
		for (int i = 0;i<4;i++) {
			if (i == 1&&costOfItems[0]>15) {
				lblCostOfItems[i].setText("FREE");
			}
			else {
				lblCostOfItems[i].setText(currency.format(costOfItems[i]));
			}
			
		}
		
	}

	public void clearDrinks() {
		for (int i = 0;i<4;i++) {
			drinks[i].setSelectedIndex(0);
		}
		costOfDrinks = 0;
		drinksCost.setText(currency.format(costOfDrinks));
		calculate();
	}
	public void clearPizza() {
		//reset pizza sizes
		bg.clearSelection();
		//reset topping checkboxes and make invisible
		for (int i = 0;i<8;i++) {
			chkBoxToppings[i].setSelected(false);
			chkBoxToppings[i].setVisible(false);
			pizzaToppingsInfo[currentPizza][i]=false;
		}
		selectCurrentPizza[currentPizza].setActionCommand("New Pizza");
		lblToppingsWarning.setVisible(false);
		pizzaSizeInfo[currentPizza]=-1;
		
		//reset display prices
		costOfToppings[currentPizza] = 0;
		numToppings[currentPizza] = 0;
		toppingsCost.setText(currency.format(costOfToppings[currentPizza]));
		costOfSize[currentPizza] = 0;
		sizeCost.setText(currency.format(costOfSize[currentPizza]));
		//calculate new price after clearing since you only clearing the current pizza's details, want to update costs after
		calculate();
		
	}
	
	public void checkout() {
	//STEP ONE: CHECK IF LESS THAN 3 TOPPINGS	
		//if you order a pizza with less than 3 toppings, warn that up to 3 toppings are free
		if (costOfSize[0]>0 &&numToppings[0] <3||costOfSize[1]>0 &&numToppings[1] <3||costOfSize[2]>0 &&numToppings[2] <3) {
			JOptionPane.showMessageDialog(null, "One of your pizzas has less than 3 toppings. \nEach pizza can have up to 3 toppings for free.",
					"Little Casesars",JOptionPane.WARNING_MESSAGE);
		}
	//STEP TWO: CALCULATE NEW TOTALS
		calculate();
		
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("LittleCaesarsIcon.png"));
	//STEP THREE: GET TEXT VERSION OF CUSTOMER'S ORDER
		String pizzaOrder = "",drinksOrder = "\nDrinks:\n";
		int numPizzas=0;
		for (int i = 0;i<3;i++) {
			//if there is a pizza at index i...
			if (costOfSize[i]>0) {
				numPizzas++;
				//add the pizza size to the string for the order
				pizzaOrder += "Pizza "+(numPizzas)+": A "+sizeMessages[pizzaSizeInfo[i]].toLowerCase()+" sized pizza with ";
				
				if (numToppings[i]>0) {
					//if pizza topping array stores true, then add it to the string for the order
					for (int j = 0;j<8;j++) {
						if (pizzaToppingsInfo[i][j]) {
							pizzaOrder+= toppingsMessages[j].toLowerCase()+", ";
						}
					}
					//get rid of final comma + space
					pizzaOrder = pizzaOrder.substring(0,pizzaOrder.length()-2);
				}
				else {
					pizzaOrder += "no extra toppings";
				}
				//after each pizza, put period and go to next line
				pizzaOrder+=".\n";
			}
			
		}
		
		int numDrinks;
		for (int i = 0;i<4;i++) {
			numDrinks = drinks[i].getSelectedIndex();
			if (numDrinks>0) {
				drinksOrder+= drinksMessages[i]+": "+numDrinks+" can(s).\n";
			}
		}
		//if no drinks (number of characters in "Drinks:" and the two line skips = 9)
		if (drinksOrder.length() <=9) {
			drinksOrder+="None";
		}
		
		
	//STEP FOUR: CHECK IF THEY ORDERED A PIZZA, IF YES, ASK IF THEIR ORDER CORRECT, ELSE ERROR MESSAGE
		int option;
		if (costOfSize[0]>0||costOfSize[1]>0||costOfSize[2]>0) {
			option = JOptionPane.showConfirmDialog(null, "Is this order correct? \n"+pizzaOrder+drinksOrder,"Little Caesar's",JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				JOptionPane.showMessageDialog(null, "Thank you for ordering from Little Caesar's!\n Your pizza will be delivered in 30 minutes or it's free!","Little Caesar's",JOptionPane.INFORMATION_MESSAGE,icon);
				System.exit(0);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Your order could not be completed! \nPlease select a pizza size.","Critical Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void exit() {
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Little Caesar's",JOptionPane.YES_NO_OPTION);
		if (option == 0) {
			JOptionPane.showMessageDialog(null, "Thank you for choosing Little Caesar's!","Little Caesar's",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	public JButton createButton(String btnMessage, int xPos, int yPos) {
		JButton button = new JButton(btnMessage);
		button.setBounds(xPos, yPos, 135, 30);
		button.addActionListener(this);
		return button;
	}
}
