package SD94.convert;

import SD94.dto.ProductSizeDTO;
import SD94.entity.sanPham.KichCo;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeConvert {

    public ProductSizeDTO toDto(KichCo entity) {
        ProductSizeDTO dto = new ProductSizeDTO();
        dto.setId(entity.getId());
        dto.setShoeSize(entity.getKichCo());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public KichCo toEntity(ProductSizeDTO dto) {
        KichCo entity = new KichCo();
        entity.setId(dto.getId());
        entity.setKichCo(dto.getShoeSize());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setDeleted(dto.isDeleted());
        return entity;
    }
}
