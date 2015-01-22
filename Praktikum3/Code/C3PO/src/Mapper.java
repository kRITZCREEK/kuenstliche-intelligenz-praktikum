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
		
		LineMap map = new LineMap(scanneUmgebung(), new Rectangle(1000.0f, 1000.0f, 1000.0f, 1000.0f));
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
	
	
private Line[] scanneUmgebung() {
		
		int POINTS = 18;
		int ANGLEPERPOINT = 360 / POINTS;
		
		List<Line> rtn = new ArrayList<Line>();
		
		UltrasonicSensor USS = new UltrasonicSensor(SensorPort.S2);
		RangeFeatureDetector detector = new RangeFeatureDetector(USS, 100, 250);
		
		int angle = 0;
		float firstrange = -1;
		float secondrange = -1;
		
		while(angle < POINTS) {
			Feature f = detector.scan();
			if(f == null)
				continue;
			try {
				if (firstrange == -1) 
					firstrange = f.getRangeReading().getRange();
				else if (secondrange == -1)
					secondrange = f.getRangeReading().getRange();
				else {
					
					int currentangle = (angle - 1) * ANGLEPERPOINT;
					int lastangle = (angle - 2) * ANGLEPERPOINT;
					
					rtn.add(this.erzeugeLinie(currentangle, secondrange, lastangle, firstrange));
					firstrange = -1;
					secondrange = -1;
				}
				
			} finally {
				
			}
			Motor.A.rotate(20);
			angle++;
		}
		for (int i = 0; i < rtn.size(); i++) {
			System.out.println("(" + rtn.get(i).x1 + "|" + rtn.get(i).y1 + ")(" + rtn.get(i).x2 + "|" + rtn.get(i).y2 + ")");
		}
		Motor.A.rotate(-360);
		
		return (Line[]) rtn.toArray();
	}
	
	private Line erzeugeLinie(float range1, float angle1, float range2, float angle2) {
		float[] p1 = berechneKoordinaten(range1, angle1);
		float[] p2 = berechneKoordinaten(range2, angle2);
		
		return new Line(p1[0],p1[1],p2[0],p2[1]);
	}
	
	private float[] berechneKoordinaten(float range, float angle) {
		// rtn[0] = x 
		// rtn[1] = y
		
		double a,b,c;
		double alpha,
			  beta,
			  gamma;

		if (angle < 91) {
			c = range;
			beta = angle;
			gamma = 90;
			alpha = 180 - (gamma + beta);
			a = Math.sin(alpha) * c;
			b = Math.cos(alpha) * c;
			return new float[] { (float)a, (float)b };
		} else if (angle < 181) {
			c = range;
			beta = angle - 90;
			gamma = 90;
			alpha = 180 - (gamma + beta);
			a = Math.sin(alpha) * c;
			b = Math.cos(alpha) * c;
			return new float[] { (float)a, -(float)b };
		} else if (angle < 271) {
			c = range;
			beta = angle - 180;
			gamma = 90;
			alpha = 180 - (gamma + beta);
			a = Math.sin(alpha) * c;
			b = Math.cos(alpha) * c;
			return new float[] { -(float)a, -(float)b };
		} else {
			c = range;
			beta = angle - 270;
			gamma = 90;
			alpha = 180 - (gamma + beta);
			a = Math.sin(alpha) * c;
			b = Math.cos(alpha) * c;
			return new float[] { -(float)a, (float)b };
		}
		
	}
	
}