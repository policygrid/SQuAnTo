package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFResource;
import uk.ac.man.cs.mig.coode.owldoc.doclet.OWLDocDoclet;

import java.io.IOException;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jul 26, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class DocletContentGenerator extends OWLDocSectionGenerator {

	private OWLDocDoclet doclet;

	private RDFResource resource;

	public DocletContentGenerator(OWLDocPageGenerator pageGenerator, RDFResource resource, OWLDocDoclet doclet) {
		super(pageGenerator, doclet.getSectionHeading());
		this.doclet = doclet;
		this.resource = resource;
	}


	public void generateSectionContent()
	        throws IOException {
		doclet.generateDocletContent(getPageGenerator(), resource);
	}


	public boolean isSectionContentPresent() {
		return doclet.isSuitable(resource);
	}
}

