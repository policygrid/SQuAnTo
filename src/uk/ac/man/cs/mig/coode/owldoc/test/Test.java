package uk.ac.man.cs.mig.coode.owldoc.test;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLModelDocGenerator;

import java.io.File;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class Test {

	public static void main(String [] args) {
		try {
			//String uri = "http://www.co-ode.org/ontologies/pizza/pizza_20041007.owl";
			String uri = "file:///develop/squanto/ont/travel.owl";
			OWLModel model = ProtegeOWL.createJenaOWLModelFromURI(uri);
			File file = new File("/develop/squanto/owldocs/");
			file.mkdir();
			OWLModelDocGenerator gen = new OWLModelDocGenerator(file, model);
			gen.generateOWLDoc();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

