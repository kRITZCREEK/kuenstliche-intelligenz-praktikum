package de.fhkoeln.gm.ki.alg.util;

import java.util.Comparator;

/***
 * Compares individuals by their fitness score
 * @author Sascha Schewe
 *
 */
public class IndividualComparator implements Comparator<Individual> {

	@Override
	public int compare(Individual arg0, Individual arg1) {
		return (int)(arg1.fitness*100-arg0.fitness*100);
	}

}
