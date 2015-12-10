package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Worker {
	
	private List<Job> jobs;
	private String name;
	private Restaurant restaurant;
	
	public Worker(String name, Restaurant restaurant) {
		this.name = name;
		this.restaurant = restaurant;
		this.jobs = new ArrayList<Job>();
	}
	
	public void assignJob(Job job) {
		jobs.add(job);
		Collections.sort(jobs);
	}
	
	public boolean doJob(int index) {
		if (index < this.jobs.size()) {
			jobs.get(index).markAsDone();
			jobs.remove(index);
			Collections.sort(jobs);
			return true;
		}
		return false;
	}
	
	public int getNumberOfJobs() {
		return jobs.size();
	}
	
	public List<Job> getJobs() {
		return jobs;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
}
