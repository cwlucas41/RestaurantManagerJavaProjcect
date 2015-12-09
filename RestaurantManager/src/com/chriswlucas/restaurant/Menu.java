package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;

class Menu {
	
	List<MenuItem> getMenuItems(){
		return items;
	}
	
	public String toString(){
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
	List<MenuItem> items;
}
