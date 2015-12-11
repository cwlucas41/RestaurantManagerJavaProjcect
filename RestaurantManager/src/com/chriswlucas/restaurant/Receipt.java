package com.chriswlucas.restaurant;

import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

class Receipt {
    /**
     * Handles the creation of the receipt
     * @param checkNumber
     * @param checkNames
     */
    Receipt(int checkNumber, Hashtable<Integer, List<MenuItem>> itemsByCustomerID, List<String> custNames){
        this.checkNumber = checkNumber;
        this.custNames = custNames;
        this.itemsByCustomerID = itemsByCustomerID;
        this.subTotals = computeSubTotals();
        this.grandTotal = computeGrandTotal();
    }
    

    
    /**
     * Iterates through all tickets and orders associated with a table
     * Finds orders with customer names associated with them that are crosslisted on the receipt
     * Sums the total of the receipt for all customers involved
     */
    private double[] computeSubTotals(){
    	double[] tempTotals= new double[this.itemsByCustomerID.size()];
    	int j = 0;
    	double sum;
    	Set<Integer> keys = this.itemsByCustomerID.keySet();    	
    	for (int key : keys) {
    		sum = 0;
    		ListIterator<MenuItem> iterator = this.itemsByCustomerID.get(key).listIterator();
    		while (iterator.hasNext()) {
    			sum += iterator.next().getPrice();
    		}
    		tempTotals[j] = sum;
    		j++;
    	}
    	return tempTotals;
    }
    
    private double computeGrandTotal() {
    	double sum = 0;
    	for (int j = 0; j < this.itemsByCustomerID.size(); j++) {
    		sum += this.subTotals[j];
    	}
    	return sum;
    }
    
    /**
     * returns the total due for a receipt
     * @return
     */
    double getGrandTotal(){
    	return this.grandTotal;
    }
    
    /**
     * returns the check number
     * @return
     */
    int getCheckNumber(){
    	return checkNumber;
    }
    
    public String toString() {
    	String string = "RECEIPT\n";
    	Set<Integer> keys = this.itemsByCustomerID.keySet();    	
    	int j = 0;
    	for (int key : keys) {
    		string += this.custNames.get(key) + "\n";
    		ListIterator<MenuItem> iterator = this.itemsByCustomerID.get(key).listIterator();
    		while (iterator.hasNext()) {
    			string += iterator.next().toString() + "\n";
    		}
    		string += " Subtotal: $" + this.subTotals[j] + "\n\n";
    		j++;
    	}
    	string += "\nTOTAL: $" + this.grandTotal + "\n\n";
    	return string;
    }
    
    private Hashtable<Integer, List<MenuItem>> itemsByCustomerID;
    private double grandTotal;
    private double[] subTotals;
    private int checkNumber;
    private List<String> custNames;


}
