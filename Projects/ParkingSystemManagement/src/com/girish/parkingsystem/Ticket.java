package com.girish.parkingsystem;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ticket {
	
	private static final Logger logger = LoggerFactory.getLogger(OperatorUser.class);

	
	private ParkingSlot parkingSlot;
	private int vehicleNum;
	private int startTime;
	private int endTime;
	private int ticketNumber;
	
	final private int amountPerMin = 2; 
	
	 

	public Ticket(ParkingSlot parkingSlot, int vehicleNum, int startTime, int ticketNumber) {
		super();
		this.parkingSlot = parkingSlot;
		this.vehicleNum = vehicleNum;
		this.startTime = startTime;
		this.ticketNumber = ticketNumber;
	}

	public ParkingSlot getParkingSlot() {
		return parkingSlot;
	}

	public int getAmount() {
		if (getParkedTime() > 0)
		{
			return getParkedTime() * amountPerMin;
		}
		else
		{
			logger.info("endTime not provided");
			//throw new Exception("endTime not provided");
			return 0;
		}
			
	}

	public int getParkedTime() {
		return endTime - startTime;
	}

}
