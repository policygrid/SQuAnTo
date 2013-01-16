package org.squanto.init.gui;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
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

public class DocumentDialog extends JDialog {
  JPanel mainPanel = new JPanel();
  JLabel codenameLabel = new JLabel();
  JLabel authorLabel = new JLabel();
  JLabel languageLabel = new JLabel();
  JLabel accessLabel = new JLabel();
  JTextField codeNameTextField = new JTextField();
  JTextField authorTextField = new JTextField();
  String[] language = { "English", "French", "German", "Spanish", "Chinese" };
  String[] access = { "Shared", "Not Shared" };
  JComboBox languageComboBox = new JComboBox(language);
  JComboBox accessComboBox = new JComboBox(access);
  JButton submitButton = new JButton();
  JButton cancelButton = new JButton();
  JPanel westPanel = new JPanel();
  JPanel eastPanel = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  JPanel northPanel = new JPanel();
  JPanel southPanel = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JButton clearButton = new JButton();
  Code code = null;

  public DocumentDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DocumentDialog() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    mainPanel.setLayout(borderLayout1);
    codenameLabel.setPreferredSize(new Dimension(34, 25));
    codenameLabel.setText("Code Name:");
    accessLabel.setText("Access:");
    languageLabel.setPreferredSize(new Dimension(34, 25));
    languageLabel.setText("Language:");
    authorLabel.setText("Author:");
    submitButton.setAlignmentY((float) 0.5);
    submitButton.setText("Submit");
    submitButton.addActionListener(new DocumentDialog_submitButton_actionAdapter(this));
    cancelButton.setText("Cancel");
    cancelButton.addActionListener(new DocumentDialog_cancelButton_actionAdapter(this));
    eastPanel.setLayout(verticalFlowLayout1);
    westPanel.setLayout(verticalFlowLayout2);
    northPanel.setPreferredSize(new Dimension(10, 40));
    southPanel.setPreferredSize(new Dimension(10, 60));
    eastPanel.setBorder(BorderFactory.createEtchedBorder());
    eastPanel.setPreferredSize(new Dimension(38, 50));
    clearButton.setText("Clear");
    clearButton.addActionListener(new DocumentDialog_clearButton_actionAdapter(this));
    westPanel.setPreferredSize(new Dimension(70, 105));
    codeNameTextField.setNextFocusableComponent(authorTextField);
    codeNameTextField.setToolTipText("Enter a code name");
    codeNameTextField.setText("");
    mainPanel.setPreferredSize(new Dimension(300, 237));
    authorTextField.setNextFocusableComponent(languageComboBox);
    authorTextField.setToolTipText("Enter an author name");
    languageComboBox.setNextFocusableComponent(accessComboBox);
    languageComboBox.setToolTipText("Enter the document language");
    accessComboBox.setNextFocusableComponent(codeNameTextField);
    accessComboBox.setToolTipText("Enter the code access rights");
    getContentPane().add(mainPanel);
    mainPanel.add(eastPanel,  BorderLayout.CENTER);
    eastPanel.add(codeNameTextField, null);
    eastPanel.add(authorTextField, null);
    eastPanel.add(languageComboBox, null);
    eastPanel.add(accessComboBox, null);
    mainPanel.add(westPanel,  BorderLayout.WEST);
    westPanel.add(codenameLabel, null);
    westPanel.add(authorLabel, null);
    westPanel.add(languageLabel, null);
    westPanel.add(accessLabel, null);
    mainPanel.add(northPanel, BorderLayout.NORTH);
    mainPanel.add(southPanel,  BorderLayout.SOUTH);
    southPanel.add(submitButton, null);
    southPanel.add(clearButton, null);
    southPanel.add(cancelButton, null);
  }

  void cancel(){
    dispose();
  }

  void cancelButtonAction(ActionEvent e) {
    cancel();
  }

  void clearButtonAction(ActionEvent e) {
    codeNameTextField.setText("");
    authorTextField.setText("");
    languageComboBox.setSelectedIndex(0);
    accessComboBox.setSelectedIndex(0);
    codeNameTextField.requestFocus(true);
  }

  void submitButtonAction(ActionEvent e) {
    String codename = new String("");
    String author = new String("");
    codename = codeNameTextField.getText();
    author = authorTextField.getText();

    if(codename.equals("") || author.equals("")){
      JOptionPane.showMessageDialog(this, "Please fill in all the values.",
                                      "Code Source Document Message",
                                      JOptionPane.INFORMATION_MESSAGE);
      return;
    }else{
      boolean shared = true;
      code = new Code();
      Section section = new Section();
      code.setName(codename.trim());
      code.setAuthor(author.trim());
      code.setLanguage(languageComboBox.getSelectedItem().toString());

      if (accessComboBox.getSelectedItem().toString().equals("Not Shared")) {
        shared = false;
      }

      code.setShared(shared);
      code.setCreationDate(new java.util.Date().toString());
      section.setSectionStart(0);
      section.setSectionEnd(0);
      code.setSectionList(section);
      cancel();
    }

  }

  public Code getDocumentCode(){
    return this.code;
  }

}

class DocumentDialog_cancelButton_actionAdapter implements java.awt.event.ActionListener {
  DocumentDialog adaptee;

  DocumentDialog_cancelButton_actionAdapter(DocumentDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cancelButtonAction(e);
  }
}

class DocumentDialog_clearButton_actionAdapter implements java.awt.event.ActionListener {
  DocumentDialog adaptee;

  DocumentDialog_clearButton_actionAdapter(DocumentDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.clearButtonAction(e);
  }
}

class DocumentDialog_submitButton_actionAdapter implements java.awt.event.ActionListener {
  DocumentDialog adaptee;

  DocumentDialog_submitButton_actionAdapter(DocumentDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.submitButtonAction(e);
  }
}
