package com.mycompany.the_coffee_farm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrderScreen_Controller implements Initializable {

    @FXML
    private VBox vboxGioHang;
    @FXML
    private Button btnSuaGioHang;
    @FXML
    private RadioButton rdoOnline;
    @FXML
    private RadioButton rdoTaiQuay;
    @FXML
    private Label lblTongTien;
    @FXML
    private CheckBox chkTatCa;

    private boolean dangSuaMode = false;
    private List<Button> danhSachNutXoa = new ArrayList<>();

    class DongGioHang {

        CheckBox chkChonMua;
        Label lblSoLuong;
        int giaTienMotMon;
        HBox uiNode;
        String tenMon;
    }
    private List<DongGioHang> danhSachMonTrongGio = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (TaiKhoan.phuongThucNhan == 1) {
            rdoTaiQuay.setDisable(true);
            rdoTaiQuay.setStyle("-fx-opacity: 0.4;");
            rdoOnline.setSelected(true);
        } else {
            rdoOnline.setSelected(true);
        }

        taiDuLieuTuGioHangChung();
        capNhatTongTien();
    }

    private void taiDuLieuTuGioHangChung() {
        vboxGioHang.getChildren().clear();
        danhSachNutXoa.clear();
        danhSachMonTrongGio.clear();

        for (String tenMon : TaiKhoan.gioHangChung.keySet()) {
            int soLuongHienTai = TaiKhoan.gioHangChung.get(tenMon)[0];
            int giaTien = TaiKhoan.gioHangChung.get(tenMon)[1];

            HBox dongMonHang = new HBox(15);
            dongMonHang.setStyle("-fx-padding: 10; -fx-background-color: #f5f5f5; -fx-background-radius: 10; -fx-alignment: center-left;");

            CheckBox chkChonMua = new CheckBox();
            chkChonMua.setStyle("-fx-cursor: hand;");
            chkChonMua.setOnAction(e -> {
                capNhatTongTien();
                kiemTraNutTatCa();
            });

            Label lblTen = new Label(tenMon);
            lblTen.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

            HBox khongGianTrong = new HBox();
            HBox.setHgrow(khongGianTrong, Priority.ALWAYS);

            Button btnTru = new Button("-");
            btnTru.setStyle("-fx-background-color: #dddddd; -fx-cursor: hand; -fx-background-radius: 5;");
            Label lblSoLuong = new Label(String.valueOf(soLuongHienTai));
            lblSoLuong.setStyle("-fx-font-weight: bold; -fx-padding: 0 10 0 10;");
            Button btnCong = new Button("+");
            btnCong.setStyle("-fx-background-color: #dddddd; -fx-cursor: hand; -fx-background-radius: 5;");

            btnCong.setOnAction(e -> {
                int sl = Integer.parseInt(lblSoLuong.getText());
                lblSoLuong.setText(String.valueOf(sl + 1));
                TaiKhoan.gioHangChung.get(tenMon)[0] = sl + 1;
                capNhatTongTien();
            });

            btnTru.setOnAction(e -> {
                int sl = Integer.parseInt(lblSoLuong.getText());
                if (sl > 1) {
                    lblSoLuong.setText(String.valueOf(sl - 1));
                    TaiKhoan.gioHangChung.get(tenMon)[0] = sl - 1;
                    capNhatTongTien();
                }
            });
            HBox boxSoLuong = new HBox(5, btnTru, lblSoLuong, btnCong);
            boxSoLuong.setStyle("-fx-alignment: center; -fx-padding: 0 10 0 0;");

            String giaChu = String.format("%,dđ", giaTien).replace(",", ".");
            Label lblGia = new Label(giaChu);
            lblGia.setStyle("-fx-pref-width: 70px; -fx-alignment: center-right;");

            Button btnXoa = new Button("X");
            btnXoa.setStyle("-fx-background-color: #ff3333; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-cursor: hand;");
            btnXoa.setVisible(false);
            danhSachNutXoa.add(btnXoa);

            DongGioHang dongDuLieu = new DongGioHang();
            dongDuLieu.chkChonMua = chkChonMua;
            dongDuLieu.lblSoLuong = lblSoLuong;
            dongDuLieu.giaTienMotMon = giaTien;
            dongDuLieu.uiNode = dongMonHang;
            dongDuLieu.tenMon = tenMon;
            danhSachMonTrongGio.add(dongDuLieu);

            btnXoa.setOnAction(e -> {
                vboxGioHang.getChildren().remove(dongMonHang);
                danhSachNutXoa.remove(btnXoa);
                danhSachMonTrongGio.remove(dongDuLieu);
                TaiKhoan.gioHangChung.remove(tenMon);
                capNhatTongTien();
            });

            dongMonHang.getChildren().addAll(chkChonMua, lblTen, khongGianTrong, boxSoLuong, lblGia, btnXoa);
            vboxGioHang.getChildren().add(dongMonHang);
        }
    }

    private void capNhatTongTien() {
        int tongCong = 0;
        for (DongGioHang dong : danhSachMonTrongGio) {
            if (dong.chkChonMua.isSelected()) {
                int sl = Integer.parseInt(dong.lblSoLuong.getText());
                tongCong += (sl * dong.giaTienMotMon);
            }
        }
        lblTongTien.setText(String.format("%,dđ", tongCong).replace(",", "."));
    }

    @FXML
    private void xuLyChonTatCa(ActionEvent event) {
        boolean chonHetKhong = chkTatCa.isSelected();
        for (DongGioHang dong : danhSachMonTrongGio) {
            dong.chkChonMua.setSelected(chonHetKhong);
        }
        capNhatTongTien();
    }

    private void kiemTraNutTatCa() {
        boolean tatCaDeuTick = true;
        for (DongGioHang dong : danhSachMonTrongGio) {
            if (!dong.chkChonMua.isSelected()) {
                tatCaDeuTick = false;
                break;
            }
        }
        chkTatCa.setSelected(tatCaDeuTick);
    }

    @FXML
    private void xuLySuaGioHang(ActionEvent event) {
        dangSuaMode = !dangSuaMode;
        if (dangSuaMode) {
            btnSuaGioHang.setText("Xong");
            for (Button nutXoa : danhSachNutXoa) {
                nutXoa.setVisible(true);
            }
        } else {
            btnSuaGioHang.setText("Sửa");
            for (Button nutXoa : danhSachNutXoa) {
                nutXoa.setVisible(false);
            }
        }
    }

    @FXML
    private void xuLyQuayLai(ActionEvent event) {
        if (TaiKhoan.sceneTruocKhiVaoGio != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(TaiKhoan.sceneTruocKhiVaoGio);
        }
    }
    @FXML
    private javafx.scene.layout.AnchorPane lopPhuQR;

    @FXML
    private void xuLyMuaHang(ActionEvent event) {
        int tongTien = Integer.parseInt(lblTongTien.getText().replace(".", "").replace("đ", ""));
        if (tongTien == 0) {
            return;
        }

        if (rdoOnline.isSelected()) {
            lopPhuQR.setVisible(true);
        } else if (rdoTaiQuay.isSelected()) {
            chuyenTrang(event, "DanhSachCoSo.fxml");
        }
    }

    @FXML
    private void xacNhanDaQuetQR(javafx.scene.input.MouseEvent event) {
        lopPhuQR.setVisible(false);
        chuyenTrangSauKhiBamVungNgoai(event, "MuaHangThanhCong(Onl).fxml");
    }

    private void chuyenTrangSauKhiBamVungNgoai(javafx.scene.input.MouseEvent event, String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chuyenTrang(ActionEvent event, String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
