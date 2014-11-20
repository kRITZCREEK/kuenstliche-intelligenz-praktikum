package de.fhkoeln.gm.ki.alg.genes;

public class TestGeneB extends AbstractGene {

	@Override
	public char getName() {
		return 'B';
	}

	@Override
	public String getDescription() {
		return "Das Gen B";
	}

	@Override
	public float execute() {
		return 10f;
	}

}
