module com.example.laba3final {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.laba3final to javafx.fxml;
    exports com.example.laba3final;
    exports com.example.laba3final.Controllers;
    opens com.example.laba3final.Controllers to javafx.fxml;
}