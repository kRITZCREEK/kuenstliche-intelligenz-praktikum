package de.fhkoeln.gm.ki.alg.genes;

import lejos.nxt.Motor;

public class GeneBPowerForward extends AbstractGene {

	@Override
	public char getName() {
		return 'P';
	}

	@Override
	public String getDescription() {
		return "Gene B Powerforward";
	}

	@Override
	public float execute() {
		Motor.B.rotate(90);
		return 0f;
	}

}
