module com.example.ultimatefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    requires java.naming;

    opens com.example.ultimatefx to javafx.fxml;
    exports com.example.ultimatefx;
    exports com.example.ultimatefx.controlador;
    opens com.example.ultimatefx.controlador to javafx.fxml;
    exports com.example.ultimatefx.modelos;
    opens com.example.ultimatefx.modelos to javafx.fxml;
    exports com.example.ultimatefx.dao;
    opens com.example.ultimatefx.dao to javafx.fxml;
    exports com.example.ultimatefx.repositorios;
    exports com.example.ultimatefx.controlador.system;

}