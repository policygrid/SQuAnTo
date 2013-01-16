package uk.ac.man.cs.mig.coode.owldoc.lang;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.apache.xml.serialize.XMLSerializer;
import org.apache.xml.serialize.OutputFormat;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 21, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class LanguageMap {

	private static LanguageMap instance;

	private HashMap currentLanguageMap;

	private HashMap langMapMap;

	private String [] DEFAULT_LANGUAGE_FILE_NAMES = new String [] {"Labels-en.xml"
	                                                               };

	private void loadDefaults() {
		currentLanguageMap.clear();
		currentLanguageMap.put(LanguageKeyWords.ABSTRACT_SYNTAX, "Abstract Syntax");
		currentLanguageMap.put(LanguageKeyWords.ALL_CLASSES, "All Classes");
		currentLanguageMap.put(LanguageKeyWords.ALL_DATATYPE_PROPERTIES, "All Datatype Properties");
		currentLanguageMap.put(LanguageKeyWords.ALL_INDIVIDUALS, "All Individuals");
		currentLanguageMap.put(LanguageKeyWords.ALL_OBJECT_PROPERTIES, "All Object Properties");
		currentLanguageMap.put(LanguageKeyWords.ALL_RESOURCES, "All Resources");
		currentLanguageMap.put(LanguageKeyWords.ANNOTATIONS, "Annotations");
		currentLanguageMap.put(LanguageKeyWords.ALL_ANNOTATION_PROPERTIES, "All Annotation Properties");
		currentLanguageMap.put(LanguageKeyWords.ANNOTATION_PROPERTIES, "Annotation Properties");
		currentLanguageMap.put(LanguageKeyWords.CHARACTERISTICS, "Characteristics");
		currentLanguageMap.put(LanguageKeyWords.CLASS, "Class");
		currentLanguageMap.put(LanguageKeyWords.CLASS_DESC_DEF, "Class Description/Definition (Necessary Conditions)");
		currentLanguageMap.put(LanguageKeyWords.CLASSES, "Classes");
		currentLanguageMap.put(LanguageKeyWords.CONTENTS, "Contents");
		currentLanguageMap.put(LanguageKeyWords.DATATYPE_PROPERTIES, "Datatype Properties");
		currentLanguageMap.put(LanguageKeyWords.DEFAULT_NAMESPACE, "Default Namespace");
		currentLanguageMap.put(LanguageKeyWords.DIFFERENT_FROM, "Different From");
		currentLanguageMap.put(LanguageKeyWords.DISJOINT_CLASSES, "Disjoint Classes");
		currentLanguageMap.put(LanguageKeyWords.DOMAIN, "Domain");
		currentLanguageMap.put(LanguageKeyWords.DOMAIN_OF, "Domain of");
		currentLanguageMap.put(LanguageKeyWords.EQUIVALENT_CLASSES,
		                       "Equivalent Classes (Necessary and Sufficient Conditions)");
		currentLanguageMap.put(LanguageKeyWords.EQUIVALENT_PROPERTIES, "Equivalent Properties");
		currentLanguageMap.put(LanguageKeyWords.FUNCTIONAL, "Functional");
		currentLanguageMap.put(LanguageKeyWords.HIERARCHY, "Hierarchy");
		currentLanguageMap.put(LanguageKeyWords.IMPORTS, "Imports");
		currentLanguageMap.put(LanguageKeyWords.INDIVIDUALS, "Individuals");
		currentLanguageMap.put(LanguageKeyWords.INVERSE_FUNCTIONAL, "Inverse Functional");
		currentLanguageMap.put(LanguageKeyWords.INVERSE_PROPERTY, "Inverse Property");
		currentLanguageMap.put(LanguageKeyWords.NAMESPACES, "Namespaces");
		currentLanguageMap.put(LanguageKeyWords.OBJECT_PROPERTIES, "Object Properties");
		currentLanguageMap.put(LanguageKeyWords.ONTOLOGY, "Ontology");
		currentLanguageMap.put(LanguageKeyWords.RANGE, "Range");
		currentLanguageMap.put(LanguageKeyWords.RELATIONSHIPS, "Relationships");
		currentLanguageMap.put(LanguageKeyWords.RESOURCES, "Resources");
		currentLanguageMap.put(LanguageKeyWords.SAME_AS, "Same As");
		currentLanguageMap.put(LanguageKeyWords.SUPER_PROPERTIES, "Super Properties");
		currentLanguageMap.put(LanguageKeyWords.SUPERCLASSES, "Super Classes");
		currentLanguageMap.put(LanguageKeyWords.SYMMETRIC, "Symmetric");
		currentLanguageMap.put(LanguageKeyWords.TRANSITIVE, "Transitive");
		currentLanguageMap.put(LanguageKeyWords.TYPES, "Types");
		currentLanguageMap.put(LanguageKeyWords.USAGE, "Usage");
	}


	public static synchronized LanguageMap getInstance() {
		if(instance == null) {
			instance = new LanguageMap();
			LanguageMap m = LanguageMap.getInstance();
			m.setCurrentLanguage("English");
		}
		return instance;
	}


	private LanguageMap() {
		currentLanguageMap = new HashMap();
		loadDefaults();
		langMapMap = new HashMap();
		loadDefaultLangauges();
		loadPluginLanguages();
	}


	private void loadDefaultLangauges() {
		for(int i = 0; i < DEFAULT_LANGUAGE_FILE_NAMES.length; i++) {
			InputStream curIS = LanguageMap.class.getResourceAsStream("file/" + DEFAULT_LANGUAGE_FILE_NAMES[i]);
			loadLanguage(curIS);
		}
	}


	private void loadPluginLanguages() {
		File folder = OWLDocLanguageHelper.getLangauageFolder();
		if(folder != null) {
			loadLanguageOptions(folder);
		}
	}


	private void loadLanguageOptions(File folder) {
		if(folder != null) {
			File[] files = folder.listFiles();
			for(int i = 0; i < files.length; i++) {
				File curFile = files[i];
				if(curFile.getName().endsWith("xml")) {
					try {
						FileInputStream fis = new FileInputStream(curFile);
						loadLanguage(fis);
					}
					catch(FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}


	private void loadLanguage(InputStream is) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setExpandEntityReferences(false);
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		}
		catch(ParserConfigurationException e) {
			e.printStackTrace();
		}
		try {
			Document doc = builder.parse(is);
			loadLanguage(doc);
		}
		catch(SAXException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}


	public Collection getAvailableLanguages() {
		return new ArrayList(langMapMap.keySet());
	}


	private void loadLanguage(Document doc) {
		Element root = doc.getDocumentElement();
		Element element = (Element) root.getElementsByTagName("Description").item(0);
		String name = element.getAttribute("name");
		HashMap wordsMap = new HashMap();
		Element labelsElement = (Element) root.getElementsByTagName("Labels").item(0);
		NodeList list = labelsElement.getElementsByTagName("label");
		for(int i = 0; i < list.getLength(); i++) {
			Element curElement = (Element) list.item(i);
			String key = curElement.getAttribute("labelname");
			String val = curElement.getAttribute("value");
			val = OWLDocLanguageHelper.escape(val);
			wordsMap.put(key, val);
		}
		langMapMap.put(name, wordsMap);
	}


	public void setCurrentLanguage(String langauge) {
		HashMap m = (HashMap) langMapMap.get(langauge);
		if(m != null) {
			currentLanguageMap = m;
		}
	}


	public String getWord(String key) {
		String word = (String) currentLanguageMap.get(key);
		if(word == null) {
			word = "!!! ERROR - No label defined for " + key + " !!!";
		}
		return (String) currentLanguageMap.get(key);
	}


	public String toString() {
		return currentLanguageMap.toString();
	}


	public static void main(String[] args) {
		LanguageMap m = LanguageMap.getInstance();
		m.setCurrentLanguage("Espanol");
		System.out.println(m);
	}


	private static void createDefXMLFile(String pathName) {
		try {
			String filePath = pathName;
			File file = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("OWLDoc");
			doc.appendChild(root);
			Element langNameElement = doc.createElement("Description");
			langNameElement.setAttribute("name", "English");
			root.appendChild(langNameElement);
			Element labelsElement = doc.createElement("Labels");
			root.appendChild(labelsElement);
			for(Iterator it = LanguageMap.getInstance().currentLanguageMap.keySet().iterator(); it.hasNext();) {
				String curKey = (String) it.next();
				Element curElement = doc.createElement("label");
				curElement.setAttribute("labelname", (String) curKey);
				curElement.setAttribute("value", (String) LanguageMap.getInstance().currentLanguageMap.get(curKey));
				labelsElement.appendChild(curElement);
			}
			OutputFormat format = new OutputFormat();
			format.setIndent(4);
			try {
				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				XMLSerializer serializer = new XMLSerializer(writer, format);
				serializer.serialize(doc);
				writer.flush();
				writer.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		catch(ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}

