package com.chriswlucas.restaurant;

import java.util.List;

class JobManager {
	
	JobManager (Worker waiter){
		this.server = waiter;
	}
	// SERVING
	// has to read tickets and save information about which items are done
	// then when all are done, assign a serving job
	
	// PAYMENT
	// when collecting is done, assign bussing, then free table
	
	void assignProducingJob(Ticket tick){
		tempDrinks = tick.getDrinkOrders();
		tempFood = tick.getFoodOrders();
		
		if(!tempDrinks.isEmpty()){
			drinkSize = tempDrinks.size();
			Worker bar = Restaurant.getBar();
			//find cook, bar, create temp employee and add the work to their queue.
		}
		if(!tempFood.isEmpty()){
			foodSize = tempFood.size();
			Worker cook = Restaurant.getKitchen();
		}
	}
	
	void assignServingJob(){
		
	}
	
	void assignCollectingJob(){
		
	}
	
	void assignBussingJob(){
		
	}
	
	Worker server;
	int UUID;
	int drinkSize, foodSize;
	List<Order> tempDrinks;
	List<Order> tempFood;
}
