package com.hyd.passhog.builders;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class LayoutBuilder {

  private LayoutBuilder() {

  }

  ////////////////////////// padding

  public static Insets padding(double topRightBottomLeft) {
    return new Insets(topRightBottomLeft);
  }

  public static Insets padding(double top, double right, double bottom, double left) {
    return new Insets(top, right, bottom, left);
  }

  ////////////////////////// label

  public static Label label(String text) {
    var label = new Label(text);
    label.setMinWidth(Region.USE_PREF_SIZE);
    return label;
  }

  ////////////////////////// hBox

  public static HBox hBox(double spacing, Node... children) {
    return hBox(Pos.BASELINE_LEFT, null, spacing, children);
  }

  public static HBox hBox(Pos alignment, double spacing, Node... children) {
    return hBox(alignment, null, spacing, children);
  }

  public static HBox hBox(Pos alignment, Insets padding, double spacing, Node... children) {
    var hBox = new HBox(spacing, children);
    if (padding != null) {
      hBox.setPadding(padding);
    }
    if (alignment != null) {
      hBox.setAlignment(alignment);
    }
    return hBox;
  }

  public static <T extends Node> T hGrow(T node) {
    HBox.setHgrow(node, Priority.ALWAYS);
    return node;
  }
}
