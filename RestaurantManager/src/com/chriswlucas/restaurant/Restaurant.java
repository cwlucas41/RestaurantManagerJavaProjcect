package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
/**
 * Manages and modifies the internal state of the Restaurant
 * Holds the user interface fore the Restaurant
 * @author cwlucas41
 * 
 */
class Restaurant implements Interfaceable{

	private Hashtable<Integer, Table> tables;
	private Hashtable<Integer, PartyManager> partyManagers;
	private Hashtable<Integer, Worker> waiters;
	private Hashtable<Integer, Worker> bussers;
	private Hashtable<Integer, Worker> allWorkers;
	private int kitchenID;
	private int barID;
	private int ticketNumber;
	private Menu menu;
	private Hashtable<String, Integer> tableWaitlist;
	private Hashtable<String, Integer> barWaitlist;
	private List<Ticket> tickets;
	private int partyID;	
	private RestaurantInterface restaurantInterface;

	/**
	 * constructs a Restaurant and initializes all fields
	 */
	public Restaurant() {
		this.waiters = new Hashtable<Integer, Worker>();
		this.bussers = new Hashtable<Integer, Worker>();
		this.allWorkers = new Hashtable<Integer, Worker>();
		this.partyManagers = new Hashtable<Integer, PartyManager>();
		this.tables = new Hashtable<Integer, Table>();
		
		this.ticketNumber = 0;
		this.partyID = 0;
		this.tableWaitlist = new Hashtable<String, Integer>();
		this.barWaitlist = new Hashtable<String, Integer>();
		this.tickets = new ArrayList<Ticket>();
		this.menu = new Menu();
		
		Worker kitchen = new Worker("Kitchen", this);
		this.kitchenID = 0;
		Worker bar = new Worker("Bar", this);
		this.barID = 1;
		this.allWorkers.put(this.kitchenID, kitchen);
		this.allWorkers.put(this.barID, bar);
		this.waiters.put(this.barID, bar);
		this.bussers.put(this.barID, bar);
		
		this.restaurantInterface = new CLInterface(this);
	}
	
	/**
	 * Gets the party manager with the specified ID
	 * @param partyID is the ID of a party
	 * @return PartyManager matching partyID
	 */
	public PartyManager getPartyManager(int partyID) {
		return this.partyManagers.get(partyID);
	}
	
	
	/**
	 * Decided if an employeeID represents the kitchen or bar
	 * @param employeeID
	 * @return boolean indicating if the kitchen or bar is represented by the id
	 */
	private boolean isKitchenOrBar(int employeeID){
		if ((employeeID == this.kitchenID) || (employeeID == this.barID)) {
			return true;
		}
		return false;
	}
	
	/**
	 * checks if a worker represented by an ID is currently the waiter for a partyManager
	 * @param employeeID
	 * @return boolean indicating the the worker is a waiter for a partyManager
	 */
	private boolean WorkerIsWaiterForAParty(int employeeID) {
		Set<Integer> keys = this.partyManagers.keySet();
		for (int key : keys) {
			if (partyManagers.get(key).getWaiterID() == employeeID) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a new waiter to the Restaurant
	 * waiters are also bussers
	 * @param employeeID to be added
	 * @param name of employee
	 * @return boolean indicating if operation was successful
	 */
	public boolean addWaiter(int employeeID, String name) {
		if (!this.allWorkers.containsKey(employeeID)){
			Worker newWaiter = new Worker(name, this);
			this.waiters.put(employeeID, newWaiter);
			this.bussers.put(employeeID, newWaiter);
			this.allWorkers.put(employeeID, newWaiter);
			return true;
		} 
		return false;
	}
	
	/**
	 * Adds a new busser to the Restaurant
	 * @param employeeID to be added
	 * @param name of employee
	 * @return boolean indicating if operation was successful
	 */
	public boolean addBusser(int employeeID, String name) {
		if (!this.allWorkers.containsKey(employeeID)){
			Worker newBusser = new Worker(name, this);
			this.bussers.put(employeeID, newBusser);
			this.allWorkers.put(employeeID, newBusser);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a waiter from the restaurant
	 * @param employeeID 
	 * @return removed waiter
	 */
	public Worker removeWaiter(int employeeID) {
		if (this.waiters.containsKey(employeeID)) {
			if ((this.waiters.get(employeeID).getNumberOfJobs() == 0) && !WorkerIsWaiterForAParty(employeeID) && !isKitchenOrBar(employeeID)) {
				this.waiters.remove(employeeID);
				this.bussers.remove(employeeID);
				return this.allWorkers.remove(employeeID);
			}
		}
		return null;
	}
	
	/**
	 * Removes a busser from the restaurant
	 * @param employeeID
	 * @return removed busser
	 */
	public Worker removeBusser(int employeeID) {
		if (this.bussers.containsKey(employeeID)) {
			if ((this.bussers.get(employeeID).getNumberOfJobs() == 0) && !isKitchenOrBar(employeeID)) {
				this.bussers.remove(employeeID);
				return this.allWorkers.remove(employeeID);
			}
		}
		return null;
	}
	
	/**
	 * Creates a new table and adds it to the restaurant
	 * @param tableNumber
	 * @param numberOfSeats
	 * @return boolean indication if operation was successful
	 */
	public boolean addTable(int tableNumber,int numberOfSeats) {
		if (!this.tables.containsKey(tableNumber)) {
			this.tables.put(tableNumber, new Table(numberOfSeats));
			return true;
		}
		return false;
	}
	
	/**
	 * Creates a new bar seat and adds it to the restaurant
	 * @param tableNumber
	 * @return boolean indicating if operation was successful
	 */
	public boolean addBarSeat(int tableNumber) {
		if (!this.tables.containsKey(tableNumber)) {
			this.tables.put(tableNumber, new BarSeat());
			return true;
		}
		return false;
	}
	
	/**
	 * Removes and returns a table from the restaurant
	 * @param tableNumber
	 * @return removed table
	 */
	public Table removeTable(int tableNumber) {
		if (this.tables.containsKey(tableNumber)) {
			if (!this.tables.get(tableNumber).isOccupied()) {
				return this.tables.remove(tableNumber);
			}
		} 
		return null;
	}
	
	/**
	 * Passes menu item information to menu to add the item
	 * @param name
	 * @param price
	 * @param isFood
	 */
	public void addMenuItem(String name, double price, boolean isFood) {
		this.menu.addMenuItem(name, price, isFood);
	}
	
//	 /**
//	  * Removes an item from the menu and returns it
//	  * @param itemNumber
//	  * @param isFood
//	  * @return removed menu item
//	  */
//	public MenuItem removeMenuItem(int itemNumber, boolean isFood) {
//		return this.menu.removeMenuItem(itemNumber, isFood);
//	}
	
	/**
	 * generates next partyID so IDs don't conflict and have consistent pattern
	 * @return next party ID as int
	 */
	private int nextPartyID(){
		return partyID++;
	}	
	
	/**
	 * Gets the menu
	 * @return menu
	 */
	public Menu getMenu(){
		return menu;
	}
	
	/**
	 * Gets the kitchen worker
	 * @return kitchen
	 */
	public Worker getKitchen(){
		return this.allWorkers.get(this.kitchenID);
	}
	
	/**
	 * Gets the bar worker
	 * @return bar
	 */
	public Worker getBar(){
		return this.allWorkers.get(this.barID);
	}
	
	/**
	 * Gets the waiter for a particular partyManager
	 * @param partyManager
	 * @return waiter for partyManager
	 */
	public Worker getWaiter(PartyManager partyManager){
		return this.getWorker(partyManager.getWaiterID());
	}
	
	/**
	 * Finds an appropriate busser to be assigned a job
	 * if isBar is asserted, the bar is returned because the bar busses parties seated at the bar
	 * @param isBar
	 * @return busser
	 */
	public Worker getBusser(boolean isBar){
		if (isBar) {
			return this.bussers.get(this.barID);
		} else {
			return bussers.get(getIDOfLeastBusyExceptBar(bussers));
		}
	}
	
	/**
	 * Gets the ID of the least busy worker in a hashtable of workers
	 * The bar is excluded from consideration and will not be returned
	 * @param hashTable of workers
	 * @return workerID
	 */
	private int getIDOfLeastBusyExceptBar(Hashtable<Integer, Worker> hashTable) {
		Set<Integer> keys = hashTable.keySet();
		int leastToDoSoFar = Integer.MAX_VALUE;
		int workerID = 0;
		for (Integer key : keys) {
			if (key != this.barID) {
				Worker worker = hashTable.get(key);
				int numberOfJobs = worker.getNumberOfJobs();
				if (numberOfJobs < leastToDoSoFar) {
					workerID = key;
					leastToDoSoFar = numberOfJobs;
				}
			}
		}
		return workerID;
	}
	
	/**
	 * Gets the worker represented by the given ID
	 * @param employeeID
	 * @return worker with specified ID
	 */
	public Worker getWorker(int employeeID) {	
		return this.allWorkers.get(employeeID);
	}
	
	public void collectTickets(List<Ticket>tickets){
		this.tickets.addAll(tickets);
	}
	
	/**
	 * Adds a party to a waitlist
	 * @param parytName identifier for party
	 * @param partySize size of party
	 * @param isAtBar if asserted, party will be added to bar waitlist, otherwise to normal waitlist
	 */
	public void addToWaitlist(String parytName, int partySize, boolean isAtBar) {
		if (isAtBar) {
			this.barWaitlist.put(parytName, partySize);
		} else {
			this.tableWaitlist.put(parytName, partySize);
		}
	}
	
	/**
	 * Gets the waitlist for normal tables
	 * @return waitlist hashtable
	 */
	public Hashtable<String, Integer> getTableWaitlist() {
		return tableWaitlist;
	}
	
	/**
	 * Gets the waitlist for the bar
	 * @return waitlist hashtable
	 */
	public Hashtable<String, Integer> getBarWaitlist() {
		return barWaitlist;
	}
	
	/**
	 * Gets the next ticket number to ensure uniqueness and a simple pattern
	 * @return ticket number int
	 */
	public int getTicketNumber () {       
		ticketNumber++;
		return ticketNumber;
	}
	
	/**
	 * gets all of the stored tickets
	 * @return a list of tickets
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}
	
	/**
	 * Creates a new party manager if possible from the given information
	 * @param isAtBar should the party be seated at the bar
	 * @param partyName used as key for waitlist
	 * @return party number Integer
	 */
	public Integer createParty(boolean isAtBar, String partyName) {
		
		Hashtable<String, Integer> waitlist;
		int waiterID;
		if (isAtBar) {
			waitlist = this.barWaitlist;
			waiterID = this.barID;
		} else {
			waitlist = this.tableWaitlist;
			waiterID = getIDOfLeastBusyExceptBar(waiters);
		}
		int partySize = waitlist.get(partyName);
		if (partySize == 0) {
			return null;
		}
		List<Integer> assignedTableNumbers = this.getHostInterface().assignTableNumbers(isAtBar, partySize);
		if (assignedTableNumbers == null) {
			return null;
		}
		
		// can be seated
		markTablesIsOccupiedByNumberAs(assignedTableNumbers, true);
		waitlist.remove(partyName);		
		int partyID = this.nextPartyID();
		partyManagers.put(partyID, new PartyManager(this, waiterID, assignedTableNumbers, partySize, isAtBar));
		return partyID;
	}
	
	/**
	 * Marks a list of table numbers as either occupied or not occupied
	 * @param tableNumbers list
	 * @param isOccupied selector
	 */
	public void markTablesIsOccupiedByNumberAs(List<Integer> tableNumbers, boolean isOccupied) {
		for (int tableNumber : tableNumbers) {
			Table table = this.tables.get(tableNumber);
			if (isOccupied) {
				table.setOccupied();
			} else {
				table.setNotOccupied();
			}
		}
	}
	
	/**
	 * Finds all of the unoccupied tables and returns a list of their IDs
	 * @param barOrNormal if asserted, empty bar seats are returned, else empty tables
	 * @return unoccupied tableID list
	 */
	public List<Integer> getUnoccupiedTables(boolean barOrNormal) {
		List<Integer> unoccupiedTableKeys = new ArrayList<Integer>();
		Set<Integer> keys = tables.keySet();
		for (int key : keys) {
			Table table = this.tables.get(key);
			if (!table.isOccupied()) {
				boolean isBarSeat = table.getClass() == BarSeat.class;
				if (barOrNormal == isBarSeat) {
					unoccupiedTableKeys.add(key);
				}
			}
		}
		return unoccupiedTableKeys;
	}
	
	/**
	 * Returns key set of all Workers
	 * @return key set
	 */
	public Set<Integer> getSetOfAllWorkersIDs() {
		return this.allWorkers.keySet();
	}
	
	/**
	 * Returns key set of all Producers
	 * @return key set
	 */
	public Set<Integer> getSetOFAllProducerIDs() {
		Set<Integer> ids = new TreeSet<Integer>();
		ids.add(this.kitchenID);
		ids.add(this.barID);
		return ids;
	}
	
	/**
	 * Returns key set of all Waiters
	 * @return key set
	 */
	public Set<Integer> getSetOfAllWaiterIDs() {
		return this.waiters.keySet();
	}
	
	/**
	 * Returns key set of all Bussers
	 * @return key set
	 */
	public Set<Integer> getSetOfAllBusserIDs() {
		return this.bussers.keySet();
	}
	
	/**
	 * Returns key set of all Tables
	 * @return key set
	 */
	public Set<Integer> getSetOfTableNumbers() {
		return this.tables.keySet();
	}
	
	/**
	 * Returns key set of all partyManagers
	 * @return key set
	 */
	public Set<Integer> getSetOfPartyNumbers() {
		return this.partyManagers.keySet();
	}
	
	/**
	 * gets the table with the provided number
	 * @param tableNumber
	 * @return table with given number
	 */
	public Table getTable(int tableNumber) {
		return this.tables.get(tableNumber);
	}

	@Override
	public WorkerUI getWorkerInterface() {
		return this.restaurantInterface.getWorkerInterface();
	}

	@Override
	public HostUI getHostInterface() {
		return this.restaurantInterface.getHostInterface();
	}

	@Override
	public ManagerUI getManagerInterface() {
		return this.restaurantInterface.getManagerInterface();
	}
	
	@Override
	public CustomerUI getCustomerInterface() {
		return this.restaurantInterface.getCustomerInterface();
	}
}
