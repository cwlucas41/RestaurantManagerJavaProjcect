package com.chriswlucas.restaurant;

import java.util.Scanner;
import java.util.List;

class PaymentManager {
    
    PaymentManager(PartyManager partyManager){
        this.partyManager = partyManager;
        
    }
    
    
    void checkout(int split){
        for (int = 0; i<split; i++)
            String output = "Enter customers for first ticket separated by a space:";
            System.out.print(output);
            Scanner in = new Scanner(System.in);
            String input = sc.nextLine()
            checkNames[] = input.split(" ");
            this.createReceipt(i, checkNames);
            in.close();
        
                          
        
            
    }
    
    
    void createReceipt(int i, list checkNames){
        Receipt receipt = new Receipt();
        partyManager.jobManager.assignCollectingJob();
        
    }
    
    private PartyManager partyManager;
                   
    
                   
                   
    
}
