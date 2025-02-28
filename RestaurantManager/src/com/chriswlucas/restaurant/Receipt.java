package com.chriswlucas.restaurant;

import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
/**
 * Receipt handles the creation and displaying of total to customers
 * @author jonbundy93
 *
 */
public class Receipt {
    private Hashtable<Integer, List<MenuItem>> itemsByCustomerID;
    
    private double grandTotal;
    
    private double[] subTotals;
    
    private int checkNumber;
    
    private List<String> custNames;
    
	/**
     * Handles the creation of the receipt
     * @param checkNumber - the number of the check item
     * @param checkNames - the names associated with a check
     */
    Receipt(int checkNumber, Hashtable<Integer, List<MenuItem>> itemsByCustomerID, List<String> custNames){
        this.checkNumber = checkNumber;
        this.custNames = custNames;
        this.itemsByCustomerID = itemsByCustomerID;
        this.subTotals = computeSubTotals();
        this.grandTotal = computeGrandTotal();
    }
    
    /**
     * sums subtotals to get grand total
     * @return grand total
     */
    private double computeGrandTotal() {
    	double sum = 0;
    	for (int j = 0; j < this.itemsByCustomerID.size(); j++) {
    		sum += this.subTotals[j];
    	}
    	return sum;
    }
    /**
     * computes the total for each customer on the receipt
     * @return array of subtotals - used only internally
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
    /**
     * returns the check number
     * @return
     */
    int getCheckNumber(){
    	return checkNumber;
    }
    /**
     * returns the total due for a receipt
     * @return
     */
    double getGrandTotal(){
    	return this.grandTotal;
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


}
