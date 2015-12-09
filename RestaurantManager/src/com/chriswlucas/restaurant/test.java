package com.chriswlucas.restaurant;


public class test {
	public static void main (String args[]){
		Restaurant r = new Restaurant();
//		int temp = r.getRestaurantInterface().getCustomerInterface().getIntegerFromUser();
//		System.out.println(temp);
//		temp = r.getRestaurantInterface().getCustomerInterface().getIntegerFromUser();
//		System.out.println(temp);
		
		r.hireNewWaiter(2, "Nick");
		r.addTable(0, 4);
		r.addToWaitlist(4);
		r.addMenuItem("ChezBurger", 1, true);
		r.addMenuItem("Tomato Soup", 2, true);
		r.getPartyManager(0).takeOrder();
		
		r.getRestaurantInterface().getWorkerInterface().displayJobs(0);
		r.getRestaurantInterface().getWorkerInterface().doJob(0, 0);
		r.getRestaurantInterface().getWorkerInterface().displayJobs(2);
		r.getRestaurantInterface().getWorkerInterface().doJob(2, 0);

		r.getRestaurantInterface().getCustomerInterface().displayCheckout(0);
	}
}