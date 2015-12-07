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
		this.jobs = new JobManager(this);
	}
	
	/**
	 * Gets the customer names from the customers.
	 */
	void getNames(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many people in your party: ");
		numPeople = scanner.nextInt();
		
		for (int i = 0; i<numPeople; i++){
			int temp = i + 1;
			String output = "Enter customer "+temp+"(FirstName and first letter of last name without spaces): ";
			System.out.print(output);
			custNames.add(scanner.next());
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
		total+= item.getPrice();
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
	
	int getIntegerFromUser(){
		Scanner scanner = new Scanner(System.in);
		int data = scanner.nextInt();
		scanner.close();
		return data;
	}
	
	void displayAddItem(){
		List<MenuItem> menu = restaurant.getMenuItems();
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
		ListIterator<String> it = custNames.listIterator();
		int j = 0;
		while(it.hasNext()){
			String name = j + it.next();
			System.out.println(name);
			j++;
		}
		System.out.println();
		System.out.println("Choose the number of the customer you want:");
		int customerNumber = getIntegerFromUser();
		addItem(menu.get(orderVal), customerNumber);
	}
	
	void displayRemoveItem(){
		System.out.println("Choose 1 or 2 for the following choices.");
		System.out.println("1) Remove a drink item");
		System.out.println("2) Remove a food item");
		int choice = getIntegerFromUser();
		if (choice == 1){
			ListIterator<Order> iterator = tempDrinks.listIterator();
			int i = 0;
			while (iterator.hasNext()){
				String out = i+") "+custNames.get(iterator.next().getCust()) + ": " + iterator.next().toString();
				System.out.println(out);
				i++;
			}
			Order drink = tempDrinks.get(getIntegerFromUser());
			removeItem(drink.getItem(), drink.getCust());
		}
		else if (choice == 2){
			ListIterator<Order> iterator = tempFood.listIterator();
			int i = 0;
			while (iterator.hasNext()){
				String out = i+") "+custNames.get(iterator.next().getCust()) + ": " + iterator.next().toString();
				System.out.println(out);
				i++;
			}
			Order food = tempFood.get(getIntegerFromUser());
			removeItem(food.getItem(), food.getCust());
		}
		else{
			System.out.println("Invalid option.");
		}
	}
	/**
	 * Takes an order from the customers.
	 */
	void takeOrder(){
		boolean makeTick = false;
		while(!makeTick){
			System.out.println("Choose 1, 2, 3, or 4 for the following choices.");
			System.out.println("1) Add item to ticket.");
			System.out.println("2) Remove item from ticket.");
			System.out.println("3) Submit order");
			System.out.println("4) Cancel order");
			int choice = getIntegerFromUser();
			switch(choice){
			case 1: displayAddItem();
			case 2: displayRemoveItem();
			case 3: {
				makeTick = true;
				makeTicket();
			}
			case 4: {
				makeTick = true;
				emptyTemp();
			}
			default: System.out.println("Invalid option");
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

	List<String>custNames;
	JobManager jobs;
    PaymentManager payments;
	List<Order> tempDrinks;
	List<Order> tempFood;
	List<Ticket> tickets;
	int total=0;
	boolean addingItems;
	Restaurant restaurant;
	private int numPeople;
	private int waiterID;
	private List<Integer> tableNumbers;

}


