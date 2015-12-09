package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

class PartyManager {
	/**
	 * Handles everything needed to run a party, from start to finish
	 * @param waiter
	 */
	PartyManager(Restaurant restaurant, int waiterID, List<Integer> tableNumbers){
		this.restaurant = restaurant;
		this.waiterID = waiterID;
		this.tableNumbers = tableNumbers;
		this.custNames.addAll(this.restaurant.getRestaurantInterface().getCustomerInterface().setCustomerNames());
		this.jobs = new JobManager(this);
	}
	
	/**
	 * Adds an item to the appropriate list(food or drink).
	 * @param item
	 * @param customer
	 */
	void addItem(MenuItem item, int customer){
		if (item.isFood()){
			tempFood.add(new Order(item,customer));
		}
		else{
			tempDrinks.add(new Order(item,customer));
		}
		total+= item.getPrice();
	}
	
	/**
	 * Removes an item from the appropriate list (food or drink).
	 * @param item
	 * @param customer
	 */
	void removeItem(MenuItem item, int customer){
		//TODO remove based on item not customer
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
		total -= item.getPrice();
	}
	
	/**
	 * Empty's everything from the temporary lists.
	 */
	void emptyTemp(){
		tempFood.removeAll(null);
		tempDrinks.removeAll(null);
		total = 0;
	}
	
	/**
	 * Creates a ticket for the current order and sends out the job.
	 */
	void makeTicket() {
		Ticket temp = new Ticket(tempFood, tempDrinks, restaurant.getTicketNumber(), total);
		tickets.add(temp);
		jobs.assignProducingJob(temp);
		emptyTemp();
	}

	/**
	 * Takes an order from the customers.
	 */
	void takeOrder(){
		boolean makeTick = false;
		while(!makeTick){
			int choice = restaurant.getRestaurantInterface().getCustomerInterface().displayCustomerChoices();
			switch(choice){
			case 1: {
				restaurant.getRestaurantInterface().getCustomerInterface().displayAddItem();
				int itemNumber = restaurant.getRestaurantInterface().getCustomerInterface().getChoice();
				restaurant.getRestaurantInterface().getCustomerInterface().displayCustomers(custNames);
				int customerNumber = restaurant.getRestaurantInterface().getCustomerInterface().getChoice();
				addItem(restaurant.getMenuItems().get(itemNumber), customerNumber);
			}
			case 2: {
				restaurant.getRestaurantInterface().getCustomerInterface().displayRemoveItemMenu();
				int foodDrinkChoice = restaurant.getRestaurantInterface().getCustomerInterface().getChoice();
				int itemToRemove;
				Order removal;
				if(foodDrinkChoice == 1){
					restaurant.getRestaurantInterface().getCustomerInterface().displayItemsInList(tempFood, custNames);
					itemToRemove = restaurant.getRestaurantInterface().getCustomerInterface().getChoice();
					removal = tempFood.get(itemToRemove);
					removeItem(removal.getItem(), removal.getCust());
				}
				else if(foodDrinkChoice==2){
					restaurant.getRestaurantInterface().getCustomerInterface().displayItemsInList(tempDrinks, custNames);
					itemToRemove = restaurant.getRestaurantInterface().getCustomerInterface().getChoice();
					removal = tempDrinks.get(itemToRemove);
					removeItem(removal.getItem(), removal.getCust());
				}
				else{
					restaurant.getRestaurantInterface().getCustomerInterface().displayInvalidOption();
				}	
			}
			case 3: {
				makeTick = true;
				makeTicket();
			}
			case 4: {
				makeTick = true;
				emptyTemp();
			}
			default: restaurant.getRestaurantInterface().getCustomerInterface().displayInvalidOption();
			}
		}
	}
    
	/**
	 * On checkout creates a Payment Manager which will handle creating
	 * a receipt and handle sending out jobs to clean and free the table.
	 */
    void pay(){
        this.payments = new PaymentManager(this, custNames);
        this.payments.checkout();
        this.restaurant.collectTickets(tickets);
    }
	
    List<Ticket>getTickets(){
    	return tickets;
    }
    
	public List<Integer> getTableNumbers() {
		return tableNumbers;
	}

	public int getWaiterID() {
		return waiterID;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public List<String> getCustNames() {
		return custNames;
	}
	
	private List<String>custNames;
	JobManager jobs;
    PaymentManager payments;
	List<Order> tempDrinks;
	List<Order> tempFood;
	List<Ticket> tickets;


	int total=0;
	boolean addingItems;
	Restaurant restaurant;
	private int waiterID;
	private List<Integer> tableNumbers;

}


