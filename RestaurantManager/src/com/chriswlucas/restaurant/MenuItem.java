package com.chriswlucas.restaurant;

/**
 * Stores the information for a menu item
 * @author Jonathan Bundy
 *
 */
public class MenuItem {

	private double price;
	
	private String Name;
	
	private boolean isFood;
	
	/**
	 * Constructs a menuItem and fully initializes it
	 * @param name
	 * @param price
	 * @param isFood
	 */
	MenuItem(String name, double price, boolean isFood){
		this.Name = name;
		this.price = price;
		this.isFood = isFood;
	}
	
	/**
	 * Gets the name of the item
	 * @return name
	 */
	String getItemName(){
		return Name;
	}
	
	/**
	 * Gets the price of the item
	 * @return price
	 */
	double getPrice(){
		return price;
	}
	/**
	 * Gets if item is a food item
	 * @return true if food item, false if drink item
	 */
	boolean isFood(){
		return isFood;
	}
	/**
	 * Makes displayable string of item fields
	 */
	@Override
	public String toString(){
		return "\t$" + getPrice() + "\t" + getItemName();
	}
}
