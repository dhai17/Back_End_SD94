package SD94.convert;

import SD94.dto.ProductLineDTO;
import SD94.entity.ProductLine;
import org.springframework.stereotype.Component;

@Component
public class ProductLineConvert {

    public ProductLineDTO toDto(ProductLine entity) {
        ProductLineDTO dto = new ProductLineDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public ProductLine toEntity(ProductLineDTO dto) {
        ProductLine entity = new ProductLine();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setDeleted(dto.isDeleted());
        return entity;
    }
}
