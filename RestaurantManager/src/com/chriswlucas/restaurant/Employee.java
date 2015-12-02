package com.chriswlucas.restaurant;

import java.util.UUID;

abstract class Employee {
	
	private String name;
	private UUID id;
	private Restaurant restaurant;
	
	public Employee(String name, Restaurant restaurant) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.restaurant = restaurant;
	}
	
	public Employee(String name, UUID id, Restaurant restaurant) {
		this.name = name;
		this.id = id;
		this.restaurant = restaurant;
	}
	
	public boolean equals(Employee employee) {
		return this.id == employee.id;
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
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
}
