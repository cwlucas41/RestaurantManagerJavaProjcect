package com.chriswlucas.restaurant;

/**
 * CLInteface
 * complete Command Line Interface for Restaurant
 * @author cwlucas41
 *
 */
public class CLInterface extends RestaurantInterface {
	
	/**
	 * Creates a new RestaurantInterface as a CLInterface
	 * @param restaurant
	 */
	public CLInterface(Restaurant restaurant) {
		super(new WorkerCLI(restaurant), new HostCLI(restaurant), new ManagerCLI(restaurant), new CustomerCLI(restaurant));
	}

}
