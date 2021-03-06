PSM Librarian Tab plugin for Protege 3.1

Author: Monica Crubezy <crubezy@smi.stanford.edu>
Date: July 7, 2005

For documentation, instructions and example files, go to:
http://protege.stanford.edu/plugins/psmtab/PSMTab.html 

1. Contents of the edu.stanford.smi.protegex.psm folder:
- The psm.jar file contains the distribution of the PSM Librarian tab itself,- The vkbc.jar contains the distribution of the Mapping Interpreter- The other 3 Java archives are needed by the Mapping Interpreterto process TCL and Python mapping code. 

- The "psm_tab_projects" folder contains base projects needed to operate the PSM tab.

- The execution scripts (run_protege_psm.applescript for Mac OS X; run_protege_psm.sh otherwise) are needed to run Protege with a set of parameters appropriate for using the PSM tab in a certain setting: Specifically, the domain-to-method ontology-mapping component of the PSM tab depends on Java libraries that need to be explicitly on the classpath so that they can interpret TCL code in mapping relations. If you do not plan to use this functionality of the PSM tab, then you can just run Protege the usual way. 

2. Enabling the PSM Tab plugin on a project
To enable and run the PSM tab on one of your Protege projects, 
launch Protege using the above-mentioned execution script, open your project, go to the Project|Configure panel and check the PSMTab line. After you close the configuration panel, you should see the PSM tab displayed next to the other Protege tabs.

