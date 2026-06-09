package com.mycompany.the_coffee_farm;

public class LoaiSanPham {
    private int idLoai;
    private String tenLoai;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int idLoai, String tenLoai) {
        this.idLoai = idLoai;
        this.tenLoai = tenLoai;
    }

    public int getIdLoai() { return idLoai; }
    public void setIdLoai(int idLoai) { this.idLoai = idLoai; }

    public String getTenLoai() { return tenLoai; }
    public void setTenLoai(String tenLoai) { this.tenLoai = tenLoai; }
    
    @Override
    public String toString() {
        return this.tenLoai;
    }
}