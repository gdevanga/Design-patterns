package com.girish.parkingsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingModel {

	private static ParkingModel instance = null;
	private static final Logger logger = LoggerFactory.getLogger(ParkingModel.class);

		
	private Map<Integer, Ticket> activeTicketMap = new HashMap<Integer, Ticket>();
	private ArrayList<Ticket> inactiveTickets = new ArrayList<Ticket>();
	private static int ticketCounter = 100;


	public static ParkingModel getInstance() {
		if (instance == null)
		{
			instance = new ParkingModel();
		}
		return instance;
	}
	
	public ParkingSlot handleEntry (int vehicleNumber, int vehicleType)
	{
		ParkingSlot ps = null;
		if (vehicleType == 1)
			ps = CarParkingSlotManager.getInstance().getSlot();
		else
			ps = BikeParkingSlotManager.getInstance().getSlot();
		if (ps != null)
		{
			Ticket tick = new Ticket (ps, vehicleNumber, 1, ticketCounter, vehicleType);
			logger.info("Ticket creted with id " + ticketCounter);
			activeTicketMap.put(ticketCounter, tick);
			++ticketCounter;
			
		}
		return ps;
	}
	
	
	public Ticket handleExit (int ticketNumber)
	{
		Ticket tick = activeTicketMap.remove(ticketNumber);
		if (tick != null)
		{
			if (tick.getVehicleType() == 1)
				CarParkingSlotManager.getInstance().removeSlot(tick.getParkingSlot());
			else
				BikeParkingSlotManager.getInstance().removeSlot(tick.getParkingSlot());
			inactiveTickets.add(tick);
		}
		return tick;
	}
}
