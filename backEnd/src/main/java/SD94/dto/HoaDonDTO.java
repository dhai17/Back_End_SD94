package SD94.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class HoaDonDTO {

    private Long id;

    private String maHoaDon;

    private String ghiChu;

    private String email;

    private String soDienThoai;

    private int tienShip;

    private int tongTienHoaDon;

    private int tongTienDonHang;

    private List<Long> id_hoaDon;

    private String email_user;

    private String diaChi;

    private String tenMaGiamGia;

    private String nguoiTao;

    private String nguoi_thao_tac;

    private int loaiHoaDon;

    private int TienTraLai;


    public int getTienTraLai() {
        return TienTraLai;
    }

    public void setTienTraLai(int tienTraLai) {
        TienTraLai = tienTraLai;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getTienShip() {
        return tienShip;
    }

    public void setTienShip(int tienShip) {
        this.tienShip = tienShip;
    }

    public int getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(int tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public int getTongTienDonHang() {
        return tongTienDonHang;
    }

    public void setTongTienDonHang(int tongTienDonHang) {
        this.tongTienDonHang = tongTienDonHang;
    }

    public List<Long> getId_hoaDon() {
        return id_hoaDon;
    }

    public void setId_hoaDon(List<Long> id_hoaDon) {
        this.id_hoaDon = id_hoaDon;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenMaGiamGia() {
        return tenMaGiamGia;
    }

    public void setTenMaGiamGia(String tenMaGiamGia) {
        this.tenMaGiamGia = tenMaGiamGia;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoi_thao_tac() {
        return nguoi_thao_tac;
    }

    public void setNguoi_thao_tac(String nguoi_thao_tac) {
        this.nguoi_thao_tac = nguoi_thao_tac;
    }

    public int getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(int loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }
}
