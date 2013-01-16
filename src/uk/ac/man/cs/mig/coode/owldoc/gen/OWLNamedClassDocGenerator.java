package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import uk.ac.man.cs.mig.coode.owldoc.doclet.DocletManager;
import uk.ac.man.cs.mig.coode.owldoc.doclet.OWLDocDoclet;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

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
public class OWLNamedClassDocGenerator extends OWLDocPageGenerator{

	private OWLNamedClass cls;


	public OWLNamedClassDocGenerator(OWLNamedClass cls, File baseDirectory) {
		super(baseDirectory, OWLDocHelper.getFileName(cls),OWLDocHelper.getDisplayName(cls));
		this.cls = cls;
	}


	public void generateContent() throws IOException {


		OWLDocGenerator docGen;
		docGen = new OWLDocCommentsGenerator(this, cls);
		docGen.generateOWLDoc();

		docGen = new OWLClassHierarchyGenerator(cls, getWriter());
		docGen.generateOWLDoc();

/*
		docGen = new RDFResourceVerticalListDocGenerator(this, LanguageMap.getInstance().getWord(LanguageKeyWords.SUPERCLASSES),
		                                                 cls.getPureSuperclasses());
		docGen.generateOWLDoc();

		docGen = new OWLDocEquivalentClassGenerator(this, cls);
		docGen.generateOWLDoc();

		docGen = new RDFResourceCommaSepListDocGenerator(this,
		                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.DISJOINT_CLASSES),
		                                                 cls.getDisjointClasses());
		docGen.generateOWLDoc();

		docGen = new RDFResourceCommaSepListDocGenerator(this,
		                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.INDIVIDUALS),
		                                                 cls.getInstances(false));
		docGen.generateOWLDoc();


		docGen = new RDFResourceVerticalListDocGenerator(this, LanguageMap.getInstance().getWord(LanguageKeyWords.SAME_AS),
		                                                 cls.getSameAs());
		docGen.generateOWLDoc();

		docGen = new RDFResourceVerticalListDocGenerator(this,
		                                                 LanguageMap.getInstance().getWord(LanguageKeyWords.DIFFERENT_FROM),
		                                                 cls.getDifferentFrom());
		docGen.generateOWLDoc();

		AnnotationsDocGenerator annotationsDocGenerator = new AnnotationsDocGenerator(this, cls);
		annotationsDocGenerator.generateOWLDoc();



		ASHTMLGenerator ashtmlGenerator = new ASHTMLGenerator(this, cls);
		ashtmlGenerator.generateOWLDoc();


		OWLNamedClassUsageDocGenerator usageGen = new OWLNamedClassUsageDocGenerator(this, cls);
		usageGen.generateOWLDoc();
*/

// Doclets
//
//		for(Iterator it = DocletManager.getInstance().getDoclets().iterator(); it.hasNext(); ) {
//			OWLDocDoclet doclet = (OWLDocDoclet) it.next();
//			DocletContentGenerator gen = new DocletContentGenerator(this, cls, doclet);
//			gen.generateOWLDoc();
//		}
	}


	private void generateBody() throws IOException 
	{
	
	}
	
//
//	private void writeResources(Collection collection) {
//		try {
//			writer.write("<table>");
//			for(Iterator it = collection.iterator(); it.hasNext();) {
//				writer.write("<tr><td>");
//				HTMLRendererVisitor v = new HTMLRendererVisitor(writer);
//				RDFResource res = (RDFResource) it.next();
//				res.accept(v);
//				writer.write("</td></tr>");
//
//			}
//			writer.write("</table>");
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//	}

}


