package com.chriswlucas.restaurant;

abstract class UserInterface {
	private Restaurant restaurant;
	
	public UserInterface(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	/**
	 * Returns the restaurant to the customer interface.
	 * @return restaurant.
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	abstract public void display(String string);
}
