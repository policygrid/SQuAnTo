package org.squanto.init.project;

import java.util.ArrayList;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class ProjectAssociation {
  private String documentName = new String("");
  private String documentLocation = new String("");
  private String ontologyName = new String("");
  private String ontologyLocation = new String("");
  private ArrayList codings = new ArrayList();

  public void setCodings(ArrayList codings) {
    this.codings = codings;
  }

  public ArrayList getCodings() {
    return codings;
  }

  public void setCodings(CodingFile codingFile){
    this.codings.add(codingFile);
  }

  public String getOntologyLocation() {
    return ontologyLocation;
  }

  public String getOntologyName() {
    return ontologyName;
  }

  public String getDocumentLocation() {
    return documentLocation;
  }

  public String getDocumentName() {
    return documentName;
  }

  public void setOntologyLocation(String ontologyLocation) {
    this.ontologyLocation = ontologyLocation;
  }

  public void setOntologyName(String ontologyName) {
    this.ontologyName = ontologyName;
  }

  public void setDocumentLocation(String documentLocation) {
    this.documentLocation = documentLocation;
  }

  public void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  public ProjectAssociation() {
  }

}
