package uk.ac.man.cs.mig.coode.owldoc.doclet;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jul 26, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class DocletManager {

	static private DocletManager instance;

	private ArrayList doclets;

	private DocletManager() {
		doclets = new ArrayList();
	}

	public static synchronized DocletManager getInstance() {
		if(instance == null) {
			instance = new DocletManager();
		}
		return instance;
	}

	public void registerDoclet(OWLDocDoclet doclet) {
		doclets.add(doclet);
	}

	public void unregisterDoclet(OWLDocDoclet doclet) {
		doclets.remove(doclet);
	}

	public Collection getDoclets() {
		return new ArrayList(doclets);
	}


}

