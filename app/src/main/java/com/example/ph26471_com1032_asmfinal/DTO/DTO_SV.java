package com.example.ph26471_com1032_asmfinal.DTO;

public class DTO_SV {
    String hoten, nganh, email, sodt, lop;
    int id,id_class;

    public DTO_SV() {
    }

    public DTO_SV(int id, String hoten, String nganh, String email, String sodt,int id_class, String lop) {
        this.id= id;
        this.hoten = hoten;
        this.nganh = nganh;
        this.email = email;
        this.sodt = sodt;
        this.lop = lop;
        this.id_class= id_class;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    @Override
    public String toString() {
        return
                "ID: " + id + "\n" +
                "Họ Tên: " + hoten + "\n" +
                "Ngành: " + nganh + "\n" +
                "Email: " + email + "\n" +
                "Số ĐT: " + sodt + "\n" +
                "ID Lớp: " + id_class + "\n"+
                        "Tên Lớp: " + lop + "\n"
                ;
    }
}
