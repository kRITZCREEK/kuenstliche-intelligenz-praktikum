package de.fhkoeln.gm.ki.alg.fitnessFunctions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhkoeln.gm.ki.alg.util.DataSource;
import de.fhkoeln.gm.ki.alg.util.Individual;

/***
 * Abstract Class for Fitness Functions, also handles GUI integration
 * @author Sascha Schewe
 *
 */
public abstract class AbstractFitness implements ActionListener {

	public AbstractFitness(){
		DataSource.getInstance().currentFitness = this;
	}
	public abstract float evaluate(Individual genome);
	public abstract boolean thresholdReached();
	
	public abstract String getName();
	
	public final void actionPerformed(ActionEvent e) {
		DataSource.getInstance().currentFitness=this;
		
	}

}
