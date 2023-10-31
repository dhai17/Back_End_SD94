package SD94.convert;

import SD94.dto.ProductSizeDTO;
import SD94.entity.product.Size;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeConvert {

    public ProductSizeDTO toDto(Size entity) {
        ProductSizeDTO dto = new ProductSizeDTO();
        dto.setId(entity.getId());
        dto.setShoeSize(entity.getShoeSize());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public Size toEntity(ProductSizeDTO dto) {
        Size entity = new Size();
        entity.setId(dto.getId());
        entity.setShoeSize(dto.getShoeSize());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setDeleted(dto.isDeleted());
        return entity;
    }
}
