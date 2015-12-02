package com.chriswlucas.restaurant;

class HostCLI implements HostUI {
	
	private Restaurant restaurant;
	
	public HostCLI(Restaurant restaurant){
		this.restaurant = restaurant;
	}
	
	public void addNewPartyToWaitlist(int partySize) {
		this.restaurant.getHost().addNewPartyToWaitlist(partySize);
	}
}
