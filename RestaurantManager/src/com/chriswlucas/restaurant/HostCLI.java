package com.chriswlucas.restaurant;

import java.util.List;

class HostCLI extends UserInterface implements HostUI {
		
	public HostCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	public void addNewPartyToWaitlist(int partySize) {
		this.getRestaurant().addToWaitlist(partySize);
	}
	
	public void displayWaitlist() {
		System.out.println(this.getRestaurant().getWaitlistString());
	}
	
	public void displaySeatingNotification(int partyID, int partySize, List<Integer> tableNumbers) {
		System.out.println("Party of size " + partySize + " can now be seated at tables " + tableNumbers + ". Their partyID is " + partyID);
	}
}
