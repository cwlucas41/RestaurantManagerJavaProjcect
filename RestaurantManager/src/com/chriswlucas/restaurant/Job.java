package com.chriswlucas.restaurant;

import java.sql.Time;
import java.util.List;
import java.util.ListIterator;

class Job{
	//String message;
	// save time of creation
	// save type of job
	public Job(List<Object>inItems, int aType){
		this.items=inItems;
		this.type=aType;
		this.current=new Time(0);
	}
	
	void markAsDone(){
		// depends on type of job
		isDone=true;
	}
	private String getHeader(){
		switch (type) {
		case 1: return "Produce the following items:\n";
		case 2: return "Serve the following items:\n";
		case 3: return "Collect from these tables:\n";
		case 4: return "Buss the following tables:\n";
		default: return "Invalid Job type:\n";
		}
	}
	
	void displayJob(){
		System.out.println(getHeader());
		System.out.println();
		ListIterator<Object> iterator = items.listIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next().toString());
		}
	}
	
	Time getCurrent(){
		return current;
	}
		
	boolean isDone;
	Time current;
	int type; //(1 is producing, 2 is serving, 3 is collecting, 4 is bussing)
	List<Object>items;
}
