package org.squanto.init.gui;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.squanto.init.codes.Code;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: RDF Annotation</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class CodeDialog extends JDialog {
  JPanel mainPanel = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JLabel freeCodesLabel = new JLabel();
  JLabel structuredCodesLabel = new JLabel();
  JLabel otherCodesLabel = new JLabel();
  JPanel topPanel = new JPanel();
  JPanel bottomPanel = new JPanel();
  JButton okButton = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel leftPanel = new JPanel();
  VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();
  JPanel rightPanel = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  JLabel otherCodesValue = new JLabel();
  JLabel structuredCodesValue = new JLabel();
  JLabel freeCodesValue = new JLabel();
  ArrayList codes = new ArrayList();

  public CodeDialog(Frame frame, String title, boolean modal, ArrayList codes) {
    super(frame, title, modal);
    try {
      this.codes = codes;
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public CodeDialog() {
    this(null, "", false, new ArrayList());
  }

  private void jbInit() throws Exception {
    mainPanel.setLayout(verticalFlowLayout1);
    mainPanel.setPreferredSize(new Dimension(180, 113));
    mainPanel.setToolTipText("");
    freeCodesLabel.setText("Free Codes: ");
    structuredCodesLabel.setText("Structured Codes:");
    otherCodesLabel.setText("Source Document Codes:");
    topPanel.setLayout(borderLayout1);
    bottomPanel.setDebugGraphicsOptions(0);
    bottomPanel.setLayout(flowLayout1);
    okButton.setMinimumSize(new Dimension(21, 23));
    okButton.setPreferredSize(new Dimension(50, 23));
    okButton.setToolTipText("OK");
    okButton.setText("OK");
    okButton.addActionListener(new CodeDialog_okButton_actionAdapter(this));
    leftPanel.setLayout(verticalFlowLayout3);
    rightPanel.setLayout(verticalFlowLayout2);
    freeCodesValue.setToolTipText("Number of free codes");
    freeCodesValue.setText("");
    structuredCodesValue.setToolTipText("Number of structured codes");
    structuredCodesValue.setText("");
    otherCodesValue.setToolTipText("Number of source document codes");
    otherCodesValue.setText("");
    getContentPane().add(mainPanel);
    mainPanel.add(topPanel, null);
    topPanel.add(rightPanel, BorderLayout.CENTER);
    rightPanel.add(freeCodesValue, null);
    rightPanel.add(structuredCodesValue, null);
    rightPanel.add(otherCodesValue, null);
    topPanel.add(leftPanel,  BorderLayout.WEST);
    leftPanel.add(freeCodesLabel, null);
    leftPanel.add(structuredCodesLabel, null);
    leftPanel.add(otherCodesLabel, null);
    mainPanel.add(bottomPanel, null);
    bottomPanel.add(okButton, null);
    getFreeCodes();
    getStructuredCodes();
    getOtherCodes();
  }

  void okButtonAction(ActionEvent e) {
    cancel();
  }

  public void cancel(){
    dispose();
  }

  public void getFreeCodes(){
    int num = 0;

    if(!codes.isEmpty()){
      Iterator iter = codes.iterator();

      while(iter.hasNext()){
        Code temp = (Code)iter.next();
        if(temp.getCodeType().equals("normal") ||
           temp.getCodeType().equals("invivo")){
          num++;
        }
      }
    }
    freeCodesValue.setText(""+num);
  }

  public void getStructuredCodes(){
    int num = 0;

    if(!codes.isEmpty()){
     Iterator iter = codes.iterator();

     while(iter.hasNext()){
       Code temp = (Code)iter.next();
       if(temp.getCodeType().equals("structured")){
         num++;
       }
     }
   }
   structuredCodesValue.setText(""+num);
  }

  public void getOtherCodes(){
    int num = 0;

    if(!codes.isEmpty()){
     Iterator iter = codes.iterator();

     while(iter.hasNext()){
       Code temp = (Code)iter.next();
       if(temp.getCodeType().equals("sourcedoc")){
         num++;
       }
     }
   }
   otherCodesValue.setText(""+num);
  }


}

class CodeDialog_okButton_actionAdapter implements java.awt.event.ActionListener {
  CodeDialog adaptee;

  CodeDialog_okButton_actionAdapter(CodeDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.okButtonAction(e);
  }
}
