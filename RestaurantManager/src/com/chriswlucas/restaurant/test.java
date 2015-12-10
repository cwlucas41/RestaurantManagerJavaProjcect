package com.chriswlucas.restaurant;


public class test {
	public static void main (String args[]){
		Restaurant r = new Restaurant();
		
		r.hireNewWaiter(2, "Nick");
		r.addTable(0, 4);

		r.addToWaitlist(4);
		r.addMenuItem("Coke", 1, false);
		r.addMenuItem("Water", 0, false);
		r.addMenuItem("Beer", 2, false);
		r.addMenuItem("ChezBurger", 1, true);
		r.addMenuItem("Tomato Soup", 2, true);
		r.addMenuItem("Pizza", 6, true);
		
		r.getPartyManager(0).takeOrder();
		
		r.getRestaurantInterface().getWorkerInterface().displayJobs(0);
		r.getRestaurantInterface().getWorkerInterface().displayJobs(1);
		r.getRestaurantInterface().getWorkerInterface().doJob(0, 0);
		r.getRestaurantInterface().getWorkerInterface().doJob(1, 0);
		
		r.getRestaurantInterface().getWorkerInterface().displayJobs(2);
		r.getRestaurantInterface().getWorkerInterface().doJob(2, 0);

		r.getRestaurantInterface().getCustomerInterface().displayCheckout(0);
	}
}