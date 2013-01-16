package uk.ac.man.cs.mig.coode.owldoc.lang;

import edu.stanford.smi.protege.Application;
import edu.stanford.smi.protege.plugin.PluginUtilities;
import edu.stanford.smi.protege.ui.ProjectManager;

import java.io.File;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 21, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocLanguageHelper {

	private static String languageFolderPath;

	public static void setLangauageFolderPath(String path) {
		languageFolderPath = path;
	}
	public static File getLangauageFolder() {
		if(languageFolderPath != null) {
			File f = new File(languageFolderPath);
			if(f.exists() && f.isDirectory()) {
				return f;
			}
			else {
				return null;
			}
		}
		return null;
	}

	public static String escape(String s) {
		return StringEscapeUtils.escapeHtml(s);
	}
}

