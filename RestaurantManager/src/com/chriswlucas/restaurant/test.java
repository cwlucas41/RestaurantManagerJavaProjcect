package com.chriswlucas.restaurant;


public class test {
	public static void main (String args[]){
		Restaurant r = new Restaurant();
		
		r.getRestaurantInterface().getManagerInterface().hireNewWaiter(2, "Nick");
		r.getRestaurantInterface().getManagerInterface().addNewTable(0, 4);

		r.getRestaurantInterface().getHostInterface().addNewPartyToWaitlist(1, false);
		
		r.getRestaurantInterface().getManagerInterface().addItemToMenu("Coke", 1, false);
		r.getRestaurantInterface().getManagerInterface().addItemToMenu("Water", 0, false);
		r.getRestaurantInterface().getManagerInterface().addItemToMenu("Beer", 2, false);
		r.getRestaurantInterface().getManagerInterface().addItemToMenu("ChezBurger", 1, true);
		r.getRestaurantInterface().getManagerInterface().addItemToMenu("Tomato Soup", 2, true);
		r.getRestaurantInterface().getManagerInterface().addItemToMenu("Pizza", 6, true);
		
		r.getRestaurantInterface().getCustomerInterface().initiateOrdering(0);
		
		r.getRestaurantInterface().getWorkerInterface().displayJobs(0);
		r.getRestaurantInterface().getWorkerInterface().displayJobs(1);
		r.getRestaurantInterface().getWorkerInterface().doJob(0, 0);
		r.getRestaurantInterface().getWorkerInterface().doJob(1, 0);
		
		r.getRestaurantInterface().getWorkerInterface().displayJobs(2);
		r.getRestaurantInterface().getWorkerInterface().doJob(2, 0);
		r.getRestaurantInterface().getWorkerInterface().displayJobs(2);
		r.getRestaurantInterface().getWorkerInterface().doJob(2, 0);

		r.getRestaurantInterface().getCustomerInterface().displayCheckout(0);
		
		r.getRestaurantInterface().getWorkerInterface().displayJobs(2);
		r.getRestaurantInterface().getWorkerInterface().doJob(2, 0);
		
		r.getRestaurantInterface().getWorkerInterface().displayJobs(2);
		r.getRestaurantInterface().getWorkerInterface().doJob(2, 0);
	}
}