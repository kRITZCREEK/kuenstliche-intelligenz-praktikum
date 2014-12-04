package de.fhkoeln.gm.ki.alg.fitnessFunctions;

import lejos.nxt.Motor;
import de.fhkoeln.gm.ki.alg.genes.AbstractGene;
import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.remoteControl.BotMonitor;

public class DistanceFitness extends AbstractFitness {

	@Override
	public float evaluate(Individual genome) {
		
		
		BotMonitor bot = BotMonitor.getInstance();
		bot.resetArm();
		float initialDistance = bot.getUSS().getDistance();
		for(AbstractGene g : genome.getGenes()){
			g.execute();
		}
		
		
		float fitness 	= genome.fitness 
						= initialDistance - bot.getUSS().getDistance();
		
		return fitness;
	}

	@Override
	public boolean thresholdReached() {
		BotMonitor bot = BotMonitor.getInstance();
		if (bot.getUSS().getDistance() <= 6)
			return true;
		
		
		
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "BotFitness";
	}

}
