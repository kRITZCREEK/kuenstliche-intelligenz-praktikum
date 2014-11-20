package de.fhkoeln.gm.ki.gui;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphData {

	private static GraphData self;
	
	public JFreeChart chart;
	private XYSeriesCollection dataset;
	private XYSeries maxFitness;
	private XYSeries avgFitness;
	private XYSeries minFitness;
	private XYLineAndShapeRenderer renderer;
	private NumberAxis xax;
	private NumberAxis yax;
	private XYPlot plot;
	
	private GraphData(){
		maxFitness = new XYSeries("Maximum Fitness");
		avgFitness = new XYSeries("Average Fitness");
		minFitness = new XYSeries("Minimum Fitness");
		dataset = new XYSeriesCollection();

		dataset.addSeries(maxFitness);
		dataset.addSeries(avgFitness);
		dataset.addSeries(minFitness);
		
		renderer = new XYLineAndShapeRenderer();
		xax = new NumberAxis("Generation");
		yax = new NumberAxis("Fitness");
		plot = new XYPlot(dataset,xax,yax, renderer);
		chart = new JFreeChart(plot);
	}
	
	
	public static GraphData getInstance(){
		if(self==null)
			self = new GraphData();
		return self;
	}
	
	
	public void addGeneration(int generation, double maxFitness, double avgFitness, double minFitness){
		this.maxFitness.add(generation, maxFitness);
		this.avgFitness.add(generation, avgFitness);
		this.minFitness.add(generation, minFitness);
	}
	
	public void clear(){
		maxFitness.clear();
		avgFitness.clear();
		minFitness.clear();
	}
}
