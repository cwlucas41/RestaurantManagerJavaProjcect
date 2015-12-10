package com.chriswlucas.restaurant;

interface ManagerUI {
	void hireNewWaiter(int employeeID, String name);
	void hireNewBusser(int employeeID, String name);
	Worker removeWaiterByID(int employeeID);
	Worker removeBusserByID(int employeeID);
	
	void addNewTable(int tableNumber,int numberOfSeats);
	void addNewBarSeat(int tableNumber);
	Table removeTableByID(int tableNumber);
	
	void addItemToMenu(String name, double price, boolean isFood);
	MenuItem removeItemFromMenu(int itemNumber, boolean isFood);
}
