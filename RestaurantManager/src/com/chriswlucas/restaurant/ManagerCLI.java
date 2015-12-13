package com.chriswlucas.restaurant;

import java.util.Set;

class ManagerCLI extends UserCLI implements ManagerUI {
	
	public ManagerCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	public void controlManager() {
		boolean isFinished = false;
		while (!isFinished){
			printLine("\nChoose from the following choices:");
			printLine("-1) exit");
			printLine(" 0) View list of workers");
			printLine(" 1) Hire waiter");
			printLine(" 2) Hire busser");
			printLine(" 3) Remove waiter");
			printLine(" 4) Remove busser");
			printLine(" 5) View list of tables");
			printLine(" 6) Add new table");
			printLine(" 7) Add new bar seat");
			printLine(" 8) Remove table or bar seat");
			printLine(" 9) View menu");
			printLine(" 10) Add item to menu");
//			printLine(" 11) Remove item from menu");
			int choice = getIntegerFromUser();
			switch (choice) {
			case -1: isFinished = true; break;
			case 0: 
				this.displayAllWorkers();
				break;
			case 1: 
				this.hireNewWaiter(getAndCheckIntegerIsInSet("Enter unused employeeID", this.getRestaurant().getSetOfAllEmployeeIDs(), false), getNameFromUser()); 
				break;
			case 2:
				this.hireNewBusser(getAndCheckIntegerIsInSet("Enter unused employeeID", this.getRestaurant().getSetOfAllEmployeeIDs(), false), getNameFromUser()); 
				break;
			case 3: 
				this.removeWaiterByID(getAndCheckIntegerIsInSet("Enter employeeID", this.getRestaurant().getSetOfAllEmployeeIDs(), true)); 
				break;
			case 4: 
				this.removeBusserByID(getAndCheckIntegerIsInSet("Enter employeeID", this.getRestaurant().getSetOfAllEmployeeIDs(), true)); 
				break;
			case 5:
				displayAllTables();
				break;
			case 6: 
				this.addNewTable(getAndCheckIntegerIsInSet("Enter table number", this.getRestaurant().getSetOfTableNumbers(), false), getTableSize()); 
				break;
			case 7: 
				this.addNewBarSeat(getAndCheckIntegerIsInSet("Enter table number", this.getRestaurant().getSetOfTableNumbers(), false)); 
				break;
			case 8: 
				this.removeTableByID(getAndCheckIntegerIsInSet("Enter table number", this.getRestaurant().getSetOfTableNumbers(), true)); 
				break;
			case 9:
				printLine(this.getRestaurant().getMenu().toString());;
				break;
			case 10: 
				this.addItemToMenu(getNameFromUser(), getPriceFromUser(), getIsFoodFromUser()); 
				break;
//			case 11: 
//				this.removeItemFromMenu(itemNumber, isFood); 
//				break;
			default: 
				printLine("Invalid choice, try again"); 
				break;
			}
		}
	}

	@Override
	public void hireNewWaiter(int employeeID, String name) {
		displayAllProducersOrWaitersOrBussers(1);
		boolean isHired = this.getRestaurant().addWaiter(employeeID, name);
		if (!isHired) {
			System.out.println("Could not hire waiter with ID " + employeeID);
		}
	}

	@Override
	public void hireNewBusser(int employeeID, String name) {
		displayAllProducersOrWaitersOrBussers(2);
		boolean isHired = this.getRestaurant().addBusser(employeeID, name);
		if (!isHired) {
			printLine("Could not hire busser with ID " + employeeID);
		}
	}

	@Override
	public Worker removeWaiterByID(int employeeID) {
		Worker worker = this.getRestaurant().removeWaiter(employeeID);
		if (worker == null){
			printLine("Waiter with ID " + employeeID + " could not be removed");
		}
		return worker;
	}
	
	public void displayAllWorkers() {
		displayAllProducersOrWaitersOrBussers(0);
		displayAllProducersOrWaitersOrBussers(1);
		displayAllProducersOrWaitersOrBussers(2);
	}
	
	private void displayAllProducersOrWaitersOrBussers(int choice) {
		Set<Integer> idSet;
		if (choice == 0) {
			idSet = this.getRestaurant().getSetOFAllProducerIDs();
			printLine("List of Producers:");
		} else if (choice == 1) {
			idSet = this.getRestaurant().getSetOfAllWaiterIDs();
			printLine("List of Waiters:");
		} else {
			idSet = this.getRestaurant().getSetOfAllBusserIDs();
			printLine("List of Bussers:");
		}
		
		printLine("\tID\t# of Jobs\tName");
		for (int id : idSet) {
			printLine("\t" + id + "\t" + this.getRestaurant().getWorker(id).getNumberOfJobs() + "\t\t" + this.getRestaurant().getWorker(id).getName());
		}
	}
	
	private void displayAllTables() {
		Set<Integer> keys = this.getRestaurant().getSetOfTableNumbers();
		printLine("List of Tables:");
		printLine("\tNumber\tSize\tOccupied");
		for (int key : keys) {
			printLine("\t" + key + "\t" + this.getRestaurant().getTable(key).getsize() + "\t" + this.getRestaurant().getTable(key).isOccupied());
		}
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

//	@Override
//	public MenuItem removeItemFromMenu(int itemNumber, boolean isFood) {
//		MenuItem item = this.getRestaurant().removeMenuItem(itemNumber, isFood);
//		if (item == null){
//			System.out.println("Item with number " + itemNumber + " could not be removed");
//		}
//		return item;
//	}
	
	private String getNameFromUser() {
		printLine("Enter name");
		return getLineFromUser();
	}
	
	private int getTableSize() {
		printLine("Enter size of table");
		int choice = getIntegerFromUser();
		boolean isFinished = false;
		while (!isFinished) {
			if (choice > 0) {
				isFinished = true;
			} else {
				printLine("Invalid choice, try again");
				choice = getIntegerFromUser();
			}
		}
		return choice;
	}
	
	private double getPriceFromUser() {
		printLine("Enter price of item");
		double choice = getDoubleFromUser();
		boolean isFinished = false;
		while (!isFinished) {
			if (choice >= 0) {
				isFinished = true;
			} else {
				printLine("Invalid choice, try again");
				choice = getIntegerFromUser();
			}
		}
		return choice;
	}
	
	private boolean getIsFoodFromUser() {
		printLine("Is a food (0) or drink (1) item?");
		int isFoodInt = getIntegerFromUser();
		boolean isFood = true;
		boolean isFinished = false;
		while (!isFinished) {
			if (isFoodInt == 0) {
				isFinished = true;
			} else if (isFoodInt == 1) {
				isFinished = true;
				isFood = false;
			} else {
				printLine("Invalid choice, try again");
				isFoodInt = getIntegerFromUser();
			}
		}
		return isFood;
	}
}
