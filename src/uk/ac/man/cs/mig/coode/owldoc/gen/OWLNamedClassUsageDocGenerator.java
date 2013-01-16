package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLAnonymousClass;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.IOException;
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
public class OWLNamedClassUsageDocGenerator implements OWLDocGenerator {

	private OWLNamedClass cls;

	private OWLDocPageGenerator pageGenerator;

	public OWLNamedClassUsageDocGenerator(OWLDocPageGenerator pageGenerator, OWLNamedClass resource) {
		this.pageGenerator = pageGenerator;
		this.cls = resource;
	}


	public void generateOWLDoc()
	        throws IOException {
		if(getClassUsage().size() > 0 ||
		   cls.getUnionDomainProperties().size() > 0) {
			generateSectionContent();	
		}
	}


	private void generateSectionContent()
	        throws IOException {
		pageGenerator.write("<h2>" + LanguageMap.getInstance().getWord(LanguageKeyWords.USAGE) + "</h2>");

			TreeSet ts = new TreeSet(getClassUsage());
			RDFResourceCommaSepListDocGenerator gen;
			gen = new RDFResourceCommaSepListDocGenerator(pageGenerator,
			                                              LanguageMap.getInstance().getWord(LanguageKeyWords.CLASS_DESC_DEF),
			                                              ts);
			gen.generateOWLDoc();
			ts = new TreeSet(cls.getUnionDomainProperties());
			gen = new RDFResourceCommaSepListDocGenerator(pageGenerator,
			                                              LanguageMap.getInstance().getWord(LanguageKeyWords.DOMAIN_OF)
			                                              , ts);
			gen.generateOWLDoc();
	}



	private Collection getClassUsage() {
		Collection anonClses = cls.getReferringAnonymousClasses();
		TreeSet result = new TreeSet();
		for(Iterator it = anonClses.iterator(); it.hasNext();) {
			result.addAll(getNamedClasses((OWLAnonymousClass)it.next()));
		}
		result.addAll(cls.getNamedSubclasses(false));
		return result;
	}

	private Collection getNamedClasses(OWLAnonymousClass cls) {
		OWLAnonymousClass root = cls;
		OWLAnonymousClass lastRoot = null;
		while(root.equals(lastRoot) == false) {
			lastRoot = root;
			root = root.getExpressionRoot();

		}
		return lastRoot.getNamedSubclasses();
	}
}

