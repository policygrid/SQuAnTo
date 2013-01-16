package org.squanto.init.util;

import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.awt.event.*;
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
 
 public class Resource
 {
 	String title;
 	String author;
 	String date;		//or whatever format the date comes in
	String url;
	String image;
 	int codeFrequency;
 	
 	/*	Should get some kind of resource - code-frequency pair from the repository 
 	 *	and initialize the resource object
 	 *
 	public Resource(repository)
 	{
 	}
 	*/
 	
 	/*	For the time being we'll settle for these three
 	 */
 	public Resource()
 	{
 	}
 	
 	public Resource(String i, String t, String a, String d)
 	{
 		image = i;
 	    title = t;
 		author = a;
 		date = d;
 	}
 	
 	public Resource(String i, String t, String a, String d, String u)
 	{
 	    image = i;
 		title = t;
 		author = a;
 		date = d;
 		url = u;
 	}
 	
 	public String display()
 	{	//insert bibliographic description
 		StringBuffer output = new StringBuffer("<p>");
 		output.append(image + "<a href=\"" + url + "\">" + title + "</a>");
 		output.append("<br>");
 		output.append(author);
 		output.append(" (" + date + ")");
 		output.append("</p>");
 		return output.toString();
 	}
 		
 	public int getCodeFrequency()
 	{
 		return codeFrequency;
 	}
 }