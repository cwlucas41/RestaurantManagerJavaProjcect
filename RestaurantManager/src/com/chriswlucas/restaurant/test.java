package com.chriswlucas.restaurant;

public class test {
	public static void main (String args[]){
		Restaurant r = new Restaurant();
		
		r.getManagerInterface().hireNewWaiter(2, "Nick");
		r.getManagerInterface().addNewTable(0, 4);
		r.getManagerInterface().addNewBarSeat(1);
		r.getManagerInterface().addNewTable(2, 8);
		r.getManagerInterface().addNewBarSeat(3);
				
		r.getManagerInterface().addItemToMenu("Coke", 1, false);
		r.getManagerInterface().addItemToMenu("Water", 0, false);
		r.getManagerInterface().addItemToMenu("Beer", 2, false);
		r.getManagerInterface().addItemToMenu("ChezBurger", 1, true);
		r.getManagerInterface().addItemToMenu("Tomato Soup", 2, true);
		r.getManagerInterface().addItemToMenu("Pizza", 6, true);

		r.getHostInterface().controlHost();
		
		r.getCustomerInterface().controlCustomer(0);
		
		r.getWorkerInterface().controlWorker(0);
		r.getWorkerInterface().controlWorker(1);
		r.getWorkerInterface().controlWorker(2);

		r.getCustomerInterface().controlCustomer(0);
		
		r.getWorkerInterface().controlWorker(2);
	}
}