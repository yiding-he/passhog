package com.hyd.passhog;

import com.hyd.passhog.model.PasswordLib;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class PasshogApplication extends Application {

  public static final String APP_NAME = "Passhog 密码管理";

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

  public static void updatePrimaryStageTitle() {
    var savePath = PasswordLib.getInstance().getSavePath();
    if (savePath == null) {
      primaryStage.setTitle(APP_NAME + " - 未保存");
    } else {
      primaryStage.setTitle(APP_NAME + " - " + Paths.get(savePath).getFileName());
    }
  }

  @Override
  public void start(Stage stage) {
    primaryStage = stage;
    stage.setTitle(APP_NAME);
    stage.setWidth(800);
    stage.setHeight(600);

    loadScene(stage, "welcome.fxml");
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
