package de.fhkoeln.gm.ki.alg;

import java.io.NotActiveException;
import java.util.ArrayList;

import de.fhkoeln.gm.ki.alg.fitnessFunctions.AbstractFitness;
import de.fhkoeln.gm.ki.alg.mutators.AbstractMutator;
import de.fhkoeln.gm.ki.alg.recombiners.AbstractRecombiner;
import de.fhkoeln.gm.ki.alg.reproducers.AbstractReproducer;
import de.fhkoeln.gm.ki.alg.selectors.AbstractSelector;
import de.fhkoeln.gm.ki.alg.util.DataSource;
import de.fhkoeln.gm.ki.alg.util.Population;
import de.fhkoeln.gm.ki.gui.GUI_Swing;
import de.fhkoeln.gm.ki.gui.GraphData;
import de.fhkoeln.gm.ki.remoteControl.BotMonitor;

/***
 * Controls the standard flow of the GA
 * Should be able to accommodate most styles of GA without modification
 * 
 * @author Sascha Schewe
 *
 */
public class GeneticAlgorithm extends Thread {
		
	private int macroStepPause = 200;
	private int microStepPause = 20;
	private PauseSetting pauseSetting = PauseSetting.ONLYMACRO;
	
	private ArrayList<Population> allGenerations;
	private Population currentPopulation;
	private Population tmpPopulation;
	private AbstractSelector selector;
	private AbstractRecombiner recombiner;
	private AbstractMutator mutator;
	private AbstractReproducer reproducer;
	private AbstractFitness fitnessFunction;
	
	private boolean paused = false;
	private boolean killed = false;
	private int maxGenerations=1000;
	private int generation=0;

	@SuppressWarnings("unused")
	private GeneticAlgorithm(){}
	
	public GeneticAlgorithm(Population firstGeneration, AbstractFitness fitnessFunction, AbstractSelector selector, AbstractRecombiner recombiner, AbstractMutator mutator, AbstractReproducer reproducer){
		super();
		setDaemon(true);
		allGenerations = new ArrayList<Population>();
		currentPopulation=firstGeneration;
		this.selector=selector;
		this.recombiner=recombiner;
		this.mutator=mutator;
		this.reproducer=reproducer;
		this.fitnessFunction = fitnessFunction;
		DataSource.getInstance().currentGeneticAlgorithm = this;
	}
	
	@Override
	public void run(){
		for(generation = 0; generation<maxGenerations; generation++){
			evaluate();
			if(killed)
				return;
			if(fitnessFunction.thresholdReached()){
				updateGUI();
				notifyGUIFinishedAlgorithm();
				return;
			}
			select();
			recombine();
			mutate();
			updateGUI();
			reproduce();
			
			while(paused){
				yield();
			}
			try {
				if(pauseSetting.equals(PauseSetting.BOTH)||pauseSetting.equals(PauseSetting.ONLYMACRO))
					Thread.sleep(macroStepPause);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GUI_Swing.getInstance().algorithmIsFinished();
	}
	
	private void notifyGUIFinishedAlgorithm() {
		GUI_Swing.getInstance().algorithmIsFinished();
		
	}

	public void setMaxGenerations(int generations){
		maxGenerations=generations;
	}

	public void pauseThread(){
		paused=true;
	}
	
	public void resumeThread(){
		paused=false;
	}
	
	private void evaluate(){
		for(int i=0;i<currentPopulation.getPop().size();i++){
			try {
				BotMonitor.getInstance().resetArm();
				if(!BotMonitor.getInstance().checkSensorRange()){
					paused=true;
					GUI_Swing.getInstance().warnOfSensorRange();
				}
			} catch (NotActiveException e1) {}	

			while(paused)
				yield();

			fitnessFunction.evaluate(currentPopulation.getPop().get(i));

			if(killed)
				return;
			if(pauseSetting.equals(PauseSetting.BOTH)||pauseSetting.equals(PauseSetting.ONLYMICRO)){
				currentPopulation.sort();
				updateGUI();
				try {
					Thread.sleep(microStepPause);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		currentPopulation.sort();
		GraphData.getInstance().addGeneration(generation, currentPopulation.getMaximumFitness(), currentPopulation.getAverageFitness(), currentPopulation.getMinimumFitness());
	}
	
	private void select(){
		tmpPopulation=selector.selectFromPopulation(currentPopulation);
	}
	
	private void recombine() {
		tmpPopulation= recombiner.recombine(tmpPopulation);
	}

	private void mutate() {
		tmpPopulation=mutator.mutate(tmpPopulation, DataSource.getInstance().getViableGenes());
		
	}

	private void reproduce() {
		allGenerations.add(currentPopulation);
		currentPopulation = reproducer.reproduce(currentPopulation, tmpPopulation);
		
	}

	private void updateGUI(){
		DataSource.getInstance().setPopulation(currentPopulation);
		GUI_Swing.getInstance().updateTextFields();
	}
	
	public enum PauseSetting{
		NOPAUSE, ONLYMICRO, ONLYMACRO, BOTH;
	}

	public void kill() {
		killed=true;
		paused=false;
		
	}
	public boolean isPaused(){
		return paused;
	}
}


