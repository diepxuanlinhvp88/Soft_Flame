module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires java.net.http;
    requires java.desktop;
    requires freetts;
    requires javafx.media;
    requires org.kordamp.bootstrapfx.core;

    exports view;
    opens view to javafx.fxml;
}