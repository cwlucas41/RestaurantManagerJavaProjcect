package com.chriswlucas.restaurant;

import java.util.Scanner;
import java.util.Set;

/**
 * Root class of all CLI classes
 * provides place for common scanner field and convenience methods
 * @author cwlucas41
 *
 */
public class UserCLI extends UserInterface {

	private Scanner scanner;
	
	/**
	 * Constructs new User Command Line Interface
	 * @param restaurant
	 */
	public UserCLI(Restaurant restaurant) {
		super(restaurant);
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * provides getter for common scanner
	 * @return scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}
	
	/**
	 * Gets an integer choice from the user.
	 * @return data - the user's choice.
	 */
	public int getIntegerFromUser(){
		prompt();
		int data = scanner.nextInt();
		scanner.nextLine();
		printLine("");
		return data;
	}
	
	/**
	 * Gets a double choice from the user
	 * @return double
	 */
	public double getDoubleFromUser(){
		prompt();
		double data = scanner.nextDouble();
		scanner.nextLine();
		printLine("");
		return data;
	}
	
	/**
	 * Gets a string from the user
	 * @return string
	 */
	public String getLineFromUser() {
		prompt();
		String data = scanner.nextLine();
		printLine("");
		return data;
	}
	
	/**
	 * prints common prompt
	 */
	private void prompt() {
		this.print(">> ");
	}
	
	/**
	 * wrapper for println
	 * @param string
	 */
	public void printLine(String string) {
		System.out.println(string);
	}
	
	/**
	 * wrapper for print
	 * @param string
	 */
	public void print(String string) {
		System.out.print(string);
	}
	
	/**
	 * prompts the user for an integer and will keep prompting until the user enters an integer
	 * that has the appropriate membership of the supplied set
	 * @param message
	 * @param set
	 * @param membershipOfSet
	 * @return integer with appropriate membership of set
	 */
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
