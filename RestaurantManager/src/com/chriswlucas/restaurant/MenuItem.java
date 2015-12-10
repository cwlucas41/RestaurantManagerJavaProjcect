package com.chriswlucas.restaurant;

class MenuItem {

	MenuItem(String name, int price, boolean isFood){
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
	
	int getPrice(){
		return price;
	}
	
	public String toString(){
		return getItemName() + "\t$" + getPrice();
	}
	public int price;
	public String Name;
	public boolean isFood = false; //set to true if a food item, leave false if drink.
}
