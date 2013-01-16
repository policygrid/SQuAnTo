package org.squanto.init.codes;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class Section {
  private int sectionStart = -1;
  private int sectionEnd = -1;
  private String bodyText = new String("");

  public int getSectionEnd() {
    return sectionEnd;
  }

  public void setSectionStart(int sectionStart) {
    this.sectionStart = sectionStart;
  }

  public void setSectionEnd(int sectionEnd) {
    this.sectionEnd = sectionEnd;
  }

  public void setBodyText(String bodyText) {
    this.bodyText = bodyText;
  }

  public int getSectionStart() {
    return sectionStart;
  }

  public String getBodyText() {
    return bodyText;
  }

  public Section() {
  }
}
