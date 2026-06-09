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
    public void veTrangChu(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent rooPrimary = FXMLLoader.load(getClass().getResource("primary.fxml"));
            Scene sceneMoi = new Scene(rooPrimary);
            stage.setScene(sceneMoi);
        } catch (Exception e) {
            System.out.println("Lỗi không quay lại được trang trước!");
            e.printStackTrace();
        }
    }

@FXML
    private void ThemGioHang(ActionEvent event) {
        javafx.scene.control.Button btnClicked = (javafx.scene.control.Button) event.getSource();
        javafx.scene.layout.AnchorPane theMon = (javafx.scene.layout.AnchorPane) btnClicked.getParent();

        String tenMon = "";
        String chuoiGia = "";

        for (javafx.scene.Node node : theMon.getChildren()) {
            if (node instanceof javafx.scene.control.Label) {
                javafx.scene.control.Label lbl = (javafx.scene.control.Label) node;
                if (lbl.getText().contains("đ")) {
                    chuoiGia = lbl.getText();
                } else {
                    tenMon = lbl.getText();
                }
            }
        }

        int giaTien = 0;
        try {
            String soNguyen = chuoiGia.replaceAll("[^0-9]", "");
            giaTien = Integer.parseInt(soNguyen);
        } catch (Exception e) {
            System.out.println("Lỗi đọc giá tiền món: " + tenMon);
        }
        if (TaiKhoan.gioHangChung.containsKey(tenMon)) {
            TaiKhoan.gioHangChung.get(tenMon)[0] += 1;
            System.out.println(">>> Đã +1 số lượng món: " + tenMon + " (Tổng: " + TaiKhoan.gioHangChung.get(tenMon)[0] + ")");
        } else {
            TaiKhoan.gioHangChung.put(tenMon, new int[]{1, giaTien});
            System.out.println(">>> Vừa thêm mới vào giỏ: " + tenMon + " | Giá: " + giaTien);
        }
        
    }
}
