package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ArrayList;


class PaymentManager {
    /**
     * Handles all aspects of payments including if the customer would like to split into multiple receipts
     * @param partyManager - reference to current partyManager
     * @param customerNames - reference to list of customer names
     */
    PaymentManager(PartyManager partyManager, List<String> customerNames){
        this.partyManager = partyManager;
        this.receipts = new ArrayList<Receipt>();
        this.checkNames = new ArrayList<Integer>();
        this.customerUI = partyManager.getRestaurant().getCustomerInterface();
    }
    
    /**
     * Controls how people would like to pay for the bill
     */

    
    void checkout(List<Ticket>tickets, int partyID){
    	customerUI.displayCheckout();
    	int selection = customerUI.getIntegerFromUser();
    	List<String>custNames = partyManager.getCustNames();
    	if (selection == 1){
    		int split = customerUI.getSplit();
    		if (split==1){
    			createReceipt(0, customerUI.setCheckNames(custNames, split,0),tickets);
    		}
    		else{
    	    	for (int i = 0; i < split; i++){
    	    		checkNames = customerUI.setCheckNames(custNames, split, i);
    	    		this.createReceipt(i, checkNames, tickets);
    	    		checkNames.clear();
    	    	}
    		}
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
        Receipt receipt = new Receipt(n ,checkNames, ticks);
        receipt.sumTotal();
        receipts.add(receipt);  
    }
    
    private List<Receipt> receipts;
    private List<Integer> checkNames;
    private PartyManager partyManager;
    private CustomerUI customerUI; 
}
