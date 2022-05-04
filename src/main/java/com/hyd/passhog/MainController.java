package com.hyd.passhog;

import com.hyd.passhog.components.RecentPassLibList;

import java.io.File;
import java.util.Arrays;

public class MainController {

  public RecentPassLibList lstRecentFiles;

  public void initialize() {

    lstRecentFiles.setFiles(Arrays.asList(
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("pom.xml").getAbsoluteFile(),
      new File("mvnw.cmd").getAbsoluteFile(),
      new File("README.md").getAbsoluteFile()
    ));
  }
}
