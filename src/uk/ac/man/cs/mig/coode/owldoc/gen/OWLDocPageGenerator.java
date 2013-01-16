package uk.ac.man.cs.mig.coode.owldoc.gen;

import edu.stanford.smi.protege.util.SystemUtilities;

import java.io.*;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 19, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public abstract class OWLDocPageGenerator implements OWLDocGenerator {


	private File baseDirectory;

	private BufferedWriter writer;

	private String fileName;

	private String title;

	public OWLDocPageGenerator(File baseDirectory, String fileName, String title) {
		this.baseDirectory = baseDirectory;
		this.fileName = fileName;
		this.title = title;
	}

	public void generateOWLDoc() throws IOException {
		createWriter();
		writeHeader();
		generateContent();
		//writeFooter();
		closeWriter();
	}

	public void write(String s) throws IOException {
		writer.write(s);
	}

	public Writer getWriter() {
		return this.writer;
	}

	protected abstract void generateContent() throws IOException;

	private void writeHeader() throws IOException {
		write("<html>\n" +
		             "<head>\n");
		StyleSheetGenerator ssgen = new StyleSheetGenerator(writer);
		ssgen.generateStyleSheet();
		write("<META http-equiv=\"Content-Type\" content=\"text/html; charset=\"" +
		      SystemUtilities.getFileEncoding() + "\">");
		write("</head>\n" +
		             "<body><h2>");
		write(title);
		write("</h2>");
	}

	private void writeFooter() throws IOException {
		write("<div style=\"font-size: 8px; text-align: right; margin-top:10px\">Generated with <a href=\"http://www.co-ode.org/downloads/owldoc\" target=\"newwindow\">OWLDoc</a></div></body>\n" +
		             "</html>");
	}

	private void createWriter() throws IOException {
		File file = new File(baseDirectory, fileName);
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

	}

	private void closeWriter() throws IOException {
		writer.flush();
		writer.close();
	}
}

