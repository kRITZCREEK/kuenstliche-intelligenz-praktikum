package de.fhkoeln.gm.ki.alg.recombiners;

import java.util.ArrayList;
import java.util.Random;

import de.fhkoeln.gm.ki.alg.genes.AbstractGene;
import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.alg.util.Population;

public class OnePointCrossover extends AbstractRecombiner {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Population recombine(Population currentPopulation) {
		
		int crossPoint = new Random().nextInt(5) + 1;
		
		for (int i = 0; i < (currentPopulation.getCurrentSize() / 2); i++) {
			
			ArrayList<AbstractGene> fatherGenes = currentPopulation.getIndividualAt(i * 2).getGenes();
			ArrayList<AbstractGene> motherGenes = currentPopulation.getIndividualAt(i * 2 + 1).getGenes();
			
			ArrayList<AbstractGene> childOneGenes = (ArrayList<AbstractGene>) fatherGenes.subList(0, crossPoint);
			childOneGenes.addAll(motherGenes.subList(crossPoint, 7));
			
			ArrayList<AbstractGene> childTwoGenes = (ArrayList<AbstractGene>) motherGenes.subList(0, crossPoint);
			childTwoGenes.addAll(motherGenes.subList(crossPoint, 7));
			
			currentPopulation.replace(currentPopulation.getIndividualAt(i * 2), new Individual(childOneGenes));
			currentPopulation.replace(currentPopulation.getIndividualAt(i * 2 + 1), new Individual(childTwoGenes));
			
		}
		
		return currentPopulation;
	}

}
