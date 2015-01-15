import java.util.List;

import lejos.geom.Line;
import lejos.geom.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.pathfinding.Path;



public class Mapper {
	
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
		// TODO Auto-generated method stub
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

	private Pose bestimmmePose(LineMap karte, List<Line> kanten) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
