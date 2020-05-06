package com.girish.parkingsystem;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingSlot {
	
	private static final Logger logger = LoggerFactory.getLogger(ParkingSlot.class);

	
	private int isOccupied;
	private int slotNumber;
	
	

	public ParkingSlot(int isOccupied, int slotNumber) {
		super();
		this.isOccupied = isOccupied;
		this.slotNumber = slotNumber;
	}

	
		
	public int getSlotNumber() {
		return slotNumber;
	}



	public int getIsOccupied() {
		return isOccupied;
	}



	public void setIsOccupied(int isOccupied) {
		this.isOccupied = isOccupied;
	}


	
}
