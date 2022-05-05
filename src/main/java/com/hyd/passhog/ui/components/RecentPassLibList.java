package com.hyd.passhog.ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.List;

import static java.util.Collections.emptyList;

public class RecentPassLibList extends ScrollPane {

  public class FileItem extends VBox {

    private final File value;

    public FileItem(File file) {
      this.value = file;
      getStyleClass().add("file-item");
      setSpacing(5);

      Label fileName = new Label(file.getName());
      fileName.getStyleClass().add("file-name");
      Label folderPath = new Label(file.getParentFile().getAbsolutePath());
      folderPath.getStyleClass().add("folder-path");
      getChildren().addAll(fileName, folderPath);

      setOnMouseClicked(event -> {
        selectFileItem(this);
        if (event.getClickCount() == 2) {

        }
      });
    }

    public File getValue() {
      return value;
    }
  }

  public RecentPassLibList() {
    super(new VBox());
    setPadding(new Insets(10));
    setFitToWidth(true);
  }

  private VBox getContentVBox() {
    return (VBox) getContent();
  }

  private List<File> files = emptyList();

  public void setFiles(List<File> files) {
    this.files = files;
    getContentVBox().getChildren().clear();
    getContentVBox().getChildren().addAll(this.files.stream().map(FileItem::new).toList());
  }

  private void selectFileItem(FileItem fileItem) {
    getContentVBox().getChildren().forEach(n -> n.getStyleClass().remove("selected"));
    fileItem.getStyleClass().add("selected");
  }

  public List<File> getFiles() {
    return files;
  }
}
