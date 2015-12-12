package com.chriswlucas.restaurant;

import java.util.Hashtable;
import java.util.List;

interface HostUI {
	public void addNewPartyToWaitlist(boolean isAtBar);
	public Hashtable<String, Integer> displayWaitlist(boolean isBarWaitlist);
	public void seatCustomers(boolean isAtBar);
	public List<Integer> displayFreeTables(boolean isAtBar);
	public List<Integer> assignTableNumbers(boolean isAtBar, int partySize);
	public void controlHost();
}
