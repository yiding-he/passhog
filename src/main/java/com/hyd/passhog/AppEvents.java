package com.hyd.passhog;

public class AppEvents {

  private AppEvents() {
  }

  public static void onPasswordLibLoaded() {
    PasshogApplication.loadScene(PasshogApplication.primaryStage, "main.fxml");
  }
}
