package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLProperty;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLModelDocGenerator {

	private File baseDirectory;

	private OWLModel model;

	public OWLModelDocGenerator(File baseDirectory, OWLModel model) {
		if(baseDirectory.isDirectory() == false) {
			throw new IllegalArgumentException("The OWLDoc base directory must be a directory!");
		}
		this.baseDirectory = baseDirectory;
		this.model = model;
	}

	public void generateOWLDoc() {
		try {
			OWLDocIndexPageGenerator indexPageGenerator = new OWLDocIndexPageGenerator(model, baseDirectory);
			indexPageGenerator.generateOWLDoc();

			OWLDocContentsAndIndexGenerator contentsAndIndexGenerator = new OWLDocContentsAndIndexGenerator(model, baseDirectory);
			contentsAndIndexGenerator.generateOWLDoc();

			for(Iterator it = model.getUserDefinedOWLNamedClasses().iterator(); it.hasNext();) {
				OWLNamedClass namedClass = (OWLNamedClass) it.next();
				OWLNamedClassDocGenerator gen = new OWLNamedClassDocGenerator(namedClass, baseDirectory);
				gen.generateOWLDoc();
			}
			for(Iterator it = model.getUserDefinedOWLProperties().iterator(); it.hasNext();) {
				OWLPropertyDocGenerator gen = new OWLPropertyDocGenerator((OWLProperty) it.next(), baseDirectory);
				gen.generateOWLDoc();
			}
			for(Iterator it = model.getOWLIndividuals().iterator(); it.hasNext(); ) {
				OWLIndividualDocGenerator gen = new OWLIndividualDocGenerator(baseDirectory, (OWLIndividual) it.next());
				gen.generateOWLDoc();
			}
		}
		catch(IOException ioEx) {
		    System.out.println(ioEx);
		}
	}



}

