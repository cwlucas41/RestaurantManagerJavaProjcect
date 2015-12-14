package com.chriswlucas.restaurant;

/**
 * User interface is root class of CLI and GUI classes
 * primary purpose is to store restaurant reference
 * @author cwlucas41
 *
 */
public abstract class UserInterface {
	
	private Restaurant restaurant;
	
	/**
	 * Constructs new User Interface
	 * @param restaurant
	 */
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
	
	/**
	 * General display method
	 * @param string
	 */
	abstract public void display(String string);
}
