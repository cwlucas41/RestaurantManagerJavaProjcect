package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

class CustomerCLI extends UserInterface implements CustomerUI {

	
	public CustomerCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	/**
	 * Gets the customer names from the customers and stores them in a list.
	 */
	public List<String> setCustomerNames(){
		List<String>people = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many people in your party: ");
		int numPeople = scanner.nextInt();
		
		for (int i = 0; i<numPeople; i++){
			int temp = i + 1;
			String output = "Enter customer "+temp+"(FirstName and first letter of last name without spaces): ";
			System.out.print(output);
			people.add(scanner.next());
		}
		scanner.close();
		return people;
	}
	
	/**
	 * Gets an integer choice from the customer.
	 * @return data - the user's choice.
	 */
	public int getIntegerFromUser(){
		Scanner scanner = new Scanner(System.in);
		int data = scanner.nextInt();
		scanner.close();
		return data;
	}
	
	/**
	 * Display the options a user has to choose from.
	 */
	public void displayCustomerChoices(){
		System.out.println("Choose 1, 2, 3, or 4 for the following choices.");
		System.out.println("1) Add item to ticket.");
		System.out.println("2) Remove item from ticket.");
		System.out.println("3) Submit order");
		System.out.println("4) Cancel order");
	}
	
	/**
	 * Display the menu to the user.
	 */
	public void displayAddItem(){
		System.out.println("Choose the number of the item you want:");
		System.out.println(super.getRestaurant().getMenu().toString());
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
			String name = j + it.next();
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
		System.out.println("Choose the number of the item you want to remove:");
		while (iterator.hasNext()){
			Order temp = iterator.next();
			String out = i+") "+custNames.get(temp.getCust()) + ": " + temp.toString();
			System.out.println(out);
			i++;
		}
	}
	
	/**
	 * Display choices to remove an item (1 for food, 2 for drink).
	 */
	public void displayRemoveItemMenu(){
		System.out.println("Choose 1 or 2 for the following choices.");
		System.out.println("1) Remove a food item");
		System.out.println("2) Remove a drink item");
	}
	
	/**
	 * Display invalid option.
	 */
	public void displayInvalidOption(){
		System.out.println("Invalid option.");
	}
}
