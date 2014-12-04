package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneBBackward extends AbstractGene {

	@Override
	public char getName() {
		return 'b';
	}

	@Override
	public String getDescription() {
		return "Gene B Backward";
	}

	@Override
	public float execute() {
		Motor.B.rotate(-45);
		return 0f;
	}

}
