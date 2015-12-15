package com.chriswlucas.restaurant;

import java.sql.Time;
import java.util.List;
import java.util.ListIterator;

/**
 * Data to be moved around the restaurant.
 * Job is either done or undone.
 * @author Nick Anderson
 *
 */
public class Job implements Comparable<Job>{
	private JobManager jobManager;
	
	private Time current;
	
	private int type; //(1 is producing, 2 is serving, 3 is collecting, 4 is bussing)
	
	private List<Object>items;
	
	/**
	 * Holds a list of items/tables that need to be produced,
	 * served, paid, or cleaned.
	 * @param inItems list of items to be produced or tables that need to be taken care of.
	 * @param aType type of job. 
	 * @param jobManager reference back to the containing class.
	 */
	public Job(List<Object>inItems, int aType, JobManager jobManager){
		this.jobManager = jobManager;
		this.items= inItems;
		this.type=aType;
		this.current=new Time(0);
	}
	
	/**
	 * Compares the time's so jobs can be sorted for a worker
	 * @param other - another job.
	 */
	@Override
	public int compareTo(Job other) {
		return this.current.compareTo(other.current);
	}
	
	/**
	 * Get's the time the job was created.
	 * @return the time this job was created.
	 */
	Time getCurrent(){
		return current;
	}
	
	/**
	 * Builds the first line of the toString method based on Job type.
	 * @return a header string for output based on the type of job.
	 */
	private String getHeader(){
		switch (type) {
		case 1: return "Produce the following items:\n";
		case 2: return "Serve the following items:\n";
		case 3: return "Collect from these tables:\n";
		case 4: return "Buss the following tables:\n";
		default: return "Invalid Job type:\n";
		}
	}
	/**
	 * If the job is a producing job it needs to be changed over to a serving job.
	 * If the job is a collecting job(payment) it needs to be changed over to a bussing job.
	 * NOTE: No case 2 needed since a collecting job is created when the customer wants to pay.
	 */
	void markAsDone(){
		switch (type) {
		case 1: jobManager.assignServingJob(this); break; 
		case 3: jobManager.assignBussingJob(this); break; 
		case 4: jobManager.getPartyManager().getRestaurant().markTablesIsOccupiedByNumberAs(jobManager.getPartyManager().getTableNumbers(), false); break;
		default: break;
		}
		
	}
	/**
	 * Sets the type of this job.
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * Displays the current list of items in the job.
	 */
	@Override
	public String toString(){
		String temp;
		temp = getHeader();
		ListIterator<Object> iterator = items.listIterator();
		while(iterator.hasNext()){
			temp += "\t\t" + iterator.next().toString()+"\n";
		}
		return temp;
	}

}
