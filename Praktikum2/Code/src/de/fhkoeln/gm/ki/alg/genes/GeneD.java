package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneD extends AbstractGene {

	@Override
	public char getName() {
		return 'D';
	}

	@Override
	public String getDescription() {
		return "D";
	}

	@Override
	public float execute() {
		Motor.B.rotate(-45);
		return 0f;
	}

}
