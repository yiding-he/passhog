package com.hyd.passhog.model;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class PasswordLibFile {

  private PasswordLibFile() {
  }

  public static byte[] readFile(String filePath) throws IOException {
    try (ZipFile f = new ZipFile(filePath)) {
      ZipEntry entry = f.getEntry("content.bson");
      if (entry != null) {
        try (InputStream is = f.getInputStream(entry)) {
          return IOUtils.toByteArray(is);
        }
      } else {
        throw new IOException("文件内容不存在");
      }
    }
  }
}
