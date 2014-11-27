package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneC extends AbstractGene {

	@Override
	public char getName() {
		return 'C';
	}

	@Override
	public String getDescription() {
		return "C";
	}

	@Override
	public float execute() {
		Motor.A.rotate(-45);
		return 0f;
	}

}
