package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;

class JobManager {
	
	/**
	 * Converts the ticket to a job and then moves it around.
	 * @param partyManager reference back to its containing class.
	 */
	JobManager (PartyManager partyManager){
		this.partyManager = partyManager;
	}
	
	/**
	 * Creates a producing job for the bar or kitchen.
	 * @param tick the ticket for the current order.
	 */
	void assignProducingJob(Ticket tick){
		type = 1;
		tempDrinks = tick.getDrinkOrders();
		tempFood = tick.getFoodOrders();
		
		if(!tempDrinks.isEmpty()){
			List<Object>temp = new ArrayList<Object>(tempDrinks);
			partyManager.restaurant.getBar().assignJob(new Job (temp, type, this));
		}
		
		if(!tempFood.isEmpty()){
			List<Object>temp = new ArrayList<Object>(tempFood);
			partyManager.restaurant.getKitchen().assignJob(new Job (temp, type, this));		}
	}

	/**
	 * Places the items on the waiters list of jobs to be served.
	 * @param items list of items that have been produced by the bar or kitchen.
	 */
	void assignServingJob(List<Object> items){
		type = 2;
		partyManager.restaurant.getWaiter(this.partyManager).assignJob(new Job (items, type, this));
	}
	
	/**
	 * When customer wants to pay a collecting job is created.
	 * The tables that need to be collected are added to the waiters queue of jobs.
	 */
	void assignCollectingJob(){
		type = 3;
		List<Object>temp = new ArrayList<Object>(partyManager.restaurant.convertTableNumbersToTables(this.partyManager.getTableNumbers()));
		partyManager.restaurant.getWaiter(this.partyManager).assignJob(new Job (temp, type, this));	
	}
	
	/**
	 * When the patrons have checked out the tables are added to the busser's queue
	 * of jobs to be cleaned. (Can be added to a waiter if the busser is busy.)
	 * @param tables list of tables that need to be cleaned.
	 */
	void assignBussingJob(List<Object> tables){
		type = 4;
		partyManager.restaurant.getBusser().assignJob(new Job (tables, type, this));	
	}
	
	public PartyManager getPartyManager() {
		return partyManager;
	}
	
	private int type = 0;
	private List<Order> tempDrinks;
	private List<Order> tempFood;
	private PartyManager partyManager;

}
