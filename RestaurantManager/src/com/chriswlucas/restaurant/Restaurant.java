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
	
	private int kitchenID;
	private Worker kitchen;
	private int barID;
	private Worker bar;
	
	private int ticket;
	private Menu menu;
	private Queue<Integer> waitlist;
	private List<Ticket> tickets;
	private int partyID;
	
	private RestaurantInterface restaurantInterface;

	public Restaurant() {
		this.waiters = new Hashtable<Integer, Worker>();
		this.bussers = new Hashtable<Integer, Worker>();
		this.partyManagers = new Hashtable<Integer, PartyManager>();
		this.tables = new Hashtable<Integer, Table>();
		this.ticket = 0;
		this.partyID = 0;
		this.waitlist = new LinkedList<Integer>();
		this.tickets = new ArrayList<Ticket>();
		this.restaurantInterface = new CLInterface(this);
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
	
	public void hireNewWaiter(int employeeID, String name) {
		Worker newWaiter = new Worker(name, this);
		this.waiters.put(employeeID, newWaiter);
		this.bussers.put(employeeID, newWaiter);
	}
	
	public void hireNewBusser(int employeeID, String name) {
		Worker newBusser = new Worker(name, this);
		this.bussers.put(employeeID, newBusser);
	}
	
	public Worker removeWaiter(int employeeID) {
		if ((this.waiters.get(employeeID).getNumberOfJobs() == 0) && !WorkerIsWaiterForAParty(employeeID)) {
			Worker tempWaiter = this.waiters.remove(employeeID);
			this.waiters.remove(employeeID);
			return tempWaiter;
		} else {
			return null;
		}
	}
	
	public Worker removeBusser(int employeeID) {
		if (this.bussers.get(employeeID).getNumberOfJobs() == 0) {
			return this.bussers.remove(employeeID);
		} else {
			return null;
		}
	}
	
	public void addTable(int tableNumber,int numberOfSeats) {
		
	}
	
	public Table removeTable(int tableNumber) {
		
	}
	
	public void addMenuItem(int itemNumber){
		
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
		if (employeeID == kitchenID) {
			return kitchen;
		} else if (employeeID == barID) {
			return bar;
		} else if (waiters.containsKey(employeeID)) {
			return waiters.get(employeeID);
		} else if (bussers.contains(employeeID)){
			return waiters.get(employeeID);
		} else {
			return null;
		}
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
		ticket++;
		return ticket;
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
