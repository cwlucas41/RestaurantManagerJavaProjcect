package com.chriswlucas.restaurant;

class HostCLI extends UserInterface implements HostUI {
		
	public HostCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	public void addNewPartyToWaitlist(int partySize) {
		this.getRestaurant().getRestaurantInterface().getHostInterface().addNewPartyToWaitlist(partySize);
	}
}
