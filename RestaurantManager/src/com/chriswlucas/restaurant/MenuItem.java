package com.chriswlucas.restaurant;

class MenuItem {

	MenuItem(){
		//set isFood here when you create the item.
	}
	
	Boolean isFood(){
		return isFood;
	}
	
	String getItemName(){
		return Name;
	}
	public String Name;
	public boolean isFood = false; //set to true if a food item, leave false if drink.
}
