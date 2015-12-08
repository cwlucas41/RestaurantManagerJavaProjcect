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
	 * Gets the customer names from the customers.
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
	
	int getIntegerFromUser(){
		Scanner scanner = new Scanner(System.in);
		int data = scanner.nextInt();
		scanner.close();
		return data;
	}
	
	void displayAddItem(){
		List<MenuItem> menu = this.getRestaurant().getMenuItems();
		ListIterator<MenuItem> iterator = menu.listIterator();
		
		int i = 0;
		while(iterator.hasNext()){
			String temp = i + iterator.next().toString();
			System.out.println(temp);
			i++;
		}
		System.out.println();
		System.out.println("Choose the number of the item you want:");
		int orderVal = getIntegerFromUser();
		
		System.out.println();
		ListIterator<String> it = this.getRestaurant().getParty().getCustNames().listIterator();
		int j = 0;
		while(it.hasNext()){
			String name = j + it.next();
			System.out.println(name);
			j++;
		}
		System.out.println();
		System.out.println("Choose the number of the customer you want:");
		int customerNumber = getIntegerFromUser();
		addItem(menu.get(orderVal), customerNumber); // where does this go.
	}
}
