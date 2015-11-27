package com.chriswlucas.restaurant;

import java.util.List;

class Ticket {
	
	Ticket(List<Order> foodItems, List<Order>drinkItems, int ticketNum, int UUID){
		this.food = foodItems;
		this.drink = drinkItems;
		this.tNum = ticketNum;
		this.tUUID = UUID;
	}
	
	List<Order> getFoodOrders(){
		
		return food;
	}
	
	List<Order> getDrinkOrders(){
		
		return drink;
	}
	
	private List<Order>food;
	private List<Order>drink;
	private int tNum, tUUID;
}
