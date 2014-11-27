package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneB extends AbstractGene {

	@Override
	public char getName() {
		return 'B';
	}

	@Override
	public String getDescription() {
		return "B";
	}

	@Override
	public float execute() {
		Motor.B.rotate(45);
		return 0f;
	}

}
