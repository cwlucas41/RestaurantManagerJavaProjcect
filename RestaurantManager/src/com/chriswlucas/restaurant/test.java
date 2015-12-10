package com.chriswlucas.restaurant;


public class test {
	public static void main (String args[]){
		Restaurant r = new Restaurant();
		
		r.getManagerInterface().hireNewWaiter(2, "Nick");
		r.getManagerInterface().addNewTable(0, 4);

		r.getHostInterface().addNewPartyToWaitlist(1, false);
		
		r.getManagerInterface().addItemToMenu("Coke", 1, false);
		r.getManagerInterface().addItemToMenu("Water", 0, false);
		r.getManagerInterface().addItemToMenu("Beer", 2, false);
		r.getManagerInterface().addItemToMenu("ChezBurger", 1, true);
		r.getManagerInterface().addItemToMenu("Tomato Soup", 2, true);
		r.getManagerInterface().addItemToMenu("Pizza", 6, true);
		
		r.getCustomerInterface().initiateOrdering(0);
		
		r.getWorkerInterface().displayJobs(0);
		r.getWorkerInterface().displayJobs(1);
		r.getWorkerInterface().doJob(0, 0);
		r.getWorkerInterface().doJob(1, 0);
		
		r.getWorkerInterface().displayJobs(2);
		r.getWorkerInterface().doJob(2, 0);
		r.getWorkerInterface().displayJobs(2);
		r.getWorkerInterface().doJob(2, 0);

		r.getCustomerInterface().displayCheckout(0);
		
		r.getWorkerInterface().displayJobs(2);
		r.getWorkerInterface().doJob(2, 0);
		
		r.getWorkerInterface().displayJobs(2);
		r.getWorkerInterface().doJob(2, 0);
	}
}