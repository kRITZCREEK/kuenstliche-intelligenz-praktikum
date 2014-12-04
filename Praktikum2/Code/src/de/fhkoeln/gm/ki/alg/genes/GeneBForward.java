package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneBForward extends AbstractGene {

	@Override
	public char getName() {
		return 'B';
	}

	@Override
	public String getDescription() {
		return "Gene B Forward";
	}

	@Override
	public float execute() {
		Motor.B.rotate(45);
		return 0f;
	}

}
