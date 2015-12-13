package com.chriswlucas.restaurant;

/**
 * Generic interface for worker
 * @author cwlucas41
 *
 */
interface WorkerUI {
	
	/**
	 * starts event loop for specified worker
	 * @param employeeID
	 */
	public void controlWorker(int employeeID);
	
	/**
	 * Makes specified worker mark the specified job as done
	 * @param employeeID
	 * @param index
	 */
	public void doJob(int employeeID, int index);
	
	/**
	 * Displays the assigned jobs to the specified worker
	 * @param employeeID
	 */
	public void displayJobs(int employeeID);
}
