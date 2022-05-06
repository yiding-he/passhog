package com.hyd.passhog.ui.dialogs;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class AlertDialog {

  private AlertDialog() {
  }

  public static void error(String message) {
    Platform.runLater(() -> {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
    });
  }
}
