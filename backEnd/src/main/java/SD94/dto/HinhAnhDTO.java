package SD94.dto;

import SD94.entity.sanPham.MauSac;
import SD94.entity.sanPham.SanPhamChiTiet;
import lombok.Data;

import java.util.List;

@Data
public class HinhAnhDTO {
    private long id_hinh_anh;

    private long id_spct;

    private List<String> ten_anh;

    private Long id_SP;

    private String maMauSac;


}
