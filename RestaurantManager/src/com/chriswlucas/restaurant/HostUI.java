package com.chriswlucas.restaurant;

import java.util.List;

interface HostUI {
	public void addNewPartyToWaitlist(int partySize, boolean isAtBar);
	public void displayTableWaitlist();
	public void displayBarWaitlist();
	public void seatCustomers(boolean isAtBar, int index);
	public List<Integer> displayFreeTables(boolean isAtBar);
	public List<Integer> assignTableNumbers(boolean isAtBar, int partySize);
}
