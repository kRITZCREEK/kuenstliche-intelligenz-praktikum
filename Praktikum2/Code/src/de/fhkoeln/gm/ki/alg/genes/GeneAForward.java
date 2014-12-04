package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneAForward extends AbstractGene {

	@Override
	public char getName() {
		return 'A';
	}

	@Override
	public String getDescription() {
		return "Gene A Forward";
	}

	@Override
	public float execute() {
		Motor.A.rotate(15);
		return 0f;
	}

}
