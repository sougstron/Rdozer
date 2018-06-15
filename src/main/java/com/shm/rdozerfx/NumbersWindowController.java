/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shm.rdozerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Shmonya
 */
public class NumbersWindowController implements Initializable {

    @FXML private TextField firstNumberField;
    @FXML private TextField endNumberField;
    @FXML private TextField numGensField;
    @FXML private CheckBox isUniqNums;
    @FXML private Label outputLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML private javafx.scene.control.Button textgen;
    @FXML public void handleButtonAction(ActionEvent event) throws IOException {
        //Close current
        Stage stage = (Stage) textgen.getScene().getWindow();
        // do what you have to do
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        Scene scene = new Scene(root1, 300, 300);
        scene.getStylesheets().add("/styles/styles.css");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rdozer");
        stage.setScene(scene);
        stage.show();
    }
    
    public void generate(){
        try {
            if (!firstNumberField.getText().isEmpty() || !endNumberField.getText().isEmpty()){
                int r, count;
                int min = Integer.parseInt(firstNumberField.getText());
                int max = Integer.parseInt(endNumberField.getText());
                if (numGensField.getText().isEmpty()){
                    count = 1;
                } else count = Integer.parseInt(numGensField.getText());
                if (min > max){
                    r = min;
                    min = max;
                    max = r;
                }
                boolean b;
                if (isUniqNums.isIndeterminate()){
                    b = false;
                } else b = isUniqNums.isSelected();
                outputLabel.setText(random.nextInt(min, max, count, b)); //Output to screen
            } else
                outputLabel.setText("Enter some numbers in both fields.");
        } catch (NumberFormatException e) {outputLabel.setText("Don't try to type text right there...");}
    }

    public static class random {
        //Generating random number from range
        public static String nextInt (int min, int max, int count, boolean uniq) {
            String out = "";
            int r, cg = 0, tc = max - min;
            ArrayList<Integer> list = new ArrayList<>();
            for (int x = 0; x < count; x++) {
                    max -= min;
                    r = (int) (Math.random() * ++max) + min;
                    --max;
                    max += min;
                    if (cg <= tc && uniq && count - 1 <= tc) {
                        while (list.contains(r)) {
                            max -= min;
                            r = (int) (Math.random() * ++max) + min;
                            --max;
                            max += min;
                        }
                    }
                    list.add(r);
                    cg++;
                    if ((x+1) == count) {
                        out = out + r;
                    } else out = out + r + ", ";
            }
            list.clear();
            return out;
        }
    }
    
}