package com.chriswlucas.restaurant;

import java.util.Scanner;

class PartyManager {
	Worker waiter;
	MenuItem[] tempDrinks;
	MenuItem[] tempFood;
	Ticket[] orders;
	String[]custNames;
	PartyManager(){
		
	}
	
	void getNames(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many people in your party: ");
		int numPeople = scanner.nextInt();
		custNames = new String[numPeople];
		
		for (int i = 0; i<numPeople; i++){
			int temp = i + 1;
			String output = "Enter customer "+temp+"(FirstName and first letter of last name without spaces): ";
			System.out.print(output);
			custNames[i]=scanner.next();
		}
		scanner.close();
	}
	
	void addItem(MenuItem item, int customer){
		// from temp
		if (item.isFood()){
			
		}
	}
	
	void removeItem(MenuItem item, int customer){
		// from temp
	}
	
	void makeTicket() {
		// empty temps, create, and store ticket
	}

	public int count = 0;
}


