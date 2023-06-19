module com.otavio.wenews {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens com.otavio.wenews to javafx.fxml;
    exports com.otavio.wenews;
    exports com.otavio.wenews.controllers.users_controller;
    opens com.otavio.wenews.controllers.users_controller to javafx.fxml;
    exports com.otavio.wenews.controllers.employes_controller;
    opens com.otavio.wenews.controllers.employes_controller to javafx.fxml;
    exports com.otavio.wenews.controllers.posts_controller;
    opens com.otavio.wenews.controllers.posts_controller to javafx.fxml;
}