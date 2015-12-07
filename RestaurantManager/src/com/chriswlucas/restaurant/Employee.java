package com.chriswlucas.restaurant;

abstract class Employee {
	
	private String name;
	private Restaurant restaurant;
	
	public Employee(String name, Restaurant restaurant) {
		this.name = name;
		this.restaurant = restaurant;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
}
