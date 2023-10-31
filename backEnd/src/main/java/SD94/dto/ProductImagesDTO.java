package SD94.dto;

import SD94.entity.sanPham.MauSac;
import SD94.entity.sanPham.SanPhamChiTiet;
import lombok.Data;

@Data
public class ProductImagesDTO {

    private long id;

    private String img;

    private String name;

    private MauSac mauSac;

    private SanPhamChiTiet sanPhamChiTiet;

}
