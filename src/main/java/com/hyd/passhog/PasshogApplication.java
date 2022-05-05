package com.hyd.passhog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PasshogApplication extends Application {

  public static Stage primaryStage;

  public static void loadScene(Stage stage, String fxml) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(PasshogApplication.class.getResource(fxml));
      Scene scene = new Scene(fxmlLoader.load());
      stage.setScene(scene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void start(Stage stage) {
    primaryStage = stage;
    stage.setTitle("Passhog 密码管理");
    stage.setWidth(800);
    stage.setHeight(600);

    loadScene(stage, "welcome.fxml");
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
