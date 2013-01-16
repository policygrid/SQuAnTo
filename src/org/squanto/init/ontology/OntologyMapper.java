package org.squanto.init.ontology;

import java.util.ArrayList;
import com.hp.hpl.jena.rdf.model.Model;
import java.io.File;
import java.io.FileNotFoundException;

public interface OntologyMapper {
  public void createCodesOntology() throws FileNotFoundException;

  public ArrayList getCodes(Model inModel) throws Exception;

  public ArrayList getLocalCodes(String location) throws Exception;

  public String getProjectName();

  public ArrayList getRemoteCodes() throws Exception;

  public void importCodesOntology() throws Exception;

  public void setProjectName(String projectName);

  public boolean writeLocalCodes(ArrayList codesList, File outputFile) throws
      Exception;

  public boolean writeRemoteCodes(ArrayList codes) throws Exception;
}
