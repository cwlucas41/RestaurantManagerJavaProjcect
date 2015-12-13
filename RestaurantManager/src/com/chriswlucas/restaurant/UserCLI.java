package com.chriswlucas.restaurant;

import java.util.Scanner;

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

	@Override
	public void display(String string) {
		printLine(string);
	}
}
