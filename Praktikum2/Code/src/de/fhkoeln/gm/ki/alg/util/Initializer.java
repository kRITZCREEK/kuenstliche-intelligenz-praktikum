package de.fhkoeln.gm.ki.alg.util;

import java.util.ArrayList;

import de.fhkoeln.gm.ki.alg.genes.AbstractGene;

public class Initializer {
	
	/***
	 * Generates a new random Population, not suited for permutation-GAs (TSP solver etc.)
	 * @param populationSize population size
	 * @param individualSize size of each individual
	 * @param applicableGenes Array of all Genes that should be used for generation
	 * @return a randomized population
	 */
	public Population initialize(int populationSize, int individualSize, AbstractGene[] applicableGenes){
		ArrayList<Individual> population = new ArrayList<Individual>();
		int maxRand = applicableGenes.length;
		int i2=0;
		ArrayList<AbstractGene> tmpInd = new ArrayList<AbstractGene>();
		for(int i=0;i<populationSize;i++){
			tmpInd.clear();
			for(i2=0;i2<individualSize;i2++){
				tmpInd.add(applicableGenes[(int) (Math.floor(Math.random()*maxRand))]);
			}
			Individual tmp = new Individual(tmpInd);
			population.add(tmp);
		}
		
		Population newPop = new Population(population);
		return newPop;
	}

}
