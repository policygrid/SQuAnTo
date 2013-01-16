package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import edu.stanford.smi.protegex.owl.model.impl.OWLUtil;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 19, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLClassHierarchyGenerator implements OWLDocGenerator {

	private OWLNamedClass cls;

	private Writer writer;

	public OWLClassHierarchyGenerator(OWLNamedClass cls, Writer writer) {
		this.cls = cls;
		this.writer = writer;
	}

	public void generateOWLDoc() throws IOException {
		writer.write("<h3>");
		LanguageMap.getInstance().getWord(LanguageKeyWords.HIERARCHY);
		writer.write("</h3>");
		writer.write("<table>");
		generateClassHiearchy();
		writer.write("</table>");
	}

	private void generateClassHiearchy() throws IOException {
		Collection result = new HashSet(cls.getNamedSuperclasses(true));
		result.add(cls);
		insertClasses(Collections.singleton(cls.getOWLModel().getOWLThingClass()), 0, result);
	}

	private void insertClasses(Collection clses, int indent, Collection restictTo) throws IOException {
		for(Iterator it = clses.iterator(); it.hasNext();) {

			writer.write("<tr><td>");
			RDFSClass curCls = (RDFSClass) it.next();
			insertIndent(indent);
			writer.write("&#8225;");
			HTMLRendererVisitor v = new HTMLRendererVisitor(writer);
			if(curCls.equals(cls)) {
				writer.write("<b>");
			}
			curCls.accept(v);
			if(curCls.equals(cls)) {
				writer.write("</b>");
			}
			writer.write("</td></tr>");
			// Need to prevent infinite recursion.
			Collection subClses = curCls.getNamedSubclasses();
			for(Iterator subClsIt = subClses.iterator(); subClsIt.hasNext();) {
				RDFSClass curSubCls = (RDFSClass) subClsIt.next();
				// If the curent subclass contains the current class
				// then infinite recurision will occur - remove the current
				// subclass after inserting it
				if(curSubCls.getSubclasses(false).contains(curCls) &&
				   curCls.equals(cls) == false) {
					writer.write("<tr><td>");
					insertIndent(indent + 1);
					writer.write("&#8225;");
					HTMLRendererVisitor veq = new HTMLRendererVisitor(writer);
					if(curSubCls.equals(cls)) {
						writer.write("<b>");
					}
					curSubCls.accept(veq);
					if(curSubCls.equals(cls)) {
						writer.write("</b>");
					}
					writer.write("</td></tr>");
					subClsIt.remove();
				}
			}
			subClses.retainAll(restictTo);
			insertClasses(subClses, indent + 1, restictTo);
		}
	}

	private void insertIndent(int indent) throws IOException {
		for(int i = 0; i < indent; i++) {
			writer.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}

	}
}

