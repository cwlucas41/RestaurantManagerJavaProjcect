package com.chriswlucas.restaurant;

import java.util.List;
import java.util.UUID;

class Ticket {
	
	Ticket(List<Order> foodItems, List<Order>drinkItems, int ticketNum){
		this.food = foodItems;
		this.drink = drinkItems;
		this.tNum = ticketNum;
	}
	
	int getTickNum(){
		return this.tNum;
	}
	
	List<Order> getFoodOrders(){
		return food;
	}
	
	List<Order> getDrinkOrders(){	
		return drink;
	}
	
	int getFoodSize(){
		return food.size();
	}
	
	int getDrinkSize(){
		return drink.size();
	}

	private List<Order>food;
	private List<Order>drink;
	private int tNum;
}
