package de.fhkoeln.gm.ki.alg.genes;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/***
 * Abstract Class for Genes, Also handles GUI integration
 * @author Sascha Schewe
 *
 */
public abstract class AbstractGene implements ItemListener {
	
	private boolean checked = true;
	
	//Short name such as "A" or "B"
	public abstract char getName();
	//Description of what the block does
	public abstract String getDescription();
	/***
	 * Use this to implement any actions
	 * @return in case a return signal is needed
	 */
	public abstract float execute();
	
	
	
	
	
	@SuppressWarnings("static-access")
	public final void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == e.SELECTED)
			setChecked(true);
		if(e.getStateChange() == e.DESELECTED)
			setChecked(false);
	}
	public final boolean isChecked() {
		return checked;
	}
	private final void setChecked(boolean checked) {
		this.checked = checked;
	}
}
