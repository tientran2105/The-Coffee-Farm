package com.mycompany.the_coffee_farm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Account_Screen_Controller implements Initializable {

    @FXML private javafx.scene.Node btnLichSu;
    @FXML private javafx.scene.Node btnTimKiem;
    @FXML private javafx.scene.Node btnUuDai;
    @FXML private javafx.scene.Node btnDanhGia;
    @FXML private javafx.scene.Node btnThongTin;
    @FXML private javafx.scene.Node btnDiaChi;
    @FXML private javafx.scene.Node btnDangXuat;

    @FXML private javafx.scene.layout.AnchorPane lopPhuDangXuat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean chuaLogin = !TaiKhoan.daDangNhap;

        if (btnLichSu != null) btnLichSu.setDisable(chuaLogin);
        if (btnTimKiem != null) btnTimKiem.setDisable(chuaLogin);
        if (btnUuDai != null) btnUuDai.setDisable(chuaLogin);
        if (btnDanhGia != null) btnDanhGia.setDisable(chuaLogin);
        if (btnThongTin != null) btnThongTin.setDisable(chuaLogin);
        if (btnDiaChi != null) btnDiaChi.setDisable(chuaLogin);
        if (btnDangXuat != null) btnDangXuat.setDisable(chuaLogin);
    }

    @FXML
    private void moDieuKhoan(javafx.event.ActionEvent event) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("DieuKhoan.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(root);

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Lỗi chuyển sang Điều Khoản!");
            e.printStackTrace();
        }
    }

    @FXML
    private void moLichSuDonHang(javafx.event.ActionEvent event) {
        System.out.println("Đã xác nhận tài khoản. Đang tải Lịch sử đơn hàng!");
    }

    @FXML
    private void moTimKiemDonHang(javafx.event.ActionEvent event) {
        System.out.println("Đã xác nhận tài khoản. Đang tải Tìm kiếm đơn hàng!");
    }

    @FXML
    private void moUuDaiCuaBan(javafx.event.ActionEvent event) {
        System.out.println("Đã xác nhận tài khoản. Đang tải Ưu đãi của bạn!");
    }

    @FXML
    public void xuLyDangXuat(javafx.event.ActionEvent event) {
        TaiKhoan.daDangNhap = false; 
        TaiKhoan.gioHangChung.clear();
        TaiKhoan.phuongThucNhan = 0;

        if (lopPhuDangXuat != null) {
            lopPhuDangXuat.setVisible(true);
        }
    }

    @FXML
    public void chuyenVePrimary(javafx.scene.input.MouseEvent event) {
        try {
            lopPhuDangXuat.setVisible(false);

            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("primary.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            System.out.println("Lỗi không về trang chủ được!");
            e.printStackTrace();
        }
    }
        @FXML
    private void quayLaiAccount(javafx.event.ActionEvent event) {
        try {
            javafx.scene.Parent voChinh = javafx.fxml.FXMLLoader.load(getClass().getResource("ThanhChucNang.fxml"));
            javafx.scene.Parent ruotAccount = javafx.fxml.FXMLLoader.load(getClass().getResource("Account_Screen.fxml"));

            javafx.scene.layout.BorderPane khungChinh = (javafx.scene.layout.BorderPane) voChinh.lookup("#khungChinh");
            khungChinh.setCenter(ruotAccount);

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(voChinh));
            stage.show();

        } catch (Exception e) {
            System.out.println("Lỗi lúc quay về màn Account!");
            e.printStackTrace();
        }
    }
    @FXML
    private void moLienHeVaGopY(javafx.event.ActionEvent event) {
        try {
            // "LienHeVaGopY.fxml" phải khớp chính xác với tên file FXML m vừa làm
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("LienHeVaGopY.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            
            // Lấy Stage hiện tại để chuyển cảnh
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
            // Quan trọng: Lưu vết trang Tài khoản để nút Back nó biết đường về
            TaiKhoan.trangTruocDo = "Account_Screen.fxml";
            
        } catch (Exception e) {
            System.out.println("Lỗi không sang được trang Liên hệ!");
            e.printStackTrace();
        }
    }
}