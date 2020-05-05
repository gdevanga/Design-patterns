package com.girish.parkingsystem;

import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingSystemCli {
	
	 /** constant to use to add new lines to output */
    private static final String NEW_LINE = System.lineSeparator();
	
	
	// -------------------------------------------------------------------------
    // CLASS VARIABLES
    // -------------------------------------------------------------------------

    /** class logger */
	 private static final Logger logger = LoggerFactory.getLogger(ParkingSystemCli.class);


	/**
     * Entry point to the application.
     *
     * @param args
     *         command line arguments
     */
	public static void main(String[] args) {
		
		try
        {
		    Options options = getOptions();
            CommandLineParser parser = new DefaultParser();
            final CommandLine cmdLine = parser.parse(options, args);
            validateCommandLine(cmdLine);

            switch (cmdLine.getArgs().length)
            {
                case 1:
                case 2:
                	throw new ParseException("No right arguments supplied");

                case 3:
                {
                	Operator op = null;
                	Scanner sc = new Scanner(System.in);
                	if (cmdLine.getArgs()[2].equals("user"))
                	{
                		op = new OperatorUser(sc);
                	}
                	else if (cmdLine.getArgs()[2].equals("admin"))
                	{
                		op = new OperatorAdmin(sc);
                	}
                	else
                	{
                		sc.close();
                		throw new ParseException("It has be either 'user' or 'admin'");
                	}
                	op.run();
                	sc.close();
                }
                break;

                default:
                    throw new ParseException("No arguments supplied");
            }
        }
        /*catch (final IOException ex)
        {
            logger.error("Could not parse file", ex);
            System.exit(1);
        }*/
        catch (final ParseException e)
        {
        	printUsage(e.getMessage());
        	System.exit(1);
        }
		catch (final Exception e)
        {
			logger.error("Exited due to " + e.getMessage());
	        System.exit(1);
        }

	}
	
	/**
     * Performs application validation against the provided command line.  If the validation fails
     * then an appropriate exception will be thrown (ParseException or subclasses)
     *
     * @param cmdLine
     *         the command line passes to main
     *
     * @throws ParseException
     *         if there are issues with the options or arguments
     */
    private static void validateCommandLine(final CommandLine cmdLine) throws ParseException
    {
        if (cmdLine.hasOption("h"))
        {
            throw new ParseException("");
        }

        if (cmdLine.getArgs().length != 1 && cmdLine.getArgs().length != 3)
        {
            throw new MissingArgumentException("Must specify 1 or 3 arguments");
        }

        // All good!
    }
    
    /**
     * Returns the Command Line Options for this application
     *
     * @return the Command Line Options for this application
     */
    private static Options getOptions()
    {
        return new Options().addOption("h", "help", false, "Print out help");
    }
    
    /**
     * Prints the usage message
     *
     * @param footerMessage
     *         adds to the footer of the message, useful for specifying known issues with usage.
     */
    private static void printUsage(final String footerMessage)
    {
        final String callPattern = "USAGE: parkingSystem [options] <userName> <password> <mode>" + NEW_LINE
                + NEW_LINE + NEW_LINE + "Where:" + NEW_LINE
                + "    userName        username of user"
                + NEW_LINE
                + "    password        password of user"
                + NEW_LINE
                + "    mode            either 'user' mode or 'admin' mode";

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(callPattern, "Options:", getOptions(), NEW_LINE + footerMessage);
    }


}
