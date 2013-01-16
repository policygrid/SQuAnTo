package org.squanto.init.project;

/**
 *
 * <p>Title: SQuAt</p>
 * <p>Description: Semantic Qualitative Analysis Annotation Tool</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: University of Aberdeen</p>
 * @author Georgios Kritikos
 * @version 1.0
 */

public class CodingFile {
  private String codingName = new String("");
  private String codingLocation = new String("");

  public String getCodingLocation() {
    return codingLocation;
  }

  public void setCodingName(String codingName) {
    this.codingName = codingName;
  }

  public void setCodingLocation(String codingLocation) {
    this.codingLocation = codingLocation;
  }

  public String getCodingName() {
    return codingName;
  }

  public CodingFile() {
  }
}
