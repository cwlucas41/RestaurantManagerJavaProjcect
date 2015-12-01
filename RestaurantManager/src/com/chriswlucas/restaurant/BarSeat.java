package com.chriswlucas.restaurant;

class BarSeat extends Table {
    
	public BarSeat(){
		super(size, number);
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
    final static int size = 1;
    final static int number = 1;
    public boolean isOccupied = false; //set to true if table becomes occupied

}
