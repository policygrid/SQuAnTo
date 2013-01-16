package org.squanto.init.document;

import javax.swing.text.*;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class AnnotDocument {
  private String title = new String("");
  private String location = new String("");
  private String format = new String("");
  private int length = 0;
  private Document doc = new DefaultStyledDocument(new StyleContext());

  public AnnotDocument() {
  }

  public String getFormat() {
    return format;
  }

  public String getLocation() {
    return location;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public void setDoc(Document doc) {
    this.doc = doc;
  }

  public String getTitle() {
    return title;
  }

  public int getLength() {
    return length;
  }

  public Document getDoc() {
    return doc;
  }
}
