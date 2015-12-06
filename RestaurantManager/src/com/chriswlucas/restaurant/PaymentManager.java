package com.chriswlucas.restaurant;

import java.util.Scanner;
import java.util.List;
import java.util.ListIterator;

class PaymentManager {
    
    PaymentManager(PartyManager partyManager){
        this.partyManager = partyManager;
        
    }
    
    
    void checkout(int split){
    	this.split = split;
		for (int i = 0; i<split; i++){
			Scanner scanner = new Scanner(System.in);
            System.out.println("How many people in party " + i+1 + "?:");
            numPeople = scanner.nextInt();
    		checkNames = new int[numPeople];
            System.out.println();
            ListIterator<String> names = custNames.listIterator();
            int k = 0;
            while(it.hasNext()){
                String customer = k +name.next();
                System.out.println(name);
                k++;
            }
            for (int = 0; i<numPeople; i++){
                System.out.println();
                System.out.println("Choose the number of the customer to add to this receipt:");
                int custNumber = scanner.nextInt();
                checkNames.add(custNumber);
            }
            this.createReceipt(int i, this.checkNames);
            this.checkNames.clear();
            scanner.close();
            
    }
    
    
    void createReceipt(int i, List<checkNames>checkNames){
        Receipt receipt = new Receipt();
        partyManager.jobManager.assignCollectingJob();    
    }
    
    PartyManager partyManager;
    List<int> checkNames;
    private int split;
    private int numPeople;
    
                   
    
                   
                   
    
}
