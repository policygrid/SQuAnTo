package uk.ac.man.cs.mig.coode.owldoc.plugin;

import edu.stanford.smi.protege.ui.ProjectMenuBar;
import edu.stanford.smi.protege.ui.ProjectView;
import edu.stanford.smi.protege.ui.ProjectToolBar;
import edu.stanford.smi.protege.plugin.ProjectPluginAdapter;
import edu.stanford.smi.protege.plugin.PluginUtilities;
import edu.stanford.smi.protege.Application;
import edu.stanford.smi.protegex.owl.model.OWLModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import com.hp.hpl.jena.vocabulary.OWL;
import uk.ac.man.cs.mig.coode.owldoc.lang.OWLDocLanguageHelper;
import uk.ac.man.cs.mig.coode.owldoc.doclet.DocletManager;
import uk.ac.man.cs.mig.coode.owldoc.doclet.standard.ASHTMLDoclet;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocProjectPlugin extends ProjectPluginAdapter {

	public static final String MENU_NAME = "OWLDoc...";

	public void afterShow(ProjectView projectView,
	                      ProjectToolBar projectToolBar,
	                      ProjectMenuBar projectMenuBar) {

		if(projectView.getProject().getKnowledgeBase() instanceof OWLModel) {
			OWLModel model = (OWLModel) projectView.getProject().getKnowledgeBase();
			JMenu toolsMenu = getToolsMenu(projectMenuBar);
			JMenuItem item = new JMenuItem(new GenerateOWLDocAction(model));
			toolsMenu.add(item);
		}
		File pluginsFolder = PluginUtilities.getPluginsDirectory();
		File owlDocFolder = new File(pluginsFolder, "uk.ac.man.cs.mig.coode.owldoc");
		File langFolder = new File(owlDocFolder, "languages");
		if(langFolder.exists() == false) {
			langFolder.mkdir();
		}
		OWLDocLanguageHelper.setLangauageFolderPath(langFolder.getPath());
		DocletManager.getInstance().registerDoclet(new ASHTMLDoclet());
	}


	private JMenu getToolsMenu(ProjectMenuBar projectMenuBar) {
		JMenu toolsMenu = null;
		for(int i = 0; i < projectMenuBar.getMenuCount(); i++) {
			Component curComponent = projectMenuBar.getMenu(i);
			if(curComponent instanceof JMenu) {
				JMenu curMenu = (JMenu) curComponent;
				String name = curMenu.getText();
				if(name != null) {
					if(name.equals("Tools")) {
						toolsMenu = curMenu;
					}
				}
			}
		}
		if(toolsMenu == null) {
			toolsMenu = new JMenu("Tools");
			projectMenuBar.add(toolsMenu);
		}
		return toolsMenu;
	}

	public static void main(String [] args) {
		Application.main(args);
	}
}

