package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Sets up a command line interface for the customers.
 * @author Nick Anderson
 * @author Jonathon Bundy.
 */
class CustomerCLI extends UserCLI implements CustomerUI {
	
	public CustomerCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	/**
	 * Starts the whole ordering procedure for a specific party.
	 * @param partyID
	 */
	public void initiateOrdering(int partyID){
		this.getRestaurant().getPartyManager(partyID).takeOrder();
	}
	
	public void initiateCheckout(int partyID){
		this.getRestaurant().getPartyManager(partyID).pay(partyID);
	}
	/**
	 * Gets the customer names from the customers and stores them in a list.
	 */
	public List<String> setCustomerNames(int partySize){
		List<String>people = new ArrayList<String>();
		
		for (int i = 0; i<partySize; i++){
			int temp = i + 1;
			String output = "Enter customer "+temp+" (first and last name): ";
			System.out.print(output);
			people.add(getLineFromUser());
		}
		System.out.println();
		return people;
	}
	
	/**
	 * Display the options a user has to choose from.
	 */
	public void displayCustomerChoices(){
		System.out.println("Choose 0, 1, 2, 3, or 4 for the following choices.");
		System.out.println("0) Add item to ticket.");
		System.out.println("1) Remove item from ticket.");
		System.out.println("2) View your ticket.");
		System.out.println("3) Submit order");
		System.out.println("4) Cancel order");
		System.out.println();
		return;
	}
	
	/**
	 * Display the menu to the user.
	 * @param isFood - true for food, false for drinks.
	 */
	public void displayMenuItems(boolean isFood){
		System.out.println("Choose the number of the item you want:");
		System.out.println(super.getRestaurant().getMenu().printMenu(isFood));
	}
	
	/**
	 * Displays the remove instruction to the user.
	 */
	public void displayRemoveInstruction(){
		System.out.println("Choose the number of the item you want to remove:");
	}
	
	/**
	 * Displays the customer to be chosen.
	 * @param custNames - list of customer names.
	 */
	public void displayCustomers(List<String>custNames){
		System.out.println("Choose the number of the customer you want:");
		ListIterator<String> it = custNames.listIterator() ;
		int j = 0;
		while(it.hasNext()){
			String name = j +") " + it.next();
			System.out.println(name);
			j++;
		}
		System.out.println();
	}
	
	/**
	 * Displays the items that are in the ticket currently being made.
	 * @param items - items in the ticket
	 * @param custNames - list of customer names.
	 */
	public void displayItemsInList(List<Order>items, List<String>custNames){
		ListIterator<Order> iterator = items.listIterator();
		int i = 0;
		while (iterator.hasNext()){
			Order temp = iterator.next();
			String out = i+") "+custNames.get(temp.getCust()) + ": " + temp.toString();
			System.out.println(out);
			i++;
		}
		System.out.println();
	}
	
	/**
	 * Display choices to remove an item (0 for food, 1 for drink).
	 */
	public void displayFoodOrDrinkChoice(){
		System.out.println("Choose 0 or 1 for the following choices.");
		System.out.println("0) A food item");
		System.out.println("1) A drink item");
		System.out.println();
	}
	
	/**
	 * Display invalid option.
	 */
	public void displayInvalidOption(){
		System.out.println("Invalid option.");
		System.out.println();
	}
	/**
	 * Displays the process for patrons to checkout
	 */
	public void displayCheckout() {
		System.out.println("Would you like to checkout?");
		System.out.println("Choose 1 for yes or 2 for no: ");
	}
	
	public List<Integer> setCheckNames(List<String>custNames, int split, int num) {
		List<Integer> checkNames = new ArrayList<Integer>();
		//List<String> custNames = new ArrayList<String>();
		//custNames = this.getRestaurant().getPartyManager(partyID).getCustNames();
		//System.out.println("Hello");
		if (split == 1){
			for (int i = 0; i<custNames.size(); i++){
    			checkNames.add(i);
    		}
			return checkNames;
		}
		else {
				
				int people = num+1;
				System.out.println("How many people in party " + people + "?: ");
				int numPeople = this.getIntegerFromUser();
				System.out.println();
				for (int j = 0; j<numPeople; j++){
					this.displayCustomers(custNames);
					System.out.println();
					System.out.println("Choose the number of the customer to add to this receipt:");
					int custNumber = this.getIntegerFromUser();
					checkNames.add(custNumber);
				}
			
			//System.out.println("Hello");
			return checkNames;
		}
	}

	/**
	 * Displays the total of a receipt
	 */
	public void displayTotal(Receipt receipt) {
		System.out.println("The total for receipt " + receipt.getCheckNumber() + " is " + receipt.getTotal());
	}
	/**
	 * Displays the total amount of receipts for a table and their associated check number and total
	 */
	public void displayTickets(List<Receipt> receipts) {
		ListIterator<Receipt> receiptit = receipts.listIterator();
		while (receiptit.hasNext()){
			System.out.println("The total amount of receipts and their check numbers are: ");
			System.out.println(receipts.size());
			System.out.println("Receipt " + receiptit.next().getCheckNumber() + " has total " + receiptit.next().getTotal());
		}
	}
	
	public int getSplit(){
		System.out.println("How many ways would you like to split the check?: ");
		int split = this.getIntegerFromUser();
		return split;
	}


	public void displayThanks() {
		System.out.println("Thank you. Please come again.");
	}

	public void displayPayWhenReady() {
		System.out.println("Please pay when you are ready.");
		
	}

}
