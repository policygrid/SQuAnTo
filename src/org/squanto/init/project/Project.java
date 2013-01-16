package org.squanto.init.project;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.io.FileNotFoundException;

public interface Project {
  public Element createCodeElements(CodingFile cfile, Document document);

  public Element createDocumentElements(ProjectAssociation projectassoc,
                                        Document document);

  public Element createOntElements(ProjectAssociation projectassoc,
                                   Document document);

  public ArrayList getProjectAssoc();

  public String getProjectFullName();

  public String getProjectLocation();

  public String getProjectName();

  public void openProject(File inputFile) throws ParserConfigurationException,
      Exception;

  public void printToFile(Document doc, File outputFile) throws FileNotFoundException,
      IOException;

  public void saveProject(File outputFile) throws ParserConfigurationException,
      Exception;

  public ProjectAssociation setNodeList(ProjectAssociation projectassoc,
                                        NodeList nodelist);

  public void setProjectAssoc(ArrayList projectAssociation);

  public void setProjectAssoc(ProjectAssociation projectAssociation);

  public void setProjectFullName(String projectFullName);

  public void setProjectLocation(String projectLocation);

  public void setProjectName(String projectName);
}
