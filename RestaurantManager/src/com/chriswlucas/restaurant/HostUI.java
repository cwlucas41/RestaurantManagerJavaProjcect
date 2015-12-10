package com.chriswlucas.restaurant;

import java.util.List;

interface HostUI {
	public void addNewPartyToWaitlist(int partySize, boolean isAtBar);
	public void displayTableWaitlist();
	public void displayBarWaitlist();
	public void seatCustomers(boolean isAtBar, int index, List<Integer> assignedTableNumbers);
	public void displayFreeTables();
}
