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
		System.out.println(this.getRestaurant().getTableWaitlistString());
	}
	
	public void displayBarWaitlist() {
		System.out.println(this.getRestaurant().getBarWaitlistString());
	}
	
	public void displaySeatingNotification(int partyID, int partySize, List<Integer> tableNumbers) {
		System.out.println("Party of size " + partySize + " can now be seated at tables " + tableNumbers + ". Their partyID is " + partyID);
	}
}
