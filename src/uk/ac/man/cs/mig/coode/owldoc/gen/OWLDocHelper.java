package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.RDFResource;

import java.io.File;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 19, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocHelper {



	public static String getFileName(RDFResource resource) {
		return OWLDocNameCache.getInstance().getFileName(resource);
	}

	public static String getDisplayName(RDFResource resource) {
		if(OWLDocPreferences.getInstance().isUseBrowserText()) {
			return resource.getBrowserText();
		}
		else {
			return resource.getName();
		}
	}

	/**
	 * Generates the OWLDoc for the specified model into the
	 * specified base directory.
	 * @param model The model that OWLDoc should be generated for.
	 * @param baseDirectory The directory where the generated pages
	 * will be placed.
	 */
	public static void generateOWLDoc(OWLModel model, File baseDirectory) {
		OWLModelDocGenerator gen = new OWLModelDocGenerator(baseDirectory, model);
		gen.generateOWLDoc();
	}
}

