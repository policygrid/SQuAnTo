package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.OWLProperty;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.IOException;
import java.util.ArrayList;
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
public class OWLDocPropertyCharacteristicsGenerator implements OWLDocGenerator {

	private OWLDocPageGenerator pageGenerator;

	private OWLProperty prop;


	public OWLDocPropertyCharacteristicsGenerator(OWLDocPageGenerator pageGenerator,
	                                              OWLProperty prop) {
		this.pageGenerator = pageGenerator;
		this.prop = prop;
	}


	public void generateOWLDoc()
	        throws IOException {
		ArrayList characteristics = new ArrayList();
		if(prop.isFunctional()) {
			characteristics.add(LanguageMap.getInstance().getWord(LanguageKeyWords.FUNCTIONAL));
		}
		if(prop.isInverseFunctional()) {
			characteristics.add(LanguageMap.getInstance().getWord(LanguageKeyWords.INVERSE_FUNCTIONAL));
		}

		if(prop.isObjectProperty()) {
			if(((OWLObjectProperty) prop).isTransitive()) {
				characteristics.add(LanguageMap.getInstance().getWord(LanguageKeyWords.TRANSITIVE));
			}
			if(((OWLObjectProperty) prop).isSymmetric()) {
				characteristics.add(LanguageMap.getInstance().getWord(LanguageKeyWords.SYMMETRIC));
			}

			if(prop.getInverseProperty() != null) {
				RDFResourceCommaSepListDocGenerator docGen;
				docGen = new RDFResourceCommaSepListDocGenerator(pageGenerator, LanguageMap.getInstance().getWord(LanguageKeyWords.INVERSE_PROPERTY),
				                                                 Collections.singleton(prop.getInverseProperty()));
				docGen.generateOWLDoc();
			}
		}

		if(characteristics.size() > 0) {
			ObjectVerticalListDocGenerator docGen;
			docGen = new ObjectVerticalListDocGenerator(pageGenerator, LanguageMap.getInstance().getWord(LanguageKeyWords.CHARACTERISTICS), characteristics);
			docGen.generateOWLDoc();
		}

	}
}

