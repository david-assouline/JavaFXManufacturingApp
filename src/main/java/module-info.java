module com.main.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.main.javafxproject to javafx.fxml;
    opens com.main.javafxproject.Controllers to javafx.fxml;
    opens com.main.javafxproject.Model to javafx.fxml, javafx.base;
    opens com.main.javafxproject.Toolkit to javafx.fxml;
    exports com.main.javafxproject;
}