package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLIntersectionClass;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 21, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocPropertyRangeGenerator implements OWLDocGenerator {

	private OWLDocPageGenerator pageGenerator;

	private RDFProperty prop;


	public OWLDocPropertyRangeGenerator(OWLDocPageGenerator pageGenerator,
	               RDFProperty prop) {
		this.pageGenerator = pageGenerator;
		this.prop = prop;
	}


	public void generateOWLDoc()
	        throws IOException {
		RDFResource range = prop.getRange();
		Collection ranges;
		if(range != null) {
			if(range instanceof OWLIntersectionClass) {
				ranges = ((OWLIntersectionClass)range).getOperands();
			}
			else {
				ranges = Collections.singleton(range);
			}
			RDFResourceVerticalListDocGenerator docGen;
			docGen = new RDFResourceVerticalListDocGenerator(pageGenerator,
			                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.RANGE),
			                                                 ranges);
			docGen.generateOWLDoc();

		}
	}

}

