package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.model.RDFSNames;
import edu.stanford.smi.protegex.owl.model.visitor.Visitable;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 20, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocCommentsGenerator implements OWLDocGenerator {

	private RDFResource resource;

	private OWLDocPageGenerator pageGenerator;

	public OWLDocCommentsGenerator(OWLDocPageGenerator pageGenerator, RDFResource resource) {
		this.pageGenerator = pageGenerator;
		this.resource = resource;
	}


	public void generateOWLDoc()
	        throws IOException {
		RDFProperty property = resource.getOWLModel().getRDFProperty(RDFSNames.Slot.COMMENT);
		Collection values = resource.getPropertyValues(property);
		if(values.size() > 0) {
			pageGenerator.write("<table>");
			for(Iterator it = values.iterator(); it.hasNext(); ) {
				Object curVal = it.next();
				pageGenerator.write("<tr><td>");
				if(curVal instanceof Visitable) {
					((Visitable)curVal).accept(new HTMLRendererVisitor(pageGenerator.getWriter()));
				}
				else {
					pageGenerator.write(curVal.toString());
				}
				pageGenerator.write("</td></tr>");
			}
			pageGenerator.write("</table>");
		}
	}

}

