package com.chriswlucas.restaurant;

class CLInterface extends RestaurantInterface {
	
	public CLInterface(Restaurant restaurant) {
		super(new WorkerCLI(restaurant), new HostCLI(restaurant), new ManagerCLI(restaurant), new CustomerCLI(restaurant));
	}

}
