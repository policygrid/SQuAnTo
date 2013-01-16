package uk.ac.man.cs.mig.coode.owldoc.plugin;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protege.Application;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.File;
import java.util.Iterator;

import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocHelper;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocPreferences;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class GenerateOWLDocAction extends AbstractAction {

	private OWLModel model;

	public GenerateOWLDocAction(OWLModel model) {
		super("Generate OWLDoc...");
		this.model = model;
	}


	public void actionPerformed(ActionEvent e) {
		OWLDocPluginHelper.generateOWLDoc(model);
	}
}

