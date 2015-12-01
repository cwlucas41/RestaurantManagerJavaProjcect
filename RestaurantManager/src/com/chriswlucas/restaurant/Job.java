package com.chriswlucas.restaurant;

import java.sql.Time;
import java.util.List;

class Job implements Comparable<Job>{
	//String message;
	// save time of creation
	// save type of job
	public Job(List<Order>inItems, String aType, int tNum, boolean sub){
		this.isSub=sub;
		this.type=aType;
		this.current=new Time(0);
		this.ticketNum=tNum;
	}
	
	void markAsDone(){
		// depends on type of job
		isDone=true;
	}
	
	String getType(){
		return type;
	}
	
	Time getTime(){
		return current;
	}
	
	int getTicketNum(){
		return ticketNum;
	}
	
	boolean checkSub(){
		return isSub;
	}
	
	int ticketNum;
	Time current;
	String type;
	boolean isDone=false;
	boolean isSub = false;

	public Time getCurrent() {
		return current;
	}
}
