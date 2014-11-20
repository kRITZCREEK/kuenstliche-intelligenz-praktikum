package de.fhkoeln.gm.ki.alg.mutators;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhkoeln.gm.ki.alg.genes.AbstractGene;
import de.fhkoeln.gm.ki.alg.util.DataSource;
import de.fhkoeln.gm.ki.alg.util.Population;

/***
 * Abstract Class for Mutators, also handles GUI integration
 * @author Sascha Schewe
 *
 */
public abstract class AbstractMutator implements ActionListener{
	
	public AbstractMutator(){
		DataSource.getInstance().currentMutator = this;
	}

	/**
	 * Mutates a population using the viable Genes
	 * @param tmpPopulation the Population to mutate
	 * @param viableGenes the set of genes that are allowed as replacement genes
	 * @return the mutated population
	 */
	public abstract Population mutate(Population tmpPopulation, AbstractGene[] viableGenes);
	
	public abstract String getName();
	

	
	
	public final void actionPerformed(ActionEvent e) {
		DataSource.getInstance().currentMutator=this;
		
	}

}
