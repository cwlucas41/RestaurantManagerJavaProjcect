package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
	private List<Integer> tableWaitlist;
	private List<Integer> barWaitlist;
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
	
	public Integer createParty(boolean isAtBar, int index) {
		
		List<Integer> waitlist;
		int waiterID;
		if (isAtBar) {
			waitlist = this.barWaitlist;
			waiterID = this.barID;
		} else {
			waitlist = this.tableWaitlist;
			waiterID = getIDOfLeastBusyExceptBar(waiters);
		}
		
		int partySize = waitlist.get(index);
		if ((Integer) partySize == null) {
			return null;
		}
		List<Integer> assignedTableNumbers = this.getHostInterface().assignTableNumbers(isAtBar, partySize);
		int capacityOfAssignedTables = getCapacityOfTablesByNumber(assignedTableNumbers);
		if (capacityOfAssignedTables < partySize) {
			return null;
		}
		
		// can be seated
		markTablesIsOccupiedByNumberAs(assignedTableNumbers, true);
		waitlist.remove(index);		
		int partyID = this.nextPartyID();
		partyManagers.put(partyID, new PartyManager(this, waiterID, assignedTableNumbers, partySize, isAtBar));
		return partyID;
	}
	
	private int getCapacityOfTablesByNumber(List<Integer> tableNumbers) {
		int capacity = 0;
		for (int tableNumber : tableNumbers) {
			capacity += this.tables.get(tableNumber).getsize();
		}
		return capacity;
	}
	
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
	
	public List<Integer> getUnoccupiedTables(boolean barOrNormal) {
		List<Integer> unoccupiedTableKeys = new ArrayList<Integer>();
		Set<Integer> keys = tables.keySet();
		for (int key : keys) {
			Table table = this.tables.get(key);
			if (!table.isOccupied()) {
				boolean isBarSeat = table.getClass() == BarSeat.class;
				if (barOrNormal != isBarSeat) {
					unoccupiedTableKeys.add(key);
				}
			}
		}
		return unoccupiedTableKeys;
	}
	
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
