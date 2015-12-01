package com.chriswlucas.restaurant;

class BarSeat extends Table {
    
	public BarSeat(){
		super(size, number);
	}
    int getTableNum(){
        return super.tableNum;
    }
    
    Boolean isOccupied(){
        return super.isOccupied;
    }
    
    public void toggle() {
        super.isOccupied = !super.isOccupied;
    }
    
    int tableNum;
    final static int size = 1;
    final static int number = 1;
    public boolean isOccupied = false; //set to true if table becomes occupied

}
