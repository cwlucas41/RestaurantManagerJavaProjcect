package com.chriswlucas.restaurant;

import java.util.List;


class Ticket {
	/**
	 * Ticket that holds all the food items for this particular order.
	 * @param foodItems
	 * @param drinkItems
	 * @param ticketNum
	 * @param total
	 */
	Ticket(List<Order> foodItems, List<Order>drinkItems, int ticketNum, int total){
		this.food = foodItems;
		this.drink = drinkItems;
		this.tNum = ticketNum;
		this.total = total;
	}
	
	/**
	 * 
	 * @return the ticket number.
	 */
	int getTickNum(){
		return this.tNum;
	}
	
	/**
	 * 
	 * @return the total for this ticket.
	 */
	int getTicketTotal(){
		return this.total;
	}
	
	/**
	 * 
	 * @return the food items on this ticket.
	 */
	List<Order> getFoodOrders(){
		return food;
	}
	
	/**
	 * 
	 * @return the drink items on this ticket.
	 */
	List<Order> getDrinkOrders(){	
		return drink;
	}
	
	private List<Order>food;
	private List<Order>drink;
	private int tNum, total;
}
