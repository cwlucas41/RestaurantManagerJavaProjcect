package com.chriswlucas.restaurant;

import java.util.List;

/**
 * Ticket that holds all the food items for this particular order.
 * @author Nick Anderson
 *
 */
class Ticket {
	/**
	 * @param foodItems - list of the foodItems on this ticket.
	 * @param drinkItems - list of the drinkITems on this ticket.
	 * @param ticketNum - the ticketNumber for this ticket.
	 * @param total - the total price of this ticket.
	 */
	Ticket(List<Order> foodItems, List<Order>drinkItems, int ticketNum, double total){
		this.food = foodItems;
		this.drink = drinkItems;
		this.tNum = ticketNum;
		this.total = total;
	}
	
	/**
	 * Returns a ticketNumber.
	 * @return tNum - the ticket number.
	 */
	int getTickNum(){
		return tNum;
	}
	
	/**
	 * Returns the price of this ticket.
	 * @return total - price for this ticket.
	 */
	double getTicketTotal(){
		return total;
	}
	
	/**
	 * Returns a list of foodItems.
	 * @return food - foodItems on this ticket.
	 */
	List<Order> getFoodOrders(){
		return food;
	}
	
	/**
	 * Returns a list of drinkItems.
	 * @return drink - drinkItems on this ticket.
	 */
	List<Order> getDrinkOrders(){	
		return drink;
	}
	
	private List<Order>food;
	private List<Order>drink;
	private int tNum;
	private double total;
}
