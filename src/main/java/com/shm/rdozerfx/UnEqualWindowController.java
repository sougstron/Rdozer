/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shm.rdozerfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
/**
 *
 * @author Shmonya
 */
public class UnEqualWindowController {
    
    @FXML private TextField definition;
    @FXML private TextField chancePercent;
    @FXML private Label outputLabel;
    @FXML private ComboBox defList;
    private final Map<Integer, String> map = new HashMap<>();
    
    public void defList() {
        try {
            if (!definition.getText().isEmpty() && !chancePercent.getText().isEmpty()){
                if (!(Integer.parseInt(chancePercent.getText()) >= 100 || Integer.parseInt(chancePercent.getText()) <= 0)){
                    defList.getItems().add(getFullDef(Integer.parseInt(chancePercent.getText()), definition.getText()));
                    map.put(Integer.parseInt(chancePercent.getText()), definition.getText());
                    defList.getSelectionModel().selectLast();
                    definition.clear();
                    chancePercent.clear();
                    definition.positionCaret( 0 );
                } else {outputLabel.setText("Try chances from 1 to 99.");}
            } else {outputLabel.setText("Empty field, type something.");}
        } catch (NumberFormatException e) {outputLabel.setText("Wrong number.");}
    }
    
    public String getFullDef(int chance, String def) {
        if (chance >= 10 && chance < 100) {
            return def.concat(" + " + chance + "%");
        } else return def.concat(" + " + chance + " %");
    }
    
    public String getDef(String def) {
        return def.substring(0, def.length() - 3);
    }
    
    public int getChance(String def) {
        if (def.substring(def.length() - 2, def.length() - 1).matches(" ")) {
            return Integer.parseInt(def.substring(def.length() - 3, def.length() - 2));
        } else return Integer.parseInt(def.substring(def.length() - 3, def.length() - 1));
    }
    
    public void generate() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(map.keySet());
        Iterator iterator = list.iterator();
        while(list.size() > 1 && iterator.hasNext()) {
                if(!random((int) iterator.next())){
                    iterator.remove();
                }
        }
        outputLabel.setText(String.valueOf(map.get(list.get(0))));
    }
    
    public boolean random(int chance) {
        int max = 99, min = 1, r;
        max -= min;
        r = (int) (Math.random() * ++max) + min;
        return chance >= r;
    }
    
    @FXML private javafx.scene.control.Button textGen;
    @FXML public void handleButtonActionText(ActionEvent event) throws IOException {
        //Close current
        Stage stage = (Stage) textGen.getScene().getWindow();
        // do what you have to do
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        Scene scene = new Scene(root1, 350, 400);
        scene.getStylesheets().add("/styles/styles.css");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rdozer");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML private javafx.scene.control.Button numGen;
    @FXML public void handleButtonActionNums(ActionEvent event) throws IOException {
        //Close current
        Stage stage = (Stage) numGen.getScene().getWindow();
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
    
    public void clear() {
        if (!defList.getItems().isEmpty()){
            map.clear();
            defList.getItems().removeAll(defList.getItems());
            defList.setValue(null);
        }
    }
    
    public void remove() {
            if (defList.getSelectionModel().getSelectedItem() != null){
                String s = String.valueOf(defList.getSelectionModel().getSelectedItem());
                defList.getItems().remove(defList.getSelectionModel().getSelectedItem());
                map.remove(getChance(s));
                defList.setValue(null);
            }
    }
}
