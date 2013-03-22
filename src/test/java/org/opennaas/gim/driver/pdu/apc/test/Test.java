package org.opennaas.gim.driver.pdu.apc.test;
import java.io.IOException;
import java.util.Date;



import GIM.MeasuredLoad;
import GIM.PowerDeliveryResource;
import GIM.PowerMonitorLog;
import GIM.RouterResource;


public class Test {
	public static void main(String[] args) throws IOException {
		
		//PowerDeliveryResource PDU = new PowerDeliveryResource("udp:10.37.128.95/161");
		PowerDeliveryResource PDU = new PowerDeliveryResource("udp:193.1.29.121/161");
		RouterResource router1 = new RouterResource(PDU);
		
		System.out.println("Router Power Supply : "+router1.getPowerSupplyDeviceName());

		//System.out.println("Voltage :"+voltage);
		//System.out.println("Watts :"+watts);
		
		boolean status = router1.getPowerStatus();
		System.out.println("Router Status: "+status);
		

		//router1.powerOff();
		
		status = router1.getPowerStatus();
		System.out.println("Router Status: "+status);
		
		//router1.powerOn();
		
		status = router1.getPowerStatus();
		System.out.println("Router Status: "+status);
		
		MeasuredLoad ml = router1.getCurrentRouterPower();
		
		System.out.println("Router Energy Class (PowerSupply) "+router1.getEnergyClass());
		System.out.println("Router Energy Type (PowerSupply) "+router1.getEnergyType());
		System.out.println("Router Energy CO2perKW: "+router1.getCO2PerKw());
		
		System.out.println("Router Voltage @ "+ml.getReadingTime().toLocaleString()+": "+ml.getVoltage());
		System.out.println("Router Current @ "+ml.getReadingTime().toLocaleString()+": "+ml.getCurrent());
		System.out.println("Router Power @ "+ml.getReadingTime().toLocaleString()+": "+ml.getPower());
			
		int time = 0, monintoringDuration = router1.getPowerMonitoringSampleDuration(), 
		sampleTime = router1.getPowerMonitoringSamplePeriod();
	
		while(time < monintoringDuration){
			
			if(time%sampleTime ==0)
				router1.readToPowerLog();
			
			time++;
		}
		
		
		
		}
}
