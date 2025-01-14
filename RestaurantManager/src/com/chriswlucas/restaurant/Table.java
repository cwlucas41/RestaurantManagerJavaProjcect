package com.chriswlucas.restaurant;

/**
 * Tables have size and state
 * @author Jonathan Bundy
 *
 */
public class Table implements Comparable<Table>{
	
	private int size;
	
	private boolean isOccupied; //set to true if table becomes occupied
    
	/**
	 * Constructs table with all fields initialized
	 * @param size
	 */
	Table(int size) {
		this.size = size;
		this.isOccupied = false;
	}
    
    @Override
	public int compareTo(Table o) {
		Integer size = this.size;
		return size.compareTo(o.size);
	}
    
    /**
	 * gets the size of the table
	 * @return size
	 */
	public int getsize(){
		return size;
	}
    
    /**
	 * gets the state of the table
	 * @return boolean occupied state - 1: occupied, 0: not occupied
	 */
    public Boolean isOccupied(){
        return isOccupied;
    }
    
	/**
     * sets the table to unoccupied state
     */
    public void setNotOccupied() {
    	this.isOccupied = false;
    }
	
	/**
     * sets the table to occupied state
     */
    public void setOccupied() {
        this.isOccupied = true;
    }
    @Override
	public String toString() {
    	Integer i = size;
    	return i.toString();
    }

}
