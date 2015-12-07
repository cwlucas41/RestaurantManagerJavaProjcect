package com.chriswlucas.restaurant;

class UserInterface {
	private Restaurant restaurant;
	
	public UserInterface(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
}
