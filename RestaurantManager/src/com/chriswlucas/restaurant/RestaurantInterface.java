package com.chriswlucas.restaurant;

/**
 * Stores the interface for the restaurant
 * @author cwlucas41
 *
 */
class RestaurantInterface implements Interfaceable{
	
	private WorkerUI workerInterface;
	private HostUI hostInterface;
	private ManagerUI managerInterface;
	private CustomerUI customerInterface;
	
	/**
	 * Constructs the RestaurantInterface and initializes all fields
	 * @param workerUI
	 * @param hostUI
	 * @param managerUI
	 * @param customerUI
	 */
	public RestaurantInterface(WorkerUI workerUI, HostUI hostUI, ManagerUI managerUI, CustomerUI customerUI){
		this.workerInterface = workerUI;
		this.hostInterface = hostUI;
		this.managerInterface = managerUI;
		this.customerInterface = customerUI;
	}
	
	
	@Override
	public WorkerUI getWorkerInterface() {
		return workerInterface;
	}

	@Override
	public HostUI getHostInterface() {
		return hostInterface;
	}

	@Override
	public ManagerUI getManagerInterface() {
		return managerInterface;
	}

	@Override
	public CustomerUI getCustomerInterface() {
		return customerInterface;
	}
}
