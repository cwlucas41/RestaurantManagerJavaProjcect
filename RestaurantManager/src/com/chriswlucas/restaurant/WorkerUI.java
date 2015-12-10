package com.chriswlucas.restaurant;

interface WorkerUI {
	public void controlWorker(int employeeID);
	public void doJob(int employeeID, int index);
	public void displayJobs(int employeeID);
}
