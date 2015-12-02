package com.chriswlucas.restaurant;

class Order{
	/**
	 * Structure to combine the item and which customer ordered it.
	 * @param item
	 * @param customer
	 */
	Order(MenuItem item, int customer){
		this.it = item;
		this.cust=customer;
	}
	
	/**
	 * 
	 * @return the menu item.
	 */
	MenuItem getItem(){
		return it;
	}
	
	/**
	 * 
	 * @return the customer.
	 */
	int getCust(){
		return cust;
	}
	
	/**
	 * 
	 * @return the item name.
	 */
	public String toString(){
		return it.getItemName();
	}
	
	private MenuItem it;
	private int cust;
}