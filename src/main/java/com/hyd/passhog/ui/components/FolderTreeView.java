package com.hyd.passhog.ui.components;

import com.hyd.passhog.lib.Folder;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.javafx.FontIcon;

public class FolderTreeView extends TreeView<Folder> {

  public static TreeItem<Folder> convert(Folder folder) {
    var item = new TreeItem<>(folder);
    folder.getChildren().forEach(subFolder -> item.getChildren().add(convert(subFolder)));
    return item;
  }

  public FolderTreeView() {
    setCellFactory(folderTreeView -> new TreeCell<>() {
      @Override
      protected void updateItem(Folder item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty && item != null) {
          setText(item.getName());
          var icon = new FontIcon(FontAwesomeRegular.FOLDER_OPEN);
          icon.setIconColor(Color.web("#85c1ec"));
          setGraphic(icon);
        } else {
          setText(null);
          setGraphic(null);
        }
      }
    });
  }

}
