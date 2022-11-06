package com.crescent.drone.constants;

public class DroneConstants {
	public static final String DRONE_NOT_FOUND="Drone not found for the serial number:";
	public static final String DRONE_EXISTS="Drone already exists for the serial number:";
	public static final String DRONE_BATTERY_LOW="Drone battery too low for the delivery.";
	public static final String DRONE_WEIGT_LOW="Drone does not have enough weight capacity";
	public static final String INVALID_DRONE_MODEL="Invalid Drone model. Valid models are: 'Lightweight', 'Middleweight', 'Cruiserweight' and 'Heavyweight'";
	public static final String INVALID_DRONE_STATES="Invalid Drone state. Valid states are: 'IDLE', 'LOADING', 'LOADED', 'DELIVERING','DELIVERED' and 'RETURNING'";
	public static final String[] DRONE_MODELS= {"Lightweight","Middleweight","Cruiserweight","Heavyweight"};
	public static final String[] DRONE_STATES= {"IDLE","LOADING","LOADED","DELIVERING","DELIVERED","RETURNING"};
	public static final long MAX_WEIGHT=500;
	public static final long MIN_BATTERY=25;
	
}
