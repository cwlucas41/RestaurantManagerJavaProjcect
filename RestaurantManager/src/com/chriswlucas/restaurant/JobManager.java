package com.chriswlucas.restaurant;

import java.util.List;
import java.util.UUID;

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
		type = 1;
		tickets.add(tick);
		int drinkSize, foodSize;
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
		type = 2;
	}
	
	void assignCollectingJob(){
		type = 3;
	}
	
	void assignBussingJob(){
		type = 4;
	}
	
	Worker server;
	UUID id;
	int type = 0;
	List<Ticket> tickets;
	List<Order> tempDrinks;
	List<Order> tempFood;
}
