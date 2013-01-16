package uk.ac.man.cs.mig.coode.owldoc.plugin;

import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocPreferences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 22, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocDialog extends JDialog {

	private OWLDocPanel owlDocPanel;

	private boolean okPressed;

	public OWLDocDialog(OWLDocPanel panel) {
		super((Frame)null, "OWLDoc", true);
		this.owlDocPanel = panel;
		okPressed = false;
		createUI();
	}

	private void createUI() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(owlDocPanel);
		JPanel buttonHolderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 7, 7));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 0, 7, 7));
		buttonPanel.add(new JButton(new AbstractAction("OK") {
			public void actionPerformed(ActionEvent e) {
				okPressed = true;
				if(applyAndValidateOptions()) {
					dispose();
				}
			}
		}));
		buttonPanel.add(new JButton(new AbstractAction("Cancel") {
			public void actionPerformed(ActionEvent e) {
				okPressed = false;
				dispose();
			}
		}));
		buttonHolderPanel.add(buttonPanel);
		panel.add(buttonHolderPanel, BorderLayout.SOUTH);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);
		Dimension screenSize = getToolkit().getScreenSize();
		pack();
		setLocation(screenSize.width / 2  - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
	}

	public boolean showDialog () {
		super.show();
		return okPressed;
	}

	private boolean applyAndValidateOptions() {
		owlDocPanel.applyOptions();
		File f = OWLDocPreferences.getInstance().getBaseDirectory();
		if(f != null) {
			if(f.isDirectory()) {
				return true;
			}
		}
		showInvalidFolderMessage();
		return false;
	}

	private void showInvalidFolderMessage() {
		JOptionPane.showMessageDialog(this,
						                              "You must specify a folder which the OWLDoc will be generated in.",
						                              "Invalid folder specified",
						                              JOptionPane.ERROR_MESSAGE);

	}


}

