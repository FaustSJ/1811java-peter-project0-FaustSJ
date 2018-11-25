package com.revature;

import org.apache.log4j.Logger;

import com.revature.controller.Session;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */

/*
 * keyboard shortcuts:
 * ctrl+shift+o
 * to clean up imports
 * 
 * ctrl+space
 * to auto-complete for main(), sysout
 * 
 * ctrl+shift+numPad/ (num pad back-slash)
 * to collapse code blocks
 * 
 * one sequence and one trigger per table
 */

/*
 * Session Tests:
 * 
 * quit register
 * register fail
 * register success
 * quit login
 * login fail
 * login success
 * view transactions fail (no transactions listed)
 * withdraw fail
 * deposit fail (by entering a non-numeric value)
 * deposit success
 * view transactions pass (1 transaction listed)
 * withdraw success
 * view transactions pass (2 transactions listed)
 * logout success
 * quit program success
 * 
 */

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		new Session().welcomeScreen();
		
	}
}
