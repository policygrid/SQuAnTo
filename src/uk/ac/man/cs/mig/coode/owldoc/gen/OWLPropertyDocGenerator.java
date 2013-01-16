package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLProperty;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.File;
import java.io.IOException;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLPropertyDocGenerator extends OWLDocPageGenerator {

	private OWLProperty prop;

	public OWLPropertyDocGenerator(OWLProperty property, File baseDirectory) {
		super(baseDirectory, OWLDocHelper.getFileName(property), "Property: " + OWLDocHelper.getDisplayName(property));
		this.prop = property;

	}



	public void generateContent() throws IOException {
		OWLDocGenerator docGen;

		docGen = new OWLDocCommentsGenerator(this, prop);
		docGen.generateOWLDoc();

		docGen = new RDFResourceVerticalListDocGenerator(this, LanguageKeyWords.TYPES, prop.getRDFTypes());
		docGen.generateOWLDoc();

		docGen = new RDFResourceVerticalListDocGenerator(this,
		                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.SUPER_PROPERTIES),
		                                                 prop.getSuperproperties(false));
		docGen.generateOWLDoc();

		docGen = new RDFResourceVerticalListDocGenerator(this,
		                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.DOMAIN),
		                                                 prop.getUnionDomain());
		docGen.generateOWLDoc();

		docGen = new OWLDocPropertyRangeGenerator(this, prop);
		docGen.generateOWLDoc();

		docGen = new OWLDocPropertyCharacteristicsGenerator(this, prop);
		docGen.generateOWLDoc();

		docGen = new RDFResourceVerticalListDocGenerator(this,
		                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.EQUIVALENT_PROPERTIES),
		                                                 prop.getEquivalentProperties());
		docGen.generateOWLDoc();

		docGen = new RDFResourceVerticalListDocGenerator(this,
		                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.SAME_AS)
		                                                 , prop.getSameAs());
		docGen.generateOWLDoc();

		docGen = new RDFResourceVerticalListDocGenerator(this,
		                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.DIFFERENT_FROM)
		                                                 , prop.getDifferentFrom());
		docGen.generateOWLDoc();

		AnnotationsDocGenerator annotationsDocGenerator = new AnnotationsDocGenerator(this, prop);
		annotationsDocGenerator.generateOWLDoc();

		ASHTMLGenerator ashtmlGenerator = new ASHTMLGenerator(this, prop);
		ashtmlGenerator.generateOWLDoc();
	}
}

