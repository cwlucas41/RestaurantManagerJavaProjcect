package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Class for restaurant workers
 * Workers
 *   maintain a job list
 *   can mark any job on the list as done
 * @author cwlucas41
 *
 */
class Worker {
	
	private List<Job> jobs;
	private String name;
	private Restaurant restaurant;
	
	/**
	 * Creates a new worker with initialized fields
	 * @param name of worker
	 * @param restaurant
	 */
	public Worker(String name, Restaurant restaurant) {
		this.name = name;
		this.restaurant = restaurant;
		this.jobs = new ArrayList<Job>();
	}
	
	/**
	 * Assigns a provided job to the worker
	 * sorts jobs by time of creation
	 * @param job
	 */
	public void assignJob(Job job) {
		jobs.add(job);
		Collections.sort(jobs);
	}
	
	/**
	 * Marks a job as done
	 * @param index
	 * @return boolean indicating if operation was successful
	 */
	public boolean doJob(int index) {
		if (index < this.jobs.size()) {
			jobs.get(index).markAsDone();
			jobs.remove(index);
			Collections.sort(jobs);
			return true;
		}
		return false;
	}
	
	/**
	 * gets the number of jobs the worker currently is assigned
	 * @return number of jobs
	 */
	public int getNumberOfJobs() {
		return jobs.size();
	}
	
	/**
	 * Gets the list of all jobs for the worker
	 * @return job list
	 */
	public List<Job> getJobs() {
		return jobs;
	}
	
	/**
	 * Gets the name of the worker
	 * @return name string
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name of the worker
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Gets the restaurant that the worker belongs to 
	 * @return restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}
}
