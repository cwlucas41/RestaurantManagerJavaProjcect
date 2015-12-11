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
    }
    
    /**
     * Controls how people would like to pay for the bill
     */
//    void checkout(List<Ticket>tickets, int split){
//    	Scanner scanner = new Scanner(System.in);
//    	System.out.println("How many ways would you like to split the check?: ");
//    	split = scanner.nextInt();
//    	if (split == 1){
//    		List<Integer> checkNames = new ArrayList<Integer>();
//    		for (int i = 0; i<customerNames.size(); i++){
//    			checkNames.add(i);
//    		}
//    		createReceipt(split - 1, checkNames, tickets);
//    	}
//    	else {
//    		for (int i = 0; i<split; i++){
//    			List<Integer> checkNames = new ArrayList<Integer>();
//    			int temp = i++;
//    			System.out.println("How many people in party " + temp + "?:");
//    			numPeople = scanner.nextInt();
//    			System.out.println();
//    			ListIterator<String> names = customerNames.listIterator();
//    			int k = 0;
//    			while(names.hasNext()){
//    				String customer = k + ") " + names.next();
//    				System.out.println(customer);
//    				k++;
//    			}
//    			for (int j = 0; j<numPeople; j++){
//    				System.out.println();
//    				System.out.println("Choose the number of the customer to add to this receipt:");
//    				int custNumber = scanner.nextInt();
//    				checkNames.add(custNumber);
//    			}
//    			createReceipt(i, checkNames,tickets);
//    		}
//    	}
//    }
    
    void checkout(List<Ticket>tickets, int partyID, int split){
    	
    	for (int i = 0; i < split; i++){
    		checkNames = customerUI.setCheckNames(partyID, split);
    		this.createReceipt(i, checkNames, tickets);
    		checkNames.clear();
    	}
//    	for (int i = 0; i < split; i++){
//    		int size = customerUI.setCheckNames(partyID, split).size();
//    		for (int k = 0; k < size; k++){
//    			checkNames.add(customerUI.setCheckNames(partyID, split).get(k));
//    		}
//    		this.createReceipt(i, checkNames, tickets);
//    		checkNames.clear();
//    	}
    }
    /**
     * Creates a receipt based on who is included in that check
     */
    void createReceipt(int n, List<Integer>checkNames, List<Ticket> ticks){
        Receipt receipt = new Receipt(n ,checkNames, ticks);
        receipt.sumTotal();
        receipts.add(receipt);
        partyManager.getJobManager().assignCollectingJob();   
    }
    
    private List<Receipt> receipts;
    private List<Integer> checkNames;
    private PartyManager partyManager;
    private CustomerUI customerUI;

    
                   
    
                   
                   
    
}
