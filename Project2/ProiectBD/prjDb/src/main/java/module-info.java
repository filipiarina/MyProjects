module com.example.prjdb {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.prjdb to javafx.fxml;
    exports com.example.prjdb;
    opens com.example.prjdb.controllers to javafx.fxml;
    exports com.example.prjdb.controllers;
}