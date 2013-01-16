package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.RDFResource;

import java.util.HashMap;
import java.util.HashSet;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Feb 28, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocNameCache {

	private HashSet usedNames;

	private HashMap nameMap;

	private static OWLDocNameCache instance;

	private static HashSet RESERVED_NAMES;

	static {
		RESERVED_NAMES = new HashSet();
		RESERVED_NAMES.add("index");
		RESERVED_NAMES.add("contents");
		RESERVED_NAMES.add("ontology");
		RESERVED_NAMES.add("IndexAllResources");
		RESERVED_NAMES.add("IndexAllClasses");
		RESERVED_NAMES.add("IndexAllObjectProperties");
		RESERVED_NAMES.add("IndexAllDatatypeProperties");
		RESERVED_NAMES.add("IndexAllAnnotationProperties");
		RESERVED_NAMES.add("IndexAllIndividuals");
	}

	private OWLDocNameCache() {
		usedNames = new HashSet();
		nameMap = new HashMap();
	}

	public static synchronized OWLDocNameCache getInstance() {
		if(instance == null) {
			instance = new OWLDocNameCache();
		}
		return instance;
	}

	public String getFileName(RDFResource resource) {
		String fileName;
		fileName = (String) nameMap.get(resource);
		if(fileName == null) {
			// Generate a file name
			// Normalise by converting to lower case
			String resourceName = resource.getName().toLowerCase();
			// Replace any prefix marker with an underscore
			String name = resourceName.replaceAll(":", "_");
			// Make sure the file name does not correspond to a reserved name
			while(RESERVED_NAMES.contains(name)) {
				name = "!" + name;
			}
			// Check we haven't already used the file name
			int counter = 1;
			fileName = name;
			while(usedNames.contains(fileName) == true) {
				fileName = name + "_" + counter;
			}
			// Mark the name as used and map the resource to the name
			usedNames.add(fileName);
			nameMap.put(resource, fileName);
		}
		return fileName + ".html";
	}

	public void emptyCache() {
		usedNames.clear();
		nameMap.clear();
	}
}

