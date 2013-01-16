package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLIntersectionClass;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 21, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocEquivalentClassGenerator implements OWLDocGenerator {

	private OWLDocPageGenerator pageGenerator;

	private OWLNamedClass cls;


	public OWLDocEquivalentClassGenerator(OWLDocPageGenerator pageGenerator, OWLNamedClass cls) {
		this.pageGenerator = pageGenerator;
		this.cls = cls;
	}

	public void generateOWLDoc()
	        throws IOException {
		for(Iterator it = cls.getEquivalentClasses().iterator(); it.hasNext();) {
			RDFSClass equCls = (RDFSClass) it.next();
			Collection curEquClsCol;
			if(equCls instanceof OWLIntersectionClass) {
				curEquClsCol = ((OWLIntersectionClass)equCls).getOperands();
			}
			else {
				curEquClsCol = Collections.singleton(equCls);
			}
			OWLDocGenerator docGen = new RDFResourceVerticalListDocGenerator(pageGenerator,
			                                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.EQUIVALENT_CLASSES),
		                                                     curEquClsCol);
			docGen.generateOWLDoc();
		}
	}
}

