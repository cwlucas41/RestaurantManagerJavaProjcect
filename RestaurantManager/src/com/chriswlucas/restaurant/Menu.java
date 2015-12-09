package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;

class Menu {
	
	public List<MenuItem> getFoodItems(){
		return foodItems;
	}
	
	public List<MenuItem> getDrinkItems(){
		return drinkItems;
	}
	public String toString(List<MenuItem> items){
		ListIterator<MenuItem> iterator = items.listIterator();
		String menuString=null;
		int i = 0;
		while(iterator.hasNext()){
			String temp = i + iterator.next().toString();
			menuString+=(temp +"\n");
			i++;
		}
		return menuString;
	}
	
	public String printMenu(boolean isFood){
		if(isFood){
			return toString(foodItems);
		}
		else{
			return toString(drinkItems);
		}
	}
	private List<MenuItem> foodItems;
	private List<MenuItem> drinkItems;
}
