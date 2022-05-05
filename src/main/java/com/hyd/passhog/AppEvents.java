package com.hyd.passhog;

public class AppEvents {

  private AppEvents() {
  }

  public static void onPasswordLibLoaded() {
    PasshogApplication.updatePrimaryStageTitle();
    PasshogApplication.loadScene(PasshogApplication.primaryStage, "main.fxml");
  }
}
