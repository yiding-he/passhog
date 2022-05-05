package com.hyd.passhog.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataCenter {

  private String name;

  private String address;

  private String note;

  private List<Node> nodes = new ArrayList<>();
}
