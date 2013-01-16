package uk.ac.man.cs.mig.coode.owldoc.gen;

import java.io.Writer;
import java.io.IOException;
import java.io.File;
import java.util.Collection;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 18, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class StyleSheetGenerator {

	private Writer writer;

	public StyleSheetGenerator(Writer writer) {
		this.writer = writer;
	}

	public static final String KEYWORD_CLASS = "keyword";

	public static final String OWL_OBJECT_CLASS = "onc";

	public static final String CODE_BOX_CLASS = "codebox";

	public void generateStyleSheet() {
		
		String stylesheet = OWLDocPreferences.getInstance().getStyleSheet();
	
		try {
			if (stylesheet.length() == 0){
				writer.write("<style>\n");
				writeClass(KEYWORD_CLASS, "arial", "arial", "11px");
				writeClass(OWL_OBJECT_CLASS, "arial", "arial", "11px");
				writer.write("." + CODE_BOX_CLASS +  "{\n" + "\tborder: 1px solid #c7cfd5; margin: 1px; margin-bottom: 1px; text-align: left; overflow: auto;}");
				writer.write(".spacer {margin: 1px; padding: 1px;}");
				writer.write("body {margin: 0; padding: 0; font: 11px Arial;}\n" + "table, td {font: 11px Arial; color: #000;}");
				writer.write("</style>\n");
			}
			else
			{
				writer.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+stylesheet+"\">\n");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	private void writeClass(String name, String color, String font, String size) {
		try {
			writer.write(".");
			writer.write(name);
			writer.write(" {");
			writer.write("color: ");
			writer.write(color);
			writer.write("; font-family: ");
			writer.write(font);
			writer.write("; font-size: ");
			writer.write(size);
			writer.write(";");
			writer.write("}\n");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	static public void writeCodeBox(Writer writer) {
		try {
			writer.write("<div class=\"");
			writer.write(CODE_BOX_CLASS);
			writer.write("\">");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}

