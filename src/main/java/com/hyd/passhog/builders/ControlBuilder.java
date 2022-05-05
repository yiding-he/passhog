package com.hyd.passhog.builders;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControlBuilder {

  private ControlBuilder() {
  }

  ////////////////////////// TextField

  public static TextField textField() {
    return textField(null);
  }

  public static TextField textField(double prefWidth) {
    return textField(null, prefWidth);
  }

  public static TextField textField(String placeholder) {
    return textField(placeholder, -1);
  }

  public static TextField textField(String placeholder, double prefWidth) {
    TextField textField = new TextField();
    if (prefWidth > 0) {
      textField.setPrefWidth(prefWidth);
    }
    if (placeholder != null) {
      textField.setPromptText(placeholder);
    }
    return textField;
  }

  ////////////////////////// PasswordField

  public static PasswordField passwordField(double prefWidth) {
    var passwordField = new PasswordField();
    passwordField.setPrefWidth(prefWidth);
    return passwordField;
  }
}
