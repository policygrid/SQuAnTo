package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLOntology;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.File;
import java.io.IOException;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 21, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocOntologyPageGenerator extends OWLDocPageGenerator {

	private OWLModel model;

	public OWLDocOntologyPageGenerator(OWLModel model, File baseDirectory) {
		super(baseDirectory, "ontology.html", LanguageMap.getInstance().getWord(LanguageKeyWords.ONTOLOGY));
		this.model = model;
	}

	public void generateContent()
	        throws IOException {
		OWLOntology ontology = model.getDefaultOWLOntology();
		OWLDocGenerator docGen = new OWLDocCommentsGenerator(this, ontology);
		docGen.generateOWLDoc();
		docGen = new AnnotationsDocGenerator(this, ontology);
		docGen.generateOWLDoc();
		docGen = new OWLDocOntologyNamespacesGenerator(this, model);
		docGen.generateOWLDoc();
		docGen = new ObjectVerticalListDocGenerator(this, LanguageMap.getInstance().getWord(LanguageKeyWords.IMPORTS), ontology.getImports());
		docGen.generateOWLDoc();
	}
}

