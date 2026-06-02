
package com.mycompany.the_coffee_farm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NguyenLieuSoChe_Controller {
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
    
@FXML
    private void ThemGioHang(ActionEvent event) {
        // 1. Tóm cổ xem cái nút '+' NÀO vừa bị bấm
        javafx.scene.control.Button btnClicked = (javafx.scene.control.Button) event.getSource();
        
        // 2. Lấy cái thẻ (AnchorPane) đang chứa cái nút '+' đó
        javafx.scene.layout.AnchorPane theMon = (javafx.scene.layout.AnchorPane) btnClicked.getParent();
        
        String tenMon = "";
        String chuoiGia = "";
        
        // 3. Lục soát trong thẻ đó để moi tên và giá ra
        for (javafx.scene.Node node : theMon.getChildren()) {
            if (node instanceof javafx.scene.control.Label) {
                javafx.scene.control.Label lbl = (javafx.scene.control.Label) node;
                
                if (lbl.getText().contains("đ")) {
                    chuoiGia = lbl.getText(); // Nếu có chữ "đ" thì nó là nhãn Giá
                } else {
                    tenMon = lbl.getText();   // Không có chữ "đ" thì nó là nhãn Tên món
                }
            }
        }
        
        // 4. Bóc tách lấy đúng con số từ chuỗi giá (VD: "40.000đ/Hộp" -> 40000)
        int giaTien = 0;
        try {
            String soNguyen = chuoiGia.replaceAll("[^0-9]", ""); // Xóa hết chữ và dấu đi
            giaTien = Integer.parseInt(soNguyen);
        } catch (Exception e) {
            System.out.println("Lỗi đọc giá tiền món: " + tenMon);
        }

        // 5. Kiểm tra xem món này đã có trong Giỏ Hàng chưa
       // 5. Kiểm tra xem món này đã có trong Giỏ Hàng chưa
        boolean daCoTrongGio = false;
        int soLuongHienTai = 1; // Thêm biến này để theo dõi số lượng

        for (GioHang.MonHang mh : GioHang.danhSachMua) {
            if (mh.tenMon.equals(tenMon)) {
                mh.soLuong += 1; // Có rồi thì cộng thêm 1 hộp/ly
                soLuongHienTai = mh.soLuong; // Cập nhật số lượng để in ra
                daCoTrongGio = true;
                break;
            }
        }
        
        // 6. Nếu chưa có thì ném nó vào giỏ (số lượng mặc định là 1)
        if (!daCoTrongGio) {
            GioHang.danhSachMua.add(new GioHang.MonHang(tenMon, giaTien, 1));
        }
        
        // 7. In ra Console đầy đủ số lượng luôn
        System.out.println(">>> Vừa thêm: " + tenMon + " | Giá: " + giaTien + " | Số lượng: " + soLuongHienTai);
    }
}
