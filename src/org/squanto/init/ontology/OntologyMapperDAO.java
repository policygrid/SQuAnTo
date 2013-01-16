package org.squanto.init.ontology;

import java.io.*;
import java.util.*;

import org.openrdf.sesame.*;
import org.openrdf.sesame.admin.*;
import org.openrdf.sesame.constants.*;
import org.openrdf.sesame.repository.*;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.*;
import org.squanto.init.codes.Code;
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

public class OntologyMapperDAO
    implements OntologyMapper {
  private String ns = new String("http://somewhere/codes#");
  private String codens = new String("http://somewhere/code#");
  private String secns = new String("http://somewhere/section#");
  private String ontns = new String("http://somewhere/ontology#");
  private String insns = new String("http://somewhere/instance#");
  private String dcns = new String("http://purl.org/dc/elements/1.1/");
  private OntModel codesOntology = null;
  private String projectName = new String("");

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectName() {
    return projectName;
  }

  public OntologyMapperDAO() {

  }

  public void createCodesOntology() throws FileNotFoundException {
     OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM);
     codesOntology = ModelFactory.createOntologyModel(spec);
     codesOntology.setNsPrefix("coding", ns);
     codesOntology.setNsPrefix("code", codens);
     codesOntology.setNsPrefix("section", secns);
     codesOntology.setNsPrefix("ontology", ontns);
     codesOntology.setNsPrefix("instance", insns);

     OntClass codes = codesOntology.createClass(ns+"codes");
     OntClass code = codesOntology.createClass(codens+"code");
     OntClass section = codesOntology.createClass(secns+"section");
     OntClass ontology = codesOntology.createClass(ontns+"ontology");

     ObjectProperty hasCode = codesOntology.createObjectProperty(ns+"hasCode");
     DatatypeProperty ofProject = codesOntology.createDatatypeProperty(ns+"ofProject");
     DatatypeProperty hasCodeName = codesOntology.createDatatypeProperty(codens+"hasCodeName");
     DatatypeProperty hasShared = codesOntology.createDatatypeProperty(codens+"hasShared");
     DatatypeProperty hasCodeType = codesOntology.createDatatypeProperty(codens+"hasCodeType");
     ObjectProperty hasSections = codesOntology.createObjectProperty(codens+"hasSections");
     DatatypeProperty hasSectionStart = codesOntology.createDatatypeProperty(secns+"hasSectionStart");
     DatatypeProperty hasSectionEnd = codesOntology.createDatatypeProperty(secns+"hasSectionEnd");
     DatatypeProperty hasSectionText = codesOntology.createDatatypeProperty(secns+"hasSectionText");
     ObjectProperty hasOntology = codesOntology.createObjectProperty(codens+"hasOntology");
     DatatypeProperty hasOntologyReference = codesOntology.createDatatypeProperty(ontns+"hasOntologyReference");
     ObjectProperty hasConceptReference = codesOntology.createObjectProperty(ontns+"hasConceptReference");

     hasCode.setDomain(codes);
     hasCode.setRange(code);
     ofProject.setDomain(codes);
     ofProject.setRange(codesOntology.
                        createResource("http://www.w3.org/2001/XMLSchema#string"));
     hasCodeName.setDomain(code);
     hasCodeName.setRange(codesOntology.
                        createResource("http://www.w3.org/2001/XMLSchema#string"));
     hasShared.setDomain(code);
     hasShared.setRange(codesOntology.
                        createResource("http://www.w3.org/2001/XMLSchema#boolean"));
     hasCodeType.setDomain(code);
     hasCodeType.setRange(codesOntology.
                        createResource("http://www.w3.org/2001/XMLSchema#string"));
     hasSections.setDomain(code);
     hasSections.hasRange(section);
     hasSectionStart.setDomain(section);
     hasSectionStart.setRange(codesOntology.
                        createResource("http://www.w3.org/2001/XMLSchema#int"));
     hasSectionEnd.setDomain(section);
     hasSectionEnd.setRange(codesOntology.
                        createResource("http://www.w3.org/2001/XMLSchema#in"));
     hasSectionText.setDomain(section);
     hasSectionText.setRange(codesOntology.
                        createResource("http://www.w3.org/2001/XMLSchema#string"));
     hasOntology.setDomain(code);
     hasOntology.setRange(ontology);
     hasOntologyReference.setDomain(ontology);
     hasOntologyReference.setRange(codesOntology.
                        createResource("http://www.w3.org/2001/XMLSchema#string"));
     hasConceptReference.setDomain(ontology);

     try {
       codesOntology.write(new FileOutputStream(new File("codesOntology.owl")),
                           "RDF/XML");
     } catch (FileNotFoundException ex) {
       throw new FileNotFoundException();
     }
  }

  public void importCodesOntology() throws Exception {
    try{
       createCodesOntology();
       this.codesOntology = ModelFactory.createOntologyModel();
       RDFReader rdf = codesOntology.getReader();
       File file = new File("codesontology.owl");
       rdf.read(codesOntology,  new FileInputStream(file), "" );
       file.delete();
     }
     catch (Exception ex) {
       throw new Exception();
     }
  }

  public boolean writeLocalCodes(ArrayList codesList, File outputFile) throws
      Exception {
    boolean success = false;

    try{
      importCodesOntology();
      Model outModel = ModelFactory.createDefaultModel();
      outModel.setNsPrefix("coding", ns);
      outModel.setNsPrefix("code", codens);
      outModel.setNsPrefix("section", secns);
      outModel.setNsPrefix("ontology", ontns);
      outModel.setNsPrefix("instance", insns);
      outModel.setNsPrefix("dc", dcns);

      Iterator codesiter = codesList.iterator();

      int codeCount = 1;
      int sectionCount = 1;
      int ontologyCount = 1;

      Resource codesIndividual = outModel.createResource(ns+this.getProjectName()+
          "_codesinstance",
          codesOntology.getOntResource(ns+"codes"));

      codesIndividual.addProperty(codesOntology.getProperty(ns+"ofProject"),
                                  outModel.createLiteral(this.getProjectName()));

      while(codesiter.hasNext()){
        Code tempCode = (Code)codesiter.next();

        Resource codeIndividual = outModel.createResource(codens+
            "codeinstance"+codeCount, codesOntology.getResource(codens+"code"));
        codeIndividual.addProperty(codesOntology.getProperty(codens+"hasCodeName"),
                                   outModel.createLiteral(tempCode.getName()));
        codeIndividual.addProperty(codesOntology.getProperty(dcns+"title"),
                                   outModel.createLiteral(tempCode.getDocumentTitle()));
        codeIndividual.addProperty(codesOntology.getProperty(dcns+"creator"),
                                   outModel.createLiteral(tempCode.getAuthor()));
        codeIndividual.addProperty(codesOntology.getProperty(dcns+"date"),
                                   outModel.createLiteral(tempCode.getCreationDate()));
        codeIndividual.addProperty(codesOntology.getProperty(dcns+"language"),
                                   outModel.createLiteral(tempCode.getLanguage()));
        codeIndividual.addProperty(codesOntology.getProperty(codens+"hasShared"),
                                   outModel.createLiteral(tempCode.getShared()));
        codeIndividual.addProperty(codesOntology.getProperty(codens+"hasCodeType"),
                                   outModel.createLiteral(tempCode.getCodeType()));
        codeIndividual.addProperty(codesOntology.getProperty(dcns+"source"),
                                   outModel.createLiteral(tempCode.getDocLocation()));

        ArrayList sectionList = tempCode.getSectionList();

        if(!sectionList.isEmpty()){

          Iterator sectionIter = sectionList.iterator();

          while (sectionIter.hasNext()) {
            Section tempSection = (Section) sectionIter.next();

            Resource sectionIndividual = outModel.createResource(secns +
                "sectioninstance" + sectionCount, codesOntology.getResource(secns+"section"));
            sectionIndividual.addProperty(codesOntology.getProperty(secns+"hasSectionStart"),
                                          outModel.createLiteral(tempSection.
                getSectionStart()));
            sectionIndividual.addProperty(codesOntology.getProperty(secns+"hasSectionEnd"),
                                          outModel.createLiteral(tempSection.
                getSectionEnd()));
            sectionIndividual.addProperty(codesOntology.getProperty(secns+"hasSectionText"),
                                          outModel.createLiteral(tempSection.
                getBodyText()));
            outModel.add(codeIndividual, codesOntology.getProperty(codens+"hasSections"),
                         sectionIndividual);
            sectionCount++;
          }
        }

        Resource ontIndividual = outModel.createResource(ontns+
            "ontologyinstance"+ontologyCount, codesOntology.getResource(ontns+"ontology"));

        ontIndividual.addProperty(codesOntology.getProperty(ontns+"hasOntologyReference"),
                                  outModel.createLiteral(tempCode.getOntologyReference()));

       if(!tempCode.getOntologyConceptRef().equals("")){
         outModel.setNsPrefix("external", tempCode.getOntologyReference());
         int index = tempCode.getOntologyConceptRef().lastIndexOf("#");
         String instance = tempCode.getOntologyConceptRef().substring(index + 1);
         Resource concept = outModel.createResource(insns+instance+"Instance",
            outModel.createResource(tempCode.getOntologyConceptRef()));
         ontIndividual.addProperty(codesOntology.getProperty(ontns+"hasConceptReference"),
                                  concept);
       }else{
         ontIndividual.addProperty(codesOntology.getProperty(ontns+"hasConceptReference"),
                                  outModel.createLiteral(tempCode.getOntologyConceptRef()));
       }

        outModel.add(codeIndividual, codesOntology.getProperty(codens+"hasOntology"),
                     ontIndividual);
        outModel.add(codesIndividual, codesOntology.getProperty(ns+"hasCode"),
                     codeIndividual);
        codeCount++;
        ontologyCount++;
      }

      RDFWriter writer = outModel.getWriter();
      writer.setProperty("showXMLDeclaration", "true");
      writer.setProperty("xmlBase", "http://someontology/codings.owl#");
      writer.write(outModel, new FileOutputStream(outputFile), "RDF/XML");
      success = true;
   }catch(Exception ex){
     throw new Exception();
   }
   return success;
  }

  public boolean writeRemoteCodes(ArrayList codes) throws Exception {
    boolean success = false;

    try {
      File file = new File("temp.rdf");
      writeLocalCodes(codes, file);
      String modelStr = new String("");
      FileManager fm = FileManager.get();
      modelStr = fm.readWholeFileAsUTF8(new FileInputStream(file));

      java.net.URL sesameServerURL = new java.net.URL("http://roc.csd.abdn.ac.uk:8080/sesame/");
      SesameService service = Sesame.getService(sesameServerURL);
      service.login("squanto", "squanto");
      SesameRepository myRepository = service.getRepository("squanto-rep");

      String baseURI = "http://someontology/codings.owl#";
      boolean verifyData = true;
      AdminListener myListener = new StdOutAdminListener();
      myRepository.addData(modelStr, baseURI, RDFFormat.RDFXML, verifyData, myListener);
      service.logout();
      file.delete();
      success = true;
    } catch (Exception ex) {
      throw new Exception();
    }

    return success;
  }

  public ArrayList getRemoteCodes() throws Exception {
    ArrayList codes = new ArrayList();

    try {
      java.net.URL sesameServerURL = new java.net.URL("http://roc.csd.abdn.ac.uk:8080/sesame/");
      SesameService service = Sesame.getService(sesameServerURL);
      service.login("squanto", "squanto");
      SesameRepository myRepository = service.getRepository("squanto-rep");
      InputStream is = myRepository.extractRDF(RDFFormat.RDFXML, true, true, true, true);
      Model inmodel = ModelFactory.createDefaultModel();
      RDFReader rdf = inmodel.getReader();
      rdf.read(inmodel, is, "");
      service.logout();
      codes = getCodes(inmodel);
    } catch (Exception ex) {
      throw new Exception();
    }

    return codes;
  }

  public ArrayList getLocalCodes(String location) throws Exception {
    ArrayList codes = new ArrayList();

    try {
      Model inModel = ModelFactory.createDefaultModel();
      RDFReader rdf = inModel.getReader();
      rdf.read(inModel, new FileInputStream(location), "");
      codes = getCodes(inModel);
    } catch (Exception ex) {
      throw new Exception();
    }

    return codes;
  }

  public ArrayList getCodes(Model inModel) throws Exception {
    importCodesOntology();
    ArrayList codes = new ArrayList();
    boolean exists =false;

    try{

      ResIterator niter = inModel.listSubjectsWithProperty(codesOntology.
          getProperty(ns+"ofProject"));
      while (niter.hasNext()) {
        Resource codesInstances = (Resource) niter.nextResource();
        RDFNode project = codesInstances.getProperty(
            codesOntology.getProperty(ns + "ofProject")).getObject();
        if (project.toString().equals(this.getProjectName())) {
          exists = true;
        }
      }

     if(exists){
       ResIterator codeIter = inModel.listSubjectsWithProperty(
               codesOntology.getProperty(codens+"hasCodeName"));

       while (codeIter.hasNext()) {
         Resource tcode = (Resource) codeIter.nextResource();
         Code code = new Code();

         RDFNode codename = tcode.
             getProperty(codesOntology.getProperty(codens + "hasCodeName")).
             getObject();
         RDFNode doctitle = tcode.
             getProperty(codesOntology.getProperty(dcns + "title")).
             getObject();
         RDFNode author = tcode.
             getProperty(codesOntology.getProperty(dcns + "creator")).getObject();
         RDFNode creationdate = tcode.
             getProperty(codesOntology.getProperty(dcns + "date")).
             getObject();
         RDFNode language = tcode.
             getProperty(codesOntology.getProperty(dcns + "language")).
             getObject();
         RDFNode docloc = tcode.
             getProperty(codesOntology.getProperty(dcns + "source")).
             getObject();
         RDFNode shared = tcode.
             getProperty(codesOntology.getProperty(codens + "hasShared")).getObject();
         RDFNode codetype = tcode
             .getProperty(codesOntology.getProperty(codens + "hasCodeType")).
             getObject();

         code.setName(codename.toString());
         code.setDocumentTitle(doctitle.toString());
         code.setAuthor(author.toString());
         code.setCreationDate(creationdate.toString());
         code.setLanguage(language.toString());
         code.setDocLocation(docloc.toString());

         if (shared.toString().equals("true")) {
           code.setShared(true);
         }
         else {
           code.setShared(false);
         }
         code.setCodeType(codetype.toString());

         ResIterator sectioniter = inModel.listSubjectsWithProperty(
             codesOntology.getProperty(secns + "hasSectionStart"));

         while (sectioniter.hasNext()) {

           Resource section = (Resource) sectioniter.nextResource();

           if (tcode.hasProperty(codesOntology.getProperty(codens +
               "hasSections"), section)) {

             RDFNode sectionstart = section.
                 getProperty(codesOntology.getProperty(secns + "hasSectionStart")).
                 getObject();
             RDFNode sectionend = section.
                 getProperty(codesOntology.getProperty(secns + "hasSectionEnd")).
                 getObject();
             RDFNode sectiontext = section.
                 getProperty(codesOntology.getProperty(secns + "hasSectionText")).
                 getObject();

             Section sec = new Section();
             sec.setSectionStart(Integer.parseInt(sectionstart.toString()));
             sec.setSectionEnd(Integer.parseInt(sectionend.toString()));
             sec.setBodyText(sectiontext.toString());
             code.setSectionList(sec);
           }
         }

         ResIterator ontiter = inModel.listSubjectsWithProperty(
             codesOntology.getProperty(ontns + "hasOntologyReference"));

         while (ontiter.hasNext()) {
           Resource ontology = (Resource) ontiter.nextResource();

           if (tcode.hasProperty(codesOntology.getProperty(codens +
               "hasOntology"), ontology)) {

             RDFNode ontref = ontology.
                 getProperty(codesOntology.getProperty(ontns +
                                                 "hasOntologyReference"))
                 .getObject();

             code.setOntologyReference(ontref.toString());

             RDFNode concept = ontology.getProperty(
                 codesOntology.getProperty(ontns + "hasConceptReference")).getObject();

             Resource conceptref = inModel.getResource(concept.toString());

             StmtIterator siter = conceptref.listProperties();

             while (siter.hasNext()) {
               Statement stmt = (Statement) siter.nextStatement();
               code.setOntologyConceptRef(stmt.getObject().toString());
             }
           }

         }
         codes.add(code);
       }
     }
    }catch(Exception ex){
      throw new Exception();
    }

    return codes;
  }
}
