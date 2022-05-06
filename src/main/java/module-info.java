module com.hyd.passhog {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.kordamp.ikonli.javafx;
  requires org.apache.commons.lang3;
  requires lombok;
  requires com.alibaba.fastjson2;
  requires commons.io;
  requires org.kordamp.ikonli.fontawesome5;

  opens com.hyd.passhog to javafx.fxml;
  opens com.hyd.passhog.ui.components to javafx.fxml;
  opens com.hyd.passhog.ui.controllers to javafx.fxml;

  exports com.hyd.passhog;
  exports com.hyd.passhog.lib;
  exports com.hyd.passhog.ui.components;
  exports com.hyd.passhog.ui.controllers;
  exports com.hyd.passhog.file;
  opens com.hyd.passhog.file to javafx.fxml;
}
