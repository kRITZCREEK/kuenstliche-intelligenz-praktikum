package de.fhkoeln.gm.ki.alg.util;

import java.util.ArrayList;
import java.util.Collections;

/***
 * Offers the basic functions of a population
 * @author Sascha Schewe
 *
 */
public class Population {
	
	private ArrayList<Individual> population;
	private IndividualComparator comparator;
	private boolean sizelocked = false;
	private boolean sorted = false;
	private int size;
	
	public Population(ArrayList<Individual> population){
		this.population = population;
		comparator = new IndividualComparator();
		size = population.size();
		sizelocked=true;
	}
	public Population(){
		population = new ArrayList<Individual>();
		comparator = new IndividualComparator();
		sizelocked=false;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Individual> getPop(){
		return (ArrayList<Individual>) population.clone();
	}
	
	public void sort(){
		Collections.sort(population, comparator);
		sorted=true;
	}
	
	public void setMaximumPopulationSize(int size){
		this.size = size;
	}
	
	/**
	 * Lock to prevent new Individuals from being added above the current (or specified) number
	 * @param locked
	 */
	public void setLocked(boolean locked){
		this.sizelocked = locked;
	}
	/**
	 * Add an individual
	 * @param ind
	 * @throws UnsupportedOperationException if the population is locked and full
	 */
	public void add(Individual ind){
		if(sizelocked&&population.size() >= size)
			throw new UnsupportedOperationException("Population is sizelocked and maximum population size has been reached");
		sorted=false;
		population.add(ind);
	}
	
	public void remove(Individual ind){
		sorted=false;
		population.remove(ind);
	}
	
	public void replace(Individual oldInd, Individual newInd){
		remove(oldInd);
		add(newInd);
		sorted=false;
	}
	
	public Individual getFittestIndividual(){
		if(!sorted)
			sort();
		return population.get(0);
	}
	
	public Individual getWeakestIndividual(){
		if(!sorted)
			sort();
		return population.get(population.size()-1);
	}
	public Individual getIndividualAt(int position){
		return population.get(position);
	}
	
	public float getMaximumFitness(){
		if(!sorted)
			sort();
		return population.get(0).fitness;
	}
	
	public float getMinimumFitness(){
		if(!sorted)
			sort();
		return population.get(population.size()-1).fitness;
	}
	
	public int getCurrentSize(){
		return population.size();
	}
	public int getMaximumSize(){
		return size;
	}
	public boolean getLockedState(){
		return sizelocked;
	}
	
	/***
	 * Calculates the average Fitness of the population
	 * IGNORES non-evaluated Individuals
	 * @return the average Fitness, or negative Infinity if the population has no Fitness
	 */
	public float getAverageFitness(){
		float sum=0;
		float tmpAvg=0;
		int entriesToIgnore=0;
		for(int i=0; i< population.size();i++){
			tmpAvg = population.get(i).fitness;
			if(tmpAvg == Float.NEGATIVE_INFINITY){
				entriesToIgnore++;
				continue;
			}
			sum= sum + tmpAvg;
		}
		if(entriesToIgnore==population.size())
			return Float.NEGATIVE_INFINITY;
		return sum/(population.size()-entriesToIgnore);
	}
}
