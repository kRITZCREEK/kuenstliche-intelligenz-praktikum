package de.fhkoeln.gm.ki.alg.fitnessFunctions;

import lejos.nxt.Motor;
import de.fhkoeln.gm.ki.alg.genes.AbstractGene;
import de.fhkoeln.gm.ki.alg.util.DataSource;
import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.remoteControl.BotMonitor;

public class DistanceFitness extends AbstractFitness {

	@Override
	public float evaluate(Individual genome) {
		
		
		BotMonitor bot = BotMonitor.getInstance();
		bot.resetArm();
		
		

		System.out.println("Distance: " + bot.getUSS().getDistance());
		if(bot.getUSS().getDistance() <= 10) {
			Motor.A.rotate(40);
			while(bot.getUSS().getDistance() < 100) {
				Motor.B.rotate(-360);
			}

			bot.resetArm();

			DataSource ds = DataSource.getInstance();
			ds.currentGeneticAlgorithm.pauseThread();
			return genome.fitness;
		}
		
		
		
		float initialDistance = bot.getUSS().getDistance();
		for(AbstractGene g : genome.getGenes()){
			g.execute();
			System.out.println("Finished " + g.getDescription());
		}
		
		
		float fitness 	= genome.fitness 
						= initialDistance - bot.getUSS().getDistance();
		
		
		return fitness;
	}

	@Override
	public boolean thresholdReached() {
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "BotFitness";
	}

}
