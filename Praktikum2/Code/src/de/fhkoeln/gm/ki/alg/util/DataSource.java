package de.fhkoeln.gm.ki.alg.util;

import java.util.ArrayList;

import de.fhkoeln.gm.ki.alg.GeneticAlgorithm;
import de.fhkoeln.gm.ki.alg.fitnessFunctions.DummyFitness;
import de.fhkoeln.gm.ki.alg.fitnessFunctions.FitnessFunction;
import de.fhkoeln.gm.ki.alg.fitnessFunctions.AbstractFitness;
import de.fhkoeln.gm.ki.alg.genes.AbstractGene;
import de.fhkoeln.gm.ki.alg.genes.TestGeneA;
import de.fhkoeln.gm.ki.alg.genes.TestGeneB;
import de.fhkoeln.gm.ki.alg.genes.TestGeneC;
import de.fhkoeln.gm.ki.alg.genes.TestGeneD;
import de.fhkoeln.gm.ki.alg.mutators.AbstractMutator;
import de.fhkoeln.gm.ki.alg.mutators.RandomResetter;
import de.fhkoeln.gm.ki.alg.recombiners.AbstractRecombiner;
import de.fhkoeln.gm.ki.alg.recombiners.OnePointCrossover;
import de.fhkoeln.gm.ki.alg.reproducers.FullReplacement;
import de.fhkoeln.gm.ki.alg.reproducers.AbstractReproducer;
import de.fhkoeln.gm.ki.alg.reproducers.SteadyStateFullRandom;
import de.fhkoeln.gm.ki.alg.reproducers.SteadyStateStrongestForWeakest;
import de.fhkoeln.gm.ki.alg.selectors.AbstractSelector;
import de.fhkoeln.gm.ki.alg.selectors.WeightedProbabilitySelector;


/***
 * Saves initial Data such as known and selected components to pass to the GA.
 * Also used by the GA to pass information back to the GUI.
 * @author Sascha Schewe
 *
 */
public class DataSource {

	
	private ArrayList<AbstractGene> genes;
	private ArrayList<AbstractFitness> fitnessFunctions;
	private ArrayList<AbstractMutator> mutators;
	private ArrayList<AbstractReproducer> reproducers;
	private ArrayList<AbstractSelector> selectors;
	private ArrayList<AbstractRecombiner> recombiners;
	private Population pop;
	private AbstractGene[] viableGenes;
	
	private static DataSource self;
	
	public AbstractFitness currentFitness;
	public AbstractMutator currentMutator;
	public AbstractReproducer currentReproducer;
	public AbstractSelector currentSelector;
	public AbstractRecombiner currentRecombiner;
	public GeneticAlgorithm currentGeneticAlgorithm;
	
	public static String BOTNAME = "Unknown";
	private DataSource(){}
	
	public static DataSource getInstance(){
		if(self==null)
			self=new DataSource();
		return self;
	}
	
	private DataSource(ArrayList<AbstractGene> genes, ArrayList<AbstractFitness> fitnessFunctions, ArrayList<AbstractMutator> mutators, ArrayList<AbstractReproducer> reproducers){
		this.genes = genes;
//		this.population = genomes;
		this.fitnessFunctions = fitnessFunctions;
		this.mutators = mutators;
		this.reproducers = reproducers;
	}
	
	public static DataSource getDataSource(ArrayList<AbstractGene> genes, ArrayList<AbstractFitness> fitnessFunctions, ArrayList<AbstractMutator> mutators, ArrayList<AbstractReproducer> reproducers){
		if(self==null)
			self= new DataSource(genes,fitnessFunctions,mutators,reproducers);
		return self;
	}
	
	public ArrayList<AbstractGene> getGenes(){
		return genes;
	}
	
//	public ArrayList<Individual> getPopulation(){
//		return population;
//	}
	public Population getPopulation(){
		return pop;
	}
	public void setPopulation(Population population){
		pop = population;
	}
	
	public ArrayList<AbstractFitness> getFitnessFunctions(){
		return fitnessFunctions;
	}
	
	public ArrayList<AbstractSelector> getSelectors(){
		return selectors;
	}
	
	public ArrayList<AbstractRecombiner> getRecombiners(){
		return recombiners;
	}
	
	public ArrayList<AbstractMutator> getMutators(){
		return mutators;
	}
	
	public ArrayList<AbstractReproducer> getReproducers(){
		return reproducers;
	}
	
	public void setViableGenes(AbstractGene[] viableGenes){
		this.viableGenes = viableGenes;
	}
	
	public AbstractGene[] getViableGenes(){
		return viableGenes;
	}

	/***
	 * Only necessary if the Lists aren't passed via Constructor.
	 * In which case please implement.
	 */
	public void initialize(){
		genes = new ArrayList<AbstractGene>();
		fitnessFunctions = new ArrayList<AbstractFitness>();
		mutators = new ArrayList<AbstractMutator>();
		reproducers = new ArrayList<AbstractReproducer>();
		recombiners = new ArrayList<AbstractRecombiner>();
		selectors = new ArrayList<AbstractSelector>();
		genes.add(new TestGeneA());
		genes.add(new TestGeneB());
		genes.add(new TestGeneC());
		genes.add(new TestGeneD());
		fitnessFunctions.add(new DummyFitness());
		fitnessFunctions.add(new FitnessFunction());
		mutators.add(new RandomResetter());
		reproducers.add(new FullReplacement());
		reproducers.add(new SteadyStateStrongestForWeakest());
		reproducers.add(new SteadyStateFullRandom());
		recombiners.add(new OnePointCrossover());
		selectors.add(new WeightedProbabilitySelector());

		//throw new UnsupportedOperationException("Initialize needs to be implemented before you use it!");
	}
}