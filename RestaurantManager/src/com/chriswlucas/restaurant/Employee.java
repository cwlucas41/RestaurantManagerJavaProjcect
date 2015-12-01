package com.chriswlucas.restaurant;

import java.util.UUID;

abstract class Employee implements Nameable, Identifiable {
	
	private String name = "";
	private UUID id = UUID.randomUUID();
	
	public Employee(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public UUID getUUID(){
		return id;
	}
}
