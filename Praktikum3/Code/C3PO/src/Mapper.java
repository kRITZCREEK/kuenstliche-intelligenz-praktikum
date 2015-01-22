import java.util.ArrayList;
import java.util.List;

import lejos.geom.Line;
import lejos.geom.Point;
import lejos.geom.Rectangle;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RangeFinder;
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



public class Mapper {	
	
	public static void main(String[] args){
		System.out.println("C3PO");
		
		Mapper m = new Mapper();
		System.out.println(m.map());
	}

	private RegulatedMotor head = Motor.A;
	private RangeFinder rangeFinder = new UltrasonicSensor(SensorPort.S2);
	private RangeScanner scanner = new RotatingRangeScanner(head, rangeFinder);
	private MoveProvider mp = new DifferentialPilot(59, 120, Motor.B, Motor.C);
	
	private LineMap map(){
		int numParticles = 1000;
		int border = 20;
		
		Pose curPose = new Pose();
		RangeReadings rR = null; 
		
		LineMap map = new LineMap();
		MCLPoseProvider provider = new MCLPoseProvider(mp, scanner, map , numParticles, border);
		provider.generateParticles();
		//provider.getReadings();
		while(provider.incompleteRanges()){
			if(provider.update()){
				curPose = provider.getEstimatedPose();
				rR = provider.getRangeReadings();
			}
			System.out.println(curPose);
			System.out.println(rR);
		}
		return map;
	}
	
}