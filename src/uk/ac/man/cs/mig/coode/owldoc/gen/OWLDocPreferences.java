package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLProperty;
import edu.stanford.smi.protegex.owl.model.RDFProperty;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.ref.WeakReference;
import java.io.File;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 20, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocPreferences {

	private static OWLDocPreferences instance;

	private HashSet excludedProperties;

	private HashSet includedNamespaces;

	private boolean generateAbstractSyntax;

	private boolean useBrowserText;

	private boolean generateUsage;
	
	private String styleSheet;

	private File baseDirectory;

	private OWLDocPreferences() {
		excludedProperties = new HashSet();
		includedNamespaces = new HashSet();
		includedNamespaces.add(null);
		includedNamespaces.add("dc");
		styleSheet="";
	}

	public synchronized static OWLDocPreferences getInstance() {
		if(instance == null) {
			instance = new OWLDocPreferences();
		}
		return instance;
	}

	public void addExcludedProperty(RDFProperty property) {
		excludedProperties.add(new WeakReference(property));
	}

	public void removeExcludedProperty(RDFProperty property) {
		for(Iterator it = excludedProperties.iterator(); it.hasNext();) {
			WeakReference curRef = (WeakReference) it.next();
			if(curRef.get() == null) {
				it.remove();
			}
			else if(curRef.get().equals(property)) {
				it.remove();
			}
		}
	}

	public Collection getExcludedProperties() {
		ArrayList list = new ArrayList();
		for(Iterator it = excludedProperties.iterator(); it.hasNext();) {
			WeakReference curRef = (WeakReference) it.next();
			if(curRef.get() == null) {
				it.remove();
			}
			else {
				list.add(curRef.get());
			}
		}
		return list;
	}


	public boolean isGenerateAbstractSyntax() {
		return generateAbstractSyntax;
	}


	public void setGenerateAbstractSyntax(boolean generateAbstractSyntax) {
		this.generateAbstractSyntax = generateAbstractSyntax;
	}


	public boolean isUseBrowserText() {
		return useBrowserText;
	}


	public void setUseBrowserText(boolean useBrowserText) {
		this.useBrowserText = useBrowserText;
	}

	public void addIncludedNamespace(String prefix) {
		includedNamespaces.add(prefix);
	}

	public void removeIncludedNamespace(String prefix) {
		includedNamespaces.remove(prefix);
	}

	public boolean isIncludedNamespace(String prefix) {
		return includedNamespaces.contains(prefix);
	}

	public void removeAllIncludedNamespaces() {
		includedNamespaces.clear();
		includedNamespaces.add(null);
	}

	public Collection getIncludedNamespaces() {
		return new HashSet(includedNamespaces);
	}


	public boolean isGenerateUsage() {
		return generateUsage;
	}


	public void setGenerateUsage(boolean generateUsage) {
		this.generateUsage = generateUsage;
	}


	public File getBaseDirectory() {
		return baseDirectory;
	}


	public void setBaseDirectory(File baseDirectory) {
		if(baseDirectory.isDirectory()) {
			this.baseDirectory = baseDirectory;
		}
	}

	public String getStyleSheet() {
		return styleSheet;
	}

	public void setStyleSheet(String styleSheet) {
		
			this.styleSheet = styleSheet;
		
	}
}

