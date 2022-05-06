package com.hyd.passhog.lib;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONObject;
import com.hyd.passhog.AppEvents;
import com.hyd.passhog.enc.AESUtils;

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
    this.rootFolder.setName("我的账户");
  }

  private PasswordLib parseJson(JSONObject jsonObject) {
    this.passwordValidator = jsonObject.getString("passwordValidator");
    this.contentEncrypted = jsonObject.getString("content");
    return this;
  }

  @SuppressWarnings("unchecked")
  public byte[] toBsonBytes() {
    JSONObject content = new JSONObject();
    content.put("rootFolder", this.rootFolder);
    this.contentEncrypted = AESUtils.encode256(content.toJSONString(), this.mainPassword);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("passwordValidator", this.passwordValidator);
    jsonObject.put("content", this.contentEncrypted);

    return JSONB.toBytes(jsonObject);
  }

  //////////////////////////

  private Folder rootFolder = new Folder();

  public Folder getRootFolder() {
    return rootFolder;
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

    var isValid = this.passwordValidator.equals(
      AESUtils.encode256(ENC_TEST_STRING, mainPassword));

    if (isValid) {
      this.mainPassword = mainPassword;
      decodeContent();
    }
    return isValid;
  }

  private void decodeContent() {
    JSONObject contentObject = JSON.parseObject(AESUtils.decode256(this.contentEncrypted, this.mainPassword));
    this.rootFolder = contentObject.getObject("rootFolder", Folder.class);
  }

}

