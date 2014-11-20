package de.fhkoeln.gm.ki.alg.selectors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhkoeln.gm.ki.alg.util.DataSource;
import de.fhkoeln.gm.ki.alg.util.Population;

/***
 * Abstract Class for Selectors, also handles GUI integration
 * @author Sascha Schewe
 *
 */
public abstract class AbstractSelector implements ActionListener {

	public AbstractSelector(){
		DataSource.getInstance().currentSelector = this;
	}
	
	public abstract Population selectFromPopulation(Population currentGen);
	
	public abstract String getName();


	public final void actionPerformed(ActionEvent e) {
		DataSource.getInstance().currentSelector=this;
		
	}


}
