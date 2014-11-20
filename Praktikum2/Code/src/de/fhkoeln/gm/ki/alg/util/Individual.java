package de.fhkoeln.gm.ki.alg.util;

import java.util.ArrayList;

import de.fhkoeln.gm.ki.alg.genes.AbstractGene;

/***
 * Wrapper for the Gene sequence and a fitness score
 * @author Sascha Schewe
 *
 */
public class Individual {

	public float fitness=Float.NEGATIVE_INFINITY;
	private ArrayList<AbstractGene> genes;
	private String descriptor="";
	
	@SuppressWarnings("unused")
	private Individual() {
	}
	
	@SuppressWarnings("unchecked")
	public Individual(ArrayList<AbstractGene> genes){
		this.genes = (ArrayList<AbstractGene>) genes.clone();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AbstractGene> getGenes(){
		return (ArrayList<AbstractGene>) genes.clone();
	}
	
	//Execute the genome
	public float execute(){
		int length = genes.size();
		float currentReturn=0;
		for(int i=0;i<length;i++)
			currentReturn += genes.get(i).execute();
		return currentReturn;
	}
	
	public String toString(){
		if(descriptor.equals("")){
			int length = genes.size();
			for(int i=0;i<length;i++)
				descriptor += genes.get(i).getName()+":";
		}
		return descriptor + fitness;
	}
	
	@Override
	public boolean equals(Object other){
		if(other==null) return false;
		if(other==this) return true;
		if(!(other instanceof Individual)) return false;
		
		Individual otherInd = (Individual) other;
		if (this.genes.size()!= otherInd.genes.size())
			return false;
		for(int i=0;i<genes.size();i++){
			if(!genes.get(i).equals(otherInd.genes.get(i)))
				return false;
		}
			return true;
	}
}


