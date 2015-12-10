package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;

class WorkerCLI extends UserCLI implements WorkerUI {
	
	public WorkerCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	public void controlWorker(int employeeID) {
		boolean isFinished = false;
		Worker worker = this.getRestaurant().getWorker(employeeID);
		if (worker.getNumberOfJobs() != 0) {
			while (!isFinished){
				displayJobs(employeeID);
				System.out.println("Enter number of a job to mark it as complete or enter -1 to exit");
				int choice = super.getIntegerFromUser();
				if (choice == -1) {
					isFinished = true;
				} else {
					doJob(employeeID, choice);
				}
				if (worker.getNumberOfJobs() == 0){
					isFinished = true;
					System.out.println(worker.getName() + " does not have any more jobs");
					System.out.println("\tContinuing...\n");
				}
			}
		} else {
			System.out.println(worker.getName() + " does not have any jobs");
			System.out.println("\tContinuing...\n");
		}
		
	}

	public void doJob(int employeeID, int index) {
		Worker worker = this.getRestaurant().getWorker(employeeID);
		if (index < worker.getNumberOfJobs()) {
			boolean isDone = worker.doJob(index);
			if (!isDone) {
				System.out.println("Job number " + index + " for employee " + employeeID + " could not be completed");
			}
		} else {
			System.out.println("Invalid Choice\n");
		}
	}

	public void displayJobs(int employeeID) {
		Worker worker = this.getRestaurant().getWorker(employeeID);
		System.out.println("Jobs for " + worker.getName() + ":");
		List<Job> jobs = worker.getJobs();
		ListIterator<Job> jobIterator = jobs.listIterator();
		int i = 0;
		while (jobIterator.hasNext()) {
			System.out.println("\tJob " + i + ": " + jobIterator.next().toString());
			i++;
		}
	}
}
