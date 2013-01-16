package org.squanto.init.document;

import org.squanto.init.codes.Code;
import java.util.ArrayList;
import org.squanto.init.ontology.OntologyModelDAO;

public interface MainDocument {
  public void addCode(Code code);

  public AnnotDocument getAnnotDocument();

  public ArrayList getCodes();

  public ArrayList getCodesColours();

  public OntologyModelDAO getOntModel();

  public void setAnnotDocument(AnnotDocument annotDocument);

  public void setCodes(ArrayList codes);

  public void setCodesColour(ArrayList codesColour);

  public void setCodesColours(ArrayList codesColours);

  public void setOntModel(OntologyModelDAO ontModel);
}
