package uk.ac.man.cs.mig.coode.owldoc.gen;

import java.io.IOException;
import java.io.Writer;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 19, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public abstract class OWLDocSectionGenerator implements OWLDocGenerator {

	private String title;

	private OWLDocPageGenerator pageGenerator;

	public OWLDocSectionGenerator(OWLDocPageGenerator pageGenerator, String title) {
		this.pageGenerator = pageGenerator;
		this.title = title;
	}

	public void generateOWLDoc()
	        throws IOException {
		if(isSectionContentPresent()) {
			getWriter().write("<h3>");
			getWriter().write(title);
			getWriter().write("</h3>");
			StyleSheetGenerator.writeCodeBox(pageGenerator.getWriter());
			generateSectionContent();
			getWriter().write("</div>");
		}
	}

	public abstract void generateSectionContent() throws IOException;

	public Writer getWriter() {
		return this.pageGenerator.getWriter();
	}

	public boolean isSectionContentPresent() {
		return true;
	}

	protected OWLDocPageGenerator getPageGenerator() {
		return pageGenerator;
	}
}

