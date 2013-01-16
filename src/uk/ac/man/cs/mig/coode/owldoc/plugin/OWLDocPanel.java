package uk.ac.man.cs.mig.coode.owldoc.plugin;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.NamespaceManager;
import edu.stanford.smi.protegex.owl.model.impl.OWLUtil;
import edu.stanford.smi.protegex.owl.jena.Jena;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

import uk.ac.man.cs.mig.coode.owldoc.gen.OWLDocPreferences;
import uk.ac.man.cs.mig.coode.owldoc.lang.OWLDocLanguageHelper;
import uk.ac.man.cs.mig.coode.owldoc.lang.LanguageMap;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 22, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocPanel extends JPanel {

	private OWLModel model;

	private JTextField pathField;
	
	private JTextField styleSheetField;

	private JCheckBox useBrowserTextBox;

	private JCheckBox generateAbstractSyntaxBox;

	private JCheckBox generateUsageBox;

	private JComboBox languageBox;

	private JCheckBox showPageInBrowserBox;

	private JTable namespaceTable;

	private ArrayList generatedPrefixes;




	public OWLDocPanel(OWLModel model) {
		this.model = model;
		this.generatedPrefixes = new ArrayList();
		generatedPrefixes = new ArrayList();
		NamespaceManager manager = model.getNamespaceManager();
			manager = model.getNamespaceManager();
			for(Iterator it = manager.getPrefixes().iterator(); it.hasNext(); ) {
				String curPrefix = (String)it.next();
				if(manager.isModifiable(curPrefix)) {
				//if(manager.isModifiable(curPrefix)) {
					//String curURI = Jena.getURIFromNamespace(manager.getNamespaceForPrefix(curPrefix));
					//if(model.getDefaultOWLOntology().getImports().contains(curURI)) {
					GenerateOWLDocForPrefix gen = new GenerateOWLDocForPrefix(curPrefix);
					gen.setGenerateOWLDoc(true);
					generatedPrefixes.add(gen);
					//}
				}
			}
		createUI();

	}

	private void createUI() {
		setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
		                                                GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 0, 0);
		add(new JLabel("Output Path:"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 100.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		File baseDirectory = OWLDocPreferences.getInstance().getBaseDirectory();
		String pathName = "";
		if(baseDirectory != null) {
			pathName = baseDirectory.getPath();
		}
		pathField = new JTextField(pathName);
		add(pathField, gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		JButton browseButton = new JButton(new AbstractAction("Browse...") {
			public void actionPerformed(ActionEvent e) {
				browse();
			}
		});
		add(browseButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 100.0;
		gbc.weighty = 100.0;
		gbc.fill = GridBagConstraints.BOTH;
		namespaceTable = new JTable(new NamespaceTableModel());
		namespaceTable.setPreferredScrollableViewportSize(new Dimension(400, 70));
		namespaceTable.setShowHorizontalLines(true);
		namespaceTable.setShowVerticalLines(true);
		add(new JScrollPane(namespaceTable), gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 100.0;
		gbc.weighty = 0.0;
		useBrowserTextBox = new JCheckBox("Use Browser Text For Index",
		                                  OWLDocPreferences.getInstance().isUseBrowserText());
		add(useBrowserTextBox, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 100.0;
		gbc.weighty = 0.0;
		generateAbstractSyntaxBox = new JCheckBox("Generate Abstract Syntax",
		                                          OWLDocPreferences.getInstance().isGenerateAbstractSyntax());
		add(generateAbstractSyntaxBox, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 100.0;
		gbc.weighty = 0.0;
		generateUsageBox = new JCheckBox("Generate Usage",
		                                 OWLDocPreferences.getInstance().isGenerateUsage());
		add(generateUsageBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		add(new JLabel("Label Language:"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		languageBox = new JComboBox(LanguageMap.getInstance().getAvailableLanguages().toArray());
		add(languageBox, gbc);

		String osName = System.getProperty("os.name");
		if(osName.indexOf("Windows") != -1 || osName.indexOf("OS X") != -1) {
			gbc.gridx = 1;
			gbc.gridy = 7;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			showPageInBrowserBox = new JCheckBox("Open in browser when finished",
			                                     OWLDocPluginPreferences.getInstance().isOpenPageInBrowser());
			add(showPageInBrowserBox, gbc);
		}
		
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		add(new JLabel("Stylesheet name:"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		styleSheetField = new JTextField("");
		add(styleSheetField, gbc);
		
	}

	private void browse() {
		JFileChooser chooser = new JFileChooser("~/");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			pathField.setText(chooser.getSelectedFile().getPath());
		}
	}

	private class NamespaceTableModel extends AbstractTableModel {

		private final String [] columnNames = new String [] {"Prefix", "URI", "Generate OWLDoc"};

		public NamespaceTableModel() {
		}

		public String getColumnName(int column) {
			return columnNames[column];
		}


		public int getRowCount() {
			return generatedPrefixes.size();
		}


		public int getColumnCount() {
			return 3;
		}


		public Class getColumnClass(int columnIndex) {
			if(columnIndex == 2) {
				return Boolean.class;
			}
			else {
				return String.class;
			}
		}


		public Object getValueAt(int rowIndex,
		                         int columnIndex) {

			GenerateOWLDocForPrefix generateOWLDocForPrefix = (GenerateOWLDocForPrefix) generatedPrefixes.get(rowIndex);
			if(columnIndex == 2) {
				return new Boolean(generateOWLDocForPrefix.isGenerateOWLDoc());
			}
			else {
				if(columnIndex == 0) {
					return generateOWLDocForPrefix.getPrefix();
				}
				else {
					return model.getNamespaceManager().getNamespaceForPrefix(generateOWLDocForPrefix.getPrefix());
				}
			}
		}


		public void setValueAt(Object aValue,
		                       int rowIndex,
		                       int columnIndex) {
			GenerateOWLDocForPrefix generateOWLDocForPrefix = (GenerateOWLDocForPrefix) generatedPrefixes.get(rowIndex);
			generateOWLDocForPrefix.setGenerateOWLDoc(((Boolean) aValue).booleanValue());
		}


		public boolean isCellEditable(int rowIndex,
		                              int columnIndex) {
			if(columnIndex == 2) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public void applyOptions() {
		OWLDocPreferences prefs = OWLDocPreferences.getInstance();
		prefs.setBaseDirectory(new File(pathField.getText()));
		prefs.setGenerateAbstractSyntax(generateAbstractSyntaxBox.isSelected());
		prefs.setGenerateUsage(generateUsageBox.isSelected());
		prefs.setUseBrowserText(useBrowserTextBox.isSelected());
		prefs.setStyleSheet(styleSheetField.getText());
		prefs.removeAllIncludedNamespaces();
		LanguageMap.getInstance().setCurrentLanguage((String)languageBox.getSelectedItem());
		for(Iterator it = generatedPrefixes.iterator(); it.hasNext();) {
			GenerateOWLDocForPrefix gen = (GenerateOWLDocForPrefix) it.next();
			if(gen.isGenerateOWLDoc()) {
				prefs.addIncludedNamespace(gen.getPrefix());
			}
		}
		if(showPageInBrowserBox != null) { // Might be on linux where this doesn't work
			OWLDocPluginPreferences.getInstance().setOpenPageInBrowser(showPageInBrowserBox.isSelected());
		}
	}
	private class GenerateOWLDocForPrefix {
			private String prefix;
			private boolean generateOWLDoc;

			public GenerateOWLDocForPrefix(String prefix) {
				this.prefix = prefix;
			}


			public boolean isGenerateOWLDoc() {
				return generateOWLDoc;
			}


			public void setGenerateOWLDoc(boolean b) {
				this.generateOWLDoc = b;
			}


			public String getPrefix() {
				return prefix;
			}
		}

}

