package com.chriswlucas.restaurant;

import java.util.Hashtable;
import java.util.List;
/**
 * Generic Interface for host of Restaurant
 * @author cwlucas41
 *
 */
public interface HostUI {
	
	/**
	 * Adds new party to waitlist
	 * @param isAtBar - controls which waitlist party is added to
	 */
	public void addNewPartyToWaitlist(boolean isAtBar);
	
	/**
	 * Interface for assigning subset of free tables to a party of a particular size
	 * @param isAtBar - controls which tables are available to choose from
	 * @param partySize
	 * @return keys for assigned tables
	 */
	public List<Integer> assignTableNumbers(boolean isAtBar, int partySize);
	
	/**
	 * Enters into host event loop
	 */
	public void controlHost();
	
	/**
	 * displays all of the active partyManagers to the host
	 */
	public void displayAllActiveParties();
	
	/**
	 * Displays the unoccupied tables to the host
	 * @param isAtBar - controls which table, normal or bar seats, are displayed
	 * @return keys of free tables
	 */
	public List<Integer> displayFreeTables(boolean isAtBar);
	
	/**
	 * Displays a waitlist
	 * @param isBarWaitlist - controls which waitlist is displayed
	 * @return
	 */
	public Hashtable<String, Integer> displayWaitlist(boolean isBarWaitlist);
	
	/**
	 * seats party on waitlist in restaurant, creates necessary party manager
	 * @param isAtBar - controls which waitlist is pulled from, and where party is seated
	 */
	public void seatCustomers(boolean isAtBar);
}
