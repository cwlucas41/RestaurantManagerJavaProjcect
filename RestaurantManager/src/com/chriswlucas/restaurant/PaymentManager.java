package com.chriswlucas.restaurant;

import java.util.Scanner;
import java.util.List;
import java.util.ListIterator;

class PaymentManager {
    
    PaymentManager(PartyManager partyManager, List<String> customerNames){
        this.partyManager = partyManager;
        this.customerNames = customerNames;
    }
    
    /**
     * Controls how people would like to pay for the bill
     */
    void checkout(){
    	// get split details
		for (int i = 0; i<split; i++){
			Scanner scanner = new Scanner(System.in);
            System.out.println("How many people in party " + i+1 + "?:");
            numPeople = scanner.nextInt();
    		checkNames = new int[numPeople];
            System.out.println();
            ListIterator<String> names = customerNames.listIterator();
            int k = 0;
            while(names.hasNext()){
                String customer = k + " " + names.next();
                System.out.println(customer);
                k++;
            }
            for (int = 0; i<numPeople; i++){
                System.out.println();
                System.out.println("Choose the number of the customer to add to this receipt:");
                int custNumber = scanner.nextInt();
                checkNames.add(custNumber);
            }
            this.createReceipt(int i+1, this.checkNames);
            this.checkNames.clear();
            scanner.close();
            
    }
    
    /**
     * Creates a receipt based on who is included in that check
     */
    void createReceipt(int i, List<checkNames>checkNames){
        Receipt receipt = new Receipt(this.i+1, this.checkNames);
        partyManager.jobManager.assignCollectingJob();    
    }
    
    private List<String> customerNames;
    private PartyManager partyManager;
    private int[] checkNames;
    private int split;
    private int numPeople;
    
                   
    
                   
                   
    
}
