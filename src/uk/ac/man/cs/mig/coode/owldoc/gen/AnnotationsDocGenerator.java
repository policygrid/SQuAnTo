package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.model.RDFSNames;
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
public class AnnotationsDocGenerator extends OWLDocPropertyValuesGenerator {

	private RDFResource resource;

	public AnnotationsDocGenerator(OWLDocPageGenerator pageGenerator, RDFResource resource) {
		super(pageGenerator, LanguageMap.getInstance().getWord(LanguageKeyWords.ANNOTATIONS), resource);
		this.resource = resource;
	}

	public Collection getProperties() {
		Collection properties = resource.getRDFProperties();
		for(Iterator it = properties.iterator(); it.hasNext();) {
			if(((RDFProperty)it.next()).isAnnotationProperty() == false) {
				it.remove();
			}
		}
		// Remove rdfs:Comment as this will be shown at the top near
		// the class name
		properties.remove(resource.getOWLModel().getRDFProperty(RDFSNames.Slot.COMMENT));
		return properties;
	}
}

