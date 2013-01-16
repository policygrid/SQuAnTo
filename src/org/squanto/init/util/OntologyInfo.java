package org.squanto.init.util;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import javax.swing.text.html.*;
import javax.swing.event.*;
import java.awt.*;

/**
 *
 * <p>Title: SQuAnto</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author ??
 * @version 1.0
 *
 */

public class OntologyInfo {

	private JEditorPane info; 
 	//private int i;
  
 	public OntologyInfo()
 	{
    info =  new JEditorPane();
    info.setToolTipText("Ontology Information");
    info.setContentType("text/html");
    info.setEditable(false);
    //info.setText("Ontology Information");
   
 	}
	
	public void loadOwlDoc(String ontologyName, String ontologyClass)
 	{
 		info.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
 		info.putClientProperty(JEditorPane.W3C_LENGTH_UNITS, Boolean.TRUE);
 		HTMLEditorKit kit = (HTMLEditorKit)info.getEditorKit();
 		info.addHyperlinkListener(new Hyperactive());
 		
 		try 
 		{
 		    File fl = new File("owldocs/"+ ontologyName + "/" + ontologyClass + ".html");
 		    //System.out.println("FILE PATH:" + fl.getAbsolutePath() ) ;
 		    
 		    URL url = fl.toURL();
 		    System.out.println("URL:" + url ) ;	
        info.setPage(url);
        
    } catch (IOException e) {
        System.out.println("IOException : " + e );
    }
 	}
 	

	class Hyperactive implements HyperlinkListener 
	{        
		public void hyperlinkUpdate(HyperlinkEvent e) 
		{
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
			{
      	JEditorPane pane = (JEditorPane) e.getSource();
        if (e instanceof HTMLFrameHyperlinkEvent) 
        {
        	HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
          HTMLDocument doc = (HTMLDocument)pane.getDocument();
          doc.processHTMLFrameHyperlinkEvent(evt);
        } else {
          try 
          {
             pane.setPage(e.getURL());
          } catch (IOException ex) {
             System.out.println("IOException : " + ex );
          }
        }
       }
     }
	 }
  
     
 	// Constructor: the parameter is the ontology name and class
 	public OntologyInfo(String ontologyName, String ontologyClass) 
 	{
    this();
 	}
 
 
 	// Return a panel with the UI
 	public JComponent getPanel()
 	{
		return info;
 	}
 
 
 	// Return a panel with the UI after refreshing the results with 
 	// new ontology name and/or class
 	public JComponent refreshPanel(String ontologyName, String ontologyClass)
 	{
    loadOwlDoc(ontologyName, ontologyClass);
 		return info;
 	}
 
  
}
