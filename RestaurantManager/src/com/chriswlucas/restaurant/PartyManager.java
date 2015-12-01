package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.UUID;

class PartyManager {
	//Ticket[] orders;

	PartyManager(Worker waiter){
		this.workName=waiter.getName();
		this.id = waiter.getUUID();
		jobs = new JobManager(waiter);
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
			tempFood.add(new Order(item,customer));
		}
		else{
			tempDrinks.add(new Order(item,customer));
		}
	}
	
	void removeItem(MenuItem item, int customer){
		if(item.isFood){
			ListIterator<Order> iterator = tempFood.listIterator();
			while (iterator.hasNext()){
				Order current = iterator.next();
				if((item==current.getItem())&&(customer==current.getCust())){
					tempFood.remove(iterator.next());
				}
			}
		}
		else{
			ListIterator<Order> iterator = tempDrinks.listIterator();
			while (iterator.hasNext()){
				Order current = iterator.next();
				if((item==current.getItem())&&(customer==current.getCust())){
					tempDrinks.remove(iterator.next());
				}
			}
		}		
	}
	
	void emptyTemp(){
		tempFood.removeAll(null);
		tempDrinks.removeAll(null);
	}
	
	void makeTicket() {
		// empty temps, create, and store ticket
		Ticket temp = new Ticket(tempFood, tempDrinks, Restaurant.getTicket(), id);
		tickets.add(temp);
		jobs.assignProducingJob(temp);
		emptyTemp();
	}
	
	void takeOrder(){
		
	}
    
    void pay(){
        payments = new PaymentManager(waiter);
    }
	

	String[]custNames;
	JobManager jobs;
        PaymentManager payments;
	List<Order> tempDrinks;
	List<Order> tempFood;
	List<Ticket> tickets;
	String workName;
	UUID id;
	public int count = 0;
}


