package com.chriswlucas.restaurant;


public class test {
	public static void main (String args[]){
		Restaurant r = new Restaurant();
		
		r.hireNewWaiter(2, "Nick");
		r.addTable(0, 4);
		r.getRestaurantInterface().getHostInterface().addNewPartyToWaitlist(4);
		r.addMenuItem("ChezBurger", 1, true);
		r.getPartyManager(0).takeOrder();
		
		r.getRestaurantInterface().getWorkerInterface().displayJobs(0);
		r.getRestaurantInterface().getWorkerInterface().doJob(0, 0);
		r.getRestaurantInterface().getWorkerInterface().displayJobs(2);
		r.getRestaurantInterface().getWorkerInterface().doJob(2, 0);
		
		r.getPartyManager(0).pay();
		r.getRestaurantInterface().getCustomerInterface().displayCheckout(0);
	}
}