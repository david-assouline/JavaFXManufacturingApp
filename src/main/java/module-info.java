module com.main.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.main.javafxproject to javafx.fxml;
    exports com.main.javafxproject;
}