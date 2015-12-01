package com.chriswlucas.restaurant;

class BarSeat extends Table {
    
    int getTableNum(){
        return tableNum
    }
    
    Boolean isOccupied(){
        return isOccupied;
    }
    
    public void toggle() {
        this.isOccupied = !this.isOccupied;
    }
    
    int tableNum;
    public boolean isOccupied = false; //set to true if table becomes occupied

}
