package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.File;
import java.io.IOException;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 19, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLIndividualDocGenerator extends OWLDocPageGenerator {

	private OWLIndividual individual;

	public OWLIndividualDocGenerator(File baseDirectory, OWLIndividual individual) {
		super(baseDirectory, OWLDocHelper.getFileName(individual), "Individual: " + OWLDocHelper.getDisplayName(individual));
		this.individual = individual;
	}


	public void generateContent()
	        throws IOException {
		OWLDocGenerator gen = new RDFResourceVerticalListDocGenerator(this,
		                                                              LanguageMap.getInstance().getWord(LanguageKeyWords.TYPES),
		                                                              individual.getRDFTypes());
		gen.generateOWLDoc();

		gen = new AnnotationsDocGenerator(this, individual);
		gen.generateOWLDoc();

		gen = new RDFResourcePropertyValuesGenerator(this, individual);
		gen.generateOWLDoc();

		gen = new ASHTMLGenerator(this, individual);
		gen.generateOWLDoc();

	}
}

