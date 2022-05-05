package com.hyd.passhog.ui.controllers;

import com.hyd.passhog.PasshogApplication;
import com.hyd.passhog.ui.components.RecentPassLibList;
import com.hyd.passhog.ui.dialogs.CreateLibDialog;

import java.io.File;
import java.util.Arrays;

public class WelcomeController {

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

  public void onCreateLibClicked() {
    new CreateLibDialog(PasshogApplication.primaryStage).showAndWait();
  }
}
