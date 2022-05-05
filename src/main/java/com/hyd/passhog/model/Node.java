package com.hyd.passhog.model;

import lombok.Data;

import java.util.List;

/**
 * A server node in the datacenter.
 */
@Data
public class Node {

  private String name;

  private String ip;

  private String hostname;

  private String note;

  private List<Account> accounts;
}
