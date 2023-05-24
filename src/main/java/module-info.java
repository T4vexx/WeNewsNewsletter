module com.otavio.wenews {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.otavio.wenews to javafx.fxml;
    exports com.otavio.wenews;
    opens com.otavio.wenews.controllers to javafx.fxml;
    exports com.otavio.wenews.controllers;
}