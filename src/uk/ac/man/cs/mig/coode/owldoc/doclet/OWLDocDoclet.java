package uk.ac.man.cs.mig.coode.owldoc.doclet;

import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocSectionGenerator;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocGenerator;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocPageGenerator;

import java.io.IOException;

import edu.stanford.smi.protegex.owl.model.RDFResource;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jul 26, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public interface OWLDocDoclet {

	 boolean isSuitable(RDFResource resource);

	 void generateDocletContent(OWLDocPageGenerator pageGenerator, RDFResource resource);

     String getSectionHeading();

	 String getName();

	 String getDescription();
}

