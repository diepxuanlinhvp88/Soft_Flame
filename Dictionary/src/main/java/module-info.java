module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    opens view to javafx.fxml;
    exports view;
}