package com.chriswlucas.restaurant;

import java.util.List;

interface CustomerUI {

	public List<String> setCustomerNames();
	public int displayCustomerChoices();
	public void displayAddItem();
	public void displayCustomers(List<String>custNames);
	public int getChoice();
	public void displayRemoveItemMenu();
	public void displayItemsInList(List<Order>items, List<String>custNames);
	public void displayInvalidOption();
}
