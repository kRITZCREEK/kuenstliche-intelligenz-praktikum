package de.fhkoeln.gm.ki.remoteControl;
import lejos.nxt.Motor;
import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTCommandConnector;
import lejos.pc.comm.NXTConnector;

/**
 * Simple NXT BT-connector
 * 
 * Based on the example by Lawrie Griffiths and Brian Bagnall
 * 
 * This REQUIRES lejos NXJ!
 * http://www.lejos.org/nxj-downloads.php
 * 
 * @author Sascha Schewe
 */
public class Connector {
	
	private static NXTConnector conn;
	private static boolean connected = false;
	
	public static void connect(String name) throws Exception {
		conn = new NXTConnector();
		conn.addLogListener(new NXTCommLogListener() {
			public void logEvent(String message) {
				System.out.println(message);				
			}

			public void logEvent(Throwable throwable) {
				System.err.println(throwable.getMessage());			
			}			
		});
		conn.setDebug(true);
		if (!conn.connectTo("btspp://"+name, NXTComm.LCP)) {
			System.err.println("Failed to connect");
			System.exit(1);
		}
		NXTCommandConnector.setNXTCommand(new NXTCommand(conn.getNXTComm()));
		connected=true;

		Motor.A.setSpeed(900);
		Motor.B.setSpeed(900);

	}	
	
	public static void close() throws Exception{
		if(connected)
			conn.close();
		connected=false;
	}
	
	public static boolean isConnected(){
		return connected;
	}
}