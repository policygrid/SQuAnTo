package uk.ac.man.cs.mig.coode.owldoc.doclet.standard;

import uk.ac.man.cs.mig.coode.owldoc.doclet.OWLDocDoclet;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocPageGenerator;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocPreferences;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.abstractsyntax.ASAxiomWriter;
import uk.ac.man.cs.mig.coode.abstractsyntax.ASDescriptionWriter;
import edu.stanford.smi.protegex.owl.model.RDFResource;

import java.io.Writer;
import java.io.IOException;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jul 26, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class ASHTMLDoclet implements OWLDocDoclet {

	public boolean isSuitable(RDFResource resource) {
		return OWLDocPreferences.getInstance().isGenerateAbstractSyntax();
	}


	public void generateDocletContent(OWLDocPageGenerator pageGenerator,
	                                  RDFResource resource) {
		try {
			Writer writer = pageGenerator.getWriter();
			ASAxiomWriter asAxiomCreator = new ASAxiomWriter(new ASDescriptionWriter(new StringBuffer()));
			resource.accept(asAxiomCreator);
			String as = asAxiomCreator.getBuffer().toString();
			as = as.replaceAll("<", "&lt;");
			as = as.replaceAll(">", "&gt;");
			writer.write("<pre>");
			writer.write(as);
			writer.write("</pre>");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public String getSectionHeading() {
		return LanguageMap.getInstance().getWord(LanguageKeyWords.ABSTRACT_SYNTAX);
	}


	public String getName() {
		return "Abstract Syntax Generator";
	}


	public String getDescription() {
		return "Causes the OWL Abstract Syntax to be generated and added to the OWLDoc page for classes, properties and" +
		       "individuals.";
	}
}

