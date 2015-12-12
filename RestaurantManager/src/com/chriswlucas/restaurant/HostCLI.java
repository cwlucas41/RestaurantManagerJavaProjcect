package com.chriswlucas.restaurant;

import java.util.Collections;
import java.util.List;

class HostCLI extends UserCLI implements HostUI {
		
	public HostCLI(Restaurant restaurant){
		super(restaurant);
	}
	
//	public void controlHost() {
//		boolean isFinished = false;
//		while (!isFinished){
//			printLine("Choose from the following choices:");
//			printLine("0) display unoccupied tables");
//			printLine("1) ");
//			printLine("2) ");
//			int choice = super.getIntegerFromUser();
//			switch (choice) {
//			case 0:
//			case 1:
//			case 2:
//			}
//		}
//	}
	
	public void addNewPartyToWaitlist(int partySize, boolean isAtBar) {
		this.getRestaurant().addToWaitlist(partySize, isAtBar);
	}
	
	public void displayTableWaitlist() {
		printLine(this.getRestaurant().getTableWaitlistString());
	}
	
	public void displayBarWaitlist() {
		printLine(this.getRestaurant().getBarWaitlistString());
	}
	
	public void seatCustomers(boolean isAtBar, int index, List<Integer> assignedTableNumbers) {
		int partyNumber = this.getRestaurant().createParty(isAtBar, index, assignedTableNumbers);
		if ((Integer) partyNumber != null) {
			printLine("Customers have party number of " + partyNumber);
		} else {
			printLine("The party cannot be seated at this time");
		}
	}
	
	public void displayFreeTables() {
		printLine("The available tables are:");
		printLine("\tNumber\tCapacity");
		List<Integer> tableNumbers = this.getRestaurant().getUnoccupiedTables(true);
		Collections.sort(tableNumbers);
		for (int tableNumber : tableNumbers) {
			printLine("\t" + tableNumber + "\t" + this.getRestaurant().getTable(tableNumber));
		}
		printLine("");
		printLine("The available bar seats are:");
		printLine("\tNumber\tCapacity");
		tableNumbers = this.getRestaurant().getUnoccupiedTables(false);
		Collections.sort(tableNumbers);
		for (int tableNumber : tableNumbers) {
			printLine("\t" + tableNumber + "\t" + this.getRestaurant().getTable(tableNumber));
		}
		printLine("");
	}
}
