package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;

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
	private Queue<Integer> tableWaitlist;
	private Queue<Integer> barWaitlist;
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
		this.tableWaitlist = new LinkedList<Integer>();
		this.barWaitlist = new LinkedList<Integer>();
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
	
	public PartyManager getPartyManager(int partyID) {
		return this.partyManagers.get(partyID);
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
	
	public boolean addBusser(int employeeID, String name) {
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
		if (!this.tables.containsKey(tableNumber)) {
			this.tables.put(tableNumber, new Table(numberOfSeats));
			return true;
		}
		return false;
	}
	
	public boolean addBarSeat(int tableNumber) {
		if (!this.tables.containsKey(tableNumber)) {
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
	
	public void addMenuItem(String name, double price, boolean isFood) {
		this.menu.addMenuItem(name, price, isFood);
	}
	
	public MenuItem removeMenuItem(int itemNumber, boolean isFood) {
		return this.menu.removeMenuItem(itemNumber, isFood);
	}
	
	private int nextPartyID(){
		return partyID++;
	}	
	
	public Menu getMenu(){
		return menu;
	}
	
	public Worker getKitchen(){
		return this.allWorkers.get(this.kitchenID);
	}
	
	public Worker getBar(){
		return this.allWorkers.get(this.barID);
	}
	
	public Worker getWaiter(PartyManager partyManager){
		return this.getWorker(partyManager.getWaiterID());
	}
	
	public Worker getBusser(boolean isBar){
		if (isBar) {
			return this.bussers.get(this.barID);
		} else {
			return bussers.get(getIDOfLeastBusyExceptBar(bussers));
		}
	}
	
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
	
	public Worker getWorker(int employeeID) {	
		return this.allWorkers.get(employeeID);
	}
	
	public void collectTickets(List<Ticket>tickets){
		this.tickets.addAll(tickets);
	}
	
	public void addToWaitlist(int partySize, boolean isAtBar) {
		if (isAtBar) {
			this.barWaitlist.add(partySize);
		} else {
			this.tableWaitlist.add(partySize);
		}
		createParty(isAtBar);
	}
	
	public String getTableWaitlistString() {
		return tableWaitlist.toString();
	}
	
	public String getBarWaitlistString() {
		return barWaitlist.toString();
	}
	
	public int getTicketNumber () {       
		ticketNumber++;
		return ticketNumber;
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}
	
	public void createParty(boolean isAtBar) {
		Queue<Integer> waitlist = null;
		int partySize = 0;
		
		if (isAtBar) {
			waitlist = this.barWaitlist;
		} else {
			waitlist = this.tableWaitlist;
		}
		
		if (waitlist.isEmpty()) {
			return;
		} else {
			partySize = waitlist.peek();
		}
		
		List<Integer> assignedTableNumbers = new ArrayList<Integer>();
		assignedTableNumbers.add(0);
		// TODO get all tables greater than or equal to party size
		//		if free table big enough exists, choose smallest
		//		else if smaller free table exists choose largest and repeat with difference
		//		else break with message can't seat party
		
		// TODO mark tables as occupied
		int waiterID = getIDOfLeastBusyExceptBar(waiters);
		int partyID = this.nextPartyID();
		waitlist.poll();
		this.getHostInterface().displaySeatingNotification(partyID, partySize, assignedTableNumbers);
		partyManagers.put(partyID, new PartyManager(this, waiterID, assignedTableNumbers, partySize, isAtBar));
		
//		if (!waitlist.isEmpty()) {
//			createParty();
//		}
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
		createParty(true);
		createParty(false);
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
