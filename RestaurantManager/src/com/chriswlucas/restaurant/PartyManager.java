package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;

class PartyManager {
	
	/**
	 * Handles everything needed to run a party, from start to finish.
	 * @param restaurant - reference to the current restaurant.
	 * @param waiterID - waiter assigned to this party.
	 * @param tableNumbers - table numbers assigned to this party.
	 */
	PartyManager(Restaurant restaurant, int waiterID, List<Integer> tableNumbers, int partySize, boolean isAtBar){
		this.restaurant = restaurant;
		this.waiterID = waiterID;
		this.tableNumbers = tableNumbers;
		this.customerUI = restaurant.getRestaurantInterface().getCustomerInterface();
		this.custNames = new ArrayList<String>(customerUI.setCustomerNames(partySize));
		this.tempDrinks = new ArrayList<Order>();
		this.tempFood = new ArrayList<Order>();
		this.tickets = new ArrayList<Ticket>();
		this.isAtBar = isAtBar;
		this.jobs = new JobManager(this);
		this.payments = new PaymentManager(this, custNames);
	}
	
	public PaymentManager getPaymentManager() {
		return this.payments;
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
	void removeItem(Order removal){
		//TODO remove based on item not customer
		if(removal.getItem().isFood()){
			tempFood.remove(removal);
		}
		else{
			tempDrinks.remove(removal);
		}
		total -= removal.getItem().getPrice();
	}
	
	/**
	 * Empty's everything from the temporary lists.
	 */
	void emptyTemp(){
		tempFood.clear();
		tempDrinks.clear();
		total = 0;
	}
	
	/**
	 * Creates a ticket for the current order and sends out the job.
	 */
	void makeTicket() {
		List<Order> tempTempFood = new ArrayList<Order>();
		for (Order order : tempFood){
			tempTempFood.add(new Order(order));
		}
		
		List<Order> tempTempDrinks = new ArrayList<Order>();
		for (Order order : tempDrinks){
			tempTempDrinks.add(new Order(order));
		}
		
		Ticket temp = new Ticket(tempTempFood, tempTempDrinks, restaurant.getTicketNumber(), total);
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
			MenuItem item;
			customerUI.displayCustomerChoices();
			int choice = customerUI.getIntegerFromUser();
			switch(choice){
			case 0: {
				customerUI.displayItemMenu();
				int foodDrinkChoice = customerUI.getIntegerFromUser();
				if(foodDrinkChoice == 0){
					customerUI.displayAddItem(true);
					int itemNumber = customerUI.getIntegerFromUser();
					if(itemNumber>restaurant.getMenu().getFoodItems().size()){
						customerUI.displayInvalidOption();
						break;
					}
					item = restaurant.getMenu().getFoodItems().get(itemNumber);
				}
				else if(foodDrinkChoice == 1){
					customerUI.displayAddItem(false);
					int itemNumber = customerUI.getIntegerFromUser();
					if(itemNumber>restaurant.getMenu().getFoodItems().size()){
						customerUI.displayInvalidOption();
						break;
					}
					item = restaurant.getMenu().getDrinkItems().get(itemNumber);
				}
				else {
					customerUI.displayInvalidOption();
					break;
				}
	
				customerUI.displayCustomers(custNames);
				int customerNumber = customerUI.getIntegerFromUser();
				addItem(item, customerNumber);
				break;
			}
			case 1: {
				customerUI.displayItemMenu();
				int foodDrinkChoice = customerUI.getIntegerFromUser();
				int itemToRemove;
				Order removal;
				customerUI.displayInstruction();
				if(foodDrinkChoice == 0){
					customerUI.displayItemsInList(tempFood, custNames);
					itemToRemove = customerUI.getIntegerFromUser();
					removal = tempFood.get(itemToRemove);
					removeItem(removal);
				}
				else if(foodDrinkChoice==1){
					customerUI.displayItemsInList(tempDrinks, custNames);
					itemToRemove = customerUI.getIntegerFromUser();
					removal = tempDrinks.get(itemToRemove);
					removeItem(removal);
				}
				else{
					customerUI.displayInvalidOption();
				}
				break;
			}
			case 2: {
				customerUI.displayItemsInList(tempDrinks, custNames);
				customerUI.displayItemsInList(tempFood, custNames);
				break;
			}
			case 3: {
				makeTick = true;
				makeTicket();
				break;
			}
			case 4: {
				makeTick = true;
				emptyTemp();
				break;
			}
			default: customerUI.displayInvalidOption(); break;
			}
		}
	}
    
	/**
	 * On checkout creates a Payment Manager which will handle creating
	 * a receipt and handle sending out jobs to clean and free the table.
	 */
    void pay(){
        this.restaurant.collectTickets(tickets);
        this.payments.checkout(tickets);
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
	
	public JobManager getJobManager() {
		return jobs;
	}
	
	public boolean isAtBar() {
		return this.isAtBar;
	}
	
	private List<String>custNames;
	private JobManager jobs;
    private PaymentManager payments;
	private List<Order> tempDrinks;
	private List<Order> tempFood;
	private List<Ticket> tickets;
	private CustomerUI customerUI;
	private double total=0;
	private Restaurant restaurant;
	private int waiterID;
	private List<Integer> tableNumbers;
	private boolean isAtBar;
}


