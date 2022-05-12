module com.example.banking_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.opencsv;
    exports com.example.banking_system.services;
    requires org.apache.commons.lang3;
    requires java.sql;
    opens com.example.banking_system to javafx.fxml;
    exports com.example.banking_system;
    exports com.example.banking_system.controllers;
    opens com.example.banking_system.controllers to javafx.fxml;
    opens com.example.banking_system.services to org.apache.commons.lang3;
}