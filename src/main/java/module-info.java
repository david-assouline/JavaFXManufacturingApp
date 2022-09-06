module com.main.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.main.javafxproject to javafx.fxml;
    exports com.main.javafxproject;
    exports com.main.javafxproject.controllers;
    opens com.main.javafxproject.controllers to javafx.fxml;
    exports com.main.javafxproject.model;
    opens com.main.javafxproject.model to javafx.fxml;
}