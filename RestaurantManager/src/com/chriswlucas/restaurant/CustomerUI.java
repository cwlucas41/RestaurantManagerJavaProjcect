package com.chriswlucas.restaurant;

import java.util.List;

interface CustomerUI {
	
	/**
	 * Gets the customer names from the customers and stores them in a list.
	 */
	public List<String> setCustomerNames();
	
	/**
	 * Display the options a user has to choose from.
	 */
	public void displayCustomerChoices();
	
	/**
	 * Display the menu to the user.
	 * @param isFood - true for food, false for drinks.
	 */
	public void displayAddItem(boolean isFood);
	
	/**
	 * Displays the customer to be chosen.
	 * @param custNames - list of customer names.
	 */
	public void displayCustomers(List<String>custNames);
	
	/**
	 * Gets an integer choice from the customer.
	 * @return data - the user's choice.
	 */
	public int getIntegerFromUser();
	
	/**
	 * Display choices for items (1 for food, 2 for drink).
	 */
	public void displayItemMenu();
	
	/**
	 * Displays the items that are in the ticket currently being made.
	 * @param items - items in the ticket
	 * @param custNames - list of customer names.
	 */
	public void displayItemsInList(List<Order>items, List<String>custNames);
	
	/**
	 * Display invalid option.
	 */
	public void displayInvalidOption();
	/**
	 * Displays the option for patrons to checkout
	 * @param partyID
	 */
	public void displayCheckout(int partyID);
	/**
	 * Displays the total for a receipt
	 */
	public void displayTotal(Receipt receipt);
	/**
	 * Displays the total receipts for a table
	 */
	public void displayTickets(List<Receipt>receipts);
	
}
