package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFResource;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class RDFResourceCommaSepListDocGenerator extends OWLDocSectionGenerator {

	private Collection collection;


	public RDFResourceCommaSepListDocGenerator(OWLDocPageGenerator pageGenerator,
	                                           String title,
	                                           Collection collection) {
		super(pageGenerator, title);
		this.collection = collection;
	}


	public void generateSectionContent()
	        throws IOException {
		for(Iterator it = collection.iterator(); it.hasNext();) {
			HTMLRendererVisitor v = new HTMLRendererVisitor(getWriter());
			((RDFResource) it.next()).accept(v);
			if(it.hasNext()) {
				getWriter().write(", ");
			}
		}
	}


	public boolean isSectionContentPresent() {
		return collection.size() > 0;
	}
}

