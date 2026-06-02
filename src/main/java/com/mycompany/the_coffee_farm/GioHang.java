package com.mycompany.the_coffee_farm;

import java.util.ArrayList;
import java.util.List;

public class GioHang {
    
    // Class phụ để cấu trúc 1 món hàng trong giỏ
    public static class MonHang {
        public String tenMon;
        public int giaTien;
        public int soLuong;

        public MonHang(String tenMon, int giaTien, int soLuong) {
            this.tenMon = tenMon;
            this.giaTien = giaTien;
            this.soLuong = soLuong;
        }
    }

    // Cái giỏ hàng tĩnh này sẽ sống suốt quá trình bật app, đi đâu cũng gọi được
    public static List<MonHang> danhSachMua = new ArrayList<>();
}
