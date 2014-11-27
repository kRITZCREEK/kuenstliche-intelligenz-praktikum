package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneA extends AbstractGene {

	@Override
	public char getName() {
		return 'A';
	}

	@Override
	public String getDescription() {
		return "A";
	}

	@Override
	public float execute() {
		System.out.println(Motor.A.getTachoCount());
		Motor.A.rotate(45);
//		Motor.A.rotateTo(180);
//		Motor.B.rotateTo(180);
		return 0f;
	}

}
