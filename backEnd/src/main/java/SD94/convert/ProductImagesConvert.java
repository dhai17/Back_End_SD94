package SD94.convert;

import SD94.dto.ProductImagesDTO;
import SD94.entity.sanPham.HinhAnh;
import org.springframework.stereotype.Component;

@Component
public class ProductImagesConvert {

    public ProductImagesDTO toDto(HinhAnh entity) {
        ProductImagesDTO dto = new ProductImagesDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getTenAnh());
//        dto.setImg(entity.getImg());
        dto.setSanPhamChiTiet(entity.getSanPhamChiTiet());
        dto.setMauSac(entity.getMauSac());
        return dto;
    }

    public HinhAnh toEntity(ProductImagesDTO dto) {
        HinhAnh entity = new HinhAnh();
        entity.setId(dto.getId());
        entity.setTenAnh(dto.getName());
//        entity.setImg(dto.getImg());
        entity.setSanPhamChiTiet(dto.getSanPhamChiTiet());
        entity.setMauSac(dto.getMauSac());
        return entity;
    }
}
