package uk.ac.man.cs.mig.coode.owldoc.plugin;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 24, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocPluginPreferences {


	private static OWLDocPluginPreferences instance;

	private boolean openPageInBrowser;


	private OWLDocPluginPreferences() {
		openPageInBrowser = false;
	}

	public static synchronized OWLDocPluginPreferences getInstance() {
		if(instance == null) {
			instance = new OWLDocPluginPreferences();
		}
		return instance;
	}


	public boolean isOpenPageInBrowser() {
		return openPageInBrowser;
	}


	public void setOpenPageInBrowser(boolean openPageInBrowser) {
		this.openPageInBrowser = openPageInBrowser;
	}
}

