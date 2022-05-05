package com.hyd.passhog.ui.dialogs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class AbstractDialog extends Stage {

  private final Window owner;

  public AbstractDialog(Window owner, String title) {
    this.owner = owner;
    setTitle(title);

    if (owner != null) {
      initOwner(owner);
      initModality(Modality.APPLICATION_MODAL);
    }

    setOnShowing(event -> {
      var dialogPane = createDialogPane();
      dialogPane.getButtonTypes().addAll(getDialogButtons());
      dialogPane.getButtonTypes().forEach(buttonType -> {
        var button = (Button) dialogPane.lookupButton(buttonType);
        button.setOnAction(e -> {
          if (onButtonClicked(buttonType)) {
            close();
          }
        });
      });
      setDialogPane(dialogPane);
    });

    setOnShown(event -> {
      sizeToScene();
      adjustPosition();
      onShown();
    });
  }

  private void adjustPosition() {
    var center = new double[]{
      owner.getX() + owner.getWidth() / 2,
      owner.getY() + owner.getHeight() / 2
    };

    this.setX(center[0] - this.getWidth() / 2);
    this.setY(center[1] - this.getHeight() / 2);
  }

  private void setDialogPane(DialogPane dialogPane) {
    setScene(new Scene(dialogPane));
  }

  protected abstract DialogPane createDialogPane();

  protected abstract ButtonType[] getDialogButtons();

  protected abstract boolean onButtonClicked(ButtonType buttonType);

  protected void onShown() {

  }
}
