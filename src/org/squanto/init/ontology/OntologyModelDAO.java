package org.squanto.init.ontology;

import java.io.*;
import java.util.*;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.*;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class OntologyModelDAO
    implements OntologyModel {
  private OntModel model = null;
  private String ontName = new String("");
  private String location = new String("");
  private int conceptCount = 0;

  public OntologyModelDAO(String ontName, String location) {
    this.ontName = ontName;
    this.location = location;
  }

  public OntologyModelDAO(){

  }

  public void setOntName(String ontName){
    this.ontName = ontName;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setModel(OntModel model) {
    this.model = model;
  }

  public void setConceptCount(int conceptCount) {
    this.conceptCount = conceptCount;
  }

  public String getOntName(){
    return this.ontName;
  }

  public String getLocation() {
    return location;
  }

  public OntModel getModel() {
    return this.model;
  }

  public int getConceptCount() {
    return conceptCount;
  }

  public void readOntology() throws Exception {
    try{
        this.model = ModelFactory.createOntologyModel();
        RDFReader rdf = model.getReader();
        rdf.read(model,  new FileInputStream(location), "" );
   }catch(Exception ex){
     throw new Exception();
   }
  }

  public ArrayList getConcepts() throws Exception {
    ArrayList alist = new ArrayList();

    if(this.model == null){
      this.readOntology();
    }

    try{
      ExtendedIterator iter = this.model.listClasses();

      while(iter.hasNext()){
        OntClass ontclass = (OntClass)iter.next();
        if(ontclass.isAnon() == false){
          alist.add(ontclass.getLocalName());
          conceptCount++;
        }
      }
    }catch(Exception ex){
      throw new Exception();
    }
    return alist;
  }

  public void clearModel(){
    conceptCount = 0;
    this.model.close();
  }

  public String getOntURI(){
    String uri = new String("");

    if(model != null){
      return this.model.getNsPrefixURI("");
    }

    return uri;
  }

  public String getConceptURI(String concept){
    String conceptUri = new String("");
    ExtendedIterator eiter = this.model.listClasses();


    while(eiter.hasNext()){
      OntClass ontclass = (OntClass) eiter.next();

      try {
        if (ontclass.getLocalName().equals(concept)) {
          return ontclass.getURI();
        }
      }catch(NullPointerException npe){
        return new String("");
      }
    }

    return conceptUri;
  }
}
