package de.fhkoeln.gm.ki.alg.reproducers;

import de.fhkoeln.gm.ki.alg.fitnessFunctions.FitnessFunction;
import de.fhkoeln.gm.ki.alg.util.Population;

public class SteadyStateStrongestForWeakest extends AbstractReproducer {
	@Override
	public Population reproduce(Population oldGeneration,
			Population tmpGeneration) {

		FitnessFunction fitnessCalc = new FitnessFunction();
		for (int i = 0; i < tmpGeneration.getCurrentSize(); i++) {
			fitnessCalc.evaluate(tmpGeneration.getIndividualAt(i));
		}

		// Ersetzt die 100 besten Individuen aus 'tmpGenenration' mit den 100 schlechtesten aus der 'oldGeneration'
		for (int i = 0; i < 1; i++) {
			if (tmpGeneration.getFittestIndividual().fitness > oldGeneration.getWeakestIndividual().fitness) {
				oldGeneration.replace(oldGeneration.getWeakestIndividual(), tmpGeneration.getFittestIndividual());
				tmpGeneration.remove(tmpGeneration.getFittestIndividual());
			}
		}

		return oldGeneration;
	}

	@Override
	public String getName() {
		return "SteadyStateStrongestForWeakest";
	}

}
