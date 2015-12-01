package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;

class JobManager {
	
	JobManager (PartyManager partyManager){
		this.partyManager = partyManager;
	}
	
	void assignProducingJob(Ticket tick){
		type = 1;
		tempDrinks = tick.getDrinkOrders();
		tempFood = tick.getFoodOrders();
		
		if(!tempDrinks.isEmpty()){
			List<Object>temp = new ArrayList<Object>(tempDrinks);
			partyManager.restaurant.getBar(this.partyManager).assignJob(new Job (temp, type, this));
		}
		
		if(!tempFood.isEmpty()){
			List<Object>temp = new ArrayList<Object>(tempFood);
			partyManager.restaurant.getKitchen(this.partyManager).assignJob(new Job (temp, type, this));		}
	}

	void assignServingJob(List<Object> items){
		type = 2;
		partyManager.restaurant.getWaiter(this.partyManager).assignJob(new Job (items, type, this));	}
	
	void assignCollectingJob(){
		type = 3;
		List<Object>temp = new ArrayList<Object>(partyManager.restaurant.getTables(this.partyManager));
		partyManager.restaurant.getWaiter(this.partyManager).assignJob(new Job (temp, type, this));	}
	
	void assignBussingJob(List<Object> tables){
		type = 4;
		partyManager.restaurant.getBusser(this.partyManager).assignJob(new Job (tables, type, this));	}
	
	private int type = 0;
	private List<Order> tempDrinks;
	private List<Order> tempFood;
	private PartyManager partyManager;
}
