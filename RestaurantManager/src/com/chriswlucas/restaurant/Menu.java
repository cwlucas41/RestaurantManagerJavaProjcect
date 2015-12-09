package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class Menu {
	
	public Menu() {
		this.foodItems = new ArrayList<MenuItem>();
		this.drinkItems = new ArrayList<MenuItem>();
	}
	
	public List<MenuItem> getFoodItems(){
		return foodItems;
	}
	
	public List<MenuItem> getDrinkItems(){
		return drinkItems;
	}
	
	public String toString(List<MenuItem> items){
		ListIterator<MenuItem> iterator = items.listIterator();
		String menuString="Item\t\tPrice\n";
		int i = 0;
		while(iterator.hasNext()){
			String temp = i +") " + iterator.next().toString();
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
	
	public void addMenuItem(String name, int price, boolean isFood){
		MenuItem item = new MenuItem(name, price, isFood);
		if (isFood) {
			foodItems.add(item);
		} else {
			drinkItems.add(item);
		}
	}
	
	public MenuItem removeMenuItem(int itemNumber, boolean isFood) {
		if(isFood){
			return foodItems.remove(itemNumber);
		} else {
			return drinkItems.remove(itemNumber);
		}
	}
	
	private List<MenuItem> foodItems;
	private List<MenuItem> drinkItems;

}
