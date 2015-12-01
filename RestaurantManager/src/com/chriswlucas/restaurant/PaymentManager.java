package com.chriswlucas.restaurant;

import java.util.Scanner;
import java.util.List;
import java.io.InputStream;
import java.awt.FlowLayout;
import javax.swing.JFrame;

class PaymentManager {
    
    PaymentManager(PartyManager partyManager){
        this.partyManager = partyManager;
        
    }
    
    
    void checkout(int split){
    	this.split = split;
        Object Scanner;
		for (int i = 0; i<split; i++){
			Scanner scanner = new Scanner(System.in);
            System.out.println("How many people in party" + i + "?:");
            numPeople = scanner.nextInt();
    		checkNames = new String[numPeople];
		
    		for (int i = 0; i<numPeople; i++){
    			System.out.println("Enter the next name:");
    			checkNames[i]=scanner.next();
    		}
    			
            this.createReceipt(int i, this.checkNames);
            scanner.close();
        
                          
        
            
    }
    
    
    void createReceipt(int i, List<checkNames>checkNames){
        Receipt receipt = new Receipt();
        partyManager.jobManager.assignCollectingJob();
        
    }
    
    private PartyManager partyManager;
    public String[]checkNames;
    private int split;
    private int numPeople;
    
                   
    
                   
                   
    
}
