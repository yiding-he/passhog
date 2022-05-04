module com.hyd.passhog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.hyd.passhog to javafx.fxml;
    opens com.hyd.passhog.components to javafx.fxml;
    exports com.hyd.passhog;
    exports com.hyd.passhog.components;
}
