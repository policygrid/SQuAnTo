/*
 * Created on 13-Sep-2006 13:58:42 
 *
 * Author       : epignott
 * Project Name : SQuAnTo
 * Package Name : org.squanto.init.util
 */
package org.squanto.init.util;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;
import javax.swing.tree.*;

/**
 * @author epignott
 *
 */
public class TreePopupMenu extends MouseAdapter implements ActionListener {
    
    public TreePopupMenu() {
        menu = new JPopupMenu();
        JCheckBoxMenuItem item;
        for(int i = 0; i < 12; i++) {
            item = new JCheckBoxMenuItem("Item " + i, true);
            item.addActionListener(this);
            menu.add(item);
        }
    }
    
    public void mousePressed(java.awt.event.MouseEvent e) {
 
        displayMenu(e);
    }
 
    public void mouseReleased(java.awt.event.MouseEvent e) {
        
        displayMenu(e);
    }
    
    private void displayMenu(MouseEvent e) {
        if(e.isPopupTrigger())
            menu.show(e.getComponent(), e.getX(), e.getY());
    }
 
    public void actionPerformed(java.awt.event.ActionEvent e) {
        
        JCheckBoxMenuItem item;
        MenuElement[] elements = menu.getSubElements();
        for(int i = 0; i < elements.length; i++) {
            item = (JCheckBoxMenuItem)elements[i];
            System.out.println("Item " + i );
        }
    }
     
        
    private JPopupMenu menu;
}

