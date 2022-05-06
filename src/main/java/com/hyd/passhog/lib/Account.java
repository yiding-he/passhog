package com.hyd.passhog.lib;

import lombok.Data;

@Data
public class Account {

  private String username;

  private AuthType authType;

  private String password;

  private String publicKey;

  private String privateKey;

  private String privateKeyPassword;

  private String note;
}
