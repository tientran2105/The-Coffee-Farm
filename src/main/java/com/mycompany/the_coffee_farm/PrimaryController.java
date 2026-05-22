package com.mycompany.the_coffee_farm; // Giữ nguyên dòng package của m

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

public class PrimaryController implements Initializable {

    @FXML
    private ImageView bannerImage;

    private int imageIndex = 0;
    
    // Đổi lại tên file ảnh cho đúng với trong máy m
    private String[] danhSachAnh = {
        "/com/mycompany/the_coffee_farm/images/anh1.jpg",
        "/com/mycompany/the_coffee_farm/images/anh2.jpg",
        "/com/mycompany/the_coffee_farm/images/anh3.jpg",
        "/com/mycompany/the_coffee_farm/images/anh4.jpg"
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rectangle khuonBoGoc = new Rectangle(350,200);
        khuonBoGoc.setArcWidth(30);
        khuonBoGoc.setArcHeight(30);
        bannerImage.setClip(khuonBoGoc);
        try {
            bannerImage.setImage(new Image(getClass().getResourceAsStream(danhSachAnh[0])));
            
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                imageIndex++;
                if (imageIndex >= danhSachAnh.length) imageIndex = 0;
                try {
                    bannerImage.setImage(new Image(getClass().getResourceAsStream(danhSachAnh[imageIndex])));
                } catch (Exception e) {}
            }));
            
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        } catch (Exception e) {
            System.out.println("Lỗi đường dẫn ảnh!");
        }
    }

    @FXML
    public void chuyenTrangThongTin(ActionEvent event) {
        try {
javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        
        // 2. Tải file giao diện trang Thông tin doanh nghiệp (ThongTin.fxml)
        javafx.scene.Parent trangThongTin = javafx.fxml.FXMLLoader.load(getClass().getResource("ThongTinDoanhNghiep.fxml"));
        
        // 3. Tạo một cảnh mới (Scene) chứa trang Thông tin vừa load
        javafx.scene.Scene sceneMoi = new javafx.scene.Scene(trangThongTin);
        
        // 4. Thay thế toàn bộ màn hình hiện tại thành trang Thông tin để đọc
        stage.setScene(sceneMoi);
        } catch (IOException e) {
            System.out.println("Lỗi không load được file thông tin!");
        }
    }
    
   // === VÀO NHÁNH 1: NGUYÊN LIỆU THÔ ===
    @FXML
    public void vaoNguyenLieuTho(javafx.event.ActionEvent event) {
        try {
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            // 1. Lôi cái Vỏ có thanh 4 nút đáy ra
            javafx.scene.layout.BorderPane voChinh = javafx.fxml.FXMLLoader.load(getClass().getResource("ThanhChucNang.fxml"));
            // 2. Lôi cái Ruột của nhánh này ra
            javafx.scene.Parent ruotNLT = javafx.fxml.FXMLLoader.load(getClass().getResource("NguyenLieuTho.fxml"));
            // 3. Nhét ruột vào giữa vỏ
            voChinh.setCenter(ruotNLT);
            // 4. Lên đèn!
            stage.setScene(new javafx.scene.Scene(voChinh));
        } catch (Exception e) {
            System.out.println("Lỗi vào Nguyên liệu thô!");
            e.printStackTrace();
        }
    }

    // === VÀO NHÁNH 2: NGUYÊN LIỆU SƠ CHẾ ===
    @FXML
    public void vaoNguyenLieuSoChe(javafx.event.ActionEvent event) {
        try {
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            javafx.scene.layout.BorderPane voChinh = javafx.fxml.FXMLLoader.load(getClass().getResource("ThanhChucNang.fxml"));
            javafx.scene.Parent ruotNLSC = javafx.fxml.FXMLLoader.load(getClass().getResource("NguyenLieuSoChe.fxml"));
            voChinh.setCenter(ruotNLSC);
            stage.setScene(new javafx.scene.Scene(voChinh));
        } catch (Exception e) {
            System.out.println("Lỗi vào Nguyên liệu sơ chế!");
            e.printStackTrace();
        }
    }

    // === VÀO NHÁNH 3: FOOD AND DRINK ===
    @FXML
    public void vaoFoodAndDrink(javafx.event.ActionEvent event) {
        try {
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            javafx.scene.layout.BorderPane voChinh = javafx.fxml.FXMLLoader.load(getClass().getResource("ThanhChucNang.fxml"));
            javafx.scene.Parent ruotFood = javafx.fxml.FXMLLoader.load(getClass().getResource("FoodAndDrink.fxml"));
            voChinh.setCenter(ruotFood);
            stage.setScene(new javafx.scene.Scene(voChinh));
        } catch (Exception e) {
            System.out.println("Lỗi vào Food and Drink!");
            e.printStackTrace();
        }
    }
}