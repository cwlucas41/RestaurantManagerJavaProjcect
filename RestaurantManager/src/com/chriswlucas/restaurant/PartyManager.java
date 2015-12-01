package com.chriswlucas.restaurant;

import java.awt.FlowLayout;
import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.JFrame;

class PartyManager {
	//Ticket[] orders;
	/**
	 * Handles everything needed to run a party, from start to finish
	 * @param waiter
	 */
	PartyManager(Restaurant restaurant){
		this.restaurant = restaurant;
		jobs = new JobManager(this);
	}
	
	/**
	 * Gets the customer names from the customers.
	 */
	void getNames(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many people in your party: ");
		numPeople = scanner.nextInt();
		custNames = new String[numPeople];
		
		for (int i = 0; i<numPeople; i++){
			int temp = i + 1;
			String output = "Enter customer "+temp+"(FirstName and first letter of last name without spaces): ";
			System.out.print(output);
			custNames[i]=scanner.next();
		}
		scanner.close();
	}
	
	/**
	 * Adds an item to the appropriate list(food or drink).
	 * @param item
	 * @param customer
	 */
	void addItem(MenuItem item, int customer){
		// from temp
		if (item.isFood()){
			tempFood.add(new Order(item,customer));
		}
		else{
			tempDrinks.add(new Order(item,customer));
		}
	}
	
	/**
	 * Removes an item from the appropriate list (food or drink).
	 * @param item
	 * @param customer
	 */
	void removeItem(MenuItem item, int customer){
		if(item.isFood){
			ListIterator<Order> iterator = tempFood.listIterator();
			while (iterator.hasNext()){
				Order current = iterator.next();
				if((item==current.getItem())&&(customer==current.getCust())){
					tempFood.remove(iterator.next());
				}
			}
		}
		else{
			ListIterator<Order> iterator = tempDrinks.listIterator();
			while (iterator.hasNext()){
				Order current = iterator.next();
				if((item==current.getItem())&&(customer==current.getCust())){
					tempDrinks.remove(iterator.next());
				}
			}
		}		
	}
	
	/**
	 * Empty's everything from the temp lists.
	 */
	void emptyTemp(){
		tempFood.removeAll(null);
		tempDrinks.removeAll(null);
	}
	
	/**
	 * Creates a ticket for the current order and sends out the job.
	 */
	void makeTicket() {
		// empty temps, create, and store ticket
		Ticket temp = new Ticket(tempFood, tempDrinks, restaurant.getTicket());
		tickets.add(temp);
		jobs.assignProducingJob(temp);
		emptyTemp();
	}
	int getIntegerFromUser(){
		return 0;
	}
	/**
	 * Takes an order from the customers.
	 */
	void takeOrder(){
		addingItems = true;
		List<MenuItem> menu = restaurant.getMenuItems();
		ListIterator<MenuItem> iterator = menu.listIterator();
		
		int i = 0;
		while(addingItems){
			while(iterator.hasNext()){
				String temp = i + iterator.next().toString();
				System.out.println(temp);
				i++;
			}
			System.out.println();
			System.out.println("Choose the number of the item you want (-1 to finish):");
			int orderVal = getIntegerFromUser();
			
			System.out.println();
			for(i = 0; i<//size ; i++){
		
			}
			if((orderVal==-1)||(customerNumber == -1)){
				addingItems = false;
			}
		}
		
	}
    
	/**
	 * On checkout creates a Payment Manager which will handle creating
	 * a receipt and handle sending out jobs to clean and free the table.
	 */
    void pay(){
        payments = new PaymentManager(this);
        restaurant.collectTickets(tickets);
    }
	
    List<Ticket>getTickets(){
    	return tickets;
    }

	String[]custNames;
	JobManager jobs;
    PaymentManager payments;
	List<Order> tempDrinks;
	List<Order> tempFood;
	List<Ticket> tickets;
	boolean addingItems;
	Restaurant restaurant;
	private int numPeople;
}


