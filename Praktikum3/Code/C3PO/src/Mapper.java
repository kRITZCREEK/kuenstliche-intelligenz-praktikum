import java.util.List;

import lejos.geom.Line;
import lejos.geom.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;
import lejos.robotics.pathfinding.Path;



public class Mapper {
	
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
		return null;
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
		// TODO Auto-generated method stub
		
	}

	private Path findeWeg(LineMap karte, Pose pose) {
		// TODO Auto-generated method stub
		return null;
	}

	private Pose bestimmmePose(LineMap karte, List<Line> kanten) {
		// TODO Auto-generated method stub
		return null;
	}

	private LineMap add(LineMap karte, List<Line> neueKanten) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Line> diff(LineMap karte, List<Line> kanten) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Line> scanneUmgebung() {
		// TODO Auto-generated method stub
		return null;
	}

}
