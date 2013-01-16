package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protegex.owl.model.OWLModel;

import java.io.*;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class OWLDocIndexPageGenerator {


	private OWLModel model;

	private File baseDirectory;

	private BufferedWriter writer;

	public OWLDocIndexPageGenerator(OWLModel model, File baseDirectory) {
		this.model = model;
		this.baseDirectory = baseDirectory;
	}

	public void generateOWLDoc() throws IOException {
		createWriter("index.html");
		writeHeader();
		writeBody();
		writeFooter();
		closeWriter();
	}

	private void writeHeader() throws IOException {
		writer.write("<html>\n");
		writer.write("<head>\n");
		writer.write("<title>\n");
		writer.write("OWLDoc\n");
		writer.write("</title>\n");
		writer.write("</head>");
	}

	private void writeBody() throws IOException {
		writer.write("<frameset cols=\"30%,70%\">");
		writer.write("<frameset rows=\"30%,70%\">");
		writer.write("<frame src=\"contents.html\" name=\"nav\" title=\"Contents\"/>");
		writer.write("<frame src=\"IndexAllResources.html\" name=\"subnav\" title=\"Index\"/>");
		writer.write("</frameset>");
		writer.write("<frame src=\"ontology.html\" name=\"content\" title=\"Content\"/>");
		writer.write("<noframes>");
		writer.write("IndexAllResources.html");
		writer.write("</noframes>");
		writer.write("</frameset>");
	}

	private void writeFooter() throws IOException {
		writer.write("</html>");
	}

	private void createWriter(String fileName) throws IOException {
		File file = new File(baseDirectory, fileName);
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

	}

	private void closeWriter() throws IOException {
		writer.flush();
		writer.close();
	}

}

