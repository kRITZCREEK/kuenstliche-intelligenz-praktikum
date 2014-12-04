package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneABackward extends AbstractGene {

	@Override
	public char getName() {
		return 'a';
	}

	@Override
	public String getDescription() {
		return "Gene A Backward";
	}

	@Override
	public float execute() {
		Motor.A.rotate(-45);
		return 0f;
	}

}
