package org.squanto.init.util;

import org.squanto.init.codes.*;
import org.squanto.init.document.*;

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
 
 /*	The 'simple search' toolbar. The user can specify which files and in which 
  *	mode they want to search, and the search term, which can be typed or selected
  *	from the ontology
  */
 public class SimpleSearch extends JPanel
 {
 	private JButton findButton, clearButton, ontologyButton;
 	private JTextField input;
 	private JLabel searchForLabel, searchInLabel, fileLabel;
 	private JComboBox searchInBox, fileBox;
 	private ButtonActionListener buttonListener;
 	private ComboActionListener comboListener;
 	
 	private String searchInChoice;
 	private String fileChoice;
 	private File localFile;
 	private boolean subclassesIncluded;
 	private RDFSClass selectedClass;
 	private String ontologyFileName = "docs/Grampian.owl";
 	private ArrayList docs;
 	private JEditorPane documentPane;
 	
 	public static final String FIND = "Find now";
 	public static final String CLEAR = "Clear";
 	public static final String ONTOLOGY = "Select structured code";
 	
 	public static final String LOCAL_FILE = "Local file";
 	public static final String CURRENT_DOC = "Current document";
 	public static final String PROJECT_DOC = "All project documents";
 	public static final String RELATED_RESOURCE = "Related resource";
 	
 	public static final String CODES = "Codes";
 	public static final String TEXT = "Text";
 	public static final String ANNOTATIONS = "Annotations";
 	public static final String MEMOS = "Memos";
 	
 	private int intX = 550;
 	private int intY = 50;
 	
 	public SimpleSearch(ArrayList documents, JEditorPane pane)
 	{
 		super();
 		docs = documents;
 		documentPane = pane;
 		init();
 	}
 	
 	private void init()
 	{ 	 		
 		buttonListener = new ButtonActionListener();
 		comboListener = new ComboActionListener();
 		
 		//create 'search for' label
 		searchForLabel = new JLabel("Search for:");
		searchForLabel.setBounds(5, 5, 70, 18);
		add(searchForLabel);
 		
 		//create 'search for' text field
 		input = new JTextField();
 		input.setEditable(true);
		input.setBounds(85, 5, 130, 18);
		add(input);
 		
 		//create 'structured code' button
 		ontologyButton = new JButton(ONTOLOGY);
 		ontologyButton.setBounds(5, 27, 210, 18);
		ontologyButton.addActionListener(buttonListener);
		add(ontologyButton);
		
		//create 'search in' label
 		searchInLabel = new JLabel("Search in:");
		searchInLabel.setBounds(225, 5, 70, 18);
		add(searchInLabel);
		
 		//create 'search in' drop down menu
 		String[] searchInList = {CODES, ANNOTATIONS, MEMOS, TEXT};
 		searchInBox = new JComboBox(searchInList);
 		searchInBox.addActionListener(comboListener);
 		searchInBox.setBounds(305, 5, 130, 18);
 		add(searchInBox);
 		
 		//create 'search file' label
 		fileLabel = new JLabel("Search file:");
		fileLabel.setBounds(225, 27, 70, 18);
		add(fileLabel);
 		
 		//create 'search files' drop down menu
 		String[] fileList = {CURRENT_DOC, PROJECT_DOC, LOCAL_FILE, RELATED_RESOURCE};
 		fileBox = new JComboBox(fileList);
 		fileBox.addActionListener(comboListener);
 		fileBox.setBounds(305, 27, 130, 18);
 		add(fileBox);
 		
 		//create 'find now' button
 		findButton = new JButton(FIND);
 		findButton.setBounds(445, 5, 100, 18);//change these
		findButton.addActionListener(buttonListener);
		add(findButton);
		
 		//create 'clear' button
 		clearButton = new JButton(CLEAR);
 		clearButton.setBounds(445, 27, 100, 18);//change these
		clearButton.addActionListener(buttonListener);
		add(clearButton);
		
		//lay-out of the panel
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		setLayout(null);   
		setBackground(Color.lightGray );
		setSize(intX, intY);
    	setLocation((screenSize.width - intX)/2, (screenSize.height - intY)/2);		
		setVisible (true);
 	}
 	
 	/*	Listens to the buttons 'find now', 'clear' and 'select structured code'
 	 */
 	class ButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
 		{
 			JButton button = (JButton) e.getSource();
 			String label = button.getLabel();
 			if (label.equals(FIND))
 			{	
 				if (input.getText().equals(""))	//show warning
 					JOptionPane.showMessageDialog(null, "Please specify a search term", "No search term specified", JOptionPane.WARNING_MESSAGE);
 				else
 				{	//delete obsolete selectedClass
 					if (selectedClass == null);
 					else if (input.getText().indexOf(selectedClass.getName()) < 0)
 						selectedClass = null;
 					//initialise the choice variables (if necessary) 
 					if (searchInChoice == null)
	 					searchInChoice = (String) searchInBox.getSelectedItem();
	 				if (fileChoice == null)
	 					fileChoice = (String) fileBox.getSelectedItem();
	 				if (fileChoice.equals(LOCAL_FILE))
		 				System.out.println(searchInChoice);
		 			//conduct the search
		 			Search search;
		 			if (selectedClass == null)
			 			search = new Search(input.getText(), searchInChoice, fileChoice, localFile, docs, documentPane);
		 			else
		 				search = new Search(selectedClass, subclassesIncluded, searchInChoice, fileChoice, localFile, docs, documentPane);
	 				search.search();
	 			}	 				
 			}
 			else if (label.equals(CLEAR))
 			{	//clear the input text field
 				input.setText("");
 				selectedClass = null;
 			}
 			else if (label.equals(ONTOLOGY))
 			{	//show the ontology and let the user select a class
 				showOntology();
 			}
 		}
	}
	
	/*	Listens to the combo-boxes and initialises the choice variables.
	 *	If the user chooses 'local file', lets him/her browse.
	 */
	class ComboActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
 		{
 			JComboBox box = (JComboBox) e.getSource();
 			if (box.equals(searchInBox))
 				searchInChoice = (String) box.getSelectedItem();
 			else if (box.equals(fileBox))
 			{
 				fileChoice = (String) box.getSelectedItem();
 				if (fileChoice.equals(LOCAL_FILE))
 				{
 					JFileChooser chooser = new JFileChooser();
					int returnVal = chooser.showOpenDialog(box.getParent());
					if (returnVal == JFileChooser.APPROVE_OPTION)
						localFile = chooser.getSelectedFile();
 				}
 			}				
 		}
	}
	
	/*	Shows the ontology and lets the user select a class
	 */
	private void showOntology()
	{
		if (ontologyFileName == null)
		{
			JOptionPane.showMessageDialog(null, "Cannot find the ontology", "No ontology found", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		FileInputStream in;
	    OWLModel owlModel = null;
	    try
	    {
	    	in = new FileInputStream(ontologyFileName);
	       	owlModel = ProtegeOWL.createJenaOWLModelFromInputStream(in);
	    }
	    catch (Exception ex) 
	    {
	    	System.out.println(ex);
	    	return;
	    }
        
        selectedClass = OWLUI.pickConcreteClass(owlModel,"Select Structured Code");
        if (selectedClass == null)
        	return;
        if (selectedClass.getNamedSubclasses().size() > 0)
        {
	        int option = JOptionPane.showConfirmDialog(null, "Do you wish to include its subclasses?", "Structured code selection", JOptionPane.YES_NO_OPTION);
	        if (option == JOptionPane.YES_OPTION)
	        	subclassesIncluded = true;
	        else if (option == JOptionPane.NO_OPTION)
	        	subclassesIncluded = false;
        }
        else
        	subclassesIncluded = false;
        
        if (subclassesIncluded)
        	input.setText(selectedClass.getName() + " (incl. subcl.)");
        else
        	input.setText(selectedClass.getName());
        
	}
 }