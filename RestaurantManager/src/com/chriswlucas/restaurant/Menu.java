package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Menu stores food and drink menu items
 * @author Jonathan Bundy
 *
 */
public class Menu {
	
	/**
	 * Constructs a menu and initializes all fields
	 */
	public Menu() {
		this.foodItems = new ArrayList<MenuItem>();
		this.drinkItems = new ArrayList<MenuItem>();
	}
	
	/**
	 * Gets list of food items
	 * @return list of food items
	 */
	public List<MenuItem> getFoodItems(){
		return foodItems;
	}
	
	/**
	 * Gets list of drink items
	 * @return list of drink items
	 */
	public List<MenuItem> getDrinkItems(){
		return drinkItems;
	}
	
	/**
	 * returns a string that is a nice formatting of the menu
	 * @param items
	 * @return displayable string
	 */
	public String toString(List<MenuItem> items){
		ListIterator<MenuItem> iterator = items.listIterator();
		String menuString="\tPrice\tItem\n";
		int i = 0;
		while(iterator.hasNext()){
			String temp = i +") " + iterator.next().toString();
			menuString+=(temp +"\n");
			i++;
		}
		return menuString;
	}
	
	/**
	 * Returns sub-menu for food or drinks as a string
	 * @param isFood
	 * @return
	 */
	public String displayMenu(boolean isFood){
		if(isFood){
			return "Food Items:\n" + toString(foodItems);
		}
		else{
			return "Drink Items:\n" + toString(drinkItems);
		}
	}
	
	/**
	 * Adds a new menu item to the menu with the specified information
	 * @param name
	 * @param price
	 * @param isFood
	 */
	public void addMenuItem(String name, double price, boolean isFood){
		MenuItem item = new MenuItem(name, price, isFood);
		if (isFood) {
			foodItems.add(item);
		} else {
			drinkItems.add(item);
		}
	}
	
//	/**
//	 * Removes a specified menu item from the menu
//	 * @param itemNumber
//	 * @param isFood
//	 * @return removed item
//	 */
//	public MenuItem removeMenuItem(int itemNumber, boolean isFood) {
//		if(isFood){
//			return foodItems.remove(itemNumber);
//		} else {
//			return drinkItems.remove(itemNumber);
//		}
//	}
	
	private List<MenuItem> foodItems;
	private List<MenuItem> drinkItems;

}
