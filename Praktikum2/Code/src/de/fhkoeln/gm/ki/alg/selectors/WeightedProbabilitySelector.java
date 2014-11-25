package de.fhkoeln.gm.ki.alg.selectors;

import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.alg.util.Population;

public class WeightedProbabilitySelector extends AbstractSelector {

	public static final double SURVIVALRATE = 1; // Fürs Erste
	@Override
	public Population selectFromPopulation(Population currentGen) {
		Population population = new Population();
		currentGen.sort();
		double stopVal;
		double accumulator;
		double sumFitness = currentGen.getAverageFitness()*currentGen.getCurrentSize();
		for(int i = 0; i < (int) (currentGen.getCurrentSize() * SURVIVALRATE); i++){
			stopVal = sumFitness * Math.random();
			accumulator = 0;
			for(int j = 0; j < currentGen.getCurrentSize(); j++){
				Individual individual = currentGen.getIndividualAt(j);
				if(j-1 == currentGen.getCurrentSize()){
					//Bevor gar kein Individuum hinzugefügt wird nehmen wir das Letzte.
					//Nur zur Sicherheit
					//TODO: Braucht nochmal ein paar Gedanken
					population.add(individual);
					break;
				}
				accumulator += individual.fitness;
				if (accumulator >= stopVal){
					population.add(individual);
					break;
				}
			}						
		}
		return population;
	}

	@Override
	public String getName() {
		return "WeightedProbabilitySelector";
	}

}
