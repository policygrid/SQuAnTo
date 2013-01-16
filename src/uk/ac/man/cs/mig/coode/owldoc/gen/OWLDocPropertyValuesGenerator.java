package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.model.impl.OWLUtil;
import edu.stanford.smi.protegex.owl.model.visitor.Visitable;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 19, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public abstract class OWLDocPropertyValuesGenerator extends OWLDocSectionGenerator {


	private RDFResource resource;

	private String content;

	public OWLDocPropertyValuesGenerator(OWLDocPageGenerator pageGenerator, String title, RDFResource resource) {
		super(pageGenerator, title);
		this.resource = resource;
	}


	public void generateSectionContent()
	        throws IOException {
		getWriter().write(content);
	}

	public abstract Collection getProperties();

	public boolean isSectionContentPresent() {
		Collection excludedProps = OWLDocPreferences.getInstance().getExcludedProperties();
		Collection properties = getProperties();
		boolean written = false;
		StringWriter sw = new StringWriter();
		sw.write("<table cellspacing=\"8px\">");
		TreeSet props = new TreeSet();
		props.addAll(OWLUtil.getRDFProperties(resource));
		for(Iterator it = props.iterator(); it.hasNext();) {
			RDFProperty property = (RDFProperty) it.next();
			if(properties.contains(property) &&
			   excludedProps.contains(property) == false &&
			   property.isSystem() == false) {
				for(Iterator valIt = resource.getPropertyValues(property).iterator(); valIt.hasNext();) {
					Object curVal = valIt.next();
					written = true;
					sw.write("<tr><td valign=\"top\">");
					HTMLRendererVisitor v = new HTMLRendererVisitor(sw);
					property.accept(v);
					sw.write("</td><td>");

					if(curVal instanceof Visitable) {
						((Visitable) curVal).accept(v);
					}
					else {
						sw.write(curVal.toString());
					}
					sw.write("</td></tr>");
				}
			}
		}
		sw.write("</table>");
		content = sw.getBuffer().toString();
		return written;
	}
}

