package SD94.convert;

import SD94.dto.ProductDTO;
import SD94.entity.sanPham.SanPham;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert {

    public ProductDTO toDto(SanPham entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getTenSanPham());
        dto.setPrice(entity.getGia());
        dto.setStatus(entity.getTrangThai());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setIdChatLieu(entity.getChatLieu());
        dto.setIdLoaiSanPham(entity.getLoaiSanPham());
        dto.setIdNhaSanXuat(entity.getNhaSanXuat());
        return dto;
    }

    public SanPham toEntity(ProductDTO dto) {
        SanPham entity = new SanPham();
        entity.setId(dto.getId());
        entity.setTenSanPham(dto.getName());
        entity.setGia(dto.getPrice());
        entity.setTrangThai(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setChatLieu(dto.getIdChatLieu());
        entity.setLoaiSanPham(dto.getIdLoaiSanPham());
        entity.setNhaSanXuat(dto.getIdNhaSanXuat());
        return entity;
    }
}
