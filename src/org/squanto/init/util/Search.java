package org.squanto.init.util;

import org.squanto.init.document.*;
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
 
 /*	The search engine
  */
 public class Search
 {
 	private RDFSClass selectedClass;			//structured code
 	private boolean subclassesIncluded;			//whether subclasses should be included
 	private String searchTerm;					//free code
 	private String searchInChoice;				//text, codes, annotations or text
 	private String fileChoice;					//current doc, project docs, related resources or local file
 	private File localFile;						//local file
 	private ArrayList documents;				//open documents
 	private DefaultHighlighter.DefaultHighlightPainter highlight = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
 	private JEditorPane documentPane;			//pane in main gui that shows the current document
  	
  	/*	Constructor for a free code search term
  	 */
 	public Search(String term, String searchIn, String file, File local, ArrayList main, JEditorPane pane)
 	{
 		searchTerm = term;
 		searchInChoice = searchIn;
 		fileChoice = file;
 		localFile = local;
 		documents = main;
 		documentPane = pane;
 	}
 	
 	/*	Constructor for a structured code search term
 	 */
 	public Search(RDFSClass cl, boolean subclasses, String searchIn, String file, File local, ArrayList main, JEditorPane pane)
 	{
 		selectedClass = cl;
 		subclassesIncluded = subclasses;
 		searchInChoice = searchIn;
 		fileChoice = file;
 		localFile = local;
 		documents = main;
 		documentPane = pane;
 	}
 	
 	/*	This function is called from outside to initiate the search
 	 */
 	public void search()
 	{	
 		if (fileChoice.equals(SimpleSearch.CURRENT_DOC))	//focus on searching in current document for now
 			searchInCurrent();
 		else
 		{
 			JOptionPane.showMessageDialog(null, "Sorry, that function is not supported yet", "No support", JOptionPane.WARNING_MESSAGE);
 			return;
 		}
 		    
 	}
 	
 	/*	Search for something in the current document
 	 */
 	private void searchInCurrent()
 	{
 		Document doc = documentPane.getDocument();
 		if (doc == null)
 		{
 			JOptionPane.showMessageDialog(null, "Please open a document first", "No current document", JOptionPane.WARNING_MESSAGE);
 			return;
 		}
 		
 		if (selectedClass != null)					
 			searchClassInCurrent(doc);
      	else if (searchTerm != null)
       		searchTermInCurrent(doc);
 	}
 	
 	/*	Searches and highlights all occurrences of the given free code in the
 	 *	current document
 	 */
 	private void searchTermInCurrent(Document doc)
 	{
 		if(searchInChoice.equals(SimpleSearch.TEXT))
 		{
 			String content = null;
 			try	//get document content
 			{
	 			content = doc.getText(0, doc.getLength());
	 		}
	 		catch(BadLocationException e)
	 		{
	 			return;
	 		}
	 		documentPane.getHighlighter().removeAllHighlights();	//remove obsolete highlights
	 		highlightInCurrent(content, searchTerm);	//highlight the term in the document
 		}
 		else if (searchInChoice.equals(SimpleSearch.CODES))
 		{	//THIS MAY NOT ALWAYS WORK. IDEALLY, ALL OPEN DOCUMENTS SHOULD SHARE THEIR CODES?
 			MainDocumentDAO maindoc = (MainDocumentDAO) documents.get(0);	
 			ArrayList existingCodes = maindoc.getCodes();		//get all codes the user has used
 			Iterator itExist = existingCodes.iterator();
 			while (itExist.hasNext())
 			{
 				Code code = (Code) itExist.next();
 				String name = code.getName().toLowerCase();
 				searchTerm = searchTerm.toLowerCase();
 				if (name.equals(searchTerm))
 				{
 					ArrayList sectionList = code.getSectionList();
				
         	 		if(!sectionList.isEmpty())
         	 		{
		            	Iterator it = sectionList.iterator();
		            	while (it.hasNext()) 
		            	{
              				Section section = (Section) it.next();
							try 
							{
                				documentPane.getHighlighter().addHighlight(section.getSectionStart(), section.getSectionEnd(), highlight);
              				} 
              				catch (BadLocationException ex) 
              				{}
            			}
 					}
 					return;	//ASSUMING THERE ARE NOT 2 IDENTICAL CODES IN EXISTENCE
 				}
 			}
 		}
 		else	//focus on searching in plain text for now
 			JOptionPane.showMessageDialog(null, "Sorry, that function is not supported yet", "No support", JOptionPane.WARNING_MESSAGE);
 	}
 	
 	/*	Searches and highlights all occurrences of the given structured code
 	 *	(textually or as coded material)
 	 *	and (if specified) its subclasses in the current document
 	 *	Though actually, if there is a free code with the same name, those 
 	 *	occurrences will probably get highlighted too
 	 */
 	private void searchClassInCurrent(Document doc)
 	{
 		if(searchInChoice.equals(SimpleSearch.MEMOS) || searchInChoice.equals(SimpleSearch.ANNOTATIONS))	//focus on searching in plain text for now
 		{
 			JOptionPane.showMessageDialog(null, "Sorry, that function is not supported yet", "No support", JOptionPane.WARNING_MESSAGE);
 			return;
 		}
 				
 		documentPane.getHighlighter().removeAllHighlights();
 		String content = null;
 		
 		try	//get document content
 		{
	 		content = doc.getText(0, doc.getLength());
	 	}
	 	catch(BadLocationException e)
	 	{
	 		return;
	 	}
	 	
	 	if (searchInChoice.equals(SimpleSearch.TEXT))
	 	{	//THIS MAY NOT ALWAYS WORK. IDEALLY, ALL OPEN DOCUMENTS SHOULD SHARE THEIR CODES?
 			MainDocumentDAO maindoc = (MainDocumentDAO) documents.get(0);	
 			ArrayList existingCodes = maindoc.getCodes();		//get all codes the user has used
		 	ArrayList codes = getCodes(existingCodes, false);
		 	
 			for (int i = 0; i < codes.size(); i++)
 			{
 				Code code = (Code) codes.get(i);
  				System.out.println(code.getName());	 		
 				highlightInCurrent(content, code.getName());
			}
			return;
 		}
 		else if (searchInChoice.equals(SimpleSearch.CODES))
 		{	//THIS MAY NOT ALWAYS WORK. IDEALLY, ALL OPEN DOCUMENTS SHOULD SHARE THEIR CODES?
 			MainDocumentDAO maindoc = (MainDocumentDAO) documents.get(0);	
 			ArrayList existingCodes = maindoc.getCodes();		//get all codes the user has used
		 	ArrayList codes = getCodes(existingCodes, true);
		 	
		 	for (int i = 0; i < codes.size(); i++)
 			{
 				Code code = (Code) codes.get(i);
 				ArrayList sectionList = code.getSectionList();
				
         	 	if(!sectionList.isEmpty())
         	 	{
		            Iterator it = sectionList.iterator();
		            while (it.hasNext()) 
		            {
              			Section section = (Section) it.next();
						try 
						{
                			documentPane.getHighlighter().addHighlight(section.getSectionStart(), section.getSectionEnd(), highlight);
              			} 
              			catch (BadLocationException ex) 
              			{}
            		}
 				}
 			}
 			return;
 		}
       	return;
 	}
	
	/*	Highlights the given phrase in the current document (you pass the document content)
	 */
	private void highlightInCurrent(String content, String term)
	{	
	 	StringBuffer termBuf = new StringBuffer(" ");
	 	termBuf.append(term.toLowerCase());
	 	int index = 0;
	 	String tempContent = content.toLowerCase();
	 	
	 	while (true)
	 	{	//search for the next occurrence of the term
	 		index = tempContent.indexOf(termBuf.toString(), index);
	 		if (index < 0)	//finished
	 			break;
	 		index++;	//set index at start of word (instead of space)
	 		char c = tempContent.charAt(index + termBuf.toString().length() - 1);
	 		//only highlight words, not phrases
	 		if (c =='.' || c == ' ' || c == ',' || c == '!' || c == '?' || c == ';' || c == ':' || c == '\"' || c == '\'' || c == '-')
	 		{
	 			try
 				{
	 				documentPane.getHighlighter().addHighlight(index, index + term.length(), highlight);
	 			}
	 			catch (BadLocationException e)
	 			{
	 				return;
	 			}
	 		}
		 }	
	}
	
 	/*	Returns a list of the codes corresponding with the selected class and,
 	 *	if specified, its subclasses
 	 */
 	private ArrayList getCodes(ArrayList existingCodes, boolean onlyExisting)
 	{	
 		ArrayList result = new ArrayList();
 		Code temp = getCode(selectedClass, existingCodes, onlyExisting);
 		if (temp != null)
 		{
			result.add(temp);
 			System.out.println("added " + temp.getName());
 		}
 		
 		if (subclassesIncluded)
 		{
 			Collection subclasses = selectedClass.getNamedSubclasses(true);
	 		Iterator it = subclasses.iterator();
 		
        	while (it.hasNext())
        	{
        		RDFSClass subClass = (RDFSClass) it.next();
        		Code temp2 = getCode(subClass, existingCodes, onlyExisting);
        		if (temp2 != null)
        		{
	        		result.add(temp2);
    	    		System.out.println("added " + subClass.getName());

    	    	}
        	}
	 	}
	 
       	return result;
 	}
 	
 	/*	Takes a class and checks whether the equivalent code exists. If so, that
 	 *	code is returned; if not, a new code is made with the same name.
 	 *	Eventually that will probably not be sufficient, but for now it will do
 	 */
 	private Code getCode(RDFSClass owlClass, ArrayList existingCodes, boolean onlyExisting)
 	{
 		Iterator it = existingCodes.iterator();
 		while (it.hasNext())
 		{
 			Code code = (Code) it.next();
        	if (owlClass.getName().equals(code.getName()))
        		return code;     			
 		}
 		if (!onlyExisting)
        {
       		Code newCode = new Code();
       		newCode.setName(owlClass.getName());
       		return newCode;
       	}
       	return null;	
 	}
 }