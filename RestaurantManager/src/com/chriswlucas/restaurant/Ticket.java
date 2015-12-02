package com.chriswlucas.restaurant;

import java.util.List;


class Ticket {
	/**
	 * Ticket that holds all the food items for this particular order.
	 * @param foodItems
	 * @param drinkItems
	 * @param ticketNum
	 */
	Ticket(List<Order> foodItems, List<Order>drinkItems, int ticketNum){
		this.food = foodItems;
		this.drink = drinkItems;
		this.tNum = ticketNum;
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
	private int tNum;
}
