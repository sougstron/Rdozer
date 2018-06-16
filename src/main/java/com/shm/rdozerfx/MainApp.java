package com.shm.rdozerfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.stage.Stage;


public class MainApp extends Application {


    public static void main(String[] args) throws Exception {
        
        launch(args);
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.hide();
        String fxmlFile = "/fxml/numbersWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        
        Scene scene = new Scene(rootNode, 350, 400);
        scene.getStylesheets().add("/styles/styles.css");
        
        //Animation and graphics
        rootNode.setEffect(new Reflection());
        
        stage.setTitle("Rdozer");
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void sw(Stage stage) throws Exception {
        stage.hide();
        String fxmlFile = "/fxml/hello.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        
        Scene scene = new Scene(rootNode, 400, 200);
        scene.getStylesheets().add("/styles/styles.css");

        //Animation and graphics
        rootNode.setEffect(new Reflection());
        
        stage.setTitle("Rdozer");
        stage.setScene(scene);
        stage.show();
    }
}
