package SD94.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
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
}
