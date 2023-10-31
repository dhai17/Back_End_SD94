package SD94.convert;

import SD94.dto.ProductColorDTO;
import SD94.entity.sanPham.MauSac;
import org.springframework.stereotype.Component;

@Component
public class ProductColorConvert {

    public ProductColorDTO toDto(MauSac entity) {
        ProductColorDTO dto = new ProductColorDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getMaMauSac());
        dto.setColor(entity.getTenMauSac());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        return dto;
    }

    public MauSac toEntity(ProductColorDTO dto) {
        MauSac entity = new MauSac();
        entity.setId(dto.getId());
        entity.setMaMauSac(dto.getCode());
        entity.setTenMauSac(dto.getColor());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        return entity;
    }
}
