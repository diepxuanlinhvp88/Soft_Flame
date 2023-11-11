module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires java.net.http;
    requires java.desktop;

    opens view to javafx.fxml;
    exports view;
}