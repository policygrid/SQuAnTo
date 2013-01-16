package uk.ac.man.cs.mig.coode.owldoc.gen;

import java.io.IOException;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 20, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocSpacer implements OWLDocGenerator {

	private OWLDocPageGenerator pageGenerator;

	public OWLDocSpacer(OWLDocPageGenerator pageGenerator) {
		this.pageGenerator = pageGenerator;
	}

	public void generateOWLDoc()
	        throws IOException {
		pageGenerator.write("<div class=\"spacer\"></div>");
	}
}

