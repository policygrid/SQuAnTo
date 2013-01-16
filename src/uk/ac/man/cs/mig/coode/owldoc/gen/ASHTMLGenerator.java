package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFResource;
import uk.ac.man.cs.mig.coode.abstractsyntax.ASWriterHelper;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.IOException;
import java.io.Writer;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class ASHTMLGenerator extends OWLDocSectionGenerator {

	private RDFResource resource;


	public ASHTMLGenerator(OWLDocPageGenerator pageGenerator,
	                       RDFResource resource) {
		super(pageGenerator, LanguageMap.getInstance().getWord(LanguageKeyWords.ABSTRACT_SYNTAX));
		this.resource = resource;
	}


	public void generateSectionContent()
	        throws IOException {
		Writer writer = getWriter();
		StringBuffer buffer = new StringBuffer();
		ASWriterHelper.writeDescription(resource,  buffer);
		String as = buffer.toString();
		as = as.replaceAll("<", "&lt;");
		as = as.replaceAll(">", "&gt;");
		writer.write("<pre>");
		writer.write(as);
		writer.write("</pre>");
	}


	public boolean isSectionContentPresent() {
		return OWLDocPreferences.getInstance().isGenerateAbstractSyntax();
	}
}

