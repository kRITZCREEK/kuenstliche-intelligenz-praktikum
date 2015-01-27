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
	
	public static void main(String[] args) throws IOException{
		System.out.println("C3PO");
		RobotLogger rl = new RobotLogger();
		rl.map();
	}

	private RegulatedMotor head = Motor.A;
	private UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S2);
	private RangeScanner scanner = new RotatingRangeScanner(head, sensor);
	private DifferentialPilot mp = new DifferentialPilot(59, 120, Motor.B, Motor.C);
	
	
	private void map() throws IOException{
		long initialTime = System.nanoTime();
		
		FileOutputStream fos_motor = new FileOutputStream("motor_log.txt"); 
		OutputStreamWriter out_motor = new OutputStreamWriter(fos_motor, "UTF-8");
		
		FileOutputStream fos_uss = new FileOutputStream("uss_log.txt"); 
		OutputStreamWriter out_uss = new OutputStreamWriter(fos_uss, "UTF-8");
		
		StringBuilder sb_motor = new StringBuilder("M" + " " 
				  + (System.nanoTime() - initialTime) / 1000000 + " "
		          + Motor.C.getTachoCount() + " "
		          + "a a a " +
		          + Motor.B.getTachoCount() + " "
		          + "a a a a a a a\n");
		StringBuilder sb_uss = new StringBuilder("");
		
		float [] angles = new float[36]; 
		for(int i = 0; i < 36; i++){
			if (i==0){
				angles[i] = 0.0f;
			}
			else{
				angles[i] = angles[i-1] + 10.0f;
			}
				
		}
		scanner.setAngles(angles);
		Pose curPose = new Pose();
		RangeReadings rR = null;

		mp.setTravelSpeed(50.0);
		
		for(int j = 0; j < 5; j++){
			rR = scanner.getRangeValues();
			sb_uss.append("S ");
			for(RangeReading r : rR){
				System.out.println(r.getAngle() + ", " + r.getRange());
				if(r.getRange()== -1){
					sb_uss.append((2550) + " ");
				}
				else{
					sb_uss.append(Math.round((r.getRange()*10)) + " ");
				}	
			}
			sb_uss.append("\n");
			System.out.println("Motor B: " + Motor.B.getTachoCount());
			System.out.println("Motor C: " + Motor.C.getTachoCount());
			
			mp.travel(10.0);
			
			String out_string_motor = "M" + " " 
							  + (System.nanoTime() - initialTime) / 1000000 + " "
					          + Motor.C.getTachoCount() + " "
					          + "a a a " +
					          + Motor.B.getTachoCount() + " "
					          + "a a a a a a a\n";
			sb_motor.append(out_string_motor); 			
			
			System.out.println("Motor B: " + Motor.B.getTachoCount());
			System.out.println("Motor C: " + Motor.C.getTachoCount());
			
		}
		out_motor.write(sb_motor.toString());
		out_motor.flush();
		out_motor.close();
		
		rR = scanner.getRangeValues();
		sb_uss.append("S ");
		for(RangeReading r : rR){
			System.out.println(r.getAngle() + ", " + r.getRange());
			sb_uss.append(Math.round((r.getRange()*10)) + " ");
		}
		sb_uss.append("\n");
		
		out_uss.write(sb_uss.toString());
		out_uss.flush();
		out_uss.close();
	
	}
}