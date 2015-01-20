import java.util.ArrayList;
import java.util.List;

import lejos.geom.Line;
import lejos.geom.Rectangle;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.localization.MCLPoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.robotics.pathfinding.Path;



public class Mapper {	
	
	public static void main(String[] args){
		System.out.println("C3PO");
		
		Mapper m = new Mapper();
		m.map();
	}
	
	private Navigator navigator;

	public LineMap map() {
		boolean karteFertig = false;
		
		LineMap karte = new LineMap();
		
		while (!karteFertig) {
			List<Line> kanten = scanneUmgebung();
			List<Line> neueKanten = diff(karte, kanten);
			karte = add(karte, neueKanten);
			Pose pose = bestimmmePose(karte, kanten);
			karteFertig = istGeschlosseneKarte(karte);
			Path weg = findeWeg(karte, pose);
			bewege(weg);
			
		}
		return karte;
		/*
		 *abgeschlosseneUmgebung = false
    	karte <- [ ] : [Kante]
    		while(!abgeschlosseneUmgebung)
      			kanten <- scanneUmgebung() : [Kante]
			      neueKanten <- diff(karte, kanten)
			      add(karte, neueKanten) : void
			      position <- bestimmePosition(karte, kanten) : Pose 
			      zeichneKarte(karte, position) : void
			      abgeschlosseneUmgebung = hatKeineLuecken(karte) : bool
			      pfadZuLuecke <- findePfad(karte, position) : Pfad
			      bewege(pfadZuLuecke) : void
		 */
	}

	private boolean istGeschlosseneKarte(LineMap karte) {
		Rectangle boundingRect = karte.getBoundingRect();
		float x1 = boundingRect.x;
		float x2 = x1 + boundingRect.width;
		float y1 = boundingRect.y;
		float y2 = y1 + boundingRect.height;
		
		Line l1 = new Line(x1, y1, x2, y1);
		Line l2 = new Line(x1, y2, x2, y2);
		Line l3 = new Line(x1, y1, x1, y2);
		Line l4 = new Line(x2, y1, x2, y2);
		for(Line l : karte.getLines())
			System.out.println("IMPLEMENTIEREN");
		return false;
	}

	private void bewege(Path weg) {
		this.navigator.followPath(weg);
		this.navigator.waitForStop();
		return;
	}

	private Path findeWeg(LineMap karte, Pose pose) {
		// TODO Auto-generated method stub
		return null;
	}

	// Pose = Standort aus x- und y-Koordinate, sowie die Ausrichtung
	private Pose bestimmmePose(LineMap karte, List<Line> kanten) {
		// TODO Auto-generated method stub
		MCLPoseProvider pp = new MCLPoseProvider(karte, 0, 0);
		
		return null;
	}

	private LineMap add(LineMap karte, List<Line> neueKanten) {
		 Line [] kanten = karte.getLines();
		 Rectangle rect = karte.getBoundingRect();
		 
		 Line [] newKarte = new Line [kanten.length + neueKanten.size()];
		 
		 for (int i = 0; i < newKarte.length; i++){
			 if(i < kanten.length){
				 newKarte[i] = kanten[i];
			 }
			 else{
				 newKarte[i] = neueKanten.get(i-kanten.length);
			 }
		 }
		return new LineMap(newKarte, rect);
	}

	private List<Line> diff(LineMap karte, List<Line> kanten) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Line> scanneUmgebung() {
		
		int POINTS = 18;
		int ANGLEPERPOINT = 360 / POINTS;
		
		List<Line> rtn = new ArrayList<Line>();
		
		UltrasonicSensor USS = new UltrasonicSensor(SensorPort.S2);
		RangeFeatureDetector detector = new RangeFeatureDetector(USS, 1000, 10);
		
		int angle = 0;
		float firstrange = -1;
		float secondrange = -1;
		
		while(angle < POINTS) {
			Feature f = detector.scan();
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
		
		return rtn;
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
