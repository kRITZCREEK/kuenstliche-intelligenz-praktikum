import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import lejos.geom.Line;
import lejos.geom.Point;
import lejos.geom.Rectangle;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.RangeReading;
import lejos.robotics.RangeReadings;
import lejos.robotics.RangeScanner;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RotatingRangeScanner;
import lejos.robotics.localization.MCLPoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.mapping.RangeMap;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.robotics.pathfinding.Path;



public class RobotLogger {	
	
	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println("C3PO");
		RobotLogger rl = new RobotLogger();
		rl.map();
	}

	private RegulatedMotor head = Motor.A;
	private UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S2);
	private RangeScanner scanner = new RotatingRangeScanner(head, sensor);
	private DifferentialPilot mp = new DifferentialPilot(56, 120, Motor.B, Motor.C);
	private StringBuilder sb_motor = new StringBuilder("");
	private StringBuilder sb_uss = new StringBuilder("");
	private long initialTime;
	
	
	private void map() throws IOException, InterruptedException{
		initialTime = System.nanoTime();
		
		FileOutputStream fos_motor = new FileOutputStream("motor_log.txt"); 
		OutputStreamWriter out_motor = new OutputStreamWriter(fos_motor, "UTF-8");
		
		FileOutputStream fos_uss = new FileOutputStream("uss_log.txt"); 
		OutputStreamWriter out_uss = new OutputStreamWriter(fos_uss, "UTF-8");
		
		float[] angles = new float[36]; 
		for(int i = 0; i < 36; i++){
			angles[i] = i * 10.0f;
		}
		scanner.setAngles(angles);
		mp.setTravelSpeed(50.0);
		
		String out_string_motor =  "M" + " " 
				  + (System.nanoTime() - initialTime) / 1000000 + " "
		          + Motor.C.getTachoCount() + " "
		          + "a a a " +
		          + Motor.B.getTachoCount() + " "
		          + "a a a a a a a\n";
		sb_motor.append(out_string_motor);
		scan();

		int movements = 5;
		for (int i = 0; i < movements; i++) {
			move(350);
		}
		
		
		
		out_uss.write(sb_uss.toString());
		out_uss.flush();
		out_uss.close();
		
		out_motor.write(sb_motor.toString());
		out_motor.flush();
		out_motor.close();
	}
	
	private void move(double distance) throws InterruptedException {
		mp.travel(distance, true);

		float range = -1;
		while(mp.isMoving()) {
			range = sensor.getRange();
			System.out.println(range);
			Thread.sleep(100);
			if (range <= 20) 
				mp.stop();
		}
		
		
		
		
		String out_string_motor = "M" + " " 
						  + (System.nanoTime() - initialTime) / 1000000 + " "
				          + Motor.C.getTachoCount() + " "
				          + "a a a " +
				          + Motor.B.getTachoCount() + " "
				          + "a a a a a a a\n";
		

		sb_motor.append(out_string_motor); 
		scan();
		
		if (range <= 25f) 
			rotate(90,150);
		
	}
	
	private void rotate(double radius, double distance) {
		mp.travelArc(radius, distance);
		
		String out_string_motor = "M" + " " 
						  + (System.nanoTime() - initialTime) / 1000000 + " "
				          + Motor.C.getTachoCount() + " "
				          + "a a a " +
				          + Motor.B.getTachoCount() + " "
				          + "a a a a a a a\n";
		

		sb_motor.append(out_string_motor); 
		scan();
	}
	
	private void scan() {
		RangeReadings rR = scanner.getRangeValues();
		sb_uss.append("S a ");
		for(RangeReading r : rR){
			sb_uss.append(Math.round((r.getRange()*10)) + " ");
		}
		sb_uss.append("\n");
	}
}
