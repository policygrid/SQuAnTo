package org.squanto.init.gui;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;
import javax.swing.tree.*;


import com.borland.jbcl.layout.*;
import org.squanto.init.codes.*;
import org.squanto.init.document.*;
import org.squanto.init.util.*;

import org.squanto.init.project.*;
import org.squanto.init.ontology.*;


import edu.stanford.smi.protegex.owl.model.*;
import edu.stanford.smi.protegex.owl.*;      
import edu.stanford.smi.protegex.owl.ui.widget.*;

import edu.stanford.smi.protege.model.*;
import edu.stanford.smi.protege.resource.*;
import edu.stanford.smi.protege.ui.*;
import edu.stanford.smi.protege.util.*;

import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import uk.ac.man.cs.mig.coode.owldoc.gen.OWLModelDocGenerator;


/**
 * <p>Title: SQuAT</p>
 * <p>Description: RDF Annotation</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class MainGuiFrame extends JFrame {
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();
  JMenuBar mainMenuBar = new JMenuBar();
  JMenu fileMenu = new JMenu();
  
  JMenuItem searchItem = new JMenuItem(); 
  
  JMenuItem fileMenu_Exit = new JMenuItem();
  JMenu helpMenu = new JMenu();
  JMenuItem helpMenuAbout = new JMenuItem();
  JToolBar toolBar = new JToolBar();
  JButton openProjectButton = new JButton();
  JButton saveProjectButton = new JButton();
  JButton helpButton = new JButton();
  ImageIcon openFileImage = new ImageIcon(org.squanto.init.gui.MainGuiFrame.class.
                                          getResource("images/openFile.png"));
  ImageIcon closeFileImage = new ImageIcon(org.squanto.init.gui.MainGuiFrame.class.
                                           getResource("images/closeFile.png"));
  ImageIcon helpImage = new ImageIcon(org.squanto.init.gui.MainGuiFrame.class.
                                      getResource("images/help.png"));
  ImageIcon goUpImage = new ImageIcon(org.squanto.init.gui.MainGuiFrame.class.
                                      getResource("images/up.PNG"));
  ImageIcon goDownImage = new ImageIcon(org.squanto.init.gui.MainGuiFrame.class.
                                        getResource("images/down.PNG"));
  ImageIcon exitImage = new ImageIcon(org.squanto.init.gui.MainGuiFrame.class.
                                        getResource("images/exit.png"));
  JLabel statusBar = new JLabel();
  RTFEditorKit rtf = new RTFEditorKit();
  JPopupMenu documentPopupMenu = new JPopupMenu();
  JPopupMenu folderPopupMenu = new JPopupMenu();
  JPanel mainPanel = new JPanel();
  JSplitPane leftPanelSplitPane = new JSplitPane();
  JSplitPane rightPanelSplitPane = new JSplitPane();
  DefaultMutableTreeNode codingRootNode = new DefaultMutableTreeNode("Coding");
  DefaultMutableTreeNode codingCodesNode = new DefaultMutableTreeNode("Codes");
  DefaultMutableTreeNode codingAnnotationNode = new DefaultMutableTreeNode(
      "Annotations");
  DefaultMutableTreeNode codingMemoNode = new DefaultMutableTreeNode("Memos");
  DefaultMutableTreeNode associationsTreeNode = new DefaultMutableTreeNode("Associations");
  
  
  
  DefaultMutableTreeNode codingFreeCodeNode = new DefaultMutableTreeNode(
      "Free Codes");
  DefaultMutableTreeNode codingStructuredCodeNode = new DefaultMutableTreeNode(
      "Structured Codes");
  DefaultMutableTreeNode codingOtherCodeNode = new DefaultMutableTreeNode(
      "Other Codes");
  DefaultTreeModel codesdtm = new DefaultTreeModel(codingRootNode);
  JTree codesTree = new JTree(codesdtm);
  DefaultMutableTreeNode directoryRootNode = new DefaultMutableTreeNode(
      "Folders");
  DefaultMutableTreeNode documentNode = new DefaultMutableTreeNode("Documents");
  DefaultMutableTreeNode ontologyNode = new DefaultMutableTreeNode("Structured Codes");
  DefaultTreeModel directorydtm = new DefaultTreeModel(directoryRootNode);
  JTree directoryTree = new JTree(directorydtm);
  JMenu documentPopupMenu_CodeMenu = new JMenu();
  JMenuItem codeMenu_Document = new JMenuItem();
  //Menu for new structured coding
  //--------------------------------------------------------------------------
  JMenuItem codeMenu_DocumentStructured = new JMenuItem();
  
  JMenuItem codeMenu_Code = new JMenuItem();
  JMenu ontologyMenu = new JMenu();
  JMenuItem ontologyMenu_Close = new JMenuItem();
  GridLayout gridLayout3 = new GridLayout();
  GridLayout gridLayout1 = new GridLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  PaneLayout paneLayout1 = new PaneLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel leftPanel = new JPanel();
  JPanel rightPanel = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel centrePanel = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JScrollPane documentScrollPane = new JScrollPane();
  JEditorPane documentPane = new JEditorPane();
  JMenu ontologyMenu_OpenOntology = new JMenu();
  JMenuItem openOntologyMenu_Local = new JMenuItem();
  JMenuItem openOntologyMenu_Remote = new JMenuItem();
  JMenuItem fileMenu_CloseDocument = new JMenuItem();
  JMenu fileMenu_OpenDocumentMenu = new JMenu();
  JMenuItem openDocumentMenu_Local = new JMenuItem();
  JMenuItem openDocumentMenu_Remote = new JMenuItem();
  JMenuItem codeMenu_Invivo = new JMenuItem();
  JMenu codeMenu_Concepts = new JMenu();
  JMenuItem codeMenu_Concepts_Goup = new JMenuItem();
  JMenuItem codeMenu_Concepts_Godown = new JMenuItem();
  int conceptGoUp = 2;
  int conceptGoDown = 12;
  int existGoUp = 2;
  int existGoDown = 12;
  JScrollPane codesScrollPane = new JScrollPane();
  JScrollPane directoryScrollPane = new JScrollPane();
  JEditorPane infoPane = new JEditorPane();
  JScrollPane infoScrollPane = new JScrollPane();
  
  JScrollPane ontologyScrollPane = new JScrollPane(); //Panel showing ontology context
  JScrollPane resourcesScrollPane = new JScrollPane(); //Panel showing related resources
  
  OntologyInfo ontologyInfo = new OntologyInfo();
  RelatedResources  relatedResources;
  
   
  
  JMenu codesMenu = new JMenu();
  JMenu codeMenu_Existing = new JMenu();
  JMenuItem codeMenu_Existing_Goup = new JMenuItem();
  JMenuItem codeMenu_Existing_Godown = new JMenuItem();
  JMenuItem codesMenu_Information = new JMenuItem();
  JMenuItem codesMenu_Similarities = new JMenuItem();
  JMenuItem codesMenu_Delete = new JMenuItem();
  JMenu viewsMenu = new JMenu();
  JRadioButtonMenuItem viewsMenu_HideDirectory = new JRadioButtonMenuItem();
  JRadioButtonMenuItem viewsMenu_HideCoding = new JRadioButtonMenuItem();
  JRadioButtonMenuItem viewsMenu_Show = new JRadioButtonMenuItem();
  ButtonGroup viewsGroup = new ButtonGroup();
  ArrayList documents = new ArrayList();
  MainDocumentDAO maindoc = new MainDocumentDAO();
  JMenu folderPopupMenu_ImportMenu = new JMenu();
  JMenuItem importMenu_Document = new JMenuItem();
  JMenuItem importMenu_Ontology = new JMenuItem();
  JMenu folderPopupMenu_CloseMenu = new JMenu();
  JMenuItem closeMenu_Document = new JMenuItem();
  JMenuItem closeMenu_Ontology = new JMenuItem();
  CustomDefaultRenderer dtcr1 = new CustomDefaultRenderer();
  CustomDefaultRenderer dtcr2 = new CustomDefaultRenderer();
  DefaultHighlighter.DefaultHighlightPainter highlight;
  JMenuItem codesMenu_Search = new JMenuItem();
  JMenu codesMenu_SortMenu = new JMenu();
  JMenuItem sortMenu_Name = new JMenuItem();
  JMenuItem sortMenu_Popularity = new JMenuItem();
  JMenu otherMenu = new JMenu();
  JMenu otherMenu_DeleteMenu = new JMenu();
  JMenuItem otherDeleteMenu_Annotations = new JMenuItem();
  JMenuItem otherDeleteMenu_Memos = new JMenuItem();
  JMenu otherMenu_ExportMenu = new JMenu();
  JMenuItem otherExportMenu_Annotations = new JMenuItem();
  JMenuItem otherExportMenu_Memos = new JMenuItem();
  ProjectDAO project = null;
  JMenuItem fileMenu_OpenProject = new JMenuItem();
  JMenuItem fileMenu_SaveProject = new JMenuItem();
  JMenuItem fileMenu_CreateProject = new JMenuItem();
  JMenuItem fileMenu_CloseProject = new JMenuItem();
  boolean savedcodes = true;
  boolean savedproject = false;
  JMenuItem codeMenu_View = new JMenuItem();
  JMenuItem codesMenu_View = new JMenuItem();
  JButton closeButton = new JButton();
  JMenu codesMenu_Save = new JMenu();
  JMenuItem codesMenu_SaveLocal = new JMenuItem();
  JMenuItem codesMenu_SaveRemote = new JMenuItem();
  JPopupMenu treePopup = new JPopupMenu();
  
  
  //Right panels
  


  public MainGuiFrame() {
    try {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Component initialization.
   *
   * @throws java.lang.Exception
   */
  private void jbInit() throws Exception {
  
  try {
   relatedResources = new RelatedResources();
  }  catch (Exception exc) {System.out.println(exc);}
   
    //Open ontology
    openOntologyMenu_Local.setText("Local Structured Codes File");
    openOntologyMenu_Remote.setText("Remote Structured Codes File");
    openOntologyMenu_Local.addActionListener(new
               MainGuiFrame_openOntologyMenu_Local_actionAdapter(this));
    ontologyMenu_OpenOntology.setEnabled(false);
    ontologyMenu_OpenOntology.setText("Open Structured Codes File");
   
   //Close document
    fileMenu_CloseDocument.setEnabled(false);
    fileMenu_CloseDocument.setFocusPainted(false);
    fileMenu_CloseDocument.setText("Close Document");
    fileMenu_CloseDocument.addActionListener(new
               MainGuiFrame_fileMenu_CloseDocument_actionAdapter(this));
  //Open Document    
    fileMenu_OpenDocumentMenu.setText("Open Document");
    openDocumentMenu_Local.setText("Local Document");
    openDocumentMenu_Remote.setText("Remote Document");
    openDocumentMenu_Local.addActionListener(new
               MainGuiFrame_openDocumentMenu_Local_actionAdapter(this));
  //Code menu               
    codeMenu_Invivo.setEnabled(false);
    codeMenu_Invivo.setText("In Vivo Coding");
    codeMenu_Invivo.addActionListener(new
              MainGuiFrame_codeMenu_Invivo_actionAdapter(this));
              
              
    openProjectButton.addActionListener(new
              MainGuiFrame_openProjectButton_actionAdapter(this));
              
    ontologyMenu_Close.addActionListener(new
             MainGuiFrame_ontologyMenu_Close_actionAdapter(this));
             
    codeMenu_Document.setEnabled(false);
    codeMenu_Document.addActionListener(
        new MainGuiFrame_codeMenu_Document_actionAdapter(this));
   
   
    codeMenu_DocumentStructured.addActionListener(
        new MainGuiFrame_codeMenu_DocumentStructured_actionAdapter(this));     
        
        
    codeMenu_Code.setEnabled(false);
    codeMenu_Concepts.setEnabled(false);
    codeMenu_DocumentStructured.setEnabled(false);
    codeMenu_Concepts.setRolloverEnabled(true);
    codeMenu_Concepts.setText("New Structured Code");
    codeMenu_Concepts_Goup.setIcon(goUpImage);
    codeMenu_Existing_Goup.setIcon(goUpImage);
    codeMenu_Concepts_Godown.setIcon(goDownImage);
    codeMenu_Existing_Godown.setIcon(goDownImage);
    codeMenu_Concepts_Goup.setText("Go Up");
    codeMenu_Concepts_Goup.addMouseMotionListener(
        new MainGuiFrame_codeMenu_Concepts_Goup_mouseMotionAdapter(this));
    codeMenu_Concepts_Goup.addMouseListener(new
         MainGuiFrame_codeMenu_Concepts_Goup_mouseAdapter(this));
    codeMenu_Concepts_Godown.setRolloverEnabled(true);
    codeMenu_Concepts_Godown.setText("Go Down");
    codeMenu_Concepts_Godown.addMouseMotionListener(
        new MainGuiFrame_codeMenu_Concepts_Godown_mouseMotionAdapter(this));
    codeMenu_Concepts_Godown.addMouseListener(new
            MainGuiFrame_codeMenu_Concepts_Godown_mouseAdapter(this));
    documentPopupMenu_CodeMenu.setEnabled(false);
    documentPopupMenu_CodeMenu.setForeground(Color.white);
    documentPopupMenu_CodeMenu.setHorizontalAlignment(SwingConstants.LEADING);
    ontologyMenu_Close.setEnabled(false);
    documentPane.addFocusListener(new MainGuiFrame_documentPane_focusAdapter(this));
    documentPane.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
    
    codesMenu.setText("Coding");
    
    
        
    
    
    
    
    codeMenu_Existing.setEnabled(false);
    codeMenu_Existing.setText("Use Existing Code");
    codeMenu_Existing_Goup.setText("Go Up");
    codeMenu_Existing_Goup.addMouseMotionListener(
        new MainGuiFrame_codeMenu_Existing_Goup_mouseMotionAdapter(this));
    codeMenu_Existing_Goup.addMouseListener(
        new MainGuiFrame_codeMenu_Existing_Goup_mouseAdapter(this));
    codesMenu_Information.setEnabled(false);
    codesMenu_Information.setText("Coding Information");
    codesMenu_Information.addActionListener(
      new MainGuiFrame_codesMenu_Information_actionAdapter(this));
    codesMenu_Similarities.setEnabled(false);
    codesMenu_Similarities.setText("Find Similarities");
    codesMenu_Delete.setEnabled(false);
    codesMenu_Delete.setText("Delete Codes");
    codesMenu_Delete.addActionListener(
      new MainGuiFrame_codesMenu_Delete_actionAdapter(this));
    
    infoPane.setToolTipText("Various Information");
    infoPane.setEditable(false);
    
    
    
    codeMenu_Existing_Godown.addMouseMotionListener(
        new MainGuiFrame_codeMenu_Existing_Godown_mouseMotionAdapter(this));
    viewsMenu.setText("Views");
    viewsMenu_HideDirectory.setText("Hide Directory Pane");
    viewsMenu_HideDirectory.addActionListener(
        new MainGuiFrame_viewsMenu_HideDirectory_actionAdapter(this));
    viewsMenu_HideCoding.setText("Hide Coding Pane");
    viewsMenu_HideCoding.addActionListener(
        new MainGuiFrame_viewsMenu_HideCoding_actionAdapter(this));
    viewsMenu_Show.setSelected(true);
    viewsMenu_Show.setText("Show Both Panes");
    viewsMenu_Show.addActionListener(
        new MainGuiFrame_viewsMenu_Show_actionAdapter(this));
    helpButton.addActionListener(new MainGuiFrame_helpButton_actionAdapter(this));
    directoryTree.addTreeSelectionListener(new
        MainGuiFrame_directoryTree_treeSelectionAdapter(this));
    directoryTree.addMouseListener(new MainGuiFrame_directoryTree_mouseAdapter(this));
    folderPopupMenu_ImportMenu.setText("Import");
    importMenu_Document.setEnabled(false);
    importMenu_Document.setText("Document");
    importMenu_Document.addActionListener(
      new MainGuiFrame_importMenu_Document_actionAdapter(this));
    importMenu_Ontology.setEnabled(false);
    importMenu_Ontology.setText("Structured Codes File");
    importMenu_Ontology.addActionListener(
      new MainGuiFrame_importMenu_Ontology_actionAdapter(this));
    folderPopupMenu_CloseMenu.setText("Close");
    closeMenu_Document.setText("Document");
    closeMenu_Document.addActionListener(
      new MainGuiFrame_closeMenu_Document_actionAdapter(this));
    closeMenu_Ontology.setText("Structured Codes File");
    closeMenu_Ontology.addActionListener(
      new MainGuiFrame_closeMenu_Ontology_actionAdapter(this));
    directoryTree.setToolTipText("Folders Tree");
    codesMenu_Search.setEnabled(false);
    codesMenu_Search.setText("Search for Codes");
    codesMenu_Search.addActionListener(new MainGuiFrame_codesMenu_Search_actionAdapter(this));
    sortMenu_Name.setEnabled(false);
    sortMenu_Name.setText("Name");
    sortMenu_Popularity.setEnabled(false);
    sortMenu_Popularity.setText("Popularity");
    codesMenu_SortMenu.setEnabled(false);
    codesMenu_SortMenu.setText("Sort Codes By");
    otherMenu.setText("Other");
    otherMenu_DeleteMenu.setEnabled(false);
    otherMenu_DeleteMenu.setText("Delete");
    otherDeleteMenu_Annotations.setEnabled(false);
    otherDeleteMenu_Annotations.setText("Annotations");
    otherDeleteMenu_Memos.setEnabled(false);
    otherDeleteMenu_Memos.setText("Memos");
    otherMenu_ExportMenu.setEnabled(false);
    otherMenu_ExportMenu.setText("Export");
    otherExportMenu_Annotations.setEnabled(false);
    otherExportMenu_Annotations.setText("Annotations");
    otherExportMenu_Memos.setEnabled(false);
    otherExportMenu_Memos.setText("Memos");
    fileMenu_OpenProject.setText("Open Project");
    fileMenu_OpenProject.addActionListener(
      new MainGuiFrame_fileMenu_OpenProject_actionAdapter(this));
    fileMenu_SaveProject.setEnabled(false);
    fileMenu_SaveProject.setText("Save Project");
    fileMenu_SaveProject.addActionListener(
      new MainGuiFrame_fileMenu_SaveProject_actionAdapter(this));
    fileMenu_CreateProject.setText("Create New Project");
    fileMenu_CreateProject.addActionListener(
      new MainGuiFrame_fileMenu_CreateProject_actionAdapter(this));
    saveProjectButton.addActionListener(
      new MainGuiFrame_saveProjectButton_actionAdapter(this));
    fileMenu_CloseProject.setEnabled(false);
    fileMenu_CloseProject.setText("Close Project");
    fileMenu_CloseProject.addActionListener(
      new MainGuiFrame_fileMenu_CloseProject_actionAdapter(this));
    folderPopupMenu.setEnabled(false);
    saveProjectButton.setEnabled(false);
    codeMenu_View.setEnabled(false);
    codeMenu_View.setText("View All Codes");
    codeMenu_View.addActionListener(
      new MainGuiFrame_codeMenu_View_actionAdapter(this));
    codesMenu_View.setEnabled(false);
    codesMenu_View.setText("View All Codes");
    codesMenu_View.addActionListener(
      new MainGuiFrame_codesMenu_View_actionAdapter(this));
    //documentPane.setToolTipText("Document");
    statusBar.setToolTipText("Status Bar");
    toolBar.setToolTipText("Toolbar");
    closeButton.setEnabled(false);
    closeButton.setToolTipText("Close Project");
    closeButton.setIcon(exitImage);
    closeButton.addActionListener(
      new MainGuiFrame_closeButton_actionAdapter(this));
    closeButton.addActionListener(
      new MainGuiFrame_closeButton_actionAdapter(this));
    mainMenuBar.setRequestFocusEnabled(false);
    codesMenu_Save.setEnabled(false);
    codesMenu_Save.setText("Save Codes");
    codesMenu_SaveLocal.setText("Save Codes Locally");
    codesMenu_SaveLocal.addActionListener(new MainGuiFrame_codesMenu_SaveLocal_actionAdapter(this));
    codesMenu_SaveRemote.setText("Save Codes Remotely");
    codesMenu_SaveRemote.addActionListener(new MainGuiFrame_codesMenu_SaveRemote_actionAdapter(this));
    ontologyMenu.add(ontologyMenu_OpenOntology);
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(borderLayout1);
    setSize(new Dimension(400, 300));
    setTitle("Frame Title");
    statusBar.setText(" ");
    fileMenu.setText("File");
    fileMenu_Exit.setText("Exit");
    searchItem.setText("Search");
    fileMenu_Exit.addActionListener(new
                                    GUIFrame_jMenuFileExit_ActionAdapter(this));
    searchItem.addActionListener(new
                                    GUIFrame_jMenusearchItemActionAdapter(this));                                    
    helpMenu.setText("Help");
    helpMenuAbout.setText("About");
    helpMenuAbout.addActionListener(new
                                    GUIFrame_jMenuHelpAbout_ActionAdapter(this));
    
   
    
      
    
    mainPanel.setLayout(borderLayout3);
    
    documentPopupMenu_CodeMenu.setText("Code");
    codeMenu_Document.setText("Code Source Document");
    codeMenu_DocumentStructured.setText("Create new Structured Code");
    
    codeMenu_Code.setText("Create New Free Code");
    codeMenu_Code.addActionListener(new GUIFrame_codeMenu_Code_actionAdapter(this));
    ontologyMenu.setText("Structured Codes");
    ontologyMenu_Close.setText("Close Structured Codes File");
    contentPane.setPreferredSize(new Dimension(257, 97));
    
    
    centrePanel.setLayout(borderLayout6);
    documentPane.setEditable(false);
    documentPane.setSelectedTextColor(Color.black);
    documentPane.setSelectionColor(Color.orange);
    documentPane.setText("");
    documentPane.setContentType("text/rtf");
    documentPane.setEditorKit(rtf);
    documentPane.addMouseListener(new MainGuiFrame_documentPane_mouseAdapter(this));
    codesTree.addTreeSelectionListener(new
                                       MainGuiFrame_codesTree_treeSelectionAdapter(this));
    
    JMenuItem mi = new JMenuItem("Associate to Structured Code");
    mi.addActionListener(new MainGuiFrame_associateCode_actionAdapter(codesTree, associationsTreeNode));
    mi.setActionCommand("associate");
    treePopup.add(mi);
    treePopup.setOpaque(true);
    treePopup.setLightWeightPopupEnabled(true);
    
    //Tree mouse lintener
    codesTree.addMouseListener( new MouseAdapter() {
        public void mouseReleased( MouseEvent e ) {
            if ( e.isPopupTrigger()) {                
                treePopup.show( (JComponent)e.getSource(), e.getX(), e.getY() );
                 }
             }
            });
    
                                      
    codesTree.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));                                       
    
    fileMenu_OpenDocumentMenu.setActionCommand("Open Document");
    fileMenu_OpenDocumentMenu.setText("Open Document");
    openDocumentMenu_Local.setText("Local Document");
  
    directoryScrollPane.getViewport().add(directoryTree, null);
    //directoryScrollPane.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
    codesScrollPane.getViewport().add(codesTree, null);
    
  
    //codesScrollPane.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
    
    infoScrollPane.getViewport().add(infoPane, null);
    mainPanel.add(centrePanel, BorderLayout.CENTER);
    centrePanel.add(documentScrollPane, BorderLayout.CENTER);
    //centrePanel.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
    documentScrollPane.getViewport().add(documentPane, null);
    //documentScrollPane.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
    mainMenuBar.add(fileMenu);
    mainMenuBar.add(ontologyMenu);
    mainMenuBar.add(codesMenu);
    mainMenuBar.add(viewsMenu);
    mainMenuBar.add(otherMenu);
    mainMenuBar.add(helpMenu);
    fileMenu.add(fileMenu_OpenProject);
    fileMenu.add(fileMenu_SaveProject);
    fileMenu.add(fileMenu_CreateProject);
    fileMenu.add(fileMenu_CloseProject);
    fileMenu.add(fileMenu_OpenDocumentMenu);
    fileMenu.add(fileMenu_OpenDocumentMenu);
    fileMenu.add(fileMenu_CloseDocument);
    fileMenu.addSeparator();
    fileMenu.add(searchItem);
    fileMenu.addSeparator();
    fileMenu.add(fileMenu_Exit);
   
    
    helpMenu.add(helpMenuAbout);
    setJMenuBar(mainMenuBar);
    openProjectButton.setIcon(openFileImage);
    openProjectButton.setToolTipText("Open Project");
    saveProjectButton.setIcon(closeFileImage);
    saveProjectButton.setToolTipText("Save Project");
    helpButton.setIcon(helpImage);
    helpButton.setToolTipText("Help");
    
    
    //openProjectButton.setText("Open Project");
    //saveProjectButton.setText("Save Project");
    //closeButton.setText("Close");
    //helpButton.setText("Help");
    
    openProjectButton.setHorizontalTextPosition(JButton.CENTER);
    openProjectButton.setVerticalTextPosition(JButton.BOTTOM);
    saveProjectButton.setHorizontalTextPosition(JButton.CENTER);
    saveProjectButton.setVerticalTextPosition(JButton.BOTTOM);
    closeButton.setHorizontalTextPosition(JButton.CENTER);
    closeButton.setVerticalTextPosition(JButton.BOTTOM);
    helpButton.setHorizontalTextPosition(JButton.CENTER);
    helpButton.setVerticalTextPosition(JButton.BOTTOM);
    
    JPanel spacer1 = new JPanel();
	spacer1.setMaximumSize(new Dimension(20,5));
	
	JPanel spacer2 = new JPanel();
	spacer2.setMaximumSize(new Dimension(1000,5));
    
    toolBar.add(openProjectButton);
    toolBar.add(saveProjectButton);
    toolBar.add(closeButton, null);
    toolBar.add(helpButton);
    toolBar.add(spacer1);
    JPanel sSearch = new SimpleSearch(documents, documentPane); 
    sSearch.setOpaque(false);
    toolBar.add(sSearch);    
   // toolBar.setTextLabels(true);
    
    contentPane.add(mainPanel, java.awt.BorderLayout.CENTER);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    
    JPanel resAndToolBar = new JPanel(new BorderLayout());
    resAndToolBar.add(toolBar, BorderLayout.CENTER);
    
    ImageIcon pLogo = new ImageIcon(org.squanto.init.gui.AboutDialog.class.getResource("images/PolicyGridLogo.png"));
        
    JLabel imgLabel = new JLabel(pLogo);
    imgLabel.setOpaque(false);
    
    toolBar.add(imgLabel);
    
    //imgLabel.setBackground(Color.WHITE);
    //imgLabel.setForeground(Color.WHITE);
    
    //resAndToolBar.add(spacer2,BorderLayout.WEST);
    //resAndToolBar.add(, BorderLayout.EAST);
   
    //Graphics g2d = base.getGraphics();
    //g2d.drawImage(pLogo.getImage(), 0, 0, base);
    
    contentPane.add(resAndToolBar, BorderLayout.NORTH);
    
    
    documentPopupMenu_CodeMenu.add(codeMenu_Code);
    documentPopupMenu_CodeMenu.add(codeMenu_Document);
    documentPopupMenu_CodeMenu.add(codeMenu_DocumentStructured);
    //documentPopupMenu_CodeMenu.add(codeMenu_Concepts);
    documentPopupMenu_CodeMenu.add(codeMenu_Existing);
    documentPopupMenu_CodeMenu.add(codeMenu_View);
    documentPopupMenu.add(documentPopupMenu_CodeMenu);
    documentPopupMenu_CodeMenu.addSeparator();
    documentPopupMenu_CodeMenu.add(codeMenu_Invivo);
    ontologyMenu.add(ontologyMenu_Close);
    fileMenu_OpenDocumentMenu.add(openDocumentMenu_Local);
    ontologyMenu_OpenOntology.add(openOntologyMenu_Local);
    ontologyMenu_OpenOntology.add(openOntologyMenu_Remote);
    fileMenu_OpenDocumentMenu.add(openDocumentMenu_Local);
    //Remote
    fileMenu_OpenDocumentMenu.add(openDocumentMenu_Remote);
    
    codeMenu_Concepts.add(codeMenu_Concepts_Goup);
    codeMenu_Concepts.addSeparator();
    this.setSize(1024, 768);
    documentPopupMenu_CodeMenu.setRolloverEnabled(true);
    codingCodesNode.add(codingFreeCodeNode);
    codingCodesNode.add(codingStructuredCodeNode);
    codingCodesNode.add(codingOtherCodeNode);
    codingRootNode.add(codingCodesNode);
    codingRootNode.add(codingAnnotationNode);
    codingRootNode.add(codingMemoNode);
    codingRootNode.add(associationsTreeNode);
    directoryRootNode.add(documentNode);
    directoryRootNode.add(ontologyNode);
    codesMenu.add(codesMenu_Save);
    codesMenu.add(codesMenu_Delete);
    codesMenu.add(codesMenu_View);
    codesMenu.add(codesMenu_Search);
    codesMenu.add(codesMenu_Similarities);
    codesMenu.add(codesMenu_Information);
    //Add menu for coding association
    
    
    JMenuItem mi1 = new JMenuItem("Associate to Structured Code");
    mi1.addActionListener(new MainGuiFrame_associateCode_actionAdapter(codesTree, associationsTreeNode));
    mi1.setActionCommand("associate");
    //treeAssociateMenu.setLightWeightPopupEnabled(true);
    
    codesMenu.add(mi1);
    
    codeMenu_Existing.add(codeMenu_Existing_Goup);
    viewsMenu.add(viewsMenu_HideCoding);
    viewsMenu.add(viewsMenu_HideDirectory);
    viewsMenu.add(viewsMenu_Show);
    codeMenu_Existing.addSeparator();
    codeMenu_Concepts.setAutoscrolls(true);
    codeMenu_Existing_Godown.setText("Go Down");
    codeMenu_Existing_Godown.addMouseListener(
      new MainGuiFrame_codeMenu_Existing_Godown_mouseAdapter(this));
    
    viewsGroup.add(viewsMenu_HideCoding);
    viewsGroup.add(viewsMenu_HideDirectory);
    viewsGroup.add(viewsMenu_Show);
    folderPopupMenu.add(folderPopupMenu_ImportMenu);
    folderPopupMenu.add(folderPopupMenu_CloseMenu);
    folderPopupMenu_ImportMenu.add(importMenu_Document);
    folderPopupMenu_ImportMenu.add(importMenu_Ontology);
    folderPopupMenu_CloseMenu.add(closeMenu_Document);
    folderPopupMenu_CloseMenu.add(closeMenu_Ontology);
    codesTree.getSelectionModel().setSelectionMode(
        TreeSelectionModel.SINGLE_TREE_SELECTION);
        
    
        
        
    directoryTree.getSelectionModel().setSelectionMode(
        TreeSelectionModel.SINGLE_TREE_SELECTION);
    directoryTree.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
    
    folderPopupMenu_CloseMenu.setEnabled(false);
    importMenu_Ontology.setEnabled(false);
    ToolTipManager.sharedInstance().registerComponent(codesTree);
    ToolTipManager.sharedInstance().registerComponent(directoryTree);
    codesTree.setCellRenderer(dtcr1);
    directoryTree.setCellRenderer(dtcr2);
    dtcr1.setIcon(new ImageIcon(org.squanto.init.gui.MainGuiFrame.class.
                                          getResource("images/document_annot.png")));
    dtcr2.setIcon(new ImageIcon(org.squanto.init.gui.MainGuiFrame.class.
                                          getResource("images/document_source.png")));
    Component c1 = dtcr1.getTreeCellRendererComponent(codesTree, "",
                                                     true, true, true, 0, true);
    Component c2 = dtcr2.getTreeCellRendererComponent(directoryTree, "",
                                                     true, true, true, 0, true);
    codesMenu.add(codesMenu_SortMenu);
    codesMenu_SortMenu.add(sortMenu_Name);
    codesMenu_SortMenu.add(sortMenu_Popularity);
    otherMenu.add(otherMenu_DeleteMenu);
    otherMenu.add(otherMenu_ExportMenu);
    otherMenu_DeleteMenu.add(otherDeleteMenu_Annotations);
    otherMenu_DeleteMenu.add(otherDeleteMenu_Memos);
    otherMenu_ExportMenu.add(otherExportMenu_Annotations);
    otherMenu_ExportMenu.add(otherExportMenu_Memos);
    codesMenu_Save.add(codesMenu_SaveLocal);
    codesMenu_Save.add(codesMenu_SaveRemote);
    openProjectButton.requestFocus(true);
    highlight = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
    
    
    //LeftPAnel
    
    leftPanelSplitPane.setMinimumSize(new Dimension(50, 28));
    leftPanelSplitPane.setPreferredSize(new Dimension(280, 44));
    leftPanelSplitPane.setBottomComponent(codesScrollPane);
    leftPanelSplitPane.setContinuousLayout(false);
    leftPanelSplitPane.setDividerSize(10);
    leftPanelSplitPane.setOneTouchExpandable(true);
    leftPanelSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    leftPanelSplitPane.add(codesScrollPane, JSplitPane.RIGHT);
    leftPanelSplitPane.add(directoryScrollPane, JSplitPane.LEFT);
    leftPanelSplitPane.setDividerLocation(249);
    
    
    leftPanel.setLayout(borderLayout4);  
    
    mainPanel.add(leftPanel, BorderLayout.WEST);
    leftPanel.add(leftPanelSplitPane, BorderLayout.WEST);
    
    
    
    
    //Right panels 
   //ontologyScrollPane.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
   //resourcesScrollPane.setBorder(BorderFactory.createMatteBorder(5,5, 5, 5, Color.white));
   
    ontologyScrollPane.getViewport().add(ontologyInfo.getPanel(), null);
    resourcesScrollPane.getViewport().add(relatedResources.getPanel(), null);
    
    //Right panel
    rightPanel.setPreferredSize(new Dimension(280, 23));
    rightPanel.setLayout(borderLayout5);
    

    rightPanelSplitPane.setMinimumSize(new Dimension(50, 28));
    rightPanelSplitPane.setPreferredSize(new Dimension(280, 44));
    rightPanelSplitPane.setBottomComponent(ontologyScrollPane); //BottomComponent
    
            
    rightPanelSplitPane.add(resourcesScrollPane,JSplitPane.RIGHT); //Bottom Component
            
   
    rightPanelSplitPane.add(ontologyScrollPane, JSplitPane.LEFT); //TopComponent
    
    //this.getGraphics().drawImage((new ImageIcon(org.squanto.init.gui.AboutDialog.class.getResource("images/PolicyGridLogo.png"))).getImage(), 10, 10,null); 
    
    rightPanelSplitPane.setContinuousLayout(false);
    rightPanelSplitPane.setDividerSize(10);
    rightPanelSplitPane.setOneTouchExpandable(true);
    rightPanelSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    
    rightPanelSplitPane.setDividerLocation(210);
  
    //image1 = new ImageIcon(org.squanto.init.gui.AboutDialog.class.getResource("images/PolicyGridLogo.png"));
    
    rightPanel.add(rightPanelSplitPane, BorderLayout.WEST);
    //rightPanel.add(infoScrollPane, BorderLayout.CENTER);
    
    mainPanel.add(rightPanel, BorderLayout.EAST);
    
    ProjectManager.getProjectManager().setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    
  }
  
  
  class MainGuiFrame_associateCode_actionAdapter implements java.awt.event.ActionListener {
      JTree tree;
      DefaultMutableTreeNode associationsTreeNode;

      MainGuiFrame_associateCode_actionAdapter(JTree tree, DefaultMutableTreeNode associationsTreeNode) {
        this.tree = tree;
        this.associationsTreeNode = associationsTreeNode;
      }
      public void actionPerformed(ActionEvent ae) {
          DefaultMutableTreeNode dmtn, node;
          
          TreePath path = tree.getSelectionPath();
          dmtn = (DefaultMutableTreeNode) path.getLastPathComponent();
          if (ae.getActionCommand().equals("associate")) {
              FileInputStream in = null;
              
                try{
         	     in = new FileInputStream(maindoc.getOntModel().getLocation());
         	    } catch (Exception ex) {System.out.println(ex);}
         	    
         	    OWLModel owlModel = null;
         	    
         	    try {
                 //owlModel = ProtegeOWL.createJenaOWLModelFromURI(uri);
                 owlModel = ProtegeOWL.createJenaOWLModelFromInputStream(in);
                 } catch (Exception ex) {System.out.println(ex);}
                
                 
                String ontClass = OWLUI.pickConcreteClass(owlModel,"Select Structured Code").getLocalName();
                 
                Object[] possibleValues = {"is a instance of","is a sub-category of", "is equivalent to", "is related to"};
         		Object selectedValue = JOptionPane.showInputDialog(null, "Select the relationship between " + dmtn.toString() + " and " + ontClass, 
         		                                                   "Relationship", JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);
         	
         		String relation = (String) selectedValue;
                 
                 
              
              node = new DefaultMutableTreeNode(" (" + dmtn.toString() + ") " + relation + " (" + ontClass + ")");
              associationsTreeNode.add(node);
              
              // thanks to Yong Zhang for the tip for refreshing the tree structure.
              ((DefaultTreeModel )tree.getModel()).nodeStructureChanged((TreeNode)dmtn);
              tree.repaint();
          }
       }
    }
  
   

  protected void processWindowEvent(WindowEvent e) {
   if (e.getID() == WindowEvent.WINDOW_CLOSING) {
     exit();
   }
   super.processWindowEvent(e);
 }

  void exitAction(ActionEvent e) {
    exit();
  }
  
   void searchAction(ActionEvent e) {
    AdvancedSearchDialog search = new AdvancedSearchDialog(null,"Advanced Search");
   }

  public void exit(){
    closeProject();
    System.exit(0);
  }

  void showHelpDialog(ActionEvent e) {
    AboutDialog dlg = new AboutDialog(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
  }

  public void documentPane_mouseClicked(MouseEvent e) {

    if((e.getButton() == e.BUTTON1) || (e.getButton() == e.BUTTON2)){
      documentPane.getHighlighter().removeAllHighlights();
    }

    if (e.getButton() == e.BUTTON3) {
      try {

        if (documentPane.getDocument().getLength() > 1) {
          documentPopupMenu_CodeMenu.setEnabled(true);
        }

        if(maindoc != null){
          if(!maindoc.getCodes().isEmpty()){
            codeMenu_View.setEnabled(true);
          }else{
            codeMenu_View.setEnabled(false);
          }
        }

        if (documentPane.getSelectedText() != null) {

          codeMenu_Code.setEnabled(true);
          codeMenu_Document.setEnabled(false);
          codeMenu_Invivo.setEnabled(true);

          if (maindoc.getOntModel() != null) {
            if (!maindoc.getOntModel().getConcepts().isEmpty()) {
              codeMenu_Concepts.setEnabled(true);
              codeMenu_DocumentStructured.setEnabled(true);
            }
          }

          if ( (codingFreeCodeNode.getChildCount() != 0) ||
              (codingStructuredCodeNode.getChildCount() != 0)) {
            codeMenu_Existing.setEnabled(true);
          }

        }
        else {
          disableCodeMenuConcepts();
          codeMenu_Document.setEnabled(true);
        }

        Point point = e.getPoint();
        documentPopupMenu.show(documentPane, point.x, point.y);

      } catch (Exception ex) {

      }
    }
  }

  public void openLocalDocumentMain(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    FileChooserFilter fcf = new FileChooserFilter("rtf", "RTF Files");
    jfc.setFileFilter(fcf);
    jfc.setDialogTitle("Open Document");
    int returnVal = jfc.showOpenDialog(contentPane);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();
      openLocalDocument(file);
    }
  }

  public void openLocalDocument(File file) {
      boolean docexists = checkDocExists(file.getName());

      if(docexists){
        JOptionPane.showMessageDialog(this, "Document \""+file.getName()+
                                      "\" has already been loaded.",
                                      "Open Document Message", JOptionPane.INFORMATION_MESSAGE);
      }else{

        try {
          Document doc = new DefaultStyledDocument(new StyleContext());
          BufferedReader bf = new BufferedReader(new FileReader(file.getAbsoluteFile()));
          rtf.read(bf, doc, 0);

          if(doc.getLength() <= 1){
            JOptionPane.showMessageDialog(this, "Cannot load empty document.",
                              "Open Document Message", JOptionPane.INFORMATION_MESSAGE);
            return;
          }

          documentPane.setDocument(doc);
          AnnotDocument annotDocument = new AnnotDocument();
          annotDocument.setTitle(file.getName());
          annotDocument.setLocation(file.getAbsolutePath());
          annotDocument.setFormat(file.getName().substring(file.getName().
              lastIndexOf(".") + 1, file.getName().length()));
          annotDocument.setLength(doc.getLength());
          annotDocument.setDoc(doc);
          maindoc = new MainDocumentDAO();
          maindoc.setAnnotDocument(annotDocument);
          documents.add(maindoc);

          if(project == null){
            project = new ProjectDAO();
          }

          ArrayList projectassoclist = project.getProjectAssoc();

          if(projectassoclist.isEmpty()){
            ProjectAssociation pa = new ProjectAssociation();
            pa.setDocumentName(file.getName());
            pa.setDocumentLocation(file.getAbsolutePath());
            project.setProjectAssoc(pa);
          }

          Iterator iter = projectassoclist.iterator();
          boolean exists = false;

          while (iter.hasNext()) {
              ProjectAssociation temp = (ProjectAssociation) iter.next();
              if ((temp.getDocumentName().equals(file.getName())) &&
                  (temp.getDocumentLocation().equals(file.getAbsolutePath()))) {
                exists = true;
                break;
              }
          }

          if(!exists){
                 ProjectAssociation pa = new ProjectAssociation();
                 pa.setDocumentName(file.getName());
                 pa.setDocumentLocation(file.getAbsolutePath());
                 project.setProjectAssoc(pa);
          }

          fileMenu_CloseDocument.setEnabled(true);
          closeMenu_Document.setEnabled(true);
          statusBar.setText("Opened Document \"" + file.getAbsolutePath()+"\".");
          DefaultMutableTreeNode child = new DefaultMutableTreeNode(
              file.getPath());
          documentNode.add(child);
          directorydtm.reload(documentNode);
          directoryTree.scrollPathToVisible(new TreePath(child.getPath()));
          refreshDocumentContents(maindoc.getAnnotDocument().getLocation());
          ontologyMenu_OpenOntology.setEnabled(true);
          ontologyMenu_Close.setEnabled(false);
          savedproject = false;
          fileMenu_SaveProject.setEnabled(true);
          fileMenu_CloseProject.setEnabled(true);
          saveProjectButton.setEnabled(true);
          closeButton.setEnabled(true);
        } catch (FileNotFoundException fnfe) {
          JOptionPane.showMessageDialog(this,
                                        "The system has been unable to find the specified file.",
                                        "Open Document Message",
                                        JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(this,
                                        "Unknown Exception has occurred. " +
                                        "Try to open a file at a later time.",
                                        "Open Document Message",
                                        JOptionPane.ERROR_MESSAGE);
        }
     }
  }

  public boolean checkDocExists(String filename){
    boolean exists = false;

    if(!documents.isEmpty()){
      Iterator iter = documents.iterator();

      while(iter.hasNext()){
        MainDocumentDAO md = (MainDocumentDAO)iter.next();
        if(md.getAnnotDocument().getTitle().equals(filename)){
          exists = true;
          break;
        }
      }
    }
    return exists;
  }

  public void conceptMenuItem_Code(ActionEvent e) {
    createCode(e.getActionCommand(), "structured", false);
    setExistCodeList();
  }

  public void existingMenuItem_Code(ActionEvent e) {
    Iterator iter = maindoc.getCodes().iterator();
    String codeType = new String("normal");

    while (iter.hasNext()) {
      Code code = (Code) iter.next();
      if (code.getName().equals(e.getActionCommand())) {
        codeType = code.getCodeType();
        break;
      }
    }

    createCode(e.getActionCommand(), codeType, true);
    setExistCodeList();
  }

  public void disableCodeMenuConcepts() {
    codeMenu_Code.setEnabled(false);
    codeMenu_Document.setEnabled(false);
    codeMenu_DocumentStructured.setEnabled(false);
    codeMenu_Concepts.setEnabled(false);
    codeMenu_Invivo.setEnabled(false);
    codeMenu_Existing.setEnabled(false);
  }

  public void closeDocumentMain(ActionEvent e) {
    closeDocument(maindoc.getAnnotDocument().getLocation());
  }

  public void closeDocument(String filelocation){
    try {
      if(!savedproject){
        saveProject();
        closeProject();
      }else{
        closeOntology();
        documentPane.getDocument().remove(0,
                                          documentPane.getDocument().getLength());
        deleteCodes();
        MutableTreeNode mtn = null;

        for (Enumeration _enum = documentNode.children(); _enum.hasMoreElements(); ) {
          mtn = (MutableTreeNode) _enum.nextElement();
          if (mtn.toString().equals(filelocation)) {
            break;
          }
        }

        directoryTree.scrollPathToVisible(new TreePath(documentNode));
        documentNode.remove(mtn);
        directorydtm.reload(documentNode);
        directoryTree.clearSelection();

        if (documents.size() == 1) {
          documents.clear();
          maindoc = new MainDocumentDAO();
        }
        else {

          Iterator iter = documents.iterator();

          while (iter.hasNext()) {
            MainDocumentDAO md = (MainDocumentDAO) iter.next();

            if (maindoc.getAnnotDocument().getTitle().equals(md.
                getAnnotDocument().getTitle())) {
              documents.remove(md);
              maindoc = new MainDocumentDAO();
              break;
            }
          }
        }

        infoPane.setText("");
        if (documents.isEmpty()) {
          fileMenu_CloseDocument.setEnabled(false);
          ontologyMenu_OpenOntology.setEnabled(false);
        }
        savedproject = false;
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Have not been able to close the"+
                                    "document. Please try again at a later time.",
                                    "Close Document Message", JOptionPane.ERROR_MESSAGE);
    }

  }

  void codesTree_valueChanged(TreeSelectionEvent evt){
    if (evt.isAddedPath() == true) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode)
          codesTree.getLastSelectedPathComponent();
      Iterator iter = maindoc.getCodes().iterator();

      while (iter.hasNext()) {
        infoPane.setText("");
        Code code = (Code) iter.next();

        Iterator coloriter = maindoc.getCodesColours().iterator();

        while(coloriter.hasNext()){
          ArrayList codeinfo = (ArrayList)coloriter.next();
          if(((String)codeinfo.get(0)).equals(code.getName())){
            Integer a = (Integer)codeinfo.get(1);
            Integer b = (Integer)codeinfo.get(2);
            Integer c = (Integer)codeinfo.get(3);
            highlight = new DefaultHighlighter.DefaultHighlightPainter(new Color(
           a.intValue(), b.intValue(), c.intValue()));
          }
        }

        if ((node.toString().equals(code.getName())) &&
            (!node.getParent().toString().equals(code.getName()))) {
          documentPane.getHighlighter().removeAllHighlights();
          ArrayList sectionList = code.getSectionList();

          if(!sectionList.isEmpty()){

            Iterator it = sectionList.iterator();

            while (it.hasNext()) {
              Section section = (Section) it.next();

              try {
                documentPane.getHighlighter().
                    addHighlight(section.getSectionStart(),
                                 section.getSectionEnd(), highlight);
              } catch (BadLocationException ex) {

              }
            }
          }
          setInfoPaneText(code.getName(), code.getCodeType(), code.getDocumentTitle(),
                          code.getDocLocation(), code.getAuthor(), code.getLanguage(),
                          code.getShared(),
                          ((Section)code.getSectionList().get(0)).getSectionStart(),
                          ((Section)code.getSectionList().get(0)).getSectionEnd(),
                          ((Section)code.getSectionList().get(0)).getBodyText(),
                          code.getOntologyReference(), code.getOntologyConceptRef());
          break;
        }else if(node.getParent().toString().equals(code.getName())){
          ArrayList sectionList = code.getSectionList();
          documentPane.getHighlighter().removeAllHighlights();
          String instance = node.toString();
          String temp = instance.substring(instance.length()-1, instance.length());
          int index = Integer.parseInt(temp);
          Section section = (Section)sectionList.get(index-1);

          try {
              documentPane.getHighlighter().
                  addHighlight(section.getSectionStart(),
                               section.getSectionEnd(), highlight);
          } catch (BadLocationException ex) {

          }
          setInfoPaneText(code.getName(), code.getCodeType(), code.getDocumentTitle(),
                           code.getDocLocation(), code.getAuthor(), code.getLanguage(),
                           code.getShared(), section.getSectionStart(),
                           section.getSectionEnd(), section.getBodyText(),
                           code.getOntologyReference(), code.getOntologyConceptRef());
          break;
        }else if(node.getParent().toString().equals("Associations")){
            
            String nn = node.toString();
            
            String cl = nn.substring(nn.lastIndexOf('(')+1,nn.lastIndexOf(')'));
            
            System.out.println("NODE:"+cl);
            
            
		            documentPane.getHighlighter().removeAllHighlights();
		            ArrayList sectionList = code.getSectionList();
		
		            if(!sectionList.isEmpty()){
		
		              Iterator it = sectionList.iterator();
		
		              while (it.hasNext()) {
		                Section section = (Section) it.next();
		
		                try {
		                  documentPane.getHighlighter().
		                      addHighlight(section.getSectionStart(),
		                                   section.getSectionEnd(), highlight);
		                } catch (BadLocationException ex) {
		
		                }
		              }
		            }
		            setInfoPaneText(cl, code.getCodeType(), code.getDocumentTitle(),
		                            code.getDocLocation(), code.getAuthor(), code.getLanguage(),
		                            code.getShared(),
		                            ((Section)code.getSectionList().get(0)).getSectionStart(),
		                            ((Section)code.getSectionList().get(0)).getSectionEnd(),
		                            ((Section)code.getSectionList().get(0)).getBodyText(),
		                            code.getOntologyReference(), code.getOntologyConceptRef());
		            break;
           
          }
      }
    }
  }

  public void setInfoPaneText(String codeName, String codeType, String docTitle,
                              String docLocation, String author, String language,
                              boolean shared, int sectionStart, int sectionEnd,
                              String sectionText, String ontRef, String conceptRef){
    infoPane.setText("Code Details: \n\nCode Name: " + codeName + "\n\n Code Type: " +
                        codeType + "\n\n Document Title: "+ docTitle +
                        "\n\n Document Location: "+ docLocation +
                        "\n\n Author: "+ author +
                        "\n\n Language: "+ language +
                        "\n\n Shared: "+ shared +
                        "\n\n Section Start: "+sectionStart +
                        "\n\n Section End: "+sectionEnd +
                        "\n\n Section Text: "+sectionText +
                        "\n\n Ontology Reference: "+ ontRef +
                        "\n\n Concept Reference: "+conceptRef);
                        
                        //TEMPORARY
    
   //System.out.println(maindoc.getOntModel().getOntName());
    ontologyInfo.refreshPanel(maindoc.getOntModel().getOntName(),codeName);
    relatedResources.refreshPanel(codeName);
    
                        
  }

  void openLocalOntologyMain(ActionEvent e) {
    JFileChooser jfc = new JFileChooser();
    FileChooserFilter fcf = new FileChooserFilter("owl", "OWL Files");
    jfc.setFileFilter(fcf);
    jfc.setDialogTitle("Open Structured Codes File");
    int returnVal = jfc.showOpenDialog(contentPane);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();
      openLocalOntology(file);
    }
 }
 
//-----------------------------------------------------------------------------------------
//Open ontology from local file
//-----------------------------------------------------------------------------------------
  public void openLocalOntology(File file) {
      boolean ontexists = checkOntModelExists(file.getName());

      if(ontexists){
        JOptionPane.showMessageDialog(this, "Ontology \""+file.getName()+
                                      "\" has already been loaded.",
                                      "Open Ontology Message", JOptionPane.INFORMATION_MESSAGE);
      }else{

        try {
          OntologyModelDAO ontmodel = new OntologyModelDAO(file.getName(),
              file.getAbsolutePath());
          
          // Generate OwlDoc based on chosen ontology
          try 
          {						
                        System.out.println("FILE:" + file);
						FileInputStream ontFile = new FileInputStream(file);
						OWLModel model = ProtegeOWL.createJenaOWLModelFromInputStream(ontFile);
						File owlDocFile = new File("owldocs/" + file.getName());
						owlDocFile.mkdir();
						System.out.println("MODEL: "+ model);
						System.out.println("OWLDOCFILE: "+ owlDocFile);
						OWLModelDocGenerator gen = new OWLModelDocGenerator(owlDocFile, model);
						System.out.println("GEN: "+ gen);
						gen.generateOWLDoc();
										    					
					}
					catch(Exception e) {
					    System.out.println("MAIN GUI: "+e);
						e.printStackTrace();
					}
          
          
          ontmodel.readOntology();

          if(ontmodel.getModel().isEmpty()){
            JOptionPane.showMessageDialog(this, "Cannot load empty ontology.",
                              "Open Ontology Message", JOptionPane.INFORMATION_MESSAGE);
            return;
          }

          maindoc.setOntModel(ontmodel);
          
//        Load IndexClasses
			ontologyInfo.refreshPanel(maindoc.getOntModel().getOntName(),"IndexClasses");

          ArrayList projectassoc = project.getProjectAssoc();
          Iterator iter = projectassoc.iterator();

          while (iter.hasNext()) {
            ProjectAssociation temp = (ProjectAssociation) iter.next();
            if ( (temp.getDocumentName().equals(maindoc.getAnnotDocument().getTitle())) &&
                 (temp.getOntologyName().equals(""))) {
             ProjectAssociation pa = new ProjectAssociation();
             pa.setOntologyName(file.getName());
             pa.setOntologyLocation(file.getAbsolutePath());
             pa.setDocumentName(temp.getDocumentName());
             pa.setDocumentLocation(temp.getDocumentLocation());
             pa.setCodings(temp.getCodings());

             projectassoc.add(pa);
             projectassoc.remove(temp);
             project.setProjectAssoc(projectassoc);
             break;
            }
          }

          statusBar.setText("Opened Ontology \"" + file.getAbsolutePath()+"\".");
          DefaultMutableTreeNode child = new DefaultMutableTreeNode(
              file.getPath());
          ontologyNode.add(child);
          directorydtm.reload(ontologyNode);
          directoryTree.scrollPathToVisible(new TreePath(child.getPath()));
          ontologyMenu_OpenOntology.setEnabled(false);
          refreshMainDocument();
          setConceptList();
          savedproject = false;
        } catch (FileNotFoundException fnfe) {
          JOptionPane.showMessageDialog(this,
                                        "The system has been unable to find the specified file.",
                                        "Open Ontology Message.",
                                        JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(this,
                                        "Unknown Exception has occurred. " +
                                        "Try to open an ontology at a later time.",
                                        "Open Ontology Message",
                                        JOptionPane.ERROR_MESSAGE);
        }
     }
  }

  public boolean checkOntModelExists(String ontname){
    boolean exists = false;
    if(maindoc.getOntModel() != null){
      if(maindoc.getOntModel().getOntName().equals(ontname)){
        exists = true;
      }
    }

    return exists;
  }

  public void setConceptList() {
    try {
      int items = codeMenu_Concepts.getMenuComponentCount();
      items = items - 1;

      while (items > 1) {
        codeMenu_Concepts.remove(items);
        items = items - 1;
      }

      conceptGoUp = 2;
      conceptGoDown = 12;

      OntologyModelDAO ontmodel = maindoc.getOntModel();

      if(ontmodel != null){

        ArrayList conceptList = ontmodel.getConcepts();

        if (!conceptList.isEmpty()) {
          Iterator iter = conceptList.iterator();
          double menuHeight = 0.0;
          int count = 1;

          while (iter.hasNext()) {
            String temp = (String) iter.next();
            JMenuItem menuitem = new JMenuItem(temp);

            menuitem.setFont(new java.awt.Font("Times New Roman", 0, 11));
            menuitem.setForeground(Color.BLUE);
            menuitem.addActionListener(new
                                       GUIFrame_conceptMenuItem_actionAdapter(this));
            menuHeight += menuitem.getPreferredSize().getHeight();

            if (count > 10) {
              menuitem.setVisible(false);
            }

            codeMenu_Concepts.add(menuitem);
            count++;
          }

          codeMenu_Concepts.addSeparator();
          codeMenu_Concepts.add(codeMenu_Concepts_Godown);
          ontologyMenu_Close.setEnabled(true);

        }
      }else {
          codeMenu_Concepts.setEnabled(false);
          codeMenu_DocumentStructured.setEnabled(false);
      }
    } catch (Exception ex) {

    }

  }

  void closeOntologyMain(ActionEvent e){
    closeOntology();
  }

  public void closeOntology() {
    try {
      if (maindoc.getOntModel() != null) {
        int items = codeMenu_Concepts.getMenuComponentCount();
        items = items - 1;

        while (items > 1) {
          codeMenu_Concepts.remove(items);
          items = items - 1;
        }

        conceptGoUp = 2;
        conceptGoDown = 12;

        codeMenu_Concepts.setEnabled(false);

        MutableTreeNode mtn = null;

        for (Enumeration _enum = ontologyNode.children(); _enum.hasMoreElements(); ) {
          mtn = (MutableTreeNode) _enum.nextElement();
          if (mtn.toString().equals(maindoc.getOntModel().getLocation())) {
            break;
          }
        }

        directoryTree.scrollPathToVisible(new TreePath(ontologyNode));
        Iterator iter = documents.iterator();

        while (iter.hasNext()) {
          MainDocumentDAO md = (MainDocumentDAO) iter.next();
          OntologyModelDAO ontmodel = maindoc.getOntModel();

          if(ontmodel != null){
            if (ontmodel.getLocation().equals(mtn.toString())) {
              if(md.getOntModel() != null){
                md.getOntModel().clearModel();
                md.setOntModel(null);
                refreshMainDocument();
                break;
              }
            }
          }
        }

        ontologyNode.remove(mtn);
        directorydtm.reload(ontologyNode);
        directoryTree.clearSelection();

        if (ontologyNode.getChildCount() == 0) {
          ontologyMenu_Close.setEnabled(false);
        }
      }
      savedproject = false;
    } catch (Exception ex) {
      System.out.println(ex);
      JOptionPane.showMessageDialog(this, "Have not been able to close the "+
                                   "structured codes file. Please try again at a later time.",
                                   "Close Structured Codes File Message",
                                   JOptionPane.ERROR_MESSAGE);

    }
  }

  void codeMenu_Concepts_Goup_mouseEntered(MouseEvent e) {
    if (conceptGoUp != 2 || conceptGoDown != 12) {
      codeMenu_Concepts.getItem(conceptGoUp - 1).setVisible(true);
      codeMenu_Concepts.getItem(conceptGoDown - 1).setVisible(false);
      conceptGoUp = conceptGoUp - 1;
      conceptGoDown = conceptGoDown - 1;
    }
  }

  void codeMenu_Concepts_Godown_mouseEntered(MouseEvent e) {
    int itemcount = codeMenu_Concepts.getItemCount();

    if (conceptGoDown != itemcount - 2) {
      codeMenu_Concepts.getItem(conceptGoUp).setVisible(false);
      codeMenu_Concepts.getItem(conceptGoDown).setVisible(true);
      conceptGoUp = conceptGoUp + 1;
      conceptGoDown = conceptGoDown + 1;
    }
  }

  void codeInvivoAction(ActionEvent e) {
    codeInvivo();
  }

  public void codeInvivo(){
    String temp = documentPane.getSelectedText();
    if(temp.length() > 10){
      createCode(temp.substring(0, 10), "invivo", false);
    }else{
      createCode(temp.substring(0), "invivo", false);
    }
    setExistCodeList();
  }

  public void setExistCodeList() {
    try {
      int items = codeMenu_Existing.getMenuComponentCount();
      items = items - 1;

      while (items > 1) {
        codeMenu_Existing.remove(items);
        items = items - 1;
      }

      existGoUp = 2;
      existGoDown = 12;

      Iterator iter = maindoc.getCodes().iterator();
      double menuHeight = 0.0;
      int count = 1;

      while (iter.hasNext()) {
        Code temp = (Code) iter.next();

        if (!temp.getCodeType().equals("sourcedoc")) {

          JMenuItem menuitem = new JMenuItem(temp.getName());

          menuitem.setFont(new java.awt.Font("Times New Roman", 0, 11));
          menuitem.setForeground(Color.BLUE);
          menuitem.addActionListener(new
                                     GUIFrame_existingMenuItem_actionAdapter(this));

          menuHeight += menuitem.getPreferredSize().getHeight();

          if (count > 10) {
            menuitem.setVisible(false);
          }

          codeMenu_Existing.add(menuitem);
          count++;
        }
      }

      if (!maindoc.getCodes().isEmpty()) {
        codeMenu_Existing.addSeparator();
        codeMenu_Existing.add(codeMenu_Existing_Godown);
      }
      else {
        codeMenu_Existing.setEnabled(false);
      }

    } catch (Exception ex) {

    }
  }

  void documentPane_focusGained(FocusEvent e) {
    documentPane.setSelectionColor(Color.orange);
  }

  void codeMenu_Existing_Goup_mouseEntered(MouseEvent e) {
    int itemcount = codeMenu_Existing.getItemCount();

    if (itemcount > 10) {
      if (existGoUp != 2 || existGoDown != 12) {
        codeMenu_Existing.getItem(existGoUp - 1).setVisible(true);
        codeMenu_Existing.getItem(existGoDown - 1).setVisible(false);
        existGoUp = existGoUp - 1;
        existGoDown = existGoDown - 1;
      }
    }
  }

  void codeMenu_Existing_Godown_mouseEntered(MouseEvent e) {
    int itemcount = codeMenu_Existing.getItemCount();

    if (itemcount > 14) {
      if (existGoDown != itemcount - 2) {
        codeMenu_Existing.getItem(existGoUp).setVisible(false);
        codeMenu_Existing.getItem(existGoDown).setVisible(true);
        existGoUp = existGoUp + 1;
        existGoDown = existGoDown + 1;
      }
    }

  }

  void deleteCodesAction(ActionEvent e) {
    deleteCodes();
  }

  public void deleteCodes(){
    documentPopupMenu_CodeMenu.setEnabled(false);
    codingFreeCodeNode.removeAllChildren();
    codingStructuredCodeNode.removeAllChildren();
    codingOtherCodeNode.removeAllChildren();
    codingAnnotationNode.removeAllChildren();
    codingMemoNode.removeAllChildren();
    codeMenu_Existing.setEnabled(false);
    maindoc.setCodes(new ArrayList());
    maindoc.setCodesColours(new ArrayList());
    codesdtm.reload(codingRootNode);
    disableCodesMenu();
    savedproject = false;
  }

  void newMainCode(ActionEvent e) {
    String codeName = null;

    codeName = JOptionPane.showInputDialog(this, "Enter a code name:",
                                           "Input Code Name Message",
                                           JOptionPane.QUESTION_MESSAGE);
    if (codeName != null) {
      if (!codeName.equals("")) {
        createCode(codeName.trim(), "normal", false);
        setExistCodeList();
      }
      else {
        JOptionPane.showMessageDialog(this, "You cannot enter an empty value.",
                                      "Input Code Name Message",
                                      JOptionPane.ERROR_MESSAGE);
        newMainCode(e);
      }
    }
  }

  public void createCode(String codeName, String codeType, boolean existing) {
      boolean codenamexists = checkCodeNameExists(codeName);

      if((!existing) && (!codenamexists)){
        DefaultMutableTreeNode child;
        Code code = new Code();
        child = new DefaultMutableTreeNode(codeName);
        code.setName(codeName);

        codesTree.repaint();
        Section section = new Section();
        section.setSectionStart(documentPane.getSelectionStart());
        section.setSectionEnd(documentPane.getSelectionEnd());
        section.setBodyText(documentPane.getSelectedText());
        code.setSectionList(section);
        code.setCreationDate(new String(new java.util.Date().toString()));
        code.setShared(true);
        code.setCodeType(codeType);
        code.setDocumentTitle(maindoc.getAnnotDocument().getTitle());
        code.setDocLocation(maindoc.getAnnotDocument().getLocation());
        maindoc.addCode(code);
        DefaultMutableTreeNode instance = new DefaultMutableTreeNode(
                   code.getName()+"_1");

        if (codeType.equals("structured")) {
          code.setOntologyReference(maindoc.getOntModel().getOntURI());

          if(maindoc.getOntModel().getConceptURI(codeName).equals("")){
            code.setOntologyConceptRef("http://somewhere/nouri#"+codeName);
          }else{
            code.setOntologyConceptRef(maindoc.getOntModel().getConceptURI(codeName));
          }
          child.add(instance);
          codingStructuredCodeNode.add(child);
          codesdtm.reload(codingStructuredCodeNode);
        }else {
          child.add(instance);
          codingFreeCodeNode.add(child);
          codesdtm.reload(codingFreeCodeNode);
        }

        savedcodes = false;
        savedproject = false;
        codesTree.scrollPathToVisible(new TreePath(child.getPath()));
        statusBar.setText("Successfully added code \""+code.getName()+"\".");
        Random random = new Random();
        ArrayList codeinfo = new ArrayList();
        codeinfo.add(code.getName());
        codeinfo.add(new Integer(random.nextInt(255)));
        codeinfo.add(new Integer(random.nextInt(255)));
        codeinfo.add(new Integer(random.nextInt(255)));
        maindoc.setCodesColour(codeinfo);
      }else if(existing){
        ArrayList codes = maindoc.getCodes();
        Iterator iter = codes.iterator();

         while(iter.hasNext()){
           Code code = (Code)iter.next();

             if (code.getName().equals(codeName)) {

               ArrayList sectionList = code.getSectionList();

               if(!sectionList.isEmpty()){

                 Iterator it = sectionList.iterator();

                 while (it.hasNext()) {
                   Section section = (Section) it.next();

                   if (!section.getBodyText().equals(documentPane.getSelectedText())) {
                     Section sec = new Section();
                     sec.setSectionStart(documentPane.getSelectionStart());
                     sec.setSectionEnd(documentPane.getSelectionEnd());
                     sec.setBodyText(documentPane.getSelectedText());
                     code.setSectionList(sec);
                     int count = code.getSectionList().size();
                     DefaultMutableTreeNode instance = new DefaultMutableTreeNode(
                            code.getName()+"_"+count);

                     if(code.getCodeType().equals("normal") || code.getCodeType()
                        .equals("invivo")){
                       addCodeInstance(codingFreeCodeNode, code.getName(), instance);
                     }else if(code.getCodeType().equals("structured")){
                       addCodeInstance(codingStructuredCodeNode, code.getName(), instance);
                     }

                     codesdtm.reload(codingCodesNode);
                     codesTree.scrollPathToVisible(new TreePath(instance.getPath()));
                     statusBar.setText("Used existing code \"" + code.getName()+"\".");
                     savedcodes = false;
                     savedproject = false;
                     break;
                   }else{
                     JOptionPane.showMessageDialog(this,
                        "You have already used an existing code on this piece of text.",
                            "Coding Message",
                           JOptionPane.INFORMATION_MESSAGE);
                   }
                 }
               }
             }
           }
      }else{
        JOptionPane.showMessageDialog(this,
       "This code already exists. Please, define a new one or use an existing one.",
                                "Coding Message",
                               JOptionPane.INFORMATION_MESSAGE);
      }

      refreshMainDocument();
      disableCodeMenuConcepts();
      enableCodesMenu();
  }

  public void addCodeInstance(DefaultMutableTreeNode treeNode, String codeName,
                              DefaultMutableTreeNode instance){
    for (Enumeration e = treeNode.children();
         e.hasMoreElements(); ) {
      DefaultMutableTreeNode tn = (DefaultMutableTreeNode) e.
          nextElement();
      if (tn.toString().equals(codeName)) {
        tn.add(instance);
      }
    }
  }

  public void refreshMainDocument() {
    Iterator iter = documents.iterator();

    while (iter.hasNext()) {
      MainDocumentDAO md = (MainDocumentDAO) iter.next();

      if (maindoc.getAnnotDocument().getTitle().equals(md.
          getAnnotDocument().getTitle())) {
        documents.remove(md);
        documents.add(maindoc);
        break;
      }

    }
  }

  void codeMenu_Concepts_Goup_mouseMoved(MouseEvent e) {
    codeMenu_Concepts_Goup_mouseEntered(e);
  }

  /**
   * codeMenu_Concepts_Godown_mouseMoved
   *
   * @param e MouseEvent
   */
  void codeMenu_Concepts_Godown_mouseMoved(MouseEvent e) {
    codeMenu_Concepts_Godown_mouseEntered(e);
  }

  public boolean checkCodeNameExists(String codeName) {
    boolean exists = false;

    Iterator iter = maindoc.getCodes().iterator();

    while (iter.hasNext()) {
      Code code = (Code) iter.next();
      if (code.getName().equalsIgnoreCase(codeName)) {
        exists = true;
        break;
      }
    }

    return exists;
  }

  void codeMenu_Existing_Goup_mouseMoved(MouseEvent e) {
    codeMenu_Existing_Goup_mouseEntered(e);
  }

  void codeMenu_Existing_Godown_mouseMoved(MouseEvent e) {
    codeMenu_Existing_Godown_mouseEntered(e);
  }

  void viewsMenu_HideCoding_actionPerformed(ActionEvent e) {
    leftPanelSplitPane.setDividerLocation(1.0);
  }

  void viewsMenu_HideDirectory_actionPerformed(ActionEvent e) {
    leftPanelSplitPane.setDividerLocation(0.0);
  }

  void viewsMenu_Show_actionPerformed(ActionEvent e) {
    leftPanelSplitPane.setDividerLocation(0.5);
  }

  void codesMenu_Clear_actionPerformed(ActionEvent e) {
    deleteCodes();
  }

  public void disableCodesMenu() {
    codesMenu_Save.setEnabled(false);
    codesMenu_Delete.setEnabled(false);
    codesMenu_Similarities.setEnabled(false);
    codesMenu_Search.setEnabled(false);
    codesMenu_Information.setEnabled(false);
    codesMenu_SortMenu.setEnabled(false);
    codesMenu_View.setEnabled(false);
    sortMenu_Name.setEnabled(false);
    sortMenu_Popularity.setEnabled(false);
  }

  public void enableCodesMenu() {
    codesMenu_Save.setEnabled(true);
    codesMenu_Delete.setEnabled(true);
    codesMenu_Search.setEnabled(true);
    codesMenu_Information.setEnabled(true);
    codesMenu_View.setEnabled(true);
  }

  public void enableOtherMenus(){
    otherMenu_DeleteMenu.setEnabled(true);
    otherMenu_ExportMenu.setEnabled(true);

    otherDeleteMenu_Annotations.setEnabled(true);
    otherDeleteMenu_Memos.setEnabled(true);
    otherExportMenu_Annotations.setEnabled(true);
    otherExportMenu_Memos.setEnabled(true);
  }

  public void disableOtherMenus(){
    otherMenu_DeleteMenu.setEnabled(false);
    otherMenu_ExportMenu.setEnabled(false);

    otherDeleteMenu_Annotations.setEnabled(false);
    otherDeleteMenu_Memos.setEnabled(false);
    otherExportMenu_Annotations.setEnabled(false);
    otherExportMenu_Memos.setEnabled(false);
  }

  void codeSourceDocumentAction(ActionEvent e) {
    codeSourceDocument();
  }
  
  //Structured document action
  void codeSourceDocumentStructuredAction(ActionEvent e) {
   // codeSourceDocument();
   
    System.out.println(""+maindoc.getOntModel().getLocation());
    FileInputStream in = null;
	    
	    try{
	     in = new FileInputStream(maindoc.getOntModel().getLocation());
	    } catch (Exception ex) {System.out.println(ex);}
	    
	    OWLModel owlModel = null;
	    
	    try {
        //owlModel = ProtegeOWL.createJenaOWLModelFromURI(uri);
        owlModel = ProtegeOWL.createJenaOWLModelFromInputStream(in);
        } catch (Exception ex) {System.out.println(ex);}
        
        String ontClass = OWLUI.pickConcreteClass(owlModel,"Select Structured Code").getLocalName();
        if (ontClass != null) {
         createCode(ontClass, "structured", false);
        }
        setExistCodeList();
                       
  }
  

  public void codeSourceDocument(){
    DocumentDialog dd = new DocumentDialog(this, "Code Source Document", true);
    Dimension dlgSize = dd.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dd.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                   (frmSize.height - dlgSize.height) / 2 + loc.y);
    dd.setVisible(true);
    Code code = null;

    code = dd.getDocumentCode();

    if (code != null) {
      DefaultMutableTreeNode child = new DefaultMutableTreeNode(code.getName());
      code.setDocumentTitle(maindoc.getAnnotDocument().getTitle());
      code.setDocLocation(maindoc.getAnnotDocument().getLocation());
      code.setCodeType("sourcedoc");
      maindoc.addCode(code);
      codingOtherCodeNode.add(child);
      codesTree.repaint();
      codesdtm.reload(codingOtherCodeNode);
      codesTree.scrollPathToVisible(new TreePath(child.getPath()));
      savedcodes = false;
      savedproject = false;
      enableCodesMenu();
    }
  }

  void directoryTree_valueChanged(TreeSelectionEvent e) {
    TreePath path = e.getPath();
    TreePath parent = path.getParentPath();
    if(parent != null){
      if (parent.getLastPathComponent().toString().equals("Documents")) {
        String spath = path.getLastPathComponent().toString();
        refreshDocumentContents(spath);
      }else if(parent.getLastPathComponent().toString().equals("Structured Codes")){
        showOntologyInfo();
      }
    }
  }

  public void showOntologyInfo(){
    if(maindoc.getOntModel() != null){
      infoPane.setText("");
      infoPane.setText("Structured Codes Information\n\n Name: " +
                       maindoc.getOntModel().getOntName() + "\n\nLocation: " +
                       maindoc.getOntModel().getLocation() +
                       "\n\nNumber of concepts: " +
                       maindoc.getOntModel().getConceptCount());
    }
  }

  public void refreshDocumentContents(String path){
    Iterator iter = documents.iterator();

    while (iter.hasNext()) {
      MainDocumentDAO md = (MainDocumentDAO) iter.next();

      if (md.getAnnotDocument().getLocation().equals(path)) {

        maindoc = md;
        refreshCodes(true);
        break;
      }
    }
  }
  
  //--------------------------------------------------------------------------
  //INVOKED WHEN REFRESHING THE CODES
  //--------------------------------------------------------------------------
  public void refreshCodes(boolean refresh){
    if(refresh){
      codingFreeCodeNode.removeAllChildren();
      codingStructuredCodeNode.removeAllChildren();
      codingOtherCodeNode.removeAllChildren();
      codesdtm.reload(codingRootNode);
    }

    documentPane.getHighlighter().removeAllHighlights();
    ArrayList codes = maindoc.getCodes();
    Iterator it = codes.iterator();

    while(it.hasNext()){
      Code code = (Code)it.next();
      int count = code.getSectionList().size();
      DefaultMutableTreeNode node = new DefaultMutableTreeNode(code.getName());
      DefaultMutableTreeNode instance = null;

      for(int i = 1; i <= count; i++){
        instance = new DefaultMutableTreeNode(code.getName()+"_"+i);
        node.add(instance);
      }

      if(code.getCodeType().equals("normal") ||
             code.getCodeType().equals("invivo")){
          codingFreeCodeNode.add(node);
      }else if(code.getCodeType().equals("structured")){
          codingStructuredCodeNode.add(node);
      }else{
          codingOtherCodeNode.add(node);
      }


    }

    codesdtm.reload(codingRootNode);
    documentPane.setDocument(maindoc.getAnnotDocument().getDoc());
    infoPane.setText("");
    infoPane.setText("Document Details: "+ "\n\nTitle: "+maindoc.getAnnotDocument()
                     .getTitle() + "\n\nLocation: "+maindoc.getAnnotDocument()
                     .getLocation() + "\n\nFormat: "+maindoc.getAnnotDocument()
                     .getFormat() + "\n\nLength: "+maindoc.getAnnotDocument().
                     getLength()+" characters." );
    setConceptList();
    setExistCodeList();

    if(!codes.isEmpty()){
      enableCodesMenu();
    }else{
      disableCodesMenu();
    }
  }

  void directoryTree_mouseClicked(MouseEvent e) {
    if (e.getButton() == 3) {
        try {

          TreePath path = directoryTree.getSelectionPath();

          if (path != null) {

            String selection = path.getLastPathComponent().toString();

            if (path.getParentPath() != null) {
              if (path.getParentPath().getLastPathComponent().toString().equals(
                  "Documents")) {
                folderPopupMenu_ImportMenu.setEnabled(true);
                closeMenu_Document.setEnabled(true);
                folderPopupMenu_CloseMenu.setEnabled(true);
                if (maindoc.getOntModel() != null) {
                  importMenu_Ontology.setEnabled(false);
                  closeMenu_Ontology.setEnabled(true);
                }
                else {
                  importMenu_Ontology.setEnabled(true);
                  closeMenu_Ontology.setEnabled(false);
                }
                importMenu_Document.setEnabled(false);
              }else if (path.getParentPath().getLastPathComponent().toString().
                       equals(
                  "Structured Codes")) {
                folderPopupMenu_ImportMenu.setEnabled(false);
                closeMenu_Document.setEnabled(false);
                if (maindoc.getOntModel() != null) {
                  folderPopupMenu_CloseMenu.setEnabled(true);
                  importMenu_Ontology.setEnabled(false);
                  closeMenu_Ontology.setEnabled(true);
                }
                else {
                  importMenu_Ontology.setEnabled(true);
                  closeMenu_Ontology.setEnabled(false);
                }
              }
              Point point = e.getPoint();
              folderPopupMenu.show(directoryTree, point.x, point.y);
            }

            if (selection.equals("Documents") || selection.equals("Structured Codes")) {

              if (selection.equals("Documents")) {
                folderPopupMenu_ImportMenu.setEnabled(true);
                importMenu_Document.setEnabled(true);
                importMenu_Ontology.setEnabled(false);
                if (!documents.isEmpty()) {
                  folderPopupMenu_CloseMenu.setEnabled(true);
                  closeMenu_Ontology.setEnabled(false);
                }
                else {
                  folderPopupMenu_CloseMenu.setEnabled(false);
                }
              }

              if (selection.equals("Structured Codes")) {
                folderPopupMenu_ImportMenu.setEnabled(false);
                importMenu_Document.setEnabled(false);
                closeMenu_Document.setEnabled(false);

                if (documentNode.getChildCount() > 0) {
                  folderPopupMenu_ImportMenu.setEnabled(true);
                  importMenu_Ontology.setEnabled(true);
                }

                if (ontologyNode.getChildCount() > 0) {
                  folderPopupMenu_CloseMenu.setEnabled(true);
                  closeMenu_Ontology.setEnabled(true);
                  if(ontologyNode.getChildCount() == (documentNode.getChildCount()-1)){
                    importMenu_Ontology.setEnabled(true);
                  }else{
                    importMenu_Ontology.setEnabled(false);
                  }
                }
                else {
                  folderPopupMenu_CloseMenu.setEnabled(false);
                }
              }
              Point point = e.getPoint();
              folderPopupMenu.show(directoryTree, point.x, point.y);
            }
          }
        } catch (Exception ex) {

        }
     }
 }

  public void saveCodesAction(ActionEvent e) {
    saveLocalCodes();
  }

  public void saveLocalCodes(){
    OntologyMapperDAO mapper = new OntologyMapperDAO();
    mapper.setProjectName(project.getProjectFullName());
    boolean success = false;
    JFileChooser jfc = new JFileChooser();
    FileChooserFilter fcf = new FileChooserFilter("rdf", "RDF Files");
    jfc.setFileFilter(fcf);
    jfc.setDialogTitle("Save Local Codes");
    File file = null;

    try {
      if(project.getProjectName().equals("")){
        boolean created = createNewProject();
        if(created){
          saveProject();
        }else{
          return;
        }
      }

      int returnVal = jfc.showSaveDialog(contentPane);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
          file = jfc.getSelectedFile();
          if (file.exists()) {
            JOptionPane.showMessageDialog(this,
                                          "The filename you have selected already exists." +
                                          "Please select a different one.",
                                          "Save Codes Message",
                                          JOptionPane.INFORMATION_MESSAGE);
            saveLocalCodes();
          } else {
            success = mapper.writeLocalCodes(maindoc.getCodes(), file);
          }
      } else {
          return;
      }
    } catch (Exception ex) {
      success = false;
      JOptionPane.showMessageDialog(this,
                        "Unknown error, have not been able to save your codes.",
                            "Save Local Codes Message",
                           JOptionPane.ERROR_MESSAGE);
    }

    if(success){
      savedcodes = true;
      savedproject = false;
      ArrayList projects = project.getProjectAssoc();

      if(!projects.isEmpty()){
        Iterator piter = projects.iterator();

        while(piter.hasNext()){
          ProjectAssociation pa = (ProjectAssociation)piter.next();
          if(pa.getDocumentName().equals(maindoc.getAnnotDocument().getTitle()) &&
             (pa.getDocumentLocation().equals(maindoc.getAnnotDocument().getLocation()))){
            CodingFile cfile = new CodingFile();
            cfile.setCodingName(file.getName());
            cfile.setCodingLocation(file.getAbsolutePath());
            pa.setCodings(cfile);
          }
        }
      }

      JOptionPane.showMessageDialog(this,
                        "You have successfully saved your codes.",
                            "Save Local Codes Message",
                           JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public void saveRemoteCodes(){
    OntologyMapperDAO mapper = new OntologyMapperDAO();
    mapper.setProjectName(project.getProjectFullName());
    boolean success = false;

    try {
      if(project.getProjectName().equals("")){
        boolean created = createNewProject();
        if(created){
          saveProject();
          saveRemoteCodes();
        }else{
          return;
        }
      }

      success = mapper.writeRemoteCodes(maindoc.getCodes());

    } catch (Exception ex) {
      success = false;
      JOptionPane.showMessageDialog(this,
                        "Unknown error, have not been able to save your codes.",
                            "Save Remote Codes Message",
                           JOptionPane.ERROR_MESSAGE);
    }

    if(success){
      savedcodes = true;
      savedproject = false;
      ArrayList projects = project.getProjectAssoc();

      if(!projects.isEmpty()){
        Iterator piter = projects.iterator();

        while(piter.hasNext()){
          ProjectAssociation pa = (ProjectAssociation)piter.next();
          if(pa.getDocumentName().equals(maindoc.getAnnotDocument().getTitle()) &&
             (pa.getDocumentLocation().equals(maindoc.getAnnotDocument().getLocation()))){
            CodingFile cfile = new CodingFile();
            cfile.setCodingName("temp.rdf");
            cfile.setCodingLocation("remotecodings");
            pa.setCodings(cfile);
          }
        }
      }

      JOptionPane.showMessageDialog(this,
                        "You have successfully saved your codes.",
                            "Save Remote Codes Message",
                           JOptionPane.INFORMATION_MESSAGE);
    }
  }


  public void openProject(){
    JFileChooser jfc = new JFileChooser();
    FileChooserFilter fcf = new FileChooserFilter("xml", "XML Files");
    jfc.setFileFilter(fcf);
    jfc.setDialogTitle("Open Project");

     try{
       if (project != null) {

         int value = JOptionPane.showConfirmDialog(this,
             "Would you like to discard the existing project?",
             "Open Project Message",
             JOptionPane.YES_NO_OPTION);

         if (value == JOptionPane.YES_OPTION) {
           if (!savedcodes) {
             int codesval = JOptionPane.showConfirmDialog(this,
                 "Would you like to save your codes?",
                 "Save Codes Message",
                 JOptionPane.YES_NO_OPTION);

             if (codesval == JOptionPane.YES_OPTION) {
               saveLocalCodes();
               openProject();
             } else {
               savedcodes = true;
               openProject();
             }
           }

             if (!savedproject) {
               int val = JOptionPane.showConfirmDialog(this,
                   "Would you like to save the existing project first?",
                   "Save Project Message",
                   JOptionPane.YES_NO_OPTION);

               if (val == JOptionPane.YES_OPTION) {

                 if (project.getProjectName().equals("")) {
                   boolean created = createNewProject();

                   if(created){

                     boolean saved = saveProject();

                     if (saved) {
                       savedproject = true;
                       closeProject();
                       openProject();
                     }else {
                       return;
                     }
                   }
                 }else{
                   boolean saved = saveProject();

                   if (saved) {
                     savedproject = true;
                     closeProject();
                     openProject();
                   }else {

                   }
                 }
               }else {
                 savedproject = true;
                 closeProject();
                 openProject();
               }
             }else {
              closeProject();
              openProject();
            }
         }
       }else{
         int returnVal = jfc.showOpenDialog(contentPane);

         if (returnVal == JFileChooser.APPROVE_OPTION) {
           File file = jfc.getSelectedFile();

             project = new ProjectDAO();
             project.openProject(file);

             if (!project.getProjectName().equals("")) {

               ArrayList projects = project.getProjectAssoc();

               if (!projects.isEmpty()) {

                 Iterator projectiter = projects.iterator();

                 while (projectiter.hasNext()) {
                   ProjectAssociation temp = (ProjectAssociation) projectiter.
                       next();

                   String doclocation = temp.getDocumentLocation();
                   String ontlocation = temp.getOntologyLocation();
                   ArrayList codingFiles = temp.getCodings();

                   if (!doclocation.equals("")) {
                     openLocalDocument(new File(doclocation));
                   }

                   if (!ontlocation.equals("")) {
                     openLocalOntology(new File(ontlocation));
                   }

                   if (!codingFiles.isEmpty()) {
                     openCodingFiles(codingFiles);
                   }

                 }

                 fileMenu_SaveProject.setEnabled(true);
                 fileMenu_CloseProject.setEnabled(true);
                 closeButton.setEnabled(true);
                 savedproject = true;
                 this.setTitle("SQuAnTo - Project \"" + project.getProjectName() +
                               "\"");
                 statusBar.setText("Successfully opened project \"" +
                                   project.getProjectName() + "\".");
               }
             }
          }
       }
     }catch(Exception ex){
       JOptionPane.showMessageDialog(this, "Have not been able to open the"+
                                   "selected project. Please try again at a later time.",
                                   "Open Project Message", JOptionPane.ERROR_MESSAGE);

     }
  }

  public boolean codeExists(Code code){
    boolean exists = false;

    Iterator iter = maindoc.getCodes().iterator();

    while(iter.hasNext()){
      Code temp = (Code)iter.next();
      if(temp.getName().equals(code.getName())){
        exists = true;
      }
    }

    return exists;
  }

  public void openCodingFiles(ArrayList codingFiles){
       try {
         Iterator iter = codingFiles.iterator();
         OntologyMapperDAO mapper = new OntologyMapperDAO();

         while(iter.hasNext()){
           CodingFile cfile = (CodingFile)iter.next();
           mapper = new OntologyMapperDAO();
           ArrayList codesList = new ArrayList();
           mapper.setProjectName(project.getProjectFullName());

           if(cfile.getCodingName().equals("temp.rdf") &&
              cfile.getCodingLocation().equals("remotecodings")){
             codesList = mapper.getRemoteCodes();
           }else{
             codesList = mapper.getLocalCodes(cfile.getCodingLocation());
           }
           Iterator codeiter = codesList.iterator();

           while(codeiter.hasNext()){
             Code temp = (Code)codeiter.next();

             if (!codeExists(temp)) {
               maindoc.addCode(temp);
             }
           }

         }

         refreshCodes(false);

       } catch (FileNotFoundException fnfe) {
         JOptionPane.showMessageDialog(this,
                                       "The system has been unable to find the specified file.",
                                       "Open Codings Message",
                                       JOptionPane.ERROR_MESSAGE);
       } catch (Exception ex) {
         JOptionPane.showMessageDialog(this,
                                       "Unknown Exception has occurred. ",
                                       "Open Codings Message",
                                       JOptionPane.ERROR_MESSAGE);
    }
  }

  public boolean saveProject(){
    boolean success = false;
    JFileChooser jfc = new JFileChooser();
    FileChooserFilter fcf = new FileChooserFilter("xml", "XML Files");
    jfc.setFileFilter(fcf);
    jfc.setDialogTitle("Save Project");


    try {
      if (project.getProjectName().equals("")) {

        if (!savedcodes) {
          int codesval = JOptionPane.showConfirmDialog(this,
              "Would you like to save your codes first?",
              "Save Codes Message",
              JOptionPane.YES_NO_OPTION);

          if (codesval == JOptionPane.YES_OPTION) {
            saveLocalCodes();
            saveProject();
          }
          else {
            savedcodes = true;
            saveProject();
          }
        }else{
          boolean created = createNewProject();

          if (created) {
            boolean saved = saveProject();
          }else{

          }
        }
      }else{

        int returnVal = jfc.showSaveDialog(contentPane);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = jfc.getSelectedFile();
          if (file.exists()) {
            int value = JOptionPane.showConfirmDialog(this,
            "Would you like to overwrite the existing project file?",
            "Save Project Message",
            JOptionPane.YES_NO_OPTION);

            if(value == JOptionPane.YES_OPTION){
              project.saveProject(file);
              success = true;
            }else{
              saveProject();
            }
          }
          else {
            project.saveProject(file);
            success = true;
          }
        }else {

        }
      }
    } catch (Exception ex) {
      success = false;
      savedproject = false;
      JOptionPane.showMessageDialog(this,
                                   "Unknown error has occurred. " +
                                   "Please try to save your project at a later time.",
                                   "Project Save Message",
                                   JOptionPane.ERROR_MESSAGE);
    }

    if(success){
      savedproject = true;
      JOptionPane.showMessageDialog(this,
                                 "You have successfully saved your project.",
                                 "Project Save Message",
                                 JOptionPane.INFORMATION_MESSAGE);
      statusBar.setText("Successfully saved project \""+project.getProjectName()+"\".");
    }
    return success;
  }

  void openProjectMain(ActionEvent e) {
    openProject();
  }

  void saveProjectMain(ActionEvent e) {
    saveProject();
  }

  void closeProjectMain(ActionEvent e) {
    closeProject();
  }

  public void closeProject(){
    if(project != null){
      if (!savedproject) {
        int val = JOptionPane.showConfirmDialog(this,
                                                "Would you like to save the existing project first?",
                                                "Save Project Message",
                                                JOptionPane.YES_NO_OPTION);
        if (val == JOptionPane.YES_OPTION) {

          if (project.getProjectName().equals("")) {
            boolean created = createNewProject();

            if (created) {

              boolean saved = saveProject();

              if (saved) {
                savedproject = true;
                closeProject();
              }
              else {

              }
            }else{
              return;
            }
          } else {
            boolean saved = saveProject();

            if (saved) {
              savedproject = true;
              closeProject();
            }
            else {

            }
          }
        } else {
          savedproject = true;
          closeProject();
        }
      }else if (!savedcodes) {
        int codesval = JOptionPane.showConfirmDialog(this,
            "Would you like to save your codes?",
            "Save Codes Message",
            JOptionPane.YES_NO_OPTION);

        if (codesval == JOptionPane.YES_OPTION) {
          saveLocalCodes();
          closeProject();
        }
        else {
          savedcodes = true;
          closeProject();
        }
      } else {
        ArrayList projects = project.getProjectAssoc();

        if (!projects.isEmpty()) {
          Iterator projectiter = projects.iterator();

          while (projectiter.hasNext()) {
            ProjectAssociation temp = (ProjectAssociation) projectiter.next();

            String doclocation = temp.getDocumentLocation();
            refreshDocumentContents(doclocation);

            if (!doclocation.equals("")) {
              closeDocument(doclocation);
            }

          }
        }
        statusBar.setText("Successfully closed project \"" +
                          project.getProjectName() + "\".");
        this.setTitle("SQuAnTo");
        fileMenu_SaveProject.setEnabled(false);
        fileMenu_CloseProject.setEnabled(false);
        closeButton.setEnabled(false);
        saveProjectButton.setEnabled(false);
        project = null;
        savedproject = true;
      }
    }
  }

  void createNewProjectMain(ActionEvent e) {
    createNewProject();
  }

  public boolean createNewProject(){
    boolean created = false;
    if(project == null || project.getProjectName().equals("")){

      if(project == null){
        project = new ProjectDAO();
      }

      ProjectDialog dlg = new ProjectDialog(this, "Create New Project", true);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.pack();
      dlg.setVisible(true);

      if(!dlg.getProjectName().equals("")){
        project.setProjectName(dlg.getProjectName());

        fileMenu_SaveProject.setEnabled(true);
        fileMenu_CloseProject.setEnabled(true);
        closeButton.setEnabled(true);
        savedproject = false;
        created = true;
        statusBar.setText("Successfully created project \""+project.getProjectName()+"\".");
        this.setTitle("SQuAnTo - Project \""+project.getProjectName()+"\"");
      }else{

      }

    }else{
      int value = JOptionPane.showConfirmDialog(this,
                                                "Would you like to discard the existing project?",
                                                "Create New Project Message",
                                                JOptionPane.YES_NO_OPTION);
      if (value == JOptionPane.YES_OPTION) {

        if(!savedproject) {
          int val = JOptionPane.showConfirmDialog(this,
                                                "Would you like to save the existing project first?",
                                                "Save Project Message",
                                                JOptionPane.YES_NO_OPTION);
          if(val == JOptionPane.YES_OPTION){
              boolean saved = saveProject();

              if (saved) {
                savedproject = true;
                closeProject();
                createNewProject();
              }
              else {

              }
          }else{
            savedproject = true;
            closeProject();
            createNewProject();
          }
        }else{
          closeProject();
          createNewProject();
        }

        if(!savedcodes){
          int codesval = JOptionPane.showConfirmDialog(this,
              "Would you like to save your codes?",
              "Save Codes Message",
              JOptionPane.YES_NO_OPTION);

          if (codesval == JOptionPane.YES_OPTION) {
            saveLocalCodes();
            createNewProject();
          }else{
            savedcodes = true;
            createNewProject();
          }
        }
    }else if (value == JOptionPane.NO_OPTION) {

    }
   }
   return created;
  }

  void viewAllCodesAction(ActionEvent e) {
    viewAllCodes();
  }

  public void viewAllCodes(){
    Iterator iter = maindoc.getCodes().iterator();
   documentPane.getHighlighter().removeAllHighlights();
    highlight = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);

   while (iter.hasNext()) {

     Code code = (Code) iter.next();

     ArrayList sectionList = code.getSectionList();

     if (!sectionList.isEmpty()) {

       Iterator it = sectionList.iterator();

       while (it.hasNext()) {
         Section section = (Section) it.next();

         try {
           documentPane.getHighlighter().addHighlight(section.getSectionStart(),
                            section.getSectionEnd(), highlight);
         } catch (BadLocationException ex) {

         }
       }
     }
   }
  }

  void closeButtonAction(ActionEvent e) {
    closeProject();
  }

  void codesInformationAction(ActionEvent e) {
    CodeDialog dlg = new CodeDialog(this, "Codes Information", true,
                                    maindoc.getCodes());
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);
  }

  void saveCodesLocalMain() {
    saveLocalCodes();
  }

  void saveCodesRemoteMain() {
    saveRemoteCodes();
  }

  void searchCodesMain(ActionEvent e) {
    searchCodes();
  }

  public void searchCodes(){
    String searchString = null;

    searchString = JOptionPane.showInputDialog(this, "Enter a code name to search for:",
                                           "Input Code Name Message",
                                           JOptionPane.QUESTION_MESSAGE);

    if (searchString != null) {
      if (!searchString.equals("")) {

        for(Enumeration e =codingCodesNode.children(); e.hasMoreElements(); ){
          DefaultMutableTreeNode tnode1 = (DefaultMutableTreeNode) e.
              nextElement();

          for (Enumeration ee = tnode1.children(); ee.hasMoreElements(); ) {
            DefaultMutableTreeNode tnode2 = (DefaultMutableTreeNode) ee.
                nextElement();

            if (tnode2.toString().equals(searchString) ||
                (tnode2.toString().startsWith(searchString)) ||
                (tnode2.toString().endsWith(searchString))) {
              codesTree.scrollPathToVisible(new TreePath(tnode2.getPath()));
              codesTree.setSelectionPath(new TreePath(tnode2.getPath()));
              break;
            }
          }
        }

      } else {
        JOptionPane.showMessageDialog(this, "You cannot enter an empty value.",
                                  "Input Code Name Message",
                                  JOptionPane.ERROR_MESSAGE);
        searchCodes();
      }
    }
  }

}


class GUIFrame_jMenusearchItemActionAdapter implements ActionListener {
    MainGuiFrame adaptee;

    GUIFrame_jMenusearchItemActionAdapter(MainGuiFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.searchAction(e);
    }
}


class GUIFrame_jMenuFileExit_ActionAdapter implements ActionListener {
    MainGuiFrame adaptee;

    GUIFrame_jMenuFileExit_ActionAdapter(MainGuiFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.exitAction(e);
    }
}

class GUIFrame_jMenuHelpAbout_ActionAdapter implements ActionListener {
    MainGuiFrame adaptee;

    GUIFrame_jMenuHelpAbout_ActionAdapter(MainGuiFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.showHelpDialog(e);
    }
}

class GUIFrame_codeMenu_Code_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  GUIFrame_codeMenu_Code_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.newMainCode(e);
  }
}

class GUIFrame_conceptMenuItem_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  GUIFrame_conceptMenuItem_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.conceptMenuItem_Code(e);
  }
}

class GUIFrame_existingMenuItem_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  GUIFrame_existingMenuItem_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.existingMenuItem_Code(e);
  }
}

class MainGuiFrame_documentPane_mouseAdapter extends java.awt.event.MouseAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_documentPane_mouseAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.documentPane_mouseClicked(e);
  }
}

class MainGuiFrame_codesTree_treeSelectionAdapter implements javax.swing.event.TreeSelectionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codesTree_treeSelectionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(TreeSelectionEvent e) {
    adaptee.codesTree_valueChanged(e);
  }
}

class MainGuiFrame_fileMenu_CloseDocument_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_fileMenu_CloseDocument_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.closeDocumentMain(e);
  }
}

class MainGuiFrame_openOntologyMenu_Local_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_openOntologyMenu_Local_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.openLocalOntologyMain(e);
  }
}

class MainGuiFrame_openDocumentMenu_Local_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_openDocumentMenu_Local_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.openLocalDocumentMain(e);
  }
}

class MainGuiFrame_openProjectButton_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_openProjectButton_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.openProjectMain(e);
  }
}

class MainGuiFrame_ontologyMenu_Close_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_ontologyMenu_Close_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.closeOntologyMain(e);
  }
}

class MainGuiFrame_codeMenu_Concepts_Goup_mouseAdapter extends java.awt.event.MouseAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Concepts_Goup_mouseAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.codeMenu_Concepts_Goup_mouseEntered(e);
  }
  public void mouseMoved(MouseEvent e) {
    adaptee.codeMenu_Concepts_Goup_mouseMoved(e);
  }

}

class MainGuiFrame_codeMenu_Concepts_Godown_mouseAdapter extends java.awt.event.MouseAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Concepts_Godown_mouseAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.codeMenu_Concepts_Godown_mouseEntered(e);
  }
  public void mouseMoved(MouseEvent e) {
    adaptee.codeMenu_Concepts_Godown_mouseMoved(e);
  }

}

class MainGuiFrame_codeMenu_Invivo_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Invivo_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.codeInvivoAction(e);
  }
}

class MainGuiFrame_documentPane_focusAdapter extends java.awt.event.FocusAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_documentPane_focusAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.documentPane_focusGained(e);
  }
}

class MainGuiFrame_codeMenu_Existing_Goup_mouseAdapter extends java.awt.event.MouseAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Existing_Goup_mouseAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.codeMenu_Existing_Goup_mouseEntered(e);
  }
}

class MainGuiFrame_codeMenu_Existing_Godown_mouseAdapter extends java.awt.event.MouseAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Existing_Godown_mouseAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.codeMenu_Existing_Godown_mouseEntered(e);
  }
}

class MainGuiFrame_codeMenu_Concepts_Goup_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Concepts_Goup_mouseMotionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseMoved(MouseEvent e) {
    adaptee.codeMenu_Concepts_Goup_mouseMoved(e);
  }
}

class MainGuiFrame_codeMenu_Concepts_Godown_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Concepts_Godown_mouseMotionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseMoved(MouseEvent e) {
    adaptee.codeMenu_Concepts_Godown_mouseMoved(e);
  }
}

class MainGuiFrame_codeMenu_Existing_Goup_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Existing_Goup_mouseMotionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseMoved(MouseEvent e) {
    adaptee.codeMenu_Existing_Goup_mouseMoved(e);
  }
}

class MainGuiFrame_codeMenu_Existing_Godown_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Existing_Godown_mouseMotionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseMoved(MouseEvent e) {
    adaptee.codeMenu_Existing_Godown_mouseMoved(e);
  }
}

class MainGuiFrame_viewsMenu_HideCoding_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_viewsMenu_HideCoding_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.viewsMenu_HideCoding_actionPerformed(e);
  }
}

class MainGuiFrame_viewsMenu_HideDirectory_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_viewsMenu_HideDirectory_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.viewsMenu_HideDirectory_actionPerformed(e);
  }
}

class MainGuiFrame_viewsMenu_Show_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_viewsMenu_Show_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.viewsMenu_Show_actionPerformed(e);
  }
}

class MainGuiFrame_codeMenu_Document_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_Document_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.codeSourceDocumentAction(e);
  }
}

class MainGuiFrame_codeMenu_DocumentStructured_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_DocumentStructured_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.codeSourceDocumentStructuredAction(e);
  }
}

class MainGuiFrame_helpButton_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_helpButton_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.showHelpDialog(e);
  }
}

class MainGuiFrame_directoryTree_treeSelectionAdapter implements javax.swing.event.TreeSelectionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_directoryTree_treeSelectionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(TreeSelectionEvent e) {
    adaptee.directoryTree_valueChanged(e);
  }
}

class MainGuiFrame_directoryTree_mouseAdapter extends java.awt.event.MouseAdapter {
  MainGuiFrame adaptee;

  MainGuiFrame_directoryTree_mouseAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.directoryTree_mouseClicked(e);
  }
}

class MainGuiFrame_importMenu_Document_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_importMenu_Document_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.openLocalDocumentMain(e);
  }
}

class MainGuiFrame_closeMenu_Document_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_closeMenu_Document_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.closeDocumentMain(e);
  }
}

class MainGuiFrame_importMenu_Ontology_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_importMenu_Ontology_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.openLocalOntologyMain(e);
  }
}

class MainGuiFrame_closeMenu_Ontology_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_closeMenu_Ontology_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.closeOntologyMain(e);
  }
}

class MainGuiFrame_codesMenu_Delete_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codesMenu_Delete_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.deleteCodesAction(e);
  }
}

class MainGuiFrame_fileMenu_OpenProject_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_fileMenu_OpenProject_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.openProjectMain(e);
  }
}

class MainGuiFrame_fileMenu_SaveProject_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_fileMenu_SaveProject_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.saveProjectMain(e);
  }
}

class MainGuiFrame_saveProjectButton_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_saveProjectButton_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.saveProjectMain(e);
  }
}

class MainGuiFrame_fileMenu_CloseProject_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_fileMenu_CloseProject_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.closeProjectMain(e);
  }
}

class MainGuiFrame_fileMenu_CreateProject_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_fileMenu_CreateProject_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.createNewProjectMain(e);
  }
}

class MainGuiFrame_codeMenu_View_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codeMenu_View_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.viewAllCodesAction(e);
  }
}

class MainGuiFrame_codesMenu_View_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codesMenu_View_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.viewAllCodesAction(e);
  }
}

class MainGuiFrame_closeButton_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_closeButton_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.closeButtonAction(e);
  }
}

class MainGuiFrame_codesMenu_Information_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codesMenu_Information_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.codesInformationAction(e);
  }
}

class MainGuiFrame_codesMenu_SaveLocal_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codesMenu_SaveLocal_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.saveCodesLocalMain();
  }
}

class MainGuiFrame_codesMenu_SaveRemote_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codesMenu_SaveRemote_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.saveCodesRemoteMain();
  }
}

class MainGuiFrame_codesMenu_Search_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame adaptee;

  MainGuiFrame_codesMenu_Search_actionAdapter(MainGuiFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.searchCodesMain(e);
  }
}
