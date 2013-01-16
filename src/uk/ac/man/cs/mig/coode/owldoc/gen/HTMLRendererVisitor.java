package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.model.visitor.OWLModelVisitor;
import edu.stanford.smi.protegex.owl.model.visitor.Visitable;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class HTMLRendererVisitor implements OWLModelVisitor {

	private Writer writer;

	public HTMLRendererVisitor(Writer writer) {
		this.writer = writer;
	}

	public void visitOWLAllDifferent(OWLAllDifferent owlAllDifferent) {

	}


	public void visitOWLAllValuesFrom(OWLAllValuesFrom owlAllValuesFrom) {
		try {
			owlAllValuesFrom.getOnProperty().accept(this);
			writer.write(KeyWords.SPACE);
			writer.write(KeyWords.ALL);
			writer.write(KeyWords.SPACE);
			if(owlAllValuesFrom.getFiller() instanceof OWLNamedClass == false) {
				writer.write("(");
				owlAllValuesFrom.getFiller().accept(this);
				writer.write(")");
			}
			else {
				owlAllValuesFrom.getFiller().accept(this);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitOWLCardinality(OWLCardinality owlCardinality) {
		try {
			owlCardinality.getOnProperty().accept(this);
			writer.write(KeyWords.SPACE);
			writer.write(KeyWords.EQ);
			writer.write(KeyWords.SPACE);
			writer.write("" + owlCardinality.getCardinality());
			writeQualifier(owlCardinality);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void visitOWLComplementClass(OWLComplementClass owlComplementClass) {
		try {
			writer.write(KeyWords.NOT);
			writer.write("(");
			owlComplementClass.getComplement().accept(this);
			writer.write(")");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitOWLDataRange(OWLDataRange owlDataRange) {
		try {
			writer.write("{");
			for(Iterator it = owlDataRange.getOneOfValueLiterals().iterator(); it.hasNext();) {
				((RDFSLiteral)it.next()).accept(this);
				if(it.hasNext()) {
					writer.write(KeyWords.SPACE);
				}
			}
			writer.write("}");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitOWLDatatypeProperty(OWLDatatypeProperty owlDatatypeProperty) {
		writeLink(owlDatatypeProperty);
	}


	public void visitOWLEnumeratedClass(OWLEnumeratedClass owlEnumeratedClass) {
		try {
			writer.write("{");
			for(Iterator it = owlEnumeratedClass.getOneOf().iterator(); it.hasNext();) {
				Visitable v = (Visitable) it.next();
				v.accept(this);
				if(it.hasNext()) {
					writer.write(KeyWords.SPACE);
				}
			}
			writer.write("}");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitOWLHasValue(OWLHasValue owlHasValue) {
		try {
			owlHasValue.getOnProperty().accept(this);
			writer.write(KeyWords.SPACE);
			writer.write(KeyWords.HAS);
			writer.write(KeyWords.SPACE);
			Object hasVal = owlHasValue.getHasValue();
			if(hasVal instanceof Visitable) {
				((Visitable)hasVal).accept(this);
			}
			else {
				writer.write(hasVal.toString());
			}

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitOWLIndividual(OWLIndividual owlIndividual) {
		writeLink(owlIndividual);
	}


	public void visitOWLIntersectionClass(OWLIntersectionClass owlIntersectionClass) {
		try {
			for(Iterator it = owlIntersectionClass.getOperands().iterator(); it.hasNext();) {
				RDFSClass curCls = (RDFSClass) it.next();
				if(curCls instanceof OWLNamedClass == false) {
					writer.write("(");
				}
				curCls.accept(this);
				if(curCls instanceof OWLNamedClass == false) {
					writer.write(")");
				}
				if(it.hasNext()) {
					writer.write(KeyWords.SPACE);
					writer.write(KeyWords.AND);
					writer.write(KeyWords.SPACE);
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void writeQualifier(OWLCardinalityBase cardinalityBase) {
		try {
			if(cardinalityBase.getQualifier() != null) {
				writer.write(KeyWords.SPACE);
				cardinalityBase.getQualifier().accept(this);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void visitOWLMaxCardinality(OWLMaxCardinality owlMaxCardinality) {
		try {
			owlMaxCardinality.getOnProperty().accept(this);
			writer.write(KeyWords.SPACE);
			writer.write(KeyWords.MAX);
			writer.write(KeyWords.SPACE);
			writer.write("" + owlMaxCardinality.getCardinality());
			writeQualifier(owlMaxCardinality);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitOWLMinCardinality(OWLMinCardinality owlMinCardinality) {
		try {
			owlMinCardinality.getOnProperty().accept(this);
			writer.write(KeyWords.SPACE);
			writer.write(KeyWords.MIN);
			writer.write(KeyWords.SPACE);
			writer.write("" + owlMinCardinality.getCardinality());
			writeQualifier(owlMinCardinality);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitOWLNamedClass(OWLNamedClass owlNamedClass) {
		writeLink(owlNamedClass);
	}


	public void visitOWLObjectProperty(OWLObjectProperty owlObjectProperty) {
		writeLink(owlObjectProperty);
	}


	public void visitOWLOntology(OWLOntology owlOntology) {
		writeLink(owlOntology);
	}


	public void visitOWLSomeValuesFrom(OWLSomeValuesFrom owlSomeValuesFrom) {
		try {
			owlSomeValuesFrom.getOnProperty().accept(this);
			writer.write(KeyWords.SPACE);
			writer.write(KeyWords.SOME);
			writer.write(KeyWords.SPACE);
			if(owlSomeValuesFrom.getFiller() instanceof OWLNamedClass == false) {
				writer.write("(");
				owlSomeValuesFrom.getFiller().accept(this);
				writer.write(")");
			}
			else {
				owlSomeValuesFrom.getFiller().accept(this);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitOWLUnionClass(OWLUnionClass owlUnionClass) {
		try {
			for(Iterator it = owlUnionClass.getOperands().iterator(); it.hasNext();) {
				RDFSClass curCls = (RDFSClass) it.next();
				if(curCls instanceof OWLNamedClass == false) {
					writer.write("(");
				}
				curCls.accept(this);
				if(curCls instanceof OWLNamedClass == false) {
					writer.write(")");
				}
				if(it.hasNext()) {
					writer.write(KeyWords.SPACE);
					writer.write(KeyWords.OR);
					writer.write(KeyWords.SPACE);
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitRDFDatatype(RDFSDatatype rdfDatatype) {
		try {
			writer.write(rdfDatatype.getURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitRDFExternalResource(RDFExternalResource rdfExternalResource) {
		try {
			writer.write("<a href=\"" + rdfExternalResource.getResourceURI() + "\">");
			writer.write(rdfExternalResource.getResourceURI() + "</a>");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitRDFUntypedResource(RDFUntypedResource rdfUntypedResource) {
		try {
			writer.write("<a href=\"" + rdfUntypedResource.getURI() + "\">");
			writer.write(rdfUntypedResource.getURI() + "</a>");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}



	public void visitRDFIndividual(RDFIndividual rdfIndividual) {
		writeLink(rdfIndividual);
	}


	public void visitRDFList(RDFList rdfList) {
	}


	public void visitRDFProperty(RDFProperty rdfProperty) {
		writeLink(rdfProperty);
	}


	public void visitRDFSLiteral(RDFSLiteral rdfsLiteral) {
		try {
			writer.write("\"");
			String lang = rdfsLiteral.getLanguage();
			if(lang != null) {
				writer.write(rdfsLiteral.getString());
				writer.write("\" [lang: " + lang + "]");
			}
			else {
				// Typed literal
				writer.write(rdfsLiteral.toString());
				writer.write("\"");
				RDFSDatatype datatype = rdfsLiteral.getDatatype();
				if(datatype != null) {
					writer.write("^^");
					datatype.accept(this);
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void visitRDFSNamedClass(RDFSNamedClass rdfsNamedClass) {
		writeLink(rdfsNamedClass);
	}

	private void writeLink(RDFResource resource) {
		try {
			if(OWLDocPreferences.getInstance().isIncludedNamespace(resource.getNamespacePrefix())) {
				writer.write("<a href=\"");
				writer.write(OWLDocHelper.getFileName(resource));
				writer.write("\" target=\"content\">");
				writer.write(OWLDocHelper.getDisplayName(resource));
				writer.write("</a>");
			}
			else {
				writer.write(OWLDocHelper.getDisplayName(resource));
			}

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


}

