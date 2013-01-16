package org.squanto.init.gui;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class ProjectDialog extends JDialog {
  JPanel mainPanel = new JPanel();
  JLabel projectLabel = new JLabel();
  JTextField projectTextField = new JTextField();
  JPanel westPanel = new JPanel();
  JPanel eastPanel = new JPanel();
  JPanel southPanel = new JPanel();
  JButton submitButton = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel northPanel = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  String projectName = new String("");
  JButton clearButton = new JButton();
  JButton cancelButton = new JButton();

  public ProjectDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public ProjectDialog() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    mainPanel.setLayout(borderLayout1);
    projectLabel.setPreferredSize(new Dimension(102, 20));
    projectLabel.setText("Enter a project name:");
    projectTextField.setMinimumSize(new Dimension(11, 20));
    projectTextField.setNextFocusableComponent(submitButton);
    projectTextField.setPreferredSize(new Dimension(50, 20));
    projectTextField.setToolTipText("Enter a project name");
    projectTextField.setText("");
    submitButton.setNextFocusableComponent(projectTextField);
    submitButton.setText("Submit");
    submitButton.addActionListener(new ProjectDialog_submitButton_actionAdapter(this));
    southPanel.setLayout(flowLayout1);
    northPanel.setPreferredSize(new Dimension(10, 20));
    westPanel.setPreferredSize(new Dimension(112, 30));
    westPanel.setLayout(verticalFlowLayout2);
    eastPanel.setPreferredSize(new Dimension(50, 30));
    eastPanel.setLayout(verticalFlowLayout1);
    mainPanel.setPreferredSize(new Dimension(230, 103));
    clearButton.setToolTipText("");
    clearButton.setText("Clear");
    clearButton.addActionListener(new ProjectDialog_clearButton_actionAdapter(this));
    cancelButton.setText("Cancel");
    cancelButton.addActionListener(new ProjectDialog_cancelButton_actionAdapter(this));
    getContentPane().add(mainPanel);
    mainPanel.add(westPanel,  BorderLayout.WEST);
    westPanel.add(projectLabel, null);
    mainPanel.add(eastPanel,  BorderLayout.CENTER);
    eastPanel.add(projectTextField, null);
    mainPanel.add(southPanel,  BorderLayout.SOUTH);
    southPanel.add(submitButton, null);
    southPanel.add(clearButton, null);
    southPanel.add(cancelButton, null);
    mainPanel.add(northPanel, BorderLayout.NORTH);
  }

  void submitButtonAction(ActionEvent e) {
    String projectname = new String("");
    projectname = projectTextField.getText();

    if(projectname.equals("")){
      JOptionPane.showMessageDialog(this, "Please provide a project name.",
                                      "Create New Project Message",
                                      JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    this.setProjectName(projectname);
    cancel();
  }

  void cancel(){
    dispose();
  }

  public String getProjectName(){
    return this.projectName;
  }

  public void setProjectName(String projectName){
    this.projectName = projectName;
  }

  void cancelButtonAction(ActionEvent e) {
    cancel();
  }

  void clearButtonAction(ActionEvent e) {
    projectTextField.setText("");
    projectTextField.requestFocus(true);
  }
}

class ProjectDialog_submitButton_actionAdapter implements java.awt.event.ActionListener {
  ProjectDialog adaptee;

  ProjectDialog_submitButton_actionAdapter(ProjectDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.submitButtonAction(e);
  }
}

class ProjectDialog_cancelButton_actionAdapter implements java.awt.event.ActionListener {
  ProjectDialog adaptee;

  ProjectDialog_cancelButton_actionAdapter(ProjectDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cancelButtonAction(e);
  }
}

class ProjectDialog_clearButton_actionAdapter implements java.awt.event.ActionListener {
  ProjectDialog adaptee;

  ProjectDialog_clearButton_actionAdapter(ProjectDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.clearButtonAction(e);
  }
}
