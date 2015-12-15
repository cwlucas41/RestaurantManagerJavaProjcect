package com.chriswlucas.restaurant;

import java.util.Scanner;
import java.util.Set;

/**
 * Test environment that allows for automatic initialization of restaurant, then enters event loop to switch between
 * Restaurant personal.
 * @author cwlucas41
 *
 */
public class SimulateRestaurant {
	
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * convenience method to prompt the user and get an integer
	 * @return integer from user
	 */
	private static int getIntegerFromUser(){
		System.out.print(">> ");
		int data = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		return data;
	}
	
	/**
	 * convenience method
	 * gets an integer from user and checks for integer's membership in a set
	 * keeps prompting the user until a valid integer is entered
	 * @param set
	 * @return integer that is member of set
	 */
	private static int getIntegerFromUserThatIsInSet(Set<Integer> set) {
		int choice = getIntegerFromUser();
		boolean isFinished = false;
		while (!isFinished) {
			if (set.contains(choice)) {
				isFinished = true;
			} else {
				System.out.println("Invalid choice, try again");
				choice = getIntegerFromUser();
			}
		}
		return choice;
	}
	
	/**
	 * method for handling initialization of restaurant
	 * more could be added here to change initial state
	 * @return setup Restaurant object
	 */
	private static Restaurant initializeRestaurant() {
		Restaurant r = new Restaurant();
		
		r.getManagerInterface().hireNewWaiter(2, "Nick");
		r.getManagerInterface().hireNewBusser(3, "Chris");
		r.getManagerInterface().hireNewWaiter(4, "Jonathan");
		
		r.getManagerInterface().addNewTable(0, 2);
		r.getManagerInterface().addNewTable(1, 2);
		r.getManagerInterface().addNewTable(2, 4);
		r.getManagerInterface().addNewTable(3, 4);
		r.getManagerInterface().addNewTable(4, 8);
		
		r.getManagerInterface().addNewBarSeat(5);
		r.getManagerInterface().addNewBarSeat(6);
		r.getManagerInterface().addNewBarSeat(7);
		r.getManagerInterface().addNewBarSeat(8);
		r.getManagerInterface().addNewBarSeat(9);
		
		r.getManagerInterface().addItemToMenu("Coke", 1.75, false);
		r.getManagerInterface().addItemToMenu("Water", 0, false);
		r.getManagerInterface().addItemToMenu("Beer", 2.50, false);
		
		r.getManagerInterface().addItemToMenu("Cheese Burger", 7.99, true);
		r.getManagerInterface().addItemToMenu("Tomato Soup", 3.99, true);
		r.getManagerInterface().addItemToMenu("Pizza", 9.99, true);
		
		return r;
	}
	
	/**
	 * main method
	 * sets up restaurant then enters event loop
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Simulation started");
		Restaurant r = initializeRestaurant();
		boolean isFinished = false;
		while (!isFinished) {
			System.out.println("\nChoose next type of person to control in the restaurant");
			System.out.println("-1) End simulation");
			System.out.println("0) Manager");
			System.out.println("1) Host");
			System.out.println("2) Worker");
			System.out.println("3) Customer");
			int choice = getIntegerFromUser();
			switch (choice) {
			case -1:
				isFinished = true;
				break;
			case 0:
				r.getManagerInterface().controlManager();
				break;
			case 1:
				r.getHostInterface().controlHost();
				break;
			case 2:
				r.getManagerInterface().displayAllWorkers();
				System.out.println("Choose a worker by entering their ID, -1 to exit");
				Integer employeeID = getIntegerFromUserThatIsInSet(r.getSetOfAllWorkersIDs());
				if (employeeID != null) {
					r.getWorkerInterface().controlWorker(employeeID);
				}
				break;
			case 3:
				r.getHostInterface().displayAllActiveParties();
				System.out.println("Choose a party by entering its ID, -1 to exit");
				Integer partyID = getIntegerFromUserThatIsInSet(r.getSetOfAllWorkersIDs());
				if (partyID != null) {
					r.getCustomerInterface().controlCustomer(partyID);
				}
				break;
			}
		}
		System.out.println("Simulation ended");
	}
}
