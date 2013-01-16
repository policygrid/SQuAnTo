package uk.ac.man.cs.mig.coode.owldoc;

import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocHelper;

import java.io.File;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.ProtegeOWL;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Aug 5, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDoc {

	public static void generateOWLDoc(String uri, File baseDirectory) {
		try {
			OWLModel model = ProtegeOWL.createJenaOWLModelFromURI(uri);
			OWLDocHelper.generateOWLDoc(model, baseDirectory);
			model = null;
		}
		catch(Exception e) {
			System.out.println("Error: ");
			System.out.println(e.getMessage());
		}
	}

	public static void main(String [] args) {
		String uri = args[0];
		String base = args[1];
		if(args.length >= 2) {
			System.out.println("Generating OWLDoc for " + uri + "...");
			File baseDirectory = new File(base);
			baseDirectory.mkdir();
			generateOWLDoc(uri, baseDirectory);
			System.out.println("... OWLDoc generated to " + base);
		}
		else {
			printUsage();
		}
	}

	public static void printUsage() {
		System.out.print("OWLDoc Usage:");
		System.out.print("OWLDoc <OWL file URI> <Base directory>");
	}
}

