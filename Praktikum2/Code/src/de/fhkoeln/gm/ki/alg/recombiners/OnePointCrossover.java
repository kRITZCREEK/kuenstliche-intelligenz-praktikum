package de.fhkoeln.gm.ki.alg.recombiners;

import java.util.ArrayList;
import java.util.Random;
import de.fhkoeln.gm.ki.alg.genes.AbstractGene;
import de.fhkoeln.gm.ki.alg.util.Population;

public class OnePointCrossover extends AbstractRecombiner {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Population recombine(Population currentPopulation) {
		int crossPoint = new Random().nextInt(5)+1;
		for (int i = 0; i < (currentPopulation.getCurrentSize()/2); i++) {
			ArrayList<AbstractGene> fatherGenes = currentPopulation.getIndividualAt(i*2).getGenes();
			ArrayList<AbstractGene> motherGenes = currentPopulation.getIndividualAt(i*2+1).getGenes();
			ArrayList<AbstractGene> childOne = (ArrayList<AbstractGene>) fatherGenes.subList(0, crossPoint);
			ArrayList<AbstractGene> childTwo
		}
		
		return currentPopulation;
	}

}
