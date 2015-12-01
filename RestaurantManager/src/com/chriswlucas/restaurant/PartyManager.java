package com.chriswlucas.restaurant;

import java.awt.FlowLayout;
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
	PartyManager(Worker waiter){
		this.wait = waiter;
		this.workName=waiter.getName();
		this.id = waiter.getUUID();
		jobs = new JobManager(waiter);
	}
	
	/**
	 * Gets the customer names from the customers.
	 */
	void getNames(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many people in your party: ");
		int numPeople = scanner.nextInt();
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
		Ticket temp = new Ticket(tempFood, tempDrinks, Restaurant.getTicket(), id);
		tickets.add(temp);
		jobs.assignProducingJob(temp);
		emptyTemp();
	}
	
	/**
	 * Takes an order from the customers.
	 */
	void takeOrder(){
		JFrame frame = new JFrame();
		final int FIELD_WIDTH = 20;
		frame.setLayout(new FlowLayout());
		//Need a string of all the menu item names.
	}
    
	/**
	 * On checkout creates a Payment Manager which will handle creating
	 * a receipt and handle sending out jobs to clean and free the table.
	 */
    void pay(){
        payments = new PaymentManager(this.wait);
    }
	

	String[]custNames;
	JobManager jobs;
    PaymentManager payments;
	List<Order> tempDrinks;
	List<Order> tempFood;
	List<Ticket> tickets;
	String workName;
	UUID id;
	Worker wait;
}


