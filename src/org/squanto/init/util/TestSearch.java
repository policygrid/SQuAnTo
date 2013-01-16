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
 
 public class TestSearch
 {
 	private JFrame frame;
 	private JButton start;
 	
 	public TestSearch()
 	{
 		frame = new JFrame("Test-search");
 		frame.getContentPane().setLayout(null);
		frame.setBackground(Color.lightGray);
		frame.setSize(700, 450);
		
 		start = new JButton("Search");
 		TestActionListener listen = new TestActionListener();
 		start.addActionListener(listen);
		start.setBounds(20, 10, 300, 20);
		frame.getContentPane().add(start);
		
		frame.setVisible(true);
		frame.setResizable(true);
		frame.addWindowListener(new CloseWindowAndExit());
 	}
 	
 	public JFrame getFrame()
 	{
 		return frame;
 	}
 	
 	class TestActionListener implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			AdvancedSearchDialog search = new AdvancedSearchDialog(frame, "Advanced Search");
 		}
 	}
 	
 	class CloseWindowAndExit extends WindowAdapter 
 	{
 		public void windowClosing (WindowEvent e) 
 		{
 			System.exit(0);
 		}
	}
 	
 	public static void main(String[] args)
 	{
 		TestSearch test = new TestSearch();
 	}
 }