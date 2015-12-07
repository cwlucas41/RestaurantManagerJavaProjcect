package com.chriswlucas.restaurant;

class RestaurantInterface {
	
	private WorkerUI workerInterface;
	private HostUI hostInterface;
	private ManagerUI managerInterface;
	private CustomerUI customerInterface;
	
	public RestaurantInterface(WorkerUI workerUI, HostUI hostUI, ManagerUI managerUI, CustomerUI customerUI){
		this.workerInterface = workerUI;
		this.hostInterface = hostUI;
		this.managerInterface = managerUI;
		this.customerInterface = customerUI;
	}
	
	
	public WorkerUI getWorkerInterface() {
		return workerInterface;
	}

	public HostUI getHostInterface() {
		return hostInterface;
	}

	public ManagerUI getManagerInterface() {
		return managerInterface;
	}

	public CustomerUI getCustomerInterface() {
		return customerInterface;
	}
}
