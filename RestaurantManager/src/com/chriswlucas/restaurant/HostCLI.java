package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HostCLI extends UserCLI implements HostUI {
		
	public HostCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	public void controlHost() {
		boolean isFinished = false;
		while (!isFinished){
			printLine("Choose from the following choices:");
			printLine("0) display unoccupied tables");
			printLine("1) display unoccupied bar seats");
			printLine("2) ");
			int choice = super.getIntegerFromUser();
			switch (choice) {
			case 0: this.displayFreeTables(false); break;
			case 1: this.displayFreeTables(true); break;
			case 2:
			}
		}
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
	
	public void seatCustomers(boolean isAtBar, int index) {
		int partyNumber = this.getRestaurant().createParty(isAtBar, index);
		if ((Integer) partyNumber != null) {
			printLine("Customers have party number of " + partyNumber);
		} else {
			printLine("The party cannot be seated at this time");
		}
	}
	
	public List<Integer> assignTableNumbers(boolean isAtBar, int partySize) {
		List<Integer> assignedTables = new ArrayList<Integer>();
		int remainingToSeat = partySize;
		List<Integer> freeTables = this.displayFreeTables(isAtBar);
		while (remainingToSeat > 0) {
			printLine("Choose table number, remaining customers to seat is " + remainingToSeat);
			int tableNumber = getIntegerFromUser();
			if (freeTables.contains(tableNumber) && !assignedTables.contains(tableNumber)) {
				assignedTables.add(tableNumber);
				remainingToSeat -= this.getRestaurant().getTable(tableNumber).getsize();
			} else {
				printLine("Invalid choice, please try again");
			}
		}
		return assignedTables;
	}
	
	public List<Integer> displayFreeTables(boolean isAtBar) {
		if (isAtBar) {
			printLine("The available bar seats are:");
		} else {
			printLine("The available tables are:");
		}
		printLine("\tNumber\tCapacity");
		List<Integer> tableNumbers = this.getRestaurant().getUnoccupiedTables(isAtBar);
		Collections.sort(tableNumbers);
		for (int tableNumber : tableNumbers) {
			printLine("\t" + tableNumber + "\t" + this.getRestaurant().getTable(tableNumber));
		}
		printLine("");
		return tableNumbers;
	}
}
