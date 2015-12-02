package com.chriswlucas.restaurant;

class Host extends Employee {
	
	public Host(String name, Restaurant restaurant){
		super(name, restaurant);
	}
	
	public void addNewPartyToWaitlist(int partySize) {
		this.getRestaurant().addToWaitlist(partySize);
	}
}
