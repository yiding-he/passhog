package com.hyd.passhog.model;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONObject;
import com.hyd.passhog.AppEvents;
import com.hyd.passhog.util.AESUtils;

public final class PasswordLib {

  private static final String ENC_TEST_STRING = "abcdefghijklmnopqrstuvwxyz";

  private String passwordValidator;

  private transient String mainPassword;

  private transient String contentEncrypted;

  ////////////////////////// instance

  private static PasswordLib instance;

  public static PasswordLib getInstance() {
    return instance;
  }

  public static void setInstance(PasswordLib instance) {
    PasswordLib.instance = instance;
    AppEvents.onPasswordLibLoaded();
  }

  ////////////////////////// constructors

  public static PasswordLib create(String mainPassword) {
    var passwordLib = new PasswordLib();
    passwordLib.validatePassword(mainPassword);
    return passwordLib;
  }

  public static PasswordLib readJson(String json) {
    var jsonObject = JSON.parseObject(json);
    return new PasswordLib().parseJson(jsonObject);
  }

  public static PasswordLib readBson(byte[] bson) {
    var jsonObject = JSONB.parseObject(bson);
    return new PasswordLib().parseJson(jsonObject);
  }

  private PasswordLib() {
  }

  //////////////////////////

  public String getPasswordValidator() {
    return passwordValidator;
  }

  public boolean validatePassword(String mainPassword) {
    if (this.mainPassword == null) {
      this.mainPassword = mainPassword;
      this.passwordValidator = AESUtils.encode256(ENC_TEST_STRING, mainPassword);
      return true;
    }

    return this.passwordValidator.equals(
      AESUtils.encode256(ENC_TEST_STRING, mainPassword));
  }

  private PasswordLib parseJson(JSONObject jsonObject) {
    this.passwordValidator = jsonObject.getString("passwordValidator");
    this.contentEncrypted = jsonObject.getString("content");
    return this;
  }

}

