package de.fhkoeln.gm.ki.alg.reproducers;

import de.fhkoeln.gm.ki.alg.util.Population;

public class SteadyStateStrongestForWeakest extends AbstractReproducer{

	@Override
	public Population reproduce(Population oldGeneration,
			Population tmpGeneration) {


		// Wenn das stärkste Individuum der TMP-Generation schwächer oder gleichschwach dem schwächstem Individuum der 
		// alten Generation ist, wird die Schleife unterbrochen
		// ODER
		// Die Schleife läuft so lange, bis alle mitglieder in oldGeneration Stärker sind als die von tmpGeneration
		while(tmpGeneration.getFittestIndividual().fitness > oldGeneration.getWeakestIndividual().fitness) {
			
			// Ansonsten muss das schwächste Individuum der alten Generation dem stärksten der TMP-Armada weichen.
			oldGeneration.replace(oldGeneration.getWeakestIndividual(), tmpGeneration.getFittestIndividual());
			tmpGeneration.remove(tmpGeneration.getFittestIndividual());
			
		}
		
		return oldGeneration;
	}

	@Override
	public String getName() {
		return "SteadyStateStrongestForWeakest";
	}

	
}
