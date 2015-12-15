package com.chriswlucas.restaurant;

import java.util.List;

/**
 * Interface for the customer uses.
 * Can be implemented as a command line or as a GUI.
 * @author Nick Anderson 
 * @author Jonathon Bundy
 */
public interface CustomerUI {
	
	/**
	 * starts event loop for ordering and paying
	 * @param partyID
	 */
	public void controlCustomer(int partyID);
	
	/**
	 * displays the given string
	 * @param string
	 */
	public void display(String string);
	
	/**
	 * Displays the option for patrons to checkout
	 * @param partyID
	 */
	public void displayCheckout();
	
	/**
	 * Displays the customer to be chosen.
	 * @param custNames - list of customer names.
	 */
	public void displayCustomers(List<String>custNames);
	
	/**
	 * Display choices for items (0 for food, 1 for drink).
	 */
	public void displayFoodOrDrinkChoice();
	
	/**
	 * Display invalid option.
	 */
	public void displayInvalidOption();
	
	/**
	 * Displays the items that are in the ticket currently being made.
	 * @param items - items in the ticket
	 * @param custNames - list of customer names.
	 */
	public void displayItemsInList(List<MenuItem> itemList);
	
	/**
	 * Display the menu to the user.
	 * @param isFood - true for food, false for drinks.
	 */
	public void displayMenuItems(boolean isFood);
	
	/**
	 * Display the options a user has to choose from.
	 */
	public void displayOrderingChoices();
	
	/**
	 * Displays payment choice
	 */
	public void displayPayWhenReady();
	
	/**
	 * Displays the total for a receipt
	 */
	public void displayReceipt(Receipt receipt);
	
	/**
	 * Displays the total receipts for a table
	 */
	public void displayReceipts(List<Receipt>receipts);
	
	/**
	 * Displays the remove instruction to the user.
	 */
	public void displayRemoveInstruction();
	
	/**
	 * Displays thank you message
	 */
	public void displayThanks();
	
	/**
	 * Gets an integer choice from the customer.
	 * @return data - the user's choice.
	 */
	public int getIntegerFromUser();
	
	/**
	 * Gets how many ways the check should be split
	 * @return number of splits
	 */
	public int getSplit();
	
	/**
	 * starts the checkout process
	 * @param partyID
	 */
	public void initiateCheckout(int partyID);
	
	/**
	 * Starts the whole ordering procedure for a specific party.
	 * @param partyID
	 */
	public void initiateOrdering(int partyID);
	
	/**
	 * Propts user for splitting the check
	 * @param custNames
	 * @param split
	 * @param num
	 * @return customerIDs for receipt
	 */
	public List<Integer> setCheckNames(List<String>custNames, int split, int num);	
	
	/**
	 * Gets the customer names from the customers and stores them in a list.
	 */
	public List<String> setCustomerNames(int partySize);

}
