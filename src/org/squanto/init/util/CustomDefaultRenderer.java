package org.squanto.init.util;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class CustomDefaultRenderer extends DefaultTreeCellRenderer {
  private ImageIcon icon = null;

  public Component getTreeCellRendererComponent(
              JTree tree,
              Object value,
              boolean selected,
              boolean expanded,
              boolean leaf,
              int row,
              boolean hasFocus) {
  // Allow the original renderer to set up the label
   Component c = super.getTreeCellRendererComponent(
                  tree, value, selected,
                  expanded, leaf, row,
                  hasFocus);

    if(value != null){
      ( (JComponent) c).setToolTipText(value.toString());
    }

    TreePath path = tree.getPathForRow(row);

    if(path != null){

      TreePath parent = path.getParentPath();
      if (parent != null) {

        if (parent.getLastPathComponent().toString().equals("Free Codes") ||
            parent.getLastPathComponent().toString().equals("Structured Codes") ||
           parent.getLastPathComponent().toString().equals("Other Codes") ||
          parent.getLastPathComponent().toString().equals("Documents") ||
          parent.getLastPathComponent().toString().equals("Ontologies")){
               ((JComponent)c).setForeground(Color.BLUE);
        }else {
          if(parent.getPathCount()-2 > 0){
            if (parent.getPathComponent(parent.getPathCount() - 2).toString().
                equals("Free Codes") || parent.getPathComponent(parent.getPathCount() - 2).toString().
                  equals("Structured Codes")) {
              ( (JComponent) c).setForeground(Color.RED);
            }
          }
        }
      }
    }

   this.setOpenIcon(icon);

   return c;
  }

  public void setIcon(ImageIcon icon){
    this.icon = icon;
  }

}
