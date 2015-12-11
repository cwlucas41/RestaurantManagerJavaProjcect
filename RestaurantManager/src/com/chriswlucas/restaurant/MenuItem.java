package com.chriswlucas.restaurant;

class MenuItem {

	MenuItem(String name, double price, boolean isFood){
		this.Name = name;
		this.price = price;
		this.isFood = isFood;
	}
	
	boolean isFood(){
		return isFood;
	}
	
	String getItemName(){
		return Name;
	}
	
	double getPrice(){
		return price;
	}
	
	public String toString(){
		return "\t$" + getPrice() + "\t" + getItemName();
	}
	private double price;
	private String Name;
	private boolean isFood = false; //set to true if a food item, leave false if drink.
}
