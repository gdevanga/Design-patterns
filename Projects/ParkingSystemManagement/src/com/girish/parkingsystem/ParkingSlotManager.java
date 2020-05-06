package com.girish.parkingsystem;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingSlotManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ParkingSlotManager.class);
	//private static ParkingSlotManager instance = null;
	
	
	
	ParkingSlotManager()
	{
		createParkingSlots();
	}
	
	
	private PriorityQueue<ParkingSlot> slotQueue;
	private Set<ParkingSlot> usedSlots = new HashSet<ParkingSlot>();
	
	
	
	
	void createParkingSlots()
	{
		class PriorityQueueComparator implements Comparator<ParkingSlot> { 
		    public int compare(ParkingSlot ps1, ParkingSlot ps2) 
		    { 
		        if (ps1.getIsOccupied() > ps2.getIsOccupied()) 
		        	return 1;
		        else if (ps1.getIsOccupied() < ps2.getIsOccupied())
		        	return -1;
		        else
		        	return 0; 
		    } 
		}
		
		slotQueue = new PriorityQueue<ParkingSlot>(new PriorityQueueComparator()); 
		
		// ideally read from config
		slotQueue.add(new ParkingSlot(0,1));
		slotQueue.add(new ParkingSlot(0,2));
		slotQueue.add(new ParkingSlot(0,3));
		slotQueue.add(new ParkingSlot(0,4));
		slotQueue.add(new ParkingSlot(0,5));
		
		
	}

	public ParkingSlot getSlot()
	{
		ParkingSlot ps = slotQueue.poll(); 
		if (ps == null)
			logger.info("No new Parking slot available");
		else
		{
			ps.setIsOccupied(1);
			usedSlots.add(ps);
		}
		return ps;
		
	}
	
	public void removeSlot(ParkingSlot ps)
	{
		if (ps != null)
		{
			usedSlots.remove(ps);
			ps.setIsOccupied(0);
			slotQueue.add(ps);
		}
		else
		{
			logger.error("removed slot received ps as empty");
		}
		
	}
}
