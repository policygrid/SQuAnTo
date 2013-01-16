package uk.ac.man.cs.mig.coode.owldoc.plugin;

import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocHelper;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocPreferences;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocNameCache;

import javax.swing.*;
import java.io.File;

import edu.stanford.smi.protegex.owl.model.OWLModel;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 24, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocPluginHelper {

	public static void generateOWLDoc(OWLModel model) {
		showDialog(model);
		File baseDirectory = OWLDocPreferences.getInstance().getBaseDirectory();
		if(baseDirectory != null) {
			OWLDocHelper.generateOWLDoc(model, baseDirectory);
			if(OWLDocPluginPreferences.getInstance().isOpenPageInBrowser()) {
				BrowserInvoker.showPage(new File(baseDirectory, "index.html").toString());
			}
			else {
				JOptionPane.showMessageDialog(null, "OWLDoc generated to " + baseDirectory.toString(), "OWLDoc Generated",
				                              JOptionPane.PLAIN_MESSAGE);
			}
		}
		OWLDocNameCache.getInstance().emptyCache();
	}


	private static boolean showDialog(OWLModel model) {
		OWLDocPanel panel = new OWLDocPanel(model);
		OWLDocDialog dlg = new OWLDocDialog(panel);
		dlg.pack();
		if(dlg.showDialog()) {
			panel.applyOptions();
			return true;
		}
		else {
			return false;
		}
	}
}

