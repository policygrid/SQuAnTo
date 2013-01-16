package org.squanto.init.util;

import org.squanto.init.codes.*;

import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.net.*;

import edu.stanford.smi.protege.model.*;
import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.model.event.*;
import edu.stanford.smi.protegex.owl.*;      
import edu.stanford.smi.protegex.owl.ui.*;
import edu.stanford.smi.protegex.owl.ui.icons.*;
import edu.stanford.smi.protegex.owl.ui.widget.*;


/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Feikje Hielkema
 * @version 1.0
 *
 */
 
 public class AdvancedSearchDialog extends JDialog
 {
 	private String searchField;		//current doc, project docs, related resources or local file
 	private File localFile;
 	private String contentType;		//codes, annotation, memo, tezt
 	private String searchType;		//regular, co-occurrance of specified codes, correlations
 	private Code[] codes;
 	
 	public static final String CURRENT_DOC = "Current Document";
 	public static final String PROJECT_DOC = "Project Documents";
 	public static final String RELATED_RESOURCE = "Related Resources";
 	public static final String LOCAL_FILE = "Local File";
 	
 	public static final String CODE = "Codes";
 	public static final String ANNOTATION = "Annotations";
 	public static final String MEMO = "Memos";
 	public static final String TEXT = "Text";
 	
 	public static final String OCCURRENCE = "Occurrence of specified code(s)";
 	public static final String CO_OCCURRENCE = "Co-occurrence of specified codes";
 	public static final String CORRELATION = "Co-occurrence of any codes";
 	
 	public static final String ADD = ">>";
 	public static final String REMOVE = "<<";
 	public static final String ONTOLOGY = "Ontology";
 	
 	private int intX = 700;
 	private int intY = 450;
 	 	
 	public AdvancedSearchDialog(java.awt.Frame fr, String title)
 	{
 		super(fr, title);
 		chooseSearchField();
 		chooseSearchType();
 		chooseCode();
	}
	
	/* Add some actionlisteners */
	private void chooseSearchField()
	{
		Object[] possibleValues = {CURRENT_DOC, PROJECT_DOC, RELATED_RESOURCE, LOCAL_FILE};
		Object selectedValue = JOptionPane.showInputDialog(this, "Which file(s) do you want to search?", 
		"Advanced search", JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);
		searchField = (String) selectedValue;
		
		if (searchField.equals(LOCAL_FILE))
		{
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
				localFile = chooser.getSelectedFile();
		}
	}
	
	private void chooseSearchType()
	{
		Object[] possibleValues = {CODE, ANNOTATION, MEMO, TEXT};
		Object selectedValue = JOptionPane.showInputDialog(this, "In what information type do you want to search?", 
		"Advanced Search", JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);
		contentType = (String) selectedValue;
		
		if (contentType.equals(CODE))
		{
			Object[] values = {OCCURRENCE, CO_OCCURRENCE, CORRELATION};
			Object value = JOptionPane.showInputDialog(this, "What do you want to search for?", 
			"Advanced Search", JOptionPane.QUESTION_MESSAGE, null, values, values[0]);
			searchType = (String) value;			
		}
	}
	
	private void chooseCode()
	{
		JDialog dialog = new JDialog(this, "Advanced Search");
		ActionListener listen = new AdvancedActionListener();
		
		JLabel summaryLabel = new JLabel("Selected codes:");
		summaryLabel.setBounds(375, 20, 275, 20);
		dialog.getContentPane().add(summaryLabel);
		
		JTextField summary = new JTextField("");
		summary.setEditable(true);
		summary.setBounds(375, 50, 275, 350);
		dialog.getContentPane().add(summary);
		
		JLabel inputLabel = new JLabel("Type code or add from ontology:");
		inputLabel.setBounds(50, 145, 200, 20);
		dialog.getContentPane().add(inputLabel);
							
		JTextField input = new JTextField("");
		input.setEditable(true);
		input.setBounds(50, 175, 200, 20);
		dialog.getContentPane().add(input);
		
		JButton add = new JButton(ADD);
		add.setBounds(300, 150, 50, 20);
		add.addActionListener(listen);
		dialog.getContentPane().add(add);
		
		JButton remove = new JButton(REMOVE);
		remove.setBounds(300, 200, 50, 20);
		remove.addActionListener(listen);
		dialog.getContentPane().add(remove);
		
		JButton ontology = new JButton(ONTOLOGY);
		ontology.setBounds(100, 210, 100, 20);
		ontology.addActionListener(listen);
		dialog.getContentPane().add(ontology);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		dialog.getContentPane().setLayout(null);   
		dialog.setBackground(Color.lightGray );
		dialog.setSize(intX, intY);
    	dialog.setLocation((screenSize.width - intX)/2, (screenSize.height - intY)/2);		
		dialog.setVisible (true);
		dialog.setResizable(false);	
	}
	
	class AdvancedActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
 		{
 			JButton button = (JButton) e.getSource();
 			String label = button.getLabel();
 			System.out.println(label);
 //			if (label.equals(ADD))
 			
 		}
	}
	
	private void showOntology(String fileName)
	{
		FileInputStream in = null;
	    OWLModel owlModel = null;
	    
	    try
	    {
	    	in = new FileInputStream(fileName);
	       	owlModel = ProtegeOWL.createJenaOWLModelFromInputStream(in);
	    }
	    catch (Exception ex) 
	    {
	    	System.out.println(ex);
	    }
        
        OWLUI.pickConcreteClass(owlModel,"Select Structured Code");
	}
	
 }