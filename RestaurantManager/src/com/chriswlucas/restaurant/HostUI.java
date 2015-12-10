package com.chriswlucas.restaurant;

import java.util.List;

interface HostUI {
	public void addNewPartyToWaitlist(int partySize);
	public void displayWaitlist();
	public void displaySeatingNotification(int partyID, int partySize, List<Integer> tableNumbers);
}
