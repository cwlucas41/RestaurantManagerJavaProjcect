package com.chriswlucas.restaurant;

/**
 * Structure to combine the item and which customer ordered it.
 * @author Nick Anderson
 *
 */
class Order{
	
	/**
	 * @param item - a MenuItem
	 * @param customer - a customer.
	 */
	public Order(MenuItem item, int customer){
		this.it = item;
		this.cust=customer;
	}
	/**
	 * This copies the order so we can clone it if needed.
	 * @param order - an order.
	 */
	public Order(Order order) {
		this.it = order.getItem();
		this.cust = order.getCust();
	}
	
	/**
	 * Returns a MenuItem
	 * @return it - the MenuItem.
	 */
	MenuItem getItem(){
		return it;
	}
	
	/**
	 * Returns a customer.
	 * @return cust - the customer.
	 */
	int getCust(){
		return cust;
	}
	
	/**
	 * Returns a string with the item name and price.
	 * @return the item name.
	 */
	public String toString(){
		return it.getItemName();
	}
	
	private MenuItem it;
	private int cust;
}