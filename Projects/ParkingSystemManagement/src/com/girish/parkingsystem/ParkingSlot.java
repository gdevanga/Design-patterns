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
	
	private static PriorityQueue<ParkingSlot> slotQueue;
	private static Set<ParkingSlot> usedSlots = new HashSet<ParkingSlot>();
	

	public ParkingSlot(int isOccupied, int slotNumber) {
		super();
		this.isOccupied = isOccupied;
		this.slotNumber = slotNumber;
	}

	
	static void createParkingSlots()
	{
		class PriorityQueueComparator implements Comparator<ParkingSlot> { 
		    public int compare(ParkingSlot ps1, ParkingSlot ps2) 
		    { 
		        if (ps1.isOccupied > ps2.isOccupied) 
		        	return 1;
		        else if (ps1.isOccupied < ps2.isOccupied)
		        	return -1;
		        else
		        	return 0; 
		    } 
		}
		
		slotQueue = new PriorityQueue<ParkingSlot>(new PriorityQueueComparator()); 
		
		slotQueue.add(new ParkingSlot(0,1));
		slotQueue.add(new ParkingSlot(0,2));
		slotQueue.add(new ParkingSlot(0,3));
		slotQueue.add(new ParkingSlot(0,4));
		slotQueue.add(new ParkingSlot(0,5));
		
		
	}
	
	public int getSlotNumber() {
		return slotNumber;
	}


	public static ParkingSlot getSlot()
	{
		ParkingSlot ps = slotQueue.poll(); 
		if (ps == null)
			logger.info("No new Parking slot available");
		else
		{
			ps.isOccupied = 1;
			usedSlots.add(ps);
		}
		return ps;
		
	}
	
	public static void removeSlot(ParkingSlot ps)
	{
		if (ps != null)
		{
			usedSlots.remove(ps);
			ps.isOccupied = 0;
			slotQueue.add(ps);
		}
		else
		{
			logger.error("removed slot received ps as empty");
		}
		
	}

}
