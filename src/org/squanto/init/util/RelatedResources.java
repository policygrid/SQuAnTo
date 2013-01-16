package org.squanto.init.util;

import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.event.*;
import java.net.*;

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

public class RelatedResources 
{
	
	private JEditorPane info;
	private JFrame resourceFrame;
	private JEditorPane document;
	
	public RelatedResources() throws Exception
	{ 	//initialize pane for displaying resource summary
		info = new JEditorPane();
 	//	info.setToolTipText("Related Resources");
 	/*
 		info.setEditable(false);
 		info.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
 		
 		//initialize frame for displaying documents
 		resourceFrame = new JFrame();
 		document = new JEditorPane();
 		resourceFrame.setContentPane(document);
 		document.setEditable(false);
 		//resourceFrame.setSize(700, 450);
		resourceFrame.setVisible (false);
		resourceFrame.setResizable(true);
 		
 		fillPanel();		//FOR TESTING PURPOSES
 	*/
  	}
 	
 	 //Constructor: the parameter is the list of codes in the document 
 	//See documentation for the class Code
 	public RelatedResources(ArrayList codes) throws Exception
 	{
 		this();
 		refreshPanel("");
 	}
 	
 	/*	ONLY FOR TESTING PURPOSES - SHOULD BE DELETED WHEN FINISHED */
 	private void fillPanel(String co)
 	{
 		info.setContentType("text/html");
		HTMLEditorKit editorKit = (HTMLEditorKit)info.getEditorKit();
		info.addHyperlinkListener(new MyHyperlinkListener());
		
		StringBuffer text = new StringBuffer();
		text.append("<html><font face=\"Arial\" size=\"-1\"><b>Related Resources</b></font><br>");
		text.append("<font face=\"Arial\" size=\"-1\">");
		/*
		Farmer B
		Farmer C
		Farmer D
		Farmer A Successor
		National Farmer’s Union Rep
		Scottish Farmer News Articles
*/
		
		try {
		File fl = new File("img/");

	if (co.equals("PostProductivistPolicyMeasure")) {				
		Resource r3 = new Resource("<img src=\"" + fl.toURL()+ "/person.gif\"  width=\"16\" height=\"16\"></img>","Farmer A Successor", "Macaulay", "2005", "Macaulay");
		text.append(r3.display());		
		Resource r = new Resource("<img src=\"" + fl.toURL()+ "/document.gif\"  width=\"16\" height=\"16\"></img>","The Politics of Pluriactivity in Britain. In: J Rural Studies", "Shucksmith and Winter ", "1990", "David De Roure");
		text.append(r.display());
 		Resource s = new Resource("<img src=\"" + fl.toURL()+ "/document.gif\"  width=\"16\" height=\"16\"></img>","Farm Household Strategies and Pluriactivity in Upland Scotland.", "Shucksmith and Smith", "1991", "Shucksmith and Smith");
  		text.append(s.display());
 		Resource t = new Resource("<img src=\"" + fl.toURL()+ "/document.gif\"  width=\"16\" height=\"16\"></img>","Farm Household Behaviour and the Transition to Post-Productivism.", "Shucksmith", "1993", "Shucksmith");  		
  		text.append(t.display());		
	}
	
	if (co.equals("Cattle")) {				
		Resource r1 = new Resource("<img src=\"" + fl.toURL()+ "/person.gif\"  width=\"16\" height=\"16\"></img>","Farmer B", "Macaulay", "2005", "Macaulay");
		text.append(r1.display());
		Resource r2 = new Resource("<img src=\"" + fl.toURL()+ "/person.gif\"  width=\"16\" height=\"16\"></img>","Farmer C", "Macaulay", "2005", "Macaulay");
		text.append(r2.display());
		Resource r3 = new Resource("<img src=\"" + fl.toURL()+ "/person.gif\"  width=\"16\" height=\"16\"></img>","Farmer A Successor", "Macaulay", "2005", "Macaulay");
		text.append(r3.display());
 		Resource v = new Resource("<img src=\"" + fl.toURL()+ "/pdficon.gif\"  width=\"16\" height=\"16\"></img> ","The older cattle (DISPOSAL) (SCOTLAND) regulations", "EXECUTIVE SUMMARY", "2006", "EXECUTIVE SUMMARY");
  		text.append(v.display());	
	}
	
		} catch (Exception ex) {System.out.println(ex);}
 		
 		text.append("</font> </html>");
 		
 		info.setText(text.toString());
 	}
 	
 	/*	Fills 'info' with descriptions of the given resources
  	*/
  	private JEditorPane fillPanel(Resource[] resources)
  	{
  		info.setContentType("text/html");
		HTMLEditorKit editorKit = (HTMLEditorKit)info.getEditorKit();
		info.addHyperlinkListener(new MyHyperlinkListener());
		
		StringBuffer text = new StringBuffer();
		text.append("<html><font face=\"Arial\" size=\"-1\">");
				
		for (int i = 0; i < resources.length; i++)	//load the resources into the styled document
			text.append(resources[i].display());

		text.append("</font> </html>");
 		info.setText(text.toString());
  		return info;
  	}
 
 	class MyHyperlinkListener implements HyperlinkListener 
 	{
        public void hyperlinkUpdate(HyperlinkEvent evt) 
        {             
        	try 
            {
            	if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
            	{
               	//	document.setPage(evt.getURL());
               		document.setText("Hey, I opened a new window for " + evt.getDescription());	//FOR TESTING PURPOSES
               		resourceFrame.setVisible(true);
                }
            }
            catch (Exception e) 
            {
             	e.printStackTrace();
            }
        }
    }
 
 	/*	Searches the repository for resources that contain the
 	 *	given codes, returns a list sorted for frequency
 	 *
 	private Resource[] searchRelatedResources(ArrayList codes)
 	{
 		//turn codes (and subcodes) into list of strings or whatever goes into the repository
 		//query repository and get some container of something from which a resource can be built
 		//(and which contains a code-count integer)
 	
 		Iterator it = resources.iterator();
 		Resource[] list = new Resource[resources.size()];
 		int i = 0;
 	
 		while it.hasNext()
 		{
 			try
 			{
	 			list[i] = new Resource((??)it.next());		//cast the  object to whatever it is, and create new Resource
 				i++;
 			}
 			catch (Exception e)
 			{
 				System.out.println("Could not read this resource - ignored it");
 			}
 		}
 		
 		ResourceComparator compare = new ResourceComparator();
 		sort(list, compare); 							//sort in order of code-frequency
 		return list;
 	}
 	*/
 	
 	//Return a component with the UI
 	public JComponent getPanel()
 	{
 		return info;
 	}
 	
 	//Return a component with the UI after refreshing the results with new set of code
 	public JEditorPane refreshPanel(String co)
 	{
 	
 	    info.setEditable(false);
 		info.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
 		
 		//initialize frame for displaying documents
 		resourceFrame = new JFrame();
 		document = new JEditorPane();
 		resourceFrame.setContentPane(document);
 		document.setEditable(false);
 		//resourceFrame.setSize(700, 450);
		resourceFrame.setVisible (false);
		resourceFrame.setResizable(true);
 		
 		fillPanel(co);		//FOR TESTING PURPOSES
 		//fillPanel(resources);
 	
 		//if (codes == null || codes.size() == 0)
    	//if no codes are passed, do nothing
 			
 		//ArrayList resources = searchRelatedResources(codes);
 		
 		return info;
 	}
}