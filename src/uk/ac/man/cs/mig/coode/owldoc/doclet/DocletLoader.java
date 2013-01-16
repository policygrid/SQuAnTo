package uk.ac.man.cs.mig.coode.owldoc.doclet;

import java.io.File;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jul 26, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class DocletLoader {

	private static String docletFolderPath;

	public static void setDocletFolderPath(String path) {
		docletFolderPath = path;
	}
	public static File getDocletFolderPath() {
		if(docletFolderPath != null) {
			File f = new File(docletFolderPath);
			if(f.exists() && f.isDirectory()) {
				return f;
			}
			else {
				return null;
			}
		}
		return null;
	}

}

