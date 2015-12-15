package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Payment Manager handles payments, splitting, and receipt generation
 * @author Jonathan Bundy
 *
 */
public class PaymentManager {
	
    private List<Receipt> receipts;
    
    private PartyManager partyManager;

    private CustomerUI customerUI;
    
    /**
     * Handles all aspects of payments including if the customer would like to split into multiple receipts
     * @param partyManager - reference to current partyManager
     * @param customerNames - reference to list of customer names
     */
    PaymentManager(PartyManager partyManager, List<String> customerNames){
        this.partyManager = partyManager;
        this.receipts = new ArrayList<Receipt>();
        this.customerUI = partyManager.getRestaurant().getCustomerInterface();
    }
    
    /**
     * Builds the hashtable of customer ID to a list of menu items for the creation of receipts
     * @param customerIDs
     * @return hashtable for customers and Lists of menu items
     */
    private Hashtable<Integer, List<MenuItem>> buildHashtableForReceipt(List<Integer> customerIDs) {
    	Hashtable<Integer, List<MenuItem>> hashtable = new Hashtable<Integer, List<MenuItem>>();
    	ListIterator<Integer> iterator = customerIDs.listIterator();
    	while (iterator.hasNext()) {
    		int customerID = iterator.next();
    		hashtable.put(customerID, partyManager.getItemsForCustomerID(customerID));
    	}
    	return hashtable;
    }
    /**
     * Controls how people would like to pay for the bill
     */
    void checkout(List<Ticket>tickets, int partyID){
    	customerUI.displayCheckout();
    	int selection = customerUI.getIntegerFromUser();
    	List<String>custNames = partyManager.getCustNames();
    	if (selection == 1){
    		int split = this.customerUI.getSplit();
    		if (split==1){
    			createReceipt(0, customerUI.setCheckNames(custNames, split, 0), tickets);
    		}
    		else{
    	    	for (int i = 0; i < split; i++){
    	    		List<Integer> checkNames = customerUI.setCheckNames(custNames, split, i);
    	    		this.createReceipt(i, checkNames, tickets);
    	    		checkNames.clear();
    	    	}
    		}
    		customerUI.displayReceipts(receipts);
    		customerUI.displayThanks();
            partyManager.getJobManager().assignCollectingJob(); //HAS TO GO HERE 
    	}


    	else if (selection == 2){
    		customerUI.displayPayWhenReady();
    	}
    	else {
    		customerUI.displayInvalidOption();
    	}
    }
    /**
     * Creates a receipt based on who is included in that check
     */
    void createReceipt(int n, List<Integer>checkNames, List<Ticket> ticks){
        Receipt receipt = new Receipt(n, buildHashtableForReceipt(checkNames), this.partyManager.getCustNames());
        receipts.add(receipt);  
    } 
}
