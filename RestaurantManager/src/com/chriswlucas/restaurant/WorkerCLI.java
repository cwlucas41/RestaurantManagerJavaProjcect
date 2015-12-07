package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;

class WorkerCLI extends UserInterface implements WorkerUI {
	
	public WorkerCLI(Restaurant restaurant){
		super(restaurant);
	}

	@Override
	public void doJob(int employeeID, int index) {
		this.getRestaurant().getWorker(employeeID).doJob(index);
	}

	@Override
	public void displayJobs(int employeeID) {
		List<Job> jobs = this.getRestaurant().getWorker(employeeID).getJobs();
		ListIterator<Job> jobIterator = jobs.listIterator();
		int i = 0;
		while (jobIterator.hasNext()) {
			System.out.println("Job " + i);
			System.out.println(jobIterator.next().toString());
			System.out.println();
		}
	}
}
