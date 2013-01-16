package org.squanto.init.ontology;

import java.util.ArrayList;
import com.hp.hpl.jena.ontology.OntModel;

public interface OntologyModel {
  public void clearModel();

  public int getConceptCount();

  public String getConceptURI(String concept);

  public ArrayList getConcepts() throws Exception;

  public String getLocation();

  public OntModel getModel();

  public String getOntName();

  public String getOntURI();

  public void readOntology() throws Exception;

  public void setConceptCount(int conceptCount);

  public void setLocation(String location);

  public void setModel(OntModel model);

  public void setOntName(String ontName);
}
