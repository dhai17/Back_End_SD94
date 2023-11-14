package SD94.dto;

import SD94.entity.sanPham.NhaSanXuat;
import SD94.entity.sanPham.LoaiSanPham;
import SD94.entity.sanPham.ChatLieu;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.Date;
import java.util.List;

@Data
public class SanPhamDTO {

    private Long id;

    private String tenSanPham;

    private Float gia;

    private String origin;

    private Integer trangThai;

    private Date ngayTao;

    private String nguoiTao;

    private ChatLieu chatLieu;

    private LoaiSanPham loaiSanPham;

    private NhaSanXuat nhaSanXuat;

    private List<Long> kichCo;

    private List<Long> mauSac;

    private long chatLieu_id;

    private long loaiSanPham_id;

    private long nhaSanXuat_id;

    //Dùng để add to cart bên phía customer
    private String maMauSac;

    private String kichCoDaChon;

    private Long san_pham_id;

    private int soLuongDaChon;

    private int donGia;

    private int soLuong;

    private int tongTien;

    private Long id_hoaDon;

    private Long anh_id;
}
