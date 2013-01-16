package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.visitor.OWLModelVisitorHelper;
import edu.stanford.smi.protegex.owl.model.visitor.Visitable;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageKeyWords;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocContentsAndIndexGenerator extends OWLDocPageGenerator {

	private OWLModel model;

	private File baseDirectory;

	private ArrayList entries;

	public OWLDocContentsAndIndexGenerator(OWLModel model, File baseDirectory) {
		super(baseDirectory, "contents.html", LanguageMap.getInstance().getWord(LanguageKeyWords.CONTENTS));
		this.model = model;
		this.baseDirectory = baseDirectory;
		entries = new ArrayList();
	}

	public void generateContent()
	        throws IOException {
		RDFResourceNameSpaceFilterVisitor v = new RDFResourceNameSpaceFilterVisitor();
		OWLModelVisitorHelper helper = new OWLModelVisitorHelper(model, v);
		helper.visitUserDefinedOWLNamedClasses();
		helper.visitUserDefinedOWLProperties();
		// Add individuals (currently not individual method in helper!)
		for(Iterator it = model.getOWLIndividuals().iterator(); it.hasNext();) {
			((Visitable)it.next()).accept(v);
		}
		entries.add(new ContentsIndexEntry(LanguageMap.getInstance().getWord(LanguageKeyWords.ALL_RESOURCES), "IndexAllResources.html", baseDirectory, v.getAllResources()));
		entries.add(new ContentsIndexEntry(LanguageMap.getInstance().getWord(LanguageKeyWords.ALL_CLASSES), "IndexClasses.html", baseDirectory, v.getAllClasses()));
		entries.add(new ContentsIndexEntry(LanguageMap.getInstance().getWord(LanguageKeyWords.ALL_OBJECT_PROPERTIES), "IndexObjectProperties.html", baseDirectory, v.getAllObjectProperties()));
		entries.add(new ContentsIndexEntry(LanguageMap.getInstance().getWord(LanguageKeyWords.ALL_DATATYPE_PROPERTIES), "IndexDatatypeProperties.html", baseDirectory, v.getAllDatatypeProperties()));
		entries.add(new ContentsIndexEntry(LanguageMap.getInstance().getWord(LanguageKeyWords.ALL_ANNOTATION_PROPERTIES), "IndexAnnotationProperties.html", baseDirectory, v.getAllAnnotationProperties()));
		entries.add(new ContentsIndexEntry(LanguageMap.getInstance().getWord(LanguageKeyWords.INDIVIDUALS), "IndexIndividuals.html", baseDirectory, v.getAllIndividuals()));
		for(Iterator it = OWLDocPreferences.getInstance().getIncludedNamespaces().iterator(); it.hasNext();) {
			String curPrefix = (String) it.next();
			String dispPrefix;
			if(curPrefix == null) {
				dispPrefix = "&#8225;";
			}
			else {
				dispPrefix = curPrefix;
			}
			entries.add(new ContentsIndexEntry("[" + dispPrefix + "] " + LanguageMap.getInstance().getWord(LanguageKeyWords.RESOURCES), "Index" + curPrefix + "AllResources.html", baseDirectory, v.getAllResources(curPrefix)));
			entries.add(new ContentsIndexEntry("[" + dispPrefix + "] " + LanguageMap.getInstance().getWord(LanguageKeyWords.CLASSES), "Index" + curPrefix + "Classes.html", baseDirectory, v.getClasses(curPrefix)));
			entries.add(new ContentsIndexEntry("[" + dispPrefix + "] " + LanguageMap.getInstance().getWord(LanguageKeyWords.OBJECT_PROPERTIES), "Index" + curPrefix + "ObjectProperties.html", baseDirectory, v.getObjectPropertiees(curPrefix)));
			entries.add(new ContentsIndexEntry("[" + dispPrefix + "] " + LanguageMap.getInstance().getWord(LanguageKeyWords.DATATYPE_PROPERTIES), "Index" + curPrefix + "DatatypeProperties.html", baseDirectory, v.getDatatypeProperties(curPrefix)));
			entries.add(new ContentsIndexEntry("[" + dispPrefix + "] " + LanguageMap.getInstance().getWord(LanguageKeyWords.ANNOTATION_PROPERTIES), "Index" + curPrefix + "AnnotationProperties.html", baseDirectory, v.getAnnotationProperties(curPrefix)));
			entries.add(new ContentsIndexEntry("[" + dispPrefix + "] " + LanguageMap.getInstance().getWord(LanguageKeyWords.INDIVIDUALS), "Index" + curPrefix + "Individuals.html", baseDirectory, v.getIndividuals(curPrefix)));
		}

		//createContents();
		//generateIndexFiles();
	}


	private void generateIndexFiles() throws IOException {
		for(Iterator it = entries.iterator(); it.hasNext();) {
			OWLDocResourceIndexPageGenerator gen = new OWLDocResourceIndexPageGenerator((ContentsIndexEntry) it.next());
			gen.generateOWLDoc();
		}
	}


	private void createContents() throws IOException {
		OWLDocOntologyPageGenerator docGen = new OWLDocOntologyPageGenerator(model, baseDirectory);
		docGen.generateOWLDoc();
		ArrayList list = new ArrayList();
		list.add("<a href=\"ontology.html\" target=\"content\">" + LanguageMap.getInstance().getWord(LanguageKeyWords.ONTOLOGY) + "</a>");
		for(Iterator it = entries.iterator(); it.hasNext();) {
			ContentsIndexEntry curEntry = (ContentsIndexEntry) it.next();
			addContentsEntry(list, curEntry.getFileName(), curEntry.getDisplayName());
		}
		ObjectVerticalListDocGenerator gen = new ObjectVerticalListDocGenerator(this, "",
		                                                                        list);
		gen.generateOWLDoc();

	}

	private void addContentsEntry(List list, String fileName, String displayName) {
		list.add("<a href=\"" + fileName + "\" target=\"subnav\">" + displayName + "</a>");
	}
}

