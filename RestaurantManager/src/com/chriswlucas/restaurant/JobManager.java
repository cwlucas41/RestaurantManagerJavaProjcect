package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles moving the jobs around the restaurant for a specific party.
 * @author Nick Anderson
 *
 */
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
		List<MenuItem> tempDrinks = tick.getDrinkOrders();
		List<MenuItem> tempFood = tick.getFoodOrders();
		
		if(!tempDrinks.isEmpty()){
			List<Object>temp = new ArrayList<Object>(tempDrinks);
			partyManager.getRestaurant().getBar().assignJob(new Job (temp, type, this));
		}
		
		if(!tempFood.isEmpty()){
			List<Object>temp1 = new ArrayList<Object>(tempFood);
			partyManager.getRestaurant().getKitchen().assignJob(new Job (temp1, type, this));		
		}
	}

	/**
	 * Places the items on the waiters list of jobs to be served.
	 * @param items list of items that have been produced by the bar or kitchen.
	 */
	void assignServingJob(Job job){
		type = 2;
		job.setType(type);
		partyManager.getRestaurant().getWaiter(this.partyManager).assignJob(job);
	}
	
	/**
	 * When customer wants to pay a collecting job is created.
	 * The tables that need to be collected are added to the waiters queue of jobs.
	 */
	void assignCollectingJob(){
		type = 3;
		List<Object>temp = new ArrayList<Object>(this.partyManager.getTableNumbers());
		partyManager.getRestaurant().getWaiter(this.partyManager).assignJob(new Job (temp, type, this));
	}
	
	/**
	 * When the patrons have checked out the tables are added to the busser's queue
	 * of jobs to be cleaned. (Can be added to a waiter if the busser is busy.)
	 * @param tables list of tables that need to be cleaned.
	 */
	void assignBussingJob(Job job){
		type = 4;
		job.setType(type);
		partyManager.getRestaurant().getBusser(this.partyManager.isAtBar()).assignJob(job);
	}
	
	/**
	 * Gets the containing party manager for the job manager
	 * @return partyManager
	 */
	public PartyManager getPartyManager() {
		return partyManager;
	}
	
	private int type = 0;
	private PartyManager partyManager;

}
