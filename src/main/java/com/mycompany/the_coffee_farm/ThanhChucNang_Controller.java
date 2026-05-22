
package com.mycompany.the_coffee_farm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ThanhChucNang_Controller {
    
    @FXML
    private javafx.scene.control.Button btnShippingMethod; 

    @FXML
    private javafx.scene.layout.AnchorPane lopPhuShippingMethod;
   
    @FXML
    public void bamNutShippingMethod(javafx.event.ActionEvent event) {
        // Bật lớp phủ lên (hiện bảng chọn)
        if (lopPhuShippingMethod != null) {
            lopPhuShippingMethod.setVisible(true);
        } else {
            System.out.println("Lỗi: Chưa nối ID lớp phủ bên Scene Builder!");
        }
    }
    @FXML
    public void chonGiaoHang(javafx.event.ActionEvent event) {
        System.out.println("Bạn đã chọn: Giao hàng tận nơi");
        if(btnShippingMethod != null) {
            btnShippingMethod.setText("Giao tận nơi");
        }
        lopPhuShippingMethod.setVisible(false); 
    }

    @FXML
    public void chonMangDi(javafx.event.ActionEvent event) {
        System.out.println("Bạn đã chọn: Đến lấy mang đi");
        
        if(btnShippingMethod != null) {
            btnShippingMethod.setText("Mang đi");
        }
        lopPhuShippingMethod.setVisible(false); 
    }

    @FXML
    public void chonTaiQuan(javafx.event.ActionEvent event) {
        System.out.println("Bạn đã chọn: Dùng tại quán");
        
        if(btnShippingMethod != null) {
            btnShippingMethod.setText("Tại quán");
        }
        
        lopPhuShippingMethod.setVisible(false); 
    }
}