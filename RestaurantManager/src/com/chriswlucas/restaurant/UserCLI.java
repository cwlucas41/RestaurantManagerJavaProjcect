package com.chriswlucas.restaurant;

import java.util.Scanner;
import java.util.Set;

public class UserCLI extends UserInterface {

	private Scanner scanner;
	
	public UserCLI(Restaurant restaurant) {
		super(restaurant);
		this.scanner = new Scanner(System.in);
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	/**
	 * Gets an integer choice from the customer.
	 * @return data - the user's choice.
	 */
	public int getIntegerFromUser(){
		prompt();
		int data = scanner.nextInt();
		scanner.nextLine();
		printLine("");
		return data;
	}
	
	public double getDoubleFromUser(){
		prompt();
		double data = scanner.nextDouble();
		scanner.nextLine();
		printLine("");
		return data;
	}
	
	public String getLineFromUser() {
		prompt();
		String data = scanner.nextLine();
		printLine("");
		return data;
	}
	
	private void prompt() {
		this.print(">> ");
	}
	
	public void printLine(String string) {
		System.out.println(string);
	}
	
	public void print(String string) {
		System.out.print(string);
	}
	
	public int getAndCheckIntegerIsInSet(String message, Set<Integer> set, boolean membershipOfSet){
		printLine(message);
		int choice = getIntegerFromUser();
		boolean isFinished = false;
		while (!isFinished) {
			if (membershipOfSet == set.contains(choice)) {
				isFinished = true;
			} else {
				printLine("Invalid choice, try again");
				choice = getIntegerFromUser();
			}
		}
		return choice;
	}

	@Override
	public void display(String string) {
		printLine(string);
	}
}
