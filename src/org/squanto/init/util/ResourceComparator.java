package org.squanto.init.util;

import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;

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
 
 public class ResourceComparator implements Comparator
 {
 	/*	Compares the relative relevance of two resources, using the code-frequency.
 	 *	Returns 1, 0 or -1 if one is more, equally, or less relevant than two
 	 */
 	public int compare(Object one, Object two)
 	{
 		Resource rOne = (Resource) one;
 		Resource rTwo = (Resource) two;
 		int freqOne = rOne.getCodeFrequency();
 		int freqTwo = rTwo.getCodeFrequency();
 		
 		if (freqOne > freqTwo)
 			return 1;
 		if (freqOne == freqTwo)
 			return 0;
 		return -1;
 	}
 }