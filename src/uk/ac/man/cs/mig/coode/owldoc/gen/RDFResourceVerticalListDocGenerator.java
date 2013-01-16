package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.visitor.Visitable;

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
public class RDFResourceVerticalListDocGenerator extends OWLDocSectionGenerator {

	private Collection collection;


	public RDFResourceVerticalListDocGenerator(OWLDocPageGenerator pageGenerator,
	                                           String title,
	                                           Collection collection) {
		super(pageGenerator, title);
		this.collection = collection;

	}


	public void generateSectionContent()
	        throws IOException {
		getWriter().write("<table>");
		for(Iterator it = collection.iterator(); it.hasNext();) {
			getWriter().write("<tr><td>");
			HTMLRendererVisitor v = new HTMLRendererVisitor(getWriter());
			((Visitable) it.next()).accept(v);
			getWriter().write("</td></tr>");
		}
		getWriter().write("</table>");
	}


	public boolean isSectionContentPresent() {
		return collection.size() > 0;
	}
}

