package uk.ac.man.cs.mig.coode.owldoc.gen;

import java.io.File;
import java.util.Collection;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 21, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class ContentsIndexEntry {

	private String displayName;

	private String fileName;

	private File baseDirectory;

	private Collection resources;


	public ContentsIndexEntry(String displayName,
	               String fileName,
	               File baseDirectory,
	               Collection resources) {
		this.displayName = displayName;
		this.fileName = fileName;
		this.baseDirectory = baseDirectory;
		this.resources = resources;
	}


	public String getDisplayName() {
		return displayName;
	}


	public String getFileName() {
		return fileName;
	}


	public File getBaseDirectory() {
		return baseDirectory;
	}


	public Collection getResources() {
		return resources;
	}

}

