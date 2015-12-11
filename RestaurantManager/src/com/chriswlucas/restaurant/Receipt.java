package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;

class Receipt {
    /**
     * Handles the creation of the receipt
     * @param checkNumber
     * @param checkNames
     */
    Receipt(int checkNumber, List<Integer>checkNames, List<Ticket>tickets){
        this.checkNumber = checkNumber;
        this.checkNames = checkNames;
        this.tickets = tickets;  
    }
    /**
     * Iterates through all tickets and orders associated with a table
     * Finds orders with customer names associated with them that are crosslisted on the receipt
     * Sums the total of the receipt for all customers involved
     */
    void sumTotal(){
    	total = 0;
    	ListIterator<Ticket> allticks = tickets.listIterator();
    	ListIterator<Integer>names = checkNames.listIterator();
    	while(names.hasNext()){
        	currName = names.next();
        	while (allticks.hasNext()){
        		currTick = allticks.next();
        		
        		ListIterator<Order> alldrinks = currTick.getDrinkOrders().listIterator();
        		while(alldrinks.hasNext()){
        			Order next = alldrinks.next();
		    		if (currName == next.getCust()){
		    			total += next.getItem().getPrice();
		    		}
        		}
        		
        		ListIterator<Order> allfood = currTick.getFoodOrders().listIterator();
        		while(allfood.hasNext()){
        			Order next = allfood.next();
		    		if (currName == next.getCust()){
		    			total += next.getItem().getPrice();
		    		}
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
    
    private List<Ticket> tickets;
 //   private PartyManager partyManager;
    private List<Integer> checkNames;
    private Ticket currTick;
    public int total;
    private int checkNumber;
    private int currName;


}
