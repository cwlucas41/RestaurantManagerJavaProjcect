package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;

class Restaurant {

	private Hashtable<Integer, Table> tables;
	private Hashtable<Integer, PartyManager> partyManagers;
	
	private Hashtable<Integer, Worker> waiters;
	private Hashtable<Integer, Worker> bussers;
	private Hashtable<Integer, Worker> allWorkers;
	
	private int kitchenID;
	private int barID;
	
	private int ticketNumber;
	private Menu menu;
	private Queue<Integer> waitlist;
	private List<Ticket> tickets;
	private int partyID;
	
	private RestaurantInterface restaurantInterface;

	public Restaurant() {
		this.waiters = new Hashtable<Integer, Worker>();
		this.bussers = new Hashtable<Integer, Worker>();
		this.allWorkers = new Hashtable<Integer, Worker>();
		this.partyManagers = new Hashtable<Integer, PartyManager>();
		this.tables = new Hashtable<Integer, Table>();
		
		this.ticketNumber = 0;
		this.partyID = 0;
		this.waitlist = new LinkedList<Integer>();
		this.tickets = new ArrayList<Ticket>();
		
		Worker kitchen = new Worker("Kitchen", this);
		this.kitchenID = 1;
		Worker bar = new Worker("Bar", this);
		this.barID = 2;
		this.allWorkers.put(this.kitchenID, kitchen);
		this.allWorkers.put(this.barID, kitchen);
		this.waiters.put(this.barID, bar);
		this.bussers.put(this.barID, bar);
		
		this.restaurantInterface = new CLInterface(this);
	}
	
	private boolean isKitchenOrBar(int employeeID){
		if ((employeeID == this.kitchenID) || (employeeID == this.barID)) {
			return true;
		}
		return false;
	}
	
	private boolean WorkerIsWaiterForAParty(int employeeID) {
		Set<Integer> keys = this.partyManagers.keySet();
		for (int key : keys) {
			if (partyManagers.get(key).getWaiterID() == employeeID) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hireNewWaiter(int employeeID, String name) {
		if (!this.allWorkers.containsKey(employeeID)){
			Worker newWaiter = new Worker(name, this);
			this.waiters.put(employeeID, newWaiter);
			this.bussers.put(employeeID, newWaiter);
			this.allWorkers.put(employeeID, newWaiter);
			return true;
		} 
		return false;
	}
	
	public boolean hireNewBusser(int employeeID, String name) {
		if (!this.allWorkers.containsKey(employeeID)){
			Worker newBusser = new Worker(name, this);
			this.bussers.put(employeeID, newBusser);
			this.allWorkers.put(employeeID, newBusser);
			return true;
		}
		return false;
	}
	
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
	
	public Worker removeBusser(int employeeID) {
		if (this.bussers.containsKey(employeeID)) {
			if ((this.bussers.get(employeeID).getNumberOfJobs() == 0) && !isKitchenOrBar(employeeID)) {
				this.bussers.remove(employeeID);
				return this.allWorkers.remove(employeeID);
			}
		}
		return null;
	}
	
	public boolean addTable(int tableNumber,int numberOfSeats) {
		if (this.tables.containsKey(tableNumber)) {
			this.tables.put(tableNumber, new Table(numberOfSeats));
			return true;
		}
		return false;
	}
	
	public boolean addBarSeat(int tableNumber) {
		if (this.tables.containsKey(tableNumber)) {
			this.tables.put(tableNumber, new BarSeat());
			return true;
		}
		return false;
	}
	
	public Table removeTable(int tableNumber) {
		if (this.tables.containsKey(tableNumber)) {
			if (!this.tables.get(tableNumber).isOccupied()) {
				return this.tables.remove(tableNumber);
			}
		} 
		return null;
	}
	
	public boolean addMenuItem(int itemNumber){
		
	}
	
	public MenuItem removeMenuItem(int itemNumber) {
		
	}
	
	private int nextPartyID(){
		return partyID++;
	}	
	
	public Menu getMenu(){
		return menu;
	}
	
	public Worker getKitchen(){
		return kitchen;
	}
	
	public Worker getBar(){
		return bar;
	}
	
	public Worker getWaiter(PartyManager partyManager){
		return this.getWorker(partyManager.getWaiterID());
	}
	
	public Worker getBusser(){
		return bussers.get(getLeastBusy(bussers));
	}
	
	public int getLeastBusy(Hashtable<Integer, Worker> table) {
		Set<Integer> keys = table.keySet();
		int leastToDoSoFar = Integer.MAX_VALUE;
		int workerID = 0;
		for (Integer key : keys) {
			Worker worker = table.get(key);
			int numberOfJobs = worker.getNumberOfJobs();
			if (numberOfJobs < leastToDoSoFar) {
				workerID = key;
				leastToDoSoFar = numberOfJobs;
			}
		}
		return workerID;
	}
	
	public Worker getWorker(int employeeID) {	
		return this.allWorkers.get(employeeID);
	}
	
	public void collectTickets(List<Ticket>tickets){
		this.tickets.addAll(tickets);
	}
	
	public void addToWaitlist(int partySize) {
		this.waitlist.add(partySize);
		createParty();
	}
	
	public List<Table> convertTableNumbersToTables(List<Integer> tableNumbers){
		List<Table> tablesList = new ArrayList<Table>();
		ListIterator<Integer> iterator = tableNumbers.listIterator();
		while (iterator.hasNext()) {
			int curr = iterator.next();
			if (this.tables.containsKey(curr)) {
				tablesList.add(this.tables.get(curr));
			}
		}
		return tablesList;
	}
	
	public int getTicketNumber () {       
		ticketNumber++;
		return ticketNumber;
	};
	
	public List<Ticket> getTickets() {
		return tickets;
	}
	
	public void createParty() {
		int waiterID = getLeastBusy(waiters);
		List<Integer> tableNumbers = new ArrayList<Integer>(); 
		// TODO get all tables greater than or equal to party size
		//		if free table big enough exists, choose smallest
		//		else if smaller free table exists choose largest and repeat with difference
		//		else break with message can't seat party
		
		// TODO mark tables as occupied
		partyManagers.put(this.nextPartyID(), new PartyManager(this, waiterID, tableNumbers));
		
		if (!waitlist.isEmpty()) {
			createParty();
		}
	}
	
	public List<Integer> getUnoccupiedTables() {
		List<Integer> unoccupiedTableKeys = new ArrayList<Integer>();
		Set<Integer> keys = tables.keySet();
		for (int key : keys) {
			if (!tables.get(key).isOccupied()) {
				unoccupiedTableKeys.add(key);
			}
		}
		return unoccupiedTableKeys;
	}
	
	public void freeTables(List<Integer> emptyTables) {
		ListIterator<Integer> iterator = emptyTables.listIterator();
		while (iterator.hasNext()) {
			this.tables.get(iterator.next()).setNotOccupied();
		}
		createParty();
	}
	
	public RestaurantInterface getRestaurantInterface() {
		return restaurantInterface;
	}
}
