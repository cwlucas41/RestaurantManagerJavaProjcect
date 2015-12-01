package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;

class Restaurant {


	private List<Table> tableList = new ArrayList<Table>();
	
	public List<PartyManager> partyManagerList = new ArrayList<PartyManager>();
	
	public Menu menu;
	
	public Manager manager;
	public Host host;
	public List<Worker> waiterList;
	public List<Worker> busserList;
	public Worker kitchen;
	public Worker bar;
	public int ticket = 0;
	
	
	static Worker getKitchen(){
		return kitchen;
	};
	
	Worker getBar() {
		return bar;
	};
	
	int getTicket () {
		ticket++;
		return ticket;
	}; //has to return a ticket number
	// are you sure, does Restaurant need to know about tickets?
	
	List<Table> findTables(int partySize) {
		
	}
	
	void freeTables(List<Table> emptyTables) {
		
	}
}
