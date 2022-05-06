package com.hyd.passhog.file;

import com.hyd.passhog.lib.PasswordLib;
import com.hyd.passhog.ui.dialogs.AlertDialog;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class PasswordLibFile {

  public static final String ENTRY_NAME = "content.bson";

  private PasswordLibFile() {
  }

  public static void openFile(String filePath) {
    try {
      var content = readArchiveContent(filePath);
      var passwordLib = PasswordLib.readBson(content);
      PasswordLib.setInstance(passwordLib);
    } catch (IOException e) {
      e.printStackTrace();
      AlertDialog.error("Error opening file: " + e.getMessage());
    }
  }

  public static byte[] readArchiveContent(String filePath) throws IOException {
    try (ZipFile f = new ZipFile(filePath)) {
      ZipEntry entry = f.getEntry(ENTRY_NAME);
      if (entry != null) {
        try (InputStream is = f.getInputStream(entry)) {
          return IOUtils.toByteArray(is);
        }
      } else {
        throw new IOException("Entry not found: " + ENTRY_NAME);
      }
    }
  }
}
