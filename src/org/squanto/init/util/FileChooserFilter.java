package org.squanto.init.util;

import java.io.*;
import java.util.*;

import javax.swing.filechooser.FileFilter;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 *
 * I acknowledge I have not produced this class myself.
 * I have borrowed it from a Java Swing Tutorial
 */

public class FileChooserFilter extends FileFilter{
  private Hashtable filters = null;
  private String description = null;
  private String fullDescription = null;
  private boolean useExtensionsInDescription = true;

  public FileChooserFilter() {
    this.filters = new Hashtable();
  }

  public FileChooserFilter(String extension) {
    this.filters = new Hashtable(5);
    this.filters.put(extension, new Integer(1));
  }

  public FileChooserFilter(String extension, String description) {
    if(extension != null){
      addExtension(extension);
    }
    if(description != null){
      setDescription(description);
    }
  }

  public boolean accept(File f) {
    if(f != null) {
            if(f.isDirectory()) {
                return true;
            }
            String extension = getExtension(f);
            if(extension != null && filters.get(getExtension(f)) != null) {
                return true;
            };
        }
    return false;
  }

  public String getExtension(File f) {
        if(f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if(i>0 && i<filename.length()-1) {
                return filename.substring(i+1).toLowerCase();
            };
        }
        return null;
    }

  public String getDescription() {

    if(fullDescription == null) {
            if(description == null || isExtensionListInDescription()) {
                 fullDescription = description==null ? "(" : description + " (";
                Enumeration extensions = filters.keys();
                if(extensions != null) {
                    fullDescription += "." + (String) extensions.nextElement();
                    while (extensions.hasMoreElements()) {
                        fullDescription += ", ." + (String) extensions.nextElement();
                    }
                }
                fullDescription += ")";
            } else {
                fullDescription = description;
            }
        }
        return fullDescription;
  }

  public boolean isExtensionListInDescription(){
    return this.useExtensionsInDescription;
  }

  public void addExtension(String extension) {
    if(filters == null) {
       filters = new Hashtable(5);
    }
    filters.put(extension.toLowerCase(), this);
    this.fullDescription = null;
  }

  public void setDescription(String description) {
    this.description = description;
    this.fullDescription = null;
  }
}
