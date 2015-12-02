package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;

class Restaurant {

	private List<Table> tableList = new ArrayList<Table>();
	
	private List<PartyManager> partyManagerList = new ArrayList<PartyManager>();
	
	private Menu menu;
	
	private Manager manager;
	private Host host;
	private List<Worker> waiterList;
	private List<Worker> busserList;
	private Worker kitchen;
	private Worker bar;
	private int ticket = 0;
	
	private UserInterface workerInterface;
	
	public Restaurant() {
		this.workerInterface = new WorkerCLI(this);
	}
	
	
	public List<MenuItem> getMenuItems(){
		return menu.getMenu();
	}
	
	public Worker getWorker(int employeeID) {
		
	}
	
	public Host getHost() {
		
	}
	
	public Worker getWaiter(PartyManager partyManager){
		
	}
	
	public Worker getKitchen(PartyManager partyManager){
		return kitchen;
	}
	
	public Worker getBar(PartyManager partyManager){
		return bar;
	}
	
	public Worker getBusser(PartyManager partyManager){
		
	}
	
	public void collectTickets(List<Ticket>tickets){
		
	}
	
	public void addToWaitlist(int partySize) {
		
	}
	
	public List<Table> getTables(PartyManager partyManager){
		return;
	}
	
	int getTicket () {       
		ticket++;
		return ticket;
	}; //has to return a ticket number
	// are you sure, does Restaurant need to know about tickets?
	
	List<Table> findTables(int partySize) {
		return;
	}
	
	void freeTables(List<Table> emptyTables) {
		
	}
}
