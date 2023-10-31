package SD94.convert;

import SD94.dto.ProductDetailsDTO;
import SD94.entity.sanPham.SanPhamChiTiet;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailsConvert {

    public ProductDetailsDTO toDto(SanPhamChiTiet entity) {
        ProductDetailsDTO dto = new ProductDetailsDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getSoLuong());
        dto.setStatus(entity.getTrangThai());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setSanPham(entity.getSanPham());
        dto.setMauSac(entity.getMauSac());
        dto.setSize(entity.getKichCo());
        return dto;
    }

    public SanPhamChiTiet toEntity(ProductDetailsDTO dto) {
        SanPhamChiTiet entity = new SanPhamChiTiet();
        entity.setId(dto.getId());
        entity.setSoLuong(dto.getQuantity());
        entity.setTrangThai(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setSanPham(dto.getSanPham());
        entity.setMauSac(dto.getMauSac());
        entity.setKichCo(dto.getSize());
        return entity;
    }

}
