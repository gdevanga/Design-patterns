package com.girish.parkingsystem;

public class BikeParkingSlotManager extends ParkingSlotManager {

	//private static final Logger logger = LoggerFactory.getLogger(CarParkingSlotManager.class);
	private static BikeParkingSlotManager instance = null;
	

	public static BikeParkingSlotManager getInstance()
	{
		if (instance == null)
		{
			instance = new BikeParkingSlotManager();
		}
		return instance;
	}
}
