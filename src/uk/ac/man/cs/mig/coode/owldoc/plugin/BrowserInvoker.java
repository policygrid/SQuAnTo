package uk.ac.man.cs.mig.coode.owldoc.plugin;

import java.io.IOException;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 24, 2005<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class BrowserInvoker {

	public static void showPage(String url) {

		String platformName = System.getProperty("os.name");
		if(platformName.indexOf("Windows") != -1) {
			// On windows
			String params = "rundll32 url.dll,FileProtocolHandler " + url;
			try {
				Process p = Runtime.getRuntime().exec(params);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (platformName.indexOf("OS X") != -1){
			// Mac
			String [] params = new String [] {"open", url};
			try {
				Process p = Runtime.getRuntime().exec(params);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			// Linux or unix
			// Try firefox
			try {
				String [] params = new String [] {"firefox", url};
				Process p = Runtime.getRuntime().exec(params);
			}
			catch(IOException e) {
				System.err.println("Could not launch browser (tried firefox)");
			}
		}
	}

	public static void main(String [] args) {
		BrowserInvoker.showPage("/Users/matthewhorridge/Desktop/OWL Doc Test Spaces/index.html");
	}


}

