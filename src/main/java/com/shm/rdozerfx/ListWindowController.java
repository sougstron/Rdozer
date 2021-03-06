package com.shm.rdozerfx;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListWindowController
{


    @FXML private TextField textField;
    @FXML private Label messageLabel;
    @FXML private ComboBox textList;
    @FXML private javafx.scene.control.Button numberGen;
    @FXML public void handleButtonToNumberGen(ActionEvent event) throws IOException {
        //Close current
        Stage stage = (Stage) numberGen.getScene().getWindow();
        // do what you have to do
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/numbersWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        Scene scene = new Scene(root1, 350, 400);
        scene.getStylesheets().add("/styles/styles.css");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rdozer");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML private javafx.scene.control.Button unEqGen;
    @FXML public void handleButtonToUnEqGen(ActionEvent event) throws IOException {
        //Close current
        Stage stage = (Stage) unEqGen.getScene().getWindow();
        // do what you have to do
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/unEqualWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        Scene scene = new Scene(root1, 350, 400);
        scene.getStylesheets().add("/styles/styles.css");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rdozer");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void onEnter(ActionEvent ae){
       if (!textField.getText().isEmpty()){
            textList.getItems().add(textField.getText());
            textList.getSelectionModel().selectLast();
            textField.clear();
            textField.positionCaret( 0 );
        } else {messageLabel.setText("Empty field, type something.");}
    }
    
    public void textList() {
        if (!textField.getText().isEmpty()){
            textList.getItems().add(textField.getText());
            textList.getSelectionModel().selectLast();
            textField.clear();
            textField.positionCaret( 0 );
        } else {messageLabel.setText("Empty field, type something.");}
    }
    
    public void generate() {
        ArrayList<String> list = new ArrayList<>();
        try {
            if (!textList.getItems().isEmpty()){
                for (int x = 0; x < textList.getItems().size(); x++){
                    list.add(String.valueOf(textList.getItems().get(x)));
                }

                int i = list.size();
                if (i != 1) {
                    i -= 1;
                    i = (int) (Math.random() * ++i);
                    messageLabel.setText(String.valueOf(list.get(i)));
                } else {messageLabel.setText(list.get(0));}
            } else {messageLabel.setText("Empty list, add smthng to this.");}
        } catch (IndexOutOfBoundsException e) {messageLabel.setText("Array bug.");}
    }
    
    public void clear() {
        if (!textList.getItems().isEmpty()){
            textList.setValue(null);
            textList.getItems().removeAll(textList.getItems());
        }
    }
    
    
    
    public void remove() {
            if (textList.getSelectionModel().getSelectedItem() != null){
                String s = String.valueOf(textList.getSelectionModel().getSelectedItem());
                textList.getItems().remove(textList.getSelectionModel().getSelectedItem());
                textList.setValue(null);
            }
    }

}
