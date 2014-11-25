package de.fhkoeln.gm.ki.alg.recombiners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.fhkoeln.gm.ki.alg.genes.AbstractGene;
import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.alg.util.Population;

public class OnePointCrossover extends AbstractRecombiner {

	@Override
	public String getName() {
		return "OnePointCrossover";
	}

	@Override
	public Population recombine(Population currentPopulation) {
		Population rtn = new Population();
		int crossPoint = new Random().nextInt(5) + 1;
		
		for (int i = 0; i < (currentPopulation.getCurrentSize() / 2); i++) {
			
			ArrayList<AbstractGene> fatherGenes = currentPopulation.getIndividualAt(i * 2).getGenes();
			ArrayList<AbstractGene> motherGenes = currentPopulation.getIndividualAt(i * 2 + 1).getGenes();
			
			
			
			ArrayList<AbstractGene> childOneGenes = toArrayList(fatherGenes.subList(0, crossPoint));
			childOneGenes.addAll(motherGenes.subList(crossPoint, 7));
			
			ArrayList<AbstractGene> childTwoGenes = toArrayList(motherGenes.subList(0, crossPoint));
			childTwoGenes.addAll(fatherGenes.subList(crossPoint, 7));
			

			rtn.add(new Individual(childOneGenes));
			rtn.add(new Individual(childTwoGenes));
		}
		
		return rtn;
	}
	

	
	private ArrayList<AbstractGene> toArrayList(List<AbstractGene> genes) {
		return new ArrayList<AbstractGene>(genes);
	}
	

}
