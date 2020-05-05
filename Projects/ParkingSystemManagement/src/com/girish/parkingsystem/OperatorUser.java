package com.girish.parkingsystem;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorUser extends Operator {
	
	private static final Logger logger = LoggerFactory.getLogger(OperatorUser.class);


	public OperatorUser(Scanner sc) {
		super (sc);
	}

	void run()
	{
		  
		while (true)
		{
			logger.info ("Enter 1 for Entry, 2 for for Exit, 3 for logout");
			
			int option = sc.nextInt();
			
			switch (option)
			{
				case 1:
				{
					handleEntry();
					
				}
				break;
				
				case 2:
				{
					handleExit();
					
				}
				break;
				
				case 3:
				{
					logger.info ("Exiting..");
					return;
				}
				
				default:
				{
					logger.info ("No Valid input");
					continue;
				}
				
			}
		}
	}

}
