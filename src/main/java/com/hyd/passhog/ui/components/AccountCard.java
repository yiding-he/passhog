package com.hyd.passhog.ui.components;

import com.hyd.passhog.lib.Account;
import com.hyd.passhog.ui.builders.LayoutBuilder;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class AccountCard extends VBox {

  private final Account account;

  public AccountCard(Account account) {
    this.account = account;
    getStyleClass().add("account-card");
    setPadding(new Insets(5));

    if (account != null) {
      getChildren().addAll(
        LayoutBuilder.label(account.getUsername())
      );
    }
  }
}
