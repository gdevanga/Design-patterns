package com.girish.parkingsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarParkingSlotManager extends ParkingSlotManager {
	
	//private static final Logger logger = LoggerFactory.getLogger(CarParkingSlotManager.class);
	private static CarParkingSlotManager instance = null;
	

	public static CarParkingSlotManager getInstance()
	{
		if (instance == null)
		{
			instance = new CarParkingSlotManager();
		}
		return instance;
	}
}
