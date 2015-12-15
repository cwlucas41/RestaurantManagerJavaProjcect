package com.chriswlucas.restaurant;

import java.util.List;
import java.util.ListIterator;

/**
 * Worker Command Line Interface
 * @author cwlucas41
 *
 */
public class WorkerCLI extends UserCLI implements WorkerUI {
	
	/**
	 * Constructs an new WorkerCLI
	 * @param restaurant
	 */
	public WorkerCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	@Override
	public void controlWorker(int employeeID) {
		boolean isFinished = false;
		Worker worker = this.getRestaurant().getWorker(employeeID);
		if (worker.getNumberOfJobs() != 0) {
			while (!isFinished){
				displayJobs(employeeID);
				printLine("Enter number of a job to mark it as complete or enter -1 to exit");
				int choice = super.getIntegerFromUser();
				if (choice == -1) {
					isFinished = true;
				} else {
					doJob(employeeID, choice);
				}
				if (worker.getNumberOfJobs() == 0){
					isFinished = true;
					printLine(worker.getName() + " does not have any more jobs");
					printLine("\tContinuing...\n");
				}
			}
		} else {
			printLine(worker.getName() + " does not have any jobs");
			printLine("\tContinuing...\n");
		}
		
	}

	@Override
	public void displayJobs(int employeeID) {
		Worker worker = this.getRestaurant().getWorker(employeeID);
		printLine("Jobs for " + worker.getName() + ":");
		List<Job> jobs = worker.getJobs();
		ListIterator<Job> jobIterator = jobs.listIterator();
		int i = 0;
		while (jobIterator.hasNext()) {
			printLine("\tJob " + i + ": " + jobIterator.next().toString());
			i++;
		}
	}

	@Override
	public void doJob(int employeeID, int index) {
		Worker worker = this.getRestaurant().getWorker(employeeID);
		if (index < worker.getNumberOfJobs()) {
			boolean isDone = worker.doJob(index);
			if (!isDone) {
				printLine("Job number " + index + " for employee " + employeeID + " could not be completed");
			}
		} else {
			printLine("Invalid Choice\n");
		}
	}
}
