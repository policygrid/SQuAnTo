package org.squanto.init.document;

import java.util.*;
import org.squanto.init.codes.Code;
import org.squanto.init.ontology.OntologyModelDAO;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class MainDocumentDAO
    implements MainDocument {
  private ArrayList codes = new ArrayList();
  private OntologyModelDAO ontModel = null;
  private AnnotDocument annotDocument = new AnnotDocument();
  private ArrayList codesColours = new ArrayList();

  public ArrayList getCodes() {
    return codes;
  }

  public AnnotDocument getAnnotDocument() {
    return annotDocument;
  }

  public void setOntModel(OntologyModelDAO ontModel) {
    this.ontModel = ontModel;
  }

  public void setCodes(ArrayList codes) {
    this.codes = codes;
  }

  public void setAnnotDocument(AnnotDocument annotDocument) {
    this.annotDocument = annotDocument;
  }

  public void setCodesColours(ArrayList codesColours) {
    this.codesColours = codesColours;
  }

  public void setCodesColour(ArrayList codesColour) {
    this.codesColours.add(codesColour);
  }

  public OntologyModelDAO getOntModel() {
    return ontModel;
  }

  public ArrayList getCodesColours() {
    return codesColours;
  }

  public MainDocumentDAO() {
  }

  public void addCode(Code code){
    this.codes.add(code);
  }
}
