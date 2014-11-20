package de.fhkoeln.gm.ki.alg.reproducers;

import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.alg.util.Population;

public class SteadyStateFullRandom extends AbstractReproducer {

	@Override
	public Population reproduce(Population oldGeneration,
			Population tmpGeneration) {
		Individual randOld = oldGeneration.getIndividualAt((int)Math.random()*oldGeneration.getCurrentSize());
		Individual randTmp = tmpGeneration.getIndividualAt((int)Math.random()*tmpGeneration.getCurrentSize());
		
		oldGeneration.replace(randOld, randTmp);
		return oldGeneration;
	}

	@Override
	public String getName() {
		return "Steady State Full Random";
	}

}
