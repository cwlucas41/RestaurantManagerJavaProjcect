package com.chriswlucas.restaurant;

/**
 * Interface defining what a manager can do
 * @author cwlucas41
 *
 */
public interface ManagerUI {
	/**
	 * enters manager event loop
	 */
	public void controlManager();
	
	/**
	 * displays all the workers in the restaurant
	 */
	public void displayAllWorkers();
	
	/**
	 * Adds a new waiter to the restaurant
	 * @param employeeID
	 * @param name
	 */
	public void hireNewWaiter(int employeeID, String name);
	
	/**
	 * Adds a new busser to the restaurant
	 * @param employeeID
	 * @param name
	 */
	public void hireNewBusser(int employeeID, String name);
	
	/**
	 * Removes the specified waiter from the restaurant
	 * @param employeeID
	 * @return removed waiter
	 */
	public Worker removeWaiterByID(int employeeID);
	
	/**
	 * Removes the specified busser from the restaurant
	 * @param employeeID
	 * @return removed busser
	 */
	public Worker removeBusserByID(int employeeID);
	
	/**
	 * Adds a new table to the restaurant
	 * @param tableNumber
	 * @param numberOfSeats
	 */
	public void addNewTable(int tableNumber,int numberOfSeats);
	
	/**
	 * Adds a new bar seat to the restaurant
	 * @param tableNumber
	 */
	public void addNewBarSeat(int tableNumber);
	
	/**
	 * Removes the specified table from the restaurant
	 * @param tableNumber
	 * @return
	 */
	public Table removeTableByID(int tableNumber);
	
	/**
	 * Adds a menu item to the menu with the specified information
	 * @param name
	 * @param price
	 * @param isFood
	 */
	public void addItemToMenu(String name, double price, boolean isFood);
	
//	/**
//	 * Removes the specified menu item from the menu
//	 * @param itemNumber
//	 * @param isFood
//	 * @return removed item
//	 */
//	public MenuItem removeItemFromMenu(int itemNumber, boolean isFood);
}
