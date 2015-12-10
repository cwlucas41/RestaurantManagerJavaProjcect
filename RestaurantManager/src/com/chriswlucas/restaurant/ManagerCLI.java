package com.chriswlucas.restaurant;

class ManagerCLI extends UserCLI implements ManagerUI {
	
	public ManagerCLI(Restaurant restaurant){
		super(restaurant);
	}

	@Override
	public void hireNewWaiter(int employeeID, String name) {
		boolean isHired = this.getRestaurant().addWaiter(employeeID, name);
		if (!isHired) {
			System.out.println("Could not hire waiter with ID " + employeeID);
		}
	}

	@Override
	public void hireNewBusser(int employeeID, String name) {
		boolean isHired = this.getRestaurant().addBusser(employeeID, name);
		if (!isHired) {
			System.out.println("Could not hire busser with ID " + employeeID);
		}
	}

	@Override
	public Worker removeWaiterByID(int employeeID) {
		Worker worker = this.getRestaurant().removeWaiter(employeeID);
		if (worker == null){
			System.out.println("Waiter with ID " + employeeID + " could not be removed");
		}
		return worker;
	}

	@Override
	public Worker removeBusserByID(int employeeID) {
		Worker worker = this.getRestaurant().removeBusser(employeeID);
		if (worker == null){
			System.out.println("Busser with ID " + employeeID + " could not be removed");
		}
		return worker;
	}

	@Override
	public void addNewTable(int tableNumber, int numberOfSeats) {
		boolean isAdded = this.getRestaurant().addTable(tableNumber, numberOfSeats);
		if (!isAdded) {
			System.out.println("Could not add table with number " + tableNumber);
		}
	}

	@Override
	public void addNewBarSeat(int tableNumber) {
		boolean isAdded = this.getRestaurant().addBarSeat(tableNumber);
		if (!isAdded) {
			System.out.println("Could not add bar seat with number " + tableNumber);
		}
	}

	@Override
	public Table removeTableByID(int tableNumber) {
		Table table = this.getRestaurant().removeTable(tableNumber);
		if (table == null){
			System.out.println("Table with number " + tableNumber + " could not be removed");
		}
		return table;
	}

	@Override
	public void addItemToMenu(String name, double price, boolean isFood) {
		this.getRestaurant().addMenuItem(name, price, isFood);;
	}

	@Override
	public MenuItem removeItemFromMenu(int itemNumber, boolean isFood) {
		MenuItem item = this.getRestaurant().removeMenuItem(itemNumber, isFood);
		if (item == null){
			System.out.println("Item with number " + itemNumber + " could not be removed");
		}
		return item;
	}

}
