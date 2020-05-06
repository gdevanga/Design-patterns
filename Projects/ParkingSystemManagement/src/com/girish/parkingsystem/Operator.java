package com.girish.parkingsystem;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Operator {
	
	private static final Logger logger = LoggerFactory.getLogger(Operator.class);

	abstract void run ();
	
	protected Scanner sc;
	
	public Operator(Scanner sc) {
		this.sc = sc;
	}
	
	
	void handleEntry()
	{
		logger.info ("Enter vehicle number : ");
		int vehicleNumber = sc.nextInt();
		logger.info ("Enter 1 for car, 2 for bike: ");
		int vehicleType = sc.nextInt();
		ParkingSlot slot = ParkingModel.getInstance().handleEntry(vehicleNumber, vehicleType);
		if (slot == null)
			logger.info ("No slot available. Parking full!");
		else
			logger.info ("Parked vehicle at " + slot.getSlotNumber());
	}
	
	void handleExit()
	{
		logger.info ("Scan the ticket or enter ticket number : ");
		int ticketNumber = sc.nextInt();
		Ticket ticket = ParkingModel.getInstance().handleExit(ticketNumber);
		if (ticket == null)
			logger.info ("Ticket number is wrong");
		else
			logger.info ("Collect amount : " + ticket.getAmount() + " for time parked " + ticket.getParkedTime());
	}
}
