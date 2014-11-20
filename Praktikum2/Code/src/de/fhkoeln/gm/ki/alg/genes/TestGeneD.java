package de.fhkoeln.gm.ki.alg.genes;

public class TestGeneD extends AbstractGene {

	@Override
	public char getName() {
		return 'D';
	}

	@Override
	public String getDescription() {
		return "Das Gen D";
	}

	@Override
	public float execute() {
		return -200f;
	}

}
