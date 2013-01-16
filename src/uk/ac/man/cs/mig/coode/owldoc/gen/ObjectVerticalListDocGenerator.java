package uk.ac.man.cs.mig.coode.owldoc.gen;

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
public class ObjectVerticalListDocGenerator extends OWLDocSectionGenerator {

	private Collection collection;


	public ObjectVerticalListDocGenerator(OWLDocPageGenerator pageGenerator,
	                                      String title,
	                                      Collection collection) {
		super(pageGenerator, title);
		this.collection = collection;
	}


	public void generateSectionContent()
	        throws IOException {
			if(collection.size() > 0) {
				getWriter().write("<table>");
				for(Iterator it = collection.iterator(); it.hasNext();) {
					getWriter().write("<tr><td>");
					getWriter().write(it.next().toString());
					getWriter().write("</td></tr>");
				}
				getWriter().write("</table>");
			}

	}


	public boolean isSectionContentPresent() {
		return collection.size() > 0;
	}
}

