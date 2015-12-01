package com.chriswlucas.restaurant;

class Table {
	
	Table(int size, int number) {
		this.size = size;
		this.number = number;
	}
	
	int getTableNum(){
		return tableNum;
	}
    
    Boolean isOccupied(){
        return isOccupied;
    }
    
    public void toggle() {
        this.isOccupied = !this.isOccupied;
    }
	
	int tableNum;
	int size;
	int number;
    public boolean isOccupied = false; //set to true if table becomes occupied
}
