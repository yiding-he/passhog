package com.hyd.passhog.lib;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Folder {

  private String name;

  private String address;

  private String note;

  private List<Folder> children = new ArrayList<>();

  public Folder() {
  }

  public Folder(String name) {
    this.name = name;
  }

  public Folder(String name, String address) {
    this.name = name;
    this.address = address;
  }
}
