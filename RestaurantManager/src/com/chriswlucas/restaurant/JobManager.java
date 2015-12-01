package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class JobManager {
	
	JobManager (PartyManager partyManager){
		this.partyManager = partyManager;
	}
	// SERVING
	// has to read tickets and save information about which items are done
	// then when all are done, assign a serving job
	
	// PAYMENT
	// when collecting is done, assign bussing, then free table
	
	void assignProducingJob(Ticket tick){
		type = 1;
		tempDrinks = tick.getDrinkOrders();
		tempFood = tick.getFoodOrders();
		
		if(!tempDrinks.isEmpty()){
			List<Object>temp = new ArrayList<Object>(tempDrinks);
			partyManager.restaurant.assignJobToBar(this.partyManager,new Job (temp, type, this));
		}
		
		if(!tempFood.isEmpty()){
			List<Object>temp = new ArrayList<Object>(tempFood);
			partyManager.restaurant.assignJobToKitchen(this.partyManager,new Job (temp, type, this));
		}
	}

	void assignServingJob(List<Object> items){
		type = 2;
		partyManager.restaurant.assignJobToWaiter(this.partyManager,new Job (items, type, this));
	}
	
	void assignCollectingJob(){
		type = 3;
		List<Object>temp = new ArrayList<Object>(partyManager.restaurant.getTables(this.partyManager));
		partyManager.restaurant.assignJobToWaiter(this.partyManager,new Job(temp, type, this));
	}
	
	void assignBussingJob(List<Object> tables){
		type = 4;
		partyManager.restaurant.assignJobToBusser(this.partyManager, new Job (tables, type, this));
	}
	
	int type = 0;
	List<Order> tempDrinks;
	List<Order> tempFood;
	PartyManager partyManager;
}
