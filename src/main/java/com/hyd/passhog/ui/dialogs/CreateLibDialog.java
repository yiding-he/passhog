package com.hyd.passhog.ui.dialogs;

import com.hyd.passhog.model.PasswordLib;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.stage.Window;
import org.apache.commons.lang3.StringUtils;

import static com.hyd.passhog.ui.builders.ControlBuilder.passwordField;
import static com.hyd.passhog.ui.builders.LayoutBuilder.*;

public class CreateLibDialog extends AbstractDialog {

  private final PasswordField textField = passwordField(200);

  public CreateLibDialog(Window owner) {
    super(owner, "创建密码库");
    setResizable(false);
  }

  protected DialogPane createDialogPane() {
    var dialogPane = new DialogPane();
    dialogPane.setContent(hBox(10,
      label("请输入主密码："),
      hGrow(textField)
    ));
    return dialogPane;
  }

  @Override
  protected ButtonType[] getDialogButtons() {
    return new ButtonType[]{ButtonType.OK, ButtonType.CANCEL};
  }

  @Override
  protected void onShown() {
    textField.requestFocus();
  }

  @Override
  protected boolean onButtonClicked(ButtonType buttonType) {
    if (buttonType == ButtonType.OK) {
      if (StringUtils.isBlank(textField.getText())) {
        textField.requestFocus();
        return false;
      } else {
        PasswordLib.setInstance(PasswordLib.create(textField.getText()));
        return true;
      }
    }
    return true;
  }
}
