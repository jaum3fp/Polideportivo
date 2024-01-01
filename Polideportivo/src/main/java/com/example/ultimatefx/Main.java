package com.example.ultimatefx;

import com.example.ultimatefx.controlador.system.Sistema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Esta clase MAIN.
 * @author alumne
 * @version java 20
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Sistema.getInstance().stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 314, 393);
        stage.setTitle("Eu polideportinho!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}