package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;

class PartyManager {
	
	/**
	 * Handles everything needed to run a party, from start to finish.
	 * @param restaurant - reference to the current restaurant.
	 * @param waiterID - waiter assigned to this party.
	 * @param tableNumbers - table numbers assigned to this party.
	 * @author Nick Anderson
	 */
	PartyManager(Restaurant restaurant, int waiterID, List<Integer> tableNumbers, int partySize, boolean isAtBar){
		this.restaurant = restaurant;
		this.waiterID = waiterID;
		this.tableNumbers = tableNumbers;
		this.customerUI = restaurant.getCustomerInterface();
		this.custNames = new ArrayList<String>(customerUI.setCustomerNames(partySize));
		this.tempDrinksByCustomerID = initializeHashtable();
		this.tempFoodByCustomerID = initializeHashtable();
		this.itemsByCustomerID = initializeHashtable();
		this.tickets = new ArrayList<Ticket>();
		this.isAtBar = isAtBar;
		total = 0;
		this.jobs = new JobManager(this);
		this.payments = new PaymentManager(this, custNames);
	}
	
	private Hashtable<Integer, List<MenuItem>> initializeHashtable() {
		Hashtable<Integer, List<MenuItem>> hashtable = new Hashtable<Integer, List<MenuItem>>();
		for (int i = 0; i < custNames.size(); i++) {
			hashtable.put(i, new ArrayList<MenuItem>());
		}
		return hashtable;
	}
	
	private void clearTemp() {
		this.tempDrinksByCustomerID = initializeHashtable();
		this.tempFoodByCustomerID = initializeHashtable();
		total = 0;
	}
	
	/**
	 * Returns the PaymentManager.
	 * @return payments.
	 */
	public PaymentManager getPaymentManager() {
		return payments;
	}
	
	/**
	 * Adds an item to the appropriate list(food or drink).
	 * @param item - a menu item.
	 * @param customer - customer who ordered the item.
	 */
	void addItem(MenuItem item, int customer){
		if (item.isFood()){
			tempFoodByCustomerID.get(customer).add(item);
		}
		else{
			tempDrinksByCustomerID.get(customer).add(item);
		}
		total+= item.getPrice();
	}
	
	/**
	 * Removes an item from the appropriate list (food or drink).
	 * @param item - a menu item.
	 * @param customer - customer who wants the item removed.
	 */
	void removeItem(MenuItem item, int customer){
		if(item.isFood()){
			this.tempFoodByCustomerID.get(customer).remove(item);
		}
		else{
			this.tempDrinksByCustomerID.get(customer).remove(item);
		}
		total -= item.getPrice();
	}
	
	/**
	 * Creates a ticket for the current order and sends out the job.
	 */
	void makeTicket() {
		Ticket temp = new Ticket(tempFoodByCustomerID, tempDrinksByCustomerID, restaurant.getTicketNumber(), total);
		tickets.add(temp);
		jobs.assignProducingJob(temp);
		for (int key : itemsByCustomerID.keySet()) {
			List<MenuItem> customerItems = this.itemsByCustomerID.get(key);
			customerItems.addAll(this.tempFoodByCustomerID.get(key));
			customerItems.addAll(this.tempDrinksByCustomerID.get(key));
		}
		clearTemp();
	}

	/**
	 * Takes an order from the customers.
	 * Case 0 Takes user through adding an item to the ticket.
	 * Case 1 Takes user through removing an item from the ticket.
	 * Case 2 Lets user view the current ticket.
	 * Case 3 Let the user submit their ticket.
	 * Case 4 Lets the user cancel their current ticket.
	 */
	void takeOrder(){
		boolean makeTick = false;
		while(!makeTick){
			MenuItem item;
			customerUI.displayOrderingChoices();
			int choice = customerUI.getIntegerFromUser();
			switch(choice){
			case 0: {
				customerUI.displayFoodOrDrinkChoice();
				int foodDrinkChoice = customerUI.getIntegerFromUser();
				if(foodDrinkChoice == 0){
					customerUI.displayMenuItems(true);
					int itemNumber = customerUI.getIntegerFromUser();
					if(itemNumber>restaurant.getMenu().getFoodItems().size()){
						customerUI.displayInvalidOption();
						break;
					}
					item = restaurant.getMenu().getFoodItems().get(itemNumber);
				}
				else if(foodDrinkChoice == 1){
					customerUI.displayMenuItems(false);
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
				customerUI.displayFoodOrDrinkChoice();
				int foodDrinkChoice = customerUI.getIntegerFromUser();
				int itemToRemove;
				customerUI.displayRemoveInstruction();
				if(foodDrinkChoice == 0){
					customerUI.displayCustomers(custNames);
					int customer = customerUI.getIntegerFromUser();
					customerUI.displayItemsInList(this.tempFoodByCustomerID.get(customer));	
					itemToRemove = customerUI.getIntegerFromUser();
					removeItem(this.tempFoodByCustomerID.get(customer).get(itemToRemove), customer);
				}
				else if(foodDrinkChoice==1){
					customerUI.displayCustomers(custNames);
					int customer = customerUI.getIntegerFromUser();
					customerUI.displayItemsInList(this.tempDrinksByCustomerID.get(customer));
					itemToRemove = customerUI.getIntegerFromUser();
					removeItem(this.tempDrinksByCustomerID.get(customer).get(itemToRemove), customer);
				}
				else{
					customerUI.displayInvalidOption();
				}
				break;
			}
			case 2: {
				for (int i = 0; i < custNames.size(); i++) {
					customerUI.display(this.custNames.get(i));
					customerUI.displayItemsInList(this.tempDrinksByCustomerID.get(i));
					customerUI.displayItemsInList(this.tempFoodByCustomerID.get(i));
				}
				
				break;
			}
			case 3: {
				makeTick = true;
				makeTicket();
				break;
			}
			case 4: {
				makeTick = true;
				clearTemp();
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
    void pay(int partyID){
        this.restaurant.collectTickets(tickets);
        this.payments.checkout(tickets, partyID);
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
	
	public List<Integer> getCustIndeces(){
		List<Integer> custInd = new ArrayList<Integer>();
		ListIterator<String> it = custNames.listIterator();
		int i = 0;
		while(it.hasNext()){
			custInd.add(i);
			i++;
		}
		return custInd;
	}
	/**
	 * Returns the JobManager.
	 * @return jobs.
	 */
	public JobManager getJobManager() {
		return jobs;
	}
	
	/**
	 * Tells whether this party is at the bar or not.
	 * @return isAtBar.
	 */
	public boolean isAtBar() {
		return isAtBar;
	}
	
	public List<MenuItem> getItemsForCustomerID(int customerID) {
		return this.itemsByCustomerID.get(customerID);
	}
	
	private List<String>custNames;
	private JobManager jobs;
    private PaymentManager payments;
	private Hashtable<Integer, List<MenuItem>> tempDrinksByCustomerID;
	private Hashtable<Integer, List<MenuItem>> tempFoodByCustomerID;
	private Hashtable<Integer, List<MenuItem>> itemsByCustomerID;
	private List<Ticket> tickets;
	private CustomerUI customerUI;
	private double total;
	private Restaurant restaurant;
	private int waiterID;
	private List<Integer> tableNumbers;
	private boolean isAtBar;
}


