package com.example.ultimatefx.fabrica;


import javafx.scene.control.Button;

/**
 * La clase UserButtonFactory contiene plantillas de elementos
 * @author alumne
 * @version java 20
 */
public final class UserButtonFactory {
    private static UserButtonFactory instance;
    private UserButtonFactory(){}
    public static UserButtonFactory getInstance(){
        if (instance == null){
            instance = new UserButtonFactory();
        }
        return instance;
    }

    /**
     * @param text Texto del botón
     * @return Retorna un botón con sus propiedades
     */
    public Button createButton(String text){
        Button btn = new Button();

        btn.setText(text);

        btn.setPrefWidth(200);

        return btn;
    }


}
