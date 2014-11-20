package de.fhkoeln.gm.ki.alg.genes;

public class TestGeneA extends AbstractGene {

	@Override
	public char getName() {
		return 'A';
	}

	@Override
	public String getDescription() {
		return "Das Gen A";
	}

	@Override
	public float execute() {
		return 500f;
	}

}
