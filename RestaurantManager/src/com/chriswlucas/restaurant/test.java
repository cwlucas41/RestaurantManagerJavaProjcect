package com.chriswlucas.restaurant;

public class test {
	public static void main (String args[]){
		Worker nick = new Worker("Nick");
		PartyManager test = new PartyManager(nick);
		test.getNames();
	}
}
