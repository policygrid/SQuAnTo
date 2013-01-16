package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.model.visitor.OWLModelVisitor;

import java.util.*;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 19, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class RDFResourceNameSpaceFilterVisitor implements OWLModelVisitor {

	private ArrayList allResources;

	private ArrayList allClasses;

	private ArrayList allIndividuals;

	private ArrayList allObjectProperties;

	private ArrayList allDatatypeProperties;

	private ArrayList allAnnotationProperties;

	private HashMap namespaceAllResourceMap;
	private HashMap namespaceClassesMap;
	private HashMap namespaceObjectPropertiesMap;
	private HashMap namespaceDatatypePropertiesMap;
	private HashMap namespaceAnnotationPropertiesMap;
	private HashMap namespaceIndividualsMap;


	private OWLDocPreferences preferences;


	public RDFResourceNameSpaceFilterVisitor() {
		this.allResources = new ArrayList();
		this.allClasses = new ArrayList();
		this.allIndividuals = new ArrayList();
		this.allObjectProperties = new ArrayList();
		this.allDatatypeProperties = new ArrayList();
		this.allAnnotationProperties = new ArrayList();
		preferences = OWLDocPreferences.getInstance();
		namespaceAllResourceMap = new HashMap();
		namespaceClassesMap = new HashMap();
		namespaceObjectPropertiesMap = new HashMap();
		namespaceDatatypePropertiesMap = new HashMap();
		namespaceAnnotationPropertiesMap = new HashMap();
		namespaceIndividualsMap = new HashMap();
		for(Iterator it = preferences.getIncludedNamespaces().iterator(); it.hasNext(); ) {
			String curPrefix = (String) it.next();
			namespaceAllResourceMap.put(curPrefix, new ArrayList());
			namespaceClassesMap.put(curPrefix, new ArrayList());
			namespaceObjectPropertiesMap.put(curPrefix, new ArrayList());
			namespaceDatatypePropertiesMap.put(curPrefix, new ArrayList());
			namespaceAnnotationPropertiesMap.put(curPrefix, new ArrayList());
			namespaceIndividualsMap.put(curPrefix, new ArrayList());
		}
		namespaceAllResourceMap.put(null, new ArrayList());
		namespaceClassesMap.put(null, new ArrayList());
		namespaceObjectPropertiesMap.put(null, new ArrayList());
		namespaceDatatypePropertiesMap.put(null, new ArrayList());
		namespaceAnnotationPropertiesMap.put(null, new ArrayList());
		namespaceIndividualsMap.put(null, new ArrayList());
	}


	public Collection getAllResources() {
		return allResources;
	}


	public Collection getAllClasses() {
		return allClasses;
	}

	public Collection getAllIndividuals() {
		return allIndividuals;
	}


	public Collection getAllObjectProperties() {
		return allObjectProperties;
	}


	public Collection getAllDatatypeProperties() {
		return allDatatypeProperties;
	}

	public Collection getAllAnnotationProperties() {
		return allAnnotationProperties;
	}

	public Collection getAllResources(String prefix) {
		return Collections.unmodifiableCollection((Collection)namespaceAllResourceMap.get(prefix));
	}


	public Collection getIndividuals(String prefix) {
		return Collections.unmodifiableCollection((Collection)namespaceIndividualsMap.get(prefix));
	}

	public Collection getClasses(String prefix) {
		return Collections.unmodifiableCollection((Collection)namespaceClassesMap.get(prefix));
	}

	public Collection getObjectPropertiees(String prefix) {
		return Collections.unmodifiableCollection((Collection)namespaceObjectPropertiesMap.get(prefix));
	}

	public Collection getDatatypeProperties(String prefix) {
		return Collections.unmodifiableCollection((Collection)namespaceDatatypePropertiesMap.get(prefix));
	}

	public Collection getAnnotationProperties(String prefix) {
		return Collections.unmodifiableCollection((Collection)namespaceAnnotationPropertiesMap.get(prefix));
	}

	public void clear() {
		allResources.clear();
		allClasses.clear();
		allObjectProperties.clear();
		allDatatypeProperties.clear();
		allIndividuals.clear();
		allAnnotationProperties.clear();
	}

	public void visitOWLIndividual(OWLIndividual owlIndividual) {
		if(preferences.isIncludedNamespace(owlIndividual.getNamespacePrefix())) {
			Collection c = (Collection)namespaceIndividualsMap.get(owlIndividual.getNamespacePrefix());
			c.add(owlIndividual);
			c = (Collection)namespaceAllResourceMap.get(owlIndividual.getNamespacePrefix());
			c.add(owlIndividual);
			allResources.add(owlIndividual);
			allIndividuals.add(owlIndividual);

		}
	}


	public void visitOWLNamedClass(OWLNamedClass owlNamedClass) {
		if(preferences.isIncludedNamespace(owlNamedClass.getNamespacePrefix())) {
			Collection c = (Collection)namespaceClassesMap.get(owlNamedClass.getNamespacePrefix());
			c.add(owlNamedClass);
			c = (Collection)namespaceAllResourceMap.get(owlNamedClass.getNamespacePrefix());
			c.add(owlNamedClass);
			allResources.add(owlNamedClass);
			allClasses.add(owlNamedClass);
		}
	}


	public void visitOWLObjectProperty(OWLObjectProperty owlObjectProperty) {
		if(preferences.isIncludedNamespace(owlObjectProperty.getNamespacePrefix())) {
			Collection c = (Collection)namespaceObjectPropertiesMap.get(owlObjectProperty.getNamespacePrefix());
			c.add(owlObjectProperty);
			c = (Collection)namespaceAllResourceMap.get(owlObjectProperty.getNamespacePrefix());
			c.add(owlObjectProperty);
			if(owlObjectProperty.isAnnotationProperty()) {
				c = (Collection)namespaceAnnotationPropertiesMap.get(owlObjectProperty.getNamespacePrefix());
				c.add(owlObjectProperty);
				allAnnotationProperties.add(owlObjectProperty);
			}
			allResources.add(owlObjectProperty);
			allObjectProperties.add(owlObjectProperty);

		}
	}


	public void visitOWLDatatypeProperty(OWLDatatypeProperty owlDatatypeProperty) {
		if(preferences.isIncludedNamespace(owlDatatypeProperty.getNamespacePrefix())) {
			Collection c = (Collection)namespaceDatatypePropertiesMap.get(owlDatatypeProperty.getNamespacePrefix());
			c.add(owlDatatypeProperty);
			c = (Collection)namespaceAllResourceMap.get(owlDatatypeProperty.getNamespacePrefix());
			c.add(owlDatatypeProperty);
			if(owlDatatypeProperty.isAnnotationProperty()) {
				c = (Collection)namespaceAnnotationPropertiesMap.get(owlDatatypeProperty.getNamespacePrefix());
				c.add(owlDatatypeProperty);
				allAnnotationProperties.add(owlDatatypeProperty);
			}
			allResources.add(owlDatatypeProperty);
			allDatatypeProperties.add(owlDatatypeProperty);
		}
	}






	/////////////////////////////////////////////////////////////////
	// Null Implementations

	public void visitOWLAllDifferent(OWLAllDifferent owlAllDifferent) {
	}


	public void visitOWLAllValuesFrom(OWLAllValuesFrom owlAllValuesFrom) {
	}


	public void visitOWLCardinality(OWLCardinality owlCardinality) {
	}


	public void visitOWLComplementClass(OWLComplementClass owlComplementClass) {
	}


	public void visitOWLDataRange(OWLDataRange owlDataRange) {
	}


	public void visitOWLEnumeratedClass(OWLEnumeratedClass owlEnumeratedClass) {
	}


	public void visitOWLHasValue(OWLHasValue owlHasValue) {
	}


	public void visitOWLIntersectionClass(OWLIntersectionClass owlIntersectionClass) {
	}


	public void visitOWLMaxCardinality(OWLMaxCardinality owlMaxCardinality) {
	}


	public void visitOWLMinCardinality(OWLMinCardinality owlMinCardinality) {
	}


	public void visitOWLOntology(OWLOntology owlOntology) {
	}


	public void visitOWLSomeValuesFrom(OWLSomeValuesFrom owlSomeValuesFrom) {
	}


	public void visitOWLUnionClass(OWLUnionClass owlUnionClass) {
	}


	public void visitRDFDatatype(RDFSDatatype rdfDatatype) {
	}


	public void visitRDFExternalResource(RDFExternalResource rdfExternalResource) {
	}


	public void visitRDFIndividual(RDFIndividual rdfIndividual) {
	}


	public void visitRDFList(RDFList rdfList) {
	}


	public void visitRDFProperty(RDFProperty rdfProperty) {
	}


	public void visitRDFSLiteral(RDFSLiteral rdfsLiteral) {
	}


	public void visitRDFSNamedClass(RDFSNamedClass rdfsNamedClass) {
	}


	public void visitRDFUntypedResource(RDFUntypedResource rdfUntypedResource) {
	}

}

