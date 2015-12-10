package com.chriswlucas.restaurant;

import java.util.List;

class HostCLI extends UserCLI implements HostUI {
		
	public HostCLI(Restaurant restaurant){
		super(restaurant);
	}
	
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
		List<Integer> tableNumbers = this.getRestaurant().getUnoccupiedTables();
		for (int tableNumber : tableNumbers) {
			printLine("\t" + tableNumber + "\t" + this.getRestaurant().getTable(tableNumber));
		}
		printLine("");
	}
}
