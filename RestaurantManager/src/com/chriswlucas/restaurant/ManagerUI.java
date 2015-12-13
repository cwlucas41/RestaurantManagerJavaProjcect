package com.chriswlucas.restaurant;

interface ManagerUI {
	public void controlManager();
	
	public void hireNewWaiter(int employeeID, String name);
	public void hireNewBusser(int employeeID, String name);
	public Worker removeWaiterByID(int employeeID);
	public Worker removeBusserByID(int employeeID);
	
	public void addNewTable(int tableNumber,int numberOfSeats);
	public void addNewBarSeat(int tableNumber);
	public Table removeTableByID(int tableNumber);
	
	public void addItemToMenu(String name, double price, boolean isFood);
//	public MenuItem removeItemFromMenu(int itemNumber, boolean isFood);
}
