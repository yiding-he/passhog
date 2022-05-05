package com.hyd.passhog.model;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONObject;
import com.hyd.passhog.AppEvents;
import com.hyd.passhog.util.AESUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PasswordLib {

  private static final String ENC_TEST_STRING = "abcdefghijklmnopqrstuvwxyz";

  private transient String savePath;

  private transient String mainPassword;

  private transient String contentEncrypted;

  public String getSavePath() {
    return savePath;
  }

  public void setSavePath(String savePath) {
    this.savePath = savePath;
  }

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

  private PasswordLib parseJson(JSONObject jsonObject) {
    this.passwordValidator = jsonObject.getString("passwordValidator");
    this.contentEncrypted = jsonObject.getString("content");
    return this;
  }

  //////////////////////////

  private final List<DataCenter> dataCenters = new ArrayList<>();

  public void addDataCenter(DataCenter dataCenter) {
    this.dataCenters.add(dataCenter);
  }

  public void removeDataCenter(DataCenter dataCenter) {
    this.dataCenters.remove(dataCenter);
  }

  public List<DataCenter> getDataCenters() {
    return Collections.unmodifiableList(this.dataCenters);
  }

  //////////////////////////

  private String passwordValidator;

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

}

