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
    requires com.google.gson;
    requires org.slf4j;

    exports view;
    opens view to javafx.fxml;
}