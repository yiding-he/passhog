package com.hyd.passhog.ui.controllers;

import com.hyd.passhog.lib.Folder;
import com.hyd.passhog.lib.PasswordLib;
import com.hyd.passhog.ui.components.FolderTreeView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class MainController {

  public FolderTreeView tvFolders;

  public Label lblFolderName;

  public Hyperlink lnkAddress;

  public void initialize() {
    var rootFolder = PasswordLib.getInstance().getRootFolder();
    rootFolder.getChildren().add(new Folder("网站1", "https://www.baidu.com"));
    rootFolder.getChildren().add(new Folder("网站2", "https://www.google.com"));
    rootFolder.getChildren().add(new Folder("网站3", "https://www.taobao.com"));
    rootFolder.getChildren().add(new Folder("网站4", "https://www.jd.com"));

    var rootItem = FolderTreeView.convert(rootFolder);
    rootItem.setExpanded(true);
    tvFolders.setRoot(rootItem);

    tvFolders.getSelectionModel().selectedItemProperty().addListener(
      (o, d, newValue) -> loadFolder(newValue.getValue())
    );
  }

  private void loadFolder(Folder folder) {
    lblFolderName.setText(folder.getName());
    if (folder.getAddress() != null) {
      lnkAddress.setVisible(true);
      lnkAddress.setText(folder.getAddress());
    } else {
      lnkAddress.setVisible(false);
    }
  }
}
