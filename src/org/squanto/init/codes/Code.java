package org.squanto.init.codes;

import java.util.ArrayList;
import org.squanto.init.codes.Section;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class Code {
  private ArrayList sectionList = new ArrayList();
  private String name = new String("");
  private String docTitle = new String("");
  private String author = new String("");
  private String creationDate = new String(new java.util.Date().toString());
  private String ontologyReference = new String("");
  private String ontologyConceptRef = new String("");
  private String language = new String("");
  private boolean shared = false;
  private String codeType = new String("");
  private String docLocation = new String("");

  public Code() {

  }

  public ArrayList getSectionList(){
    return this.sectionList;
  }

  public String getName(){
    return this.name;
  }

  public String getDocumentTitle(){
    return this.docTitle;
  }

  public String getAuthor(){
    return this.author;
  }

  public String getCreationDate(){
    return this.creationDate;
  }

  public String getOntologyReference(){
    return this.ontologyReference;
  }

  public String getOntologyConceptRef(){
    return this.ontologyConceptRef;
  }

  public String getLanguage() {
    return language;
  }

  public String getCodeType() {
    return codeType;
  }

  public String getDocLocation() {
    return docLocation;
  }

  public boolean getShared() {
    return shared;
  }

  public void setSectionList(Section section){
    this.sectionList.add(section);
  }

  public void setSectionList(ArrayList sectionList){
    this.sectionList = sectionList;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setDocumentTitle(String docTitle){
    this.docTitle = docTitle;
  }

  public void setAuthor(String author){
    this.author = author;
  }

  public void setCreationDate(String creationDate){
    this.creationDate = creationDate;
  }

  public void setOntologyReference(String ontologyReference){
    this.ontologyReference = ontologyReference;
  }

  public void setOntologyConceptRef(String ontologyConceptRef){
    this.ontologyConceptRef = ontologyConceptRef;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public void setCodeType(String codetype) {
    this.codeType = codetype;
  }

  public void setDocLocation(String docLocation) {
    this.docLocation = docLocation;
  }

  public void setShared(boolean shared) {
    this.shared = shared;
  }

}
