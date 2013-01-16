package org.squanto.init.project;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;

import org.apache.xml.serialize.*;
import org.w3c.dom.*;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class ProjectDAO
    implements Project{
  private static final String project = new String("project");
  private static final String name = new String("name");
  private static final String document = new String("document");
  private static final String codeFile = new String("codefile");
  private static final String ontFile = new String("ontfile");
  private static final String docName = new String("docname");
  private static final String docLocation = new String("doclocation");
  private static final String codeFileName = new String("codefilename");
  private static final String codeFileLocation = new String("codefilelocation");
  private static final String ontFileName = new String("ontfilename");
  private static final String ontFileLocation = new String("ontfilelocation");
  private ArrayList projectAssociation = new ArrayList();
  private String projectName = new String("");
  private String projectLocation = new String("");
  private String projectFullName = new String("");

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public void setProjectLocation(String projectLocation) {
    this.projectLocation = projectLocation;
  }

  public void setProjectFullName(String projectFullName) {
    this.projectFullName = projectFullName;
  }

  public String getProjectName() {
    return projectName;
  }

  public String getProjectLocation() {
    return projectLocation;
  }

  public String getProjectFullName() {
    return projectFullName;
  }

  public void setProjectAssoc(ArrayList projectAssociation) {
    this.projectAssociation = projectAssociation;
  }

  public void setProjectAssoc(ProjectAssociation projectAssociation) {
    this.projectAssociation.add(projectAssociation);
  }

  public ArrayList getProjectAssoc() {
    return projectAssociation;
  }

  public ProjectDAO() {

  }

  public void openProject(File inputFile) throws ParserConfigurationException,
      Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    try {
      this.setProjectLocation(inputFile.getAbsolutePath());
      this.setProjectFullName(inputFile.getName());
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(inputFile);

      Element root = doc.getDocumentElement();
      NodeList children = root.getChildNodes();

      for (int i = 0; i < children.getLength(); i++) {
        Node child = children.item(i);

        if (child instanceof Element) {
          Element childElement = (Element) child;

          if (childElement.getTagName().equals(name)) {
            Text tn = (Text) childElement.getFirstChild();
            String data = tn.getData().trim();
            this.setProjectName(data);
          }
          else {
            NodeList projectChildren = childElement.getChildNodes();
            ProjectAssociation projectassoc = new ProjectAssociation();

            for(int k = 0; k < projectChildren.getLength(); k++){
              Node projectChild = projectChildren.item(k);

              if(projectChild instanceof Element){
                Element project = (Element) projectChild;

                NodeList doclist = project.getElementsByTagName(document);
                NodeList codelist = project.getElementsByTagName(codeFile);
                NodeList ontlist = project.getElementsByTagName(ontFile);
                projectassoc = this.setNodeList(projectassoc, doclist);
                projectassoc = this.setNodeList(projectassoc, codelist);
                projectassoc = this.setNodeList(projectassoc, ontlist);

              }
            }
            projectAssociation.add(projectassoc);

          }
        }
      }
    }catch (ParserConfigurationException ex) {
      throw new ParserConfigurationException();
    }
    catch (Exception ex) {
      throw new Exception();
    }
  }

  public ProjectAssociation setNodeList(ProjectAssociation projectassoc,
                                        NodeList nodelist){
    try{

     for(int a = 0; a < nodelist.getLength(); a++) {
        Node child = nodelist.item(a);
      CodingFile cFile = new CodingFile();

        Element childElement = (Element) child;

        for (int i = 0; i < childElement.getChildNodes().getLength(); i++) {
          Node node = childElement.getChildNodes().item(i);

          if (node instanceof Element) {
              Element elem = (Element) node;
              String elemTag = elem.getTagName();
              String data = new String("");

              try{
                Text tn = (Text) elem.getFirstChild();
                data = tn.getData().trim();
              }catch(Exception ex){
                data = new String("");
              }

              if (elemTag.equals(docName)) {
                projectassoc.setDocumentName(data);
              }
              else if (elemTag.equals(docLocation)) {
                projectassoc.setDocumentLocation(data);
              }
              else if (elemTag.equals(ontFileName)) {
                projectassoc.setOntologyName(data);
              }
              else if (elemTag.equals(ontFileLocation)) {
                projectassoc.setOntologyLocation(data);
              }
              else if (elemTag.equals(codeFileName)) {
                cFile.setCodingName(data);
              }
              else if (elemTag.equals(codeFileLocation)) {
                cFile.setCodingLocation(data);
              }

           }

        }
        if((!cFile.getCodingName().equals("")) && (!cFile.getCodingLocation().equals(""))){
          projectassoc.setCodings(cFile);
        }
      }
    }catch(Exception ex){

    }
    return projectassoc;
  }

  public void saveProject(File outputFile) throws ParserConfigurationException,
      Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    try {
      this.setProjectLocation(outputFile.getAbsolutePath());
      this.setProjectFullName(outputFile.getName());
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.newDocument();

      Element root = document.createElement(project);
      document.appendChild(root);

      Element name = document.createElement("name");
      Text authText = document.createTextNode(this.getProjectName());
      name.appendChild(authText);
      root.appendChild(name);

      Iterator projectIter = projectAssociation.iterator();

      while(projectIter.hasNext()){
        ProjectAssociation temp = (ProjectAssociation)projectIter.next();
        Element projectAssoc = document.createElement("projectassoc");

        projectAssoc.appendChild(this.createDocumentElements(temp, document));

        ArrayList codingsList = temp.getCodings();
        Iterator iter = codingsList.iterator();

        while(iter.hasNext()){
          CodingFile cFile = (CodingFile)iter.next();
          projectAssoc.appendChild(this.createCodeElements(cFile, document));
        }
        projectAssoc.appendChild(this.createOntElements(temp, document));

        root.appendChild(projectAssoc);
      }
      this.setProjectLocation(outputFile.getAbsolutePath());
      printToFile(document, outputFile);
    }
    catch (ParserConfigurationException ex) {
      throw new ParserConfigurationException();
    }catch(Exception ex){
      throw new Exception();
    }
  }

  public Element createDocumentElements(ProjectAssociation projectassoc,
                                        Document document){
    Element docassoc = document.createElement("docassoc");
    Element docelem = document.createElement("document");
    Element docname = document.createElement("docname");
    Element doclocation = document.createElement("doclocation");

    Text docnameText = document.createTextNode(projectassoc.getDocumentName());
    docname.appendChild(docnameText);
    Text doclocText = document.createTextNode(projectassoc.getDocumentLocation());
    doclocation.appendChild(doclocText);
    docelem.appendChild(docname);
    docelem.appendChild(doclocation);
    docassoc.appendChild(docelem);

    return docassoc;
  }

  public Element createCodeElements(CodingFile cfile,
                                    Document document){
    Element codeassoc = document.createElement("codeassoc");
    Element codefilename = document.createElement("codefilename");
    Element codefilelocation = document.createElement("codefilelocation");
    Element codefile = document.createElement("codefile");

    Text codefilenameText = document.createTextNode(cfile.getCodingName());
    codefilename.appendChild(codefilenameText);
    Text codefilelocationText = document.createTextNode(cfile.getCodingLocation());
    codefilelocation.appendChild(codefilelocationText);
    codefile.appendChild(codefilename);
    codefile.appendChild(codefilelocation);
    codeassoc.appendChild(codefile);

    return codeassoc;
  }

  public Element createOntElements(ProjectAssociation projectassoc,
                                   Document document){
    Element ontassoc = document.createElement("ontassoc");
    Element ontfile = document.createElement("ontfile");
    Element ontfilename = document.createElement("ontfilename");
    Element ontfilelocation = document.createElement("ontfilelocation");

    Text ontfilenameText = document.createTextNode(projectassoc.getOntologyName());
    ontfilename.appendChild(ontfilenameText);
    Text ontfilelocationText = document.createTextNode(projectassoc.getOntologyLocation());
    ontfilelocation.appendChild(ontfilelocationText);
    ontfile.appendChild(ontfilename);
    ontfile.appendChild(ontfilelocation);
    ontassoc.appendChild(ontfile);

    return ontassoc;
  }

  public void printToFile(Document doc, File outputFile) throws
      FileNotFoundException, IOException {
    OutputFormat format = new OutputFormat(doc);
    format.setStandalone(true);
    format.setEncoding("UTF-8");
    format.setOmitXMLDeclaration(false);
    format.setVersion("1.0");
    format.setIndenting(true);

    try {
      XMLSerializer serializer = new XMLSerializer(new
                                                    FileOutputStream(outputFile),
                                                    format);
      serializer.serialize(doc);
    }
    catch (FileNotFoundException ex) {
      throw new FileNotFoundException();
    }catch(IOException ioe){
      throw new IOException();
    }
  }
}
