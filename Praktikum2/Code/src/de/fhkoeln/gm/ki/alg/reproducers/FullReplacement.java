package de.fhkoeln.gm.ki.alg.reproducers;

import de.fhkoeln.gm.ki.alg.util.Population;

public class FullReplacement extends AbstractReproducer{

	@Override
	public Population reproduce(Population oldGeneration,
			Population tmpGeneration) {
		
		return tmpGeneration; // W O W
	}

	@Override
	public String getName() {
		return "FullReplacement";
	}

}
