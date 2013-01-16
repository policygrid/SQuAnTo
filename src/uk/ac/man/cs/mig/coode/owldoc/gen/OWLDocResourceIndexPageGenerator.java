package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFResource;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
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
public class OWLDocResourceIndexPageGenerator extends OWLDocPageGenerator {

	private Collection resources;

	private String title;

	public OWLDocResourceIndexPageGenerator(ContentsIndexEntry indexEntry) {
		super(indexEntry.getBaseDirectory(), indexEntry.getFileName(), "");
		this.title = indexEntry.getDisplayName();
		this.resources = indexEntry.getResources();
	}

	public void generateContent()
	        throws IOException {
		TreeSet ts;
		if(OWLDocPreferences.getInstance().isUseBrowserText()) {
			ts = new TreeSet(new BrowserTestComparator());
			ts.addAll(resources);
		}
		else {
			ts = new TreeSet(resources);
		}

		RDFResourceVerticalListDocGenerator gen = new RDFResourceVerticalListDocGenerator(this, title, ts);
		gen.generateOWLDoc();
		if(ts.size() == 0) {
			write("<h3>" + title + "</h3>");
		}
	}

	private class BrowserTestComparator implements Comparator {

		public int compare(Object o1,
		                   Object o2) {
			RDFResource res1, res2;
			res1 = (RDFResource) o1;
			res2 = (RDFResource) o2;
			return res1.getBrowserText().compareTo(res2.getBrowserText());
		}
	}
}

