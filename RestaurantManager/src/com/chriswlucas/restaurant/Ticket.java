package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Ticket that holds all the food items for this particular order.
 * @author Nick Anderson
 *
 */
public class Ticket {
	private Hashtable<Integer, List<MenuItem>> foodByCustomerID;
	
	private Hashtable<Integer, List<MenuItem>> drinksByCustomerID;
	
	private int tNum;
	
	private double total;
	
	/**
	 * @param foodItems - list of the foodItems on this ticket.
	 * @param drinkItems - list of the drinkITems on this ticket.
	 * @param ticketNum - the ticketNumber for this ticket.
	 * @param total - the total price of this ticket.
	 */
	Ticket(Hashtable<Integer, List<MenuItem>> tempFoodByCustomerID, Hashtable<Integer, List<MenuItem>> tempDrinksByCustomerID, int ticketNum, double total){
		this.foodByCustomerID = tempFoodByCustomerID;
		this.drinksByCustomerID = tempDrinksByCustomerID;
		this.tNum = ticketNum;
		this.total = total;
	}
	
	/**
	 * Gets the list resulting from all lists stored as values in the supplied hashtable being appended together.
	 * @param hashtable
	 * @return
	 */
	private List<MenuItem> getAllValuesAsList(Hashtable<Integer, List<MenuItem>> hashtable) {
		List<MenuItem> list = new ArrayList<MenuItem>();
		for (int key : hashtable.keySet()) {
			list.addAll(hashtable.get(key));
		}
		return list;
	}
	
	/**
	 * Returns a list of drinkItems.
	 * @return drink - drinkItems on this ticket.
	 */
	List<MenuItem> getDrinkOrders(){	
		return this.getAllValuesAsList(drinksByCustomerID);
	}
	/**
	 * Returns a list of foodItems.
	 * @return food - foodItems on this ticket.
	 */
	List<MenuItem> getFoodOrders(){
		return this.getAllValuesAsList(foodByCustomerID);
	}
	/**
	 * Returns the price of this ticket.
	 * @return total - price for this ticket.
	 */
	double getTicketTotal(){
		return total;
	}
	/**
	 * Returns a ticketNumber.
	 * @return tNum - the ticket number.
	 */
	int getTickNum(){
		return tNum;
	}
}
