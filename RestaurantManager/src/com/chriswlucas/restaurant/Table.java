package com.chriswlucas.restaurant;

class Table implements Comparable<Table>{
	
	Table(int size) {
		this.size = size;
		this.isOccupied = false;
	}
	
	public int getsize(){
		return size;
	}
    
    public Boolean isOccupied(){
        return isOccupied;
    }
    
    public void setOccupied() {
        this.isOccupied = true;
    }
    
    public void setNotOccupied() {
    	this.isOccupied = false;
    }
    
    public String toString() {
    	Integer i = (Integer) size;
    	return i.toString();
    }
    
	@Override
	public int compareTo(Table o) {
		Integer size = this.size;
		return size.compareTo(o.size);
	}
	
	private int size;
    private boolean isOccupied; //set to true if table becomes occupied

}
