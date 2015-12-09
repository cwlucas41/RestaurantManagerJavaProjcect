package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;

class PartyManager {
	/**
	 * Handles everything needed to run a party, from start to finish.
	 * @param restaurant - reference to the current restaurant.
	 * @param waiterID - waiter assigned to this party.
	 * @param tableNumbers - table numbers assigned to this party.
	 */
	PartyManager(Restaurant restaurant, int waiterID, List<Integer> tableNumbers){
		this.restaurant = restaurant;
		this.waiterID = waiterID;
		this.tableNumbers = tableNumbers;
		this.customerUI = restaurant.getRestaurantInterface().getCustomerInterface();
		this.custNames.addAll(customerUI.setCustomerNames());
		this.jobs = new JobManager(this);
	}
	
	/**
	 * Adds an item to the appropriate list(food or drink).
	 * @param item - a menu item.
	 * @param customer - customer who ordered the item.
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
	 * @param item - a menu item.
	 * @param customer - customer who wants the item removed.
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
			customerUI.displayCustomerChoices();
			int choice = customerUI.getIntegerFromUser();
			switch(choice){
			case 1: {
				customerUI.displayAddItem();
				int itemNumber = customerUI.getIntegerFromUser();
				customerUI.displayCustomers(custNames);
				int customerNumber = customerUI.getIntegerFromUser();
				addItem(restaurant.getMenu().getMenuItems().get(itemNumber), customerNumber);
			}
			case 2: {
				customerUI.displayRemoveItemMenu();
				int foodDrinkChoice = customerUI.getIntegerFromUser();
				int itemToRemove;
				Order removal;
				if(foodDrinkChoice == 1){
					customerUI.displayItemsInList(tempFood, custNames);
					itemToRemove = customerUI.getIntegerFromUser();
					removal = tempFood.get(itemToRemove);
					removeItem(removal.getItem(), removal.getCust());
				}
				else if(foodDrinkChoice==2){
					customerUI.displayItemsInList(tempDrinks, custNames);
					itemToRemove = customerUI.getIntegerFromUser();
					removal = tempDrinks.get(itemToRemove);
					removeItem(removal.getItem(), removal.getCust());
				}
				else{
					customerUI.displayInvalidOption();
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
			default: customerUI.displayInvalidOption();
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
	
    /**
     * Returns the all the tickets this party manager has handled.
     * @return tickets
     */
    List<Ticket>getTickets(){
    	return tickets;
    }
    
    /**
     * Returns the tables associated with this party manager.
     * @return - tableNumbers.
     */
	public List<Integer> getTableNumbers() {
		return tableNumbers;
	}

	/**
	 * Returns the waiterID for this table.
	 * @return - waiterID.
	 */
	public int getWaiterID() {
		return waiterID;
	}
	
	/**
	 * Returns the restaurant.
	 * @return restaurant.
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Returns the list of customer names in this party.
	 * @return custNames.
	 */
	public List<String> getCustNames() {
		return custNames;
	}
	
	private List<String>custNames;
	private JobManager jobs;
    private PaymentManager payments;
	private List<Order> tempDrinks;
	private List<Order> tempFood;
	private List<Ticket> tickets;
	private CustomerUI customerUI;
	private int total=0;
	private Restaurant restaurant;
	private int waiterID;
	private List<Integer> tableNumbers;

}


