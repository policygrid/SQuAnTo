package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.NamespaceManager;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 21, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocOntologyNamespacesGenerator extends OWLDocSectionGenerator {

	private OWLModel model;

	private OWLDocPageGenerator pageGenerator;

	public OWLDocOntologyNamespacesGenerator(OWLDocPageGenerator pageGenerator, OWLModel model) {
		super(pageGenerator, LanguageMap.getInstance().getWord(LanguageKeyWords.NAMESPACES));
		this.model = model;
		this.pageGenerator = pageGenerator;
	}

	public void generateSectionContent()
	        throws IOException {
		NamespaceManager nsm = model.getNamespaceManager();
		OWLDocGenerator gen = new ObjectVerticalListDocGenerator(pageGenerator, LanguageMap.getInstance().getWord(LanguageKeyWords.DEFAULT_NAMESPACE),
		                                                              Collections.singleton(nsm.getDefaultNamespace()));
		gen.generateOWLDoc();
		if(nsm.getPrefixes().size() > 0) {
			pageGenerator.write("<table cellpadding=\"10px\">");
			for(Iterator it = nsm.getPrefixes().iterator(); it.hasNext();) {
				pageGenerator.write("<tr><td>");
				String curPrefix = (String) it.next();
				pageGenerator.write(curPrefix);
				pageGenerator.write("</td><td>");
				String uri = nsm.getNamespaceForPrefix(curPrefix);
				pageGenerator.write("<a href=\"" + uri + "\">" + uri + "</a>");
				pageGenerator.write("</td></tr>");
			}
			pageGenerator.write("</table>");
		}
	}
}

