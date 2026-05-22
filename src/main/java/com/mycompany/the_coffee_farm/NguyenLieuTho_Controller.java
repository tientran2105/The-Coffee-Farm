
package com.mycompany.the_coffee_farm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NguyenLieuTho_Controller {
        @FXML
    private Button btnBack;
    
        @FXML
    public void veTrangChu(ActionEvent event){
        try{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent rooPrimary = FXMLLoader.load(getClass().getResource("primary.fxml"));
            Scene sceneMoi = new Scene(rooPrimary);
            stage.setScene(sceneMoi);
        } catch(Exception e){
            System.out.println("Lỗi không quay lại được trang trước!");
            e.printStackTrace();
        }
    }
}
