package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.util.Collection;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 19, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class RDFResourcePropertyValuesGenerator extends OWLDocPropertyValuesGenerator {

	private RDFResource resource;

	public RDFResourcePropertyValuesGenerator(OWLDocPageGenerator pageGenerator, RDFResource resource) {
		super(pageGenerator, LanguageMap.getInstance().getWord(LanguageKeyWords.RELATIONSHIPS), resource);
		this.resource = resource;
	}

	public Collection getProperties() {
		Collection props = resource.getRDFProperties();
		for(Iterator it = props.iterator(); it.hasNext();) {
			if(((RDFProperty) it.next()).isAnnotationProperty()) {
				it.remove();
			}
		}
		return props;
	}

}

