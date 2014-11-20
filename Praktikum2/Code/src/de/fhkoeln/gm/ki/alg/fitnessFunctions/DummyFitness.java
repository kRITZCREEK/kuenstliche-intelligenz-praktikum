package de.fhkoeln.gm.ki.alg.fitnessFunctions;

import de.fhkoeln.gm.ki.alg.util.Individual;

public class DummyFitness extends AbstractFitness {

	@Override
	public float evaluate(Individual genome) {
		return 100;
	}

	@Override
	public boolean thresholdReached() {
		return false;
	}

	@Override
	public String getName() {
		return "DummyFitness";
	}

}
