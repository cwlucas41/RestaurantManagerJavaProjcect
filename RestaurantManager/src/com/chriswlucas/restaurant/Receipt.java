package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;

class Receipt {
    /**
     * Handles the creation of the receipt
     * @param checkNumber
     * @param checkNames
     */
    Receipt(int checkNumber, List<Integer>checkNames){
        this.checkNumber = checkNumber;
        this.checkNames = checkNames;
        this.sumTotal();     
    }
    /**
     * Iterates through all tickets and orders associated with a table
     * Finds orders with customer names associated with them that are crosslisted on the receipt
     * Sums the total of the receipt for all customers involved
     */
    void sumTotal(){
    	total = 0;
    	ListIterator<Ticket> allticks = this.partyManager.getTickets().listIterator();
        for (int i = 0; i<this.checkNames.size(); i++){
        	currName = checkNames.get(i);
        	while (allticks.hasNext()){
        		currTick = allticks.next();
        		ListIterator<Order> allfood = currTick.getFoodOrders().listIterator();
        		ListIterator<Order> alldrinks = currTick.getDrinkOrders().listIterator();
        		
        		if (currName == alldrinks.next().getCust()){
        			total += alldrinks.next().getItem().getPrice();
        		}
        		if (currName == allfood.next().getCust()){
        			total += allfood.next().getItem().getPrice();
        		}
        	}
        }
    }
    /**
     * returns the total due for a receipt
     * @return
     */
    int getTotal(){
    	return total;
    }
    /**
     * returns the check number
     * @return
     */
    int getCheckNumber(){
    	return checkNumber;
    }
    
    private PartyManager partyManager;
    private List<Integer> checkNames;
    private Ticket currTick;
    public int total;
    private int checkNumber;
    private int currName;
    private CustomerUI customerUI;
	private Restaurant restaurant;
    


}
