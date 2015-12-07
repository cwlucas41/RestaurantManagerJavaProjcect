package com.chriswlucas.restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Worker extends Employee {
	private List<Job> jobs = new LinkedList<Job>();
	
	public Worker(String name, Restaurant restaurant){
		super(name, restaurant);
	}
	
	private Comparator<Job> comp = new Comparator<Job>() {
		public int compare(Job j1, Job j2) {
			return j1.getCurrent().compareTo(j2.getCurrent());
		}
	};
	
	public void assignJob(Job job) {
		jobs.add(job);
		Collections.sort(jobs, comp);
	}
	
	public void doJob(int index) {
		jobs.get(index).markAsDone();
		jobs.remove(index);
		Collections.sort(jobs, comp);
	}
	
	public int getNumberOfJobs() {
		return jobs.size();
	}
	
	public List<Job> getJobs() {
		return jobs;
	}
}
