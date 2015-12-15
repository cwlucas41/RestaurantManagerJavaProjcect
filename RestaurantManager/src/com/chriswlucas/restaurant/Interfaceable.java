package com.chriswlucas.restaurant;
 /**
  * Defines interfaces available to the restaurant
  * @author cwlucas41
  *
  */
public interface Interfaceable {
	/**
	 * Gets the interface for customers
	 */
	public CustomerUI getCustomerInterface();
	
	/**
	 * Gets the interface for the host
	 */
	public HostUI getHostInterface();

	/**
	 * Gets the interface for the manager
	 */
	public ManagerUI getManagerInterface();

	/**
	 * Gets the interface for workers
	 */
	public WorkerUI getWorkerInterface();
}
