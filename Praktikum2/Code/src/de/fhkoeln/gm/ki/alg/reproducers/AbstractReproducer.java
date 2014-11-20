package de.fhkoeln.gm.ki.alg.reproducers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhkoeln.gm.ki.alg.util.DataSource;
import de.fhkoeln.gm.ki.alg.util.Population;


/***
 * Abstract Class for Reproducers, also handles GUI integration
 * @author Sascha Schewe
 *
 */
public abstract class AbstractReproducer implements ActionListener {

	public AbstractReproducer(){
		DataSource.getInstance().currentReproducer = this;
	}
	
	public abstract Population reproduce(Population oldGeneration, Population tmpGeneration);
	
	public abstract String getName();




	public final void actionPerformed(ActionEvent e) {
		DataSource.getInstance().currentReproducer=this;
		
	}



}
